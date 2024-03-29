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

package org.josso.gl2.agent;


import org.apache.catalina.Context;
import org.josso.agent.LocalSession;
import org.josso.agent.SSOAgentRequestImpl;
import org.josso.agent.http.HttpSSOAgentRequest;

/**
 * @author <a href="mailto:sgonzalez@josso.org">Sebastian Gonzalez Oyuela</a>
 * @version $Id: CatalinaSSOAgentRequest.java 974 2009-01-14 00:39:45Z sgonzalez $
 */

public class CatalinaSSOAgentRequest extends HttpSSOAgentRequest {

    private Context _context;

    public CatalinaSSOAgentRequest(int action, String sessionId, LocalSession session, String assertionId) {
        super(action, sessionId, session, assertionId);
    }

    public CatalinaSSOAgentRequest(int action, String sessionId, LocalSession session) {
        this(action, sessionId, session, null);
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
