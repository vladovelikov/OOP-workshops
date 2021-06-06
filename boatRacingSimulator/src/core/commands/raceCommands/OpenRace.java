package core.commands.raceCommands;
import core.RaceWorkshop;
import core.factories.controllers.interfaces.RaceManager;
import core.factories.interfaces.RaceFactory;
import exceptions.ArgumentException;
import exceptions.RaceAlreadyExistsException;
import models.Race;

public class OpenRace extends RaceCommand {

    private RaceFactory raceFactory;

    public OpenRace(RaceManager raceController) {
        super(raceController);
        raceFactory = new RaceWorkshop();
    }

    @Override
    public String execute(String[] args) {
        String message = null;

        try {
            Race race = this.raceFactory.produce(args);
            this.getRaceController().setRace(race);

            message = String.format("A new race with distance %s meters, wind speed %s m/s and ocean current speed %s m/s has been set.",
                       race.getDistance(), race.getWindSpeed(), race.getOceanCurrentSpeed());

        } catch (RaceAlreadyExistsException | ArgumentException e) {
            message = e.getMessage();
        }

        return message;
    }
}
