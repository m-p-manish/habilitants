/*
Copyright Stéphane Georges Popoff, (Décembre 2009)

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

package techDecision.xacmlPdp.ws;

import org.springframework.remoting.jaxrpc.ServletEndpointSupport;
import os.schema.context._0._2.xacml.tc.names.oasis.RequestDocument;
import os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument;
/**
 * classe qui porte le service PDP Decision pour Spring et répond à getDecision (opération soap)
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class SpringWSxacmlPdp extends ServletEndpointSupport implements PdpServiceSkeletonInterface {

    private PdpServiceSkeletonInterface springWS;

    @Override
    protected void onInit() {
        this.springWS = (PdpServiceSkeletonInterface) getWebApplicationContext().getBean("xacmlWSpdp");
    }
    public ResponseDocument getDecision(RequestDocument request) {
        return springWS.getDecision(request);
    }

}
