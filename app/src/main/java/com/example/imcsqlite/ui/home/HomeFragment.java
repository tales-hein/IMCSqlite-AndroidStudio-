package com.example.imcsqlite.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.imcsqlite.BD;
import com.example.imcsqlite.IMC;
import com.example.imcsqlite.R;
import com.example.imcsqlite.databinding.FragmentHomeBinding;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;
    private Button btn;
    private EditText edPeso;
    private EditText edAltura;
    private TextView tvImc;
    private IMC imc = new IMC();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        BD bancoDados = new BD(getActivity());

        edPeso = (EditText) root.findViewById(R.id.edtPeso);
        edAltura = (EditText) root.findViewById(R.id.edtAltura);
        tvImc = (TextView) root.findViewById(R.id.tvResult);

        btn = (Button) root.findViewById(R.id.btnCalcular);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                double peso = Double.parseDouble(edPeso.getText().toString());
                double altura = Double.parseDouble(edAltura.getText().toString());
                String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                imc.setPeso(peso);
                imc.setAltura(altura);
                imc.setDataCalculo(currentDate);
                tvImc.setText("Seu IMC é: "+String.format("%.2f", imc.calcImc(altura, peso)));

                bancoDados.inserir(imc);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}