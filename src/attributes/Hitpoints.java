package attributes;

import editor.editorConstructor;

@SuppressWarnings("serial")
public class Hitpoints extends Attribute
{
    private int myHitpoints;
    private int myMaxHP;

    @editorConstructor(parameterNames = {"number of hitpoints"})
    public Hitpoints (int hitpoints) {
        myHitpoints = hitpoints;
        myMaxHP = myHitpoints;
    }


    public String getName () {
        return "Hitpoints";
    }


    public void modifyHitpoints (int hpChange) {
        myHitpoints += hpChange;
        if (myHitpoints <= 0) {
            try {
            	myEnemy.updateAttribute("NumberOfLives", -1);
                resetHP();
            }
            //Will add better error handling
            catch (Exception e) {
            	myEnemy.setActive(false);
            }
        }

    }


    public void resetHP () {
        myHitpoints = myMaxHP;
    }


    public String toString () {
        return "Attribute Hitpoints is currently " + myHitpoints + " MaxHP is " +
               myMaxHP;
    }

}
