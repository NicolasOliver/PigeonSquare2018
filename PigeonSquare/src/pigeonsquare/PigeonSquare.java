/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pigeonsquare;

import GUI.Window;
import Objets.GameManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Yves
 */
public class PigeonSquare extends Application{

    private static Stage ps;
    private static GameManager instance;
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
       primaryStage=new Window();
       primaryStage.setResizable(false);
       primaryStage.show();
       instance=GameManager.getInstance();
       instance.setWindow((Window)primaryStage);
       instance.launchGame();
    }
    
    public static void main(String[] args)
    {
        launch(args);   
    }
    
}
