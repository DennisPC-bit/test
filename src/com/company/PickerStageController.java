package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.awt.event.MouseEvent;
import java.util.List;

public class PickerStageController {
    @FXML
    private Button addContentBtn;
    @FXML
    private AnchorPane ap;
    @FXML
    private BorderPane root;
    private SplitPane splitPane;
    private ViewController viewController;

    public void setViewController(ViewController viewController){
        this.viewController=viewController;
    }

    public void splitSceneHorizontally() {
        viewController.split(Orientation.HORIZONTAL, this);
    }

    public void splitSceneVertically() {
        viewController.split(Orientation.VERTICAL,this);
    }

    public void addContent() {
        ImageView imageView = new ImageView("com/company/imgs/KO.jpg");
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(root.getHeight());
        imageView.setFitWidth(root.getWidth());
        if(this.getSplitPane()==null){
            ap.getChildren().clear();
        ap.getChildren().add(imageView);
        }
    }

    public Button getAddContentBtn() {
        return addContentBtn;
    }

    public void makeSplitPane(SplitPane splitPane){
        this.splitPane=splitPane;
        getRoot().setCenter(splitPane);
        getRoot().setLeft(null);
        getRoot().setRight(null);
    }

    public SplitPane getSplitPane() {
        return splitPane;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public BorderPane getRoot() {
        return root;
    }

    public AnchorPane getAp() {
        return ap;
    }
}
