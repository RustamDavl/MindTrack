package ru.rstd.mtrack.core.service.impl.mutation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.core.dao.api.mutation.note.NoteMutationDao;
import ru.rstd.mtrack.core.security.exception.AccessDeniedException;
import ru.rstd.mtrack.core.security.service.api.MtrSecurityContextService;
import ru.rstd.mtrack.core.service.api.mutation.note.NoteMutationService;
import ru.rstd.mtrack.core.model.note.Note;
import ru.rstd.mtrack.core.service.api.search.note.NoteSearchService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteMutationServiceImpl implements NoteMutationService {
    private final NoteMutationDao noteMutationDao;
    private final MtrSecurityContextService mtrSecurityContextService;
    private final NoteSearchService noteSearchService;

    @Override
    public Note save(Note note) {
        UUID userId = getCurrentUserId();
        note.setUserId(userId);
        return noteMutationDao.save(note);
    }

    @Override
    public Note update(Note note) {
        Note existsNote = noteSearchService.find(note.getId());

        UUID userId = getCurrentUserId();

        if(!userId.equals(existsNote.getUserId())) {
            throw new AccessDeniedException("Note does not exist");
        }

        return noteMutationDao.save(note);
    }

    @Override
    public boolean delete(UUID noteId) {
        Note existsNote = noteSearchService.find(noteId);

        UUID userId = getCurrentUserId();

        if(!userId.equals(existsNote.getUserId())) {
            throw new AccessDeniedException("Note does not exist");
        }

        try {
            noteMutationDao.delete(existsNote.getId());
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    private UUID getCurrentUserId() {
        return mtrSecurityContextService.getCurrentUserId();
    }
}
