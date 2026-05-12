package ru.rstd.mtrack.core.outbox.dao.search;

import ru.rstd.mtrack.core.outbox.model.OutboxEventModel;
import ru.rstd.mtrack.core.outbox.model.OutboxEventStatus;
import ru.rstd.mtrack.core.outbox.model.OutboxEventType;

import java.util.List;

public interface OutboxSearchDao {
    List<OutboxEventModel> findAllWithLock(OutboxEventType eventType, OutboxEventStatus eventStatus, Integer limit);
}
