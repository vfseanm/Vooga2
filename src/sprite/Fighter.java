package sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import powerUps.PowerUp;

@SuppressWarnings("serial")
public class Fighter extends GameSprite {

    ArrayList<PowerUp> powerUps;
    
    public Fighter(Game game, BufferedImage im, double x, double y, String image) {
        super(im, x, y, image);
        powerUps = new ArrayList<PowerUp>();
    }
    
    public void update(long elapsedTime) {
        for (PowerUp ability: powerUps) {
            ability.update(elapsedTime);
        }
    }
    
    public void addPowerUp(PowerUp power) {
        powerUps.add(power);
    }
    //STRATEGY pattern for the following?
    //public void loseAllPowerUps()
    //public void loseFirstPowerUp()
    //public void loseLastPowerUp()
    //public void loseRandomPowerUp()
}
