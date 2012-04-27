package weapons;

import playfield.SingletonSpriteManager;
import sprite.AnimatedGameSprite;
import com.golden.gamedev.object.Sprite;
import character.AttributeUser;


public class Fireball implements Weapon
{
    private AnimatedGameSprite myFireball;
    private double myDamage;
    private int myDelay;
    private int counter;

    //associates a sprite with the attack also needs speed damage and delay between attacks
    public Fireball (AnimatedGameSprite fireball, double speed, double damage, int delay)
    {
        myFireball = fireball;
        myFireball.setGroup("FIREBALL");
        myDamage = damage;
        myDelay = delay;
        myFireball.setHorizontalSpeed(speed);

    }

    //uses the fireball attack only when the counter allows
    public void use (AttributeUser character)
    {
        if (counter == 0)
        {
            SingletonSpriteManager.getInstance().add(myFireball);
            myFireball.setLocation(character.getX(), character.getY());
        }
        else if (counter > myDelay)
        {
            counter = 0;
        }
        counter++;
    }

    //useful for collisons to inflict damage
    public double getDamage ()
    {
        return myDamage;
    }


    public void invert ()
    {
        myFireball.setHorizontalSpeed(-myFireball.getHorizontalSpeed());

    }

}
