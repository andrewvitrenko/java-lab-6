import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            Equipment equipment = new Equipment();

            equipment.turnOnOvens();
            System.out.printf("Consuming power - %.2f\n", equipment.getConsumingPower());

            Device[] sortedByPower = equipment.sort();
            System.out.println("\nSorted by power");
            System.out.println(Arrays.toString(sortedByPower));

            Device[] filteredDevices = equipment.findByFrequency(500, 5000);
            System.out.println("\nFound by frequency");
            System.out.println(Arrays.toString(filteredDevices));
        } catch (LabException e) {
            System.out.println(e.getMessage());
        }
    }
}
