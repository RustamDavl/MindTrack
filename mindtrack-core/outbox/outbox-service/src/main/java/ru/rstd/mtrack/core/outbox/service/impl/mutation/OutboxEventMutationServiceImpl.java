package ru.rstd.mtrack.core.outbox.service.impl.mutation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.core.outbox.dao.mutation.OutboxMutationDao;
import ru.rstd.mtrack.core.outbox.model.OutboxEventModel;
import ru.rstd.mtrack.core.outbox.model.OutboxEventStatus;
import ru.rstd.mtrack.core.outbox.service.api.mutation.OutboxEventMutationService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OutboxEventMutationServiceImpl implements OutboxEventMutationService {
   private final OutboxMutationDao outboxMutationDao;

    @Override
    public OutboxEventModel save(OutboxEventModel model) {
        log.debug("Saving outbox event message: {}", model);
        return outboxMutationDao.save(model);
    }

    @Override
    public OutboxEventModel update(OutboxEventModel model) {
        log.debug("Updating outbox event message: {}", model);
        return outboxMutationDao.update(model);
    }

    @Override
    @Transactional
    public void updateAll(List<UUID> ids, OutboxEventStatus status) {
        log.debug("Updating outbox event messages for ids: {} to status: {}", ids, status.name());
        outboxMutationDao.updateAll(ids, status);
    }
}
