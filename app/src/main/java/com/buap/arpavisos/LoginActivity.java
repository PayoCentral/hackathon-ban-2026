package com.buap.arpavisos;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.buap.arpavisos.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Setup View Binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Apply LinearGradient text color shader
        TextView tvTitle = findViewById(R.id.tv_login_title);
        Shader textShader = new LinearGradient(0, 0, 0, tvTitle.getTextSize(),
                new int[]{Color.WHITE, Color.parseColor("#EA3E99")},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        tvTitle.getPaint().setShader(textShader);

        // Login Action listener
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {
        // Reset errors
        binding.tilUsername.setError(null);
        binding.tilPassword.setError(null);

        // Get fields
        String username = binding.etUsername.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        // Validate password
        if (TextUtils.isEmpty(password)) {
            binding.tilPassword.setError(getString(R.string.error_empty_fields));
            focusView = binding.tilPassword;
            cancel = true;
        }

        // Validate username
        if (TextUtils.isEmpty(username)) {
            binding.tilUsername.setError(getString(R.string.error_empty_fields));
            focusView = binding.tilUsername;
            cancel = true;
        }

        if (cancel) {
            // Focus first error field
            focusView.requestFocus();
        } else {
            // Static bypass verification: admin / 1234
            if ("admin".equalsIgnoreCase(username) && "1234".equals(password)) {
                // Redirect to MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close LoginActivity so the user cannot back-navigate here
            } else {
                binding.tilUsername.setError(getString(R.string.error_invalid_credentials));
                binding.tilPassword.setError(getString(R.string.error_invalid_credentials));
                binding.tilUsername.requestFocus();
            }
        }
    }
}
