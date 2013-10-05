package skyport.api.message;

public class Loadout extends Message {
    private String message = "loadout";
    private String primaryWeapon = "laser";
    private String secondaryWeapon = "mortar";
    
    public Loadout(String primary, String secondary) {
	this.primaryWeapon = primary;
	this.secondaryWeapon = secondary;
    }
}
