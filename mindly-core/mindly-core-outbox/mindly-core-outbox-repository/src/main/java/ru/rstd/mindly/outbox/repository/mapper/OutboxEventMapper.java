package ru.rstd.mindly.outbox.repository.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rstd.mindly.common.model.mapper.Mapper;
import ru.rstd.mindly.outbox.entity.OutboxEventEntity;
import ru.rstd.mindly.outbox.entity.OutboxEventEntityStatus;
import ru.rstd.mindly.outbox.entity.OutboxEventEntityType;
import ru.rstd.mindly.outbox.model.OutboxEventModel;
import ru.rstd.mindly.outbox.model.OutboxEventStatus;
import ru.rstd.mindly.outbox.model.OutboxEventType;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OutboxEventMapper implements Mapper<OutboxEventEntity, OutboxEventModel> {
    private final ObjectMapper objectMapper;

    @Override
    public OutboxEventEntity toEntity(OutboxEventModel model) {
        OutboxEventEntity entity = new OutboxEventEntity();
        entity.setId(model.getId());
        entity.setAttempts(Optional.ofNullable(model.getAttempts()).orElse(0));
        entity.setEventType(OutboxEventEntityType.valueOf(model.getEventType().name()));
        entity.setStatus(OutboxEventEntityStatus.valueOf(model.getStatus().name()));
        entity.setPayload(convertToJson(model.getPayload()));
        entity.setProcessedAt(model.getProcessedAt());
        entity.setLastErrorMessage(model.getLastErrorMessage());
        return entity;
    }

    @Override
    public OutboxEventModel toModel(OutboxEventEntity entity) {
        OutboxEventModel model = new OutboxEventModel();
        model.setId(entity.getId());
        model.setAttempts(entity.getAttempts());
        model.setEventType(OutboxEventType.valueOf(entity.getEventType().name()));
        model.setStatus(OutboxEventStatus.valueOf(entity.getStatus().name()));
        model.setPayload(entity.getPayload());
        model.setProcessedAt(entity.getProcessedAt());
        model.setLastErrorMessage(entity.getLastErrorMessage());
        return model;
    }

    private String convertToJson(Object payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
