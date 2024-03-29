/*
 * JOSSO: Java Open Single Sign-On
 *
 * Copyright 2004-2009, Atricore, Inc.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *
 */

package org.josso.agent.http;

import org.josso.agent.SSOAgentRequestImpl;
import org.josso.agent.LocalSession;
import org.apache.catalina.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This SSO Agent Request wrapps original servlet request and response objects.
 * <p/>
 * It also provides a placeholder for the JOSSO Security context created by the Servlet SSO Agent during authentication.
 * <p/>
 * Date: Nov 27, 2007
 * Time: 11:53:50 AM
 *
 * @author <a href="mailto:sgonzalez@josso.org">Sebastian Gonzalez Oyuela</a>
 */
public class HttpSSOAgentRequest extends SSOAgentRequestImpl {

    private Context _context;
    private HttpServletRequest request;

    private HttpServletResponse response;

    public HttpSSOAgentRequest(int action, String sessionId, LocalSession session, String assertionId) {
        super(action, sessionId, session, assertionId);
    }

    public HttpSSOAgentRequest(int action, String sessionId, LocalSession session) {
        super(action, sessionId, session);
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
    public void setContext(Context c) {
        _context = c;
    }

    /**
     * The context associated with this request.
     */
    public Context getContext() {
        return _context;
    }

}

                        