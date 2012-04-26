package attributes.interfaces;

import com.golden.gamedev.engine.BaseInput;

/**
 * This interface is used to distinguish Attributes that utilize
 * BaseInput so as to function on user input.
 * 
 * @author Tori
 */
public interface Input {

	public abstract void setUserInput(BaseInput userInput);
}
