package com.ngondo.instagram.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.ngondo.instagram.R;

public class LoadingButton extends FrameLayout {

    private AppCompatButton button;
    private ProgressBar progressBar;
    private String text;

    public LoadingButton(@NonNull Context context) {
        super(context);
        setup(context, null);
    }

    public LoadingButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    public LoadingButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);
    }

    private void setup(Context context, AttributeSet  attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.button_loading, this, true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingButton, 0, 0);

        text = typedArray.getString(R.styleable.LoadingButton_text);

        typedArray.recycle();

        // Getting button
        button = (AppCompatButton) getChildAt(0);
        button.setText(text);
        button.setEnabled(false);

        // Getting progressbar
        progressBar = (ProgressBar) getChildAt(1);

        progressBar.getIndeterminateDrawable().setColorFilter(
                ContextCompat.getColor(context, android.R.color.white), PorterDuff.Mode.SRC_IN
        );
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
       button.setOnClickListener(l);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        button.setEnabled(enabled);
    }

    public void showProgress(boolean enabled) {
        progressBar.setVisibility(enabled ? VISIBLE : GONE);
        button.setText(enabled ? "" : text);
    }
}
