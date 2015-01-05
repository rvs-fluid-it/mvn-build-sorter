package org.jenkinsci.plugins.buildsorter.dispatch;

import hudson.model.Job;
import hudson.model.Queue;
import org.jenkinsci.plugins.buildsorter.model.ChainableJob;
import org.jenkinsci.plugins.buildsorter.model.JobRegistry;
import org.jenkinsci.plugins.buildsorter.mvn.model.MavenJobRegistry;
import org.jenkinsci.plugins.buildsorter.mvn.model.GroupIdArtifactIdVersion;
import org.jenkinsci.plugins.buildsorter.mvn.model.MavenJob;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRegistry {
    private final Map<Class, JobRegistry> jobRegistries = new HashMap<Class, JobRegistry>();
    private final Map<Queue.Item, Object> jobIds = new HashMap<Queue.Item, Object>();
    private final Map<Queue.Item, Class> jobTypes = new HashMap<Queue.Item, Class>();

    // Testing
    public ItemRegistry(List<JobRegistry> jobRegistries) {
        for (JobRegistry jobRegistry: jobRegistries) {
            this.jobRegistries.put(jobRegistry.jobClazz(), jobRegistry);
        }
    }

    public synchronized ChainableJob job(Queue.Item item) {
        ChainableJob result = null;
        Class jobType = jobTypes.get(item);
        Object jobId = jobIds.get(item);
        if (jobType != null && jobId != null) {
            if (jobRegistries.containsKey(jobType)) {
                result = jobRegistries.get(jobType).job(jobId);
            } else {
                // TODO warning
            }
        }
        return result;
    }

    public void register(Queue.Item item) {

    }

    public synchronized void remove(Queue.Item item) {
        jobTypes.remove(item);
        jobIds.remove(item);
    }
}
