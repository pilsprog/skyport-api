package skyport.api.game;


public class Player {
    private String name;
    private Weapon primaryWeapon;
    private Weapon secondaryWeapon;
    private Point position;
    private int score;
    private int health;
    
    public String getName() {
	return name;
    }
    
    public Weapon getPrimary() {
	return this.primaryWeapon;
    }

    public Weapon getSecondary() {
	return this.secondaryWeapon;
    }

    public Weapon getWeapon(String string) {
	if (this.primaryWeapon.getName().equals(string)) {
	    return this.primaryWeapon;
	} else if (this.secondaryWeapon.getName().equals(string)) {
	    return this.secondaryWeapon;
	} else {
	    return null;
	}
    }

    public Point getPosition() {
	return position;
    }
}
