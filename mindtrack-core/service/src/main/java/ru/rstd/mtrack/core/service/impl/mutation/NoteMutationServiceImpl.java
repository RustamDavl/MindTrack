package ru.rstd.mtrack.core.service.impl.mutation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.core.dao.api.mutation.note.NoteMutationDao;
import ru.rstd.mtrack.core.service.api.mutation.note.NoteMutationService;
import ru.rstd.mtrack.core.model.note.Note;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class NoteMutationServiceImpl implements NoteMutationService {
    private final NoteMutationDao noteMutationDao;

    @Override
    public Note save(Note note) {
        final OffsetDateTime now = OffsetDateTime.now();
        note.setCreatedAt(now);
        note.setUpdatedAt(now);

        return noteMutationDao.save(note);
    }
}
