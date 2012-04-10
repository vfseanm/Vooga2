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

import platforms.Platform;

import com.golden.gamedev.Game;
import com.golden.gamedev.gui.*;
import com.golden.gamedev.gui.toolkit.*;
import com.golden.gamedev.object.Sprite;

import enemies.Enemy;
import enemies.attributes.Attribute;

import sprite.AnimatedGameSprite;
import sprite.GameSprite;

public class EditorView extends Game {
    private FrameWork framework;
    private ArrayList<Button> allButtons;
    private static final int MENU_START = 800;
    private static final double HORIZONTAL_MOVE = 5;
    private static final double VERTICAL_MOVE = 5;

    private LevelEditor myEditor;
    private EditorModel myModel;
    private TPanel infoBox;

    private AnimatedGameSprite spriteClicked;
    private double[] origPosition;
    private double[] clickedSpriteOffset;

  //  private double horizontalOffset;
   // private double verticalOffset;

    @SuppressWarnings("unchecked")
    @Override
    public void initResources()
    {
     //   horizontalOffset = 0;
       // verticalOffset = 0;
        myModel = new EditorModel(this);
        allButtons = new ArrayList<Button>();

        framework = new FrameWork(bsInput, 1000, 600);
        framework.getTheme().getUIRenderer("Label")
                .put("Background Border Color", Color.BLACK);

        infoBox = new TPanel(MENU_START, 0, 200, 600);
        infoBox.UIResource().put("Background Color", Color.LIGHT_GRAY);
        TLabel l = new TLabel("Menu", 2, 0, 196, 20);
        
        PlayerButton playerButton = new PlayerButton("Configure Player", 25, 30, 150, 40, this);

        infoBox.add(playerButton);
        
        l.UIResource().put("Background Border Color", Color.LIGHT_GRAY);
        l.UIResource().put("Text Horizontal Alignment Integer",
                UIConstants.CENTER);

        TLabel l2 = new TLabel("Enemies", 2, 70, 196, 40);
        l2.UIResource().put("Text Horizontal Alignment Integer",
                UIConstants.CENTER);
        l2.UIResource().put("Background Border Color", Color.LIGHT_GRAY);

        
        infoBox.add(l);
        infoBox.add(l2);
        
        BlankButton newEnemyButton = new BlankButton("Create Enemy", 25, 110, 150, 40, this, "enemy");

        infoBox.add(newEnemyButton);
        
        

        TLabel l3 = new TLabel("Platforms", 2, 300, 196, 40);
        l3.UIResource().put("Text Horizontal Alignment Integer",
                UIConstants.CENTER);
        l3.UIResource().put("Background Border Color", Color.LIGHT_GRAY);
        
        BlankButton newPlatformButton = new BlankButton("Create Platform", 25, 340, 150, 40, this, "platform");

        infoBox.add(newPlatformButton);

        /*
         * Button platformbutton1 = new Button("Platform1", 10, 240, 60, 40,
         * getImage("resources/platform1.png"), "resources/platform1.png",
         * "platform", null); Button platformbutton2 = new Button("Platform2",
         * 100, 240, 60, 40, getImage("resources/platform2.png"),
         * "resources/platform2.png", "platform", null);
         */

        TButton openButton = new OpenButton("Open", 20, 540, 60, 40, this);

        SaveButton saveButton = new SaveButton("Save", 120, 540, 60, 40, this);

        
        infoBox.add(l3);

        /*
         * infoBox.add(button); infoBox.add(bowserbutton);
         * infoBox.add(platformbutton1); infoBox.add(platformbutton2);
         */
        infoBox.add(openButton);
        infoBox.add(saveButton);

        /*
         * allButtons.add(button); allButtons.add(bowserbutton);
         * allButtons.add(platformbutton1); allButtons.add(platformbutton2);
         */
        framework.add(infoBox);

    }

    @Override
    public void render(Graphics2D pen)
    {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());

        
        for (AnimatedGameSprite s : myModel.getAllSprites())
        {

            s.render(pen);
        }
        
        framework.render(pen);

    }

    @Override
    public void update(long elapsedTime)
    {
        framework.update();
        if (click())
        {
            for (Button button : allButtons)
            {
                if (button.getClicked())
                {
                    System.out.println(button.getType());
                    AnimatedGameSprite s = null;
                    if (button.getType().equals("platform"))
                    {
                        BufferedImage[] im = new BufferedImage[1];
                        im[0] = button.getImage();
                        ArrayList<String> imageNames = new ArrayList<String>();
                        imageNames.add(button.getImageName());
                        Platform e = new Platform(im, getMouseX(),
                                getMouseY() - button.getImage().getHeight(),
                                imageNames);
                        s= e;
                    }
                    else if (button.getType().equals("enemy"))
                    {
                        BufferedImage[] im = new BufferedImage[1];
                        im[0] = button.getImage();
                        ArrayList<String> imageNames = new ArrayList<String>();
                        imageNames.add(button.getImageName());
                        Enemy e = new Enemy(im, getMouseX(),
                                getMouseY() - button.getImage().getHeight(),
                                imageNames);
                        for(Attribute a: button.getAttributes())
                        {
                            e.addAttribute(a);
                        }          
                        s = e;
                        
                    }

                    if (checkInterference(s))
                        myModel.addSprite(s);
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

    }

    public boolean checkInterference(Sprite s)
    {
        boolean t = true;
        for (Sprite sprite : myModel.getAllSprites())
        {
            // System.out.println("checking");
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

    public void addButton(Button newButton)
    {
        infoBox.add(newButton);
        allButtons.add(newButton);
        frame.setVisible(false);
    }

    public void saveFile()
    {
        
        String selectedValue = JOptionPane
                .showInputDialog("Where would you like to save the level?");
        myModel.writeFile(selectedValue);
        
    }

    private JFrame frame;

    public void addEnemy()
    {
        DialogueBox myView = new DialogueBox(myModel, "enemy");
        frame = new JFrame("Enemy Behaviors");
        Dimension d = new Dimension(500, 300);
        frame.setPreferredSize(d);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myView);
        frame.pack();
        frame.setVisible(true);

    }
    public void addPlatform()
    {
        DialogueBox myView = new DialogueBox(myModel, "platform");
        frame = new JFrame("Enemy Behaviors");
        Dimension d = new Dimension(500, 300);
        frame.setPreferredSize(d);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myView);
        frame.pack();
        frame.setVisible(true);

    }

}
