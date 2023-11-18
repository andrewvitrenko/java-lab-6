import java.util.ArrayList;
import java.util.Arrays;

public class Equipment {
    Device[] devices;

    public Equipment() {
        this.devices = new Device[]{
                new Iron(1000, 2500),
                new Oven(10000, 5000),
                new Microwave(750, 9000)
        };
    }

    public Device[] sort() {
        Device[] devicesClone = this.devices.clone();
        Arrays.sort(devicesClone, new SortByPower());

        return devicesClone;
    }

    public Device[] findByFrequency(double min, double max) throws LabException {
        ArrayList<Device> result = new ArrayList<>();

        for (Device device : this.devices) {
            if (device.frequency >= min && device.frequency <= max) {
                result.add(device);
            }
        }

        if (result.isEmpty()) throw new LabException("No devices in this frequency range");

        return result.toArray(new Device[0]);
    }

    // overloading in order there is no max provided
    public Device[] findByFrequency(double min) throws LabException {
        ArrayList<Device> result = new ArrayList<>();

        for (Device device : this.devices) {
            if (device.frequency >= min) {
                result.add(device);
            }
        }

        if (result.isEmpty()) throw new LabException("No devices in this frequency range");

        return result.toArray(new Device[0]);
    }

    public void turnOnOvens() {
        for (Device device : this.devices) {
            if (device instanceof Oven) {
                device.turnOn();
            }
        }
    }

    public double getConsumingPower() {
        Device[] turnedOnDevices = this.getTurnedOnDevices();
        double result = 0;

        for (Device device : turnedOnDevices) {
            result += device.power;
        }

        return result;
    }

    public Device[] getTurnedOnDevices() {
        ArrayList<Device> result = new ArrayList<>();

        for (Device device : this.devices) {
            if (device.isTurnedOn) {
                result.add(device);
            }
        }

        return result.toArray(new Device[0]);
    }
}
