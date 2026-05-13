package ru.rstd.mtrack.core.rest.mapper;

public interface CreateMapper<DTO, M>{
    M toModel(DTO dto);
}
