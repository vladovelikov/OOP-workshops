package core.commands.boatCommands;
import core.commands.interfaces.Command;
import core.factories.interfaces.EngineFactory;
import exceptions.ArgumentException;
import exceptions.DuplicateModelException;
import models.Engine;
import repositories.Repository;

public class CreateBoatEngine implements Command {

    private EngineFactory engineFactory;
    private Repository<Engine> engineRepository;

    public CreateBoatEngine(EngineFactory engineFactory, Repository<Engine> engineRepository) {
        this.engineFactory = engineFactory;
        this.engineRepository = engineRepository;
    }

    @Override
    public String execute(String[] args) {
        String result = null;

        try {
            Engine engine = this.engineFactory.produce(args);
            if (engine != null) {
                engineRepository.addEntity(engine);
                result = String.format("Engine model %s with %s HP and displacement %s cm3 created successfully.",
                        args[1], args[2], args[3]);
            }
        } catch (DuplicateModelException | ArgumentException e) {
            result = e.getMessage();
        }

        return result;
    }
}
