package main;

public class SimulationObject {

    String name;

    double radius;
    double cross_section_area;
    double drag_coefficient;
    double mass;

    //For non-spherical objects
    public SimulationObject (String name, double radius, double cross_section_area, double drag_coefficient, double mass) {
        this.name = name;
        this.radius = radius;
        this.cross_section_area = cross_section_area;
        this.drag_coefficient = drag_coefficient;
        this.mass = mass;
    }

    //For spherical objects
    public SimulationObject (String name, double radius, double drag_coefficient, double mass) {
        this.name = name;
        this.radius = radius;
        this.cross_section_area = radius * radius * Math.PI;
        this.drag_coefficient = drag_coefficient;
        this.mass = mass;
    }
}