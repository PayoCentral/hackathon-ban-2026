package com.buap.arpavisos;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.buap.arpavisos.databinding.FragmentComunidadBinding;
import java.util.ArrayList;
import java.util.List;

public class ComunidadFragment extends Fragment {

    private FragmentComunidadBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentComunidadBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup LayoutManager
        binding.rvFeed.setLayoutManager(new LinearLayoutManager(getContext()));

        // Show progress bar to represent loading state (UX feedback)
        binding.pbLoading.setVisibility(View.VISIBLE);
        binding.rvFeed.setVisibility(View.GONE);

        // Simulate network fetch with a brief delay
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (binding == null) return;

                // Create mock news list
                List<NewsItem> news = new ArrayList<>();
                news.add(new NewsItem(
                        "Exposición Colectiva 'Luz y Sombra'",
                        "12 Jun, 2026",
                        "Los alumnos de la Licenciatura en Artes Visuales presentan su proyecto de fin de curso. Galería 1 del Edificio Central.",
                        "Evento"
                ));
                news.add(new NewsItem(
                        "Convocatoria: Festival de Cortometrajes ARPA",
                        "15 Jun, 2026",
                        "Abierta la recepción de propuestas cinematográficas de temática libre. Envía tu cortometraje antes del 30 de junio de 2026.",
                        "Convocatoria"
                ));
                news.add(new NewsItem(
                        "Clase Magistral: Técnicas de Grabado Contemporáneo",
                        "18 Jun, 2026",
                        "Impartida por el renombrado artista invitado Mtro. Alberto Domínguez. Registro gratuito en Secretaría Académica.",
                        "Evento"
                ));
                news.add(new NewsItem(
                        "Requisitos para Reinscripción Otoño 2026",
                        "20 Jun, 2026",
                        "Conoce las fechas de reinscripción, costos de matrícula y las materias disponibles para este nuevo periodo escolar.",
                        "Noticia"
                ));

                // Bind adapter
                NewsAdapter adapter = new NewsAdapter(news);
                binding.rvFeed.setAdapter(adapter);

                // Hide progress, show feed
                binding.pbLoading.setVisibility(View.GONE);
                binding.rvFeed.setVisibility(View.VISIBLE);
            }
        }, 600);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
