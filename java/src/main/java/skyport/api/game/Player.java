package skyport.api.game;


public class Player {
    private String name;
    private Weapon primaryWeapon;
    private Weapon secondaryWeapon;
    private String position;
    private int score;
    private int health;
    
    public String getName() {
	return name;
    }
    
    public Weapon getPrimary() {
	return this.primaryWeapon;
    }
    
    public int getJ() {
	String[] pos = position.split(",");
	System.out.println("J:"+pos[0].trim());
	return Integer.parseInt(pos[0].trim());
    }
    
    public int getK() {
	String[] pos = position.split(",");
	System.out.println("K:"+ pos[1].trim());
	return Integer.parseInt(pos[1].trim());
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
}
