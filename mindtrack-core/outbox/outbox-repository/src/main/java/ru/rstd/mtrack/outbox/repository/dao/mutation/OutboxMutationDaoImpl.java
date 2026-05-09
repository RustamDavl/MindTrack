package ru.rstd.mtrack.outbox.repository.dao.mutation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mtrack.outbox.dao.mutation.OutboxMutationDao;
import ru.rstd.mtrack.outbox.entity.OutboxEventEntity;
import ru.rstd.mtrack.outbox.model.OutboxEventModel;
import ru.rstd.mtrack.outbox.model.OutboxEventStatus;
import ru.rstd.mtrack.outbox.repository.jpa.OutboxEventEntityJpaRepository;
import ru.rstd.mtrack.outbox.repository.mapper.OutboxEventMapper;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OutboxMutationDaoImpl implements OutboxMutationDao {
    private final OutboxEventMapper mapper;
    private final OutboxEventEntityJpaRepository repository;

    @Override
    public OutboxEventModel save(OutboxEventModel model) {
        OutboxEventEntity entity = mapper.toEntity(model);
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public OutboxEventModel update(OutboxEventModel model) {
        return null;
    }

    @Override
    public void updateAll(List<UUID> ids, OutboxEventStatus status) {
        repository.updateAll(ids, status.name());
    }
}
