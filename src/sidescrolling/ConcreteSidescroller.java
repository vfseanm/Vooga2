package sidescrolling;

import com.golden.gamedev.object.SpriteGroup;

import fighter.Fighter;

/**
 * This is the most basic type of usable Sidescroller. Does not actually do any sidescrolling but allows
 * for other sidescrollers to access the fighter and the sprites that need to be moved.
 * @author Dustin
 *
 */
public class ConcreteSidescroller implements Sidescroller {

    private Fighter fighter;
    private SpriteGroup[] myGroups;
    
    /**
     * Creates a new ConcreteSidescroller.
     * @param character - the Fighter that the sidescroller uses.
     * @param groups - an array of SpriteGroups containing sprites that the sidescroller must move.
     */
    public ConcreteSidescroller(Fighter character, SpriteGroup...groups) {
        fighter = character;
        myGroups = groups;
    }

    /**
     * Does nothing.
     */
    public void update(long elapsedTime) {
        return;
    }
    
    /**
     * returns the list of SpriteGroups the ConcreteSidescroller holds.
     */
    public SpriteGroup[] getSpriteGroups() {
        return myGroups;
    }
    
    /**
     * returns the Fighter the ConcreteSidescroller holds.
     */
    public Fighter getFighter() {
        return fighter;
    }
    
}
