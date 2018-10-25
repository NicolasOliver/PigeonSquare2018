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

/**
 *
 * @author Yves, Nicolas, Hans
 */
public class GameManager 
{
    
    static public Window window;
    private final List<Pigeon> pigeons;
    private final List<Pigeon> enMouvement;
    private final List<Nourriture> nourritureEnAttente;
    public static GameManager instance=new GameManager();
    
    public GameManager()
    {      
       enMouvement=new ArrayList<>(); 
       pigeons=new ArrayList<>();
       nourritureEnAttente=new ArrayList<>();
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
        pigeons.add(pigeon);
    }
    
    public void affraid()
    {
        pigeons.forEach((p) -> {
            Random rnd = new Random();
            p.deplacementAleatoire(new Point(rnd.nextInt(500), rnd.nextInt(500)));
        });
    }
    
    public void launchGame()
    {
        initialization();
        pigeons.stream().map((p) -> new Thread(p)).forEachOrdered((t) -> {
            t.start();
        });
    }
    
    /**
     * @param window the window to set
     */
    public void setWindow(Window window) {
        GameManager.window = window;
    }


    public void nourritureMangee(Nourriture nourriture, Pigeon pigeon)
    {
     /*   if(!nourriture.getIsEaten()) {
            if(pigeon.getPosition() == nourriture.getPosition()) {*/
                GameManager.window.RemoveShape(nourriture.getImg());
                nourriture.setIsEaten(true);
                
                if(!nourritureEnAttente.isEmpty())
                {
                    Nourriture n=nourritureEnAttente.get(0);
                    if(n==null)
                    {
                        int a =0;
                    }
                    nourritureEnAttente.remove(n);
                    pigeon.setCible(n);
                 
                }
                else
                {
                    enMouvement.remove(pigeon);
                    pigeons.add(pigeon);
                }
           /* }
        }*/
    }
    
    public void nourritureAvariee(Nourriture nourriture, Pigeon pigeon)
    {
        
                nourriture.setIsEaten(false);
                nourriture.setFresh(Boolean.FALSE);
                if(!nourritureEnAttente.isEmpty())
                {
                    Nourriture n=nourritureEnAttente.get(0);
                    if(n!=null)
                    {
                       nourritureEnAttente.remove(n);
                       pigeon.setCible(n); 
    }
                }
                else
                {
                    enMouvement.remove(pigeon);
                    pigeons.add(pigeon);
                }
    }
    
    public synchronized void ajouterNourriture(Point position)
    {
        Nourriture n=new Nourriture(position);
        int size=pigeons.size();
        if(size!=0)
        {
             Pigeon plusProche=pigeons.get(0);
             double distance=position.distance(plusProche.getPosition());
            for(int i=1;i<size;i++)
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
            enMouvement.add(plusProche);
            pigeons.remove(plusProche);
            plusProche.setCible(n);
        }
        else
        {
            nourritureEnAttente.add(n);
        }
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
