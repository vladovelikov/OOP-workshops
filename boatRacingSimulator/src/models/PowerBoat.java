package models;
import exceptions.ArgumentException;

public class PowerBoat extends Boat {

    private Engine primaryEngine;
    private Engine secondaryEngine;

    public PowerBoat(String model, int weight, Engine primaryEngine, Engine secondaryEngine) throws ArgumentException {
        super(model, weight);
        this.primaryEngine = primaryEngine;
        this.secondaryEngine = secondaryEngine;
    }

    @Override
    public double calculateSpeed(Race race) {
        return this.primaryEngine.getOutput()
                + this.secondaryEngine.getOutput() - this.getWeight()
                + race.getOceanCurrentSpeed() / 5.0;
    }
}
