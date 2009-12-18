package techDecision.xacmlPdp.ws;
import an.chopsticks.service.Action;
import an.chopsticks.service.AuthorizationFailedException;
import an.chopsticks.service.AuthorizationResult;
import an.chopsticks.service.AuthorizationService;
import an.chopsticks.service.Context;
import an.chopsticks.service.Decision;
import an.chopsticks.service.Resource;
import an.chopsticks.service.Subject;
import techDecision.xacmlPdp.XaclmlAutorise;

public class SimplePdpService implements SimplePdpInterface {
    private XaclmlAutorise xacml = null;
    private AuthorizationService atzSvc = null;
    private Subject[] subjects;
    private Resource[] resources;
    private Action action;
    private Context context;

    public void setXacml(XaclmlAutorise xacml) {
        System.out.println("getSimpleDecision recup référence XacmlAutorise");
        this.xacml = xacml;
        if(xacml==null){
            System.err.println("Erreur getSimpleDecision référence XacmlAutorise vide");
        }
    }

    public String getSimpleDecision(String login, String ressource, String acte){
        String rep = null;
        if(xacml==null){
            System.err.println("Erreur getSimpleDecision initialisation service échouée login="+login+" ressource="+ressource+" acte="+acte);
            return "Erreur init servicepour login="+login+" ressource="+ressource+" acte="+acte;
        }
        atzSvc = xacml.getAthzSvc();
        System.out.println("Calculer décision pour login="+login+" ressource="+ressource+" acte="+acte);
        action = new Action(acte);
        resources = new Resource[1];
        resources[0] = new Resource(ressource);
        subjects = new Subject[1];
        Subject s = new Subject();
        s.setSubjecName(login);
        subjects[0] = s;
        AuthorizationResult[] aresss = null;
        try {
            aresss = atzSvc.authorize(subjects, resources, action, context);
            Decision dec = aresss[0].getDecision();
            if(dec==Decision.Permit) rep = "Permission";
            if(dec==Decision.Deny) rep = "Refus";
            if(dec==Decision.Indeterminate) rep="Indéterminé";
            if(dec==Decision.NotApplicable) rep="Non Applicable";
        } catch (AuthorizationFailedException ex) {
            System.err.println("Erreur sur décision "+ex.toString());
            rep = "Erreur sur calcul décision";
        }
       return rep;
    }
}
    