package bonusobjects;

import java.awt.image.BufferedImage;
import java.util.List;
import fighter.Fighter;

@SuppressWarnings("serial")
public class Carryable extends BonusObject {
	
	protected Fighter		myFighter;
	
	public Carryable(double x, double y,
			List<String> image) {
		super(x, y, image);
	}
	
	public void setFighter(Fighter fighter) {
		myFighter = fighter;
	}
}
