package com.buap.arpavisos;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.buap.arpavisos.databinding.ActivityMainBinding;
import com.nafis.bottomnavigation.NafisBottomNavigation;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private static final int ID_GALLERY = 1;
    private static final int ID_CLUBS = 2;
    private static final int ID_COMMUNITY = 3;
    private static final int ID_GENDER = 4;
    private static final int ID_MORE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configure Nafis Bottom Navigation items in strict order
        binding.bottomNavigation.add(new NafisBottomNavigation.Model(ID_GALLERY, R.drawable.ic_galeria));
        binding.bottomNavigation.add(new NafisBottomNavigation.Model(ID_CLUBS, R.drawable.ic_clubs));
        binding.bottomNavigation.add(new NafisBottomNavigation.Model(ID_COMMUNITY, R.drawable.ic_comunidad));
        binding.bottomNavigation.add(new NafisBottomNavigation.Model(ID_GENDER, R.drawable.ic_genero));
        binding.bottomNavigation.add(new NafisBottomNavigation.Model(ID_MORE, R.drawable.ic_mas));

        // Click listener for Nafis Bottom Navigation item clicks
        binding.bottomNavigation.setOnClickMenuListener(new Function1<NafisBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(NafisBottomNavigation.Model item) {
                // Click registered
                return Unit.INSTANCE;
            }
        });

        // Show listener to load and swap the fragments when the section is shown/animated
        binding.bottomNavigation.setOnShowListener(new Function1<NafisBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(NafisBottomNavigation.Model item) {
                Fragment selectedFragment = null;
                int itemId = item.getId();

                if (itemId == ID_GALLERY) {
                    selectedFragment = new GaleriaFragment();
                } else if (itemId == ID_CLUBS) {
                    selectedFragment = new ClubsFragment();
                } else if (itemId == ID_COMMUNITY) {
                    selectedFragment = new ComunidadFragment();
                } else if (itemId == ID_GENDER) {
                    selectedFragment = new UnidadGeneroFragment();
                } else if (itemId == ID_MORE) {
                    selectedFragment = new MasFragment();
                }

                loadFragment(selectedFragment);
                return Unit.INSTANCE;
            }
        });

        // Select the default section (Comunidad) on first start
        if (savedInstanceState == null) {
            binding.bottomNavigation.show(ID_COMMUNITY, true);
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
