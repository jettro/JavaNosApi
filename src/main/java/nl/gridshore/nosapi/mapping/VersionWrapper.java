package nl.gridshore.nosapi.mapping;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;

/**
 * @author Jettro Coenradie
 */
public class VersionWrapper {
    @JsonProperty("version")
    private ArrayList<Version> versions = new ArrayList<Version>();

    public ArrayList<Version> getVersions() {
        return versions;
    }

    public void setVersions(ArrayList<Version> versions) {
        this.versions = versions;
    }
}
