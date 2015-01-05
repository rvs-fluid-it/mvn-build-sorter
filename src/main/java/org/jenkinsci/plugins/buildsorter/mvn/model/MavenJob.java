package org.jenkinsci.plugins.buildsorter.mvn.model;

import org.jenkinsci.plugins.buildsorter.model.ChainableJob;

import java.util.List;

public class MavenJob extends ChainableJob<GroupIdArtifactIdVersion> {
    public MavenJob(MavenJobRegistry mavenJobRegistry,
                    GroupIdArtifactIdVersion groupIdArtifactIdVersion,
                    List<GroupIdArtifactIdVersion> downstreamGroupIdArtifactIdVersions) {
        super(mavenJobRegistry, groupIdArtifactIdVersion, downstreamGroupIdArtifactIdVersions);
    }

    public GroupIdArtifactIdVersion groupIdArtifactIdVersion() {
        return id();
    }

}
