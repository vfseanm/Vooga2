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
    
    protected ArrayList<AnimatedGameSprite> mySprites;
    private Level myLevel;
    private Fighter myFighter;
    //protected ImageBackground myBackground;
    protected PlayField myPlayfield;
    protected Sidescroller mySidescroller;
    protected boolean	pause;
    
    PlatformGame()
    {
        mySprites = new ArrayList<AnimatedGameSprite>();
        myPlayfield = SingletonPlayField.getInstance(); 
    }
    
    public void loadLevel(String filename)
    {
        myFighter = Fighter.getInstance();
        
        if(myFighter != null)
        {
            myFighter.setUserInput(bsInput);        	
            myPlayfield.add(myFighter);
        }
        LevelLoader loader = new LevelLoader();
        myLevel = loader.readLevel(new File(filename));

        mySprites = (ArrayList<AnimatedGameSprite>) myLevel.getSprites();
        ImageBackground myBackground = myLevel.getBackground();
        
        for(AnimatedGameSprite s: mySprites)
        {
            myPlayfield.add(s);
        }
        myPlayfield.setBackground(myBackground);
        
        
        mySidescroller = myLevel.getSidescroller();
        mySidescroller.setSprites(mySprites);
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
