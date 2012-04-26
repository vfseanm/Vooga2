package sidescrolling.special;

import java.util.List;


import collisions.CollisionAction;
import demo.PlatformGame;

import sidescrolling.Sidescroller;
import sprite.AnimatedGameSprite;

@SuppressWarnings("serial")
public class SidescrollerSwitch extends AnimatedGameSprite {
    
    private Sidescroller changeScroller;
    private PlatformGame game;
    
    public SidescrollerSwitch(double x, double y, List<String> imSources, Sidescroller newScroller, PlatformGame game) {
        super(x, y, imSources);
        setGroup("SIDESCROLLERSWITCH");
        changeScroller = newScroller;
        this.game = game;
    }
    
    public void switchSidescroller() {
        game.setSidescroller(changeScroller);
    }
    
    public Class<? extends CollisionAction> getActionClass (){
        return SidescrollerSwitchAction.class; 
    }

}
