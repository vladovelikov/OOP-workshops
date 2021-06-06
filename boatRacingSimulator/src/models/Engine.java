package models;
import exceptions.ArgumentException;
import helpers.Validator;
import models.interfaces.ModelGetter;
import models.interfaces.OutputProducer;

public abstract class Engine implements Comparable<Engine>, OutputProducer, ModelGetter {

    private static final int MIN_MODEL_LENGTH = 3;
    private String model;
    private int horsepower;
    private int displacement;

    protected Engine(String model, int horsepower, int displacement) throws ArgumentException {
        this.setModel(model);
        this.setHorsepower(horsepower);
        this.setDisplacement(displacement);
    }

    public void setModel(String model) throws ArgumentException {
        if (Validator.validateEngineModel(model)) {
            this.model = model;
        } else {
            throw new ArgumentException(String.format("Engine model must be at least %d symbols long.", MIN_MODEL_LENGTH));
        }
    }

    public void setHorsepower(int horsepower) throws ArgumentException {
        if (Validator.validateParam(horsepower)) {
            this.horsepower = horsepower;
        } else {
            throw new ArgumentException(Validator.generateErrorMessage("Horsepower"));
        }
    }

    public void setDisplacement(int displacement) throws ArgumentException {
        if (Validator.validateParam(horsepower)) {
            this.displacement = displacement;
        } else {
            throw new ArgumentException(Validator.generateErrorMessage("Displacement"));
        }
    }

    @Override
    public int hashCode() {
        return model.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass().getSuperclass() != getClass().getSuperclass()) return false;

        Engine engine = (Engine) o;

        return model.equals(engine.model);
    }

    @Override
    public int compareTo(Engine o) {
        return this.model.compareTo(o.model);
    }

    protected int getHorsepower() {
        return this.horsepower;
    }

    protected int getDisplacement() {
        return this.displacement;
    }

    @Override
    public String getModel() {
        return this.model;
    }
}
