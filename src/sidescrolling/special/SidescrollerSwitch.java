package sidescrolling.special;

import java.util.List;


import collisions.CollisionAction;
import demo.PlatformGame;

import sidescrolling.Sidescroller;
import sprite.AnimatedGameSprite;

/**
 * This is a switch that a fighter can activate. When a Fighter activates the fighter by jumping on it,
 * as shown in the SidescrollerSwitchAction class, the type of sidescroller that the game uses changes.
 * In constructing the SidescrollerSwitch, the user specifies the sidescroller that should be used after
 * the switch is pressed.
 * @author Dustin
 *
 */
@SuppressWarnings("serial")
public class SidescrollerSwitch extends AnimatedGameSprite {
    
    private Sidescroller changeScroller;
    private PlatformGame game;
    
    /**
     * Creates a new SidescrollerSwitch.
     * @param x - x coordinate of the switch
     * @param y - y coordinate of the switch
     * @param imSources - list of names of images that display the switch
     * @param newScroller - a Sidescroller that the game will use after the switch is pressed
     * @param game - the PlatformGame that is being used
     */
    public SidescrollerSwitch(double x, double y, List<String> imSources, Sidescroller newScroller, PlatformGame game) {
        super(x, y, imSources);
        setGroup("SIDESCROLLERSWITCH");
        changeScroller = newScroller;
        this.game = game;
    }
    
    /**
     * Changes the type of sidescroller.
     */
    public void switchSidescroller() {
        setFrame(1);
        game.setSidescroller(changeScroller);
    }
    
    public Class<? extends CollisionAction> getActionClass (){
        return SidescrollerSwitchAction.class; 
    }

}
