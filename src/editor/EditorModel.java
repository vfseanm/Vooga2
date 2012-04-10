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



public class EditorModel {


    private EditorView myView;
    
    private Level myLevel;
    
    
    private double horizontalOffset;
    
    private double verticalOffset;
    
    public EditorModel(EditorView view)
    {
        myView = view;
        horizontalOffset = 0;
        verticalOffset = 0;
        myLevel = new Level();

    }
    
    
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

    
    
    public List<AnimatedGameSprite> getAllSprites()
    {
        return myLevel.getAllSprites();
    }
    
    public void writeFile(String filename)
    {
        
        myLevel.moveHorizontally(horizontalOffset);
        myLevel.moveVertically(verticalOffset);
       
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
        myLevel.moveHorizontally(-horizontalOffset);
        myLevel.moveVertically(-verticalOffset);
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


public void addEnemy(BufferedImage image, String filePath, String name, ArrayList<Attribute> b)
{
    Button newButton = new Button(name, 10, 540, 60, 40, image, filePath, "enemy", b);
    myView.addButton(newButton);
}


}
