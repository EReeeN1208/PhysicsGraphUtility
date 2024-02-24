package tests;

import java.text.DecimalFormat;

public class Test {
    public static void main(String[] args) {

        DecimalFormat df = new DecimalFormat( "#,###,###,000.00" );
        df.setPositivePrefix("+"); // Set positive sign prefix

        double g = 9.81;

        double y = 10;

        double ay = -g;
        double vy = 0;

        double t = 0;
        double dt = 0.01;



        while (y > 0) {
            System.out.println("Time: " + df.format(t) + " - Height: " + df.format(y) + " - Velocity y: " + df.format(vy));
            ay = -g;
            y += vy * dt; // use old vy to calculate new y
            vy += ay * dt; // use old ay to calculate new vy
            t += dt;
        }
        System.out.println(df.format(t));
    }
}
