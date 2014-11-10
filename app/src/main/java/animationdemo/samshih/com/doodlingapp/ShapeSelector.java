package animationdemo.samshih.com.doodlingapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ShapeSelector extends View {

    private int shapeColor;
    private boolean displayShapeName;
    private Paint shapePaint;

    private String[] shapeValues = { "square", "circle", "triangle" };
    private int currentShapeIndex = 0;

    public ShapeSelector (Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ShapeSelector, 0, 0);
        // Extract custom attributes into member variables
        try {
            shapeColor = a.getColor(R.styleable.ShapeSelector_shapeColor, Color.BLACK);
            displayShapeName = a.getBoolean(R.styleable.ShapeSelector_displayShapeName, false);
        } finally {
            // TypedArray objects are shared and must be recycled.
            a.recycle();
        }
        //Paint
        shapePaint = new Paint();
        shapePaint.setColor(shapeColor);
        shapePaint.setStyle(Paint.Style.FILL);
      }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currentShapeIndex ++;
            if (currentShapeIndex > (shapeValues.length - 1)) {
                currentShapeIndex = 0;
            }
            postInvalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        // Go over measurement again!
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        String shapeSelected = shapeValues[currentShapeIndex];
        if (shapeSelected.equals("square")) {
            canvas.drawRect(0, 0, 100, 100, shapePaint);
        } else if (shapeSelected.equals("circle")) {
            canvas.drawCircle(100 / 2, 100 / 2, 100 / 2, shapePaint);
        } else if (shapeSelected.equals("triangle")) {
            canvas.drawPath(getTrianglePath(), shapePaint);
        }

    }

    protected Path getTrianglePath() {
        ///
        Point p1 = new Point(0, 100), p2 = null, p3 = null;
        p2 = new Point(p1.x + 100, p1.y);
        p3 = new Point(p1.x + (100 / 2), p1.y - 100);
        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        return path;
    }
//
//    public int getShapeColor() {
//            return shapeColor;
//        }
//         public void setShapeColor(int color) {
//            this.shapeColor = color;
//        }
  }



