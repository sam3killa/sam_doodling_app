package animationdemo.samshih.com.doodlingapp;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;


public class Stroke {
    private Path path;
    private Paint paintColor;

    public Stroke (Paint paint) {
        paintColor = paint;
    }

    public Path getPath() {
        return path;
    }

    public Paint getPaint() {
        return paintColor;
    }

    public void addPoint(Point pt) {
        if (path == null) {
            path = new Path();
            path.moveTo(pt.x, pt.y);
        } else {
            path.lineTo(pt.x, pt.y);
        }
    }
}
