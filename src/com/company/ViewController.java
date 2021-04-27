package com.company;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class Controller implements Initializable {
    public AnchorPane centerPane;
    @FXML
    private BorderPane root;
    @FXML
    private Pane bottomPane;
    @FXML
    private Pane rightPane;
    @FXML
    private Pane leftPane;
    @FXML
    private BorderPane topBar;
    @FXML
    private ImageView leftBTN;
    @FXML
    private ImageView middleBTN;
    @FXML
    private ImageView rightBTN;
    private boolean maximized = false;
    private static Controller controller;

    public static Controller getInstance(){
        if(controller==null)
            controller = new Controller();
        return controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        topBarButtons();
        moveMainView();
        sidePanes();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PickerStage.fxml"));
        try {
            root.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void split(Orientation orientation){
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("PickerStage.fxml"));
        try {
            SplitPane splitPane = new SplitPane();
            splitPane.setOrientation(orientation);
            splitPane.getItems().add(root.getCenter());
            splitPane.getItems().add(loader.load());
            root.setCenter(splitPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DoubleProperty getCenterWidthPoperty(){
        return null;
    }


    private void topBarButtons() {
        leftBTN.setOnMousePressed((v) -> Main.getStage().setIconified(true));
        middleBTN.setOnMousePressed((v) -> {
            maximized = !maximized;
            Main.getStage().setMaximized(maximized);
        });
        rightBTN.setOnMousePressed((v) -> Main.getStage().close());
    }

    private void moveMainView() {
        AtomicReference<Double> x = new AtomicReference<>((double) 0);
        AtomicReference<Double> y = new AtomicReference<>((double) 0);
        topBar.setOnMousePressed(mouseEvent -> {
            x.set(mouseEvent.getSceneX());
            y.set(mouseEvent.getSceneY());
        });
        topBar.setOnMouseDragged(mouseEvent -> {
                    Main.getStage().setX(mouseEvent.getScreenX() - x.get());
                    Main.getStage().setY(mouseEvent.getScreenY() - y.get());
                }
        );
    }

    private void sidePanes() {
        AtomicReference<Double> x = new AtomicReference<>((double) 0);
        AtomicReference<Double> y = new AtomicReference<>((double) 0);
        List<Pane> panes = new ArrayList<>(Arrays.asList(bottomPane, leftPane, rightPane));
        root.setOnMouseMoved((MouseEvent mouseEvent)->{
            x.set(mouseEvent.getSceneX());
            y.set(mouseEvent.getSceneY());
        });
        bottomPane.setOnMouseReleased(mouseEvent ->
        {
            if(Main.getStage().getHeight()<mouseEvent.getSceneY() - y.get()){
                Main.getStage().setHeight(Main.getStage().getHeight()-(mouseEvent.getSceneY() - y.get()));
            } else if(Main.getStage().getHeight()>mouseEvent.getSceneY() - y.get()){
            Main.getStage().setHeight(Main.getStage().getHeight()+(mouseEvent.getSceneY() - y.get()));
        }
        });

        rightPane.setOnMouseReleased(mouseEvent -> {
            if(Main.getStage().getWidth()<mouseEvent.getSceneX() - x.get()){
                Main.getStage().setWidth(Main.getStage().getWidth()-(mouseEvent.getSceneX() - x.get()));
            } else if(Main.getStage().getWidth()>mouseEvent.getSceneX() - x.get()){
                Main.getStage().setWidth(Main.getStage().getWidth()+(mouseEvent.getSceneX() - x.get()));
            }
        });

    }

}
