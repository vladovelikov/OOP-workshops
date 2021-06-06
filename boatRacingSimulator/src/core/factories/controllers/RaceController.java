package core.factories.controllers;
import core.factories.controllers.interfaces.RaceManager;
import exceptions.*;
import models.Boat;
import models.Race;

public class RaceController implements RaceManager {

    private Race race;
    private String raceResult;

    public RaceController() {
        this.race = null;
    }

    @Override
    public void setRace(Race race) throws RaceAlreadyExistsException {
        if (this.race == null) {
            this.race = race;
        } else {
            throw new RaceAlreadyExistsException("The current race has already been set.");
        }
    }

    @Override
    public void startRace() throws NoSetRaceException, InsufficientContestantsException {
        this.checkRaceSetStatus();
        this.race.startRace();
        this.raceResult = this.race.getResult();
        this.race = null;
    }

    @Override
    public String getRaceResult() {
        String result = this.raceResult;
        this.raceResult = "";
        return result;
    }

    @Override
    public String getStatistics() {
        return this.race.getStatistics();
    }

    @Override
    public void addBoat(Boat boat) throws NoSetRaceException, DuplicateModelException, ArgumentException {
        this.checkRaceSetStatus();
        this.race.addParticipant(boat);
    }

    private void checkRaceSetStatus() throws NoSetRaceException {
        if (this.race == null) {
            throw new NoSetRaceException("There is currently no race set.");
        }
    }

}
