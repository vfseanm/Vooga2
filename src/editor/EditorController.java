package editor;

import java.awt.image.BufferedImage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import editor.buttons.ObjectPlacingButton;
import editor.frameworks.Framework;
import fighter.Fighter;

import sprite.AnimatedGameSprite;

public class EditorController {

    private EditorView myView;

    private Level myLevel;

    private double horizontalOffset;

    private double verticalOffset;

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

    public void moveVertically(double y)
    {
        myLevel.moveVertically(y);
        verticalOffset += y;
    }

    public void addSprite(AnimatedGameSprite s, Framework f)
    {
        
        myLevel.addSprite(s,f);
    }

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
        
/*        FileWriter fileOut;
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
                        framework, myView);
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
                        40, framework, myView);
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
                        40, framework, myView);
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

}
