package skyport.api.game;

import com.google.gson.annotations.SerializedName;

/**
 * Describes the directions a player can move in skyport.
 * 
 * @author Bjarte
 */
public enum Direction {
    @SerializedName("up")
    UP,
    @SerializedName("down")
    DOWN, 
    @SerializedName("right-up")
    RIGHT_UP, 
    @SerializedName("right-down")
    RIGHT_DOWN, 
    @SerializedName("left-up")
    LEFT_UP, 
    @SerializedName("left-down")
    LEFT_DOWN, 
    @SerializedName("none")
    NONE
}
