package editor;

import java.awt.image.BufferedImage;

import java.io.File;
import java.util.List;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;

import editor.buttons.ObjectPlacingButton;
import editor.file.LevelLoader;
import editor.file.LevelWriter;
import fighter.Fighter;

import sidescrolling.Sidescroller;
import sprite.AnimatedGameSprite;

public class EditorController implements EditorConstants{

    private EditorView myView;

    private Level myLevel;

    private double horizontalOffset;

    private double verticalOffset;

    private Framework myFramework;
    private int enemyButtonCounter;
    private int platformButtonCounter;
    private int powerUpButtonCounter;

    public EditorController(EditorView view)
    {
        myView = view;
        horizontalOffset = 0;
        verticalOffset = 0;
        myLevel = new Level();
        enemyButtonCounter = 0;
        platformButtonCounter = 0;
        powerUpButtonCounter = 0;

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
    @SuppressWarnings("static-access")
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

    public void addFrameworkAndButton(String name, Framework framework)
    {
        if(!myLevel.getFrameworks().contains(framework))
        {
            myLevel.addFramework(framework);
        }
        if (framework.getType().equals("enemy"))
        {
            if (enemyButtonCounter == 23)
            {
            } else
            {
                ObjectPlacingButton newButton = new ObjectPlacingButton(name,
                        ENEMY_BUTTON_PLACEMENT[enemyButtonCounter],
                        ENEMY_BUTTON_PLACEMENT[enemyButtonCounter + 1], BUTTON_WIDTH, BUTTON_HEIGHT,
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
                        PLATFORM_BUTTON_PLACEMENT[platformButtonCounter],
                        PLATFORM_BUTTON_PLACEMENT[platformButtonCounter + 1], BUTTON_WIDTH,
                        BUTTON_HEIGHT, framework, this);
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
                        POWERUP_BUTTON_PLACEMENT[powerUpButtonCounter],
                        POWERUP_BUTTON_PLACEMENT[powerUpButtonCounter + 1], BUTTON_WIDTH,
                        BUTTON_HEIGHT, framework, this);
                powerUpButtonCounter += 2;
                myView.addButton(newButton);
            }
        else if (framework.getType().equals("WildandCrazy"))
        {
            ObjectPlacingButton newButton = new ObjectPlacingButton(name,
                    15, 540, BUTTON_WIDTH,
                    BUTTON_HEIGHT, framework, this);
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
