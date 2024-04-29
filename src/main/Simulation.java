package main;

import javafx.scene.chart.XYChart;

import java.text.DecimalFormat;

public abstract class Simulation {

    DecimalFormat df = new DecimalFormat( "#,###,###,000.0000" );
    DecimalFormat df_precise = new DecimalFormat( "#,###,###,000.0000000" );

    double constant;

    public Environment environment;
    public SimulationObject simulationObject;

    double a;
    double h;
    double v;

    public Simulation(Environment environment, SimulationObject simulationObject, double a, double h, double v) {
        df.setPositivePrefix("+");
        df_precise.setPositivePrefix("+");

        this.environment = environment;
        this.simulationObject = simulationObject;

        this.a = a;
        this.h = h;
        this.v = v;

        constant = simulationObject.cross_section_area * simulationObject.drag_coefficient * environment.air_density;
    }

    abstract XYChart.Series[] Calculate(double Start, double End, double Step);
}
