package ru.rstd.mtrack.core.service.impl.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.core.dao.api.search.note.NoteSearchDao;
import ru.rstd.mtrack.core.exception.NoteNotFoundException;
import ru.rstd.mtrack.core.service.api.search.note.NoteSearchService;
import ru.rstd.mtrack.core.model.note.Note;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteSearchServiceImpl implements NoteSearchService {
    private final NoteSearchDao noteSearchDao;

    @Override
    public Note find(UUID id) {
        return noteSearchDao.find(id)
                .orElseThrow(() -> new NoteNotFoundException(String.format("Note with id %s not found", id)));
    }
}
