/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objets;

import java.awt.Image;
import java.awt.Point;
import javafx.scene.image.ImageView;

/**
 *
 * @author Yves
 */
public class Nourriture {
    private Boolean etat;
    private Point position;
    private Boolean isEaten;
    private ImageView img;

    public Nourriture(Point position)
    {
        this.position=position;
        etat=true;
        isEaten=false;
        img = new ImageView(new javafx.scene.image.Image(getClass().getResourceAsStream("freshFood.png")));
        img.relocate(position.getX(), position.getY());
        GameManager.window.AddShape(img);
    }
    
    Point getPosition() {
       return position;
    }

    /**
     * @return the etat
     */
    public Boolean getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
}
