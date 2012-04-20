package sidescrolling.shift;

import sidescrolling.DecoratedSidescroller;


import sidescrolling.Sidescroller;


public abstract class ShiftSidescroller extends DecoratedSidescroller {
            
    public ShiftSidescroller(Sidescroller scroller) {
        super(scroller);
    }
        
    public abstract boolean fighterOffCorrectSide();
    
    public void update(long elapsedTime) {
        if (fighterOffCorrectSide()) {
            move(getFighter());
            updateGroups();
        }
        super.update(elapsedTime);
    }
    
}
