package org.jenkinsci.plugins.buildsorter.dispatch;

import hudson.model.AbstractProject;
import hudson.model.Job;
import hudson.model.Project;
import hudson.model.Queue;
import jenkins.model.Jenkins;
import org.jenkinsci.plugins.buildsorter.model.ChainableJob;
import org.jenkinsci.plugins.buildsorter.model.NamedJob;
import org.jenkinsci.plugins.buildsorter.model.NamedJobRegistry;

import java.util.LinkedList;
import java.util.List;

public class NamedJobCreator implements JobCreator<String> {
    private final NamedJobRegistry namedJobRegistry;


    public NamedJobCreator(NamedJobRegistry namedJobRegistry) {
        this.namedJobRegistry = namedJobRegistry;

    }

    @Override
    public boolean handles(Queue.Item item) {
        //item.task;

        return false;
    }

    @Override
    public ChainableJob<String> createJob(Queue.Item item) {
        return new NamedJob(this.namedJobRegistry, extractJobName(item), extractDownstreamJobs(item));
    }

    private List<String> extractDownstreamJobs(Queue.Item item) {
        List<String> downstreamJobNames = new LinkedList<String>();
        for (Project project : Jenkins.getInstance().getAllItems(Project.class)) {
            if (extractJobName(item).equals(project.getName())) {
                for ( Object downstreamProject : project.getDownstreamProjects()) {
                    
                }
                break;
            }
        }
        return downstreamJobNames;
    }

    private String extractJobName(Queue.Item item) {
        return item.task.getName();
    }

}
