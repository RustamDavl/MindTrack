package ru.rstd.mtrack.core.common.mapper;

public interface Mapper<E, M> {
    E toEntity(M from);
    M toModel(E from);
}
