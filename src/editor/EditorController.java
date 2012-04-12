package editor;





import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import attributes.Attribute;




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
        horizontalOffset+=x;
    }
    
    public void moveVertically(double y)
    {
        myLevel.moveVertically(y);
        verticalOffset+=y;
    }
    public void addSprite(AnimatedGameSprite s)
    {
        myLevel.addSprite(s);
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
        myLevel.setSpriteLocation(s,x,y);
    }
    
    public void replaceSprite(AnimatedGameSprite oldSprite, AnimatedGameSprite newSprite)
    {
        myLevel.replaceSprite(oldSprite, newSprite);
        myView.closeFrame();
    }

    
    
    public List<AnimatedGameSprite> getAllSprites()
    {
        return myLevel.getAllSprites();
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
        }
        catch(IOException ex)
        {
          ex.printStackTrace();
        }
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
          myLevel = (Level)in.readObject();
          //System.out.println(myLevel.getAllSprites().get(0).toString());
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
        horizontalOffset=0;
        verticalOffset = 0;
    }

    private static int[] enemyButtonPlacement; 
    private static int enemyButtonCounter;
    static {
        enemyButtonCounter = 0;
        int [] setUp = { 
                15, 170, 75, 170, 135, 170,
                15, 230, 75, 230, 135, 230,
            };
            enemyButtonPlacement = setUp;
    }
    private static int[] platformButtonPlacement; 
    private static int platformButtonCounter;
    static {
        platformButtonCounter = 0;
        int [] setUp = { 
                15, 400, 75, 400, 135, 400,
                15, 460, 75, 460, 135, 460,
            };
            platformButtonPlacement = setUp;
    }
   

public void addButton( String name, Framework framework, String type)
{
    if (type.contentEquals("enemy"))
    {
        if(enemyButtonCounter==5)
        {
            // make a + button that lets you look at your other ones
        }
        else
        {
            Button newButton = new Button(name, enemyButtonPlacement[enemyButtonCounter], enemyButtonPlacement[enemyButtonCounter+1], 50, 40, framework, myView);
            enemyButtonCounter +=2;
            myView.addButton(newButton);
        }
    }
    else if (type.contentEquals("platform"))
        if(platformButtonCounter==5)
        {
            // make a + button that lets you look at your other ones
        }
        else
        {
            Button newButton = new Button(name, platformButtonPlacement[platformButtonCounter], platformButtonPlacement[platformButtonCounter+1], 50, 40, framework, myView);
            platformButtonCounter +=2;
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


}
