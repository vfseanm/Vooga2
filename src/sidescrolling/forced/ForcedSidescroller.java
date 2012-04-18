package sidescrolling.forced;

import sidescrolling.DecoratedSidescroller;

import sidescrolling.Sidescroller;

public abstract class ForcedSidescroller extends DecoratedSidescroller {
    
    public ForcedSidescroller(Sidescroller scroller) {
        super(scroller);
    }
        
    public void update(long elapsedTime) {
        updateGroups();
        move(getFighter());
        super.update(elapsedTime);
    }
    
}
