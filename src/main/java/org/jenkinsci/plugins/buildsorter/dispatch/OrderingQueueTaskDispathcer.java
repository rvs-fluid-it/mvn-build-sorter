package org.jenkinsci.plugins.buildsorter.dispatch;

import hudson.model.Queue;
import hudson.model.queue.CauseOfBlockage;
import hudson.model.queue.QueueTaskDispatcher;

public class OrderingQueueTaskDispathcer extends QueueTaskDispatcher {
    private ItemRegistry itemRegistry;

    @Override
    public CauseOfBlockage canRun(Queue.Item item) {
        itemRegistry.job(item);
        return super.canRun(item);
    }
}
