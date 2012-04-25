package editor;

import java.awt.Button;
import java.awt.image.BufferedImage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;

import editor.buttons.ObjectPlacingButton;
import editor.dialogues.DialogueBox;
import editor.file.LevelLoader;
import editor.file.LevelWriter;
import enemies.Enemy;
import fighter.Fighter;

import sidescrolling.ConcreteSidescroller;
import sidescrolling.Sidescroller;
import sprite.AnimatedGameSprite;

public class EditorController {

    private EditorView myView;

    private Level myLevel;

    private double horizontalOffset;

    private double verticalOffset;

    private Framework myFramework;

    public EditorController(EditorView view)
    {
        myView = view;
        horizontalOffset = 0;
        verticalOffset = 0;
        myLevel = new Level();

    }

    /*
     * 
     */
    public void moveHorizonally(double x)
    {
        myLevel.moveHorizontally(x);
        horizontalOffset += x;
    }
    
    /*
     * 
     * 
     */
    public void checkAndPlaceSprite(ObjectPlacingButton button, int x, int y)
    {
        if (button.getClicked())
          {
              AnimatedGameSprite s = myFramework.getPotentialSprite(x, y-button.getFramework().getPrototype().getHeight());
              if (checkInterference(s))
              {
                 myFramework.createSprite(x, y-button.getFramework().getPrototype().getHeight());
              }
          }
    }
    public boolean checkInterference(Sprite s)
    {
        boolean t = true;
        for (Sprite sprite : getAllSprites())
        {
            if(sprite!=s)
            {
            if ((s.getX() + s.getWidth() > sprite.getX())
                    && (s.getX() < sprite.getX() + sprite.getWidth()))
            {
                if ((s.getY() + s.getHeight() > sprite.getY())
                        && (s.getY() < sprite.getY() + sprite.getHeight()))
                {
                    t = false;
                }
            }
            }

        }
        if (s.getX() + s.getWidth() > myView.MENU_START)
            t = false;
        return t;
    }
    
    
    
    public void setFramework(Framework f)
    {
        myFramework = f;
    }

    public void moveVertically(double y)
    {
        myLevel.moveVertically(y);
        verticalOffset += y;
    }

    public void setSidescrolling(Sidescroller s)
    {
        myLevel.setSidescrolling(s);
        myView.closeFrame();
    }

    public void clearLevel()
    {
        myLevel.clear();
    }
    
    public ImageBackground getBackground()
    {
        return myLevel.getBackground();
    }
    public void removeSprite(AnimatedGameSprite s)
    {
        myLevel.removeSprite(s);
    }

    public void setSpriteLocation(AnimatedGameSprite s, double x, double y)
    {
        myLevel.setSpriteLocation(s, x, y);
    }

    public void replaceSprite(AnimatedGameSprite oldSprite,
            AnimatedGameSprite newSprite)
    {
        
        myLevel.replaceSprite(oldSprite, newSprite);
        
        myView.closeFrame();
    }

    public List<AnimatedGameSprite> getAllSprites()
    {
        return myLevel.getAllSprites();
    }
    
    public void closeDialogue()
    {
        myView.closeFrame();
    }

    public void writeFile(String filename, LevelWriter writer)
    {

        myLevel.moveHorizontally(-horizontalOffset);
        myLevel.moveVertically(-verticalOffset);

        writer.writeLevel(filename, myLevel);
        
        myLevel.moveHorizontally(horizontalOffset);
        myLevel.moveVertically(verticalOffset);
    }

    public void loadFile(File file, LevelLoader loader)
    {
        myLevel = loader.readLevel(file);
        horizontalOffset = 0;
        verticalOffset = 0;
        
        for(Framework f: myLevel.getFrameworks())
        {
            addFrameworkAndButton(f.getName(), f);
        }
     }

    private static int[] enemyButtonPlacement;
    private static int enemyButtonCounter;
    static
    {
        enemyButtonCounter = 0;
        int[] setUp = { 25, 60, 85, 60, 145, 60, 225, 60, 305, 60, 385, 60, 25, 120, 85, 120, 145, 120, 225, 120, 305, 120, 385, 120 };
        enemyButtonPlacement = setUp;
    }
    private static int[] platformButtonPlacement;
    private static int platformButtonCounter;
    static
    {
        platformButtonCounter = 0;
        int[] setUp = { 25, 220, 85, 220, 145, 220, 225, 220, 305, 220, 385, 220, 25, 280, 85, 280, 145, 280, 225, 280, 305, 280, 385, 280 };
        platformButtonPlacement = setUp;
    }
    private static int[] powerUpButtonPlacement;
    private static int powerUpButtonCounter;
    static
    {
        powerUpButtonCounter = 0;
        int[] setUp = { 25, 380, 85, 380, 145, 380, 225, 380, 305, 380, 385, 380, 25, 440, 85, 440, 145, 440, 225, 440, 305, 440, 385, 440 };
        powerUpButtonPlacement = setUp;
    }

    public void addFrameworkAndButton(String name, Framework framework)
    {
        if(!myLevel.getFrameworks().contains(framework))
        {
            myLevel.addFramework(framework);
        }
        System.out.println("adding button");
        if (framework.getType().equals("enemy"))
        {
            if (enemyButtonCounter == 23)
            {
            } else
            {
                ObjectPlacingButton newButton = new ObjectPlacingButton(name,
                        enemyButtonPlacement[enemyButtonCounter],
                        enemyButtonPlacement[enemyButtonCounter + 1], 50, 40,
                        framework, this);
                enemyButtonCounter += 2;
                myView.addButton(newButton);
            }
        } else if (framework.getType().equals("platform"))
            if (platformButtonCounter == 23)
            {
                // make a + button that lets you look at your other ones
            } else
            {
                ObjectPlacingButton newButton = new ObjectPlacingButton(name,
                        platformButtonPlacement[platformButtonCounter],
                        platformButtonPlacement[platformButtonCounter + 1], 50,
                        40, framework, this);
                platformButtonCounter += 2;
                myView.addButton(newButton);
            }
        else if (framework.getType().equals("Power-Up"))
            if (platformButtonCounter == 23)
            {
                // make a + button that lets you look at your other ones
            } else
            {
                ObjectPlacingButton newButton = new ObjectPlacingButton(name,
                        powerUpButtonPlacement[powerUpButtonCounter],
                        powerUpButtonPlacement[powerUpButtonCounter + 1], 50,
                        40, framework, this);
                powerUpButtonCounter += 2;
                myView.addButton(newButton);
            }
        else if (framework.getType().equals("WildandCrazy"))
        {
            ObjectPlacingButton newButton = new ObjectPlacingButton(name,
                    15, 540, 50,
                    40, framework, this);
            myView.addButton(newButton);
        }
        myView.closeFrame();
        
    }

    public void setBackground(BufferedImage image, String imagePath)
    {
        myLevel.setBackground(image, imagePath);
        myView.closeFrame();
        
    }
    
    public void setFighter(Fighter fighter)
    {
        
        myLevel.setFighter(fighter);
        myView.closeFrame();
    }


    

    


}
