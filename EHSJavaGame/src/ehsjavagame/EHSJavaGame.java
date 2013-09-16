/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ehsjavagame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * EHS Java Game
 * A 2D game created using JavaFX by students of Exeter High School
 * 
 * In-Development Version
 * 
 * @author Cameron Morris
 * @author Luke Ludlington
 */
public class EHSJavaGame extends Application {
    
    // class variables (player, asteroids, etc.)
    // player variables
    private Circle player = new Circle(0, 0, 10);
    int playerMass = 100;
    double playerVelocityX = 0.0;
    double playerVelicityY = 0.0;
    
    // asteroid variables
    Circle asteroid0 = new Circle(0, 60, 50);
    int asteroid0Mass = 1000; 
    
    // the root pane
    Pane root = new Pane();
    
    // the scene
    
    
    public void gameInit() {
            double windowWidth = root.getWidth();
            double windowHeight = root.getHeight();
            
            player.setFill(Color.BLUE);
            player.setCenterX(windowWidth / 2);
            player.setCenterY(windowHeight / 2 - 10);
            
            asteroid0.setFill(Color.BROWN);
            asteroid0.setCenterX(windowWidth / 2);
            asteroid0.setCenterY(windowHeight / 2 + 50);
            
            root.getChildren().add(asteroid0);
            root.getChildren().add(player);
    }
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 640, 480);
        gameInit();
        primaryStage.setTitle("EHS Java Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println(root.getWidth());
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
