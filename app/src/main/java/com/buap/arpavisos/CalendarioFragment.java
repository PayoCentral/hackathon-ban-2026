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

        // Set initial instructions on the pseudo-glass detail card
        binding.tvEventDate.setText("Calendario de Actividades");
        binding.tvEventDesc.setText("Selecciona una fecha en el calendario para ver los eventos programados (ej. pulsa el 14 o 16 de Junio de 2026).");

        // Set date change listener on CalendarView to show system response/status (interactive feedback)
        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Month is 0-indexed in CalendarView (Jan=0, Dec=11)
                int displayMonth = month + 1;
                String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, displayMonth, year);
                binding.tvEventDate.setText("Eventos para: " + selectedDate);

                // Mock dynamic information depending on selected dates in June 2026
                if (month == 5 && year == 2026) { // June 2026
                    if (dayOfMonth == 14) {
                        binding.tvEventDesc.setText("• Muestra de Fin de Semestre\nLugar: Explanada de la Facultad\nHora: 10:00 AM - 06:00 PM\nDetalle: Presentación de proyectos y galerías de todas las carreras de la facultad ARPA.");
                    } else if (dayOfMonth == 16) {
                        binding.tvEventDesc.setText("• Taller de Grabado Experimental\nCoordinador: Andrei Carro\nHora: 04:00 PM - 06:00 PM\nLugar: Taller de Grabado ARPA\nDetalle: Sesión técnica abierta enfocada en explorar técnicas de impresión no convencionales.");
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
