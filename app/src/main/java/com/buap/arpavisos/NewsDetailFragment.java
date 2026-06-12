package com.buap.arpavisos;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.buap.arpavisos.databinding.FragmentNewsDetailBinding;

public class NewsDetailFragment extends Fragment {

    private FragmentNewsDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Read arguments passed from the adapter click
        Bundle args = getArguments();
        if (args != null) {
            String title = args.getString("title", "Noticia");
            String date = args.getString("date", "");
            String desc = args.getString("desc", "");
            String category = args.getString("category", "Noticia");

            binding.customHeader.tvHeaderTitle.setText(title);
            binding.tvNewsDetailDate.setText(date);
            binding.tvNewsDetailDesc.setText(desc);
            binding.tvNewsDetailCategory.setText(category.toUpperCase());

            // Set dynamic images based on category/content
            if (title.contains("Residencia")) {
                binding.imgNewsDetail.setImageResource(R.drawable.art_woodcut);
            } else if (title.contains("Sombra")) {
                binding.imgNewsDetail.setImageResource(R.drawable.art_still_life);
            } else {
                binding.imgNewsDetail.setImageResource(R.drawable.logo_facultad);
            }

            // Adjust category chip styles dynamically
            int chipBgColor;
            int chipTextColor;
            if ("CONVOCATORIA".equalsIgnoreCase(category)) {
                chipBgColor = 0xFFFCE4EC; // Light pink
                chipTextColor = ContextCompat.getColor(requireContext(), R.color.colorSecondary);
            } else if ("EVENTO".equalsIgnoreCase(category)) {
                chipBgColor = 0xFFE8EAF6; // Light blue/indigo
                chipTextColor = ContextCompat.getColor(requireContext(), R.color.colorPrimary);
            } else {
                chipBgColor = 0xFFE0F2F1; // Light teal
                chipTextColor = 0xFF00796B;
            }

            binding.tvNewsDetailCategory.setTextColor(chipTextColor);
            Drawable background = binding.tvNewsDetailCategory.getBackground();
            if (background != null) {
                background.setColorFilter(chipBgColor, PorterDuff.Mode.SRC_IN);
            }
        }

        // Back button navigation (User control & freedom)
        binding.btnNewsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
