package ru.rstd.mtrack.job.service.factory;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Component;
import ru.rstd.mtrack.job.api.factory.QuartzJobDefinition;
import ru.rstd.mtrack.job.service.SendEmailVerificationMessageJob;

@Component
public class EmailVerificationQuartzJobDefinitionImpl implements QuartzJobDefinition {
    private static final String GROUP = "email_verification_group";

    @Override
    public JobDetail jobDetail() {
        return JobBuilder.newJob(SendEmailVerificationMessageJob.class)
                .withIdentity("send_email_verification_job", GROUP)
                .build();
    }

    @Override
    public Trigger trigger() {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("send_email_verification_trigger", GROUP)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                .build();
    }
}
