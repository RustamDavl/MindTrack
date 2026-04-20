package ru.rstd.mindly.job.api.factory;

import org.quartz.JobDetail;
import org.quartz.Trigger;

public interface QuartzJobDefinition {
    JobDetail jobDetail();
    Trigger trigger();
}
