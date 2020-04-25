package inc.park.models;

public class Car {
    private String plate;
    private String color;

    public Car() {
    }

    public Car(String plate, String color) {
        this.plate = plate;
        this.color = color;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "plate='" + plate + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
