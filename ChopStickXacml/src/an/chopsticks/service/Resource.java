package an.chopsticks.service;

public class Resource {
    private String resourceString;

    public Resource(String resourceString) {
        this.resourceString = resourceString;
    }

    public String getResourceString() {
        return resourceString;
    }

    public String getApplication() {
        return null;
    }
}