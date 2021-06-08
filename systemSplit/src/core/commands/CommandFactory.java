package core.commands;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class CommandFactory {

    private static final String BASE_COMMAND_PACKAGE = "core.commands.";
    private static final String COMMAND_SUFFIX = "Command";

    @SuppressWarnings("unchecked")
    public static Command buildCommand(String commandName, Object... args) {

        try {
            Class commandClass = Class.forName(BASE_COMMAND_PACKAGE + commandName + COMMAND_SUFFIX);
            Constructor<Command> constructor = commandClass.getDeclaredConstructors()[0];
            return constructor.newInstance(new Object[]{args});
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
