package core.commands.raceCommands;

import core.factories.controllers.interfaces.RaceManager;
import exceptions.InsufficientContestantsException;
import exceptions.NoSetRaceException;

public class StartRace extends RaceCommand {

    public StartRace(RaceManager raceController) {
        super(raceController);
    }

    @Override
    public String execute(String[] args) {
        String message = null;

        try {
            this.getRaceController().startRace();
            message = this.getRaceController().getRaceResult();
        } catch (InsufficientContestantsException | NoSetRaceException e) {
            message = e.getMessage();
        }

        return message;
    }
}
