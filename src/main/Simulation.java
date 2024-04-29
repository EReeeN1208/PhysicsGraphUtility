package main;

import javafx.scene.chart.XYChart;

import java.text.DecimalFormat;

public abstract class Simulation {

    DecimalFormat df = new DecimalFormat( "#,###,###,000.0000" );
    DecimalFormat df_precise = new DecimalFormat( "#,###,###,000.0000000" );

    public Simulation() {

    }

    abstract XYChart.Series[] Calculate(double Start, double End, double Step);
}
