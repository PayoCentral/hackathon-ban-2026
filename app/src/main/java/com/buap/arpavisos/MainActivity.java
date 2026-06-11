package com.buap.arpavisos;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.buap.arpavisos.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Load ComunidadFragment as the default home screen
        if (savedInstanceState == null) {
            loadFragment(new ComunidadFragment());
        }

        // Handle navigation item clicks
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();

                if (itemId == R.id.nav_comunidad) {
                    selectedFragment = new ComunidadFragment();
                } else if (itemId == R.id.nav_galeria) {
                    selectedFragment = new GaleriaFragment();
                } else if (itemId == R.id.nav_clubs) {
                    selectedFragment = new ClubsFragment();
                } else if (itemId == R.id.nav_genero) {
                    selectedFragment = new UnidadGeneroFragment();
                } else if (itemId == R.id.nav_mas) {
                    selectedFragment = new MasFragment();
                }

                return loadFragment(selectedFragment);
            }
        });
    }

    /**
     * Helper method to load and swap fragments into the FrameLayout container.
     */
    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
