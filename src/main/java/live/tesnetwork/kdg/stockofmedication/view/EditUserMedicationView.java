package live.tesnetwork.kdg.stockofmedication.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import live.tesnetwork.kdg.stockofmedication.logic.EditUserMedicationViewHandler;
import live.tesnetwork.kdg.stockofmedication.utils.Filter;

public class EditUserMedicationView extends StackPane implements ViewHelper {
    private Label medicationObjectLabel;
    private Button medicationObjectButton;
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
        this.medicationObjectButton = new Button();
        this.medicationObjectButton.setText("User medication");
        this.medicationObjectButton.setOnAction(EditUserMedicationViewHandler::changeMedicationObject);
        HBox medicationObjectContainer = new HBox();
        medicationObjectContainer.setSpacing(10);
        medicationObjectContainer.getChildren().addAll(medicationObjectLabel, medicationObjectButton);

        this.goBackButton = new Button("Go back");
        this.goBackButton.setOnAction(EditUserMedicationViewHandler::goBack);

        HBox topBar = new HBox();
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topBar.getChildren().addAll(medicationObjectContainer, spacer, this.goBackButton);

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

        this.medicationTimeUnitLabel = new Label("Amount of time between taking this medication:");
        this.medicationTimeUnitChoiceBox = new ChoiceBox<>();
        this.medicationAmountOfTimeUnitTextField = new TextField();
        this.medicationAmountOfTimeUnitTextField.setTextFormatter(Filter.OnlyAllowNumbers());

        this.medicationAmountPerTimeUnitLabel = new Label("Amount to take");
        this.medicationAmountPerTimeUnitTextField = new TextField();
        this.medicationAmountPerTimeUnitTextField.setTextFormatter(Filter.OnlyAllowNumbers());

        VBox medicationTimeUnitContainer = new VBox();
        HBox medicationTimeUnitInput = new HBox();
        medicationTimeUnitInput.getChildren().addAll(this.medicationAmountOfTimeUnitTextField, this.medicationTimeUnitChoiceBox);
        medicationTimeUnitContainer.getChildren().addAll(this.medicationTimeUnitLabel, medicationTimeUnitInput);

        VBox medicationAmountPerTimeUnitContainer = new VBox();
        medicationAmountPerTimeUnitContainer.getChildren().addAll(this.medicationAmountPerTimeUnitLabel, this.medicationAmountPerTimeUnitTextField);

        this.medicationUpdateToNowLabel = new Label(" Update last taken to now?");
        this.medicationUpdateToNowCheckBox = new CheckBox();
        this.medicationUpdateToNowCheckBox.setSelected(true);

        HBox medicationUpdateToNowContainer = new HBox();
        medicationUpdateToNowContainer.getChildren().addAll(this.medicationUpdateToNowCheckBox, this.medicationUpdateToNowLabel);

        this.saveButton = new Button("Save");
        this.saveButton.setOnAction(v -> EditUserMedicationViewHandler.save(this));

        VBox container = new VBox();
        container.getChildren().addAll(
                topBar,
                medicationNameContainer,
                medicationStockContainer,
                medicationTimeUnitContainer,
                medicationAmountPerTimeUnitContainer,
                medicationUpdateToNowContainer,
                saveButton
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
