import java.util.Comparator;

public class SortByPower implements Comparator<Device> {
    // sorts by device power in ascending order
    public int compare(Device a, Device b) {
        return (int) (a.power - b.power);
    }
}
