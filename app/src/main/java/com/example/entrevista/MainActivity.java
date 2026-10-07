package com.example.entrevista;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.EditText;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText phone, identity, complement;
    private final ActivityResultLauncher<String[]> permission = registerForActivityResult(
            new ActivityResultContracts.RequestMultiplePermissions(), results -> {
                boolean granted = Boolean.TRUE.equals(results.get(Manifest.permission.ACCESS_COARSE_LOCATION))
                        || Boolean.TRUE.equals(results.get(Manifest.permission.ACCESS_FINE_LOCATION));
                if (granted) submitDemo();
                else {
                    boolean blocked = !shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
                            && !shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
                            && getPreferences(MODE_PRIVATE).getBoolean("locationRequested", false);
                    showLocationPrompt(
                            "Necesitamos tu permiso para continuar con la creación de tu Bille.",
                            "Intentar de nuevo", this::requestPermission, blocked);
                }
            });
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });
        phone = findViewById(R.id.phone);
        identity = findViewById(R.id.identity);
        complement = findViewById(R.id.complement);
        phone.clearFocus();
        identity.clearFocus();
        complement.clearFocus();
        findViewById(R.id.main).requestFocus();
        findViewById(R.id.continueButton).setOnClickListener(v -> { if (validate()) checkPermission(); });
    }
    private boolean validate() {
        phone.setError(null); identity.setError(null); complement.setError(null);
        boolean valid = true;
        if (!InputValidator.isComplementValid(complement.getText().toString())) {
            complement.setError("Usa hasta 2 letras o números, sin espacios"); complement.requestFocus(); valid = false;
        }
        if (!InputValidator.isIdentityValid(identity.getText().toString())) {
            identity.setError("Ingresa entre 1 y 10 dígitos"); identity.requestFocus(); valid = false;
        }
        if (!InputValidator.isPhoneValid(phone.getText().toString())) {
            phone.setError("Ingresa entre 1 y 8 dígitos"); phone.requestFocus(); valid = false;
        }
        return valid;
    }
    private void checkPermission() {
        boolean hasCoarse = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
        boolean hasFine = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
        if (hasCoarse || hasFine) { submitDemo(); return; }
        showLocationPrompt("Para continuar con la creación de tu Bille, necesitamos permiso para acceder a la ubicación de este dispositivo.",
                "Continuar", this::requestPermission, false);
    }
    private void requestPermission() {
        getPreferences(MODE_PRIVATE).edit().putBoolean("locationRequested", true).apply();
        permission.launch(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION});
    }
    private void submitDemo() {
        // Revalidar y comprobar el permiso inmediatamente antes del punto de conexión.
        boolean hasCoarse = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
        boolean hasFine = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
        if (!validate() || (!hasCoarse && !hasFine)) return;
        if (DemoService.submitInformation(phone.getText().toString(), identity.getText().toString(),
                complement.getText().toString())) startActivity(new Intent(this, AuthenticationActivity.class));
    }
    private void showLocationPrompt(String message, String actionLabel, Runnable action, boolean offerSettings) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.dialog_location);
        android.widget.TextView messageView = dialog.findViewById(R.id.locationMessage);
        android.widget.Button continueButton = dialog.findViewById(R.id.enableLocationButton);
        android.view.View button = dialog.findViewById(R.id.enableLocationButton);
        if (messageView != null) messageView.setText(message);
        if (continueButton != null) continueButton.setText(actionLabel);
        if (button != null) button.setOnClickListener(v -> { dialog.dismiss(); action.run(); });
        android.widget.TextView settingsButton = dialog.findViewById(R.id.openLocationSettings);
        if (settingsButton != null) {
            settingsButton.setVisibility(offerSettings ? android.view.View.VISIBLE : android.view.View.GONE);
            settingsButton.setOnClickListener(v -> {
                dialog.dismiss();
                startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        android.net.Uri.parse("package:" + getPackageName())));
            });
        }
        dialog.show();
        android.view.View sheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (sheet != null) sheet.setBackgroundColor(android.graphics.Color.TRANSPARENT);
    }
}

