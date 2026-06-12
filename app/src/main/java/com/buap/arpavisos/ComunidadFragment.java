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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentComunidadBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.customHeader.tvHeaderTitle.setText("Comunidad ARPA");
        binding.customHeader.tvHeaderSubtitle.setVisibility(View.VISIBLE);
        binding.customHeader.tvHeaderSubtitle
                .setText("Noticias, convocatorias y eventos artísticos de nuestra facultad.");

        // Setup LayoutManagers (rvEvents is horizontal, rvFeed is vertical)
        binding.rvEvents.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvFeed.setLayoutManager(new LinearLayoutManager(getContext()));

        // Show progress bar
        binding.pbLoading.setVisibility(View.VISIBLE);
        binding.nestedScrollView.setVisibility(View.GONE);

        // Fetch mock data with minor delay for system status visibility
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (binding == null)
                    return;

                // 1. Featured Events Carousel Data
                List<NewsItem> events = new ArrayList<>();
                events.add(new NewsItem(
                        "Muestra de Fin de Semestre",
                        "Domingo 14 de Junio, 2026",
                        "Lugar: Explanada de la Facultad (10:00 - 18:00 hrs)",
                        "Destacado",
                        R.drawable.event_muestra));
                events.add(new NewsItem(
                        "Clase Magistral: Grabado",
                        "Jueves 18 de Junio, 2026",
                        "Lugar: Taller de Estampado (11:00 hrs)",
                        "Talleres",
                        R.drawable.event_masterclass));
                events.add(new NewsItem(
                        "Festival Cortos ARPA",
                        "Lunes 22 de Junio, 2026",
                        "Lugar: Auditorio Principal (16:00 hrs)",
                        "Cine",
                        R.drawable.event_cine));

                EventCarouselAdapter carouselAdapter = new EventCarouselAdapter(events);
                binding.rvEvents.setAdapter(carouselAdapter);

                // 2. News and Announcements Data
                List<NewsItem> news = new ArrayList<>();
                news.add(new NewsItem(
                        "Convocatoria: Residencia Artística",
                        "Publicado: 10 de Junio, 2026",
                        "Oportunidad de realizar una estancia de creación artística en el extranjero. Consulta las bases en Secretaría de Extensión Cultural.",
                        "Convocatoria"));
                news.add(new NewsItem(
                        "Exposición Colectiva 'Luz y Sombra'",
                        "Publicado: 08 de Junio, 2026",
                        "Inauguración de la galería con proyectos destacados de fin de curso de la Licenciatura en Artes Visuales.",
                        "Evento"));
                news.add(new NewsItem(
                        "Reinscripciones Periodo Otoño 2026",
                        "Publicado: 05 de Junio, 2026",
                        "Se han publicado las guías de materias y horarios autorizados para las inscripciones correspondientes al ciclo Otoño 2026.",
                        "Noticia"));

                // Bind adapter with click callback for navigation expansion
                NewsAdapter newsAdapter = new NewsAdapter(news, new NewsAdapter.OnNewsClickListener() {
                    @Override
                    public void onNewsClick(NewsItem newsItem) {
                        Bundle args = new Bundle();
                        args.putString("title", newsItem.getTitle());
                        args.putString("date", newsItem.getDate());
                        args.putString("desc", newsItem.getDesc());
                        args.putString("category", newsItem.getCategory());

                        NewsDetailFragment detailFragment = new NewsDetailFragment();
                        detailFragment.setArguments(args);

                        getParentFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, detailFragment)
                                .addToBackStack(null) // Enables user navigation freedom to return
                                .commit();
                    }
                });
                binding.rvFeed.setAdapter(newsAdapter);

                // Hide progress, show scroll
                binding.pbLoading.setVisibility(View.GONE);
                binding.nestedScrollView.setVisibility(View.VISIBLE);
            }
        }, 500);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
