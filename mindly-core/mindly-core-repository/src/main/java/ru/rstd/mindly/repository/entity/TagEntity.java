package ru.rstd.mindly.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.rstd.mindly.common.entity.BaseEntity;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "mindly", name = "tag")
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
