package core.engine;

import core.commands.Command;
import core.commands.CommandFactory;
import core.system.SystemSplit;
import io.reader.Reader;

public class Engine {

    private static final String END_COMMAND = "System Split";
    private SystemSplit systemSplit;
    private Reader reader;

    public Engine(SystemSplit systemSplit, Reader reader) {
        this.systemSplit = systemSplit;
        this.reader = reader;
    }

    public void run() {
        String line = this.reader.readLine();

        while (!line.equals(END_COMMAND)) {

            String[] tokens = line.split("[\\(,\\s\\)]+");
            String commandName = tokens[0];
            Object[] params = new Object[tokens.length];
            params[0] = this.systemSplit;
            for (int i = 1; i < tokens.length; i++) {
                params[i] = tokens[i];
            }

            Command command = CommandFactory.buildCommand(commandName, params);
            command.execute();

            line = this.reader.readLine();
        }
        Command command = CommandFactory.buildCommand("SystemSplit", this.systemSplit);
        command.execute();

    }
}
