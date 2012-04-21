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

import editor.buttons.ObjectPlacingButton;
import editor.dialogues.DialogueBox;
import editor.frameworks.Framework;
import enemies.Enemy;
import fighter.Fighter;

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
    
    public void checkAndPlaceSprite(ObjectPlacingButton button, int x, int y)
    {
        if (button.getClicked())
          {
              AnimatedGameSprite s = myFramework.getPotentialSprite(x, y);
              if (checkInterference(s))
              {
                 myFramework.createSprite(x, y);
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

//    public void addSprite(AnimatedGameSprite s, Framework f)
//    {
//        
//        myLevel.addSprite(s,f);
//    }

    public void clearLevel()
    {
        myLevel.clear();
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

    public void writeFile(String filename)
    {

        myLevel.moveHorizontally(-horizontalOffset);
        myLevel.moveVertically(-verticalOffset);

        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try
        {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(myLevel);
            out.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
      /*  FileWriter fileOut;
        try
        {
            fileOut = new FileWriter("Becky");
            BufferedWriter out2 = new BufferedWriter(fileOut);
            out2.write(myLevel.toJson());
            out2.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/
        myLevel.moveHorizontally(horizontalOffset);
        myLevel.moveVertically(verticalOffset);
    }

    public void loadFile(File file)
    {
        clearLevel();
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try
        {
            fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);
            myLevel = (Level) in.readObject();
            // System.out.println(myLevel.getAllSprites().get(0).toString());
            in.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        horizontalOffset = 0;
        verticalOffset = 0;
        
        for(Framework f: myLevel.getFrameworks())
        {
            addButton(f.getName(), f);
        }
        
        // put in generating buttons. This should have a list of buttons or something.
    }

    private static int[] enemyButtonPlacement;
    private static int enemyButtonCounter;
    static
    {
        enemyButtonCounter = 0;
        int[] setUp = { 15, 120, 75, 120, 135, 120, 15, 180, 75, 180, 135, 180, };
        enemyButtonPlacement = setUp;
    }
    private static int[] platformButtonPlacement;
    private static int platformButtonCounter;
    static
    {
        platformButtonCounter = 0;
        int[] setUp = { 15, 300, 75, 300, 135, 300, 15, 360, 75, 360, 135, 360, };
        platformButtonPlacement = setUp;
    }
    private static int[] powerUpButtonPlacement;
    private static int powerUpButtonCounter;
    static
    {
        powerUpButtonCounter = 0;
        int[] setUp = { 15, 530, 75, 530, 135, 530, 15, 590, 75, 590, 135, 590, };
        powerUpButtonPlacement = setUp;
    }

    public void addButton(String name, Framework framework)
    {
        if(!myLevel.getFrameworks().contains(framework))
        {
            myLevel.addFramework(framework);
        }
        System.out.println("adding button");
        if (framework.getType().equals("enemy"))
        {
            if (enemyButtonCounter == 5)
            {
                // make a + button that lets you look at your other ones
            } else
            {
                // System.out.println("adding enemybutton");
                ObjectPlacingButton newButton = new ObjectPlacingButton(name,
                        enemyButtonPlacement[enemyButtonCounter],
                        enemyButtonPlacement[enemyButtonCounter + 1], 50, 40,
                        framework, this);
                enemyButtonCounter += 2;
                myView.addButton(newButton);
            }
        } else if (framework.getType().equals("platform"))
            if (platformButtonCounter == 5)
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
            if (platformButtonCounter == 5)
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

        myView.closeFrame();
        
    }

    public void setBackground(BufferedImage image, String imagePath)
    {
        myLevel.setBackground(image, imagePath);
        myView.closeFrame();
        myView.setBackground(image);
    }
    
    public void setFighter(Fighter fighter)
    {
        
        myLevel.setFighter(fighter);
    }
    
    public void loadJsonFile(File f)
    {
        Scanner scanner;
        try
        {
            scanner = new Scanner(f);
            String wholeFile = scanner.useDelimiter("\\A").next();
            System.out.println(wholeFile);
            myLevel.fromJson(wholeFile);
            System.out.println(myLevel.getAllSprites().size());
            for(AnimatedGameSprite s : myLevel.getAllSprites())
            {
                //System.out.println(((Enemy)s).getAttributes());
                System.out.println(s.getX());
            }
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    } 
    
    
    public void writeJsonFile(String f)
    {
        myLevel.moveHorizontally(-horizontalOffset);
        myLevel.moveVertically(-verticalOffset);

        
        
        FileWriter fileOut;
        try
        {
            fileOut = new FileWriter(f);
            BufferedWriter out2 = new BufferedWriter(fileOut);
            out2.write(myLevel.toJson());
            out2.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        myLevel.moveHorizontally(horizontalOffset);
        myLevel.moveVertically(verticalOffset);
    }

}
