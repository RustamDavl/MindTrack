package ru.rstd.mtrack.core.outbox.repository.dao.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mtrack.core.outbox.dao.search.OutboxSearchDao;
import ru.rstd.mtrack.core.outbox.model.OutboxEventModel;
import ru.rstd.mtrack.core.outbox.model.OutboxEventStatus;
import ru.rstd.mtrack.core.outbox.model.OutboxEventType;
import ru.rstd.mtrack.core.outbox.repository.jpa.OutboxEventEntityJpaRepository;
import ru.rstd.mtrack.core.outbox.repository.mapper.OutboxEventMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OutboxSearchDaoImpl implements OutboxSearchDao {
    private final OutboxEventMapper eventMapper;
    private final OutboxEventEntityJpaRepository repository;

    @Override
    public List<OutboxEventModel> findAllWithLock(OutboxEventType eventType, OutboxEventStatus eventStatus, Integer limit) {
        return repository.findAllWithLock(eventType.name(), eventStatus.name(), limit)
                .stream()
                .map(eventMapper::toModel)
                .toList();
    }
}
