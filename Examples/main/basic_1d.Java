package main;

import java.text.DecimalFormat;

public class basic_1d {
    public static void main(String[] args) {

        DecimalFormat df = new DecimalFormat( "#,###,###,000.0000" );
        df.setPositivePrefix("+");

        double gravity = 9.81;

        double pos_y = 10;

        double acc_y = -gravity;
        double vel_y = 0;

        double time = 0;
        double d_time = 0.01;



        while (pos_y > 0) {
            System.out.println("Time: " + df.format(time) + " - Height: " + df.format(pos_y) + " - Velocity y: " + df.format(vel_y)); // log output
            pos_y += vel_y * d_time; // use old vel_y to calculate new pos_y
            vel_y += acc_y * d_time; // use old acc_y to calculate new vel_y
            time += d_time; // increment time by d_time
        }
        System.out.println("Final Time: " + df.format(time) + " - Final Height: " + df.format(pos_y) + " - Final Velocity y: " + df.format(vel_y)); // log final output
    }
}
