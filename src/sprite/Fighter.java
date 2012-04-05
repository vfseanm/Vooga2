package sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Fighter extends GameSprite {

    ArrayList<GameSprite> powerUps;
    
    public Fighter(Game game, BufferedImage im, double x, double y, String image) {
        super(im, x, y, image);
        powerUps = new ArrayList<GameSprite>();
    }
    
    public void update(long elapsedTime) {
        
    }
    
}
