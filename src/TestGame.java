import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import platforms.*;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.SpriteGroup;

import fighter.Fighter;

public class TestGame extends Game {
    
    private Fighter fighter;
    private SpriteGroup FIGHTER_GROUP;
    
    public void initResources() {
        FIGHTER_GROUP = new SpriteGroup("Fighter Group");
        BufferedImage[] fighterImages = new BufferedImage[4];
        String[] playNames  = {"Resources/RunningChikapu1.png", "Resources/RunningChikapu2.png", 
                "Resources/RunningChikapu3.png", "Resources/RunningChikapu4.png"};
        fighterImages[0] = getImage("RunningChikapu1.png");
        fighterImages[1] = getImage("RunningChikapu2.png");
        fighterImages[2] = getImage("RunningChikapu3.png");
        fighterImages[3] = getImage("RunningChikapu4.png");
        List<String> fighterNames = new ArrayList<String>();
        for (String name: playNames) {
            fighterNames.add(name);
        }
        fighter = new Fighter((sprite.Game) new Game(), fighterImages, 300.0, 400.0, fighterNames); double x, double y, List<String> imagesnew Fighter((Game) this, fighterImages, 400.0, 300.0, fighterNames);
                
        BufferedImage[] platformImage = new BufferedImage[1]; 
        platformImage[0] = getImage("Resources/block3.png");
        ArrayList<String> platformNames = new ArrayList<String>();
        platformNames.add("Resources/block3.png");
        AbstractPlatform p1 = new SimplePlatform(platformImage, 500, 400, platformNames, );
    }
}
