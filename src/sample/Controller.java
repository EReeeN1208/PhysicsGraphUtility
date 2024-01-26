package sample;

//https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/line-chart.htm#CIHGBCFI

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import javax.sound.sampled.Line;
import javax.tools.Tool;

import static java.lang.Double.parseDouble;

public class Controller {

    @FXML public TextField InputA;
    @FXML public TextField InputB;
    @FXML public TextField InputC;

    @FXML public Button GraphButton;

    @FXML public NumberAxis LineChartX;
    @FXML public NumberAxis LineChartY;

    @FXML public LineChart<Number,Number> LineChart;

    public void SubmitPressed(ActionEvent actionEvent) {
        System.out.println("Button Pressed");

        double A = 0;
        double B = 0;
        double C = 0;


        Quadratic QuadraticObject;

        try {
            A = parseDouble(InputA.getText());
            B = parseDouble(InputB.getText());
            C = parseDouble(InputC.getText());
            QuadraticObject = new Quadratic(A, B, C);

            System.out.println(QuadraticObject.toString());

            LineChart.getData().clear();
            XYChart.Series Series = new XYChart.Series();
            Series.setName("Data points");


            for(double X = -10; X < 25.0000001; X += 0.2) {
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


        }
        catch (Exception e) {
            System.out.println("Please enter double values into the text fields");
        }



    }


}
