/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techDecision.xacmlPdp.ws;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author spopoff
 */
@WebService(serviceName="SimplePdpWS")
public class SimplePdpServiceEndPoint  extends SpringBeanAutowiringSupport {
    @Autowired
    private SimplePdpInterface xacmlWSpdp;

    @WebMethod
    public String getSimpleDecision(String login, String ressource, String acte) {
        return xacmlWSpdp.getSimpleDecision(login, ressource, acte);
    }
}
