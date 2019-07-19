package com.happening.aeven;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.happening.aeven.model.PointLocation;
import com.happening.aeven.type.DirectionToNextPoint;

public class MainContent extends Fragment implements GestureDetector.OnGestureListener {

    public static final int SWIPE_TRESHOLD = 100;
    public static final int SWIPE_VELOCITY_TRESHOLD = 100;
    private GestureDetector gestureDetector;
    private FragmentActivity fragmentBelongActivity;
    private MapsContent mapFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gestureDetector = new GestureDetector(this);
        View inflate = inflater.inflate(R.layout.content_main, container, false);
        fragmentBelongActivity = getActivity();
        inflate.setOnTouchListener((view, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });
        FragmentManager fragmentManager = fragmentBelongActivity.getSupportFragmentManager();
        mapFragment = (MapsContent) fragmentManager.findFragmentById(R.id.map_fragment);

        return inflate;
    }

    /* Gestures listener*/
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result = false;
        float diffY = moveEvent.getY() - downEvent.getY();
        float diffX = moveEvent.getX() - downEvent.getX();

        //movment accrross Y or X
        if(Math.abs(diffX) > Math.abs(diffY)) {
            if(Math.abs(diffX) > SWIPE_TRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_TRESHOLD) {
                if (diffX> 0) {
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
                result = true;
            }
        } else {
            if(Math.abs(diffY) > SWIPE_TRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_TRESHOLD) {
                if (diffY> 0) {
                    onSwipeBottom();
                } else {
                    onSwipeTop();
                }
                result = true;
            }
        }
        return result;
    }



    private void onSwipeTop() {
        mapFragment.moveToNextPoint(goToDirectedPoint(DirectionToNextPoint.North));
       // mapFragment.moveToNextPoint(52.241478, 21.012418, IconsType.MAKI_ICON_MONUMENT);
        Toast.makeText(fragmentBelongActivity, "Swipe top", Toast.LENGTH_LONG).show();
    }

    private void onSwipeBottom() {
        mapFragment.moveToNextPoint(goToDirectedPoint(DirectionToNextPoint.South));
       // mapFragment.moveToNextPoint(52.218050, 21.014459, IconsType.MAKI_ICON_LIBRARY);
        Toast.makeText(fragmentBelongActivity, "Swipe bottom", Toast.LENGTH_LONG).show();
    }

    private void onSwipeLeft() {
        mapFragment.moveToNextPoint(goToDirectedPoint(DirectionToNextPoint.West));
       // mapFragment.moveToNextPoint(52.231464, 20.982202, IconsType.MAKI_ICON_MUSEUM);
        Toast.makeText(fragmentBelongActivity, "Swipe left", Toast.LENGTH_LONG).show();
    }

    private void onSwipeRight() {
        mapFragment.moveToNextPoint(goToDirectedPoint(DirectionToNextPoint.East));
        // mapFragment.moveToNextPoint(52.239290, 21.047302, IconsType.MAKI_ICON_VOLLEYBALL);
        Toast.makeText(fragmentBelongActivity, "Swipe right", Toast.LENGTH_LONG).show();
    }

    private PointLocation goToDirectedPoint(DirectionToNextPoint south) {
        return mapFragment.getPointLocation()
                .getNearestPoints()
                .stream()
                .filter(nearestPoint -> south.equals(nearestPoint.getDirectionToNextPoint()))
                .findFirst()
                .orElse(mapFragment.getPointLocation());
    }
}
