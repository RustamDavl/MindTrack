package ru.rstd.mtrack.rest.mapper;

public interface CreateMapper<DTO, M>{
    M toModel(DTO dto);
}
