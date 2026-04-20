package ru.rstd.mindly.outbox.service.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mindly.outbox.dao.search.OutboxSearchDao;
import ru.rstd.mindly.outbox.model.OutboxEventModel;
import ru.rstd.mindly.outbox.model.OutboxEventStatus;
import ru.rstd.mindly.outbox.model.OutboxEventType;

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
