package animationdemo.samshih.com.doodlingapp; /**
 * Created by samshih on 11/4/14.
 */

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.List;


public class DoodleView extends View {

    private List<Stroke> allStrokes;
    // Maps integers to objects
    private SparseArray<Stroke> activeStrokes; // All strokes that need to be drawn
    private Paint currentPaintColor;
    private int currentColor;
    private int currentStrokeSize;
    private Boolean clear = false;


    public DoodleView(Context context) {
        super(context);
    }

    // Without a style, called by inflater
    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        allStrokes = new ArrayList<Stroke>();
        activeStrokes = new SparseArray<Stroke>();
        currentPaintColor = new Paint();
        currentColor = Color.BLUE;
        currentStrokeSize = 4;
        currentPaintColor.setColor(currentColor);
        currentPaintColor.setStyle(Paint.Style.STROKE);
        currentPaintColor.setStrokeWidth(currentStrokeSize);

    }

    public void setBrushColor(int color) {
        currentColor = color;
        currentPaintColor = new Paint();
        currentPaintColor.setColor(currentColor);
        currentPaintColor.setStrokeWidth(currentStrokeSize);
    }

    public void setStrokeSize(int strokeSize) {
        currentStrokeSize = strokeSize;
        currentPaintColor = new Paint();
        currentPaintColor.setColor(currentColor);
        currentPaintColor.setStrokeWidth(currentStrokeSize);
    }

    public void clearArray() {
        allStrokes = new ArrayList<Stroke>();
        clear = true;
        invalidate();
    }

    // If a custom style is applied

    public DoodleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // Draw
    @Override
    protected void onDraw(Canvas canvas) {
       // Look at state (int, floats, text)
       // Use the state to create a visual representation
       // canvas.drawCircle(50, 100, 100, p);
        if (clear == true){
            canvas.drawColor(Color.WHITE);
            clear = false;
        }
         else {
            if (allStrokes != null) {
                for (Stroke stroke : allStrokes) {
                    if (stroke != null) {
                        Path path = stroke.getPath();
                        Paint painter = stroke.getPaint();
                        if ((path != null) && (painter != null)) {
                            canvas.drawPath(path, painter);
                        }
                    }
                }
            }
         }
    }

    // Set the Content Height and Width
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int pointerCount = event.getPointerCount();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                pointDown((int) event.getX(), (int) event.getY(), event.getPointerId(0));
                postInvalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                for (int pc = 0; pc < pointerCount; pc++) {
                    pointMove((int) event.getX(pc), (int) event.getY(pc), event.getPointerId(pc));
                }
                postInvalidate();
                return true;
            case MotionEvent.ACTION_UP:
                return false;
        }
        return false;
    }

    private void pointDown(int x, int y, int id){
        // Create paint
        currentPaintColor.setStyle(Paint.Style.STROKE);
        //currentPaintColor.setStrokeWidth(10);

        //create the Stroke
        Point pt = new Point(x, y);
        Stroke stroke = new Stroke(currentPaintColor);
        stroke.addPoint(pt);
        activeStrokes.put(id, stroke);
        allStrokes.add(stroke);
    }

    private void pointMove(int x, int y, int id) {
        //retrieve the stroke and add new point to its path
        Stroke stroke = activeStrokes.get(id);
        if (stroke != null) {
            Point pt = new Point(x, y);
            stroke.addPoint(pt);
        }
     }

}
