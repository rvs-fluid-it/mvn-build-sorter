package org.jenkinsci.plugins.buildsorter.dispatch;

import hudson.model.Queue;
import org.jenkinsci.plugins.buildsorter.model.QueueItem;

public class JenkinsQueueItem implements QueueItem {
    private final Queue.Item item;

    public JenkinsQueueItem(Queue.Item item) {
        this.item = item;
    }



}
