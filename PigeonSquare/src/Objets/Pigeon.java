/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objets;

import javafx.scene.paint.Color;
import java.awt.Point;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author Yves
 */
public class Pigeon extends Circle implements Runnable {

    private int id;
    private Point position;
    private int vitesse;
    private Nourriture cible;
    private ImageView img;

    public Pigeon() {
        this.setRadius(10);
        position = generateRandomPosition();
        this.setCenterX(0);
        this.setCenterY(0);
        this.setFill(Color.BLACK);
        img = new ImageView(new Image(getClass().getResourceAsStream("pigeon.png")));
        vitesse = 100;
    }

    private Point generateRandomPosition() {
        Random rnd = new Random();
        Point position = new Point(rnd.nextInt(300), rnd.nextInt(300));
        return position;
    }

    public void deplacementAleatoire(Point destination) {
        vitesse = 300;
        double distance = destination.distance(position);
        this.position = destination;
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(this);
        tr.setToX(destination.getX());
        tr.setToY(destination.getY());
        tr.setDuration(Duration.seconds(distance / vitesse));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                tr.play();
            }
        });
        vitesse = 100;
    }
    
    public synchronized void deplacer(Point destination) {
        double distance = destination.distance(position);
        this.position = destination;
        TranslateTransition tr = new TranslateTransition();
        tr.setNode(this);
        tr.setToX(destination.getX());
        tr.setToY(destination.getY());
        tr.setDuration(Duration.seconds(distance / vitesse));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                tr.play();
            }
        });
        mangerNourriture();
        tr.setOnFinished(e->
           mangerNourriture());
    }

    @Override
    public void run() {
        if(cible!=null)
        {
            deplacer(cible.getPosition());
        }
        
    }

    private void mangerNourriture() {
        if (getCible().getEtat()) {
            GameManager.instance.nourritureMangee(getCible());
        } else {
            GameManager.instance.nourritureAvariee(getCible());
        }
        cible=null;
    }

    /**
     * @return the position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * @return the vitesse
     */
    public int getVitesse() {
        return vitesse;
    }

    public ImageView getImg() {
        return img;
    }
    /**
     * @param vitesse the vitesse to set
     */
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    /**
     * @return the cible
     */
    public Nourriture getCible() {
        return cible;
    }

    /**
     * @param cible the cible to set
     */
    public void setCible(Nourriture cible) {
        this.cible = cible;
        run();
    }

}
