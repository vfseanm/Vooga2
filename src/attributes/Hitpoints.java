package attributes;

import editor.editorConstructor;

@SuppressWarnings("serial")
public class Hitpoints extends Attribute
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
            if(myGameCharacter.hasAttributeByName("NumberOfLives"))
            {
                myGameCharacter.updateAttribute("NumberOfLives", -1);
                myGameCharacter.restoreOriginalAttribute("Hitpoints");
            }
            
            else 
            {
                myGameCharacter.setActive(false);
            }
        }

    }

    public int getHitPoints(){
		return myHitpoints;
    	
    }
 

    public String toString ()
    {
        return "Attribute Hitpoints is currently " + myHitpoints;
    }
    
    public Object clone()
    {
        return new Hitpoints(myHitpoints);
    }
    
    public String toJson()
    {
        return myHitpoints+"";
    }
    
    public static Hitpoints fromJson(String json)
    {
        int points = Integer.parseInt(json);
        return new Hitpoints(points);
    }

}
