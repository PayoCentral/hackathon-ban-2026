package com.buap.arpavisos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.buap.arpavisos.databinding.FragmentGaleriaBinding;
import java.util.ArrayList;
import java.util.List;

public class GaleriaFragment extends Fragment {

    private FragmentGaleriaBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentGaleriaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.customHeader.tvHeaderTitle.setText("Galería ARPA");
        binding.customHeader.tvHeaderSubtitle.setVisibility(View.VISIBLE);
        binding.customHeader.tvHeaderSubtitle
                .setText("Explora y comparte las creaciones artísticas y exposiciones estudiantiles de la comunidad. ");

        binding.rvGalleryFeed.setLayoutManager(new LinearLayoutManager(getContext()));

        // Populate mock social media posts (at least 4 realistic art posts)
        List<PostItem> posts = new ArrayList<>();
        posts.add(new PostItem(
                "Taller de Pintura 3",
                "Naturaleza Muerta - Ejercicio de bodegón geométrico y estudio de color. Óleo sobre lienzo, 2026.",
                128,
                false,
                "Hace 2 horas",
                R.drawable.ic_person,
                R.drawable.art_still_life));
        posts.add(new PostItem(
                "Gráfica ARPA",
                "Grabado Experimental - Composición abstracta en xilografía y prensa calcográfica.",
                94,
                true,
                "Hace 5 horas",
                R.drawable.ic_person,
                R.drawable.art_woodcut));
        posts.add(new PostItem(
                "Colectivo Audiovisual",
                "Diseño de paletas cromáticas inspiradas en nuestra facultad ARPA.",
                215,
                false,
                "Ayer",
                R.drawable.ic_person,
                R.drawable.logo_facultad));
        posts.add(new PostItem(
                "Escultura ARPA 1",
                "Composición volumétrica de naturaleza abstracta.",
                73,
                false,
                "Hace 3 días",
                R.drawable.ic_person,
                R.drawable.art_still_life));

        // Setup Adapter
        GalleryAdapter adapter = new GalleryAdapter(posts);
        binding.rvGalleryFeed.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
