package demo;

import java.io.File;

import playfield.SingletonSpriteManager;

import sidescrolling.Sidescroller; 

import com.golden.gamedev.Game;
import com.golden.gamedev.object.background.ImageBackground;
import editor.Level;
import editor.file.LevelLoader;
import fighter.Fighter;

public abstract  class PlatformGame extends Game {

    protected Level 					myLevel;
    protected LevelLoader				myLevelLoader;
    protected File 						myLevelFile;
    protected Fighter 					myFighter;
    protected ImageBackground 			myBackground;
    protected SingletonSpriteManager 	myPlayfield;
    protected SingletonKeyController	myKeyController;
    protected Sidescroller 				mySidescroller;
    protected boolean					pause;
    
    public PlatformGame()
    {  
        myPlayfield = SingletonSpriteManager.getInstance();
        myKeyController = SingletonKeyController.getInstance();
        myKeyController.convertResourceBundleToMap();
    }
    
    public void loadLevel(String filename)
    {
        myLevelFile = new File(filename);
        myLevelLoader = new LevelLoader();
        myLevel = myLevelLoader.readLevel(myLevelFile);
        	
        myFighter = myLevel.getFighter();
        if (myFighter != null)
        {
            myFighter.setUserInput(bsInput);            
            myPlayfield.add(myFighter);
        }
        myPlayfield.setMySprites(myLevel.getSprites());
        myBackground = myLevel.getBackground();
        
        
        mySidescroller = myLevel.getSidescroller();
        mySidescroller.setUserInput(bsInput);
    }
    
    public void reloadLevel(String filename)
    {
    	myFighter = myLevel.getFighter();

        myLevel = myLevelLoader.readLevel(myLevelFile);
        if (myFighter != null)
        {
            myFighter.setX(myLevel.getFighter().getX());
            myFighter.setY(myLevel.getFighter().getY());
        }
        myPlayfield.setMySprites(myLevel.getSprites());
        myBackground = myLevel.getBackground();
        
        
        mySidescroller = myLevel.getSidescroller();
      
        mySidescroller.setUserInput(bsInput);
    }
    
    
    public void update(long elapsedTime) {
    	if (bsInput.isKeyPressed(myKeyController.getKeyCode("PAUSE")))
        {
           if (pause) pause = false;
           else pause = true;
        }
    	
    	if (!pause) myPlayfield.update(elapsedTime);
    }
    
    public Fighter getFighter() {
    	return myFighter;
    }

    public void setSidescroller(Sidescroller scroller) {
        scroller.setUserInput(bsInput);
        mySidescroller = scroller;
    }
    
}
