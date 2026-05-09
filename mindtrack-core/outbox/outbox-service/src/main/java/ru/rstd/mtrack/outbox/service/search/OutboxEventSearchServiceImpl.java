package ru.rstd.mtrack.outbox.service.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.outbox.dao.search.OutboxSearchDao;
import ru.rstd.mtrack.outbox.model.OutboxEventModel;
import ru.rstd.mtrack.outbox.model.OutboxEventStatus;
import ru.rstd.mtrack.outbox.model.OutboxEventType;

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
