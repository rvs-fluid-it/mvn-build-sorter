package org.jenkinsci.plugins.buildsorter.model;

import java.util.List;

public class NamedJob extends ChainableJob<String> {
    public NamedJob(NamedJobRegistry jobRegistry, String jobName, List<String> downstreamJobNames) {
        super(jobRegistry, jobName, downstreamJobNames);
    }

    public String name() {
        return id();
    }
}
