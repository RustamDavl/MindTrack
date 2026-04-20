package ru.rstd.mindly.job.service;

import lombok.RequiredArgsConstructor;
import org.quartz.JobExecutionContext;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.QuartzJobBean;
import ru.rstd.mindly.job.api.EmailVerificationMessageEventService;

@RequiredArgsConstructor
public class SendEmailVerificationMessageJob extends QuartzJobBean {
   private final EmailVerificationMessageEventService service;

    @Override
    protected void executeInternal(@NonNull JobExecutionContext jobExecutionContext) {
        service.doJob();
    }
}
