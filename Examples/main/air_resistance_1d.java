package main;

import java.text.DecimalFormat;

public class air_resistance_1d {
    public static void main(String[] args) {

        //ALL UNITS ARE SI UNITS (Meter, Second, KG, etc...)
        //Downwards is the negative direction

        DecimalFormat df = new DecimalFormat( "#,###,###,000.0000" );
        df.setPositivePrefix("+");

        //Tennis ball - Move this into its own object later
        double radius = 0.0335;
        double cross_section_area = radius * radius * Math.PI;
        double drag_coefficient = 0.53;
        double air_density = 1.225;
        double mass = 0.058;

        double constant = cross_section_area * drag_coefficient * air_density; //Compress into constant for more concise code
        // ===

        double gravity = 9.81;
        double acc_g = -gravity;


        double acc_y = 0; //Initial acceleration

        double pos_y = 500; //Initial position
        double vel_y = 0; //Initial velocity
        double acc_air_res_y = (constant * vel_y * vel_y) / (2 * mass);

        double time = 0;
        double d_time = 0.01;

        double pos_y_mid;
        double vel_y_mid;
        double acc_y_mid;
        double acc_air_res_y_mid;

        System.out.println("Initial:");
        System.out.println("Constant: " + df.format(constant));
        System.out.println("Simulation:");
        while (pos_y > 0) {

            //The purpose of this if-else statement is to determine weather drag is affecting the object in the positive or negative direction.
            if (vel_y < 0) {//The object is moving downwards so F of air resistance acts upwards
                acc_air_res_y = (constant * vel_y * vel_y) / (2 * mass);
            }
            else {//The object is moving upwards so F of air resistance acts downwards
                acc_air_res_y = -1 * (constant * vel_y * vel_y) / (2 * mass);
            }
            acc_y = acc_g + acc_air_res_y;

            System.out.println("Time: " + df.format(time) + " || Height: " + df.format(pos_y) + " | Vel y: " + df.format(vel_y) + " | Acc Net y: " + df.format(acc_y) + " | Acc Drag y: " + df.format(acc_air_res_y) ); // log output

            //Velocities at the middle of the tick
            pos_y_mid = pos_y + (vel_y * 0.5 * d_time);
            vel_y_mid = vel_y + (acc_y * 0.5 * d_time);

            if (vel_y < 0) {//The object is moving downwards so F of air resistance acts upwards
                acc_air_res_y_mid = (constant * vel_y_mid * vel_y_mid) / (2 * mass);
            }
            else {//The object is moving upwards so F of air resistance acts downwards
                acc_air_res_y_mid = -1 * (constant * vel_y_mid * vel_y_mid) / (2 * mass);
            }
            acc_y_mid = acc_g + acc_air_res_y_mid;

            pos_y += vel_y_mid * d_time; //Use vel_y_mid to calculate new pos_y
            vel_y += acc_y_mid * d_time; //Use acc_y_mid to calculate new vel_y
            time += d_time; //Increment time by d_time
        }

        //SIMULATION FINISHED

        //The purpose of this if-else statement is to determine weather drag is affecting the object in the positive or negative direction.
        if (vel_y < 0) {//The object is moving downwards so F of air resistance acts upwards
            acc_air_res_y = (constant * vel_y * vel_y) / (2 * mass);
        }
        else {//The object is moving upwards so F of air resistance acts downwards
            acc_air_res_y = -1 * (constant * vel_y * vel_y) / (2 * mass);
        }
        acc_y = acc_g + acc_air_res_y;

        System.out.println("Final:");
        System.out.println("Time: " + df.format(time) + " || Height: " + df.format(pos_y) + " | Vel y: " + df.format(vel_y) + " | Acc Net y: " + df.format(acc_y) + " | Acc Drag y: " + df.format(acc_air_res_y) ); // log output
    }
}
