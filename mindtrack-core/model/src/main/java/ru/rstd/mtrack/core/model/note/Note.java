package ru.rstd.mtrack.core.model.note;

import lombok.Data;
import ru.rstd.mtrack.core.common.model.audit.Auditable;

import java.util.UUID;


@Data
public class Note extends Auditable {
    private String title;
    private String body;
    private String color;
    private UUID userId;
}
