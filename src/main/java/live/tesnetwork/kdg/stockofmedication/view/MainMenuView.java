package live.tesnetwork.kdg.stockofmedication.view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import live.tesnetwork.kdg.stockofmedication.logic.MainMenuViewHandler;
import live.tesnetwork.kdg.stockofmedication.utils.Filter;


public class MainMenuView extends StackPane implements ViewHelper {
    private Label categoryChoiceBoxLabel;
    private ChoiceBox<String> categoryChoiceBox;
    private Label textFieldShowOnlyMedicationBelowLabel;
    private TextField textFieldShowOnlyMedicationBelow;
    private Label textFieldShowOnlyMedicationToTakeInLabel;
    private TextField textFieldShowOnlyMedicationToTakeIn;
    private ChoiceBox<String> choiceBoxShowOnlyMedicationToTakeIn;
    private Button buttonSearchMedication;
    private ListView<String> medicationListView;
    private Button buttonAddMedication;
    private Button buttonLogout;
    private Button buttonToggleMedicationObject;
    private Label medicationListLabel;

    public MainMenuView() {
        initialize();
    }

    @Override
    public void initialize() {
        this.buttonLogout = new Button("Logout");
        this.buttonLogout.setStyle("-fx-background-color: red;");
        this.categoryChoiceBoxLabel = new Label("Category:");
        this.categoryChoiceBox = new ChoiceBox<>();
        this.categoryChoiceBox.setOnAction(v ->  MainMenuViewHandler.search(this));

        HBox categoryChoiceView = new HBox();
        categoryChoiceView.setSpacing(10);
        categoryChoiceView.getChildren().addAll(this.categoryChoiceBoxLabel, this.categoryChoiceBox);

        HBox topBar = new HBox();
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topBar.getChildren().addAll(categoryChoiceView, spacer, this.buttonLogout);

        this.textFieldShowOnlyMedicationBelowLabel = new Label("Show only medication with stock lower than:");
        this.textFieldShowOnlyMedicationBelow = new TextField();
        this.textFieldShowOnlyMedicationBelow.setTextFormatter(Filter.OnlyAllowNumbers());

        VBox stockLowerView = new VBox();
        stockLowerView.getChildren().addAll(this.textFieldShowOnlyMedicationBelowLabel, this.textFieldShowOnlyMedicationBelow);

        this.textFieldShowOnlyMedicationToTakeInLabel = new Label("Show only medication to take in time:");
        this.textFieldShowOnlyMedicationToTakeIn = new TextField();
        this.textFieldShowOnlyMedicationToTakeIn.setTextFormatter(Filter.OnlyAllowNumbers());

        this.choiceBoxShowOnlyMedicationToTakeIn = new ChoiceBox<>();

        VBox middleBar = new VBox();
        HBox takeInTimeView = new HBox();
        takeInTimeView.setSpacing(10);
        this.buttonAddMedication = new Button("Add new medication");
        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        takeInTimeView.getChildren().addAll(this.textFieldShowOnlyMedicationToTakeIn, this.choiceBoxShowOnlyMedicationToTakeIn, spacer1, this.buttonAddMedication);
        middleBar.getChildren().addAll(this.textFieldShowOnlyMedicationToTakeInLabel, takeInTimeView);


        this.medicationListView = new ListView<>();
        ContextMenu contextMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edit");
        MenuItem deleteMenuItem = new MenuItem("Delete");
        contextMenu.getItems().addAll(editMenuItem, deleteMenuItem);

        editMenuItem.setOnAction(v -> MainMenuViewHandler.editMedication(this));
        deleteMenuItem.setOnAction(v -> MainMenuViewHandler.deleteMedication(this));
        this.medicationListView.setOnContextMenuRequested(event -> {
            contextMenu.show(this.medicationListView, event.getScreenX(), event.getScreenY());
        });

        this.buttonSearchMedication = new Button("Search");
        this.buttonAddMedication.setOnAction(MainMenuViewHandler::addMedication);
        this.buttonLogout.setOnAction(MainMenuViewHandler::logout);
        this.buttonToggleMedicationObject = new Button("Switch to Medication");
        this.buttonToggleMedicationObject.setOnAction(v -> MainMenuViewHandler.toggleMedicationObject(this));
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(this.buttonSearchMedication, this.buttonToggleMedicationObject);
        this.buttonSearchMedication.setOnAction(this::updateListView);
        this.medicationListLabel = new Label();
        VBox container = new VBox();
        container.getChildren().addAll(
                topBar,
                stockLowerView,
                middleBar,
                buttonBox,
                this.medicationListLabel,
                this.medicationListView
        );
        container.setSpacing(10);
        container.setPadding(new Insets(10));

        getChildren().add(container);
    }
    public void updateListView() {
        updateListView(null);
    }
    private void updateListView(ActionEvent actionEvent) {
        MainMenuViewHandler.search(this);
    }

    public Button getButtonToggleMedicationObject() {
        return buttonToggleMedicationObject;
    }
    public ChoiceBox<String> getCategoryChoiceBox() {
        return categoryChoiceBox;
    }

    public TextField getTextFieldShowOnlyMedicationBelow() {
        return textFieldShowOnlyMedicationBelow;
    }

    public TextField getTextFieldShowOnlyMedicationToTakeIn() {
        return textFieldShowOnlyMedicationToTakeIn;
    }

    public ChoiceBox<String> getChoiceBoxShowOnlyMedicationToTakeIn() {
        return choiceBoxShowOnlyMedicationToTakeIn;
    }

    public Button getButtonSearchMedication() {
        return buttonSearchMedication;
    }

    public Label getMedicationListLabel() {
        return medicationListLabel;
    }

    public ListView<String> getMedicationListView() {
        return medicationListView;
    }

    @Override
    public String getTitle() {
        return "UserMedication view";
    }

    public void setMedicationList(String[] array) {
        this.medicationListView.getItems().clear();
        this.medicationListView.getItems().addAll(array);
    }

    public void setCategoryChoices(String[] categories) {
        this.categoryChoiceBox.getItems().clear();
        this.categoryChoiceBox.getItems().addAll(categories);
        this.categoryChoiceBox.setValue(categories[0]);
    }

    public void setTimeChoices(String[] timeChoices) {
        this.choiceBoxShowOnlyMedicationToTakeIn.getItems().clear();
        this.choiceBoxShowOnlyMedicationToTakeIn.getItems().addAll(timeChoices);
        this.choiceBoxShowOnlyMedicationToTakeIn.setValue(timeChoices[0]);
    }
}
