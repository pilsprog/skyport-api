package skyport.api.game.adapter;

import java.io.IOException;

import skyport.api.game.Point;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class PointAdapter extends TypeAdapter<Point> {

    @Override
    public void write(JsonWriter writer, Point point) throws IOException {
        if (point == null) {
            writer.nullValue();
            return;
        }
        String jk = point.getJ() + "," + point.getK();
        writer.value(jk);
    }

    @Override
    public Point read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }

        String[] jk = reader.nextString().split(",");
        int j = Integer.parseInt(jk[0].trim());
        int k = Integer.parseInt(jk[1].trim());
        return new Point(j, k);
    }

}
