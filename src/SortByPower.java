import java.util.Comparator;

public class SortByPower implements Comparator<Device> {
    // sorts by device power in ascending order
    public int compare(Device a, Device b) {
        if (a == null) return 1;
        if (b == null) return -1;

        return (int) (a.power - b.power);
    }
}
