package skyport.api.game;

import com.google.gson.annotations.SerializedName;


/**
 * Describes the directions a player can move in skyport.
 * @author Bjarte
 */
public enum Direction {
    none,
    up,
    down,
    @SerializedName("right-up")
    rightUp,
    @SerializedName("right-down")
    rightDown,
    @SerializedName("left-up")
    leftUp,
    @SerializedName("left-down")
    leftDown
}
