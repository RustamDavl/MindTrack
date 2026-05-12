package ru.rstd.mtrack.core.job.api.factory;

import org.quartz.JobDetail;
import org.quartz.Trigger;

public interface QuartzJobDefinition {
    JobDetail jobDetail();
    Trigger trigger();
}
