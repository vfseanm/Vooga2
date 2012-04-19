package bonusobjects;

import java.awt.image.BufferedImage;
import java.util.List;

// Do we still want to do this? Implementation?

@SuppressWarnings("serial")
public class SlowEnemiesPowerUp extends PowerUp {

    public SlowEnemiesPowerUp(double x, double y, List<String> image) {
        super(x, y, image);
    }
    
    private SlowEnemiesPowerUp(BufferedImage[] im, List<String> image) {
        super(0, 0, image);
    }
}
