package live.tesnetwork.kdg.stockofmedication.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import live.tesnetwork.kdg.stockofmedication.logic.EditUserMedicationViewHandler;
import live.tesnetwork.kdg.stockofmedication.utils.Filter;

import javax.swing.*;

public class EditUserMedicationView extends StackPane implements ViewHelper {
    private Label medicationObjectLabel;
    private ChoiceBox<String> medicationObjectChoiceBox;
    private Button goBackButton;
    private Button saveButton;
    private Label medicationNameLabel;
    private ChoiceBox<String> medicationNameChoiceBox;
    private TextField medicationStockTextField;
    private Label medicationStockLabel;
    private Label medicationTimeUnitLabel;
    private ChoiceBox<String> medicationTimeUnitChoiceBox;
    private Label medicationAmountPerTimeUnitLabel;
    private TextField medicationAmountPerTimeUnitTextField;
    private Label medicationAmountOfTimeUnitLabel;
    private TextField medicationAmountOfTimeUnitTextField;
    private Label medicationUpdateToNowLabel;
    private CheckBox medicationUpdateToNowCheckBox;

    @Override
    public String getTitle() {
        return "Add Medication";
    }

    @Override
    public void initialize() {
        this.medicationObjectLabel = new Label("Medication type");
        this.medicationObjectChoiceBox = new ChoiceBox<>();
        this.medicationObjectChoiceBox.getItems().addAll("Medication product");
        this.medicationObjectChoiceBox.setValue("User medication");
        this.medicationObjectChoiceBox.setOnAction(EditUserMedicationViewHandler::changeMedicationObject);
        HBox medicationObjectContainer = new HBox();
        medicationObjectContainer.setSpacing(10);
        medicationObjectContainer.getChildren().addAll(medicationObjectLabel, medicationObjectChoiceBox);

        this.medicationNameLabel = new Label("Medication name");
        this.medicationNameChoiceBox = new ChoiceBox<>();
        this.medicationNameChoiceBox.setMinWidth(150);
        this.medicationNameChoiceBox.setValue("Select a medication");

        VBox medicationNameContainer = new VBox();
        medicationNameContainer.getChildren().addAll(this.medicationNameLabel, this.medicationNameChoiceBox);

        this.medicationStockLabel = new Label("Medication stock");
        this.medicationStockTextField = new TextField();
        this.medicationStockTextField.setTextFormatter(Filter.OnlyAllowNumbers());

        VBox medicationStockContainer = new VBox();
        medicationStockContainer.getChildren().addAll(this.medicationStockLabel, this.medicationStockTextField);

        this.medicationTimeUnitLabel = new Label("Medication time unit");
        this.medicationTimeUnitChoiceBox = new ChoiceBox<>();

        this.medicationAmountPerTimeUnitLabel = new Label("Amount to take");
        this.medicationAmountPerTimeUnitTextField = new TextField();
        this.medicationAmountPerTimeUnitTextField.setTextFormatter(Filter.OnlyAllowNumbers());

        VBox medicationTimeUnitContainer = new VBox();
        medicationTimeUnitContainer.getChildren().addAll(this.medicationTimeUnitLabel, this.medicationTimeUnitChoiceBox);

        VBox medicationAmountPerTimeUnitContainer = new VBox();
        medicationAmountPerTimeUnitContainer.getChildren().addAll(this.medicationAmountPerTimeUnitLabel, this.medicationAmountPerTimeUnitTextField);

        this.medicationAmountOfTimeUnitLabel = new Label("Amount of time unit");
        this.medicationAmountOfTimeUnitTextField = new TextField();
        this.medicationAmountOfTimeUnitTextField.setTextFormatter(Filter.OnlyAllowNumbers());

        VBox medicationAmountOfTimeUnitContainer = new VBox();
        medicationAmountOfTimeUnitContainer.getChildren().addAll(this.medicationAmountOfTimeUnitLabel, this.medicationAmountOfTimeUnitTextField);

        this.medicationUpdateToNowLabel = new Label(" Update last taken to now?");
        this.medicationUpdateToNowCheckBox = new CheckBox();
        this.medicationUpdateToNowCheckBox.setSelected(true);

        HBox medicationUpdateToNowContainer = new HBox();
        medicationUpdateToNowContainer.getChildren().addAll(this.medicationUpdateToNowCheckBox, this.medicationUpdateToNowLabel);

        this.saveButton = new Button("Save");
        this.saveButton.setOnAction(v -> EditUserMedicationViewHandler.save(this));
        this.goBackButton = new Button("Go back");
        this.goBackButton.setOnAction(EditUserMedicationViewHandler::goBack);

        HBox buttonContainer = new HBox();
        buttonContainer.setSpacing(10);
        buttonContainer.getChildren().addAll(saveButton, goBackButton);

        VBox container = new VBox();
        container.getChildren().addAll(
                medicationObjectContainer,
                medicationNameContainer,
                medicationStockContainer,
                medicationTimeUnitContainer,
                medicationAmountPerTimeUnitContainer,
                medicationAmountOfTimeUnitContainer,
                medicationUpdateToNowContainer,
                buttonContainer
        );
        container.setSpacing(10);
        container.setPadding(new Insets(10));

        getChildren().add(container);
    }

    public ChoiceBox<String> getMedicationNameChoiceBox() {
        return medicationNameChoiceBox;
    }

    public TextField getMedicationStockTextField() {
        return medicationStockTextField;
    }

    public ChoiceBox<String> getMedicationTimeUnitChoiceBox() {
        return medicationTimeUnitChoiceBox;
    }

    public TextField getMedicationAmountPerTimeUnitTextField() {
        return medicationAmountPerTimeUnitTextField;
    }

    public void setMedicationNameList(String[] medicationNames) {
        this.medicationNameChoiceBox.getItems().clear();
        this.medicationNameChoiceBox.getItems().addAll(medicationNames);
    }

    public void setMedicationTimeUnitList(String[] timeUnits) {
        this.medicationTimeUnitChoiceBox.getItems().clear();
        this.medicationTimeUnitChoiceBox.getItems().addAll(timeUnits);
        this.medicationTimeUnitChoiceBox.getItems().add("None");
        this.medicationTimeUnitChoiceBox.setValue("None");
    }

    public TextField getMedicationAmountOfTimeUnitTextField() {
        return medicationAmountOfTimeUnitTextField;
    }

    public CheckBox getMedicationUpdateToNowCheckBox() {
        return medicationUpdateToNowCheckBox;
    }
}
