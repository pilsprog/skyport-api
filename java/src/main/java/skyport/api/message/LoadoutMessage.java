package skyport.api.message;

public class LoadoutMessage extends Message {
    @SuppressWarnings("unused")
    private String primaryWeapon;
    @SuppressWarnings("unused")
    private String secondaryWeapon;

    public LoadoutMessage(String primary, String secondary) {
        this.message = "loadout";
        this.primaryWeapon = primary;
        this.secondaryWeapon = secondary;
    }
}
