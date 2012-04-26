package attributes.enemyattributes;

import weapons.Weapon;


@SuppressWarnings("serial")
public class ProjectileAttack extends Attack
{
    private Weapon myWeapon;


    public ProjectileAttack (Weapon weapon)
    {
        myWeapon = weapon;
    }


    @Override
    public void attack ()
    {
        myWeapon.use(myGameCharacter);

    }


    @Override
    public void invert ()
    {
        myWeapon.invert();

    }


    public void modifyProjectileAttack (Weapon weapon)
    {
        myWeapon = weapon;
    }


    @Override
    public Object clone ()
    {
        return new ProjectileAttack(myWeapon);
    }

}