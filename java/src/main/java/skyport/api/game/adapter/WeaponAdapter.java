package skyport.api.game.adapter;

import java.io.IOException;

import skyport.api.game.weapon.Droid;
import skyport.api.game.weapon.Laser;
import skyport.api.game.weapon.Mortar;
import skyport.api.game.weapon.Weapon;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class WeaponAdapter extends TypeAdapter<Weapon> {

    @Override
    public void write(JsonWriter out, Weapon value) throws IOException {
        if(value == null) {
            out.nullValue();
            return;
        }
        out.value(value.getClass().getSimpleName().toLowerCase());
    }

    @Override
    public Weapon read(JsonReader reader) throws IOException {
        String weapon = null;
        Integer level = null;
        
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch(name) {
            case "name":
                weapon = reader.nextString();
                break;
            case "level":
                level = reader.nextInt();
                break;
            }
        }
        reader.endObject();
        
        Weapon w = null;
        switch(weapon) {
        case "mortar":
            w = new Mortar(level);
        case "laser":
            w = new Laser(level);
        case "droid":
            w = new Droid(level);
        }
        return w;
    }

}
