package core.commands.raceCommands;
import core.factories.controllers.interfaces.RaceManager;
import exceptions.ArgumentException;
import exceptions.DuplicateModelException;
import exceptions.NoSetRaceException;
import exceptions.NonExistantModelException;
import models.Boat;
import repositories.Repository;

public class SignUpBoat extends RaceCommand {

    private Repository<Boat> boatRepository;

    public SignUpBoat(RaceManager raceController, Repository<Boat> boatRepository) {
        super(raceController);
        this.boatRepository = boatRepository;
    }

    @Override
    public String execute(String[] args) {
        String message = null;

        String model = args[1];

        try {
            this.getRaceController().addBoat(this.boatRepository.getEntity(model));
            message = String.format("Boat with model %s has signed up for the current Race.", args[1]);
        } catch (NoSetRaceException | DuplicateModelException | ArgumentException | NonExistantModelException e) {
            message = e.getMessage();
        }

        return message;
    }
}
