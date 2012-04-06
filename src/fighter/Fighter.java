package fighter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import sprite.*;
import attributes.*;
import com.golden.gamedev.*;


@SuppressWarnings("serial")
public class Fighter extends GameSprite {

	private List<Attribute>		myInherentAttributes;
	private List<Attribute>		myExtraAttributes;
	private AttributeRemover	myAttributeRemover;
	
	
	public Fighter(BufferedImage image, double x, double y, String imagePath, AttributeRemover attributeRemover) {
		super(image, x, y, imagePath);
		myInherentAttributes = new ArrayList<Attribute>();
		myExtraAttributes = new ArrayList<Attribute>();
		myAttributeRemover = attributeRemover;
	}
	
	public int searchInherentAttributes(Class attribute) {
	    for (int i = 0; i < myInherentAttributes.size(); i++) {
            if (myInherentAttributes.get(i).getClass() == attribute) {
                return i;
            }
	    }
	    return -1;
	}
	
	public void setAttributeFalse(int indexAttribute) {
	    myInherentAttributes.get(indexAttribute).setActive(false);
	}
	
	public void addExtraAttribute(Attribute extra) {
	    myExtraAttributes.add(extra);
	}
	
	public void removeExtraAttribute() {
		myAttributeRemover.removeAttribute(myExtraAttributes);
	}

}
