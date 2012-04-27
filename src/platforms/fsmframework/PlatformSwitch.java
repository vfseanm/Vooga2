package platforms.fsmframework;

import java.util.List;
import java.util.ResourceBundle;

import platforms.FrameTimer;
import platforms.platformtypes.PlatformAction;

import collisions.CollisionAction;
import sprite.AnimatedGameSprite;

/**
 * Defines a game sprite that serves as a switch in a platform state machine.
 * The switch only activates when the fighter of the game jumps on top of it.
 * Upon activation if associated with a SwitchEvent will allow a platform to
 * change state. Moreover, after activation the switch will deactivate after a
 * specified amount of time.
 * 
 * @author Nick Gordon
 * 
 */
public class PlatformSwitch extends AnimatedGameSprite {

	transient protected ResourceBundle myPlatformResources = ResourceBundle
	.getBundle("platforms.PlatformResourceBundle");
	private static final long serialVersionUID = 1L;
	private boolean SwitchOn = false;
	private int myButtonDelay = Integer.parseInt(myPlatformResources.getString("SwitchDelay"));
	private FrameTimer myTimer = new FrameTimer();

	/**
	 * Constructor for instantiating a PlatformSwitch
	 * 
	 * @param x
	 *            horizontal position of the switch
	 * @param y
	 *            vertical position of the switch
	 * @param imSources
	 *            image that is rendered to represent the switch in game
	 */
	public PlatformSwitch(double x, double y, List<String> imSources) {
		super(x, y, imSources);
		setGroup("PLATFORMSWITCH");
	}

	/**
	 * Call to activate or activate the switch. Should only be called to
	 * activate the switch in a collision manager that checks if a fighter has
	 * jumped on top of it.
	 * 
	 * @param bool
	 *            boolean to determine whethr to activate or deactivate the
	 *            switch.
	 */
	public void setOn(boolean bool) {
		SwitchOn = bool;
		if (bool) {
			setFrame(1);
		} else {
			setFrame(0);
		}
	}

	/**
	 * Returns boolean representing whether switch is activated or deactivated
	 * 
	 * @return boolean representing whether switch is activated or deactivated
	 */
	public boolean getSwitchOnOff() {
		return SwitchOn;
	}

	/**
	 * Updates the platform switch
	 * 
	 * @param elapsedTime
	 *            the amount of time that has elapsed in the game
	 */
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (myTimer.getPassedFrames() >= myButtonDelay && SwitchOn) {
			setOn(false);
			myTimer.clear();
		}
		myTimer.update();
	}

	public Class<? extends CollisionAction> getActionClass() {
		return PlatformAction.class;
	}

	/**
	 * Returns the name of the sprite group that this switch should be
	 * associated with. Is used for collisions
	 * 
	 * @return String representing name of the sprite group this switch is
	 *         assocaited with.
	 */
	public String getGroup() {
		return ("PLATFORMSWITCH");
	}
}
