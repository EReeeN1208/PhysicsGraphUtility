package sample;

public class Quadratic {
    protected double A;
    protected double B;
    protected double C;

    public Quadratic(double A, double B, double C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public double getY(double X) {
        return (A * (X * X)) + (B * X) + C;
    }

    public String toString() {
        return (A + "x^2 + " + B + "x + " +C);
    }
}
