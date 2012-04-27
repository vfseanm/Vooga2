package attributes.sharedattributes;

import attributes.Attribute;

import com.google.gson.Gson;

import editor.editorConstructor;
import editor.json.AttributeFactory;
import editor.json.JsonableAttribute;

@SuppressWarnings("serial")
public class Hitpoints extends Attribute implements JsonableAttribute
{
    private int myHitpoints;
 
    

    @editorConstructor(parameterNames = { "number of hitpoints" })
    public Hitpoints (int hitpoints)
    {
        super(hitpoints);
        myHitpoints = hitpoints;
    }


    public String getName () {
        return "Hitpoints";
    }


    public void modifyHitpoints (int hpChange) {
        myHitpoints += hpChange;
        
        if (myHitpoints <= 0)
        {
            if(myAttributeUser.hasAttributeByName("NumberOfLives"))
            {
                myAttributeUser.modifyAttribute("NumberOfLives", -1);
                myAttributeUser.restoreOriginalAttribute("Hitpoints");
            }
            
            else 
            {
                myAttributeUser.setActive(false);
            }
        }

    }


 

    public String toString ()
    {
        return "Attribute Hitpoints is currently " + myHitpoints;
    }
    
    public Object clone()
    {
        return new Hitpoints(myHitpoints);
    }
    
    public int getHitPoints(){
		return myHitpoints;
    	
    }
    
    public String toJson()
    {
        Gson gson = new Gson();
        return gson.toJson(myHitpoints);
    }
    
    public  Hitpoints fromJson(String json)
    {
        Gson gson = new Gson();
        int points = gson.fromJson(json, int.class);
        return new Hitpoints(points);
    }
    
    private Hitpoints(){}
    public static AttributeFactory<Hitpoints> getFactory()
    {
        return new AttributeFactory<Hitpoints>(new Hitpoints());
    }
   
    
}