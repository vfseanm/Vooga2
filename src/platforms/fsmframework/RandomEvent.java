package platforms.fsmframework;

import java.util.Random;

/**
 * Defines an event that allows a state machine to change state based upon
 * randomness.
 * 
 * @author Nick Gordon
 * 
 */
public class RandomEvent extends DecoratedEvent {

	private Random myRand = new Random();
	private int myNum = 0;

	/**
	 * Constructor to create a RandomEvent
	 * 
	 * @param event
	 *            event that this class decorates
	 */
	public RandomEvent(AbstractEvent event) {
		super(event);
	}

	/**
	 * Returns true for the state machine to advance to its next state if a
	 * random number generator returns 17. The odds of this occurring is 1 in
	 * every 5001 frames of the game.
	 * 
	 * @return boolean
	 */
	@Override
	public boolean isNextState() {
		System.out.println(myNum);
		return myNum == Integer.parseInt(myPlatformResources.getString("RandomNextFactor"));
	}

	/**
	 * Returns true for the state machine to revert to a previous state if a
	 * random number generator returns 8. The odds of this occurring is 1 in
	 * every 5001 frames of the game.
	 * 
	 * @return boolean
	 */
	@Override
	public boolean isPreviousState() {
		return myNum == Integer.parseInt(myPlatformResources.getString("RandomPreviousFactor"));
	}

	/**
	 * Updates the class and gets a new random number each frame of the game
	 * 
	 * @param elapsedTime
	 *            the amount of time that has elapsed in the game
	 */
	public void update(long elapsedTime) {
		myNum = myRand.nextInt(Integer.parseInt(myPlatformResources.getString("RandomEventFactor")));
		System.out.println(myNum);
		super.update(elapsedTime);
	}

}
