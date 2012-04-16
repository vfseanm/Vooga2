package bonusobjects;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;

import character.GameCharacter;
import attributes.*;

@SuppressWarnings("serial")
public class PowerUp extends BonusObject {
	
	protected GameCharacter			myGameCharacter;
	
	public PowerUp(BufferedImage[] im, double x, double y, List<String> image) {
		super(im, x, y, image);
	}

	public List<Attribute> getAttributesToOffer() {
		return Collections.unmodifiableList(myAttributesToOffer);
	}
	
	public void setGameCharacter(GameCharacter gameCharacter) {
		myGameCharacter = gameCharacter;
	}
}
