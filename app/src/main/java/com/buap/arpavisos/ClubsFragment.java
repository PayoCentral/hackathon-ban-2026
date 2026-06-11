package com.buap.arpavisos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.buap.arpavisos.databinding.FragmentClubsBinding;
import java.util.ArrayList;
import java.util.List;

public class ClubsFragment extends Fragment {

    private FragmentClubsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentClubsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup LayoutManager
        binding.rvClubs.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create mock clubs list
        List<ClubItem> clubs = new ArrayList<>();
        clubs.add(new ClubItem(
                "Taller de Pintura Óleo",
                "Artes Plásticas • Sabados 10:00 hrs",
                "Activa",
                "Aprende las técnicas tradicionales del óleo, mezcla de pigmentos y teoría del color. Dirigido a principiantes e intermedios.",
                "Sábados de 10:00 AM a 01:00 PM"
        ));
        clubs.add(new ClubItem(
                "Club de Cine Debate",
                "Artes Audiovisuales • Miércoles 14:00 hrs",
                "En curso",
                "Proyección y análisis crítico de obras del cine independiente, experimental y clásicos internacionales. Fomentando la discusión artística.",
                "Miércoles de 02:00 PM a 04:00 PM"
        ));
        clubs.add(new ClubItem(
                "Colectivo de Fotografía Urbana",
                "Fotografía • Jueves 16:00 hrs",
                "Activa",
                "Salidas de campo para capturar la esencia de la arquitectura y vida citadina. Crítica técnica y edición digital en laboratorio.",
                "Jueves de 04:00 PM a 06:00 PM"
        ));
        clubs.add(new ClubItem(
                "Taller de Grabado y Xilografía",
                "Artes Plásticas • Martes 11:00 hrs",
                "Pasada",
                "Introducción al linóleo y xilografía en madera. Uso de gubias y prensa calcográfica. Ciclo Primavera finalizado.",
                "Martes de 11:00 AM a 01:00 PM"
        ));

        // Bind adapter
        ClubsAdapter adapter = new ClubsAdapter(clubs, new ClubsAdapter.OnClubClickListener() {
            @Override
            public void onClubClick(ClubItem club) {
                // Navigate to ClubDetailFragment passing details
                navigateToDetail(club);
            }
        });
        binding.rvClubs.setAdapter(adapter);
    }

    private void navigateToDetail(ClubItem club) {
        // Build arguments
        Bundle args = new Bundle();
        args.putString("title", club.getTitle());
        args.putString("category", club.getCategory());
        args.putString("status", club.getStatus());
        args.putString("desc", club.getDesc());
        args.putString("schedule", club.getSchedule());

        ClubDetailFragment detailFragment = new ClubDetailFragment();
        detailFragment.setArguments(args);

        // Perform fragment transaction with backstack history
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null) // Ensures user can go back to listing
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
