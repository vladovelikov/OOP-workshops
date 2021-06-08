package core.commands;

import core.models.software.ExpressSoftware;
import core.models.software.Software;
import core.system.SystemSplit;

public class RegisterExpressSoftwareCommand extends BaseCommand {

    private SystemSplit systemSplit;
    private String hardwareComponentName;
    private String name;
    private int capacity;
    private int memory;

    public RegisterExpressSoftwareCommand(Object... args) {
        super(args);
    }

    @Override
    protected void parseArgs(Object... args) {
        this.systemSplit = (SystemSplit) args[0];
        this.hardwareComponentName = (String) args[1];
        this.name = (String) args[2];
        this.capacity = Integer.parseInt(args[3].toString());
        this.memory = Integer.parseInt(args[4].toString());
    }

    @Override
    public void execute() {
        Software expressSoftware = new ExpressSoftware(this.name, this.capacity, this.memory);
        this.systemSplit.addSoftwareComponentToHardware(this.hardwareComponentName, expressSoftware);
    }
}
