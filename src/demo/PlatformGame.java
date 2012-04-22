package demo;

import java.io.File;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import playfield.SingletonPlayField;

import sprite.AnimatedGameSprite;   

import com.golden.gamedev.Game;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;

import editor.Level;
import editor.file.LevelLoader;
import fighter.Fighter;

public abstract  class PlatformGame extends Game {
    
    protected List<AnimatedGameSprite> mySprites;
    private Level myLevel;
    private Fighter myFighter;
    //protected ImageBackground myBackground;
    protected PlayField myPlayfield;
    
    PlatformGame()
    {
        mySprites = new ArrayList<AnimatedGameSprite>();
        myPlayfield = SingletonPlayField.getInstance(); 
    }
    public void loadLevel(String filename)
    {
        LevelLoader loader = new LevelLoader();
        myLevel = loader.readLevel(new File(filename));

        mySprites = myLevel.getSprites();
        ImageBackground myBackground = myLevel.getBackground();
        myFighter = myLevel.getFighter();
        
        if(myFighter!=null)
        {
            myFighter.setUserInput(bsInput);
            myPlayfield.add(myFighter);
        }
        for(AnimatedGameSprite s: mySprites)
        {
            myPlayfield.add(s);
        }
        myPlayfield.setBackground(myBackground);
        
        
    }
    
    public Fighter getFighter() {
    	return myFighter;
    }

}
