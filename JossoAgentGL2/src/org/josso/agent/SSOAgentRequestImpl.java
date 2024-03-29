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
package org.josso.agent;


/**
 * @author <a href="mailto:sgonzalez@josso.org">Sebastian Gonzalez Oyuela</a>
 * @version $Id: SSOAgentRequestImpl.java 543 2008-03-18 21:34:58Z sgonzalez $
 */

public class SSOAgentRequestImpl implements SSOAgentRequest {

    private int _action;
    private String _sessionId;
    private LocalSession _session;
    private String _assertionId;

    public SSOAgentRequestImpl(int action, String sessionId, LocalSession session, String assertionId) {
        _action = action;
        _sessionId = sessionId;
        _session = session;
        _assertionId = assertionId;
    }

    public SSOAgentRequestImpl(int action, String sessionId, LocalSession session) {
        this(action, sessionId, session, null);
    }

    public int getAction() {
        return _action;
    }

    public void setAction(int action) {
        _action = action;
    }

    public String getSessionId() {
        return _sessionId;
    }

    public void setSessionId(String sessionId) {
        _sessionId = sessionId;
    }

    public LocalSession getLocalSession() {
        return _session;
    }

    public void setLocalSession(LocalSession localSession) {
        _session = localSession;
    }

    public String getAssertionId() {
        return _assertionId;
    }

    public void setAssertionId(String assertionId) {
        _assertionId = assertionId;
    }
}
