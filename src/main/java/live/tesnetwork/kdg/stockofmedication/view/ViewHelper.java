package live.tesnetwork.kdg.stockofmedication.view;

public interface ViewHelper {

    String getTitle();

    default int getSceneWidth() {
        return 500;
    }

    default int getSceneHeight() {
        return 500;
    }

    void initialize();
}
