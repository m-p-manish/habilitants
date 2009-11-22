package an.chopsticks.provider.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import an.chopsticks.provider.Auditor;
import an.chopsticks.provider.Provider;
import an.chopsticks.service.AuditService;
import an.chopsticks.service.AuditSeverity;
import an.chopsticks.service.Context;
import an.chopsticks.service.ProviderInitializationException;
import an.chopsticks.service.Service;
import an.config.ConfigElement;
import an.control.OperationFailedException;
import an.control.Status;
import an.log.DefaultLogger;
import an.log.LogFactory;
import an.log.Logger;

public class FileAuditor extends DefaultLogger implements Auditor {
    static final String ATTR_LOGPATH = "path";
    static final String ATTR_ROLLOVER_SIZE = "rolloverSize";
    static final String ATTR_SINGLELEVEL_MODE = "singleLevelMode";
    static final String ELEM_FORMATTER = "Formatter";
    static final String ATTR_OVERALLFORMAT = "overallFormat";
    static final String ELEMTYPE_ELEMENTFORMATTER = "ElementFormatterType";
    static final String ATTR_FORMATTERINTERFACE = "interface";
    static final String ATTR_ELEMENTLIST = "targetElementList";

    /**
     * All elements current auditor can produce.
     */
    public static final String AUDIT_ELEM_TIME = "time";
    public static final String AUDIT_ELEM_HOST = "host";
    public static final String AUDIT_ELEM_THREAD = "thread";
    public static final String AUDIT_ELEM_CODE = "codeline";
    public static final String AUDIT_ELEM_SEVERITY = "severity";
    public static final String AUDIT_ELEM_CATEGORY = "category";
    public static final String AUDIT_ELEM_MESSAGE = "message";
    public static final String AUDIT_ELEM_CONTEXT = "context";
    public static final String AUDIT_ELEM_EXCEPTION = "exception";
    private static final String[] AUDIT_ELEMENTS = {AUDIT_ELEM_TIME, AUDIT_ELEM_HOST, AUDIT_ELEM_THREAD,
        AUDIT_ELEM_CODE, AUDIT_ELEM_SEVERITY, AUDIT_ELEM_CATEGORY, AUDIT_ELEM_MESSAGE, AUDIT_ELEM_CONTEXT,
        AUDIT_ELEM_EXCEPTION};

    private Map<String, List<AuditElementFormatter>> formattersByElement =
        new HashMap<String, List<AuditElementFormatter>>();

    private AuditService service;

    private String providerName;
    private AuditSeverity configSeverity;
    private String categoryFilter;
    private boolean withContext;
    private String overallFormat;
    private String finalOverallFormat;
    private String[] sortedElements;
    private ConfigElement[] formattersConfig;

    public FileAuditor(Service service, ConfigElement config) throws ProviderInitializationException {
        try {
            this.service = (AuditService)service;
            loadConfigurations(config);
            System.out.println("Initializing File Audit provider '" + providerName + "' ...");
        }
        catch (Exception e) {
            throw new ProviderInitializationException("Error occurs while initializing FileAuditor.", e);
        }
        System.out.println("File Audit provider '" + providerName + "' initialized.");
    }

    protected void loadConfigurations(ConfigElement config) {
        providerName = (String)config.getAttributeValueByName(Provider.ATTR_PROVIDER_NAME);
        configSeverity = AuditSeverity.valueOf((String)config.getAttributeValueByName(Auditor.ATTR_SEVERITY));
        categoryFilter = (String)config.getAttributeValueByName(ATTR_CATEGORYFILTER);
        withContext = (Boolean)config.getAttributeValueByName(Auditor.ATTR_WITHCONTEXT);
        logFile = (String)config.getAttributeValueByName(ATTR_LOGPATH);
        rolloverSize = (Integer)config.getAttributeValueByName(ATTR_ROLLOVER_SIZE);
        singleLevelMode = (Boolean)config.getAttributeValueByName(ATTR_SINGLELEVEL_MODE);
        overallFormat = (String)config.getAttributeValueByName(ATTR_OVERALLFORMAT);
        formattersConfig = (ConfigElement[])config.getXMLElementsByType(ELEMTYPE_ELEMENTFORMATTER);
    }

    private void initialize() throws Exception {
        populateGlobalConfigurations();
        initializeWriter();
        initializeHost();
        initializeFormatters();
        initializeOverallFormat();
    }

    private void populateGlobalConfigurations() {
        // Update current settings with global ones.
        AuditSeverity globalSeverity = service.getGlobalSeverity();
        if (globalSeverity != null) {
            configSeverity = globalSeverity;
        }
        String globalCategory = service.getGlobalCategoryFilter();
        if (globalCategory != null) {
            categoryFilter = globalCategory;
        }
        if (service.isSetGlobalWithContext()) {
            withContext = service.getGlobalWithContext();
        }
    }

    private void initializeWriter() throws IOException {
        synchronized (outputMap) {
            // Each log file has a file writer, we don't create new ones for the same file. We kept the writer in a map.
            out = outputMap.get(logFile);
            if (out == null) {
                // We open the log file with append mode.
                out = new PrintWriter(new FileWriter(new File(logFile), true), true);
                // If there is error occurs, we print the log message to System default output instead.
                outputMap.put(logFile, out);
            }
        }
    }

    private void initializeHost() {
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException e) {
            hostName = "**Unknown Host**";
        }
    }

    private void initializeFormatters() throws Exception {
        if (formattersConfig != null && formattersConfig.length > 0) {
            for (int i = 0; i < formattersConfig.length; i ++) {
                ConfigElement each = formattersConfig[i];
                String intf = (String)each.getAttributeValueByName(ATTR_FORMATTERINTERFACE);
                String strElemList = (String)each.getAttributeValueByName(ATTR_ELEMENTLIST);
                // Remove all white spaces among elements
                String[] elemList = removeDuplicates(strElemList.trim().split("\\s*,\\s*"));
                // Instantial the formatter.
                Class<?> fmtClaz = Class.forName(intf);
                Constructor<?> cons = fmtClaz.getConstructor(ConfigElement.class);
                AuditElementFormatter fmt = (AuditElementFormatter)cons.newInstance(each);
                // Add the formatter to right place, indexed by element name.
                for (String elem : elemList) {
                    checkElementName(elem);
                    List<AuditElementFormatter> fmts = formattersByElement.get(elem);
                    if (fmts == null) {
                        fmts = new ArrayList<AuditElementFormatter>();
                        formattersByElement.put(elem, fmts);
                    }
                    fmts.add(fmt);
                }
            }
        }
    }

    private void initializeOverallFormat() {
        SortedMap<Integer, String> map = new TreeMap<Integer, String>();
        finalOverallFormat = new String(overallFormat);
        for (int x = 0; x < AUDIT_ELEMENTS.length; x ++) {
            String elem = AUDIT_ELEMENTS[x];
            finalOverallFormat = finalOverallFormat.replaceAll(elem, "s");
            int pos = overallFormat.indexOf(elem);
            // An element can occur one more times, so we search all of them.
            while (pos >= 0) {
                // Put all pos element pair to a sorted map, then we can get a list of sorted elements.
                map.put(pos, elem);
                pos = overallFormat.indexOf(elem, pos + elem.length());
            }
        }
        sortedElements = map.values().toArray(new String[0]);
    }

    private void checkElementName(String elem) {
        for (String name : AUDIT_ELEMENTS) {
            if (name.equals(elem)) {
                return;
            }
        }
        throw new IllegalArgumentException("Unrecognized element '" + elem + "'");
    }

    private static String[] removeDuplicates(String[] array) {
        List<String> temp = new ArrayList<String>();
        for (int i = 0; i < array.length; i ++) {
            if (!temp.contains(array[i])) {
                temp.add(array[i]);
            }
        }
        return temp.toArray(new String[0]);
    }

    private String compositeMessage(
            AuditSeverity severity, String category, String message, Context ctx, Throwable t) {
        StringBuffer strBuff = (StringBuffer)msgFormatter.out();
        strBuff.delete(0, strBuff.length());

        List<String> formattedElems = new ArrayList<String>();
        Map<String, Integer> fmtIndexes = new HashMap<String, Integer>();

        for (int x = 0; x < sortedElements.length; x ++) {
            String currentElem = sortedElements[x];
            if (AUDIT_ELEM_CONTEXT.equals(currentElem)) {
                // If we configured not with context, we pass by this element.
                if (!withContext) {
                    continue;
                }
            }

            // The formatter we will use to format the element.
            AuditElementFormatter usedFmt = null;
            // Get the formatter list by element
            List<AuditElementFormatter> fmts = formattersByElement.get(currentElem);
            if (fmts != null && fmts.size() > 0) {
                Integer index = fmtIndexes.get(currentElem);
                if (index == null) {
                    // The first time, we put 0 as the initial index.
                    index = 0;
                    fmtIndexes.put(currentElem, index);
                }
                else {
                    // If not the first time, we will try to increase the index
                    if (index.intValue() < fmts.size() - 1) {
                        index ++;
                        fmtIndexes.put(currentElem, index);
                    }
                    // if the index reaches the end of the fmts, we leave the index as it is. That mean we use the
                    // lastest formatters for all last elements.
                }

                usedFmt = fmts.get(index);
            }
            // If there is no formatter configured for current element, we use the crude value directly.
            Object crudeValue = getCrudeElementValue(currentElem, severity, category, message, ctx, t);
            formattedElems.add(usedFmt == null ?
                    (crudeValue == null ? "" : crudeValue.toString()) : usedFmt.format(crudeValue));
        }
        // Use the overall formatter format the elements.
        try {
            return msgFormatter.format(finalOverallFormat, formattedElems.toArray(new Object[0])).toString();
        }
        catch (Exception e) {
            Logger logger = LogFactory.getLogger();
            logger.error(
                    "Error occurs while formatting audit message, this may be caused by inconsistent configurations", e);
            throw new IllegalArgumentException("The 'overallFormat' doesn't match the elements that need to be audit." +
            	" Please check the configuration's consistency among 'withContext', 'overallFormat' and formatters.");
        }
    }

    private Object getCrudeElementValue(
            String element, AuditSeverity severity, String category, String message, Context ctx, Throwable t) {
        if (element.equals(AUDIT_ELEM_TIME)) {
            return getTime();
        }
        else if (element.equals(AUDIT_ELEM_HOST)) {
            return getHost();
        }
        else if (element.equals(AUDIT_ELEM_THREAD)) {
            return getThread();
        }
        else if (element.equals(AUDIT_ELEM_CODE)) {
            return getCodeLine();
        }
        else if (element.equals(AUDIT_ELEM_SEVERITY)) {
            return severity;
        }
        else if (element.equals(AUDIT_ELEM_CATEGORY)) {
            return category;
        }
        else if (element.equals(AUDIT_ELEM_MESSAGE)) {
            return message;
        }
        else if (element.equals(AUDIT_ELEM_CONTEXT)) {
            return ctx;
        }
        else {
            return t;
        }
    }

    private boolean checkSeverity(AuditSeverity currentSeverity) {
        if (currentSeverity == AuditSeverity.ALL) {
            return true;
        }
        else if (currentSeverity == AuditSeverity.NONE) {
            return false;
        }
        // Not none, not all, compute if this severity should be logged.
        else {
            return isCurrentSeverityMatchingConfig(currentSeverity, configSeverity, singleLevelMode);
        }
    }

    private static boolean isCurrentSeverityMatchingConfig(
            AuditSeverity currentSeverity, AuditSeverity configSeverity, boolean single) {
        // If single level mode is enabled, we only log the exact severity's event.
        // If single level mode is disabled, we log all severities that lower than configured severity.
        return (single ? configSeverity == currentSeverity : configSeverity.compareTo(currentSeverity) >= 0);
    }

    private boolean checkCategory(String category) {
        return category.matches(categoryFilter);
    }

    private Date getTime() {
        return new Date();
    }

    private String getHost() {
        return hostName;
    }

    private String getThread() {
        return Thread.currentThread().getName();
    }

    private String getCodeLine() {
        Exception ex = new Exception();
        StackTraceElement[] traces = ex.getStackTrace();
        for (int i = 1; i < traces.length; i ++) {
            // Get the first "non-audit" class name as audit event's code line.
            String traceClass = traces[i].getClassName();
            if (!traceClass.equals(getClass().getName()) &&
                !traceClass.equals(AuditService.class.getName())) {
                return traces[i].getClassName() + ":" + traces[i].getLineNumber();
            }
        }
        return "**Unknown calling stack**";
    }

    public void audit(AuditSeverity severity, String category, String message, Context ctx) {
        audit(severity, category, message, ctx, null);
    }

    public void audit(AuditSeverity severity, String category, String message, Context ctx, Throwable t) {
        // Start auditing
        if (checkSeverity(severity) && checkCategory(category)) {
            String auditEvent = compositeMessage(severity, category, message, ctx, t);
            try {
                synchronized (outputMap) {
                    // check if the audit file size has exceeded the limit.
                    checkRollover(auditEvent, null);
                    out.println(auditEvent);
                }
            }
            catch (IOException ioEx) {
                String msg = "Error occurs when rolling the audit file: " + ioEx.getMessage() +
                ". Will print audit event to system out.";
                // Print the audit event to system out.
                System.out.println(msg);
                ioEx.printStackTrace(System.out);
                // Then log an audit error log
                System.out.println(auditEvent);
                System.out.println(
                        compositeMessage(AuditSeverity.AUDITERROR, AuditService.CATEGORY_AUDIT, msg, ctx, ioEx));
            }
        }
    }

    public String getProviderName() {
        return providerName;
    }

    public Status getStatus() {
        // TODO Auto-generated method stub
        return null;
    }

    public Object getStatusProperty(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    public void pause() throws OperationFailedException {
        throw new OperationFailedException("We don't support pause operation on this provider.");
    }

    public void resume() throws OperationFailedException {
        throw new OperationFailedException("We don't support resume operation on this provider.");
    }

    public void shutdown() throws OperationFailedException {
        System.out.println("Shutting down File Audit provider '" + providerName + "' ...");
        synchronized (outputMap) {
            Iterator<PrintWriter> outs = outputMap.values().iterator();
            while (outs.hasNext()) {
                outs.next().close();
            }
        }
        formattersByElement.clear();
        System.out.println("File Audit provider '" + providerName + "' shutdown.");
    }

    public void shutdownForce() throws OperationFailedException {
        shutdown();
    }

    public void start() throws OperationFailedException {
        System.out.println("Starting File Audit provider '" + providerName + "' ...");
        try {
            initialize();
        } catch (Exception e) {
            throw new OperationFailedException("Failed to start File Audit '" + providerName + "'", e);
        }
        System.out.println("File Audit provider '" + providerName + "' started.");
    }
}