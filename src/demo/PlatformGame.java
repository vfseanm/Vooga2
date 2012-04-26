package demo;

import java.awt.event.KeyEvent;
import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;

import playfield.SingletonPlayField;

import sidescrolling.Sidescroller;
import sprite.AnimatedGameSprite;   

import attributes.Attribute;
import attributes.fighterattributes.Input;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;

import editor.Level;
import editor.file.LevelLoader;
import fighter.Fighter;

public abstract  class PlatformGame extends Game {
    
    
    private Level myLevel;
    private Fighter myFighter;
    //protected ImageBackground myBackground;
    protected SingletonPlayField myPlayfield;
    protected Sidescroller mySidescroller;
    protected boolean	pause;
    
    public PlatformGame()
    {  
        myPlayfield = SingletonPlayField.getInstance(); 
    }
    
    public void loadLevel(String filename)
    {
        myFighter = Fighter.getInstance();
        
        if(myFighter != null)
        {
            myFighter.setUserInput(bsInput);
            
            for (Attribute ability: myFighter.getAttributes()) {
            	Class[] attributeInterfaces = ability.getClass().getInterfaces();
            	if (Arrays.asList(attributeInterfaces).contains(Input.class)) {
            		Input inputAttribute = (Input) ability;
            		inputAttribute.setUserInput(bsInput);
            	}
            }
            	
            myPlayfield.add(myFighter);
        }
        LevelLoader loader = new LevelLoader();
        myLevel = loader.readLevel(new File(filename));

        myPlayfield.setMySprites(  myLevel.getSprites());
        ImageBackground myBackground = myLevel.getBackground();
        
        
        myPlayfield.setBackground(myBackground);
        
        
        mySidescroller = myLevel.getSidescroller();
        
    }
    
    public void update(long elapsedTime) {
    	if (bsInput.isKeyPressed(KeyEvent.VK_P))
        {
           if (pause) pause = false;
           else pause = true;
        }
    	
    	if (bsInput.isKeyPressed(KeyEvent.VK_M))
        {
           // pop up menu
        }
    	
    	if (!pause) myPlayfield.update(elapsedTime);
    }
    
    public Fighter getFighter() {
    	return myFighter;
    }
    

    public void setSidescroller(Sidescroller newScroll) {
        mySidescroller = newScroll;
    }

}
