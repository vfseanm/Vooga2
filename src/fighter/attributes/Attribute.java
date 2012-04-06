package fighter.attributes;

import com.golden.gamedev.*;
import fighter.*;

public abstract class Attribute {
	
	protected Game 				myGame;
	protected Fighter			myFighter;
	protected String			myName;
	protected boolean			isActive;
	protected boolean			isInherent;
	
	public Attribute(Game game, Fighter fighter) {
		myGame = game;
		isActive = true;
	}
	
	public abstract void doFunction();
	
	public void setActive(boolean trueFalse) {
		isActive = trueFalse;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public boolean isInherent() {
		return isInherent;
	}
	
	public void setInherent(boolean trueFalse) {
		isInherent = trueFalse;
	}
	
	public String getName() {
		return myName;
	}
}
