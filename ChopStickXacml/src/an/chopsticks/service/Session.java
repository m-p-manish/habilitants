package an.chopsticks.service;

/**
 * The Session interface represents a session object related to the current request.
 */
public interface Session {
    /**
     * Set current logged in subject to current session.
     * @param subj
     */
    public void setSubject(Subject subj);
    /**
     * Retrieve a subject from current session.
     * @return
     */
    public Subject getSubject();
    /**
     * Set an object to current session.
     * @param key
     * @param value
     */
    public void setObject(Object key, Object value);
    /**
     * Retrieve an object from current session by a given key.
     * @param key
     * @return
     */
    public Object getObject(Object key);
    /**
     * Return a session id that can identify this session.
     * @return
     */
    public Object getSessionId();
    /**
     * Determine if this session is valid.
     * @return
     */
    public boolean isValid();
    /**
     * Invalidate this session.
     */
    public void invalidate();
}