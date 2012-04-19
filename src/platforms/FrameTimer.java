package platforms;



import java.io.Serializable;
import java.util.ResourceBundle;

public class FrameTimer implements Serializable {
  
    private static final long serialVersionUID = 6108572889734667116L;
    double myFPS = Double.parseDouble(ResourceBundle.getBundle("platforms.PlatformResourceBundle").getString("TimerFPS"));
	double numFrames = 0;
	
	public void setFPS(double fps) {
		myFPS = fps;
	}
	
	public void update() {
		numFrames++;
	}
	
	public double getPassedFrames() {
		return numFrames;
	}
}
