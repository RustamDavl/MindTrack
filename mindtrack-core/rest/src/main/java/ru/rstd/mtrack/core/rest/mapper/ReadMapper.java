package ru.rstd.mtrack.core.rest.mapper;

public interface ReadMapper<M, DTO> {
    DTO toReadDto(M m);
}
