package ru.rstd.mtrack.core.outbox.service.mutation;

import ru.rstd.mtrack.core.outbox.model.OutboxEventModel;
import ru.rstd.mtrack.core.outbox.model.OutboxEventStatus;

import java.util.List;
import java.util.UUID;

public interface OutboxEventMutationService {
    OutboxEventModel save(OutboxEventModel model);
    OutboxEventModel update(OutboxEventModel model);
    void updateAll(List<UUID> ids, OutboxEventStatus status);
}
