package platforms.fsmframework;

import java.util.List;

import platforms.platformtypes.AbstractPlatform;

public abstract class AbstractEvent {
	
	protected List<AbstractPlatform> myPlatforms;
	protected List<AbstractPlatformState> myTransitionList;
	protected AbstractPlatformState myCurrentState;
	protected int myCurrentStateIndex;
	
	public AbstractEvent(List<AbstractPlatformState> transitionList, List<AbstractPlatform> platforms) {
		myTransitionList = transitionList;
		myPlatforms = platforms;
		if (myTransitionList != null) {
			myCurrentState = myTransitionList.get(0);
			myCurrentState.thing();
			myCurrentStateIndex = 0;
		}
	}
	
	/*public void setControlledPlatforms(List<AbstractPlatform> platforms) {
		myPlatforms = platforms;
		for (AbstractPlatformState state: myTransitionList) {
			state.setControlledPlatforms(platforms);
		}
	}*/
	
	public abstract boolean moveToNextState();
	public abstract boolean moveToPreviousState();
	
	public void setState(AbstractPlatformState state) {
		if (myTransitionList.contains(state)) {
			myCurrentState = state;
			myCurrentState.thing();
			int index = myTransitionList.indexOf(state);
			myCurrentStateIndex = index;
		}
	}
	
	public void setToInitialState() {
		if (myTransitionList != null) {
			myCurrentState = myTransitionList.get(0);
			myCurrentState.thing();
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
		else if (myCurrentStateIndex == myTransitionList.size() - 1) {
			myCurrentState = myTransitionList.get(0);
			myCurrentState.thing();
			myCurrentStateIndex = 0;
		}
		else {
			myCurrentStateIndex++;
			myCurrentState = myTransitionList.get(myCurrentStateIndex);
			myCurrentState.thing();
		}
	}
	
	public void changeToPreviousState() {
		if (myTransitionList == null) {
			return;
		}
		
		else if (myCurrentStateIndex == 0) {
			myCurrentStateIndex = myTransitionList.size() - 1;
			myCurrentState = myTransitionList.get(myCurrentStateIndex);
			myCurrentState.thing();
		}
		
		else {
			myCurrentStateIndex--;
			myCurrentState = myTransitionList.get(myCurrentStateIndex);
			myCurrentState.thing();
		}
	}
	
	public void update(long elapsedTime) {
		if (moveToNextState()) {
			changeToNextState();
		}
		else if (moveToPreviousState()) {
			changeToPreviousState();
		}
		myCurrentState.handle(elapsedTime);
	}
}
