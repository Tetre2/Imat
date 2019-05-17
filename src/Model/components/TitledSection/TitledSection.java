package Model.components.TitledSection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TitledSection extends AnchorPane {
    @FXML
    Label titleLabel;
    @FXML
    FlowPane centerContainerFlowPane;

    public TitledSection(String title) {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        titleLabel.setText(title);
        //centerContainerVBox.maxHeightProperty().bind(centerContainerVBox.heightProperty());
    }

    public void addNode(Node node) {
        centerContainerFlowPane.getChildren().add(node);
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Model/components/TitledSection/TitledSection.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        return fxmlLoader;
    }

    private void tryToLoadFXML(FXMLLoader fxmlLoader) {
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
