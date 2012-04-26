package platforms.fsmframework;

import java.util.List;

import platforms.platformtypes.AbstractPlatform;

public abstract class DecoratedEvent extends AbstractEvent {
	
	AbstractEvent myDecoratorComponent;
	
	public abstract boolean isNextState();
	public abstract boolean isPreviousState();
	
	public DecoratedEvent(AbstractEvent event) {
		myDecoratorComponent = event;
	}

	@Override
	public void setControlledPlatforms(List<AbstractPlatform> platforms) {
		myDecoratorComponent.setControlledPlatforms(platforms);
		
	}

	@Override
	public void setState(AbstractPlatformState state) {
		myDecoratorComponent.setState(state);
		
	}

	@Override
	public void setToInitialState() {
		myDecoratorComponent.setToInitialState();
		
	}

	@Override
	public AbstractPlatformState getState() {
		return myDecoratorComponent.getState();
	}

	@Override
	public void changeToNextState() {
		//System.out.println("changetonextstate");
		/*if (isNextState()) {
			myDecoratorComponent.changeToNextState();
		}*/
		myDecoratorComponent.changeToNextState();	
	}

	@Override
	public void changeToPreviousState() {
		/*if (isPreviousState()) {
			myDecoratorComponent.changeToPreviousState();
		}*/
		myDecoratorComponent.changeToPreviousState();
		
	}

	@Override
	public void update(long elapsedTime) {
		if (isNextState()) {
			changeToNextState();
		}
		if (isPreviousState()) {
			changeToPreviousState();
		}
		myDecoratorComponent.update(elapsedTime);
		
		
	}

}
