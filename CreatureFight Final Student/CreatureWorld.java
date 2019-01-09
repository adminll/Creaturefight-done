/**
 * TODO (1): Write an assignment comment block and update it as you code
 * 
 * 
 */

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
import java.util.List;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CreatureWorld extends World
{
    
    private String playerOneCreature;
    private String playerTwoCreature;
    
    private int turnNumber;
    
    private Menu oneFightMenu;
    private Menu oneSwitchMenu;
    private Menu twoFightMenu;
    private Menu twoSwitchMenu;
    
    private boolean playerOneMenusAdded;
    private boolean playerTwoMenusAdded;
    
    private String playerOneName = "1";
    private String playerTwoName = "2";
    
    
    private Creature[] playerOneCreatures;
    private Creature[] playerTwoCreatures;

    /**
     * Default constructor for objects of class CreatureWorld.
     * 
     * @param There are no parameters
     * @return an object of class CreatureWorld
     */
    public CreatureWorld()
    {    
        
        super(400, 400, 1);
        
        
        playerOneCreature = new String ("Charmander");
        playerTwoCreature = new String ("Pikachu");
        
        
        playerOneCreatures = new Creature[] {new Charmander(this), new Golem(this), new Ivysaur(this) };
        
        
        playerTwoCreatures = new Creature[] {new Pikachu(this), new Lapras(this), new Pidgeot(this) };

        prepareCreatures();

        turnNumber = 0;
        
        playerOneMenusAdded = false;
        playerTwoMenusAdded = false;
        
        Greenfoot.start();
    }
    
    /**
     * act will complete actions that the CreatureWorld object should
     * accomplish while the scenario is running
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void act()
    {
        List allObjects = getObjects(null);
        
         
        boolean playerOneLose = true;
        boolean playerTwoLose = true;
        
        if( turnNumber == 0 )
        {
            playerOneName = JOptionPane.showInputDialog( "Please enter your name, Player One:", null );
            playerTwoName = JOptionPane.showInputDialog( "Please enter your name, Player Two:", null );
            turnNumber = 1;
        }
        else if( turnNumber == 1 )
        {
            showText( "Your turn, " + playerOneName, getWidth()/2, getHeight()/2 );
            showText( "", getWidth()/2, getHeight()/2 + 26 );
        }
        else
        {
            showText( "Your turn, " + playerTwoName, getWidth()/2, getHeight()/2 );
            showText( "", getWidth()/2, getHeight()/2 + 26 );
        }

        if( playerOneMenusAdded == false )
        {
             
            if(playerOneCreature.equalsIgnoreCase ("Charmander"))
            {
                oneFightMenu = new Menu(" Fight ", " Scratch \n Flamethrower ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                oneSwitchMenu = new Menu(" Switch ", " Golem \n Ivysaur ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            else if(playerOneCreature.equalsIgnoreCase ("Golem"))
            {
                oneFightMenu = new Menu(" Fight ", " Scratch \n Flamethrower ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                oneSwitchMenu = new Menu(" Switch ", " Charmander \n Ivysaur ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            else
            {
                addObject(oneFightMenu, 173, getHeight() - 100 );
                addObject(oneSwitchMenu, 241, getHeight() - 100);
            }
                 
                
            oneFightMenu = new Menu(" Fight ", " Scratch \n Flamethrower ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
            oneSwitchMenu = new Menu(" Switch ", " Golem \n Ivysaur ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());

            addObject(oneFightMenu, 173, getHeight() - 100 );
            addObject(oneSwitchMenu, 241, getHeight() - 100);
            
            playerOneMenusAdded = true;
        }

        if( playerTwoMenusAdded == false )
        {
             
            if(playerTwoCreature.equalsIgnoreCase ("Pikachu"))
            {
                twoFightMenu = new Menu(" Fight ", " Tackle \n Thunderbolt ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                twoSwitchMenu = new Menu(" Switch ", " Lapras \n Pidgeot ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            
            else if(playerTwoCreature.equalsIgnoreCase ("Lapras"))
            {
                twoFightMenu = new Menu(" Fight ", " Tackle \n Thunderbolt ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                twoSwitchMenu = new Menu(" Switch ", " Pikachu \n Pidgeot ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            else
            {
                addObject(twoFightMenu, 131, 75);
                addObject(twoSwitchMenu, 199, 75);
            }
                 
                
            
            twoFightMenu = new Menu(" Fight ", " Tackle \n Thunderbolt ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
            twoSwitchMenu = new Menu(" Switch ", " Lapras \n Pidgeot ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            
            addObject(twoFightMenu, 131, 75);
            addObject(twoSwitchMenu, 199, 75);
            
            playerTwoMenusAdded = true;
        }
        
         
        for( int i = 0; playerOneLose == true && i < playerOneCreatures.length; i++)
        {
            if(playerOneCreatures[i].getHealthBar().getCurrent() > 0)
            {
                playerOneLose = false;
            }
        }
        
        for(int i = 0; playerTwoLose == true && i < playerTwoCreatures.length; i++)
        {
            if(playerTwoCreatures[i].getHealthBar().getCurrent() > 0)
            {
                playerTwoLose = false;
            }
        }
            
        if( playerOneLose == true )
        {
            removeObjects(allObjects);
            showText(playerTwoName + " wins!!", getWidth()/2, getHeight()/2);
            Greenfoot.stop();
        }
        
      
        if( playerTwoLose == true )
        {
            removeObjects(allObjects);
            showText(playerOneName + " wins!!", getWidth()/2, getHeight()/2);
            Greenfoot.stop();
        }      
    }

    /**
     * prepareCreatures adds the creatures for both players to the world
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void prepareCreatures()
    {
         
        for( int i = 0; i < playerOneCreatures.length; i++)
        {
            if( i == 0)
            {
                addObject(playerOneCreatures[i], playerOneCreatures[i].getImage().getWidth()/2,getHeight() - playerOneCreatures[i].getImage().getHeight()/2);
            }
            else
            {
                playerOneCreatures[i].getImage().setTransparency(0);
                addObject(playerOneCreatures[i],0 ,getHeight() - playerOneCreatures[i].getImage().getHeight()/2);
            }
        }
            
                
            
        for( int i = 0; i < playerTwoCreatures.length; i++)
        {
            if( i == 0)
            {
                addObject(playerTwoCreatures[i], getWidth() - playerTwoCreatures[i].getImage().getWidth()/2, playerTwoCreatures[i].getImage().getHeight()/2);
            }
            else
            {
                playerTwoCreatures[i].getImage().setTransparency(0);
                addObject(playerTwoCreatures[i],getWidth() , playerTwoCreatures[i].getImage().getHeight()/2);
            }
        }

        
        
    }
    
    /**
     * getPlayerOne returns player one's current creature for
     * use in other parts of the code or for the user's information
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public Creature getPlayerOne()
    {
        
        Creature currentPlayerOne = null;

         
        if(playerOneCreature.equalsIgnoreCase ("Charmander" ))
        {
            currentPlayerOne = playerOneCreatures[0];
        }
        
        else if(playerOneCreature.equalsIgnoreCase("Golem"))
        {
            currentPlayerOne = playerOneCreatures[1];
        }
        else
        {
            currentPlayerOne = playerOneCreatures[2];
        }
        
        return currentPlayerOne;
    }
    
    /**
     * getPlayerTwo returns player two's current creature for
     * use in other parts of the code or for the user's information
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public Creature getPlayerTwo()
    {
        
        Creature currentPlayerTwo = null;

         
        if(playerTwoCreature.equalsIgnoreCase ("Pikachu" ))
        {
            currentPlayerTwo = playerTwoCreatures[0];
        }
        
        else if(playerTwoCreature.equalsIgnoreCase("Lapras"))
        {
            currentPlayerTwo = playerTwoCreatures[1];
        }
        else
        {
            currentPlayerTwo = playerTwoCreatures[2];
        }
        
        return currentPlayerTwo;
    }
    
    /**
     * getTurnNumber returns a number that represents which player's
     * turn it is (either player 1 or player 2)
     * 
     * @param There are no parameters
     * @return An integer that represents which player's turn it is
     */
    public int getTurnNumber()
    {
        return turnNumber;
    }

    /**
     * setTurnNumber changes the turn number to the appropriate
     * number for which player's turn it is
     * 
     * @param turn is the number that represents which player's turn it is
     * @return Nothing is returned
     */
    public void setTurnNumber( int turn )
    {
        turnNumber = turn;
    }

    
    /**
     * 
     */
    public void changePlayerOne(String creature)
    {
        playerOneCreature = creature;
        removeObject(oneFightMenu);
        removeObject(oneSwitchMenu);
        playerOneMenusAdded = false;
    }

    /**
     * 
     */
    public void changePlayerTwo(String creature)
    {
        playerTwoCreature = creature;
        removeObject(twoFightMenu);
        removeObject(twoSwitchMenu);
        playerTwoMenusAdded = false;
    }

    /**
     * 
     */
    public Creature getNewOneCreature(int index)
    {
        return playerOneCreatures[index];
    }

    /**
     * 
     */
    
    public Creature getNewTwoCreature(int index)
    {
        return playerTwoCreatures[index];
    }
}
