package main;

import javafx.scene.chart.XYChart;

import java.text.DecimalFormat;

public abstract class Simulation {

    DecimalFormat df = new DecimalFormat( "#,###,###,000.0000" );
    DecimalFormat df_precise = new DecimalFormat( "#,###,###,000.0000000" );

    double constant;

    public Environment environment;
    public SimulationObject simulationObject;

    public Simulation(Environment environment, SimulationObject simulationObject) {
        df.setPositivePrefix("+");
        df_precise.setPositivePrefix("+");

        this.environment = environment;
        this.simulationObject = simulationObject;

        constant = simulationObject.cross_section_area * simulationObject.drag_coefficient * environment.air_density;
    }

    abstract XYChart.Series[] Calculate(double Start, double End, double Step);
}
