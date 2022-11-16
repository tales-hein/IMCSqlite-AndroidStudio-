package com.example.imcsqlite.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.imcsqlite.BD;
import com.example.imcsqlite.R;
import com.example.imcsqlite.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ListView listViewIMC;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textDashboard;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        BD bancoDados = new BD(getActivity());
        listViewIMC = (ListView) root.findViewById(R.id.lvIMC);
        ArrayList<String> linhas;
        ArrayAdapter meuAdaptador;
        linhas = new ArrayList<String>();
        meuAdaptador = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                linhas
        );
        listViewIMC.setAdapter(meuAdaptador);
        bancoDados.PopulaArrayCalculos(linhas);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}