package animationdemo.samshih.com.doodlingapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class DoodleActivity extends Activity {

    private DoodleView colorSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodle);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.doodle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);


    }

    public void greenButton(View v) {
        colorSelector = (DoodleView) findViewById(R.id.view);
        colorSelector.setBrushColor(Color.GREEN);
    }
    public void redButton(View v) {
        colorSelector = (DoodleView) findViewById(R.id.view);
        colorSelector.setBrushColor(Color.RED);
    }
    public void whiteButton(View v) {
        colorSelector = (DoodleView) findViewById(R.id.view);
        colorSelector.setBrushColor(Color.WHITE);
    }
    public void yellowButton(View v) {
        colorSelector = (DoodleView) findViewById(R.id.view);
        colorSelector.setBrushColor(Color.YELLOW);
    }
    public void blueButton(View v) {
        colorSelector = (DoodleView) findViewById(R.id.view);
        colorSelector.setBrushColor(Color.BLUE);
    }


    public void smallButton(View v) {
        colorSelector = (DoodleView) findViewById(R.id.view);
        colorSelector.setStrokeSize(4);
    }


    public void medButton(View v) {
        colorSelector = (DoodleView) findViewById(R.id.view);
        colorSelector.setStrokeSize(10);
    }


    public void largeButton(View v) {
        colorSelector = (DoodleView) findViewById(R.id.view);
        colorSelector.setStrokeSize(15);
    }


    public void clearButton(View v) {
        colorSelector = (DoodleView) findViewById(R.id.view);
        colorSelector.clearArray();
    }



}
