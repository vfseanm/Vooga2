package editor;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.golden.gamedev.Game;
import com.golden.gamedev.gui.*;
import com.golden.gamedev.gui.toolkit.*;

import editor.buttons.ObjectPlacingButton;
import editor.buttons.DialogueOpeningButton;
import editor.buttons.OpenButton;
import editor.buttons.SaveButton;
import editor.dialogues.DialogueBox;
import editor.dialogues.FighterDialogueBox;
import editor.dialogues.GameDialogue;
import editor.file.JsonLevelWriter;
import editor.file.LevelLoader;
import editor.file.LevelWriter;
import editor.file.SerializedLevelWriter;

import sprite.AnimatedGameSprite;

public abstract class EditorView extends Game{
    protected FrameWork framework;
    protected ArrayList<ObjectPlacingButton> allButtons;
    protected static final int MENU_START = 900;
    protected static final double HORIZONTAL_MOVE = 5;
    protected static final double VERTICAL_MOVE = 5;

    protected EditorController myController;
    protected TPanel infoBox;

    protected AnimatedGameSprite leftClickedSprite;
    
    private double[] origPosition;
    private double[] clickedSpriteOffset;
    protected DialogueBox currentDialogueBox;

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
        
        TButton openButton = new OpenButton("Open", 100, 10, 60, 40, this, new LevelLoader());

        SaveButton saveButton = new SaveButton("Save", 180, 10, 60, 40, this, new SerializedLevelWriter());
        
        TButton saveJsonButton = new SaveButton("SaveJSON", 245, 10, 80, 40, this, new JsonLevelWriter());

        bottomBox.add(openButton);
        bottomBox.add(saveButton);
        bottomBox.add(saveJsonButton);

        framework.add(bottomBox);
    }

    @SuppressWarnings("unchecked")
	public void setFrameworkTop()
    {
        framework = new FrameWork(bsInput, 1300, 800);

        TPanel MenuBox = new TPanel(MENU_START, 0, 400, 100);
        TLabel l = new TLabel("Menu", 2, 0, 396, 20);
        l.UIResource().put("Text Horizontal Alignment Integer",
                UIConstants.CENTER);

        MenuBox.add(l);
        
        DialogueOpeningButton playerButton = new DialogueOpeningButton("Configure Player", 25, 30, 150, 40, this, new FighterDialogueBox(myController));
        MenuBox.add(playerButton);
        
        DialogueOpeningButton gameButton = new DialogueOpeningButton("Configure Game", 200, 30, 150, 40, this, new GameDialogue(myController));
        MenuBox.add(gameButton);

        framework.add(MenuBox);
    }
    
    public void renderEditor(Graphics2D pen)
    {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
        if(myController.getBackground()!=null)
        {
            myController.getBackground().render(pen);
        }
        for (AnimatedGameSprite s : myController.getAllSprites())
        {
            s.render(pen);
        }
        framework.render(pen);

    }

    public void updateEditor(long elapsedTime)
    {
        if(myController.getBackground()!=null)
        {
            myController.getBackground().update(elapsedTime);
        }
        framework.update();
        if (click())
        {
            for (ObjectPlacingButton button : allButtons)
            {
                myController.checkAndPlaceSprite(button, getMouseX(), getMouseY());
            }

        }
        if (bsInput.isMouseDown(MouseEvent.BUTTON1) && leftClickedSprite == null)
        {
            for (AnimatedGameSprite s : myController.getAllSprites())
            {
                if (this.checkPosMouse(s, true))

                {
                    leftClickedSprite = s;
                    clickedSpriteOffset = new double[2];
                    clickedSpriteOffset[0] = this.getMouseX() - s.getX();
                    clickedSpriteOffset[1] = this.getMouseY() - s.getY();
                    origPosition = new double[2];
                    origPosition[0] = s.getX();
                    origPosition[1] = s.getY();
                }
            }
        }
        if (leftClickedSprite != null && bsInput.isMouseDown(MouseEvent.BUTTON1))
        {

            myController.setSpriteLocation(leftClickedSprite, this.getMouseX() - clickedSpriteOffset[0], this.getMouseY()- clickedSpriteOffset[1]);
            
        }

        if (leftClickedSprite != null
                && bsInput.isMouseReleased(MouseEvent.BUTTON1))
        {
            myController.setSpriteLocation(leftClickedSprite, this.getMouseX() - clickedSpriteOffset[0],this.getMouseY() - clickedSpriteOffset[1]); 
            if (!myController.checkInterference(leftClickedSprite))
            {
                myController.setSpriteLocation(leftClickedSprite, origPosition[0], origPosition[1]);
            }

            leftClickedSprite = null;
        }
        
        runKeyInput();
        checkAndUpdateDialogue();
    }
    
    private void runKeyInput()
    {
        if ((leftClickedSprite != null
                && bsInput.isKeyDown(java.awt.event.KeyEvent.VK_DELETE)) || leftClickedSprite != null
                        && bsInput.isKeyDown(java.awt.event.KeyEvent.VK_BACK_SPACE))
        {
            myController.removeSprite(leftClickedSprite);
            leftClickedSprite = null;
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
    }
    /**
     * 
     */
    private void checkAndUpdateDialogue()
    {
        if(currentDialogueBox!=null)
        {
            if (bsInput.isMouseDown(MouseEvent.BUTTON1))
            {
                currentDialogueBox.setClick(this.getMouseX(), this.getMouseY());
            }
            if(getRightClickedSprite()!=null)
            {
                currentDialogueBox.setRightClickSprite(getRightClickedSprite());
            }
            if(getLeftClickedSprite()!=null)
            {
                currentDialogueBox.setLeftClickSprite(getRightClickedSprite());
            }
            if(getLeftClickedFramework()!=null)
            {
                currentDialogueBox.setLeftClickFramework(getLeftClickedFramework());
            }
            if(getRightClickedFramework()!=null)
            {
                currentDialogueBox.setLeftClickFramework(getRightClickedFramework());
            }
        }
    }
    
    
    /*
     * Gets left-clicked Framework, null if none. This will return once for every click.
     */

    public Framework getLeftClickedFramework()
    {
        Framework selected = null;
                
        if (bsInput.isMousePressed(MouseEvent.BUTTON1))
        {
        for (ObjectPlacingButton button: allButtons)
        {
            if (button.isMouseOver())
            {
                selected = button.getFramework();
            }
        }
        }
        return selected;
    }
    
    /*
     * Gets right-clicked Framework, null if none. This will return once for every click.
     */
    public Framework getRightClickedFramework()
    {
        Framework selected = null;
                
        if (bsInput.isMousePressed(MouseEvent.BUTTON3))
        {
        for (ObjectPlacingButton button: allButtons)
        {
            if (button.isMouseOver())
            {
                selected = button.getFramework();
            }
        }
        }
        return selected;
    }
    
    /*
     * Gets left-clicked sprite, null if none. This will return once for every click.
     */
    public AnimatedGameSprite getLeftClickedSprite()
    {
        AnimatedGameSprite selected = null;
                
        if (bsInput.isMousePressed(MouseEvent.BUTTON1))
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
    
    /*
     * Gets right-clicked sprite, null if none. This will return once for every click.
     */
    public AnimatedGameSprite getRightClickedSprite()
    {
        AnimatedGameSprite selected = null;
        
        if (bsInput.isMousePressed(MouseEvent.BUTTON3))
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

    public void saveFile(LevelWriter writer)
    {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            myController.writeFile(file.getAbsolutePath(), writer);
        }        
    }

    /*
     * 
     * 
     */
    public void openFile(LevelLoader loader)
    {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            myController.loadFile(file, loader);
        }
    }
    
    

    private JFrame frame;
    
    /*
     * Opens a new JFrame containing the inputed dialogue box extending DialogueBox.
     */
    public void openDialogue(DialogueBox box)
    {
        currentDialogueBox = box;
        frame = new JFrame("");
        Dimension d = new Dimension(600, 300);
        frame.setPreferredSize(d);
        frame.getContentPane().add(box);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void closeFrame()
    {
        currentDialogueBox = null;
        if(frame!=null)
        {
        frame.setVisible(false);
        }
    }


}
