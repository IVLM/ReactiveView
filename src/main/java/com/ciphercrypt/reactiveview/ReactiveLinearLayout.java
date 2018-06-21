package com.ciphercrypt.reactiveview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by user on 21/06/2018.
 */

public class ReactiveLinearLayout extends LinearLayout implements Reactive {
    public ReactiveLinearLayout(Context context) {
        super(context);
    }

    public ReactiveLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ReactiveLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ReactiveLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void react() {
        for(int i = 0; i<getChildCount(); i++){
            reactAllChild(getChildAt(i));
        }
    }

    protected void reactAllChild(View v){
        if(v instanceof Reactive){
            ((Reactive)v).react();
        }

        if(v instanceof ViewGroup){
            for(int i = 0; i < ((ViewGroup) v).getChildCount(); i++){
                reactAllChild(((ViewGroup) v).getChildAt(i));
            }
        }
    }
}
