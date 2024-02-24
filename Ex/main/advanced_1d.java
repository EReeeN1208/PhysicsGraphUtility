package main;

import java.text.DecimalFormat;

public class advanced_1d {
    public static void main(String[] args) {

        DecimalFormat df = new DecimalFormat( "#,###,###,000.0000" );
        df.setPositivePrefix("+");

        double gravity = 9.81;

        double pos_y = 10;

        double acc_y = -gravity;
        double vel_y = 0;

        double time = 0;
        double d_time = 0.01;

        double pos_y_mid;
        double vel_y_mid;
        double acc_y_mid;


        while (pos_y > 0) {
            System.out.println("Time: " + df.format(time) + " - Height: " + df.format(pos_y) + " - Velocity y: " + df.format(vel_y)); // log output
            acc_y = -gravity; // will be the same value every time in the current state of the program

            pos_y_mid = pos_y + (vel_y * 0.5 * d_time);
            vel_y_mid = vel_y + (acc_y * 0.5 * d_time);
            acc_y_mid = -gravity; // will be the same value every time in the current state of the program

            pos_y += vel_y_mid * d_time; // use vel_y_mid to calculate new pos_y
            vel_y += acc_y_mid * d_time; // use acc_y_mid to calculate new vel_y
            time += d_time; // increment time by d_time
        }
        System.out.println("Final Time: " + df.format(time) + " - Final Height: " + df.format(pos_y) + " - Final Velocity y: " + df.format(vel_y)); // log final output
    }
}
