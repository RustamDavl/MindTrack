package ru.rstd.mtrack.outbox.service.search;

import ru.rstd.mtrack.outbox.model.OutboxEventModel;
import ru.rstd.mtrack.outbox.model.OutboxEventStatus;
import ru.rstd.mtrack.outbox.model.OutboxEventType;

import java.util.List;

public interface OutboxEventSearchService {
    List<OutboxEventModel> findAllWithLock(OutboxEventType eventType, OutboxEventStatus eventStatus, Integer limit);
}
