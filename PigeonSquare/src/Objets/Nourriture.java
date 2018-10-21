/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objets;

import java.awt.Point;

/**
 *
 * @author Yves
 */
public class Nourriture {
    private Boolean etat;
    private Point position;
    private Boolean isEaten;

    public Nourriture(Point position)
    {
        this.position=position;
        etat=true;
        isEaten=false;
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
