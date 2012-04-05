package platforms;

public class FrameTimer {

	double myFPS = 100;
	double numFrames = 0;
	double elapsedTime = 0;
	
	public void setFPS(double fps) {
		myFPS = fps;
	}
	
	public void update() {
		numFrames++;
		elapsedTime = numFrames;
	}
	
	public double getElapsedTime() {
		return elapsedTime;
	}
	
}
