package core.factories.controllers.interfaces;
import exceptions.*;
import models.Boat;
import models.Race;

public interface RaceManager {

    void setRace(Race race) throws RaceAlreadyExistsException;
    void addBoat(Boat boat) throws NoSetRaceException, DuplicateModelException, ArgumentException;
    void startRace() throws NoSetRaceException, InsufficientContestantsException;
    String getRaceResult();
    String getStatistics();

}
