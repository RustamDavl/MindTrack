package ru.rstd.mtrack.core.job.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import ru.rstd.mtrack.core.job.api.EmailVerificationMessageEventService;
import ru.rstd.mtrack.core.kafka.service.api.KafkaSenderService;
import ru.rstd.mtrack.core.kafka.model.KafkaMessage;
import ru.rstd.mtrack.core.outbox.model.OutboxEventModel;
import ru.rstd.mtrack.core.outbox.model.OutboxEventStatus;
import ru.rstd.mtrack.core.outbox.model.OutboxEventType;
import ru.rstd.mtrack.core.outbox.service.mutation.OutboxEventMutationService;
import ru.rstd.mtrack.core.outbox.service.api.search.OutboxEventSearchService;
import ru.rstd.mtrack.core.security.model.user.UserWithEmailToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailVerificationMessageEventServiceImpl implements EmailVerificationMessageEventService {
    private static final String EMAIL_VERIFICATION_MESSAGE_EVENT_TOPIC = "email_verification_message_event";
    private final OutboxEventSearchService outboxEventSearchService;
    private final OutboxEventMutationService outboxEventMutationService;
    private final KafkaSenderService kafkaSenderService;
    private final TransactionTemplate transactionTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void doJob() {
        List<OutboxEventModel> eventModels =
                Optional.ofNullable(
                        transactionTemplate.execute(status -> {
                            var result = outboxEventSearchService.findAllWithLock(
                                    OutboxEventType.USER_REGISTRATION,
                                    OutboxEventStatus.NEW,
                                    100
                            );
                            outboxEventMutationService.updateAll(result.stream().map(OutboxEventModel::getId).toList(),
                                    OutboxEventStatus.IN_PROGRESS);
                            return result;
                        })
                ).orElse(List.of());
        List<OutboxEventModel> precessed = new ArrayList<>();
        for (OutboxEventModel eventModel : eventModels) {
            try {
                kafkaSenderService.sendAsync(new KafkaMessage(EMAIL_VERIFICATION_MESSAGE_EVENT_TOPIC, null, convert(eventModel.getPayload())));
                precessed.add(eventModel);
            } catch (Exception e) {
                //todo
            }
        }
        outboxEventMutationService.updateAll(precessed.stream().map(OutboxEventModel::getId).toList(), OutboxEventStatus.PROCESSED);
    }

    private UserWithEmailToken convert(Object o) {
        try {
            return objectMapper.readValue((String) o, UserWithEmailToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
