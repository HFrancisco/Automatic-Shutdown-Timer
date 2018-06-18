package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class UI_Controller extends BorderPane {

    private Controller controller = new Controller();

    private Label instrucLabel;
    private Label shutdownLabel;
    private TextField timeField;
    private Button submitButton;
    private Button abortButton;

    private HBox instrucBox = new HBox();
    private HBox inputBox = new HBox(5);
    private HBox abortBox = new HBox();

    public UI_Controller(){

        // Initialize UI Elements

        // Instruction stuff
        instrucLabel = new Label("Input a time to shutdown the computer." + "\nUse HH:mm format. 24hr.");

        instrucBox.getChildren().add(instrucLabel);
        instrucBox.setPadding(new Insets(25,0,-50,0));
        instrucBox.setAlignment(Pos.TOP_CENTER);
        setTop(instrucBox);

        // Input stuff
        shutdownLabel = new Label("Shutdown Time: ");

        timeField = new TextField();
        timeField.setPrefColumnCount(16);

        submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SubmitAction();
            }
        });

        inputBox.getChildren().add(shutdownLabel);
        inputBox.getChildren().add(timeField);
        inputBox.getChildren().add(submitButton);
        inputBox.setAlignment(Pos.CENTER);
        setCenter(inputBox);


        // Abort stuff
        abortButton = new Button("Abort Shutdown");
        abortButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.AbortShutdown();
            }
        });

        abortBox.getChildren().add(abortButton);
        abortBox.setAlignment(Pos.BOTTOM_CENTER);
        abortBox.setPadding(new Insets(-50,0,50,0));
        setBottom(abortBox);
    }

    public void SubmitAction(){
        if(timeField.getText().length() > 0){

            controller.SetInputTime(timeField.getText());
            controller.SetCurrTime();
            controller.SetShutdown();

            if(!(controller.GetInputTime().length() > 0))
                timeField.clear();
            timeField.setPromptText("Wrong Format. Use HH:mm.");
        } else
            timeField.setPromptText("Please input a time");
    }
}
