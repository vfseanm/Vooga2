package platforms.fsmframework;

import java.util.Random;

public class RandomEvent extends DecoratedEvent {
	
	private Random myRand = new Random();
	private int myNum = 0;

	public RandomEvent(AbstractEvent event) {
		super(event);
	}

	@Override
	public boolean isNextState() {
		System.out.println(myNum);
		return myNum == 17;
	}

	@Override
	public boolean isPreviousState() {
		return myNum == 8;
	}
	
	public void update(long elapsedTime) {
		myNum = myRand.nextInt(5001);
		System.out.println(myNum);
		super.update(elapsedTime);
	}

}
