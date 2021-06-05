package barracksWars.core.commands;
import barracksWars.annotations.Inject;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

public class Retire extends Command {

    @Inject
    private Repository repository;

    public Retire(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String result;

        try {
            this.repository.removeUnit(this.getData()[1]);
            result = String.format("%s retired!", this.getData()[1]);
        } catch (IllegalArgumentException ex) {
            result = ex.getMessage();
        }

        return result;
    }
}
