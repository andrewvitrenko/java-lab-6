public abstract class Device {
    String name;
    double power;
    boolean isTurnedOn;
    double frequency;

    public void turnOn() {
        this.isTurnedOn = true;
    }

    public Device(String name, double power, double frequency) {
        this.name = name;
        this.isTurnedOn = false;
        this.power = power;
        this.frequency = frequency;
    }

    public String toString() {
        return String.format("\n%s: power - %.2f, frequency - %.2f, is on - %b", this.name, this.power, this.frequency, this.isTurnedOn);
    }
}
