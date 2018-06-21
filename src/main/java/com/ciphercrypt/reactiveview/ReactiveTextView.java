package com.ciphercrypt.reactiveview;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.Locale;

/**
 * Created by user on 18/06/2018.
 */

public class ReactiveTextView extends android.support.v7.widget.AppCompatTextView implements Reactive{
    protected int alternativeStringRef = 0;
    public ReactiveTextView(Context context) {
        super(context);
        init(null);
    }

    public ReactiveTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ReactiveTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attributeSet){
        if(attributeSet != null){
            TypedArray ta = getContext().obtainStyledAttributes(attributeSet, R.styleable.ReactiveTextView, 0, 0);
            try{
                alternativeStringRef = ta.getResourceId(R.styleable.ReactiveTextView_alternativeString, 0);
            }finally {
                ta.recycle();
            }
        }
    }

    @Override
    public void react() {
        if(alternativeStringRef != 0){
            Configuration configuration = getResources().getConfiguration();
            configuration.setLocale(Locale.getDefault());
            setText(getContext().createConfigurationContext(configuration)
                    .getResources().getString(alternativeStringRef));
        }
    }
}
