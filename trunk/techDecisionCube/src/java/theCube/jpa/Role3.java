/*
Copyright Stéphane Georges Popoff, (mai 2009)

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
package theCube.jpa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.transaction.UserTransaction;
import theCube.entities.Identite;
import java.util.ArrayList;
import java.util.Collection;
import theCube.entities.Role;
import theCube.entities.Roleslink;
import java.util.List;
import java.util.Vector;
import javax.persistence.PersistenceContext;
import theCube.entities.Habilitant;
import theCube.entities.Members;
import theCube.entities.Permissions;
import theCube.newRoleMiner.RoleBuilder;
import theCube.newRoleMiner.RoleBuilder.Hblt;
import theCube.newRoleMiner.RoleBuilder.Idnt;
import theCube.newRoleMiner.RoleBuilder.Rol;
import theCube.newRoleMiner.RoleBuilder.RolLink;
import theCube.roleMining.TalendConnection;
/**
 * Classe JPA qui traite la table Role de H2
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class Role3 {
    @Resource
    private UserTransaction utx = null;
    @PersistenceContext(unitName="techDecisionCubePU")
    private EntityManager em;

    public void sauverRole(Role r){
        Integer pk = r.getPkrole();
        try{

           utx.begin();
           em.joinTransaction();
           em.persist(r);
           em.flush();
           utx.commit();
        }catch(Exception err){
            System.err.println("Erreur Role3.sauverRole pk="+pk+" "+err.toString());
        }
    }
    public void modifierRole(Role r){
        Integer pk = r.getPkrole();
        try{
           utx.begin();
           em.joinTransaction();
           em.merge(r);
           em.flush();
           utx.commit();
        }catch(Exception err){
            System.err.println("Erreur Role3.modifierRole pk="+pk+" "+err.toString());
        }
    }
    public void sauverPerm(Permissions p){
        Integer pk = p.getPkpermissions();
        try{
           utx.begin();
           em.joinTransaction();
           em.persist(p);
           em.flush();
           utx.commit();
        }catch(Exception err){
            System.err.println("Erreur Role3.sauverPerm pk="+pk+" "+err.toString());
        }
    }
    public void sauverMemb(Members m){
        Integer pk = m.getPkmember();
        try{
           utx.begin();
           em.joinTransaction();
           em.persist(m);
           em.flush();
           utx.commit();
        }catch(Exception err){
            System.err.println("Erreur Role3.sauverMemb pk="+pk+" "+err.toString());
        }
    }
    public void sauverRolL(Roleslink rl){
        Integer pk = rl.getPkrolelink();
        try{
           utx.begin();
           em.joinTransaction();
           em.persist(rl);
           em.flush();
           utx.commit();
        }catch(Exception err){
            System.err.println("Erreur Role3.sauverRolL pk="+pk+ " "+err.toString());
        }
    }
    public List<Role> findRoleEntities() {
        return findRoleEntities(true, -1, -1);
    }

    public List<Role> findRoleEntities(int maxResults, int firstResult) {
        return findRoleEntities(false, maxResults, firstResult);
    }

    private List<Role> findRoleEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from Role as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public Role findRole(Integer id) {
        try {
            return em.find(Role.class, id);
        } finally {
        }
    }

    public int getRoleCount() {
        if(em==null) return 0;
        Query q = null;
        int ret = 0;
        Long ll = null;
        try {
            q = em.createNativeQuery("SELECT COUNT(*) FROM SPOPOFF.ROLE");
            ll =(Long) ((Vector) q.getSingleResult()).firstElement();
            ret = ll.intValue();
        }catch(Exception err){
            System.err.println("Erreur dans getIdentiteCount "+err.toString());
        } finally {
            return ret;
        }
    }
    /**
     * Enregistre dans un fichier les rôles
     * @param sFile
     * @param bAppend
     * @throws java.io.IOException
     */
    public void cloze(String sFile, Boolean bAppend) throws IOException{
        String str="**** Roles ****\n";
        Writer output = null;
        try{
           for(Role r: findRoleEntities()){
                str+=r.toString()+" ";
           }
           System.out.println("Serialization terminée");
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        try {
            output = new BufferedWriter(new FileWriter(sFile, bAppend));
              //FileWriter always assumes default encoding is OK!
            output.write(str);
        } finally {
            output.close();
        }
    }
    /**
     * persiste toutes les rôles créés dans le roleBuilder
     * @param objRB
     */
    public void persisteNewRole(RoleBuilder objRB){
        int pkR = 0;
        int pkP = 0;
        int pkM = 0;
        try{
            for(Rol r : objRB.getRoles()){
                //cHblt = null;
                //cIdnt = null;
                pkR = r.getId();
                Role ro = new Role(pkR);
                ro.setActive((short)1);
                ro.setName(r.getDescription());
                ro.setType((short)1);
                try{
                    System.out.println("Info Role3.persisteNewRole sauverRole pk="+pkR);
                    sauverRole(ro);
                }catch(Exception err){
                    System.err.println("Erreur Role3.persisteNewRole sauverRole pk="+pkR+" "+err.toString());
                    return;
                }
                //System.out.println("Info Role3.persisteNewRole sauverRole role="+pkR+" position="+r.getPosition());
                for(Hblt h : r.getListH()){
                    pkP = TalendConnection.Aleat();
                    Permissions p = new Permissions(pkP);
                    p.setActive((short)1);
                    p.setFkhabilitant(h.getPkHblt());
                    p.setFkrole(em.getReference(Role.class, pkR));
                    try{
                        sauverPerm(p);
                    }catch(Exception err){
                        System.err.println("Erreur Role3.persisteNewRole sauverPerm pk="+pkP+" "+err.toString());
                        return;
                    }
                    //System.out.println("   Info Role3.persisteNewRole sauverPerm perm="+pkP);
                }
                for(Idnt i : r.getListI()){
                    pkM = TalendConnection.Aleat();
                    Members m = new Members(pkM);
                    m.setFkidentity(i.getPkIdnt());
                    m.setFkrole(em.getReference(Role.class, pkR));
                    try{
                        sauverMemb(m);
                    }catch(Exception err){
                        System.err.println("Erreur Role3.persisteNewRole sauverMemb pk="+pkM+" "+err.toString());
                        return;
                    }
                    //System.out.println("   Info Role3.persisteNewRole sauverMemb memb="+pkM);
                }
            }
        }catch(Exception err){
            System.err.println("Erreur Role3.persisteNewRole "+err.toString());
        }
    }
    public void persisteNewRoleHiera(RoleBuilder objRB){
       int pkR = 0;
       try{
            for(RolLink rl : objRB.getRolLink()){
               pkR = TalendConnection.Aleat();
               Roleslink rk = new Roleslink(pkR);
               rk.setActive((short)1);
               try{
                    //rk.setFkchildrole(em.getReference(Role.class, rl.getEnfant()));
                   System.out.println("Info Role3.persisteNewRoleHiera getEnfant="+rl.getEnfant());
                    rk.setFkchildrole(em.find(Role.class, rl.getEnfant()));
               }catch(Exception err){
                   System.err.println("Erreur Role3.persisteNewRoleHiera getEnfant="+rl.getEnfant()+" "+err.toString());
                   break;
               }
               try{
                   //rk.setFkparentrole(em.getReference(Role.class, rl.getParent()));
                   System.out.println("Info Role3.persisteNewRoleHiera getParent="+rl.getParent());
                   rk.setFkparentrole(em.find(Role.class, rl.getParent()));
               }catch(Exception err){
                   System.err.println("Erreur Role3.persisteNewRoleHiera getParent="+rl.getParent()+" "+err.toString());
                   break;
               }
               try{
                   sauverRolL(rk);
               }catch(Exception err){
                   System.err.println("Erreur Role3.persisteNewRoleHiera sauverRolL "+err.toString());
                   return;
               }
               Role r = em.getReference(Role.class, rl.getParent());
               r.setHavechild((short)1);
               try{
                   modifierRole(r);
               }catch(Exception err){
                   System.err.println("Erreur Role3.persisteNewRoleHiera modifierRole "+err.toString());
                   return;
               }
           }
        }catch(Exception err){
            System.err.println("Erreur Role3.persisteNewRoleHiera "+err.toString());
        }
    }
}
