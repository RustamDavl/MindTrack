package ru.rstd.mindly.model.base;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class BaseModel {
    private UUID id;
}
