package ru.rstd.mtrack.outbox.dao.mutation;

import ru.rstd.mtrack.outbox.model.OutboxEventModel;
import ru.rstd.mtrack.outbox.model.OutboxEventStatus;

import java.util.List;
import java.util.UUID;

public interface OutboxMutationDao {
    OutboxEventModel save(OutboxEventModel model);
    OutboxEventModel update(OutboxEventModel model);

    void updateAll(List<UUID> ids, OutboxEventStatus status);
}
