package com.buap.arpavisos;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.buap.arpavisos.databinding.FragmentClubDetailBinding;

public class ClubDetailFragment extends Fragment {

    private FragmentClubDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentClubDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Read arguments
        Bundle args = getArguments();
        if (args != null) {
            String title = args.getString("title", "Club");
            String category = args.getString("category", "");
            String status = args.getString("status", "Activa");
            String desc = args.getString("desc", "");
            String schedule = args.getString("schedule", "");

            binding.tvDetailTitle.setText(title);
            binding.tvDetailCategory.setText(category);
            binding.tvDetailStatus.setText(status);
            binding.tvDetailDesc.setText(desc);
            binding.tvDetailSchedule.setText("Horario: " + schedule);

            // Dynamic status chip color mapping
            int statusColor;
            if ("Activa".equalsIgnoreCase(status)) {
                statusColor = ContextCompat.getColor(requireContext(), R.color.statusActiva);
            } else if ("En curso".equalsIgnoreCase(status)) {
                statusColor = ContextCompat.getColor(requireContext(), R.color.statusEnCurso);
            } else {
                statusColor = ContextCompat.getColor(requireContext(), R.color.statusPasada);
            }

            Drawable background = binding.tvDetailStatus.getBackground();
            if (background != null) {
                background.setColorFilter(statusColor, PorterDuff.Mode.SRC_IN);
            }

            // Disable CTA if the club status is already "Pasada" to prevent student errors
            if ("Pasada".equalsIgnoreCase(status)) {
                binding.btnInscribirse.setEnabled(false);
                binding.btnInscribirse.setText("Inscripciones Cerradas");
                binding.btnInscribirse.setBackgroundTintList(
                        ContextCompat.getColorStateList(requireContext(), R.color.statusPasada));
            }
        }

        // Back button navigation (User control & freedom)
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        // Call to action button click listener
        binding.btnInscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), getString(R.string.registration_success), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
