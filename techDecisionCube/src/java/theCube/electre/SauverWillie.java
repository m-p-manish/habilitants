/*
Copyright Stéphane Georges Popoff, (juin 2009)

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
package theCube.electre;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import theCube.entities.Compte;
import theCube.entities.CpteAttrs;
import theCube.roleMining.TalendConnection;

/**
 * Classe qui ne sert que de test pour faire une persistence
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class SauverWillie {
    @Resource
    private UserTransaction utx ;
    @PersistenceUnit(unitName = "techDecisionCubePU")
    private EntityManagerFactory emf;

    public void ajouteAttrCpte(String sAttr, String sVal, int id) throws Exception {
        EntityManager emm = getEntityManager();
        Compte c = null;
        CpteAttrs ca = null;
        try{
            c = emm.getReference(Compte.class, id);
        }catch(Exception err){
            System.err.println(" 100 " + err.toString());
            throw err;
        }
        if(c==null) throw new Exception("Pas de compte id="+id);
        CpteAttrs obj = new CpteAttrs(TalendConnection.Aleat());
        obj.setFkcpte(c);
        obj.setAttr(sAttr);
        obj.setVal(sVal);
        try {
            utx.begin();
        } catch (Exception ex) {
            System.err.println(" 11 " + ex.toString());
            throw ex;
        }
        try{
           //emm.getTransaction();
           System.out.println("Persistence new ca="+obj.getPkattrCpte());
           emm.persist(obj);
        }catch(Exception err){
           System.err.println(" 13 " + err.toString());
            throw err;
        }
        emm.flush();
        try {
            utx.commit();
        } catch (SecurityException ex) {
            System.err.println(" 17 " + ex.toString());
            throw ex;
        } catch (IllegalStateException ex) {
            System.err.println(" 18 " + ex.toString());
            throw ex;
        } catch (Exception ex) {
            System.err.println(" 19 " + ex.toString());
            throw ex;
        }
        emm.close();
    }
    public void setClasse(String classement, int cpId) throws Exception{
        //utx = InitialContext().lookup("UserTransaction"); ;
        EntityManager emm = getEntityManager();
        Compte c = null;
        CpteAttrs ca = null;
        try{
            c = emm.getReference(Compte.class, cpId);
        }catch(Exception err){
            System.err.println(" 100 " + err.toString());
            throw err;
        }
        if(c.getCpteAttrsCollection()==null){
            System.out.println("Collection attributs vide bizarre ?");
            //cpteAttrsCollection = new LinkedList<CpteAttrs>();
            ca = new CpteAttrs(TalendConnection.Aleat());
            ca.setAttr("CLASST");
            ca.setVal(classement);
            ca.setFkcpte(c);
            //cpteAttrsCollection.add(oaa);
            try {
                utx.begin();
            } catch (Exception ex) {
                System.err.println(" 11 " + ex.toString());
                throw ex;
            }
            try{
               emm.getTransaction();
               System.out.println("Persistence new ca="+ca.getPkattrCpte());
               emm.persist(ca);
           }catch(Exception err){
               System.err.println(" 13 " + err.toString());
                throw err;
           }
            try {
                utx.commit();
            } catch (SecurityException ex) {
                System.err.println(" 17 " + ex.toString());
                throw ex;
            } catch (IllegalStateException ex) {
                System.err.println(" 18 " + ex.toString());
                throw ex;
            } catch (Exception ex) {
                System.err.println(" 19 " + ex.toString());
                throw ex;
            }
        }else{
            for(CpteAttrs caa: c.getCpteAttrsCollection()){
                System.out.println("attribut compte attr="+caa.getAttr()+" val="+caa.getVal());
                if(caa.getAttr().equals("CLASST")){
                    System.out.println("Ecrase dernier classement");
                    caa.setVal(classement);
                    try {
                        utx.begin();
                    } catch (Exception ex) {
                        System.err.println(" 121 " + ex.toString());
                        throw ex;
                    }
                   try{
                       //emm.getTransaction();
                       System.out.println("Persistence modif ca="+ca.getPkattrCpte());
                       c = emm.merge(c);
                   }catch(Exception err){
                       System.err.println(" 131 " + err.toString());
                       throw err;
                   }
                    try {
                        utx.commit();
                    } catch (SecurityException ex) {
                        System.err.println(" 171 " + ex.toString());
                        throw ex;
                    } catch (IllegalStateException ex) {
                        System.err.println(" 181 " + ex.toString());
                        throw ex;
                    } catch (Exception ex) {
                        System.err.println(" 191 " + ex.toString());
                        throw ex;
                    }
                    return;
                }
            }
            System.out.println("Ajoute classement");
            ca = new CpteAttrs(TalendConnection.Aleat());
            ca.setAttr("CLASST");
            ca.setVal(classement);
            ca.setFkcpte(c);
            c.getCpteAttrsCollection().add(ca);
            if(utx==null){
                System.err.println("mais utx est vide ***************");
            }
            try {
                utx.begin();
            } catch (Exception ex) {
                System.err.println(" 122 " + ex.toString());
                throw ex;
            }
            if(utx==null){
                System.err.println("incroyable utx est vide et je peux commence une transaction ***************");
            }
            try{
               //emm.getTransaction();
            }catch(Exception err){
               System.err.println(" 132a " + err.toString());
                throw err;
            }
            try{
               System.out.println("Persistence add ca="+ca.getPkattrCpte()+" cp="+c.getPkcpte());
               //emm.persist(ca);
               c = emm.merge(c);
               emm.persist(ca);
               c = emm.merge(c);
            }catch(Exception err){
               System.err.println(" 132b " + err.toString());
                throw err;
            }
            try{
                emm.flush();
            }catch(Exception err){
                System.err.println(" 999 " + err.toString());
            }
            System.out.println("Status transac avant commit="+utx.getStatus());
            try {
                utx.commit();
            } catch (SecurityException ex) {
                System.err.println(" 172 " + ex.toString());
                return;
            } catch (IllegalStateException ex) {
                System.err.println(" 182 " + ex.toString());
                throw ex;
            } catch (Exception ex) {
                System.err.println(" 192 " + ex.toString());
                throw ex;
            }
            System.out.println("Status transac apres commit="+utx.getStatus());
        }
        emm.close();
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
