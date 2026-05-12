package ru.rstd.mtrack.core.outbox.service.mutation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.core.outbox.dao.mutation.OutboxMutationDao;
import ru.rstd.mtrack.core.outbox.model.OutboxEventModel;
import ru.rstd.mtrack.core.outbox.model.OutboxEventStatus;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutboxEventMutationServiceImpl implements OutboxEventMutationService {
   private final OutboxMutationDao outboxMutationDao;

    @Override
    public OutboxEventModel save(OutboxEventModel model) {
        return outboxMutationDao.save(model);
    }

    @Override
    public OutboxEventModel update(OutboxEventModel model) {
        return outboxMutationDao.update(model);
    }

    @Override
    @Transactional
    public void updateAll(List<UUID> ids, OutboxEventStatus status) {
        outboxMutationDao.updateAll(ids, status);
    }
}
