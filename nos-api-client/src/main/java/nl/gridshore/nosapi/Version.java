package nl.gridshore.nosapi;

/**
 * <p>Has information about the version of the api that is being used</p>
 *
 * @author Jettro Coenradie
 */
public class Version {
    private String version;
    private String build;

    public Version(String build, String version) {
        this.build = build;
        this.version = version;
    }

    public String getBuild() {
        return build;
    }

    public String getVersion() {
        return version;
    }
}
