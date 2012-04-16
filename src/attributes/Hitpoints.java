package attributes;

import editor.editorConstructor;

@SuppressWarnings("serial")
public class Hitpoints extends Attribute
{
    private int myHitpoints;
    private int myMaxHP;
    

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
        if (myHitpoints <= 0) {
            try {
            	myGameCharacter.updateAttribute("NumberOfLives", -1);
                resetHP();
            }
            //Will add better error handling
            catch (Exception e) {
            	myGameCharacter.setActive(false);
            }
        }
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

    public void resetHP () {
        myHitpoints = myMaxHP;
    }
 

    public String toString ()
    {
        return "Attribute Hitpoints is currently " + myHitpoints;
    }
}
