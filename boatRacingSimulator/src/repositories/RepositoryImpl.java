package repositories;
import exceptions.DuplicateModelException;
import exceptions.NonExistantModelException;
import models.interfaces.ModelGetter;
import java.util.HashMap;
import java.util.Map;

public class RepositoryImpl<T extends ModelGetter> implements Repository<T> {

    private Map<String, T> entities;

    public RepositoryImpl() {
        this.entities = new HashMap<>();
    }

    @Override
    public void addEntity(T entity) throws DuplicateModelException {
        if (!this.entities.containsKey(entity.getModel())) {
            this.entities.put(entity.getModel(), entity);
        } else {
            throw new DuplicateModelException();
        }
    }

    @Override
    public T getEntity(String model) throws NonExistantModelException {
        if (!this.entities.containsKey(model)) {
            throw new NonExistantModelException();
        } else {
            return this.entities.get(model);
        }
    }
}
