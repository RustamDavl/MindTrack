package ru.rstd.mindly.service.mutation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rstd.mindly.api.mutation.note.NoteMutationDao;
import ru.rstd.mindly.input.mutation.note.NoteMutationService;
import ru.rstd.mindly.model.note.Note;

import java.time.OffsetDateTime;

@Getter
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
