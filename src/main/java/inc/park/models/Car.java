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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Car compared = (Car) o;
        return plate.equals(compared.plate) && color.equals(compared.color);
    }

    @Override
    public String toString() {
        return "Car{" +
                "plate='" + plate + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
