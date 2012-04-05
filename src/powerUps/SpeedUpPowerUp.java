package powerUps;

import java.awt.image.BufferedImage;

import sprite.Fighter;

@SuppressWarnings("serial")
public class SpeedUpPowerUp extends PowerUp {

    public SpeedUpPowerUp(BufferedImage im, double x, double y, String image, Fighter fighter) {
        super(im, x, y, image, fighter);
    }
    
    public void update(long elapsedTime) {
        
    }
}
