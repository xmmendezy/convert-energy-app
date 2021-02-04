package com.globalredland.converter.ui.equivalente;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.globalredland.converter.R;
import com.globalredland.converter.database.AppDatabase;
import com.globalredland.converter.database.FuelEntry;
import com.google.android.material.navigation.NavigationView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EquivalenteFragment extends Fragment {

    private EquivalenteViewModel equivalenteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        equivalenteViewModel =
                ViewModelProviders.of(this).get(EquivalenteViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_equivalente, container, false);

        final Context context = getContext();
        final AppDatabase db = AppDatabase.getInstance(context);

        final NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(false);
        navigationView.getMenu().getItem(1).setChecked(false);
        navigationView.getMenu().getItem(2).setChecked(true);
        navigationView.getMenu().getItem(3).setChecked(false);
        navigationView.getMenu().getItem(4).setChecked(false);
        navigationView.getMenu().getItem(5).setChecked(false);

        button_disabled(root);
        button_delete_hide(root);
        loadSpinnerFuelFrom(root, db);
        loadSpinnerFuelTo(root, db);
        clearSpinnerUnitsFrom(root, db);
        clearSpinnerUnitsTo(root, db);

        Spinner spinner_fuel_from = root.findViewById(R.id.spinner_fuel_from);
        Spinner spinner_units_from = root.findViewById(R.id.spinner_units_from);
        Spinner spinner_fuel_to = root.findViewById(R.id.spinner_fuel_to);
        Spinner spinner_units_to = root.findViewById(R.id.spinner_units_to);
        Button button_converter = root.findViewById(R.id.converter_button);
        Button button_delete = root.findViewById(R.id.button_delete_data);

        spinner_fuel_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                if (i != 0){
                    loadSpinnerUnitsFrom(root, db, i);
                }

                button_delete_hide(root);
                button_disabled(root);
                Spinner spinner_fuel_from = root.findViewById(R.id.spinner_fuel_from);
                if(!(spinner_fuel_from.getSelectedItemPosition() == 0)) {
                    spinner_fuel_from.setBackgroundResource(R.drawable.round_select);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_units_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i != 0){
                    button_delete_hide(root);
                }

                Spinner spinner_units_from = root.findViewById(R.id.spinner_units_from);
                if(!(spinner_units_from.getSelectedItemPosition() == 0)) {
                    spinner_units_from.setBackgroundResource(R.drawable.round_select);
                } else {
                    spinner_units_from.setBackgroundResource(R.drawable.round_select_disable);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_fuel_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i != 0){
                    loadSpinnerUnitsTo(root, db, i);
                }

                Spinner spinner_fuel_to = root.findViewById(R.id.spinner_fuel_to);
                if(!(spinner_fuel_to.getSelectedItemPosition() == 0)) {
                    spinner_fuel_to.setBackgroundResource(R.drawable.round_select);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_units_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0){
                    button_delete_hide(root);
                    button_enabled(root);
                }

                Spinner spinner_units_to = root.findViewById(R.id.spinner_units_to);
                if(!(spinner_units_to.getSelectedItemPosition() == 0)) {
                    spinner_units_to.setBackgroundResource(R.drawable.round_select);
                    ScrollView mainScrollView = root.findViewById(R.id.mainScrollView);
                    mainScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                } else {
                    spinner_units_to.setBackgroundResource(R.drawable.round_select_disable);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        button_converter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = AppDatabase.getInstance(getContext());

                EditText converter_units = root.findViewById(R.id.converter_units);
                Spinner spinner_fuels_from = root.findViewById(R.id.spinner_fuel_from);
                Spinner spinner_fuels_to = root.findViewById(R.id.spinner_fuel_to);
                Spinner spinner_units_from = root.findViewById(R.id.spinner_units_from);
                Spinner spinner_units_to = root.findViewById(R.id.spinner_units_to);

                int position_from = spinner_fuels_from.getSelectedItemPosition();
                int position_to = spinner_fuels_to.getSelectedItemPosition();

                List<FuelEntry> fuels = db.fuelDao().loadAllFuels();
                int posClase_from = fuels.get(position_from).getId();
                int posClase_to = fuels.get(position_to).getId();

                List<FuelEntry> fuelUnitsFrom = db.fuelDao().loadFuelByClass(posClase_from);
                List<FuelEntry> fuelUnitsTo = db.fuelDao().loadFuelByClass(posClase_to);

                String unitValue = converter_units.getText().toString();

                int unitsPositionFrom =  spinner_units_from.getSelectedItemPosition();
                int unitsPositionTo = spinner_units_to.getSelectedItemPosition();

                int itemClaseFrom = fuelUnitsFrom.get(unitsPositionFrom).getId();
                int itemClaseTo = fuelUnitsTo.get(unitsPositionTo).getId();

                ConvertirUnidades(itemClaseFrom, itemClaseTo, unitValue, root);
            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_delete_hide(root);

                EditText converter_units = root.findViewById(R.id.converter_units);
                TextView convert_units_to = root.findViewById(R.id.textview_units_to);
                Spinner spinner_fuels_from = root.findViewById(R.id.spinner_fuel_from);
                Spinner spinner_fuels_to = root.findViewById(R.id.spinner_fuel_to);
                Spinner spinner_units_from = root.findViewById(R.id.spinner_units_from);
                Spinner spinner_units_to = root.findViewById(R.id.spinner_units_to);

                converter_units.setText("");
                convert_units_to.setText("---");
                convert_units_to.setTextColor(ContextCompat.getColor(context, R.color.colorDisable));
                convert_units_to.setTextSize(20);
                spinner_fuels_from.setSelection(0);
                spinner_fuels_to.setSelection(0);
                spinner_fuels_from.setBackgroundResource(R.drawable.round_select_disable);
                spinner_units_from.setBackgroundResource(R.drawable.round_select_disable);
                spinner_fuels_to.setBackgroundResource(R.drawable.round_select_disable);
                spinner_units_to.setBackgroundResource(R.drawable.round_select_disable);

                clearSpinnerUnitsFrom(root, db);
                clearSpinnerUnitsTo(root, db);

                ScrollView mainScrollView = root.findViewById(R.id.mainScrollView);
                mainScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        return root;
    }
    private void button_disabled(View root){
        Button button_convert = root.findViewById(R.id.converter_button);
        button_convert.setBackgroundResource(R.drawable.round_button_disble);
        button_convert.setEnabled(false);
    }
    private void button_enabled(View root){
        Button button_convert = root.findViewById(R.id.converter_button);
        button_convert.setBackgroundResource(R.drawable.round_button);
        button_convert.setEnabled(true);
    }
    private void button_delete_show(View root){
        Button button_delete = root.findViewById(R.id.button_delete_data);
        Button button_convert = root.findViewById(R.id.converter_button);
        button_delete.setVisibility(View.VISIBLE);
        button_convert.setVisibility(View.GONE);
    }
    private void button_delete_hide(View root){
        Button button_delete = root.findViewById(R.id.button_delete_data);
        Button button_convert = root.findViewById(R.id.converter_button);
        button_delete.setVisibility(View.GONE);
        button_convert.setVisibility(View.VISIBLE);
    }

    private void loadSpinnerFuelFrom (View root, AppDatabase db) {

        Spinner spinner_fuel_from = root.findViewById(R.id.spinner_fuel_from);
        List<FuelEntry> fuels = db.fuelDao().loadAllFuels();

        List<String> fuelDescripcion = new LinkedList<>();

        for (int i=0; i<=fuels.size()-1; i++){
            fuelDescripcion.add(fuels.get(i).getDescripcion());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, fuelDescripcion);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_fuel_from.setAdapter(adapter);

    }

    private void loadSpinnerFuelTo (View root, AppDatabase db) {

        Spinner spinner_fuel_to = root.findViewById(R.id.spinner_fuel_to);
        List<FuelEntry> fuels = db.fuelDao().loadAllFuels();

        List<String> fuelDescripcion = new LinkedList<>();

        for (int i=0; i<=fuels.size()-1; i++){
            fuelDescripcion.add(fuels.get(i).getDescripcion());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, fuelDescripcion);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_fuel_to.setAdapter(adapter);

    }

    private void clearSpinnerUnitsFrom (View root, AppDatabase db) {

        List<FuelEntry> fuels = db.fuelDao().loadAllFuels();

        int posClase = fuels.get(1).getId();

        Spinner spinner_units_from = root.findViewById(R.id.spinner_units_from);

        List<FuelEntry> fuel_units = db.fuelDao().loadFuelByClass(posClase);

        List<String> fuel_description = new LinkedList<>();

        fuel_description.add(fuel_units.get(0).getDescripcion());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, fuel_description);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_units_from.setAdapter(adapter);
    }

    private void loadSpinnerUnitsFrom (View root, AppDatabase db, int position) {

        List<FuelEntry> fuels = db.fuelDao().loadAllFuels();

        int posClase = fuels.get(position).getId();

        Spinner spinner_units_from = root.findViewById(R.id.spinner_units_from);

        List<FuelEntry> fuel_units = db.fuelDao().loadFuelByClass(posClase);

        List<String> fuel_description = new LinkedList<>();

        for (int i=0; i <= fuel_units.size()-1; i++){
            fuel_description.add(fuel_units.get(i).getDescripcion());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, fuel_description);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_units_from.setAdapter(adapter);
    }

    private void clearSpinnerUnitsTo (View root, AppDatabase db) {

        List<FuelEntry> fuels = db.fuelDao().loadAllFuels();

        int posClase = fuels.get(1).getId();

        Spinner spinner_units_to = root.findViewById(R.id.spinner_units_to);

        List<FuelEntry> fuel_units = db.fuelDao().loadFuelByClass(posClase);

        List<String> fuel_description = new LinkedList<>();


        fuel_description.add(fuel_units.get(0).getDescripcion());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, fuel_description);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_units_to.setAdapter(adapter);
    }

    private void loadSpinnerUnitsTo (View root, AppDatabase db, int position) {

        List<FuelEntry> fuels = db.fuelDao().loadAllFuels();

        int posClase = fuels.get(position).getId();

        Spinner spinner_units_to = root.findViewById(R.id.spinner_units_to);

        List<FuelEntry> fuel_units = db.fuelDao().loadFuelByClass(posClase);

        List<String> fuel_description = new LinkedList<>();

        for (int i=0; i <= fuel_units.size()-1; i++){
            fuel_description.add(fuel_units.get(i).getDescripcion());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, fuel_description);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_units_to.setAdapter(adapter);
    }

    public void ConvertirUnidades(int Firstvalue, int Secondvalue, String unitValue, View root) {
        Double totalConvertirEquivalentes = 0.0;
        String simbolo = "";
        String simbolo1 = "";

        final ArrayList<Double> ArrayParam = new ArrayList<>();
        ArrayParam.add(138095.24); //boe;
        ArrayParam.add(1000.0); //Gas Natural;
        ArrayParam.add(123456.79); //Gasolina;
        ArrayParam.add(131036.0); //Diesel;
        ArrayParam.add(127060.0); //Kerosene;
        ArrayParam.add(90500.0); //Propano;
        ArrayParam.add(97400.0); //Butano;
        ArrayParam.add(91190.0); //GLP;
        ArrayParam.add(143150.0); //Residual 6;
        ArrayParam.add(143421.0); //Residual 500;

        if(!unitValue.isEmpty()){

            Double Parametro = Double.parseDouble(unitValue);

            if(Firstvalue == 36){ Parametro = Parametro; simbolo1 = "Btu";}
            if(Firstvalue == 37){ Parametro = Parametro * 1000; simbolo1 = "kBtu";}
            if(Firstvalue == 38){ Parametro = Parametro * 1000000; simbolo1 = "MBtu";}
            if(Firstvalue == 39){ Parametro = Parametro / 1055.06; simbolo1 = "J";}
            if(Firstvalue == 40){ Parametro = Parametro / 1055.06 * 1000; simbolo1 = "kJ";}
            if(Firstvalue == 41){ Parametro = Parametro /	1055.06 * 1000000; simbolo1 = "MJ";}
            if(Firstvalue == 42){ Parametro = Parametro / 293.071 * 1000000; simbolo1 = "KwH";}
            if(Firstvalue == 43){ Parametro = Parametro / 293.071 * 1000 * 1000000; simbolo1 = "Mwh";}
            if(Firstvalue == 44){ Parametro = Parametro * 4.184 / 1055.06 * 1000; simbolo1 = "Kcal";}
            if(Firstvalue == 45){ Parametro = Parametro * 4.184 / 1055.06; simbolo1 = "Cal";}
            if(Firstvalue == 46){ Parametro = Parametro * 2544.5; simbolo1 = "Hp.Hr";}
            if(Firstvalue == 47){ Parametro = Parametro * ArrayParam.get(1); simbolo1 = "PCs (GN)";}
            if(Firstvalue == 48){ Parametro = Parametro * ArrayParam.get(1) * 1000; simbolo1 = "MPCs (GN)";}
            if(Firstvalue == 49){ Parametro = Parametro * ArrayParam.get(1) * 1000000; simbolo1 = "MMPCs (GN)";}
            if(Firstvalue == 50){ Parametro = (Parametro * ArrayParam.get(1)) / Math.pow((12 * 0.0254),3); simbolo1 = "m3s (GN)";}
            if(Firstvalue == 999){ Parametro = (Parametro * ArrayParam.get(1)) / Math.pow((12 * 0.0254),3) * 1000;}
            if(Firstvalue == 51){ Parametro = (Parametro * ArrayParam.get(1)) / Math.pow((12 * 0.0254),3) * 600; simbolo1 = "m3 (GNL)";}
            if(Firstvalue == 52){ Parametro = (Parametro * ArrayParam.get(1)) / Math.pow((12 * 0.0254),3) * 600 / 35.314; simbolo1 = "PCs (GNL)";}
            if(Firstvalue == 53){ Parametro = (Parametro * ArrayParam.get(1)) / Math.pow((12 * 0.0254),3) * 600 / 264.17; simbolo1 = "gal. (GNL)";}
            if(Firstvalue == 54){ Parametro = Parametro / 450 * ArrayParam.get(1) / Math.pow((12 * 0.0254),3) * 600; simbolo1 = "kg. (GNL)";}
            if(Firstvalue == 55){ Parametro = Parametro / 450 * ArrayParam.get(1) / Math.pow((12 * 0.0254),3) * 600 * 1000; simbolo1 = "Ton. (GNL)";}
            if(Firstvalue == 56){ Parametro = Parametro * ArrayParam.get(0) * 42; simbolo1 = "boe";}
            if(Firstvalue == 57){ Parametro = Parametro * ArrayParam.get(2); simbolo1 = "Gasolina 95";}
            if(Firstvalue == 58){ Parametro = Parametro * ArrayParam.get(3); simbolo1 = "Diesel";}
            if(Firstvalue == 59){ Parametro = Parametro * ArrayParam.get(4); simbolo1 = "Kerosene";}
            if(Firstvalue == 60){ Parametro = Parametro * ArrayParam.get(5); simbolo1 = "Propano";}
            if(Firstvalue == 61){ Parametro = Parametro * ArrayParam.get(6); simbolo1 = "Butano";}
            if(Firstvalue == 62){ Parametro = Parametro * ArrayParam.get(7); simbolo1 = "GLP";}
            if(Firstvalue == 63){ Parametro = Parametro * ArrayParam.get(8); simbolo1 = "Residual 6";}
            if(Firstvalue == 64){ Parametro = Parametro * ArrayParam.get(9); simbolo1 = "Residual 500";}
            if(Firstvalue == 65){ Parametro = Parametro * 10 / 514.2 * 264.172 * ArrayParam.get(7); simbolo1 = "GLP Balones";}

            //Petróleo
            if(Firstvalue == 84){ Parametro = Parametro * ArrayParam.get(0); simbolo1 = "gal. (Petróleo)";}
            if(Firstvalue == 85){ Parametro = Parametro * ArrayParam.get(0) * 0.264172051241558; simbolo1 = "L. (Petróleo)";}
            if(Firstvalue == 86){ Parametro = Parametro * ArrayParam.get(0) * 264; simbolo1 = "m3 (Petróleo)";}
            if(Firstvalue == 87){ Parametro = Parametro * ArrayParam.get(0) * 0.301314487632509; simbolo1 = "Kg. (Petróleo)";}
            if(Firstvalue == 88){ Parametro = Parametro * ArrayParam.get(0) * 301.314487632509; simbolo1 = "Ton. (Petróleo)";}
            if(Firstvalue == 89){ Parametro = Parametro * ArrayParam.get(0) * 42; simbolo1 = "Bls. (Petróleo)";}

            //Gasolina 95
            if(Firstvalue == 90){ Parametro = Parametro * ArrayParam.get(2); simbolo1 = "gal. (Gasolina 95)";}
            if(Firstvalue == 91){ Parametro = Parametro * ArrayParam.get(2) * 0.264172051241558; simbolo1 = "L. (Gasolina 95)";}
            if(Firstvalue == 92){ Parametro = Parametro * ArrayParam.get(2) * 264; simbolo1 = "m3 (Gasolina 95)";}
            if(Firstvalue == 93){ Parametro = Parametro * ArrayParam.get(2) * 0.388235294117647; simbolo1 = "Kg. (Gasolina 95)";}
            if(Firstvalue == 94){ Parametro = Parametro * ArrayParam.get(2) * 388.235294117647; simbolo1 = "Ton. (Gasolina 95)";}
            if(Firstvalue == 95){ Parametro = Parametro * ArrayParam.get(2) * 42; simbolo1 = "Bls. (Gasolina 95)";}

            //Diesel
            if(Firstvalue == 96){ Parametro = Parametro * ArrayParam.get(3); simbolo1 = "gal. (Diesel)";}
            if(Firstvalue == 97){ Parametro = Parametro * ArrayParam.get(3) * 0.264172051241558; simbolo1 = "L. (Diesel)";}
            if(Firstvalue == 98){ Parametro = Parametro * ArrayParam.get(3) * 264; simbolo1 = "m3 (Diesel)";}
            if(Firstvalue == 99){ Parametro = Parametro * ArrayParam.get(3) * 0.317307692307692; simbolo1 = "Kg. (Diesel)";}
            if(Firstvalue == 100){ Parametro = Parametro * ArrayParam.get(3) * 317.307692307692; simbolo1 = "Ton. (Diesel)";}
            if(Firstvalue == 101){ Parametro = Parametro * ArrayParam.get(3) * 42; simbolo1 = "Bls. (Diesel)";}

            //Kerosene
            if(Firstvalue == 108){ Parametro = Parametro * ArrayParam.get(4); simbolo1 = "gal. (Kerosene)";}
            if(Firstvalue == 109){ Parametro = Parametro * ArrayParam.get(4) * 0.264172051241558; simbolo1 = "L. (Kerosene)";}
            if(Firstvalue == 110){ Parametro = Parametro * ArrayParam.get(4) * 264; simbolo1 = "m3 (Kerosene)";}
            if(Firstvalue == 111){ Parametro = Parametro * ArrayParam.get(4) * 0.352; simbolo1 = "Kg. (Kerosene)";}
            if(Firstvalue == 112){ Parametro = Parametro * ArrayParam.get(4) * 352; simbolo1 = "Ton. (Kerosene)";}
            if(Firstvalue == 113){ Parametro = Parametro * ArrayParam.get(4) * 42; simbolo1 = "Bls. (Kerosene)";}

            //Propano
            if(Firstvalue == 114){ Parametro = Parametro * ArrayParam.get(5); simbolo1 = "gal. (Propano)";}
            if(Firstvalue == 115){ Parametro = Parametro * ArrayParam.get(5) * 0.264172051241558; simbolo1 = "L. (Propano)";}
            if(Firstvalue == 116){ Parametro = Parametro * ArrayParam.get(5) * 264; simbolo1 = "m3 (Propano)";}
            if(Firstvalue == 117){ Parametro = Parametro * ArrayParam.get(5) * 0.517647058823529; simbolo1 = "Kg. (Propano)";}
            if(Firstvalue == 118){ Parametro = Parametro * ArrayParam.get(5) * 517.647058823529; simbolo1 = "Ton. (Propano)";}
            if(Firstvalue == 119){ Parametro = Parametro * ArrayParam.get(5) * 42; simbolo1 = "Bls. (Propano)";}

            //Butano
            if(Firstvalue == 120){ Parametro = Parametro * ArrayParam.get(6); simbolo1 = "gal. (Butano)";}
            if(Firstvalue == 121){ Parametro = Parametro * ArrayParam.get(6) * 0.264172051241558; simbolo1 = "L. (Butano)";}
            if(Firstvalue == 122){ Parametro = Parametro * ArrayParam.get(6) * 264; simbolo1 = "m3 (Butano)";}
            if(Firstvalue == 123){ Parametro = Parametro * ArrayParam.get(6) * 0.45360824742268; simbolo1 = "Kg. (Butano)";}
            if(Firstvalue == 124){ Parametro = Parametro * ArrayParam.get(6) * 453.60824742268; simbolo1 = "Ton. (Butano)";}
            if(Firstvalue == 125){ Parametro = Parametro * ArrayParam.get(6) * 42; simbolo1 = "Bls. (Butano)";}

            //GLP
            if(Firstvalue == 126){ Parametro = Parametro * ArrayParam.get(7); simbolo1 = "gal. (GLP)";}
            if(Firstvalue == 127){ Parametro = Parametro * ArrayParam.get(7) * 0.264172051241558; simbolo1 = "L. (GLP)";}
            if(Firstvalue == 128){ Parametro = Parametro * ArrayParam.get(7) * 264; simbolo1 = "m3 (GLP)";}
            if(Firstvalue == 129){ Parametro = Parametro / 514.2 * 264.172 * ArrayParam.get(7); simbolo1 = "Kg. (GLP)";}
            if(Firstvalue == 130){ Parametro = Parametro * ArrayParam.get(7) * 509.65250965251; simbolo1 = "Ton. (GLP)";}
            if(Firstvalue == 131){ Parametro = Parametro * ArrayParam.get(7) * 42; simbolo1 = "Bls. (GLP)";}

            //Residual 6
            if(Firstvalue == 132){ Parametro = Parametro * ArrayParam.get(8); simbolo1 = "gal (Residual 6)";}
            if(Firstvalue == 133){ Parametro = Parametro * ArrayParam.get(8) * 0.264172051241558; simbolo1 = "L. (Residual 6)";}
            if(Firstvalue == 134){ Parametro = Parametro * ArrayParam.get(8) * 264; simbolo1 = "m3 (Residual 6)";}
            if(Firstvalue == 135){ Parametro = Parametro * ArrayParam.get(8) * 0.272164948453608; simbolo1 = "Kg. (Residual 6)";}
            if(Firstvalue == 136){ Parametro = Parametro * ArrayParam.get(8) * 272.164948453608; simbolo1 = "Ton. (Residual 6)";}
            if(Firstvalue == 137){ Parametro = Parametro * ArrayParam.get(8) * 42; simbolo1 = "Bls. (Residual 6)";}

            //Residual 500
            if(Firstvalue == 138){ Parametro = Parametro * ArrayParam.get(9); simbolo1 = "gal. (Residual 500)";}
            if(Firstvalue == 139){ Parametro = Parametro * ArrayParam.get(9) * 0.264172051241558; simbolo1 = "L. (Residual 500)";}
            if(Firstvalue == 140){ Parametro = Parametro * ArrayParam.get(9) * 264; simbolo1 = "m3 (Residual 500)";}
            if(Firstvalue == 141){ Parametro = Parametro * ArrayParam.get(9) * 0.269387755102041; simbolo1 = "Kg. (Residual 500)";}
            if(Firstvalue == 142){ Parametro = Parametro * ArrayParam.get(9) * 269.387755102041; simbolo1 = "Ton. (Residual 500)";}
            if(Firstvalue == 143){ Parametro = Parametro * ArrayParam.get(9) * 42; simbolo1 = "Bls. (Residual 500)";}


            switch(Secondvalue){
                case 36: //Btu
                    totalConvertirEquivalentes = Parametro;
                    simbolo = "Btu equiv. de energía eléctrica";
                    break;
                case 37: //kBtu
                    totalConvertirEquivalentes = Parametro / 1000;
                    simbolo = "kBtu equiv. de energía eléctrica";
                    break;
                case 38: //MillionBtu
                    totalConvertirEquivalentes = (Parametro / 1000)/1000;
                    simbolo = "MBtu equiv. de energía eléctrica";
                    break;
                case 39: //Joule
                    totalConvertirEquivalentes = Parametro * 1055.06;
                    simbolo = "J equiv. de energía eléctrica";
                    break;
                case 40: //KiloJoule
                    totalConvertirEquivalentes = (Parametro * 1055.06) / 1000;
                    simbolo = "kJ equiv. de energía eléctrica";
                    break;
                case 41: //MegaJoule
                    totalConvertirEquivalentes = ((Parametro * 1055.06) / 1000) / 1000;
                    simbolo = "MJ equiv. de energía eléctrica";
                    break;
                case 42: //KwH
                    totalConvertirEquivalentes = (Parametro * 293.071) / 1000000;
                    simbolo = "KwH equiv. de energía eléctrica";
                    break;
                case 43: //Mwh
                    totalConvertirEquivalentes = ((Parametro * 293.071) / 1000000) / 1000;
                    simbolo = "Mwh equiv. de energía eléctrica";
                    break;
                case 44: //Kcal
                    totalConvertirEquivalentes = (Parametro / 4.184) * (1055.06 / 1000);
                    simbolo = "Kcal equiv. de energía eléctrica";
                    break;
                case 45: //Cal
                    totalConvertirEquivalentes = ((Parametro / 4.184) * (1055.06 / 1000)) * 1000;
                    simbolo = "Cal equiv. de energía eléctrica";
                    break;
                case 46: //Hp.Hr
                    totalConvertirEquivalentes = Parametro / 2544.5;
                    simbolo = "Hp.Hr equiv. de energía eléctrica";
                    break;
                case 47: //PCs
                    totalConvertirEquivalentes = Parametro / ArrayParam.get(1);
                    simbolo = "PCs equivalentes de GN.";
                    break;
                case 48: //MPCs
                    totalConvertirEquivalentes = (Parametro / ArrayParam.get(1)) / 1000;
                    simbolo = "MPCs equivalentes de GN.";
                    break;
                case 49: //MMPCs
                    totalConvertirEquivalentes = (Parametro / ArrayParam.get(1)) / 1000000;
                    simbolo = "MMPCs equivalentes de GN.";
                    break;
                case 50: //m3s
                    totalConvertirEquivalentes 	= (Parametro / ArrayParam.get(1)) * Math.pow((12 * 0.0254), 3);
                    simbolo = "m3s equivalentes de GN.";
                    break;
                case 999: //Mm3s
                    totalConvertirEquivalentes = ((Parametro / ArrayParam.get(1)) * Math.pow((12 * 0.0254), 3)) / 600 / 1000;
                    simbolo = "Mm3s equivalentes de GNL.";
                    break;
                case 51: //m3
                    totalConvertirEquivalentes = ((Parametro / ArrayParam.get(1)) * Math.pow((12 * 0.0254), 3)) / 600;
                    simbolo = "m3 equivalentes de GNL.";
                    break;
                case 52: //PC
                    totalConvertirEquivalentes = ((Parametro / ArrayParam.get(1)) * Math.pow((12 * 0.0254), 3)) / 600 * 35.314;
                    simbolo = "PC equivalentes de GNL.";
                    break;
                case 53: //gal
                    totalConvertirEquivalentes = ((Parametro / ArrayParam.get(1)) * Math.pow((12 * 0.0254), 3)) / 600 * 264.17;
                    simbolo = "gal. equivalentes de GNL.";
                    break;
                case 54: //kg
                    totalConvertirEquivalentes = ((Parametro / ArrayParam.get(1)) * Math.pow((12 * 0.0254), 3)) / 600 * 450;
                    simbolo = "kg. equivalentes de GNL.";
                    break;
                case 55: //TON
                    totalConvertirEquivalentes = ((Parametro / ArrayParam.get(1)) * Math.pow((12 * 0.0254), 3)) / 600 * 450 / 1000;
                    simbolo = "Ton. equivalentes de GNL.";
                    break;
                case 56: //boe
                    totalConvertirEquivalentes = Parametro / ArrayParam.get(0) / 42;
                    simbolo = "boe";
                    break;
                case 57: //Gasolina 95
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(2);
                    simbolo = "Gasolina 95";
                    break;
                case 58: // Diesel
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(3);
                    simbolo = "Diesel";
                    break;
                case 59: // Kerosene
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(4);
                    simbolo = "Kerosene";
                    break;
                case 60: // Propano
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(5);
                    simbolo = "Propano";
                    break;
                case 61: // Butano
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(6);
                    simbolo = "Butano";
                    break;
                case 62: // GLP
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(7);
                    simbolo = "GLP";
                    break;
                case 63: // Residual 6
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(8);
                    simbolo = "Residual 6";
                    break;
                case 64: // Residual 500
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(9);
                    simbolo = "Residual 500";
                    break;
                case 65: // GLP	Balones
                    totalConvertirEquivalentes =  Parametro / 10 * 514.2 / 264.172 / ArrayParam.get(7);
                    simbolo = "GLP Balones";
                    break;

                // Petroleo
                case 84: //Galones
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(0);
                    simbolo = "gal. equivalentes de Petróleo";
                    break;
                case 85: //Litros
                    if(Firstvalue == 86){
                        totalConvertirEquivalentes =  (Parametro / ArrayParam.get(0) / 264) * 1000;
                    }
                    else{
                        totalConvertirEquivalentes =  Parametro / ArrayParam.get(0) / 0.264172051241558;
                    }
                    simbolo = "L. equivalentes de Petróleo";
                    break;
                case 86: // metros cúbicos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(0) / 264;
                    simbolo = "m3 equivalentes de Petróleo";
                    break;
                case 87: // Kilogramos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(0) / 0.301314487632509;
                    simbolo = "Kg. equivalentes de Petróleo";
                    break;
                case 88: //Toneladas
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(0) / 301.314487632509;
                    simbolo = "Ton. equivalentes de Petróleo";
                    break;
                case 89: //Barrilees
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(0) / 42;
                    simbolo = "Bls. equivalentes de Petróleo (BOE)";
                    break;

                // Gasolina 95
                case 90: //Galones
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(2);
                    simbolo = "gal. equivalentes de Gasolina 95";
                    break;
                case 91: //Litros
                    if(Firstvalue == 92){
                        totalConvertirEquivalentes =  (Parametro / ArrayParam.get(2) / 264) * 1000;
                    }
                    else{
                        totalConvertirEquivalentes =  Parametro / ArrayParam.get(2) / 0.264172051241558;
                    }
                    simbolo = "L. equivalentes de Gasolina 95";
                    break;
                case 92: // metros cúbicos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(2) / 264;
                    simbolo = "m3. equivalentes de Gasolina 95";
                    break;
                case 93: // Kilogramos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(2) / 0.388235294117647;
                    simbolo = "Kg. equivalentes de Gasolina 95";
                    break;
                case 94: //Toneladas
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(2) / 388.235294117647;
                    simbolo = "Ton. equivalentes de Gasolina 95";
                    break;
                case 95: //Barrilees
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(2) / 42;
                    simbolo = "Bls. equivalentes de Gasolina 95";
                    break;

                // Diesel
                case 96: //Galones
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(3);
                    simbolo = "gal. equivalentes de Diesel";
                    break;
                case 97: //Litros
                    if(Firstvalue == 98){
                        totalConvertirEquivalentes =  (Parametro / ArrayParam.get(3) / 264) * 1000;
                    }
                    else{
                        totalConvertirEquivalentes =  Parametro / ArrayParam.get(3) / 0.264172051241558;
                    }
                    simbolo = "L. equivalentes de Diesel";
                    break;
                case 98: // metros cúbicos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(3) / 264;
                    simbolo = "m3 equivalentes de Diesel";
                    break;
                case 99: // Kilogramos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(3) / 0.317307692307692;
                    simbolo = "Kg. equivalentes de Diesel";
                    break;
                case 100: //Toneladas
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(3) /  317.307692307692;
                    simbolo = "Ton. equivalentes de Diesel";
                    break;
                case 101: //Barrilees
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(3) / 42;
                    simbolo = "Bls. equivalentes de Diesel";
                    break;

                // Kerosene
                case 108: //Galones
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(4);
                    simbolo = "gal. equivalentes de Kerosene";
                    break;
                case 109: //Litros
                    if(Firstvalue == 110){
                        totalConvertirEquivalentes =  (Parametro / ArrayParam.get(4) / 264) * 1000;
                    }
                    else{
                        totalConvertirEquivalentes =  Parametro / ArrayParam.get(4) / 0.264172051241558;
                    }
                    simbolo = "L. equivalentes de Kerosene";
                    break;
                case 110: // metros cúbicos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(4) / 264;
                    simbolo = "m3 equivalentes de Kerosene";
                    break;
                case 111: // Kilogramos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(4) / 0.352;
                    simbolo = "Kg. equivalentes de Kerosene";
                    break;
                case 112: //Toneladas
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(4) / 352;
                    simbolo = "Ton. equivalentes de Kerosene";
                    break;
                case 113: //Barrilees
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(4) / 42;
                    simbolo = "Bls. equivalentes de Kerosene";
                    break;

                // Propano
                case 114: //Galones
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(5);
                    simbolo = "gal. equivalentes de Propano";
                    break;
                case 115: //Litros
                    if(Firstvalue == 116){
                        totalConvertirEquivalentes =  (Parametro / ArrayParam.get(5) / 264) * 1000;
                    }
                    else{
                        totalConvertirEquivalentes =  Parametro / ArrayParam.get(5) / 0.264172051241558;
                    }
                    simbolo = "L. equivalentes de Propano";
                    break;
                case 116: // metros cúbicos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(5) / 264;
                    simbolo = "m3 equivalentes de Propano";
                    break;
                case 117: // Kilogramos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(5) / 0.517647058823529;
                    simbolo = "Kg. equivalentes de Propano";
                    break;
                case 118: //Toneladas
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(5) / 517.647058823529;
                    simbolo = "Ton. de Propano";
                    break;
                case 119: //Barrilees
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(5) / 42;
                    simbolo = "Bls. equivalentes de Propano";
                    break;

                // Butano
                case 120: //Galones
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(6);
                    simbolo = "gal. equivalentes de Butano";
                    break;
                case 121: //Litros
                    if(Firstvalue == 122){
                        totalConvertirEquivalentes =  (Parametro / ArrayParam.get(6) / 264) * 1000;
                    }
                    else{
                        totalConvertirEquivalentes =  Parametro / ArrayParam.get(6) / 0.264172051241558;
                    }
                    simbolo = "L. equivalentes de Butano";
                    break;
                case 122: // metros cúbicos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(6) / 264;
                    simbolo = "m3 equivalentes de Butano";
                    break;
                case 123: // Kilogramos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(6) / 0.45360824742268;
                    simbolo = "Kg. equivalentes de Butano";
                    break;
                case 124: //Toneladas
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(6) / 453.60824742268;
                    simbolo = "Ton. equivalentes de Butano";
                    break;
                case 125: //Barrilees
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(6) / 42;
                    simbolo = "Bls. equivalentes de Butano";
                    break;


                // GLP
                case 126: //Galones
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(7);
                    simbolo = "gal. equivalentes de GLP";
                    break;
                case 127: //Litros
                    if(Firstvalue == 128){
                        totalConvertirEquivalentes =  (Parametro / ArrayParam.get(7) / 264) * 1000;
                    }
                    else{
                        totalConvertirEquivalentes =  Parametro / ArrayParam.get(7) / 0.264172051241558;
                    }
                    simbolo = "L. equivalentes de GLP";
                    break;
                case 128: // metros cúbicos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(7) / 264;
                    simbolo = "m3 equivalentes de GLP";
                    break;
                case 129: // Kilogramos
                    totalConvertirEquivalentes =  Parametro * 514.2 / 264.172 / ArrayParam.get(7);
                    simbolo = "Kg. equivalentes de GLP";
                    break;
                case 130: //Toneladas
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(7) / 509.65250965251;
                    simbolo = "Ton. equivalentes de GLP";
                    break;
                case 131: //Barrilees
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(7) / 42;
                    simbolo = "Bls. equivalentes de GLP";
                    break;

                // Residual 6
                case 132: //Galones
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(8);
                    simbolo = "gal. equivalentes de Residual 6";
                    break;
                case 133: //Litros
                    if(Firstvalue == 134){
                        totalConvertirEquivalentes =  (Parametro / ArrayParam.get(8) / 264) * 1000;
                    }
                    else{
                        totalConvertirEquivalentes =  Parametro / ArrayParam.get(8) / 0.264172051241558;
                    }
                    simbolo = "L. equivalentes de Residual 6";
                    break;
                case 134: // metros cúbicos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(8) / 264;
                    simbolo = "m3 equivalentes de Residual 6";
                    break;
                case 135: // Kilogramos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(8) / 0.272164948453608;
                    simbolo = "Kg. equivalentes de Residual 6";
                    break;
                case 136: //Toneladas
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(8) / 272.164948453608;
                    simbolo = "Ton. equivalentes de Residual 6";
                    break;
                case 137: //Barrilees
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(8) / 42;
                    simbolo = "Bls. equivalentes de Residual 6";
                    break;

                // Residual 500
                case 138: //Galones
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(9);
                    simbolo = "gal. equivalentes de Residual 500";
                    break;
                case 139: //Litros
                    if(Firstvalue == 140){
                        totalConvertirEquivalentes =  (Parametro / ArrayParam.get(9) / 264) * 1000;
                    }
                    else{
                        totalConvertirEquivalentes =  Parametro / ArrayParam.get(9) / 0.264172051241558;
                    }
                    simbolo = "L. equivalentes de Residual 500";
                    break;
                case 140: // metros cúbicos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(9) / 264;
                    simbolo = "m3 equivalentes de Residual 500";
                    break;
                case 141: // Kilogramos
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(9) / 0.269387755102041;
                    simbolo = "Kg. equivalentes de Residual 500";
                    break;
                case 142: //Toneladas
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(9) / 269.387755102041;
                    simbolo = "Ton. equivalentes de Residual 500";
                    break;
                case 143: //Barrilees
                    totalConvertirEquivalentes =  Parametro / ArrayParam.get(9) / 42;
                    simbolo = "Bls. equivalentes de Residual 500";
                    break;
            }

            button_delete_show(root);


            NumberFormat f = NumberFormat.getInstance();
            f.setMaximumFractionDigits(2);

            TextView convert_units_to = root.findViewById(R.id.textview_units_to);

            convert_units_to.setText(f.format(totalConvertirEquivalentes) + " " + simbolo);
            convert_units_to.setTextSize(16);
            convert_units_to.setTextColor(Color.BLACK);

        }
        else
            Toast.makeText(getActivity(), "Ingrese cantidad a convertir", Toast.LENGTH_LONG).show();
    }

}
