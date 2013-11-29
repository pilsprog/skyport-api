package skyport.api.message;

import skyport.api.game.weapon.Weapon;

public class LoadoutMessage extends Message {
    @SuppressWarnings("unused")
    private Weapon primaryWeapon;
    @SuppressWarnings("unused")
    private Weapon secondaryWeapon;

    public LoadoutMessage(Weapon primary, Weapon secondary) {
        this.message = "loadout";
        this.primaryWeapon = primary;
        this.secondaryWeapon = secondary;
    }
}
