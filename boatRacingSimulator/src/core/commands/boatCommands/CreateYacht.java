package core.commands.boatCommands;
import core.factories.interfaces.BoatFactory;
import models.Boat;
import repositories.Repository;

public class CreateYacht extends BoatCommand {

    public CreateYacht(Repository<Boat> boatRepository, BoatFactory boatFactory) {
        super(boatRepository, boatFactory);
    }
}
