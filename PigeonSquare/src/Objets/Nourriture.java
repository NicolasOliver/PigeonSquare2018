/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objets;

import java.awt.Point;
import javafx.scene.image.ImageView;

/**
 *
 * @author Yves, Nicolas, Hans
 */
public class Nourriture {
    private Boolean fresh;
    private final Point position;
    private Boolean isEaten;
    private final ImageView img;

    public Nourriture(Point position)
    {
        this.position=position;
        fresh=true;
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
    public Boolean getFresh() {
        return fresh;
    }

    /**
     * @param etat the etat to set
     */
    public void setFresh(Boolean etat) {
        this.fresh = etat;
    }
    
    public void setIsEaten(Boolean etat) {
        this.isEaten = etat;
    }
    public Boolean getIsEaten() {
        return isEaten;
    }
    
    public ImageView getImg() {
        return img;
    }
}
