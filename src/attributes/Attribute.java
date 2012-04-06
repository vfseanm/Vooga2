package attributes;

import com.golden.gamedev.*;

public abstract class Attribute {
	
	protected Game 				myGame;
	protected boolean			isActive;
	
	public Attribute(Game game) {
		myGame = game;
		isActive = true;
	}
	
	public abstract void doFunction();
	
	public void setActive(boolean trueFalse) {
		isActive = trueFalse;
	}
}
