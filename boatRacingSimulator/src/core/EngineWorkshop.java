package core;
import core.factories.interfaces.EngineFactory;
import exceptions.ArgumentException;
import models.Engine;
import models.Jet;
import models.Sterndrive;

public class EngineWorkshop implements EngineFactory {

    @Override
    public Engine produce(String[] args) throws ArgumentException {
        String type = args[args.length - 1];
        String model = args[1];
        int horsepower = Integer.parseInt(args[2]);
        int displacement = Integer.parseInt(args[3]);

        Engine engine = null;

        switch (type) {
            case "Jet":
                engine = new Jet(model, horsepower, displacement);
                break;
            case "Sterndrive":
                engine = new Sterndrive(model, horsepower, displacement);
                break;
        }

        return engine;
    }
}
