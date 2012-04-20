package attributes;


// FINISH IMPLEMENTING
@SuppressWarnings("serial")
public class Shoot extends Attack {

    WeaponObj weapon;
    
    public Shoot(int blam){
        super(blam);
    }
    attack()
    {
        weapon.use();
    }
    
    
	@Override
	public String getName() {
		return "Shoot";
	}


	public void invert() {
		// TODO Auto-generated method stub

	}

	public void update(long elaspedTime) {
		// TODO Auto-generated method stub

	}
	
	public Object clone()
	{
	    return new Shoot();
	}

    public static Shoot fromJson(String json)
    {
        return new Shoot();
    }
}
