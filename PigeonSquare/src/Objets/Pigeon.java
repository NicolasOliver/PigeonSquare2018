/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objets;


import java.awt.Point;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author Yves, Nicolas, Hans
 */
public class Pigeon extends Circle implements Runnable {

    private Point position;
    private int vitesse;
    private Nourriture cible;
    private final ImageView img;

    public Pigeon() {
        position = generateRandomPosition();
        img = new ImageView(new Image(getClass().getResourceAsStream("bird.png"), 120, 120, true, true));
        img.setX(position.getX());
        img.setY(position.getY());
        vitesse = 100;
    }

    private Point generateRandomPosition() {
        Random rnd = new Random();
        Point pos = new Point(rnd.nextInt(300), rnd.nextInt(300));
        return pos;
    }

    public void deplacementAleatoire(Point destination) {
        this.position = destination;
        Timeline tl = new Timeline();
        KeyValue kvX=new KeyValue(img.xProperty(),destination.getX());
        KeyValue kvY=new KeyValue(img.yProperty(),destination.getY());
        KeyFrame kf=new KeyFrame(Duration.millis(200),kvX,kvY);
        tl.getKeyFrames().add(kf);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                tl.play();
            }
        });
    }
    
    public synchronized void deplacer(Point destination) {
    if(Math.abs(this.getPosition().x - destination.getX()) > 200
                || Math.abs(this.getPosition().y - destination.getY()) > 200){
            getCible().setFresh(Boolean.FALSE);
        }
        this.position = destination;
        Timeline tl = new Timeline();
        KeyValue kvX=new KeyValue(img.xProperty(),destination.getX()-35);
        KeyValue kvY=new KeyValue(img.yProperty(),destination.getY()-35);
        KeyFrame kf=new KeyFrame(Duration.millis(300),kvX,kvY);
        tl.getKeyFrames().add(kf);
        tl.setOnFinished(e->mangerNourriture());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                tl.play();
            }
        });
        
    }

    @Override
    public void run() {
        if(cible!=null)
        {
            deplacer(cible.getPosition());
        }
        
    }

    public void mangerNourriture() {
        Nourriture c=getCible();
        this.cible=null;
        if (c.getFresh()) {
            GameManager.instance.nourritureMangee(c,this);
        } else {
            c.affAvarie();
            GameManager.instance.nourritureAvariee(c,this);
        }
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
