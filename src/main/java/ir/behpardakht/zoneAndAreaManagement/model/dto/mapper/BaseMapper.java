package ir.behpardakht.zoneAndAreaManagement.model.dto.mapper;

import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

public interface BaseMapper<D, E> {
    D toDTO(E e);

    List<D> toDTOs(Iterable<E> e);

    E toEntity(D d);

    List<E> toEntities(Collection<D> ds);

    void updateModel(D d, @MappingTarget E e);
}