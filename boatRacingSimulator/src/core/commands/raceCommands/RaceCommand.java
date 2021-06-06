package core.commands.raceCommands;
import core.commands.interfaces.Command;
import core.factories.controllers.interfaces.RaceManager;

public abstract class RaceCommand implements Command {

    private RaceManager raceController;

    public RaceCommand(RaceManager raceController) {
        this.raceController = raceController;
    }

    protected RaceManager getRaceController() {
        return this.raceController;
    }

}
