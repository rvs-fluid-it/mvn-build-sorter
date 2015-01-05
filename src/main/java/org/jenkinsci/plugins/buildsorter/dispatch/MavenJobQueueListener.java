package org.jenkinsci.plugins.buildsorter.dispatch;

import hudson.model.Queue;
import hudson.model.queue.QueueListener;

public class MavenJobQueueListener extends QueueListener {
    private ItemRegistry itemRegistry;

    @Override
    public void onEnterWaiting(Queue.WaitingItem wi) {
        itemRegistry.register(wi);
    }

    @Override
    public void onLeft(Queue.LeftItem li) {
        itemRegistry.remove(li);
    }
}
