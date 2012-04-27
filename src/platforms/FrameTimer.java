package platforms;

import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * This class is used as a timer that keeps track of the elapsed time based upon
 * the number of frames that have passed since the timer was last reset.
 * 
 * @author Nick Gordon
 */
public class FrameTimer implements Serializable {

	private static final long serialVersionUID = 6108572889734667116L;
	double myFPS = Double.parseDouble(ResourceBundle.getBundle(
			"platforms.PlatformResourceBundle").getString("TimerFPS"));
	double numFrames = 0;

	/**
	 * Sets the frame per second field in this class. User should set this as
	 * the FPS for the GTGE game. Default is 100FPS.
	 * 
	 * @param fps
	 *            a double representing the frames per second of the GTGE game
	 */
	public void setFPS(double fps) {
		myFPS = fps;
	}

	/**
	 * Resets the timer.
	 */
	public void clear() {
		numFrames = 0;
	}

	/**
	 * This function should get called every frame of the game. Updates the
	 * numFrames field which represents the number of frames that have passed
	 * since the last reset.
	 */
	public void update() {
		numFrames++;
	}

	/**
	 * Gets the field representing the elapsed time since the timer was last
	 * reset. Time is represented by frames.
	 * 
	 * @return numFrames field representing elapsed time in frames.
	 */
	public double getPassedFrames() {
		return numFrames;
	}
}
