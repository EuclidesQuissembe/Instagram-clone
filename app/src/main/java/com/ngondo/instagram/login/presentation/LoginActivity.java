package com.ngondo.instagram.login.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.ngondo.instagram.R;
import com.ngondo.instagram.common.view.LoadingButton;

public class LoginActivity extends AppCompatActivity {

    private LoadingButton buttonEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonEnter = findViewById(R.id.login_button_enter);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));

        EditText editTextEmail = findViewById(R.id.login_edit_text_email);
        EditText editTextPassword = findViewById(R.id.login_edit_text_password);

        editTextEmail.addTextChangedListener(watcher);
        editTextPassword.addTextChangedListener(watcher);

        buttonEnter.setOnClickListener(v -> {
            buttonEnter.showProgress(true);

            new Handler().postDelayed(() -> {
                buttonEnter.showProgress(false);
                TextInputLayout inputLayoutEmail = findViewById(R.id.login_edit_text_email_input);
                inputLayoutEmail.setError("Endereço de e-mail inválido");
                editTextEmail.setBackground(ContextCompat.getDrawable(this, R.drawable.edit_text_background_error));

                TextInputLayout inputLayoutPassword = findViewById(R.id.login_edit_text_password_input);
                inputLayoutPassword.setError("Senha incorrecta");
                editTextPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.edit_text_background_error));
            }, 4000);
        });
    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            buttonEnter.setEnabled(!s.toString().isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };
}