package main;

//https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/line-chart.htm#CIHGBCFI

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import static java.lang.Double.parseDouble;

public class Controller {

    @FXML public ComboBox<Environment> ComboBoxEnvironment;
    @FXML public ComboBox<SimulationObject> ComboBoxObject;

    @FXML public CheckBox CheckBoxHeight;
    @FXML public CheckBox CheckBoxVelY;
    @FXML public CheckBox CheckBoxAccNet;
    @FXML public CheckBox CheckBoxAccDrag;
    @FXML public CheckBox CheckBoxAccG;

    @FXML public TextField InputA;
    @FXML public TextField InputB;
    @FXML public TextField InputC;

    @FXML public TextField InputPropertyA;
    @FXML public TextField InputPropertyH;
    @FXML public TextField InputPropertyV;

    @FXML public TextField InputStart;
    @FXML public TextField InputEnd;
    @FXML public TextField InputStep;

    @FXML public Button GraphButton;

    @FXML public NumberAxis LineChartX;
    @FXML public NumberAxis LineChartY;

    @FXML public LineChart<Number,Number> LineChart;

    Environment Earth = new Environment("Earth");
    Environment Mars = new Environment("Mars");

    SimulationObject Tennis_Ball = new SimulationObject("Tennis Ball", 0.0335, 0.53, 0.058);
    SimulationObject Soccer_Ball = new SimulationObject("Soccer Ball", 0.1143, 0.25, 0.428);

    @FXML
    public void initialize() {

        ObservableList<Environment> EnvironmentList = FXCollections.observableArrayList();
        EnvironmentList.addAll(Earth, Mars);


        ObservableList<SimulationObject> ObjectList = FXCollections.observableArrayList();
        ObjectList.addAll(Tennis_Ball, Soccer_Ball);

        ComboBoxEnvironment.setItems(EnvironmentList);
        ComboBoxObject.setItems(ObjectList);


        //Set some initial parameters for QoL

        ComboBoxEnvironment.setValue(Earth);
        ComboBoxObject.setValue(Tennis_Ball);

        CheckBoxHeight.setSelected(true);
        CheckBoxVelY.setSelected(true);
        CheckBoxAccNet.setSelected(true);

        InputPropertyH.setText("80");
        InputPropertyV.setText("25");

        InputEnd.setText("10");
        InputStep.setText("0.1");
    }

    public void SubmitPressed(ActionEvent actionEvent) {
        System.out.println("Button Pressed");

        double QuadA = 0;
        double QuadB = 0;
        double QuadC = 0;

        double PropA = 0;
        double PropH = 0;
        double PropV = 0;

        double Start = 0;
        double End = 0;
        double Step = 0;

        CheckBox[] CheckBoxArray = new CheckBox[5];

        CheckBoxArray[0] = CheckBoxHeight;
        CheckBoxArray[1] = CheckBoxVelY;
        CheckBoxArray[2] = CheckBoxAccNet;
        CheckBoxArray[3] = CheckBoxAccDrag;
        CheckBoxArray[4] = CheckBoxAccG;

        Quadratic QuadraticObject;

        try {
            QuadA = parseDouble(InputA.getText());
            QuadB = parseDouble(InputB.getText());
            QuadC = parseDouble(InputC.getText());

            PropA = parseDouble(InputPropertyA.getText());
            PropH = parseDouble(InputPropertyH.getText());
            PropV = parseDouble(InputPropertyV.getText());

            Start = parseDouble(InputStart.getText());
            End = parseDouble(InputEnd.getText());
            Step = parseDouble(InputStep.getText());

        }
        catch (Exception e) {
            System.out.println("Please enter double values into the text fields");
            System.out.println(e);
        }


        AirResistance1D DragSimulation = new AirResistance1D(ComboBoxEnvironment.getValue(), ComboBoxObject.getValue(), PropA, PropH, PropV);

        LineChart.getData().clear();

        XYChart.Series[] SeriesArray = DragSimulation.Calculate(Start,End, Step);

        LineChart.setCreateSymbols(false);

        for(int i = 0; i < 5; i++) {
            if(CheckBoxArray[i].isSelected()) {
                LineChart.getData().add(SeriesArray[i]);
            }
        }
    }
}
