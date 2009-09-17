/*
Copyright Stéphane Georges Popoff, (mai - juillet 2009)

spopoff@rocketmail.com

Ce logiciel est un programme informatique servant à gérer des habilitations.

Ce logiciel est régi par la licence [CeCILL|CeCILL-B|CeCILL-C] soumise au droit français et
respectant les principes de diffusion des logiciels libres. Vous pouvez
utiliser, modifier et/ou redistribuer ce programme sous les conditions
de la licence [CeCILL|CeCILL-B|CeCILL-C] telle que diffusée par le CEA, le CNRS et l'INRIA
sur le site "http://www.cecill.info".

En contrepartie de l'accessibilité au code source et des droits de copie,
de modification et de redistribution accordés par cette licence, il n'est
offert aux utilisateurs qu'une garantie limitée.  Pour les mêmes raisons,
seule une responsabilité restreinte pèse sur l'auteur du programme,  le
titulaire des droits patrimoniaux et les concédants successifs.

A cet égard  l'attention de l'utilisateur est attirée sur les risques
associés au chargement,  à l'utilisation,  à la modification et/ou au
développement et à la reproduction du logiciel par l'utilisateur étant
donné sa spécificité de logiciel libre, qui peut le rendre complexe à
manipuler et qui le réserve donc à des développeurs et des professionnels
avertis possédant  des  connaissances  informatiques approfondies.  Les
utilisateurs sont donc invités à charger  et  tester  l'adéquation  du
logiciel à leurs besoins dans des conditions permettant d'assurer la
sécurité de leurs systèmes et ou de leurs données et, plus généralement,
à l'utiliser et l'exploiter dans les mêmes conditions de sécurité.

Le fait que vous puissiez accéder à cet en-tête signifie que vous avez
pris connaissance de la licence [CeCILL|CeCILL-B|CeCILL-C], et que vous en avez accepté les
termes.
 */
package theCube.dao;

import java.io.FileInputStream;
import javax.naming.NamingException;
import techDecision.entites.Identite;
import techDecision.entites.IdntAttrs;
import techDecision.entites.IdntCpte;
import techDecision.entites.ObjsCpte;
import java.util.List;
import java.util.Properties;
import techDecision.dao.identite.IIdentiteDaoLocal;
import techDecision.dao.idntattr.IIdntattrDaoLocal;
import techDecision.dao.idntcpte.IIdntCpteDaoLocal;
import techDecision.dao.cpteattr.ICpteAttrsDaoLocal;
import techDecision.dao.objscpte.IObjsCpteDaoLocal;
import techDecision.dao.objsattr.IObjsAttrsDaoLocal;
import techDecision.dao.cptehblt.ICpteHbltDaoLocal;
import javax.naming.InitialContext;
import javax.ejb.EJB;
import javax.faces.FacesException;
import java.io.InputStream;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import techDecision.entites.CpteAttrs;
import techDecision.entites.CpteHblt;
import techDecision.entites.ObjsAttrs;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.ejb.EJBException;
import techDecision.dao.exceptions.TechDecisionErreurs;
import theCube.roleMining.TalendConnection;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;

/**
 * Classe qui prend les données des ejb et construit la base temporaire H2
 * @author spopoff@rocketmail.com
 * @version 0.5
 */
//@TransactionAttribute(TransactionAttributeType.NEVER)
public class DataLoaderH2 {
    @EJB(name="ejbRefIdnt",beanInterface=techDecision.dao.identite.IIdentiteDaoLocal.class)
    IIdentiteDaoLocal daoIdnt;
    @EJB(name="ejbRefIdntAttr",beanInterface=techDecision.dao.idntattr.IIdntattrDaoLocal.class)
    IIdntattrDaoLocal daoIdntAttr;
    @EJB(name="ejbRefIdntCpte",beanInterface=techDecision.dao.idntcpte.IIdntCpteDaoLocal.class)
    IIdntCpteDaoLocal daoIdntCpte;
    @EJB(name="ejbRefCpteAttrs",beanInterface=techDecision.dao.cpteattr.ICpteAttrsDaoLocal.class)
    ICpteAttrsDaoLocal daoCpteAttrs;
    @EJB(name="ejbRefObjsCpte",beanInterface=techDecision.dao.objscpte.IObjsCpteDaoLocal.class)
    IObjsCpteDaoLocal daoObjsCpte;
    @EJB(name="ejbRefObjsAttrs",beanInterface=techDecision.dao.objsattr.IObjsAttrsDaoLocal.class)
    IObjsAttrsDaoLocal daoObjsAttrs;
    @EJB(name="ejbRefCpteHblt",beanInterface=techDecision.dao.cptehblt.ICpteHbltDaoLocal.class)
    ICpteHbltDaoLocal daoCpteHblt;

    Properties applicationProps = null;
    private String selectIdnt = "SELECT * FROM IDENTITE WHERE USERNAME LIKE 'CIF00000%' LIMIT 15;";
    private String req = "";
    @PersistenceContext(unitName="techDecisionCubePU")
    private EntityManager em;
    @Resource
    UserTransaction utx;
    private HashMap dptIdntCpte = null;
    private List<Dpt> dpt = null;

    public DataLoaderH2(){
        Properties defaultProps = null;
        try {
            // create and load default properties
            defaultProps = new Properties();
            InputStream in = getClass().getResourceAsStream("dataBase.properties");
            defaultProps.load(in);
            in.close();
        }catch(Exception err){
            System.err.println("Erreur chargement dataBase.properties "+err.toString());
        }
        //y a quelquechose ?
        System.out.println("Contenu des propriétés de dataBase.properties !");
        try{
            defaultProps.list(System.out);
        }catch(Exception err){
            System.err.println("Rien dans defaultProps "+err.toString());
        }
        // create application properties with default
        applicationProps = new Properties(defaultProps);

        // now load properties from last invocation
        try{
            FileInputStream inn = new FileInputStream(defaultProps.getProperty("externeProps"));
            applicationProps.load(inn);
            inn.close();
        }catch(Exception err){
            System.err.println("trouve pas le fichier de propriétés pour DB3, utilise défaut "+err.toString());
        }
        System.out.println("Contenu des propriétés après chargement externe !");
        try{
            applicationProps.list(System.out);
        }catch(Exception err){
            System.err.println("Rien dans defaultProps "+err.toString());
        }
        try{
            InitialContext ic = new InitialContext();
            //tient j'utilise la convention J2EE et pas celle de Sun ?
            daoIdnt = (IIdentiteDaoLocal) ic.lookup("java:comp/env/ejbRefIdnt");
            daoIdnt.init();
            daoIdntAttr = (IIdntattrDaoLocal) ic.lookup("java:comp/env/ejbRefIdntAttr");
            daoIdntAttr.init();
            daoIdntCpte = (IIdntCpteDaoLocal) ic.lookup("java:comp/env/ejbRefIdntCpte");
            daoIdntCpte.init();
            daoCpteAttrs = (ICpteAttrsDaoLocal) ic.lookup("java:comp/env/ejbRefCpteAttrs");
            daoCpteAttrs.init();
            daoObjsCpte = (IObjsCpteDaoLocal) ic.lookup("java:comp/env/ejbRefObjsCpte");
            daoObjsCpte.init();
            daoObjsAttrs = (IObjsAttrsDaoLocal) ic.lookup("java:comp/env/ejbRefObjsAttrs");
            daoObjsAttrs.init();
            daoCpteHblt = (ICpteHbltDaoLocal) ic.lookup("java:comp/env/ejbRefCpteHblt");
            daoCpteHblt.init();
        } catch (NamingException ex) {
            System.err.println("Erreur sur référence EJB dans DalaLoaderH2 "+ex.toString());
            return;
        }catch(EJBException ex){
            System.err.println("Erreur DataLoaderH2 ejbException "+ex.toString());
            return;

        } catch (Exception ex) {
            System.err.println("Erreur générale init DalaLoaderH2 "+ex.toString());
            return;
        }
        try{
            dptIdntCpte = new HashMap<Integer, AggregateDptIdntCpte>();
        }catch(Exception err){
            System.err.println("Erreur sur aggrégateur "+err.toString());
        }
        dpt = new LinkedList<Dpt>();
        //newDatabase();
    }
    public void newDBbyIdentite(String idntWhere){
        List<Identite> idntL = null;
        String dept = "";
        //filtre les identités
        if(idntWhere==null){
            System.err.println("Erreur newDBbyIdentite filtre est vide!");
            return;
        }
        if(daoIdnt==null){
            System.err.println("Erreur newDBbyIdentite ref ejb Identite est vide!");
            return;
        }
        try{
            idntL = daoIdnt.findIdentiteWhere(idntWhere);
        }catch(TechDecisionErreurs ex){
            System.err.println(ex.toString());
            return;

        }catch(Exception err){
            System.err.println(err.toString());
            return;
        }
        AggregateDptIdntCpte objD = null;
        //pour toutes les identités
        for(Identite idnt: idntL){
            //ajoute une identité dans H2
            execSet(idnt.sqlInsert("SPOPOFF."));
            //met à jour la collection des comptes
            idnt.setIdntCpteCollection(daoIdntCpte.relatedCpte(idnt.getPkidnt()));
            //fabrique ou trouve l'aggrégateur departement + Identité + Compte
            dept = idnt.getDepartement();
            //une nouvelle identité de plus
            objD = new AggregateDptIdntCpte(dept, idnt.getPkidnt());
            objD.setNbIdnt(1);
            objD.setFnct(idnt.getFonction());
            objD.setUser(idnt.getUsername());
            try{
                if(dpt.size()==0){
                    dpt.add(new Dpt(dept));
                }else{
                    Boolean found = false;
                    for(Dpt dd : dpt){
                        if(dd.getDpt().equals(dept)){
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        dpt.add(new Dpt(dept));
                    }
                }
            }catch(Exception err){

            }
            Boolean foundCpte =  false;
            //pour tous les comptes
            for(IdntCpte idcp : idnt.getIdntCpteCollection()){
                AggregateDptIdntCpte objDd = new AggregateDptIdntCpte(dept, idnt.getPkidnt(), idcp.getFkcpte().getPkcpte());
                //ajoute à l'aggrégateur
                objDd.setNbCpte(1);
                objDd.setUser(idnt.getUsername());
                if(!foundCpte){
                    objDd.setNbIdnt(1);
                    objDd.setFnct(idnt.getFonction());
                    foundCpte = true;
                }
                //System.out.println("Info ajoute compte="+idcp.getFkcpte().getPkcpte());
                dptIdntCpte.put(idcp.getFkcpte().getPkcpte(), objDd);
                //ajoute le compte
                execSet(idcp.getFkcpte().sqlInsert("SPOPOFF."));
                //ajoute le lien du compte
                execSet(idcp.sqlInsert("SPOPOFF."));
                //met à jour la collection des attribut du compte
                idcp.getFkcpte().setCpteAttrsCollection(daoCpteAttrs.relatedAttrs(idcp.getFkcpte().getPkcpte()));
                //pour tous les attributs de compte
                for(CpteAttrs cpat : idcp.getFkcpte().getCpteAttrsCollection()){
                    //insert l'attribut
                    execSet(cpat.sqlInsert("SPOPOFF."));
                }
                //pour tous les habilitants liés au compte
                for(CpteHblt cphb : daoCpteHblt.relatedHblt(idcp.getFkcpte().getPkcpte())){
                    //ajoute l'habilitant
                    execSet(cphb.getFkhblt().sqlInsert("SPOPOFF."));
                    //ajoute le lien au compte
                    execSet(cphb.sqlInsert("SPOPOFF."));
                    //attention on ne poursuit pas le lien habilitant -> objet de sécurité !
                }
                //pour tous les objets de sécurité des comptes
                for(ObjsCpte obcp : daoObjsCpte.relatedObjs(idcp.getFkcpte().getPkcpte())){
                    //insert l'objet de securité
                    execSet(obcp.getFkobjs().sqlInsert("SPOPOFF."));
                    //insert le lien objs - cpte
                    execSet(obcp.sqlInsert("SPOPOFF."));
                    //pour tous les attributs de l'objet de sécurité
                    for(ObjsAttrs obat : daoObjsAttrs.relatedAttrs(obcp.getFkobjs().getPkobjs())){
                        //insert l'attribut
                        execSet(obat.sqlInsert("SPOPOFF."));
                    }
                }
            }
            if(!foundCpte){
                dptIdntCpte.put(idnt.getPkidnt(), objD);
            }
            //met à jour la collection des attributs d'identité
            idnt.setIdntAttrsCollection(daoIdntAttr.relatedAttrs(idnt.getPkidnt()));
            for(IdntAttrs idat : idnt.getIdntAttrsCollection()){
                //insert tous les attributs d'une identité
                execSet(idat.sqlInsert("SPOPOFF."));
            }
        }
        //parcours le hash ajoute les compteurs
        Set<Map.Entry<String, AggregateDptIdntCpte>> set = dptIdntCpte.entrySet();
        for(Map.Entry<String, AggregateDptIdntCpte> me : set){
            //insert les résultats
            execSet(me.getValue().sqlInsert("SPOPOFF."));
        }
        for(Dpt d : dpt){
            execSet(d.sqlInsert("SPOPOFF."));
        }
        System.out.println("******* Fin Importe les données dans base DB3");
    }
    public String getSelectIdnt() {
        return selectIdnt;
    }

    public void setSelectIdnt(String selectIdnt) {
        this.selectIdnt = selectIdnt;
        System.out.println("******* Démarre création DB3 sur filtre:"+selectIdnt);
        try {
            newDBbyIdentite(this.selectIdnt);
        } catch (Exception ex) {
            System.err.println("Erreur dans création DB3 "+ex.toString());
            throw new FacesException(ex);
        }
        System.out.println("******* Terminé Base DB3 restreinte sur filtre:"+selectIdnt);
    }
    public String gotoIdentite(){
        return "identite_list";
    }
    /**
     * Execute une commande, pas un select
     * @param set
     */
    private void execSet(String set){
        Query q = null;
        try {
            utx.begin();
            em.joinTransaction();
            q = em.createNativeQuery(set);
        } catch (Exception ex) {
            System.err.println(ex.toString());
            return;
        }
        try{
            q.executeUpdate();
        }catch(Exception err){
            System.err.println(err.toString());
        }
        try{
            utx.commit();
        }catch(Exception err){
            System.err.println(err.toString());
        }
    }
    public class Dpt{
        private String dpt;

        public String getDpt() {
            return dpt;
        }
        public Dpt(String dpt){
            this.dpt = dpt;
        }
        public String sqlInsert(String schemaa){
            return "INSERT INTO "+schemaa+"DPT (DEPARTEMENT) VALUES ('"+dpt+"')";
        }
    }
    public class AggregateDptIdntCpte{
        private Integer key;
        private Integer fkidnt;
        private Integer fkcpte;
        private int nbIdnt;
        private int nbCpte;
        private String dpt;
        private String user;
        private String fnct;

        public AggregateDptIdntCpte(String dpt, Integer fkidnt){
            this.dpt = dpt;
            key = TalendConnection.Aleat();
            this.fkidnt = fkidnt;
            fkcpte = null;
        }

        public AggregateDptIdntCpte(String dpt, Integer fkidnt, Integer fkcpte){
            this.dpt = dpt;
            key = TalendConnection.Aleat();
            this.fkidnt = fkidnt;
            this.fkcpte = fkcpte;
        }

        public Integer getFkidnt() {
            return fkidnt;
        }

        public void setFkidnt(Integer fkidnt) {
            this.fkidnt = fkidnt;
        }
        public String getDpt() {
            return dpt;
        }

        public void setDpt(String dpt) {
            this.dpt = dpt;
        }

        public int getNbCpte() {
            return nbCpte;
        }

        public void setNbCpte(int nbCpte) {
            this.nbCpte += nbCpte;
        }

        public int getNbIdnt() {
            return nbIdnt;
        }

        public void setNbIdnt(int nbIdnt) {
            this.nbIdnt = nbIdnt;
        }

        public void setFnct(String fnct) {
            this.fnct = fnct;
        }

        public void setUser(String user) {
            this.user = user;
        }
        public String sqlInsert(String schemaa){
            String ret = "";
            if(fkcpte==null){
                ret =  "INSERT INTO "+schemaa+"DPT_IDNT_CPTE (DEPARTEMENT, NB_IDNT, NB_CPTE, PKDPT_IDNT_CPTE, FKIDNT, USERNAME, FONCTION) VALUES ('"+dpt+"',"+nbIdnt+","+nbCpte+","+key+","+fkidnt+",'"+user+"','"+fnct+"')";
            }else if(user==null && fnct==null){
                ret =  "INSERT INTO "+schemaa+"DPT_IDNT_CPTE (DEPARTEMENT, NB_IDNT, NB_CPTE, PKDPT_IDNT_CPTE, FKIDNT, FKCPTE) VALUES ('"+dpt+"',"+nbIdnt+","+nbCpte+","+key+","+fkidnt+","+fkcpte+")";
            }else{
                ret =  "INSERT INTO "+schemaa+"DPT_IDNT_CPTE (DEPARTEMENT, NB_IDNT, NB_CPTE, PKDPT_IDNT_CPTE, FKIDNT, FKCPTE, USERNAME, FONCTION) VALUES ('"+dpt+"',"+nbIdnt+","+nbCpte+","+key+","+fkidnt+","+fkcpte+",'"+user+"','"+fnct+"')";
            }
            //System.out.println("Info sqlInsert="+ret);
            return ret;
        }

    }

}
