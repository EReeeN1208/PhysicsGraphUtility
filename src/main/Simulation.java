package main;

import javafx.scene.chart.XYChart;

public abstract class Simulation {

    public Simulation() {

    }

    abstract XYChart.Series[] Calculate(double Start, double End, double Step);
}
