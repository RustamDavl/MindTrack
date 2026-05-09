package ru.rstd.mtrack.rest.mapper;

public interface ReadMapper<M, DTO> {
    DTO toReadDto(M m);
}
