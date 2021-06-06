package core.commandInterpreter;
import core.commands.interfaces.Command;
import core.factories.interfaces.CommandFactory;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler implements CommandInterpreter {

    private CommandFactory commandFactory;
    private Map<String, Command> commands;

    public CommandHandler(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
        this.commands = new HashMap<>();
    }

    @Override
    public String interpret(String type, String[] args) {
        String message = null;

        if (this.commands.containsKey(type)) {
            message = this.commands.get(type).execute(args);
        } else {
            Command cmd = this.commandFactory.produce(type);
            if (cmd != null) {
                message = cmd.execute(args);
                this.commands.put(type, cmd);
            } else {
                message = "Invalid command!";
            }
        }

        return message;
    }

}
