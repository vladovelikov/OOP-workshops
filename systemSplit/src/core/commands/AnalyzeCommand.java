package core.commands;
import core.system.SystemSplit;

public class AnalyzeCommand extends BaseCommand {

    private SystemSplit systemSplit;

    public AnalyzeCommand(Object... args) {
        super(args);
    }

    @Override
    protected void parseArgs(Object... args) {
        this.systemSplit = (SystemSplit) args[0];
    }

    @Override
    public void execute() {
        StringBuilder builder = new StringBuilder();
        int hardwareComponentsCount = this.systemSplit.getHardwareComponentsCount();
        int softwareComponentsCount = this.systemSplit.getSoftwareComponentsCount();

        int totalMemoryInUse = this.systemSplit.getTotalOperationalMemoryInUse();
        int totalCapacityTaken = this.systemSplit.getTotalCapacityTaken();

        int maximumMemory = this.systemSplit.getMaximumMemory() + totalMemoryInUse;
        int maximumCapacity = this.systemSplit.getMaximumCapacity() + totalCapacityTaken;

        builder.append("System Analysis")
                .append(System.lineSeparator())
                .append(String.format("Hardware Components: %d%n", hardwareComponentsCount))
                .append(String.format("Software Components: %d%n", softwareComponentsCount))
                .append(String.format("Total Operational Memory: %d / %d%n", totalMemoryInUse, maximumMemory))
                .append(String.format("Total Capacity Taken: %d / %d", totalCapacityTaken, maximumCapacity));
        System.out.println(builder.toString());

    }
}
