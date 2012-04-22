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
        File file = new File(filename);
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try
        {
          fis = new FileInputStream(file);
          in = new ObjectInputStream(fis);
          myLevel = (Level)in.readObject();
          in.close();
        }
        catch(IOException ex)
        {
          ex.printStackTrace();
        }
        catch(ClassNotFoundException ex)
        {
          ex.printStackTrace();
        }

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
