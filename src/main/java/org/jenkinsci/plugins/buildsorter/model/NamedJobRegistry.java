package org.jenkinsci.plugins.buildsorter.model;

import java.util.List;

public class NamedJobRegistry extends JobRegistry<String> {
    public synchronized void register(String jobName, List<String> downstreamJobNames) {
        register(new NamedJob(this, jobName, downstreamJobNames));
    }

    @Override
    public Class<? extends ChainableJob<String>> jobClazz() {
        return NamedJob.class;
    }
}
