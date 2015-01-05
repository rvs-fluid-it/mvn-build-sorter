package org.jenkinsci.plugins.buildsorter.mvn.model;

import org.jenkinsci.plugins.buildsorter.model.ChainableJob;
import org.jenkinsci.plugins.buildsorter.model.JobRegistry;

import java.util.List;

public class MavenJobRegistry extends JobRegistry<GroupIdArtifactIdVersion> {
    public synchronized void register(GroupIdArtifactIdVersion groupIdArtifactIdVersion,
                                      List<GroupIdArtifactIdVersion> downstreamGroupIdArtifactIdVersions) {
        register(new MavenJob(this, groupIdArtifactIdVersion, downstreamGroupIdArtifactIdVersions));
    }

    @Override
    public Class<? extends ChainableJob<GroupIdArtifactIdVersion>> jobClazz() {
        return MavenJob.class;
    }
}
