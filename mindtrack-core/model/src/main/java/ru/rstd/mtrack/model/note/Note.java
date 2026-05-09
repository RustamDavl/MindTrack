package ru.rstd.mtrack.model.note;

import lombok.Data;
import ru.rstd.mtrack.common.model.audit.Auditable;


@Data
public class Note extends Auditable {
    private String title;
    private String body;
    private String color;
}
