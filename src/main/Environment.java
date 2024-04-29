package main;

public class Environment {

    String name;

    //earth: 9.81 m/s^2     - mars: 3.71 m/s^2
    double gravity;

    //earth: 1.225 kg/m^3   - mars: 0.020 kg/m^3
    double air_density;

    public Environment(String name, double gravity, double air_density) {
        this.name = name;
        this.gravity = gravity;
        this.air_density = air_density;
    }

    public Environment(String preset) {
        if (preset == "Earth") {
            this.name = "Earth";
            this.gravity = 9.81;
            this.air_density = 1.225;
        }
        else if (preset == "Mars") {
            this.name = "Mars";
            this.gravity = 3.71;
            this.air_density = 0.020;
        }
        else {
            throw new ExceptionInInitializerError("No Such Preset. Available presets: 'Earth', 'Mars'");
        }
    }
}
