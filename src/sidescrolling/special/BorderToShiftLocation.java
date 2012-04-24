package sidescrolling.special;

import com.golden.gamedev.object.Sprite;

import fighter.Fighter;

import sidescrolling.*;
import sidescrolling.border.*;
import sidescrolling.shift.*;

@SuppressWarnings("serial")
public class BorderToShiftLocation extends DecoratedSidescroller {

    private BorderSidescroller borderScroller;
    private ShiftSidescroller shiftScroller;
    
    public BorderToShiftLocation(Sidescroller scroller) {
        super(scroller);
        borderScroller = new BorderRightSidescroller(new BorderLeftSidescroller(scroller));
        shiftScroller = new ShiftRightSidescroller(new ShiftLeftSidescroller(scroller));
    }

    public void move(Sprite sprite) {        
    }
    
    public void update(long elapsedTime) {
        System.out.println(Fighter.getInstance().getX());
        if (Fighter.getInstance().getX() < 800) {
            System.out.println("hee");
            borderScroller.update(elapsedTime);
        }
        else {
            System.out.println("ha");
            shiftScroller.update(elapsedTime);
        }
    }

}
