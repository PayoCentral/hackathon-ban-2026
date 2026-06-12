package com.buap.arpavisos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.buap.arpavisos.databinding.FragmentMasBinding;

public class MasFragment extends Fragment {

    private FragmentMasBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentMasBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.customHeader.tvHeaderTitle.setText("Más herramientas.");

        // Perfil option click listener
        binding.cvPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new PerfilFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Click listener for University Calendar option card
        binding.cvCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace fragment with CalendarioFragment, adding to backstack for navigation
                // freedom
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new CalendarioFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Campus Map option click listener
        binding.cvMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new MapaFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Settings option click listener
        binding.cvAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new AjustesFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
