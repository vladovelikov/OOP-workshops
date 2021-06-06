package repositories;

import exceptions.DuplicateModelException;
import exceptions.NonExistantModelException;
import models.interfaces.ModelGetter;

public interface Repository<T extends ModelGetter> {

    void addEntity(T entity) throws DuplicateModelException;
    T getEntity(String model) throws NonExistantModelException;

}
