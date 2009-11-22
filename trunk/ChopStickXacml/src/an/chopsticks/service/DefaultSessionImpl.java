package an.chopsticks.service;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

//import sun.misc.BASE64Encoder;
import an.util.Base64;

public class DefaultSessionImpl implements Session, Serializable {
    private static final long serialVersionUID = 3195002105672947325L;
    private static Random random = new Random(System.currentTimeMillis());
    private static Base64 encoder = new Base64();

    private Subject subj;
    private final Object id;
    private Map<Object, Object> attributes = new Hashtable<Object, Object>();

    private long creationTime;
    private long accessTime;

    private long timeout;
    private boolean fromAccess = true;
    private boolean isValid = true;

    /*package*/ DefaultSessionImpl(Object clientSession, long timeout, boolean fromAccess) {
        this.id = clientSession;
        this.timeout = timeout;
        this.fromAccess = fromAccess;
        this.creationTime = this.accessTime = System.currentTimeMillis();
    }

    /*package*/ DefaultSessionImpl(long timeout, boolean fromAccess) {
        this.timeout = timeout;
        this.fromAccess = fromAccess;
        byte[] strId = new byte[20];
        random.nextBytes(strId);
        id = encoder.encode(strId).substring(0, 20);
        this.creationTime = this.accessTime = System.currentTimeMillis();
    }

    public Object getObject(Object key) {
        touch();
        return attributes.get(key);
    }

    public Subject getSubject() {
        touch();
        return subj;
    }

    public void setObject(Object key, Object value) {
        attributes.put(key, value);
        touch();
    }

    public void setSubject(Subject subj) {
        this.subj = subj;
        touch();
    }

    public int hashCode() {
        return id.hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o != null && o.getClass() == this.getClass()) {
            DefaultSessionImpl other = (DefaultSessionImpl)o;
            if (id.equals(other.id)) {
                return true;
            }
        }
        return false;
    }

    public void touch() {
        this.accessTime = System.currentTimeMillis();
    }

    public Object getSessionId() {
        return id;
    }

    public void invalidate() {
        subj = null;
        attributes.clear();
        isValid = false;
    }

    public boolean isValid() {
        return isValid && (System.currentTimeMillis() - (fromAccess ? accessTime : creationTime) < timeout);
    }
}