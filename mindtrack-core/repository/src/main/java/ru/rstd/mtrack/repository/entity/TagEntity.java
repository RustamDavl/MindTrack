package ru.rstd.mtrack.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.rstd.mtrack.common.entity.BaseEntity;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "mtrack", name = "tag")
public class TagEntity extends BaseEntity {

    public TagEntity() {
    }

    public TagEntity(UUID id, String color, String name) {
        super(id);
        this.color = color;
        this.name = name;
    }

    private String name;
    private String color;
}
