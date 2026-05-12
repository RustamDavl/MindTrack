package ru.rstd.mtrack.core.outbox.service.search;

import ru.rstd.mtrack.core.outbox.model.OutboxEventModel;
import ru.rstd.mtrack.core.outbox.model.OutboxEventStatus;
import ru.rstd.mtrack.core.outbox.model.OutboxEventType;

import java.util.List;

public interface OutboxEventSearchService {
    List<OutboxEventModel> findAllWithLock(OutboxEventType eventType, OutboxEventStatus eventStatus, Integer limit);
}
