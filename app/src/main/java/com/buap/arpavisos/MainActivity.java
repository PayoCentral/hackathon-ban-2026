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

        // Handle BottomNavigationView item selection
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

                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                    return true;
                }
                return false;
            }
        });

        // Set default home screen (Comunidad) on fresh launch programmatically
        if (savedInstanceState == null) {
            binding.bottomNavigation.setSelectedItemId(R.id.nav_comunidad);
        }
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
