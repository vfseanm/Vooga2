package powerUps;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import attributes.Attribute;
import com.golden.gamedev.Game;
import fighter.Fighter;
import platforms.BreakablePlatformItemFactory;


@SuppressWarnings("serial")
public class InvincibilityPowerUp extends PowerUp{

    public InvincibilityPowerUp(BufferedImage[] im, double x, double y, List<String> image, ArrayList<Attribute> attributes, ArrayList<Attribute> attributesToGive) {
        super(im, x, y, image, attributes, attributesToGive);
    }
    
//    private InvincibilityPowerUp(BufferedImage[] im, List<String> image) {
//        super(im, 0, 0, image);
//    }
//    
//    public static BreakablePlatformItemFactory getFactory(BufferedImage[] im, List<String> image, Fighter fighter, Game game) {
//        return new BreakablePlatformItemFactory(new InvincibilityPowerUp(im, image));
//    }
    
//    public PowerUp makeItem(double x, double y) {
//        return new InvincibilityPowerUp(getImages(), x, y, getImageNames());
//    }
    
    public Attribute getAttribute() {
        //to be implemented later
        return null;
    }
}
