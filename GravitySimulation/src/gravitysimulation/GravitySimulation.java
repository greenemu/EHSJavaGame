/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitysimulation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.MotionBlur;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author CAM
 */
public class GravitySimulation extends Application {
   
    // Player's Variables
    Circle player = new Circle();
    int playerMass = 1000;
    double playerVelocX = 4.1;
    double playerVelocY = 0.0;
    double playerAccelleration = 0.0;
    double force = 0.0;
    MotionBlur blurPlayer = new MotionBlur();    
    int blurAngle = 0;
    
    // Asteriod's Variables
    Circle asteroid = new Circle();
    int asteroidMass = 1000;
    
    // Everybody's variables
    double r = Math.sqrt((asteroid.getCenterX() - player.getCenterX()) * (asteroid.getCenterX() - player.getCenterX()) + (asteroid.getCenterY() - player.getCenterY()) * (asteroid.getCenterY() - player.getCenterY()));
    
    
    public void reCallTimeline() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,  // set start position at 0
                new KeyValue(player.translateXProperty(), player.getCenterX()),
                new KeyValue(player.translateYProperty(), player.getCenterY())),
                new KeyFrame(new Duration(100), // set end position at 0.5s
                new KeyValue(player.translateXProperty(), player.getCenterX() + playerVelocX),
                new KeyValue(player.translateYProperty(), player.getCenterY() + playerVelocY)));
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // calculate accel based on gravity
                double asteroidX = asteroid.getCenterX();
                double asteroidY = asteroid.getCenterY();
                double playerX = player.getCenterX();
                double playerY = player.getCenterY();
                r = Math.sqrt((asteroidX - playerX) * (asteroidX - playerX) + (asteroidY - playerY) * (asteroidY - playerY));
                // Newton's formula (without G)
                force = (playerMass * asteroidMass) / (r*r);
                // force = mass*acceleration
                // acceleration = force/mass
                playerAccelleration = force/playerMass;

                player.setCenterX(playerX + playerVelocX);
                player.setCenterY(playerY + playerVelocY);
                // calculate direction to accelerate
                
                if (playerX > asteroidX) {
                    playerVelocX -=  playerAccelleration;
                }
                else if (playerX == asteroidX) {

                }
                else {
                    playerVelocX +=  playerAccelleration;
                }
                if (playerY > asteroidY) {
                    playerVelocY -=  playerAccelleration;
                }
                else if (playerY == asteroidY) {

                }
                else {
                    playerVelocY += playerAccelleration;
                }

                
                //blurPlayer.setAngle(Math.toDegrees(Math.atan(playerVelocY/playerVelocX)));
                //player.setEffect(blurPlayer);
                // call another animation
                reCallTimeline();
            }
        });
        
        timeline.play();
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        InnerShadow shadow = new InnerShadow();
        shadow.setRadius(10);
        InnerShadow shadow2 = new InnerShadow();
        shadow2.setRadius(45);
        shadow.setColor(Color.BLACK);
        asteroid.setCenterX(0.0f);
        asteroid.setCenterY(0.0f);
        asteroid.setRadius(50.0f);
        asteroid.setFill(Color.WHITE);
        
        // ColorAdjust coloradjust = new ColorAdjust();
        // coloradjust.setBrightness(0.1);
        // asteroid.setEffect(coloradjust);
        asteroid.setEffect(shadow2);
        
        player.setCenterX(0.0f);
        player.setCenterY(-100.0f);
        player.setRadius(10.0f);
        player.setFill(Color.WHITE);
        
        blurPlayer.setRadius(10);
        
        
        
        player.setEffect(shadow);
        // player.setEffect(blurPlayer);
        
        Rectangle rect = new Rectangle();
        rect.setHeight(1080);
        rect.setWidth(1920);
        rect.setFill(Color.BLACK);
        
        StackPane root = new StackPane();
        root.getChildren().add(rect);
        root.getChildren().add(asteroid);
        root.getChildren().add(player);
        
        Scene scene = new Scene(root, 640, 480);
        
        primaryStage.setTitle("Gravity Simulation");
        primaryStage.setScene(scene);
        
        
        Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,  // set start position at 0
                new KeyValue(player.translateXProperty(), player.getCenterX()),
                new KeyValue(player.translateYProperty(), player.getCenterY())),
                new KeyFrame(new Duration(500), // set end position at 0.5s
                new KeyValue(player.translateXProperty(), player.getCenterX() + playerVelocX),
                new KeyValue(player.translateYProperty(), player.getCenterY() + playerVelocY)));
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            // calculate accel based on gravity
            double asteroidX = asteroid.getCenterX();
            double asteroidY = asteroid.getCenterY();
            double playerX = player.getCenterX();
            double playerY = player.getCenterY();
            r = Math.sqrt((asteroidX - playerX) * (asteroidX - playerX) + (asteroidY - playerY) * (asteroidY - playerY));
            // Newton's formula (without G)
            force = (playerMass * asteroidMass) / (r*r);
            // force = mass*acceleration
            // acceleration = force/mass
            playerAccelleration = force/playerMass;
            
            // calculate direction to accelerate
            double rise = (asteroidY - playerY);
            double run = (asteroidX - playerX);
            if (playerX > asteroidX) {
                playerVelocX -= rise * playerAccelleration;
            }
            else if (playerX == asteroidX) {
                
            }
            else {
                playerVelocX += rise * playerAccelleration;
            }
            if (playerY > asteroidY) {
                playerVelocY -= run * playerAccelleration;
            }
            else if (playerY == asteroidY) {
                
            }
            else {
                playerVelocY += run * playerAccelleration;
            }
            
            player.setCenterX(playerX + playerVelocX);
            player.setCenterY(playerY + playerVelocY);
            // call another animation
            reCallTimeline();
        }
    });
           
            timeline.play();
            primaryStage.show();
       
        
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
