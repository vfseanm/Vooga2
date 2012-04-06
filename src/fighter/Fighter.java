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
		myInherentAttributes = {new Health(3)};
		myExtraAttributes = new ArrayList<Attribute>();
		myAttributeRemover = attributeRemover;
	}
	
	public boolean containsAttribute(Class attribute) {
	    for (Attribute ability: myInherentAttributes) {
	        if (ability.getClass() == attribute) {
	            return true;
	        }
	    }
        return false;
	}
	
	
	public void addExtraAttribute(Attribute extra) {
	    myExtraAttributes.add(extra);
	}
	
	
	public void removeExtraAttribute() {
		myAttributeRemover.removeAttribute(myExtraAttributes);
	}

}
