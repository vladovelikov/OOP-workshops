package core.models.hardware;
import core.models.software.Software;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public abstract class Hardware {

    private String name;
    private Type type;
    private int maximumCapacity;
    private int maximumMemory;
    private List<Software> softwareComponents;
    private int usedCapacity;
    private int usedMemory;

    protected Hardware(String name, Type type, int maximumCapacity, int maximumMemory) {
        this.name = name;
        this.type = type;
        this.maximumCapacity = maximumCapacity;
        this.maximumMemory = maximumMemory;
        this.softwareComponents = new ArrayList<>();
        this.usedCapacity = 0;
        this.usedMemory = 0;
    }

    public int getMaximumCapacity() {
        return this.maximumCapacity;
    }

    public int getMaximumMemory() {
        return this.maximumMemory;
    }

    public int getSoftwareComponentsCount() {
        return this.softwareComponents.size();
    }

    public void removeSoftware(String softwareName) {
        Software software = this.softwareComponents
                .stream()
                .filter(s -> s.getName().equalsIgnoreCase(softwareName))
                .findFirst()
                .orElse(null);
        if (software != null) {
            this.softwareComponents.remove(software);
            this.setUsedResources(software, (a, b) -> a -= b);
        }
    }

    public String getType() {
        return this.type.toString();
    }

    protected enum Type {

        POWER,
        HEAVY
    }

    public String getName() {
        return this.name;
    }

    public void addSoftware(Software software) {
        if (software.getMemoryConsumption() <= this.getMaximumMemory()
                && software.getCapacityConsumption() <= this.getMaximumCapacity()) {
            this.softwareComponents.add(software);
            this.setUsedResources(software, (a, b) -> a += b);
        }
    }

    public final int getUsedCapacity() {
        return this.usedCapacity;
    }

    public final int getUsedMemory() {
        return this.usedMemory;
    }

    @Override
    public String toString() {
        long expressSoftwareCount = this.softwareComponents.stream()
                .filter(s -> s.getClass().getSimpleName()
                .equals("ExpressSoftware"))
                .count();

        return String.format("Hardware Component - %s%n" +
                        "Express Software Components - %d%n" +
                        "Light Software Components - %d%n" +
                        "Memory Usage: %d / %d%n" +
                        "Capacity Usage: %d / %d%n" +
                        "Type: %s%n" +
                        "Software Components: %s",
                this.getName(),
                expressSoftwareCount,
                this.softwareComponents.size() - expressSoftwareCount,
                this.usedMemory,
                this.getMaximumMemory() + this.usedMemory,
                this.usedCapacity,
                this.getMaximumCapacity() + this.usedCapacity,
                this.type.compareTo(Type.HEAVY) == 0 ? "Heavy" : "Power",
                this.softwareComponents.size() == 0 ? "None" :
                        this.softwareComponents.stream().map(Software::getName).collect(Collectors.joining(", "))
                );
    }

    private void setUsedResources(Software software, BinaryOperator<Integer> operator) {
        this.usedCapacity = operator.apply(this.usedCapacity, software.getCapacityConsumption());
        this.usedMemory = operator.apply(this.usedMemory, software.getMemoryConsumption());
    }
}
