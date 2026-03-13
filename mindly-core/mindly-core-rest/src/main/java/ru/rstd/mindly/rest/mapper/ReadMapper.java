package ru.rstd.mindly.rest.mapper;

public interface ReadMapper<M, DTO> {
    DTO toReadDto(M m);
}
