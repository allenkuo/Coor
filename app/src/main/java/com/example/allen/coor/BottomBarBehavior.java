package com.example.allen.coor;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Allen on 2017/5/7.
 *
 */

public class BottomBarBehavior extends CoordinatorLayout.Behavior<View> {

    public static final String TAG = BottomBarBehavior.class.getSimpleName();
//    private int deltaY;

    public BottomBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
//        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
//    }

//    @Override
//    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
//        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
//        deltaY += dy;
//        if (deltaY < 0) {
//            deltaY = 0;
//        } else if (deltaY > child.getHeight()) {
//            deltaY = child.getHeight();
//        }
//        child.setTranslationY(deltaY);
//    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.abs(dependency.getTop());
        child.setTranslationY(translationY);
        return true;
    }
}
