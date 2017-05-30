package com.incresol.bemolpresalepoc.Activities;

import android.content.Context;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.incresol.bemolpresalepoc.R;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;


public class ImageZoomInActivity extends AppCompatActivity {

    ImageView image_to_zoom;
    PhotoViewAttacher photoViewAttacher;
    ArrayList<String> slider_image_list;


    ScaleGestureDetector scaleGestureDetector;
    Matrix matrix = new Matrix();
    Float scale = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_zoom_in);

        slider_image_list = new ArrayList<>();
        slider_image_list = getIntent().getStringArrayListExtra("slider_image_list");

        image_to_zoom = (ImageView) findViewById(R.id.image_to_zoom);
        Glide.with(this).load(slider_image_list.get(0))
                .placeholder(R.drawable.ic_action_download)
                .error(R.drawable.ic_action_error)
                .into(image_to_zoom);
      // scaleGestureDetector = new ScaleGestureDetector(this, new MySimpleOnScaleGestureListener());
        photoViewAttacher = new PhotoViewAttacher(image_to_zoom);
        photoViewAttacher.setMinimumScale(1f);
        photoViewAttacher.setMaximumScale(5f);
        photoViewAttacher.update();

    }

  /*  @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }

    private class MySimpleOnScaleGestureListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener{

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

           scale = scale*detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5f));
            matrix.setScale(scale,scale);
            image_to_zoom.setImageMatrix(matrix);
            return true;
        }
    }
*/

    private String getScreenResolution()
    {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        return "{" + width + "," + height + "}";
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
