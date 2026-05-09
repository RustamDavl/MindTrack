package ru.rstd.mtrack.outbox.dao.search;

import ru.rstd.mtrack.outbox.model.OutboxEventModel;
import ru.rstd.mtrack.outbox.model.OutboxEventStatus;
import ru.rstd.mtrack.outbox.model.OutboxEventType;

import java.util.List;

public interface OutboxSearchDao {
    List<OutboxEventModel> findAllWithLock(OutboxEventType eventType, OutboxEventStatus eventStatus, Integer limit);
}
