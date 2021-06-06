package models;
import exceptions.ArgumentException;

public class Sterndrive extends Engine {

    public Sterndrive(String model, int horsepower, int displacement) throws ArgumentException {
        super(model, horsepower, displacement);
    }

    @Override
    public int getOutput() {
        return this.getHorsepower() * 7 + this.getDisplacement();
    }
}
