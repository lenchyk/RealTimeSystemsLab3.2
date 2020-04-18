package com.example.perceptron;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;


public class Popup {
    public void showPopupWindow(final View view, long timeEx){
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up, null);

        int width = ConstraintLayout.LayoutParams.MATCH_PARENT;
        int height = ConstraintLayout.LayoutParams.MATCH_PARENT;

        boolean focusable = true;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        TextView popup = popupView.findViewById(R.id.time);
        popup.setText(String.format("Time of execution of perceptron: \n %d \n nanoseconds", timeEx));


    }
}
