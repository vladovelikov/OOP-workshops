import core.*;
import core.commandInterpreter.CommandHandler;
import core.factories.controllers.MainController;
import core.factories.controllers.RaceController;
import core.factories.interfaces.CommandFactory;
import io.InputManager;
import io.OutputManager;
import models.Boat;
import models.Engine;
import repositories.Repository;
import repositories.RepositoryImpl;

public class Main {

    public static void main(String[] args) {

        Repository<Boat> boatRepository = new RepositoryImpl<>();
        Repository<Engine> engineRepository = new RepositoryImpl<>();
        CommandFactory commandFactory = new CommandWorkshop(new EngineWorkshop(), new BoatWorkshop(engineRepository),
                engineRepository, boatRepository, new RaceController());
        MainController controller = new MainController(new InputManager(), new OutputManager(),
                new CommandHandler(commandFactory));
        controller.run();

    }
}
