package editor;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
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
import sprite.Enemy;
import sprite.GameSprite;


public class EditorView extends Game
{
    private FrameWork framework;
    private ArrayList<Button> allButtons;
    private static final int MENU_START = 800;
    private static final double HORIZONTAL_MOVE = 5;
    private static final double VERTICAL_MOVE =5;

    private LevelEditor myEditor;
    private EditorModel myModel;
    private TPanel infoBox;
    
    private GameSprite spriteClicked;
    private double[] origPosition;
    private double[] clickedSpriteOffset;
    
    private double horizontalOffset;
    private double verticalOffset;
    


    @SuppressWarnings("unchecked")
    @Override
    public void initResources ()
    {
        horizontalOffset = 0;
        verticalOffset = 0;
        myModel = new EditorModel(this);
        myEditor = new LevelEditor();
        allButtons = new ArrayList<Button>();

        framework = new FrameWork(bsInput, 1000, 600);
        framework.getTheme()
                 .getUIRenderer("Label")
                 .put("Background Border Color", Color.BLACK);


        infoBox = new TPanel(MENU_START, 0, 200, 600);
        infoBox.UIResource().put("Background Color", Color.LIGHT_GRAY);
        TLabel l = new TLabel("Menu", 2, 0, 196, 40);
        l.UIResource().put("Background Border Color", Color.LIGHT_GRAY);
        l.UIResource().put("Text Horizontal Alignment Integer",
                           UIConstants.CENTER);

        TLabel l2 = new TLabel("Enemies", 2, 80, 196, 40);
        l2.UIResource().put("Text Horizontal Alignment Integer",
                            UIConstants.CENTER);
        l2.UIResource().put("Background Border Color", Color.LIGHT_GRAY);

        infoBox.add(l);
        infoBox.add(l2);

        Button button =
            new Button("Happy",
                        10,
                        140,
                        60,
                        40,
                        getImage("resources/happy.jpg"),
                        "resources/happy.jpg",
                        "enemy", null);

        Button bowserbutton =
            new Button("Bowser",
                        100,
                        140,
                        60,
                        40,
                        getImage("resources/Bowser.jpg"),
                        "resources/Bowser.jpg",
                        "enemy", null);

        TLabel l3 = new TLabel("Platforms", 2, 200, 196, 40);
        l3.UIResource().put("Text Horizontal Alignment Integer",
                            UIConstants.CENTER);
        l3.UIResource().put("Background Border Color", Color.LIGHT_GRAY);

        Button platformbutton1 =
            new Button("Platform1",
                        10,
                        240,
                        60,
                        40,
                        getImage("resources/platform1.png"),
                        "resources/platform1.png",
                        "platform", null);
        Button platformbutton2 =
            new Button("Platform2",
                        100,
                        240,
                        60,
                        40,
                        getImage("resources/platform2.png"),
                        "resources/platform2.png",
                        "platform", null);

        TLabel l4 = new TLabel("File", 2, 300, 196, 40);
        l4.UIResource().put("Text Horizontal Alignment Integer",
                            UIConstants.CENTER);
        l4.UIResource().put("Background Border Color", Color.LIGHT_GRAY);

        TButton openButton = new OpenButton("Open", 10, 340, 60, 40, this);

        SaveButton saveButton = new SaveButton("Save", 100, 340, 60, 40, this);
        
        BlankButton blankButton = new BlankButton("+", 100, 440, 60, 40, this);

        infoBox.add(blankButton);
        infoBox.add(l3);
        infoBox.add(l4);
        infoBox.add(button);
        infoBox.add(bowserbutton);
        infoBox.add(platformbutton1);
        infoBox.add(platformbutton2);
        infoBox.add(openButton);
        infoBox.add(saveButton);

        allButtons.add(button);
        allButtons.add(bowserbutton);
        allButtons.add(platformbutton1);
        allButtons.add(platformbutton2);
        framework.add(infoBox);

    }



    @Override
    public void render (Graphics2D pen)
    {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getWidth(), getHeight());
        
        for (Sprite s : myModel.getSprites())
        {
            s.render(pen);
        }
        framework.render(pen);

    }


    @Override
    public void update (long elapsedTime)
    {
        framework.update();
        if (click())
        {
            for (Button button : allButtons)
            {
                if (button.getClicked())
                {
                    GameSprite s = null;
                    if (button.getType().equals("platform"))
                    {
                        s =
                            new Platform(button.getImage(),
                                         getMouseX(),
                                         getMouseY() -
                                                 button.getImage().getHeight(),
                                         button.getImageName());
                    }
                    if (button.getType().equals("enemy"))
                    {
                        s =
                            new Enemy(button.getImage(),
                                      getMouseX(),
                                      getMouseY() -
                                              button.getImage().getHeight(),
                                      button.getImageName(), button.getBehaviors());
                    }

                    if (checkInterference(s)) myModel.addSprite(s);
                }
            }

        }
        if(bsInput.isMouseDown(MouseEvent.BUTTON1) && spriteClicked==null)
        {
            for(GameSprite s: myModel.getSprites())
             {
                if(this.checkPosMouse(s, true))
             
                {
                    spriteClicked =s;
                    clickedSpriteOffset = new double[2];
                    clickedSpriteOffset[0] = this.getMouseX()- s.getX();
                    clickedSpriteOffset[1] = this.getMouseY() - s.getY();
                    origPosition = new double[2];
                    origPosition[0] = s.getX();
                    origPosition[1] = s.getY();
                }
             }
            
        }
        if(spriteClicked!=null &&bsInput.isMouseDown(MouseEvent.BUTTON1))
        {

            spriteClicked.setX(this.getMouseX()-clickedSpriteOffset[0]);
            spriteClicked.setY(this.getMouseY()-clickedSpriteOffset[1]);
              
        }
        
        if(spriteClicked!=null && bsInput.isMouseReleased(MouseEvent.BUTTON1))
        {

            
            myModel.getSprites().remove(spriteClicked);         
            
            spriteClicked.setX(this.getMouseX()-clickedSpriteOffset[0]);
            spriteClicked.setY(this.getMouseY()-clickedSpriteOffset[1]);
            if(!checkInterference(spriteClicked))
            {
                spriteClicked.setX(origPosition[0]);
                spriteClicked.setY(origPosition[1]);
            }
            
            myModel.getSprites().add(spriteClicked);
            spriteClicked =null;    
        }
        if(spriteClicked != null &&bsInput.isKeyReleased(java.awt.event.KeyEvent.VK_DELETE))
        {
            myModel.getSprites().remove(spriteClicked);
            spriteClicked = null;
        }
        
        if(bsInput.isKeyDown(java.awt.event.KeyEvent.VK_RIGHT))
        {
            for(Sprite s: myModel.getSprites())
            {
                s.setX(s.getX()-HORIZONTAL_MOVE);
            }
            horizontalOffset += HORIZONTAL_MOVE;
        }
        if(bsInput.isKeyDown(java.awt.event.KeyEvent.VK_LEFT))
        {
            for(Sprite s: myModel.getSprites())
            {
                s.setX(s.getX()+HORIZONTAL_MOVE);
            }
            horizontalOffset -= HORIZONTAL_MOVE;
        }
        if(bsInput.isKeyDown(java.awt.event.KeyEvent.VK_DOWN))
        {
            for(Sprite s: myModel.getSprites())
            {
                s.setY(s.getY()-VERTICAL_MOVE);
            }
            verticalOffset += VERTICAL_MOVE;
        }
        if(bsInput.isKeyDown(java.awt.event.KeyEvent.VK_UP))
        {
            for(Sprite s: myModel.getSprites())
            {
                s.setY(s.getY()+VERTICAL_MOVE);
            }
            verticalOffset -= VERTICAL_MOVE;
        }


    }


    public boolean checkInterference (Sprite s)
    {
        boolean t = true;
        for (Sprite sprite : myModel.getSprites() )
        {
            //System.out.println("checking");
            if ((s.getX() + s.getWidth() > sprite.getX()) &&
                (s.getX() < sprite.getX() + sprite.getWidth()))
            {
                if ((s.getY() + s.getHeight() > sprite.getY()) &&
                    (s.getY() < sprite.getY() + sprite.getHeight()))
                {
                    t = false;
                }
            }
            
        }
        if (s.getX() + s.getWidth() > MENU_START) t = false;
        return t;
    }


    public void openFile ()
    {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            myModel.clearLevel();
            myModel.addAllSprites(myEditor.readFile(file.getAbsolutePath()));
            horizontalOffset = 0;
            verticalOffset = 0;
        }
    }
public void addButton(Button newButton)
{
    infoBox.add(newButton);
    allButtons.add(newButton);
    frame.setVisible(false);
}

    public void saveFile ()
    {
        for(Sprite s: myModel.getSprites())
        {
            s.setX(s.getX()+horizontalOffset);
            s.setY(s.getY()+verticalOffset);
        }       
        String selectedValue =
            JOptionPane.showInputDialog("Where would you like to save the level?");
        myEditor.writeFile(selectedValue, myModel.getSprites());
        for(Sprite s: myModel.getSprites())
        {
            s.setX(s.getX()-horizontalOffset);
            s.setY(s.getY()-verticalOffset);
        }
    }
    private JFrame frame;
    public void addEnemy()
    {
        DialogueBox myView = new DialogueBox(myModel);
       frame = new JFrame("Enemy Behaviors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(myView);
        frame.pack();
        frame.setVisible(true);

    }

}
