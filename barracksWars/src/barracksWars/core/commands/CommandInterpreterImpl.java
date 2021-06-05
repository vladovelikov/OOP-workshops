package barracksWars.core.commands;
import barracksWars.interfaces.CommandInterpreter;
import barracksWars.interfaces.Executable;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {

    private static final String COMMANDS_PACKAGE_PATH = "barracksWars.core.commands.";
    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImpl (Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }


    @Override
    public Executable interpretCommand(String[] data, String commandName) {
        Executable executable = null;

        commandName = Character.toUpperCase(commandName.charAt(0)) + commandName.substring(1);

        try {
            Class<? extends Executable> commandClass = (Class<? extends Executable>) Class.forName(COMMANDS_PACKAGE_PATH + commandName);
            Constructor<? extends Executable> constructor = commandClass.getDeclaredConstructor(String[].class);
            constructor.setAccessible(true);
            executable = constructor.newInstance((Object) data);

            this.injectFields(executable);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return executable;
    }

    private void injectFields(Executable executable) throws IllegalAccessException {
        Field[] fields = executable.getClass().getDeclaredFields();
        Field[] currentFields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.getAnnotations()[0].toString().contains("Inject")) {

                for (Field currentField : currentFields) {
                    if (currentField.getType().equals(field.getType())) {
                        field.setAccessible(true);
                        field.set(executable, currentField.get(this));
                    }
                }
            }
        }


    }
}
