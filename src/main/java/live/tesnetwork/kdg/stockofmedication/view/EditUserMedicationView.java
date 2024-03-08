package live.tesnetwork.kdg.stockofmedication.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import live.tesnetwork.kdg.stockofmedication.logic.EditUserMedicationViewHandler;

public class EditUserMedicationView extends StackPane implements ViewHelper {
    private Label medicationObjectLabel;
    private ChoiceBox<String> medicationObjectChoiceBox;
    private Button goBackButton;
    private Button saveButton;
    private Label medicationNameLabel;
    private ChoiceBox<String> medicationNameChoiceBox;
    private TextField medicationStockTextField;
    private Label medicationStockLabel;

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

        VBox medicationStockContainer = new VBox();
        medicationStockContainer.getChildren().addAll(this.medicationStockLabel, this.medicationStockTextField);

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

    public void setMedicationNameList(String[] medicationNames) {
        this.medicationNameChoiceBox.getItems().clear();
        this.medicationNameChoiceBox.getItems().addAll(medicationNames);
    }
}
