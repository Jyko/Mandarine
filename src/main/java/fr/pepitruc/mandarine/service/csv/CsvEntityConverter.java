package fr.pepitruc.mandarine.service.csv;

import java.util.Collection;
import java.util.List;

public interface CsvEntityConverter<M, E> {

    public List<E> convertToEntities(final Collection<M> models);
    public E convertToEntity(final M model);
}
