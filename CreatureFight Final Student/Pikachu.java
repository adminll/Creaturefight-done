import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;

/**
 * Write a description of class Pikachu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pikachu extends Creature
{
    /**
     * Constructor for objects of class Pikachu
     * 
     * @param w is a reference to the world that Pikachu gets added to
     * @return An object of type Pikachu
     */
    public Pikachu(World w)
    {
        super(650, 2, "Electric");
        getImage().scale(150, 100);
        w.addObject( getHealthBar() , 100, 25 );
    }
    
    /**
     * Act - do whatever the Pikachu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        CreatureWorld playerWorld = (CreatureWorld)getWorld();

        if( getHealthBar().getCurrent() <= 0 )
        {
            getWorld().showText("Pikachu has fainted...", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
            Greenfoot.delay(30);
            if(playerWorld.getNewTwoCreature(1).getHealthBar().getCurrent() >0)
            {
                switchCreature(1);
                playerWorld.setTurnNumber(2);
                getWorld().showText(" ", getWorld().getWidth()/2, getWorld().getHeight()/2+26);
                getWorld().removeObject(this);
                
                
            }
            if(playerWorld.getNewTwoCreature(2).getHealthBar().getCurrent() > 0)
            {
                switchCreature(2);
                playerWorld.setTurnNumber(2);
                getWorld().showText(" ", getWorld().getWidth()/2,getWorld().getHeight()/2+26);
                getWorld().removeObject(this);
            }
                
            
        }
    } 

    /**
     * attack takes away health from the enemy Creature using one of
     * two predefined attacks
     * 
     * @param idx is the index of the attack option selected
     * @return Nothing is returned
     */
    public void attack(int idx)
    {
        CreatureWorld playerWorld = (CreatureWorld)getWorld();
        Creature enemy = playerWorld.getPlayerOne();
        String enemyType = enemy.getType();
        
       
        attackAnimation();

        if( idx == 0 )
        {
            enemy.getHealthBar().add( -30 );
        }
        else
        {
            
            if(enemyType.equalsIgnoreCase  ("Rock"))
            {
                enemy.getHealthBar().add(0);
                getWorld().showText("thou arts attack twas not very effetive",getWorld().getWidth()/2,getWorld().getHeight()/2+26);
                Greenfoot.delay(30);
            }
            if(enemyType.equalsIgnoreCase ("Grass"))
            {
                enemy.getHealthBar().add( -65 );
                getWorld().showText("thou arts attack twas not very effetive",getWorld().getWidth()/2,getWorld().getHeight()/2+26);
                Greenfoot.delay(30);
            }
            else
            {
                enemy.getHealthBar().add( -65 );
            }
            
            
            enemy.getHealthBar().add( -65 );
        }

        playerWorld.setTurnNumber(1);
    }

    /**
     * 
     */
    private void attackAnimation()
    {
        int originalX = getX();
        int originalY = getY();
        for(int i = 0; i < 15; i++)
        {
            setLocation(getX()-1,getY()+2);
            Greenfoot.delay(1);
        }
        setLocation(originalX,originalY);
    }
    

    /**
     * 
     *          
     */
    public void switchCreature(int idx)
    {
        CreatureWorld playerWorld = (CreatureWorld)getWorld();
        Creature switchCreature = playerWorld.getNewTwoCreature(idx);
        if( switchCreature.getHealthBar().getCurrent() <= 0)
        {
            JOptionPane.showMessageDialog(null, "this pokeman has done the fainting and requires the rest");
        }
        else
        {
            while(getX() < getWorld().getWidth()-1)
            {
                setLocation(getX() + 5, getY() );
                Greenfoot.delay(2);
            }
            getImage().setTransparency(0);
            getHealthBar().getImage().setTransparency(0);
            if(idx == 1)
            {
                playerWorld.changePlayerTwo("Lapras");
                
            }
            else
            {
                playerWorld.changePlayerTwo("Pidgeot");
                
            }
            switchCreature.switchedIn();
            (playerWorld).setTurnNumber(1);
        }
    }

    /**
     *
     */
    public void switchedIn()
    {
        getImage().setTransparency(255);
        getHealthBar().getImage().setTransparency(255);
        while(getX() > getWorld().getWidth() - getImage().getWidth()/2)
        {
            setLocation(getX()-5,getY());
            Greenfoot.delay(2);
        }
    }
}
