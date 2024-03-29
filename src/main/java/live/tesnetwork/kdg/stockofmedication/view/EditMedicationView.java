package live.tesnetwork.kdg.stockofmedication.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import live.tesnetwork.kdg.stockofmedication.logic.EditMedicationViewHandler;
import live.tesnetwork.kdg.stockofmedication.logic.EditUserMedicationViewHandler;
import live.tesnetwork.kdg.stockofmedication.utils.Filter;

public class EditMedicationView extends StackPane implements ViewHelper {
    private Label medicationObjectLabel;
    private Button medicationObjectButton;
    private Button goBackButton;
    private Button saveButton;
    private Label medicationNameLabel;
    private TextField medicationNameTextField;
    private Label medicationCategoryLabel;
    private ChoiceBox<String> medicationCategoryChoiceBox;
    private Label medicationMgLabel;
    private TextField medicationMgTextField;
    private TextField medicationRecommendedDoseTextField;
    private Label medicationRecommendedDoseLabel;

    @Override
    public String getTitle() {
        return "Add Medication";
    }

    @Override
    public void initialize() {
        this.medicationObjectLabel = new Label("Medication type");
        this.medicationObjectButton = new Button();
        this.medicationObjectButton.setText("Medication product");
        this.medicationObjectButton.setOnAction(EditMedicationViewHandler::changeMedicationObject);
        HBox medicationObjectContainer = new HBox();
        medicationObjectContainer.setSpacing(10);
        medicationObjectContainer.getChildren().addAll(medicationObjectLabel, medicationObjectButton);

        this.goBackButton = new Button("Go back");
        this.goBackButton.setOnAction(EditUserMedicationViewHandler::goBack);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox topBar = new HBox();
        topBar.getChildren().addAll(medicationObjectContainer, spacer, this.goBackButton);

        this.medicationCategoryLabel = new Label("Medication category");
        this.medicationCategoryChoiceBox = new ChoiceBox<>();

        this.medicationNameLabel = new Label("Medication name:");
        this.medicationNameTextField = new TextField();

        VBox medicationNameContainer = new VBox();
        medicationNameContainer.getChildren().addAll(this.medicationNameLabel, this.medicationNameTextField);

        this.medicationMgLabel = new Label("Medication mg:");
        this.medicationMgTextField = new TextField();
        this.medicationMgTextField.setTextFormatter(Filter.OnlyAllowNumbers());
        VBox medicationMgContainer = new VBox();
        medicationMgContainer.getChildren().addAll(this.medicationMgLabel, this.medicationMgTextField);
        
        this.medicationRecommendedDoseLabel = new Label("Recommended dose:");
        this.medicationRecommendedDoseTextField = new TextField();
        VBox medicationRecommendedDoseContainer = new VBox();
        medicationRecommendedDoseContainer.getChildren().addAll(this.medicationRecommendedDoseLabel, this.medicationRecommendedDoseTextField);

        VBox medicationCategoryContainer = new VBox();
        medicationCategoryContainer.getChildren().addAll(this.medicationCategoryLabel, this.medicationCategoryChoiceBox);

        this.saveButton = new Button("Save");
        this.saveButton.setOnAction(v -> EditMedicationViewHandler.save(this));

        VBox container = new VBox();
        container.getChildren().addAll(
                topBar,
                medicationCategoryContainer,
                medicationNameContainer,
                medicationMgContainer,
                medicationRecommendedDoseContainer,
                saveButton
        );
        container.setSpacing(10);
        container.setPadding(new Insets(10));

        getChildren().add(container);
    }

    public ChoiceBox<String> getMedicationCategoryChoiceBox() {
        return medicationCategoryChoiceBox;
    }

    public TextField getMedicationNameTextField() {
        return medicationNameTextField;
    }

    public void setMedicationCategoryList(String[] medicationCategories) {
        this.medicationCategoryChoiceBox.getItems().clear();
        this.medicationCategoryChoiceBox.getItems().addAll(medicationCategories);
        this.medicationCategoryChoiceBox.setValue("Select a category");
    }

    public TextField getMedicationMgTextField() {
        return medicationMgTextField;
    }

    public TextField getMedicationRecommendedDoseTextField() {
        return medicationRecommendedDoseTextField;
    }
}
