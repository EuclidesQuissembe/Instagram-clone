package com.ngondo.instagram.common.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.ngondo.instagram.R;

public class CustomDialog extends Dialog {

    private LinearLayout dialogLinearLayout;
    private LinearLayout.LayoutParams layoutParams;

    private TextView titleView;
    private TextView[] textViews;
    private int titleId;


    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);

        layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        dialogLinearLayout = findViewById(R.id.dialog_container);
        titleView = findViewById(R.id.dialog_title);

        layoutParams.setMargins(30, 50, 30, 50);
    }

    @Override
    public void setTitle(int titleId) {
        this.titleId = titleId;
    }

    @Override
    public void show() {
        super.show();

        titleView.setText(titleId);

        for (TextView textView : textViews) {
            dialogLinearLayout.addView(textView, layoutParams);
        }
    }

    private void addButton(final View.OnClickListener listener, @StringRes int... texts) {
        textViews = new TextView[texts.length];
        for (int i = 0; i < texts.length; i++) {
            TextView textView = new TextView(new ContextThemeWrapper(getContext(), R.style.InstaTextViewBaseDialog), null, 0);
            textView.setId(texts[i]);
            textView.setText(texts[i]);
            textView.setOnClickListener((v) -> {
                listener.onClick(v);
                dismiss();
            });
            textViews[i] = textView;
        }
    }

    public static class Builder {

        private final Context context;
        private int titleId;
        private View.OnClickListener listener;
        private int[] texts;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(@StringRes int titleId) {
            this.titleId = titleId;
            return this;
        }

        public Builder addButton(View.OnClickListener listener, @StringRes int... texts) {
            this.listener = listener;
            this.texts = texts;
            return this;
        }

        public CustomDialog build() {
            CustomDialog customDialog = new CustomDialog(context);
            customDialog.setTitle(titleId);
            customDialog.addButton(listener, texts);
            return customDialog;
        }
    }
}
