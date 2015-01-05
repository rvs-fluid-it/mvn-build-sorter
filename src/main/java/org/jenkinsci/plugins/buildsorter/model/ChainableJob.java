package org.jenkinsci.plugins.buildsorter.model;

import java.util.LinkedList;
import java.util.List;

public class ChainableJob<I> {
    private final JobRegistry<I> jobRegistry;
    private final I jobId;
    private final List<I> downstreamJobIds;

    public ChainableJob(JobRegistry<I> jobRegistry, I jobId, List<I> downstreamJobIds) {
        this.jobRegistry =jobRegistry;
        this.jobId = jobId;
        this.downstreamJobIds = downstreamJobIds;
    }

    private QueueItem queueItem;
    private final List<QueueItem> upstreamQueueItems = new LinkedList<QueueItem>();

    public List<I> downstreamJobIds() {
        return downstreamJobIds;
    }
    public List<ChainableJob<I>> downstreamJobs() {
        List<ChainableJob<I>>  jobs = new LinkedList<ChainableJob<I>>();
        for (I jobId : downstreamJobIds) {
            ChainableJob<I> job = jobRegistry.job(jobId);
            if (job != null) {
               jobs.add(job);
            } else {
                // log warning
            }
        }
        return jobs;
    }

    synchronized public void setQueueItem(QueueItem queueItem) {
        if (this.queueItem != null) {
            onQueueItemDone();
            // TODO Log warning
        }
        this.queueItem = queueItem;
        for (ChainableJob<I> downstreamJob : downstreamJobs()) {
            downstreamJob.addUpstreamQueueItem(queueItem);
        }
    }

    synchronized public void onQueueItemDone() {
        for (ChainableJob<I> downstreamJob : downstreamJobs()) {
            downstreamJob.removeUpstreamQueueItem(queueItem);
        }
        this.queueItem = null;
    }

    synchronized public void addUpstreamQueueItem(QueueItem queueItem) {
        this.upstreamQueueItems.add(queueItem);
    }

    synchronized public void removeUpstreamQueueItem(QueueItem queueItem) {
        this.upstreamQueueItems.remove(queueItem);
    }

    synchronized boolean isWaitingOnUpstreamQueueItems() {
        return !this.upstreamQueueItems.isEmpty();
    }

    boolean canRun() {
        return !isWaitingOnUpstreamQueueItems();
    }

    public I id() {
        return jobId;
    }
}
