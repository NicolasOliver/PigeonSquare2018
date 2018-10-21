/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Objets.GameManager;
import Objets.Pigeon;
import java.awt.Point;
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
     
   public Window()
   {
       panel=new Group();
       scene=new Scene(panel,SIZE,SIZE);
       this.setScene(scene);
       shapes=new ArrayList<Node>();
       scene.setOnMouseClicked(e->
       {
           System.out.println("click");
           Point position=new Point((int)e.getSceneX(),(int)e.getSceneY());
           GameManager.instance.ajouterNourriture(position);
       
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
