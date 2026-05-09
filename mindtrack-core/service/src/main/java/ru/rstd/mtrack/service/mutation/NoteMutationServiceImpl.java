package ru.rstd.mtrack.service.mutation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.api.mutation.note.NoteMutationDao;
import ru.rstd.mtrack.input.mutation.note.NoteMutationService;
import ru.rstd.mtrack.model.note.Note;

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
