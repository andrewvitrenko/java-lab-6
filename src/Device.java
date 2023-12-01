public abstract class Device {
    String name;
    double power;
    boolean isTurnedOn;
    double frequency;

    public Device(String name, double power, double frequency) {
        this.name = name;
        this.isTurnedOn = false;
        this.power = power;
        this.frequency = frequency;
    }

    public void turnOn() {
        this.isTurnedOn = true;
    }

    public String toString() {
        return String.format("\n%s: power - %.2f, frequency - %.2f, is on - %b", this.name, this.power, this.frequency, this.isTurnedOn);
    }

    @Override
    public boolean equals(Object obj) {
        // Check if the same object reference
        if (this == obj) {
            return true;
        }

        // Check if the other object is null or of a different class
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Cast the other object to a Device
        Device otherDevice = (Device) obj;

        // Compare individual fields for equality
        return Double.compare(otherDevice.power, power) == 0 &&
                isTurnedOn == otherDevice.isTurnedOn &&
                Double.compare(otherDevice.frequency, frequency) == 0 &&
                name.equals(otherDevice.name);
    }

    public int hashCode() {
        int result = 1;

        result += result * 31 + this.name.hashCode() + (int) this.power + (int) this.frequency;

        return result;
    }
}
