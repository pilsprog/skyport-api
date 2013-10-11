package skyport.api.game;

import com.google.gson.annotations.SerializedName;

public enum Tile {
    @SerializedName("G")
    GRASS,
    @SerializedName("V")
    VOID, 
    @SerializedName("S")
    SPAWN, 
    @SerializedName("E")
    EXPLOSIUM,
    @SerializedName("R")
    RUBIDIUM,
    @SerializedName("C")
    SCRAP, 
    @SerializedName("O")
    ROCK
}
