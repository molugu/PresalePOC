package com.incresol.bemolpresalepoc.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.incresol.bemolpresalepoc.Activities.ImageZoomInActivity;
import com.incresol.bemolpresalepoc.Adapters.SliderPagerAdapter;
import com.incresol.bemolpresalepoc.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import uk.co.senab.photoview.PhotoViewAttacher;

public class Stock_Product_Tab extends Fragment {

    ViewPager vp_slider;
    LinearLayout ll_dots;
    SliderPagerAdapter sliderPagerAdapter;
    ArrayList<String> slider_image_list;
    private TextView[] dots;
    int page_position = 0;
    Activity activity;
    ImageView zoom_button,rightArrow,leftArrow,image_to_zoom;
    PhotoViewAttacher photoViewAttacher;
    Dialog dialog;
    int slide_no=0;


    //start of zoom functionality variables
        private static final String TAG = "Touch";
        @SuppressWarnings("unused")
        private static final float MIN_ZOOM = 1f,MAX_ZOOM = 1f;

        // These matrices will be used to scale points of the image
        Matrix matrix = new Matrix();
        Matrix savedMatrix = new Matrix();

        // The 3 states (events) which the user is trying to perform
        static final int NONE = 0;
        static final int DRAG = 1;
        static final int ZOOM = 2;
        int mode = NONE;

        // these PointF objects are used to record the point(s) the user is touching
        PointF start = new PointF();
        PointF mid = new PointF();
        float oldDist = 1f;

    // end of zoom functionality variables

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stock_product_tab, container, false);
        activity = getActivity();
        // method for initialization
        init(view);

        //method for adding indicators
        addBottomDots(0);

        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == slider_image_list.size()) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                vp_slider.setCurrentItem(page_position, true);
            }
        };

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 100, 3000);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void init(View view){
        vp_slider = (ViewPager) view.findViewById(R.id.vp_slider);
        ll_dots = (LinearLayout) view.findViewById(R.id.ll_dots);

        slider_image_list = new ArrayList<>();
        slider_image_list.add("https://www.sugarsync.com/images/samsung-led-5300.png");
        slider_image_list.add("http://wirelesstradersinc.com/wp-content/uploads/2016/02/LED-TV-From-Samsung.png");
        slider_image_list.add("http://samsung-tvs.co.uk/wp-content/uploads/2012/01/samsung-ue55d8000-televisions.png");
        slider_image_list.add("http://wirelesstradersinc.com/wp-content/uploads/2016/02/LED-TV-From-Samsung.png");
        slider_image_list.add("https://www.sugarsync.com/images/samsung-led-5300.png");
        slider_image_list.add("http://wirelesstradersinc.com/wp-content/uploads/2016/02/LED-TV-From-Samsung.png");
        slider_image_list.add("http://samsung-tvs.co.uk/wp-content/uploads/2012/01/samsung-ue55d8000-televisions.png");
        slider_image_list.add("http://wirelesstradersinc.com/wp-content/uploads/2016/02/LED-TV-From-Samsung.png");


        sliderPagerAdapter = new SliderPagerAdapter(getActivity(), slider_image_list);
        vp_slider.setAdapter(sliderPagerAdapter);

        vp_slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        zoom_button = (ImageView) view.findViewById(R.id.zoom_button);

        zoom_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialog = new Dialog(getActivity(),android.R.style.Theme_Light_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.activity_image_zoom_in);

                image_to_zoom = (ImageView) dialog.findViewById(R.id.image_to_zoom);
                image_to_zoom.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        ImageView imageView = (ImageView) view;
                        imageView.setScaleType(ImageView.ScaleType.MATRIX);
                        float scale;

                        DisplayMetrics displaymetrics = new DisplayMetrics();
                        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                        int screenHeight = displaymetrics.heightPixels;
                        int screenWidth = displaymetrics.widthPixels;

                        // Handle touch events here...

                        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK)
                        {
                            case MotionEvent.ACTION_DOWN:   // first finger down only
                                savedMatrix.set(matrix);
                                start.set(motionEvent.getX(), motionEvent.getY());
                                Log.d(TAG, "mode=DRAG"); // write to LogCat
                                mode = DRAG;
                                break;

                            case MotionEvent.ACTION_UP: // first finger lifted

                            case MotionEvent.ACTION_POINTER_UP: // second finger lifted

                                mode = NONE;
                                Log.d(TAG, "mode=NONE");
                                break;

                            case MotionEvent.ACTION_POINTER_DOWN: // first and second finger down

                                oldDist = spacing(motionEvent);
                                Log.d(TAG, "oldDist=" + oldDist);
                                if (oldDist > 5f) {
                                    savedMatrix.set(matrix);
                                    midPoint(mid, motionEvent);
                                    mode = ZOOM;
                                    Log.d(TAG, "mode=ZOOM");
                                }
                                break;

                            case MotionEvent.ACTION_MOVE:

                                if (mode == DRAG)
                                {
                                    matrix.set(savedMatrix);
                                    matrix.postTranslate(motionEvent.getX() - start.x, motionEvent.getY() - start.y); // create the transformation in the matrix  of points

                                }
                                else if (mode == ZOOM)
                                {
                                    float[] f = new float[9];
                                    // pinch zooming
                                    float newDist = spacing(motionEvent);
                                    Log.d(TAG, "newDist=" + newDist);
                                    if (newDist > 5f)
                                    {
                                        matrix.set(savedMatrix);
                                        scale = newDist / oldDist; // setting the scaling of the
                                        // matrix...if scale > 1 means
                                        // zoom in...if scale < 1 means
                                        // zoom out
                                        matrix.postScale(scale, scale, mid.x, mid.y);
                                    }

                                    matrix.getValues(f);
                                    float scaleX = f[Matrix.MSCALE_X];
                                    float scaleY = f[Matrix.MSCALE_Y];

                                    if(scaleX <= 0.7f) {
                                        matrix.postScale((0.7f)/scaleX, (0.7f)/scaleY, mid.x, mid.y);
                                    } else if(scaleX >= 2.5f) {
                                        matrix.postScale((2.5f)/scaleX, (2.5f)/scaleY, mid.x, mid.y);
                                    }

                                }
                                break;
                        }

                        imageView.setImageMatrix(matrix); // display the transformation on screen

                        return true; // indicate event was handled
                    }
                });

                Glide.with(getActivity()).load(slider_image_list.get(0).toString())
                        .placeholder(R.drawable.ic_action_download)
                        .error(R.drawable.ic_action_error)
                        .into(image_to_zoom);
               /* photoViewAttacher = new PhotoViewAttacher(image_to_zoom);
                photoViewAttacher.setMinimumScale(0.2f);
                photoViewAttacher.setMaximumScale(0.1f);
                photoViewAttacher.update();*/

                leftArrow = (ImageView) dialog.findViewById(R.id.leftArrow);
                rightArrow = (ImageView) dialog.findViewById(R.id.rightArrow);

                leftArrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(slide_no>0 ) {
                            slide_no = slide_no - 1;
                        }
                        if( slide_no>=0 &&slide_no<slider_image_list.size()) {
                            Glide.with(getActivity()).load(slider_image_list.get(slide_no).toString())
                                    .placeholder(R.drawable.ic_action_download)
                                    .error(R.drawable.ic_action_error)
                                    .into(image_to_zoom);
                        }
                    }
                });

                rightArrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(slide_no>=0&&slide_no<slider_image_list.size()-1) {
                            slide_no = slide_no + 1;
                        }
                        if(slide_no!=0 && slide_no>=0 &&slide_no<slider_image_list.size()) {
                            Glide.with(getActivity()).load(slider_image_list.get(slide_no).toString())
                                    .placeholder(R.drawable.ic_action_download)
                                    .error(R.drawable.ic_action_error)
                                    .into(image_to_zoom);
                        }

                    }
                });
                dialog.show();

            }
        });





    }

  /*  @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        ImageView view = (ImageView) v;
        view.setScaleType(ImageView.ScaleType.MATRIX);
        float scale;

        // Handle touch events here...

        switch (event.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:   // first finger down only
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                Log.d(TAG, "mode=DRAG"); // write to LogCat
                mode = DRAG;
                break;

            case MotionEvent.ACTION_UP: // first finger lifted

            case MotionEvent.ACTION_POINTER_UP: // second finger lifted

                mode = NONE;
                Log.d(TAG, "mode=NONE");
                break;

            case MotionEvent.ACTION_POINTER_DOWN: // first and second finger down

                oldDist = spacing(event);
                Log.d(TAG, "oldDist=" + oldDist);
                if (oldDist > 5f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                    Log.d(TAG, "mode=ZOOM");
                }
                break;

            case MotionEvent.ACTION_MOVE:

                if (mode == DRAG)
                {
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y); // create the transformation in the matrix  of points
                }
                else if (mode == ZOOM)
                {
                    // pinch zooming
                    float newDist = spacing(event);
                    Log.d(TAG, "newDist=" + newDist);
                    if (newDist > 5f)
                    {
                        matrix.set(savedMatrix);
                        scale = newDist / oldDist; // setting the scaling of the
                        // matrix...if scale > 1 means
                        // zoom in...if scale < 1 means
                        // zoom out
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;
        }

        view.setImageMatrix(matrix); // display the transformation on screen

        return true; // indicate event was handled
    }
*/


    private float spacing(MotionEvent event)
    {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }



    private void midPoint(PointF point, MotionEvent event)
    {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    @SuppressWarnings("deprecation")
    private void addBottomDots(int currentPage) {
        dots = new TextView[slider_image_list.size()];

        ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(activity);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.GRAY);
            ll_dots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.BLUE);
    }

}
