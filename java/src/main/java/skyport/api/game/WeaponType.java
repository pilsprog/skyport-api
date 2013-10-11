package skyport.api.game;

import com.google.gson.annotations.SerializedName;

public enum WeaponType {
    @SerializedName("mortar")
    MORTAR, 
    @SerializedName("laser")
    LASER, 
    @SerializedName("droid")
    DROID
}
