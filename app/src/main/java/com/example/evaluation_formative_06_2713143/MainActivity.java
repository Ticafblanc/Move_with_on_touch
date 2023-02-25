package com.example.evaluation_formative_06_2713143;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView droidInMove;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView droid_blue = findViewById(R.id.droid_blue);
        droid_blue.setColorFilter(Color.BLUE);
        ImageView droid_rouge = findViewById(R.id.droid_rouge);
        droid_rouge.setColorFilter(Color.RED);
        droidInMove = null;

        ConstraintLayout layout = findViewById(R.id.layout_main);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                geremotion(event);
                return true;
            }
        });
    }

    private void setPositionView(View v, float x, float y){
        v.setX(x - v.getWidth() / 2);
        v.setY(y - v.getHeight() / 2);
    }

    private boolean isOnView(View v, float x, float y){
        float vx = v.getX();
        float vy = v.getY();

        return (x >= vx && x <= vx + v.getWidth()
                && y >=  vy && y <=  vy +  v.getHeight());
    }

    ImageView selectToMove(float x, float y){
        ImageView droid_blue = findViewById(R.id.droid_blue);
        ImageView droid_rouge = findViewById(R.id.droid_rouge);
        if (isOnView(droid_blue, x, y))
            return droid_blue;
        else if (isOnView(droid_rouge, x, y))
            return droid_rouge;
        return null;
    }

    void debug(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                Log.i(TAG, "geremotion: DOWN");
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                Log.i(TAG, "geremotion: P_DOWN");

                break;

            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "geremotion: MOVE");

                break;

            case MotionEvent.ACTION_UP:
                Log.i(TAG, "geremotion: UP");
                break;

            case MotionEvent.ACTION_POINTER_UP:
                Log.i(TAG, "geremotion: P_UP");
                break;

            default:
                Log.i(TAG, "geremotion: DEFAULT");
                break;
        }
    }

    private void geremotion(MotionEvent event){

            float  x = event.getX(0);
            float  y = event.getY(0);

//            debug(event);// print pour debug project

            if(droidInMove == null
                && (event.getActionMasked() == MotionEvent.ACTION_DOWN
                    || event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN))
                droidInMove = selectToMove( x, y);
            else if(droidInMove != null
                    && event.getActionMasked() == MotionEvent.ACTION_MOVE)
                setPositionView(droidInMove, x, y);
            else if(event.getActionMasked() == MotionEvent.ACTION_UP
                    || event.getActionMasked() == MotionEvent.ACTION_POINTER_UP)
                droidInMove = null;
    }
}

