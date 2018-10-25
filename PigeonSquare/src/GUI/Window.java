/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Objets.GameManager;
import Objets.Pigeon;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import static javafx.util.Duration.INDEFINITE;

/**
 *
 * @author Yves
 */
public class Window extends Stage
{ 
   private static List<Node> shapes; 
   public final static int SIZE=600; 
   private Group panel;
   private Scene scene;
     
   public Window() throws FileNotFoundException
   {
       FileInputStream input = new FileInputStream("src/GUI/grass.png");
       Image image = new Image(input,650,650,true,true);
       ImageView imageView = new ImageView(image);

       panel=new Group(imageView);
       scene=new Scene(panel,SIZE,SIZE);
       this.setScene(scene);
       shapes=new ArrayList<Node>();
       scene.setOnMouseClicked(e->
       {
           System.out.println("click");
           Point position=new Point((int)e.getSceneX(),(int)e.getSceneY());
           GameManager.instance.ajouterNourriture(position);
       
       });
       scene.setOnKeyPressed(e->
       {
           System.out.println("GUI.Window.<init>()");
           GameManager.instance.affraid();
       });
   }
   
   public void AddShape(Node node)
   {
       panel.getChildren().add(node);
   }
   
   public void RemoveShape(Node node)
   {
       panel.getChildren().remove(node);
   }
   
}
