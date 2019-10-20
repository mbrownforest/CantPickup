package com.example.cantpickup;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PopUpAnimate {

    public void addViewsAndAnimate(Context context, View view) {
        LinearLayout popUp = (LinearLayout) view;
        final ImageView circleView = new ImageView(context);
        circleView.findViewById(R.id.circle);
        final ImageView sadView = new ImageView(context);
        sadView.findViewById(R.id.sad);
        final ImageView happyView = new ImageView(context);
        happyView.findViewById(R.id.happy);

        circleView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        sadView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        happyView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        popUp.addView(circleView, 0);
        popUp.addView(sadView, 0);
        popUp.addView(happyView, 0);

        Animation fadeIn = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.fade_out);
        Animation rotate = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.rotate);

        happyView.startAnimation(fadeIn);
        happyView.startAnimation(fadeOut);
        circleView.startAnimation(rotate);
    }


}
