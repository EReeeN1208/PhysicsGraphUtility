package main;

//https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/line-chart.htm#CIHGBCFI

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Button;

import static java.lang.Double.parseDouble;

public class Controller {

    @FXML public TextField InputA;
    @FXML public TextField InputB;
    @FXML public TextField InputC;

    @FXML public TextField InputStart;
    @FXML public TextField InputEnd;
    @FXML public TextField InputStep;

    @FXML public Button GraphButton;

    @FXML public NumberAxis LineChartX;
    @FXML public NumberAxis LineChartY;

    @FXML public LineChart<Number,Number> LineChart;

    public void SubmitPressed(ActionEvent actionEvent) {
        System.out.println("Button Pressed");

        double A = 0;
        double B = 0;
        double C = 0;

        double Start = 0;
        double End = 0;
        double Step = 0;

        Quadratic QuadraticObject;

        try {
            A = parseDouble(InputA.getText());
            B = parseDouble(InputB.getText());
            C = parseDouble(InputC.getText());

            Start = parseDouble(InputStart.getText());
            End = parseDouble(InputEnd.getText());
            Step = parseDouble(InputStep.getText());

        }
        catch (Exception e) {
            System.out.println("Please enter double values into the text fields");
            System.out.println(e);
        }

        AirResistance1D DragSimulation = new AirResistance1D();

        LineChart.getData().clear();

        XYChart.Series[] SeriesArray = DragSimulation.Calculate(Start,End, Step);

        LineChart.setCreateSymbols(false);

        for(XYChart.Series i: SeriesArray) {
            LineChart.getData().add(i);
        }

        //LineChart.getData().add(SeriesArray[1]);


            /* Legacy Code (Commented out for reference)
            QuadraticObject = new Quadratic(A, B, C);

            System.out.println(QuadraticObject);

            LineChart.getData().clear();
            XYChart.Series Series = new XYChart.Series();
            Series.setName("Data points");

            for(double X = Start - 0.0000001; X < End + 0.0000001; X += Step) {
                int Index = Series.getData().size();

                double Y = QuadraticObject.getY(X);
                System.out.println("Graphing point X: " + X + " Y: " + Y + ", Point number: " + Index);

                XYChart.Data Data= new XYChart.Data(X, Y);

                Tooltip NodeTooltip = new Tooltip();
                NodeTooltip.setText("X: " + X + " Y: " + Y);

                Tooltip.install(Data.getNode(), NodeTooltip);

                Series.getData().add(Data);
            }

            LineChart.setCreateSymbols(false);
            LineChart.getData().add(Series);
             */




    }


}
