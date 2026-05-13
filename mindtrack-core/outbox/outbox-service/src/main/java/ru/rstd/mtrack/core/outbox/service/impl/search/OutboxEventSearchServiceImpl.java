package ru.rstd.mtrack.core.outbox.service.impl.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.core.outbox.dao.search.OutboxSearchDao;
import ru.rstd.mtrack.core.outbox.model.OutboxEventModel;
import ru.rstd.mtrack.core.outbox.model.OutboxEventStatus;
import ru.rstd.mtrack.core.outbox.model.OutboxEventType;
import ru.rstd.mtrack.core.outbox.service.api.search.OutboxEventSearchService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutboxEventSearchServiceImpl implements OutboxEventSearchService {
    private final OutboxSearchDao outboxSearchDao;

    @Override
    public List<OutboxEventModel> findAllWithLock(OutboxEventType eventType, OutboxEventStatus eventStatus, Integer limit) {
        return outboxSearchDao.findAllWithLock(eventType, eventStatus, limit);
    }
}
