package attributes.sharedattributes;

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
        if (isActive) myWeapon.use(myAttributeUser);
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
