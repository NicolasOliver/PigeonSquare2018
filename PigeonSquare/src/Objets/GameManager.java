/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objets;

import GUI.Window;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Yves
 */
public class GameManager 
{
    
    static public Window window;
    private List<Pigeon> pigeons;
    private List<Nourriture> nourritures;
    public static GameManager instance=new GameManager();
    
    public GameManager()
    {      
       pigeons=new ArrayList<Pigeon>();
       nourritures=new ArrayList<Nourriture>();
    }
    
    
    private void initialization()
    {
        for(int i=0;i<3;i++)
        {
            ajouterPigeon();
        }
    }
    
    private void ajouterPigeon()
    {
        Pigeon pigeon=new Pigeon();
        window.AddShape(pigeon.getImg());
        window.AddShape(pigeon);
        pigeons.add(pigeon);
    }
    
    public void affraid()
    {
        for(Pigeon p: pigeons)
        {
           Random rnd = new Random();
           p.deplacementAleatoire(new Point(rnd.nextInt(500), rnd.nextInt(500)));
        }
    }
    
    public void launchGame()
    {
        initialization();
        for(Pigeon p: pigeons)
        {
           Thread t=new Thread(p);
           t.start();
        }
    }
    
    private void ajouterNourriture()
    {
    
    }

    /**
     * @param window the window to set
     */
    public void setWindow(Window window) {
        this.window = window;
    }

    /**
     * @return the nourritures
     */
    public List<Nourriture> getNourritures() {
        return nourritures;
    }

    /**
     * @param nourritures the nourritures to set
     */
    public void setNourritures(List<Nourriture> nourritures) {
        this.nourritures = nourritures;
    }

    public void nourritureMangee(Nourriture nourriture)
    {
    
    }
    
    public void nourritureAvariee(Nourriture nourriture)
    {
    
    }
    
    public void  ajouterNourriture(Point position)
    {
        Nourriture n=new Nourriture(position);
        Pigeon plusProche=pigeons.get(0);
        double distance=position.distance(plusProche.getPosition());
        for(int i=1;i<pigeons.size();i++)
        {
            Pigeon p=pigeons.get(i);
            if(p.getCible()==null)
            {
                double temp=position.distance(p.getPosition());
                if(temp<distance)
                {
                    distance=temp;
                    plusProche=p;
                }
            }
        }
        plusProche.setCible(n);
    }
    
    
    /**
     * @return the instance
     */
    public static GameManager getInstance() {
        if(instance==null)
            instance=new GameManager();
        return instance;
    }
    
}
