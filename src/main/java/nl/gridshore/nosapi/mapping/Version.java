package nl.gridshore.nosapi.mapping;

/**
 * @author Jettro Coenradie
 */
public class Version {
    private String version;
    private String build;

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
