package ru.rstd.mindly.outbox.dao.search;

import ru.rstd.mindly.outbox.model.OutboxEventModel;
import ru.rstd.mindly.outbox.model.OutboxEventStatus;
import ru.rstd.mindly.outbox.model.OutboxEventType;

import java.util.List;

public interface OutboxSearchDao {
    List<OutboxEventModel> findAllWithLock(OutboxEventType eventType, OutboxEventStatus eventStatus, Integer limit);
}
