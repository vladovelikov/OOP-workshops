package models;
import exceptions.ArgumentException;
import helpers.Validator;

public class SailBoat extends Boat {

    private int sailEfficiency;

    public SailBoat(String model, int weight, int sailEfficiency) throws ArgumentException {
        super(model, weight);
        this.setSailEfficiency(sailEfficiency);
    }

    private void setSailEfficiency(int sailEfficiency) throws ArgumentException {
        if (Validator.validateSailEfficiency(sailEfficiency)) {
            this.sailEfficiency = sailEfficiency;
        } else {
            throw new ArgumentException("Sail Effectiveness must be between [1...100].");
        }
    }

    @Override
    public double calculateSpeed(Race race) {
        return (race.getWindSpeed() * (this.sailEfficiency / 100.0))
                - this.getWeight() + (race.getOceanCurrentSpeed() / 2.0);
    }
}
