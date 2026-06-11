package com.buap.arpavisos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.buap.arpavisos.databinding.FragmentCalendarioBinding;
import java.util.Locale;

public class CalendarioFragment extends Fragment {

    private FragmentCalendarioBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCalendarioBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Back button navigation (User control & freedom)
        binding.btnCalBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        // Set date change listener on CalendarView to show system response/status (interactive feedback)
        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Month is 0-indexed in CalendarView (Jan=0, Dec=11)
                int displayMonth = month + 1;
                String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, displayMonth, year);
                binding.tvEventDate.setText("Eventos para: " + selectedDate);

                // Mock dynamic information depending on selected dates in June 2026
                if (month == 5) { // June (June is month index 5)
                    if (dayOfMonth == 12) {
                        binding.tvEventDesc.setText("• Exposición Colectiva 'Luz y Sombra'\nLugar: Galería 1 del Edificio Central.\nHora: 10:00 AM a 06:00 PM.");
                    } else if (dayOfMonth == 15) {
                        binding.tvEventDesc.setText("• Apertura de la Convocatoria del Festival de Cortometrajes ARPA.\nConsulta las bases completas en la sección de Comunidad.");
                    } else if (dayOfMonth == 18) {
                        binding.tvEventDesc.setText("• Clase Magistral: Técnicas de Grabado Contemporáneo\nImpartida por Mtro. Alberto Domínguez.\nLugar: Taller de Estampado.");
                    } else if (dayOfMonth == 20) {
                        binding.tvEventDesc.setText("• Publicación de Requisitos y Fechas Oficiales para Reinscripciones del periodo Otoño 2026.");
                    } else {
                        binding.tvEventDesc.setText("No hay eventos programados para este día. Disfruta de tu jornada escolar.");
                    }
                } else {
                    binding.tvEventDesc.setText("No hay eventos académicos o administrativos registrados en esta fecha.");
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
