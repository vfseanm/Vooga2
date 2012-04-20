package editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

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


import editor.buttons.ObjectPlacingButton;
import editor.buttons.DialogueMakingButton;
import editor.buttons.OpenButton;
import editor.buttons.OpenJsonButton;
import editor.buttons.SaveButton;
import editor.buttons.SaveJsonButton;
import editor.dialogues.DialogueBox;
import editor.dialogues.DynamicBox;
import editor.dialogues.EditEnemyButtonDialogueBox;
import editor.dialogues.EditEnemyDialogue;
import editor.dialogues.EnemyDialogueBox;
import editor.dialogues.ExtendedDialogueBox;
import editor.dialogues.FighterDialogueBox;
import editor.dialogues.GameDialogue;
import editor.dialogues.PlatformDialogueBox;
import editor.dialogues.PowerupDialogueBox;
import editor.frameworks.Framework;
import enemies.Enemy;

import sprite.AnimatedGameSprite;

public abstract class EditorView extends Game {
    protected FrameWork framework;
    protected ArrayList<ObjectPlacingButton> allButtons;
    protected static final int MENU_START = 900;
    protected static final double HORIZONTAL_MOVE = 5;
    protected static final double VERTICAL_MOVE = 5;

    protected EditorController myController;
    protected TPanel infoBox;

    protected AnimatedGameSprite spriteClicked;           
    protected double[] origPosition;
    protected double[] clickedSpriteOffset;
    //protected Framework myFramework;
    protected Background myBackground;
    private HashMap<String, Class> boxMap;
    protected DynamicBox currentDialogueBox;

    @SuppressWarnings("unchecked")
    
    public void initialize()
    {
        currentDialogueBox = null;
        myController = new EditorController(this);
        allButtons = new ArrayList<ObjectPlacingButton>();
        setFrameworkTop();
       
        infoBox = new TPanel(MENU_START, 70, 400, 800);
        
        framework.add(infoBox);

    }
    
    public void setFrameworkBottom()
    {
        TPanel bottomBox = new TPanel(MENU_START, 680, 400, 100);
        
        TButton openButton = new OpenButton("Open", 100, 10, 60, 40, this);

        SaveButton saveButton = new SaveButton("Save", 180, 10, 60, 40, this);
        
        TButton openJsonButton = new OpenJsonButton("OpenJSON", 10, 10, 80, 40, this);
        
        TButton saveJsonButton = new SaveJsonButton("SaveJSON", 245, 10, 80, 40, this);

        bottomBox.add(openButton);
        bottomBox.add(saveButton);
        bottomBox.add(openJsonButton);
        bottomBox.add(saveJsonButton);

        framework.add(bottomBox);
    }

    public void setFrameworkTop()
    {
        framework = new FrameWork(bsInput, 1300, 800);

        TPanel MenuBox = new TPanel(MENU_START, 0, 400, 100);
        TLabel l = new TLabel("Menu", 2, 0, 396, 20);
        l.UIResource().put("Text Horizontal Alignment Integer",
                UIConstants.CENTER);

        MenuBox.add(l);
        
        DialogueMakingButton playerButton = new DialogueMakingButton("Configure Player", 25, 30, 150, 40, this, new FighterDialogueBox(myController));
        MenuBox.add(playerButton);
        
        DialogueMakingButton gameButton = new DialogueMakingButton("Configure Game", 200, 30, 150, 40, this, new GameDialogue(myController));
        MenuBox.add(gameButton);

        framework.add(MenuBox);
    }
    
    public void renderEditor(Graphics2D pen)
    {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
        if(myBackground!=null)
        {
            myBackground.render(pen);
        }
        for (AnimatedGameSprite s : myController.getAllSprites())
        {
            s.render(pen);
        }
        framework.render(pen);
        

    }

    public void updateEditor(long elapsedTime)
    {
        if(myBackground!=null)
        {
            myBackground.update(elapsedTime);
        }
        framework.update();
        if (click())
        {
            for (ObjectPlacingButton button : allButtons)
            {
                myController.checkAndPlaceSprite(button, getMouseX(), getMouseY());
//                if (button.getClicked())
//                {
//                    AnimatedGameSprite s = myFramework.getSprite(getMouseX(), getMouseY());
//                    //System.out.println(s.getClass());
//                    if (checkInterference(s))
//                    {
//                        
//                        myController.addSprite(s, myFramework);
//                        
//                    }
//                }
            }

        }
        if (bsInput.isMouseDown(MouseEvent.BUTTON1) && spriteClicked == null)
        {
            for (AnimatedGameSprite s : myController.getAllSprites())
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

            myController.setSpriteLocation(spriteClicked, this.getMouseX() - clickedSpriteOffset[0], this.getMouseY()- clickedSpriteOffset[1]);
            

        }

        if (spriteClicked != null
                && bsInput.isMouseReleased(MouseEvent.BUTTON1))
        {
            myController.setSpriteLocation(spriteClicked, this.getMouseX() - clickedSpriteOffset[0],this.getMouseY() - clickedSpriteOffset[1]); 
            if (!myController.checkInterference(spriteClicked))
            {
                myController.setSpriteLocation(spriteClicked, origPosition[0], origPosition[1]);
            }

            spriteClicked = null;
        }
        if (spriteClicked != null
                && bsInput.isKeyReleased(java.awt.event.KeyEvent.VK_DELETE))
        {
            myController.removeSprite(spriteClicked);
            spriteClicked = null;
        }

        if (bsInput.isKeyDown(java.awt.event.KeyEvent.VK_RIGHT))
        {
            myController.moveHorizonally(-HORIZONTAL_MOVE); 
        }
        if (bsInput.isKeyDown(java.awt.event.KeyEvent.VK_LEFT))
        {
            myController.moveHorizonally(HORIZONTAL_MOVE);
        }
        if (bsInput.isKeyDown(java.awt.event.KeyEvent.VK_DOWN))
        {
            myController.moveVertically(-VERTICAL_MOVE);
        }
        if (bsInput.isKeyDown(java.awt.event.KeyEvent.VK_UP))
        {
            myController.moveVertically(VERTICAL_MOVE);
        }
        if (bsInput.isMousePressed(MouseEvent.BUTTON3))
        {
            for (ObjectPlacingButton button: allButtons)
            {
                if (button.isMouseOver())
                {
                    editEnemy(button);
                }
            }
        }

    }

//    public boolean checkInterference(Sprite s)
//    {
//        boolean t = true;
//        for (Sprite sprite : myController.getAllSprites())
//        {
//            if(sprite!=spriteClicked)
//            {
//            if ((s.getX() + s.getWidth() > sprite.getX())
//                    && (s.getX() < sprite.getX() + sprite.getWidth()))
//            {
//                if ((s.getY() + s.getHeight() > sprite.getY())
//                        && (s.getY() < sprite.getY() + sprite.getHeight()))
//                {
//                    t = false;
//                }
//            }
//            }
//
//        }
//        if (s.getX() + s.getWidth() > MENU_START)
//            t = false;
//        return t;
//    }

 
    public AnimatedGameSprite getClickedSprite()
    {
        AnimatedGameSprite selected = null;
                
        if (bsInput.isMouseDown(MouseEvent.BUTTON1))
        {
            for (AnimatedGameSprite s : myController.getAllSprites())
            {
                if (this.checkPosMouse(s, true))

                {
                    selected = s;
                }
            }
        }
        return selected;
    }
    
    public AnimatedGameSprite getRightClickedSprite()
    {
        AnimatedGameSprite selected = null;
        
        if (bsInput.isMouseDown(MouseEvent.BUTTON3))
        {
            for (AnimatedGameSprite s : myController.getAllSprites())
            {
                if (this.checkPosMouse(s, true))

                {
                    selected = s;
                }
            }
        }
        return selected;
    }
    
    public void addButton(ObjectPlacingButton newButton)
    {
        infoBox.add(newButton);
        allButtons.add(newButton);
    }

    public void saveFile()
    {
        
        String selectedValue = JOptionPane
                .showInputDialog("Where would you like to save the level?");
        myController.writeFile(selectedValue);
        
    }

    public void openFile()
    {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            myController.loadFile(file);
        }
    }
    
    public void openJsonFile()
    {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            myController.loadJsonFile(file);
        }
    }
    public void saveJsonFile()
    {
        
        String selectedValue = JOptionPane
                .showInputDialog("Where would you like to save the level?");
        myController.writeJsonFile(selectedValue);
        
    }

    private JFrame frame;
    
    public void openDialogue(String type) // this needs to be refactored out
    {
        DialogueBox myView = null;
        
        frame = new JFrame("Enemy Behaviors");
        Dimension d = new Dimension(600, 300);
        frame.setPreferredSize(d);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myView);
        frame.pack();
        frame.setVisible(true);
        
        if(myView instanceof DynamicBox)
            currentDialogueBox = (DynamicBox) myView;
    }
    public void editEnemy(ObjectPlacingButton button)
    {
        EditEnemyButtonDialogueBox myView = new EditEnemyButtonDialogueBox(myController, button.getFramework());
        frame = new JFrame("Edit Enemies");
        Dimension d = new Dimension(500, 300);
        frame.setPreferredSize(d);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myView);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void openDialogue(DialogueBox box)
    {
        frame = new JFrame("");
        Dimension d = new Dimension(500, 300);
        frame.setPreferredSize(d);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(box);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void closeFrame()
    {
        if(frame!=null)
        {
        frame.setVisible(false);
        }
    }

    public void setBackground(BufferedImage image)
    {
        myBackground = new ImageBackground(image);
    }

}
