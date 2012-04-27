package platforms.fsmframework;

import java.util.List;
import platforms.platformtypes.AbstractPlatform;

public class SimpleEvent extends AbstractEvent {
	
	protected List<AbstractPlatform> myPlatforms;
	protected List<AbstractPlatformState> myTransitionList;
	protected AbstractPlatformState myCurrentState;
	protected int myCurrentStateIndex;
	
	public SimpleEvent(List<AbstractPlatformState> transitionList, List<AbstractPlatform> platforms) {
		myTransitionList = transitionList;
		setControlledPlatforms(platforms);
		setToInitialState();
	}
	
	public void setControlledPlatforms(List<AbstractPlatform> platforms) {
		myPlatforms = platforms;
		for (AbstractPlatformState state: myTransitionList) {
			state.setControlledPlatforms(platforms);
		}
	}
	
	public void setState(AbstractPlatformState state) {
		if (myTransitionList.contains(state)) {
			myCurrentState = state;
			myCurrentState.decoratePlatforms();
			int index = myTransitionList.indexOf(state);
			myCurrentStateIndex = index;
		}
	}
	
	
	public void setToInitialState() {
		if (myTransitionList != null) {
			myCurrentState = myTransitionList.get(0);
			myCurrentState.decoratePlatforms();
			myCurrentStateIndex = 0;
		}
	}
	
	public AbstractPlatformState getState() {
		return myCurrentState;
	}
	
	public void changeToNextState() {
		if (myTransitionList == null) {
			return;
		}
		myCurrentStateIndex++;
		myCurrentState = myTransitionList.get(myCurrentStateIndex % myTransitionList.size());
		myCurrentState.decoratePlatforms();
	}
	
	public void changeToPreviousState() {
		if (myTransitionList == null) {
			return;
		}
		myCurrentStateIndex--;
		myCurrentState = myTransitionList.get(myCurrentStateIndex % myTransitionList.size());
		myCurrentState.decoratePlatforms();
	}
	
	public void update(long elapsedTime) {
		myCurrentState.handle(elapsedTime);
	}
}

