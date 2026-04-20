package ru.rstd.mindly.job.service.factory;

import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import ru.rstd.mindly.job.api.factory.QuartzJobDefinition;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SchedulerFactory implements InitializingBean {
    private final Scheduler scheduler;
    private final List<QuartzJobDefinition> quartzJobDefinitionList;

    @Override
    public void afterPropertiesSet() {
        quartzJobDefinitionList.forEach(this::processJob);
    }

    private void processJob(QuartzJobDefinition factory) {
        try {
            scheduler.scheduleJob(factory.jobDetail(), factory.trigger());
        } catch (Exception e) {
        }
    }
}
