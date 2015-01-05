package org.jenkinsci.plugins.buildsorter.model;

import java.util.HashMap;
import java.util.Map;

public abstract class JobRegistry<I> {
    private final Map<I, ChainableJob<I>> jobs = new HashMap<I, ChainableJob<I>>();

    public abstract Class<? extends ChainableJob<I>> jobClazz();

    // TODO optimize for performance
    public synchronized ChainableJob<I> job(I jobId) {
        return jobs.get(jobId);
    }

    public synchronized void register(ChainableJob<I> job) {
        jobs.put(job.id(), job);
    }
}
