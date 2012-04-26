package demo;

import java.awt.event.KeyEvent;
import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;

import playfield.SingletonPlayField;

import sidescrolling.Sidescroller;
import sprite.AnimatedGameSprite;   

import attributes.Attribute;
import attributes.interfaces.Input;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;

import editor.Level;
import editor.file.LevelLoader;
import fighter.Fighter;

public abstract  class PlatformGame extends Game {
    
    
    protected Level myLevel;
    protected Fighter myFighter;
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
        
        LevelLoader loader = new LevelLoader();
        myLevel = loader.readLevel(new File(filename));
        myFighter = myLevel.getFighter();
        if(myFighter != null)
        {

            myFighter.setUserInput(bsInput);            
            myPlayfield.add(myFighter);
        }
        myPlayfield.setMySprites(myLevel.getSprites());
        ImageBackground myBackground = myLevel.getBackground();
        
        
        myPlayfield.setBackground(myBackground);
        
        
        mySidescroller = myLevel.getSidescroller();
        mySidescroller.setUserInput(bsInput);
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

    public void setSidescroller(Sidescroller scroller) {
        mySidescroller = scroller;
    }
    
}
