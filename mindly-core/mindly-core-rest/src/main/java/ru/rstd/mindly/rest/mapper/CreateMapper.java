package ru.rstd.mindly.rest.mapper;

public interface CreateMapper<DTO, M>{
    M toModel(DTO dto);
}
