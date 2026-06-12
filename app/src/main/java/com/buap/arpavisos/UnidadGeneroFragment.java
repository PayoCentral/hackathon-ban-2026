package com.buap.arpavisos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.buap.arpavisos.databinding.FragmentUnidadGeneroBinding;

public class UnidadGeneroFragment extends Fragment {

    private FragmentUnidadGeneroBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentUnidadGeneroBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.customHeader.tvHeaderTitle.setText("Unidad de Género ARPA");
        binding.customHeader.tvHeaderSubtitle.setVisibility(View.VISIBLE);
        binding.customHeader.tvHeaderSubtitle.setText(
                "Comprometidos con un entorno universitario seguro, inclusivo, equitativo y libre de violencia.");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
