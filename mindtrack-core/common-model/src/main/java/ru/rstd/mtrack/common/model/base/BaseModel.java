package ru.rstd.mtrack.common.model.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public abstract class BaseModel {
    public BaseModel() {}

    public BaseModel(UUID id) {
        this.id = id;
    }

    private UUID id;
}
