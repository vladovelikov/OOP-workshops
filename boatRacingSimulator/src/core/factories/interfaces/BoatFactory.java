package core.factories.interfaces;
import exceptions.ArgumentException;
import exceptions.NonExistantModelException;
import models.Boat;

public interface BoatFactory {

    Boat produce(String[] args) throws ArgumentException, NonExistantModelException;
}
