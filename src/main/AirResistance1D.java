package main;

import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;

import java.text.DecimalFormat;

public class AirResistance1D extends Simulation {

    //Simulation variables

    //ALL UNITS ARE SI UNITS (Meter, Second, KG, etc...)
    //Downwards is the negative direction

    public AirResistance1D(Environment environment, SimulationObject simulationObject, double a, double h, double v) {
        super(environment, simulationObject, a, h, v);
    }

    double mass = simulationObject.mass;

    double acc_g = environment.gravity;
    double acc_y = 0; //Initial acceleration

    double pos_y = h; //Initial position
    double vel_y = v; //Initial velocity
    double acc_air_res_y = (constant * vel_y * vel_y) / (2 * mass);

    double time = 0;
    double d_time;

    double pos_y_mid;
    double vel_y_mid;
    double acc_y_mid;
    double acc_air_res_y_mid;




    @Override
    XYChart.Series[] Calculate(double Start, double End, double Step) {

        d_time = Step;

        if (Start < 0) {
            Start = 0;
        }

        XYChart.Series Series = new XYChart.Series();
        Series.setName("Data points");


        XYChart.Series[] SeriesArray = new XYChart.Series[5];

        for(int i = 0; i < 5; i++) {
            SeriesArray[i] = new XYChart.Series();
        }

        SeriesArray[0].setName("Height");
        SeriesArray[1].setName("Vel Y");
        SeriesArray[2].setName("Acc Net Y");
        SeriesArray[3].setName("Acc Drag Y");
        SeriesArray[4].setName("Acc g");


        System.out.println("Initial:");
        System.out.println("Constant: " + df_precise.format(constant));
        System.out.println("Simulation:");

        for(double X = Start - 0.0000001; X < End + 0.0000001; X += Step) {
            int Index = Series.getData().size();

            //The purpose of this if-else statement is to determine weather drag is affecting the object in the positive or negative direction.
            if (vel_y < 0) {//The object is moving downwards so F of air resistance acts upwards
                acc_air_res_y = (constant * vel_y * vel_y) / (2 * mass);
            }
            else {//The object is moving upwards so F of air resistance acts downwards
                acc_air_res_y = -1 * (constant * vel_y * vel_y) / (2 * mass);
            }
            acc_y = a + acc_g + acc_air_res_y;

            System.out.println("Time: " + df.format(time) + " || Height: " + df_precise.format(pos_y) + " | Vel y: " + df_precise.format(vel_y) + " | Acc Net y: " + df_precise.format(acc_y) + " | Acc Drag y: " + df.format(acc_air_res_y) ); // log output


            XYChart.Data Height_Data = new XYChart.Data(X, pos_y);
            SeriesArray[0].getData().add(Height_Data);

            XYChart.Data Vel_Y_Data = new XYChart.Data(X, vel_y);
            SeriesArray[1].getData().add(Vel_Y_Data);

            XYChart.Data Acc_Net_Y_Data = new XYChart.Data(X, acc_y);
            SeriesArray[2].getData().add(Acc_Net_Y_Data);

            XYChart.Data Acc_Drag_Y_Data = new XYChart.Data(X, acc_air_res_y);
            SeriesArray[3].getData().add(Acc_Drag_Y_Data);

            XYChart.Data Acc_g_Data = new XYChart.Data(X, acc_g);
            SeriesArray[4].getData().add(Acc_g_Data);


            //Velocities at the middle of the tick
            pos_y_mid = pos_y + (vel_y * 0.5 * d_time);
            vel_y_mid = vel_y + (acc_y * 0.5 * d_time);

            if (vel_y < 0) {//The object is moving downwards so F of air resistance acts upwards
                acc_air_res_y_mid = (constant * vel_y_mid * vel_y_mid) / (2 * mass);
            }
            else {//The object is moving upwards so F of air resistance acts downwards
                acc_air_res_y_mid = -1 * (constant * vel_y_mid * vel_y_mid) / (2 * mass);
            }
            acc_y_mid = a + acc_g + acc_air_res_y_mid;

            pos_y += vel_y_mid * d_time; //Use vel_y_mid to calculate new pos_y
            vel_y += acc_y_mid * d_time; //Use acc_y_mid to calculate new vel_y
            time += d_time; //Increment time by d_time
        }

        //The purpose of this if-else statement is to determine weather drag is affecting the object in the positive or negative direction.
        if (vel_y < 0) {//The object is moving downwards so F of air resistance acts upwards
            acc_air_res_y = (constant * vel_y * vel_y) / (2 * mass);
        }
        else {//The object is moving upwards so F of air resistance acts downwards
            acc_air_res_y = -1 * (constant * vel_y * vel_y) / (2 * mass);
        }
        acc_y = a + acc_g + acc_air_res_y;

        System.out.println("Final:");
        System.out.println("Time: " + df.format(time) + " || Height: " + df_precise.format(pos_y) + " | Vel y: " + df_precise.format(vel_y) + " | Acc Net y: " + df_precise.format(acc_y) + " | Acc Drag y: " + df.format(acc_air_res_y) ); // log output


        return SeriesArray;
    }
}
