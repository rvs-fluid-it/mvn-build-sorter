package org.jenkinsci.plugins.buildsorter.dispatch;

import hudson.model.Queue;
import org.jenkinsci.plugins.buildsorter.model.ChainableJob;

public interface JobCreator<J> {
    boolean handles(Queue.Item item);
    ChainableJob<J> createJob(Queue.Item item);
}