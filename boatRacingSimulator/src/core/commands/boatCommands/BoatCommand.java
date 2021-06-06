package core.commands.boatCommands;
import core.commands.interfaces.Command;
import core.factories.interfaces.BoatFactory;
import exceptions.ArgumentException;
import exceptions.DuplicateModelException;
import exceptions.NonExistantModelException;
import models.Boat;
import repositories.Repository;

public abstract class BoatCommand implements Command {

    private Repository<Boat> boatRepository;
    private BoatFactory boatFactory;

    protected BoatCommand(Repository<Boat> boatRepository, BoatFactory boatFactory) {
        this.boatRepository = boatRepository;
        this.boatFactory = boatFactory;
    }

    @Override
    public String execute(String[] args) {
        String message = null;

        try {
            Boat boat = this.boatFactory.produce(args);
            if (boat != null) {
                boatRepository.addEntity(boat);
                message = String.format("%s with model %s registered successfully.",
                        this.getClass().getSimpleName().replace("Create", "").replace("Boat", " boat"), args[1]);
            }
        } catch (DuplicateModelException | ArgumentException | NonExistantModelException e) {
            message = e.getMessage();
        }

        return message;
    }
}
