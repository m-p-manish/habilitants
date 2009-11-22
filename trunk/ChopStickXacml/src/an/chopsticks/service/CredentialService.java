package an.chopsticks.service;

import an.control.OperationFailedException;
import an.control.Status;

public final class CredentialService implements Service {
    
    /**
     * Perform conversion from source credential to a destination credential.
     * @param source The source credential.
     * @param destType The destination credential's type.
     * @return The destination credential object.
     */
    public Credential convert(Credential source, String destType) {
        // TODO
        return null;
    }
    /**
     * Perform conversion from Subject to a destination credential.
     * @param subject The authenticated subject.
     * @param destType The destination credential's type.
     * @return The destination credential object.
     */
    public Credential convert(Subject subject, String destType) {
        // TODO
        return null;
    }

    public String getServiceName() {
        // TODO Auto-generated method stub
        return null;
    }

    public Status getStatus() {
        // TODO Auto-generated method stub
        return null;
    }

    public Object getStatusProperty(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    public void pause() throws OperationFailedException {
        // TODO Auto-generated method stub

    }

    public void resume() throws OperationFailedException {
        // TODO Auto-generated method stub

    }

    public void shutdown() throws OperationFailedException {
        // TODO Auto-generated method stub

    }

    public void shutdownForce() throws OperationFailedException {
        // TODO Auto-generated method stub

    }

    public void start() throws OperationFailedException {
        // TODO Auto-generated method stub

    }
}