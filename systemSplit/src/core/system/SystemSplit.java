package core.system;
import core.models.hardware.Hardware;
import core.models.software.Software;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.ToIntFunction;

public class SystemSplit {

    private Map<String, Hardware> hardwareComponents;

    public SystemSplit() {
        this.hardwareComponents = new LinkedHashMap<>();
    }

    public void addHardwareComponent(Hardware hardware) {
        this.hardwareComponents.put(hardware.getName(), hardware);
    }

    public void addSoftwareComponentToHardware(String softwareName, Software software) {
        if (this.hardwareComponents.containsKey(softwareName)) {
            Hardware hardware = this.hardwareComponents.get(softwareName);
            hardware.addSoftware(software);
        }
    }

    public void releaseSoftware(String hardwareName, String softwareName) {
        if (this.hardwareComponents.containsKey(hardwareName)) {
            Hardware hardware = this.hardwareComponents.get(hardwareName);
            hardware.removeSoftware(softwareName);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.hardwareComponents.values().stream()
                .sorted((h1, h2) -> {
                    if (h1.getType().equalsIgnoreCase("Power")) {
                        return -1;
                    } else {
                        return 1;
                    }
                })
                .forEach(h -> {sb.append(h.toString());
                sb.append(System.lineSeparator());
                });
        return sb.toString().trim();
    }

    public int getHardwareComponentsCount() {
        return this.hardwareComponents.size();
    }

    public int getSoftwareComponentsCount() {
        return this.getSumOfValues(Hardware::getSoftwareComponentsCount);
    }

    public int getTotalOperationalMemoryInUse() {
        return this.getSumOfValues(Hardware::getUsedMemory);
    }

    public int getTotalCapacityTaken() {
        return this.getSumOfValues(Hardware::getUsedCapacity);
    }

    public int getMaximumMemory() {
        return this.getSumOfValues(Hardware::getMaximumMemory);
    }

    public int getMaximumCapacity() {
        return this.getSumOfValues(Hardware::getMaximumCapacity);
    }

    private int getSumOfValues(ToIntFunction<Hardware> function) {
        return this.hardwareComponents
                .values()
                .stream()
                .mapToInt(function)
                .sum();
    }

}
