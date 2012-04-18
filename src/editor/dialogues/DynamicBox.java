package editor.dialogues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import editor.EditorController;

public abstract class DynamicBox extends DialogueBox{

    protected int currentX;
    protected int currentY;
    protected ArrayList<int[]> line;

    public DynamicBox(EditorController m) {
        super(m);
        line = new ArrayList<int[]>();
    }
    
    public void setXY(int x, int y)
    {
        currentX = x;
        currentY = y;
        int[] point = new int[2];
        point[0] = x;
        point[1] = y;
        line.add(point);
    }
    public ArrayList<int[]> getPoints()
    {
        return line;
    }

    class DrawPathAction implements ActionListener {       
        
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("line length:" + line.size());
            System.out.println(line);
            myController.closeDialogue();
            setVisible(false);
        }
    }
    
}