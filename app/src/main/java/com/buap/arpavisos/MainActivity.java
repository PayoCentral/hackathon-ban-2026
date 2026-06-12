package com.buap.arpavisos;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

        // Handle Floating Action Button click (Comunidad/Home screen)
        binding.fabComunidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ComunidadFragment());
                // Uncheck all items in BottomNavigationView to visually highlight the active FAB
                uncheckAllNavigationItems();
            }
        });

        // Handle BottomNavigationView item selection
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();

                if (itemId == R.id.nav_galeria) {
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

        // Set default home screen (Comunidad) on fresh launch
        if (savedInstanceState == null) {
            loadFragment(new ComunidadFragment());
            uncheckAllNavigationItems();
        }
    }

    /**
     * Unchecks all items in the BottomNavigationView menu.
     */
    private void uncheckAllNavigationItems() {
        int size = binding.bottomNavigation.getMenu().size();
        for (int i = 0; i < size; i++) {
            binding.bottomNavigation.getMenu().getItem(i).setChecked(false);
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
