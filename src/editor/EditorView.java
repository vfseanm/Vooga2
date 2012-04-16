package editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.golden.gamedev.Game;
import com.golden.gamedev.gui.*;
import com.golden.gamedev.gui.toolkit.*;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;
import enemies.Enemy;

import sprite.AnimatedGameSprite;

public class EditorView extends Game {
    private FrameWork framework;
    private ArrayList<Button> allButtons;
    private static final int MENU_START = 900;
    private static final double HORIZONTAL_MOVE = 5;
    private static final double VERTICAL_MOVE = 5;

    private EditorController myModel;
    private TPanel infoBox;

    private AnimatedGameSprite spriteClicked;
    private double[] origPosition;
    private double[] clickedSpriteOffset;
    private Framework myFramework;
    private Background myBackground;

  //  private double horizontalOffset;
   // private double verticalOffset;

    @SuppressWarnings("unchecked")
    @Override
    
    
    public void initResources()
    {
     //   horizontalOffset = 0;
       // verticalOffset = 0;
        myModel = new EditorController(this);
        allButtons = new ArrayList<Button>();
        
        framework = new FrameWork(bsInput, 1300, 800);
        framework.getTheme().getUIRenderer("Label")
                .put("Background Border Color", Color.BLACK);

        infoBox = new TPanel(MENU_START, 0, 400, 800);
        infoBox.UIResource().put("Background Color", Color.LIGHT_GRAY);
        TLabel l = new TLabel("Menu", 2, 0, 396, 20);
        
        PlayerButton playerButton = new PlayerButton("Configure Player", 25, 30, 150, 40, this);

        infoBox.add(playerButton);
        
        GameButton gameButton = new GameButton("Configure Game", 200, 30, 150, 40, this);

        infoBox.add(gameButton);
        
        l.UIResource().put("Background Border Color", Color.LIGHT_GRAY);
        l.UIResource().put("Text Horizontal Alignment Integer",
                UIConstants.CENTER);

        TLabel l2 = new TLabel("Enemies", 2, 70, 396, 40);
        l2.UIResource().put("Text Horizontal Alignment Integer",
                UIConstants.CENTER);
        l2.UIResource().put("Background Border Color", Color.LIGHT_GRAY);

        
        infoBox.add(l);
        infoBox.add(l2);
        
        BlankButton newEnemyButton = new BlankButton("Create Enemy", 125, 110, 150, 40, this, "enemy");

        infoBox.add(newEnemyButton);
        
        

        TLabel l3 = new TLabel("Platforms", 2, 270, 396, 40);
        l3.UIResource().put("Text Horizontal Alignment Integer",
                UIConstants.CENTER);
        l3.UIResource().put("Background Border Color", Color.LIGHT_GRAY);
        
        BlankButton newPlatformButton = new BlankButton("Create Platform", 125, 310, 150, 40, this, "platform");

        infoBox.add(newPlatformButton);

        
        TLabel l4 = new TLabel("Powerups", 2, 470, 396, 40);
        l4.UIResource().put("Text Horizontal Alignment Integer",
                UIConstants.CENTER);
        l4.UIResource().put("Background Border Color", Color.LIGHT_GRAY);
        
        infoBox.add(l4);
        
        
        BlankButton newpowerUpButton = new BlankButton("Create Power-Up", 125, 510, 150, 40, this, "power up");

        infoBox.add(newpowerUpButton);
        
  
        TButton openButton = new OpenButton("Open", 70, 660, 60, 40, this);

        SaveButton saveButton = new SaveButton("Save", 120, 660, 60, 40, this);

        
        infoBox.add(l3);

        infoBox.add(openButton);
        infoBox.add(saveButton);

        framework.add(infoBox);

    }

    @Override
    public void render(Graphics2D pen)
    {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
        if(myBackground!=null)
        {
            myBackground.render(pen);
        }
        for (AnimatedGameSprite s : myModel.getAllSprites())
        {
            s.render(pen);
        }
        framework.render(pen);
        

    }

    @Override
    public void update(long elapsedTime)
    {
        if(myBackground!=null)
        {
            myBackground.update(elapsedTime);
        }
        framework.update();
        if (click())
        {
            for (Button button : allButtons)
            {
                if (button.getClicked())
                {
                    AnimatedGameSprite s = myFramework.getSprite(getMouseX(), getMouseY());
                    //System.out.println(s.getClass());
                    if (checkInterference(s))
                    {
                        myModel.addSprite(s);
                        
                    }
                }
            }

        }
        if (bsInput.isMouseDown(MouseEvent.BUTTON1) && spriteClicked == null)
        {
            for (AnimatedGameSprite s : myModel.getAllSprites())
            {
                if (this.checkPosMouse(s, true))

                {
                    spriteClicked = s;
                    clickedSpriteOffset = new double[2];
                    clickedSpriteOffset[0] = this.getMouseX() - s.getX();
                    clickedSpriteOffset[1] = this.getMouseY() - s.getY();
                    origPosition = new double[2];
                    origPosition[0] = s.getX();
                    origPosition[1] = s.getY();
                }
            }

        }
        if (spriteClicked != null && bsInput.isMouseDown(MouseEvent.BUTTON1))
        {

            myModel.setSpriteLocation(spriteClicked, this.getMouseX() - clickedSpriteOffset[0], this.getMouseY()- clickedSpriteOffset[1]);
            
           // spriteClicked.setX(this.getMouseX() - clickedSpriteOffset[0]);
            //spriteClicked.setY(this.getMouseY() - clickedSpriteOffset[1]);

        }

        if (spriteClicked != null
                && bsInput.isMouseReleased(MouseEvent.BUTTON1))
        {

           // myModel.getAllSprites().remove(spriteClicked);

           // spriteClicked.setX(this.getMouseX() - clickedSpriteOffset[0]);
           // spriteClicked.setY(this.getMouseY() - clickedSpriteOffset[1]);
            myModel.setSpriteLocation(spriteClicked, this.getMouseX() - clickedSpriteOffset[0],this.getMouseY() - clickedSpriteOffset[1]); 
            if (!checkInterference(spriteClicked))
            {
                myModel.setSpriteLocation(spriteClicked, origPosition[0], origPosition[1]);
               // spriteClicked.setX(origPosition[0]);
               // spriteClicked.setY(origPosition[1]);
            }

            //myModel.getAllSprites().add(spriteClicked);
            spriteClicked = null;
        }
        if (spriteClicked != null
                && bsInput.isKeyReleased(java.awt.event.KeyEvent.VK_DELETE))
        {
            myModel.removeSprite(spriteClicked);
            spriteClicked = null;
        }

        if (bsInput.isKeyDown(java.awt.event.KeyEvent.VK_RIGHT))
        {
            myModel.moveHorizonally(-HORIZONTAL_MOVE); 
        }
        if (bsInput.isKeyDown(java.awt.event.KeyEvent.VK_LEFT))
        {
            myModel.moveHorizonally(HORIZONTAL_MOVE);
        }
        if (bsInput.isKeyDown(java.awt.event.KeyEvent.VK_DOWN))
        {
            myModel.moveVertically(-VERTICAL_MOVE);
        }
        if (bsInput.isKeyDown(java.awt.event.KeyEvent.VK_UP))
        {
            myModel.moveVertically(VERTICAL_MOVE);
        }
        if (bsInput.isMousePressed(MouseEvent.BUTTON3))
        {
            for (AnimatedGameSprite s : myModel.getAllSprites())
            {
                if (this.checkPosMouse(s, true))
                {
                    if(s.getClass().equals(Enemy.class))
                    {
                        editEnemy( (Enemy) s);
                    }
                }
            }
            for (Button button: allButtons)
            {
                if (button.isMouseOver())
                {
                    editEnemy(button);
                }
            }
        }

    }

    public boolean checkInterference(Sprite s)
    {
        boolean t = true;
        for (Sprite sprite : myModel.getAllSprites())
        {
            if(sprite!=spriteClicked)
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
        if (s.getX() + s.getWidth() > MENU_START)
            t = false;
        return t;
    }

    public void openFile()
    {
        //this.stop();
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            myModel.loadFile(file);

        }

        //this.start();
    }
    
    public void editEnemy(Enemy s)
    {
        EditEnemyDialogue myView = new EditEnemyDialogue(myModel, s, this.getMouseX(), this.getMouseY());
        frame = new JFrame("Enemy Behaviors");
        Dimension d = new Dimension(500, 300);
        frame.setPreferredSize(d);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myView);
        frame.pack();
        frame.setVisible(true);
    }
    public void editEnemy(Button button)
    {
        EditEnemyDialogue myView = new EditEnemyDialogue(myModel, button.getFramework());
        frame = new JFrame("Edit Enemies");
        Dimension d = new Dimension(500, 300);
        frame.setPreferredSize(d);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myView);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void closeFrame()
    {
        frame.setVisible(false);
    }
    
    public void setFramework(Framework f)
    {
        myFramework = f;
    }

    public void addButton(Button newButton)
    {
        infoBox.add(newButton);
        allButtons.add(newButton);
        
    }

    public void saveFile()
    {
        
        String selectedValue = JOptionPane
                .showInputDialog("Where would you like to save the level?");
        myModel.writeFile(selectedValue);
        
    }

    private JFrame frame;

    public void openBox(String type)
    {
        JPanel myView = null;
        if(type.contentEquals("enemy"))
            myView = new EnemyDialogueBox(myModel);
        else if (type.contentEquals("platform"))
            myView = new PlatformDialogueBox(myModel);

        else if (type.contentEquals("player"))
            myView = new FighterDialogueBox(myModel);
        else if (type.contentEquals("power up"))
            myView = new PowerupDialogueBox(myModel);
        else if (type.contentEquals("game"))
            myView = new GameDialogue(myModel);

        
        frame = new JFrame("Enemy Behaviors");
        Dimension d = new Dimension(600, 300);
        frame.setPreferredSize(d);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myView);
        frame.pack();
        frame.setVisible(true);
    }
    
    
    public void configureGame()
    {
        GameDialogue myView = new GameDialogue(myModel);
        frame = new JFrame("Enemy Behaviors");
        Dimension d = new Dimension(500, 300);
        frame.setPreferredSize(d);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myView);
        frame.pack();
        frame.setVisible(true);
    }
    public void setBackground(BufferedImage image)
    {
        myBackground = new ImageBackground(image);
    }

}
