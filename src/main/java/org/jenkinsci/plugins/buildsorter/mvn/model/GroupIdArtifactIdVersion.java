package org.jenkinsci.plugins.buildsorter.mvn.model;

public class GroupIdArtifactIdVersion {
    private final String groupId;
    private final String artifactId;
    private final String version;

    public GroupIdArtifactIdVersion(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public String groupId() {
        return groupId;
    }

    public String artifactId() {
        return artifactId;
    }

    public String version() {
        return version;
    }
}
