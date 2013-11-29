package skyport.api.message;

import skyport.api.game.weapon.Weapon;

public class UpgradeMessage extends ActionMessage {
    @SuppressWarnings("unused")
    private Weapon weapon;

    public UpgradeMessage(Weapon weapon) {
        this.type = "upgrade";
        this.weapon = weapon;
    }
}
