package fighter.attributes;

import com.golden.gamedev.Game;
import fighter.*;

public class Life extends Attribute {

	private int myNumLives;
	
	public Life(Game game, Fighter fighter, int numLives) {
		super(game, fighter);
		myNumLives = numLives;
		isInherent = true;
	}

	public void doFunction() {
		// TODO Auto-generated method stub
		
	}

	public void changeNumLives(int numLives) {
		myNumLives += numLives;
		if (myNumLives == 0) myFighter.dies();
	}
}
