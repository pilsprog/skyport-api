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
        String kj = point.getK() + ", " + point.getJ();
        writer.value(kj);
    }

    @Override
    public Point read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }

        String[] kj = reader.nextString().split(",");
        int k = Integer.parseInt(kj[0].trim());
        int j = Integer.parseInt(kj[1].trim());

        return new Point(k, j);
    }

}
