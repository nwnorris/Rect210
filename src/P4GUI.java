import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * A GUI to visualize the sweep line algorithm and centralizing managing rectangles.
 * @author nwnorris
 */
public class P4GUI extends Application{

    private static final int MAX_WIDTH = 900;
    private static final int MAX_HEIGHT = 600;
    private static final int STATUS_HEIGHT = 20;
    private Label status;
    private Menu rectMenu;
    private HBox statusBox;
    private Button sweepStep;
    private Button sweepStep10;
    private Button sweepStepEnable;
    private P4Generate generate;
    private P4Intersect intersect;
    private Pane canvas;
    private ArrayList<Rect210> rects;
    private SweepEvent currentEvent;

    /**
     * JavaFX launch point, initializes the window and shows it.
     * @param s The JavaFX Stage this takes place in.
     */
    public void start(Stage s){
        BorderPane root = new BorderPane();
        root.setPrefSize(MAX_WIDTH, MAX_HEIGHT+STATUS_HEIGHT+50);
        root.setTop(initMenuBar(s));
        root.setCenter(initCanvas());
        root.setBottom(initStatusBar());

        Group rootGroup = new Group(root);
        Scene rootScene = new Scene(rootGroup);

        s.setScene(rootScene);
        s.show();
    }

    /**
     * Creates the status bar.
     * @return The bottom status bar.
     */
    private HBox initStatusBar(){
        status = new Label("No rectangles loaded. Generate new ones or load a save.");
        statusBox = new HBox(status);
        statusBox.setPrefHeight(STATUS_HEIGHT);
        statusBox.setAlignment(Pos.BOTTOM_LEFT);
        statusBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        return statusBox;
    }

    /**
     * Draws all rectangles on the canvas; if sweepLine is running, sweepList rects are highlighted and the sweepLine is drawn.
     */
    private void update(){

        //Clear the canvas and reset the drawing set.
        canvas.getChildren().clear();
        HashSet<Rectangle> drawnRects = new HashSet<>();

        //Draw all base rects
        for(Rect210 r: rects){
            Rectangle drawn = r.getRectangle();
            drawn.setFill(Color.TRANSPARENT);
            drawn.setStroke(Color.BLACK);
            drawn.setStrokeType(StrokeType.INSIDE);
            drawnRects.add(drawn);
        }

        //Draw any sweepList rects
        if(intersect.isSweeping()){
            for(Rect210 r : intersect.getSweeper().getSweepList()){
                Rectangle drawn = r.getRectangle();
                drawn.setFill(Color.AQUAMARINE);
                drawn.setStroke(Color.BLACK);
                drawn.setStrokeType(StrokeType.INSIDE);
                drawnRects.add(drawn);
            }
        }

        //By using a hashSet with our rects, sweepList rects "override" standard rects with their highlight color.
        canvas.getChildren().addAll(drawnRects);

        //Draw the sweep line on top of everything else.
        if(intersect.isSweeping()){
            Line sweepLine = new Line();
            int x = currentEvent.getValue();
            sweepLine.setStartX(x);
            sweepLine.setEndX(x);
            sweepLine.setStartY(0);
            sweepLine.setEndY(MAX_HEIGHT);
            sweepLine.setStroke(Color.BLUE);
            canvas.getChildren().add(sweepLine);
            setStatus("Sweeping: " + intersect.getNumIntersects() + " intersections." );
        }



    }

    /**
     * Creates the central Pane where rectangles will be drawn.
     * @return The center Pane of the GUI.
     */
    private Pane initCanvas(){
        canvas = new Pane();
        canvas.setPrefSize(MAX_WIDTH, MAX_HEIGHT-STATUS_HEIGHT);
        return canvas;
    }

    /**
     * Creates the menu bar and defines button/menu item functionality.
     * @param s The JavaFX Stage this takes place on.
     * @return An HBox containing the menu bar and buttons.
     */
    private HBox initMenuBar(Stage s){
        //Menu bar configuration, handlers are grouped with their controls.
        MenuBar menuBar = new MenuBar();

        //Sweep Buttons
        sweepStepEnable = new Button("Step Through Sweep");
        sweepStepEnable.setOnAction(event -> {
            intersect.initSweepLine();
            sweep();
            sweepStep.setDisable(false);
            sweepStep10.setDisable(false);
            update();
        });

        sweepStep = new Button("Sweep [1]");
        sweepStep.setDisable(true);
        sweepStep.setOnAction(event -> {
            sweep();
        });

        sweepStep10 = new Button("Sweep [10]");
        sweepStep10.setDisable(true);
        sweepStep10.setOnAction(event -> {
            for(int i = 0; i < 10; i++){
                sweep();
            }
        });

        //Menu items
        //About menu
        Menu aboutMenu = new Menu("About");
        MenuItem aboutInfo = new MenuItem();
        aboutInfo.setGraphic(new Text("Program4 - Rect210 - by Nate Norris | github.com/nwnorris"));
        aboutInfo.setOnAction(event -> {
            try{
                Desktop.getDesktop().browse(new URI("http://github.com/nwnorris/Rect210"));
            } catch (Exception e){
                setStatus("Unable to open URL.");
            }
        });
        aboutMenu.getItems().add(aboutInfo);

        //Rect management menus
        Menu imageMenu = new Menu("Rectangle");
        MenuItem loadMenuItem = new MenuItem("Generate new set");
        loadMenuItem.setOnAction(event -> {
            generate = new P4Generate();
            intersect = new P4Intersect(generate.prefix+".txt", generate.getFactory());
            rects = intersect.getRects();
            setStatus(rects.size() + " rects.");
            update();

        });

        MenuItem saveMenuItem = new MenuItem("Load from save");
        saveMenuItem.setOnAction(event -> {
            File save = new FileChooser().showOpenDialog(s);
            intersect = new P4Intersect(save.getPath(), new NateRectFactory(5, 200)); //default values
            rects = intersect.getRects();
            update();
            setStatus(rects.size() + " rects.");
        });
        imageMenu.getItems().addAll(loadMenuItem, saveMenuItem);

        //Intersection menus
        rectMenu = new Menu("Find Intersections");

        MenuItem countArrayMenuItem = new MenuItem("Brute force");
        countArrayMenuItem.setOnAction(event -> {
            intersect.bruteForce();
            setStatus("Brute force : " + intersect.getNumIntersects() + " intersections [" + intersect.getRunTime() + "ms]");

            update();
        });

        //Automated sweep line. Must use step buttons to slowly visualize.
        MenuItem sweepLineMenuItem = new MenuItem("Sweep line");
        sweepLineMenuItem.setOnAction(event -> {
            intersect.initSweepLine();
            SweepEvent result = intersect.sweep();
            long start = System.currentTimeMillis();
            while(result != null){
                result = intersect.sweep();
            }
            long runTime = System.currentTimeMillis() - start;
            setStatus("Sweep line: " + intersect.getNumIntersects() + " intersections [" + runTime + "ms]");
        });

        rectMenu.getItems().addAll(countArrayMenuItem, sweepLineMenuItem);

        //Menus all configured, add them to root MenuBar
        menuBar.getMenus().addAll(aboutMenu, imageMenu, rectMenu);

        //Add the menu bar to a larger container to allow for more elements in it
        //https://stackoverflow.com/questions/35576445/text-on-java-fx-menu-bar
        status = new Label("No rect file loaded.");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS); //Empty space in the middle will push the label to the right wall

        //Finalize and return
        HBox menuBarPane = new HBox(menuBar, spacer, sweepStepEnable, sweepStep, sweepStep10);
        menuBarPane.getStyleClass().add("menu-bar");
        menuBarPane.setAlignment(Pos.CENTER_LEFT);
        return menuBarPane;
    }

    /**
     * Step through the sweep method one item at a time.
     */
    private void sweep(){
        if(intersect.isSweeping()){
            SweepEvent result = intersect.sweep();
            currentEvent = result;
            if(result == null){
                setStatus("Done sweeping: " + intersect.getNumIntersects() + " intersections." );
                sweepStep.setDisable(true);
                sweepStep10.setDisable(true);
            } else {
                //Draw sweepLine rectangles and line.
                update();
            }

        }
    }

    /**
     * Sets the status text to a new value.
     * @param update The new status text.
     */
    public void setStatus(String update){
        status.setText(update);
    }

}
