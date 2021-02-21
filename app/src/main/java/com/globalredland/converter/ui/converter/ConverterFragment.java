package com.globalredland.converter.ui.converter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.ContextMenu;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.globalredland.converter.R;
import com.globalredland.converter.database.AppDatabase;
import com.globalredland.converter.database.FuelEntry;
import com.google.android.material.navigation.NavigationView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConverterFragment extends Fragment {

    private ConverterViewModel converterViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        converterViewModel =
                ViewModelProviders.of(this).get(ConverterViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_converter, container, false);

        final Context context = getContext();
        final AppDatabase db = AppDatabase.getInstance(context);

        final NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(false);
        navigationView.getMenu().getItem(1).setChecked(true);
        navigationView.getMenu().getItem(2).setChecked(false);
        navigationView.getMenu().getItem(3).setChecked(false);
        navigationView.getMenu().getItem(4).setChecked(false);
        navigationView.getMenu().getItem(5).setChecked(false);

        root.clearFocus();

        hidden_keyboard(root, context);
        button_disabled(root);
        button_delete_hide(root);
        loadSpinnerFuel(root, db);
        clearSpinnerUnits(root, db);

        Spinner spinner_fuel = root.findViewById(R.id.spinner_fuel);
        Spinner spinner_units_from = root.findViewById(R.id.spinner_units_from);
        Spinner spinner_units_to = root.findViewById(R.id.spinner_units_to);
        Button button_converter = root.findViewById(R.id.converter_button);
        Button button_delete = root.findViewById(R.id.button_delete_data);

        spinner_fuel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hidden_keyboard(root, context);
                if (i != 0) {
                    loadSpinnerUnits(root, db, i);
                }
                button_delete_hide(root);
                button_disabled(root);
                Spinner spinner_fuel = root.findViewById(R.id.spinner_fuel);
                if(!(spinner_fuel.getSelectedItemPosition() == 0)) {
                    spinner_fuel.setBackgroundResource(R.drawable.round_select);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                hidden_keyboard(root, context);
            }
        });
        spinner_units_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hidden_keyboard(root, context);
                Spinner spinner_units_to = root.findViewById(R.id.spinner_units_to);
                Spinner spinner_units_from = root.findViewById(R.id.spinner_units_from);
                if(!(spinner_units_to.getSelectedItemPosition() == 0)) {
                    button_enabled(root);
                    button_delete_hide(root);
                }
                if(!(spinner_units_from.getSelectedItemPosition() == 0)) {
                    spinner_units_from.setBackgroundResource(R.drawable.round_select);
                } else {
                    spinner_units_from.setBackgroundResource(R.drawable.round_select_disable);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                hidden_keyboard(root, context);
            }
        });
        spinner_units_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hidden_keyboard(root, context);
                Spinner spinner_units_from = root.findViewById(R.id.spinner_units_from);
                Spinner spinner_units_to = root.findViewById(R.id.spinner_units_to);
                if(!(spinner_units_from.getSelectedItemPosition() == 0)) {
                    button_enabled(root);
                    button_delete_hide(root);
                }
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
                hidden_keyboard(root, context);
            }
        });

        button_converter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hidden_keyboard(root, context);

                AppDatabase db = AppDatabase.getInstance(getContext());

                EditText converter_units = root.findViewById(R.id.converter_units);
                Spinner spinner_fuels = root.findViewById(R.id.spinner_fuel);
                Spinner spinner_units_from = root.findViewById(R.id.spinner_units_from);
                Spinner spinner_units_to = root.findViewById(R.id.spinner_units_to);

                int position = spinner_fuels.getSelectedItemPosition();

                List<FuelEntry> fuels = db.fuelDao().loadAllFuels();
                int posClase = fuels.get(position).getId();

                List<FuelEntry> fuelUnits = db.fuelDao().loadFuelByClass(posClase);

                String unitValue = converter_units.getText().toString();
                int itemPositionFrom =  spinner_units_from.getSelectedItemPosition();
                int itemPositionTo = spinner_units_to.getSelectedItemPosition();

                int itemClaseFrom = fuelUnits.get(itemPositionFrom).getId();
                int itemClaseTo = fuelUnits.get(itemPositionTo).getId();

                ConvertirUnidades(itemClaseFrom, itemClaseTo, unitValue, root);
            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hidden_keyboard(root, context);
                button_delete_hide(root);

                EditText converter_units = root.findViewById(R.id.converter_units);
                TextView convert_units_to = root.findViewById(R.id.textview_units_to);
                Spinner spinner_fuels = root.findViewById(R.id.spinner_fuel);
                Spinner spinner_units_from = root.findViewById(R.id.spinner_units_from);
                Spinner spinner_units_to = root.findViewById(R.id.spinner_units_to);

                converter_units.setText("");
                convert_units_to.setText("---");
                convert_units_to.setTextColor(ContextCompat.getColor(context, R.color.colorDisable));
                spinner_fuels.setSelection(0);
                spinner_fuels.setBackgroundResource(R.drawable.round_select_disable);
                spinner_units_from.setBackgroundResource(R.drawable.round_select_disable);
                spinner_units_to.setBackgroundResource(R.drawable.round_select_disable);
                clearSpinnerUnits(root, db);
            }
        });

        return root;
    }
    private void hidden_keyboard(View root, Context context) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(root.findViewById(R.id.textview_units_to).getWindowToken(), 0);
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

    private void loadSpinnerFuel (View root, AppDatabase db) {

        Spinner spinner_fuel = root.findViewById(R.id.spinner_fuel);
        List<FuelEntry> fuels = db.fuelDao().loadAllFuels();

        List<String> fuelDescripcion = new LinkedList<>();

        for (int i=0; i<=fuels.size()-1; i++){
            fuelDescripcion.add(fuels.get(i).getDescripcion());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, fuelDescripcion);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_fuel.setAdapter(adapter);

    }

    private void loadSpinnerUnits (View root, AppDatabase db, int position) {

        List<FuelEntry> fuels = db.fuelDao().loadAllFuels();

        int posClase = fuels.get(position).getId();

        Spinner spinner_units_from = root.findViewById(R.id.spinner_units_from);

        Spinner spinner_units_to = root.findViewById(R.id.spinner_units_to);

        List<FuelEntry> fuel_units = db.fuelDao().loadFuelByClass(posClase);

        List<String> fuel_description = new LinkedList<>();

        for (int i=0; i <= fuel_units.size()-1; i++){
            fuel_description.add(fuel_units.get(i).getDescripcion());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, fuel_description);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_units_from.setAdapter(adapter);

        spinner_units_to.setAdapter(adapter);
    }

    private void clearSpinnerUnits (View root, AppDatabase db) {

        List<FuelEntry> fuels = db.fuelDao().loadAllFuels();

        int posClase = fuels.get(1).getId();

        Spinner spinner_units_from = root.findViewById(R.id.spinner_units_from);

        Spinner spinner_units_to = root.findViewById(R.id.spinner_units_to);

        List<FuelEntry> fuel_units = db.fuelDao().loadFuelByClass(posClase);

        List<String> fuel_description = new LinkedList<>();


        fuel_description.add(fuel_units.get(0).getDescripcion());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, fuel_description);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_units_from.setAdapter(adapter);

        spinner_units_to.setAdapter(adapter);
    }


    private void ConvertirUnidades(int Firstvalue, int Secondvalue, String unitValue, View root){

        Double totalConvertirUnidades = 0.0;
        String simbolo = "";
        String simbolo1 = "";

        ArrayList<Double> ArrayParam = new ArrayList<>();
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

        if(!unitValue.isEmpty()) {
            Double Parametro = Double.parseDouble(unitValue);

            if (Firstvalue == 36) {
                Parametro = Parametro;
                simbolo1 = "Btu";
            }
            if (Firstvalue == 37) {
                Parametro = Parametro * 1000;
                simbolo1 = "kBtu";
            }
            if (Firstvalue == 38) {
                Parametro = Parametro * 1000000;
                simbolo1 = "MBtu";
            }
            if (Firstvalue == 39) {
                Parametro = Parametro / 1055.06;
                simbolo1 = "J";
            }
            if (Firstvalue == 40) {
                Parametro = Parametro / 1055.06 * 1000;
                simbolo1 = "kJ";
            }
            if (Firstvalue == 41) {
                Parametro = Parametro / 1055.06 * 1000000;
                simbolo1 = "MJ";
            }
            if (Firstvalue == 42) {
                Parametro = Parametro / 293.071 * 1000000;
                simbolo1 = "KwH";
            }
            if (Firstvalue == 43) {
                Parametro = Parametro / 293.071 * 1000 * 1000000;
                simbolo1 = "Mwh";
            }
            if (Firstvalue == 44) {
                Parametro = Parametro * 4.184 / 1055.06 * 1000;
                simbolo1 = "Kcal";
            }
            if (Firstvalue == 45) {
                Parametro = Parametro * 4.184 / 1055.06;
                simbolo1 = "Cal";
            }
            if (Firstvalue == 46) {
                Parametro = Parametro * 2544.5;
                simbolo1 = "Hp.Hr";
            }
            if (Firstvalue == 47) {
                Parametro = Parametro * ArrayParam.get(1);
                simbolo1 = "PCs";
            }
            if (Firstvalue == 48) {
                Parametro = Parametro * ArrayParam.get(1) * 1000;
                simbolo1 = "MPCs";
            }
            if (Firstvalue == 49) {
                Parametro = Parametro * ArrayParam.get(1) * 1000000;
                simbolo1 = "MMPCs";
            }
            if (Firstvalue == 50) {
                Parametro = (Parametro * ArrayParam.get(1)) / Math.pow((12 * 0.0254), 3);
                simbolo1 = "m3s";
            }
            if (Firstvalue == 999) {
                Parametro = (Parametro * ArrayParam.get(1)) / Math.pow((12 * 0.0254), 3) * 1000;
            }
            if (Firstvalue == 51) {
                Parametro = (Parametro * ArrayParam.get(1)) / Math.pow((12 * 0.0254), 3) * 600;
                simbolo1 = "m3";
            }
            if (Firstvalue == 52) {
                Parametro = (Parametro * ArrayParam.get(1)) / Math.pow((12 * 0.0254), 3) * 600 / 35.314;
                simbolo1 = "PC";
            }
            if (Firstvalue == 53) {
                Parametro = (Parametro * ArrayParam.get(1)) / Math.pow((12 * 0.0254), 3) * 600 / 264.17;
                simbolo1 = "gal";
            }
            if (Firstvalue == 54) {
                Parametro = Parametro / 450 * ArrayParam.get(1) / Math.pow((12 * 0.0254), 3) * 600;
                simbolo1 = "kg";
            }
            if (Firstvalue == 55) {
                Parametro = Parametro / 450 * ArrayParam.get(1) / Math.pow((12 * 0.0254), 3) * 600 * 1000;
                simbolo1 = "TON";
            }
            if (Firstvalue == 56) {
                Parametro = Parametro * ArrayParam.get(0) * 42;
                simbolo1 = "boe";
            }
            if (Firstvalue == 57) {
                Parametro = Parametro * ArrayParam.get(2);
                simbolo1 = "Gasolina 95";
            }
            if (Firstvalue == 58) {
                Parametro = Parametro * ArrayParam.get(3);
                simbolo1 = "Diesel";
            }
            if (Firstvalue == 59) {
                Parametro = Parametro * ArrayParam.get(4);
                simbolo1 = "Kerosene";
            }
            if (Firstvalue == 60) {
                Parametro = Parametro * ArrayParam.get(5);
                simbolo1 = "Propano";
            }
            if (Firstvalue == 61) {
                Parametro = Parametro * ArrayParam.get(6);
                simbolo1 = "Butano";
            }
            if (Firstvalue == 62) {
                Parametro = Parametro * ArrayParam.get(7);
                simbolo1 = "GLP";
            }
            if (Firstvalue == 63) {
                Parametro = Parametro * ArrayParam.get(8);
                simbolo1 = "Residual 6";
            }
            if (Firstvalue == 64) {
                Parametro = Parametro * ArrayParam.get(9);
                simbolo1 = "Residual 500";
            }
            if (Firstvalue == 65) {
                Parametro = Parametro * 10 / 514.2 * 264.172 * ArrayParam.get(7);
                simbolo1 = "GLP Balones";
            }

            //Petróleo
            if (Firstvalue == 84) {
                Parametro = Parametro;
                simbolo1 = "gal";
            }
            if (Firstvalue == 85) {
                Parametro = Parametro * 0.264172051241558;
                simbolo1 = "L";
            }
            if (Firstvalue == 86) {
                Parametro = Parametro * 264;
                simbolo1 = "m3";
            }
            if (Firstvalue == 87) {
                Parametro = Parametro * 0.301314487632509;
                simbolo1 = "Kg";
            }
            if (Firstvalue == 88) {
                Parametro = Parametro * 301.314487632509;
                simbolo1 = "Ton";
            }
            if (Firstvalue == 89) {
                Parametro = Parametro * 42;
                simbolo1 = "bl";
            }

            //Gasolina 95
            if (Firstvalue == 90) {
                Parametro = Parametro;
                simbolo1 = "gal";
            }
            if (Firstvalue == 91) {
                Parametro = Parametro * 0.264172051241558;
                simbolo1 = "L";
            }
            if (Firstvalue == 92) {
                Parametro = Parametro * 264;
                simbolo1 = "m3";
            }
            if (Firstvalue == 93) {
                Parametro = Parametro * 0.388235294117647;
                simbolo1 = "Kg";
            }
            if (Firstvalue == 94) {
                Parametro = Parametro * 388.235294117647;
                simbolo1 = "Ton";
            }
            if (Firstvalue == 95) {
                Parametro = Parametro * 42;
                simbolo1 = "bl";
            }

            //Diesel
            if (Firstvalue == 96) {
                Parametro = Parametro;
                simbolo1 = "gal";
            }
            if (Firstvalue == 97) {
                Parametro = Parametro * 0.264172051241558;
                simbolo1 = "L";
            }
            if (Firstvalue == 98) {
                Parametro = Parametro * 264;
                simbolo1 = "m3";
            }
            if (Firstvalue == 99) {
                Parametro = Parametro * 0.317307692307692;
                simbolo1 = "Kg";
            }
            if (Firstvalue == 100) {
                Parametro = Parametro * 317.307692307692;
                simbolo1 = "Ton";
            }
            if (Firstvalue == 101) {
                Parametro = Parametro * 42;
                simbolo1 = "bl";
            }

            //Kerosene
            if (Firstvalue == 108) {
                Parametro = Parametro;
                simbolo1 = "gal";
            }
            if (Firstvalue == 109) {
                Parametro = Parametro * 0.264172051241558;
                simbolo1 = "L";
            }
            if (Firstvalue == 110) {
                Parametro = Parametro * 264;
                simbolo1 = "m3";
            }
            if (Firstvalue == 111) {
                Parametro = Parametro * 0.352;
                simbolo1 = "Kg";
            }
            if (Firstvalue == 112) {
                Parametro = Parametro * 352;
                simbolo1 = "Ton";
            }
            if (Firstvalue == 113) {
                Parametro = Parametro * 42;
                simbolo1 = "bl";
            }

            //Propano
            if (Firstvalue == 114) {
                Parametro = Parametro;
                simbolo1 = "gal";
            }
            if (Firstvalue == 115) {
                Parametro = Parametro * 0.264172051241558;
                simbolo1 = "L";
            }
            if (Firstvalue == 116) {
                Parametro = Parametro * 264;
                simbolo1 = "m3";
            }
            if (Firstvalue == 117) {
                Parametro = Parametro * 0.517647058823529;
                simbolo1 = "Kg";
            }
            if (Firstvalue == 118) {
                Parametro = Parametro * 517.647058823529;
                simbolo1 = "Ton";
            }
            if (Firstvalue == 119) {
                Parametro = Parametro * 42;
                simbolo1 = "bl";
            }

            //Butano
            if (Firstvalue == 120) {
                Parametro = Parametro;
                simbolo1 = "gal";
            }
            if (Firstvalue == 121) {
                Parametro = Parametro * 0.264172051241558;
                simbolo1 = "L";
            }
            if (Firstvalue == 122) {
                Parametro = Parametro * 264;
                simbolo1 = "m3";
            }
            if (Firstvalue == 123) {
                Parametro = Parametro * 0.45360824742268;
                simbolo1 = "Kg";
            }
            if (Firstvalue == 124) {
                Parametro = Parametro * 453.60824742268;
                simbolo1 = "Ton";
            }
            if (Firstvalue == 125) {
                Parametro = Parametro * 42;
                simbolo1 = "bl";
            }

            //GLP
            if (Firstvalue == 126) {
                Parametro = Parametro;
                simbolo1 = "gal";
            }
            if (Firstvalue == 127) {
                Parametro = Parametro * 0.264172051241558;
                simbolo1 = "L";
            }
            if (Firstvalue == 128) {
                Parametro = Parametro * 264;
                simbolo1 = "m3";
            }
            if (Firstvalue == 129) {
                Parametro = Parametro * 0.50965250965251;
                simbolo1 = "Kg";
            }
            if (Firstvalue == 130) {
                Parametro = Parametro * 509.65250965251;
                simbolo1 = "Ton";
            }
            if (Firstvalue == 131) {
                Parametro = Parametro * 42;
                simbolo1 = "bl";
            }

            //Residual 6
            if (Firstvalue == 132) {
                Parametro = Parametro;
                simbolo1 = "gal";
            }
            if (Firstvalue == 133) {
                Parametro = Parametro * 0.264172051241558;
                simbolo1 = "L";
            }
            if (Firstvalue == 134) {
                Parametro = Parametro * 264;
                simbolo1 = "m3";
            }
            if (Firstvalue == 135) {
                Parametro = Parametro * 0.272164948453608;
                simbolo1 = "Kg";
            }
            if (Firstvalue == 136) {
                Parametro = Parametro * 272.164948453608;
                simbolo1 = "Ton";
            }
            if (Firstvalue == 137) {
                Parametro = Parametro * 42;
                simbolo1 = "bl";
            }

            //Residual 500
            if (Firstvalue == 138) {
                Parametro = Parametro;
                simbolo1 = "gal";
            }
            if (Firstvalue == 139) {
                Parametro = Parametro * 0.264172051241558;
                simbolo1 = "L";
            }
            if (Firstvalue == 140) {
                Parametro = Parametro * 264;
                simbolo1 = "m3";
            }
            if (Firstvalue == 141) {
                Parametro = Parametro * 0.269387755102041;
                simbolo1 = "Kg";
            }
            if (Firstvalue == 142) {
                Parametro = Parametro * 269.387755102041;
                simbolo1 = "Ton";
            }
            if (Firstvalue == 143) {
                Parametro = Parametro * 42;
                simbolo1 = "bl";
            }


            switch (Secondvalue) {
                case 36: //Btu
                    totalConvertirUnidades = Parametro;
                    simbolo = "Btu";
                    break;
                case 37: //kBtu
                    totalConvertirUnidades = Parametro / 1000;
                    simbolo = "kBtu";
                    break;
                case 38: //MillionBtu
                    totalConvertirUnidades = (Parametro / 1000) / 1000;
                    simbolo = "MBtu";
                    break;
                case 39: //Joule
                    totalConvertirUnidades = Parametro * 1055.06;
                    simbolo = "J";
                    break;
                case 40: //KiloJoule
                    totalConvertirUnidades = (Parametro * 1055.06) / 1000;
                    simbolo = "kJ";
                    break;
                case 41: //MegaJoule
                    totalConvertirUnidades = ((Parametro * 1055.06) / 1000) / 1000;
                    simbolo = "MJ";
                    break;
                case 42: //KwH
                    totalConvertirUnidades = (Parametro * 293.071) / 1000000;
                    simbolo = "KwH";
                    break;
                case 43: //Mwh
                    totalConvertirUnidades = ((Parametro * 293.071) / 1000000) / 1000;
                    simbolo = "Mwh";
                    break;
                case 44: //Kcal
                    totalConvertirUnidades = (Parametro / 4.184) * (1055.06 / 1000);
                    simbolo = "Kcal";
                    break;
                case 45: //Cal
                    totalConvertirUnidades = ((Parametro / 4.184) * (1055.06 / 1000)) * 1000;
                    simbolo = "Cal";
                    break;
                case 46: //Hp.Hr
                    totalConvertirUnidades = Parametro / 2544.5;
                    simbolo = "Hp.Hr";
                    break;
                case 47: //PCs
                    totalConvertirUnidades = Parametro / ArrayParam.get(1);
                    simbolo = "PCs";
                    break;
                case 48: //MPCs
                    totalConvertirUnidades = (Parametro / ArrayParam.get(1)) / 1000;
                    simbolo = "MPCs";
                    break;
                case 49: //MMPCs
                    totalConvertirUnidades = (Parametro / ArrayParam.get(1)) / 1000000;
                    simbolo = "MMPCs";
                    break;
                case 50: //m3s
                    totalConvertirUnidades = (Parametro / ArrayParam.get(1)) * Math.pow((12 * 0.0254), 3);
                    simbolo = "m3s";
                    break;
                case 999: //Mm3s
                    totalConvertirUnidades = ((Parametro / ArrayParam.get(1)) * Math.pow((12 * 0.0254), 3)) / 600 / 1000;
                    simbolo = "Mm3s";
                    break;
                case 51: //m3
                    totalConvertirUnidades = ((Parametro / ArrayParam.get(1)) * Math.pow((12 * 0.0254), 3)) / 600;
                    simbolo = "m3";
                    break;
                case 52: //PC
                    totalConvertirUnidades = ((Parametro / ArrayParam.get(1)) * Math.pow((12 * 0.0254), 3)) / 600 * 35.314;
                    simbolo = "PC";
                    break;
                case 53: //gal
                    totalConvertirUnidades = ((Parametro / ArrayParam.get(1)) * Math.pow((12 * 0.0254), 3)) / 600 * 264.17;
                    simbolo = "gal";
                    break;
                case 54: //kg
                    totalConvertirUnidades = ((Parametro / ArrayParam.get(1)) * Math.pow((12 * 0.0254), 3)) / 600 * 450;
                    simbolo = "kg";
                    break;
                case 55: //TON
                    totalConvertirUnidades = ((Parametro / ArrayParam.get(1)) * Math.pow((12 * 0.0254), 3)) / 600 * 450 / 1000;
                    simbolo = "TON";
                    break;
                case 56: //boe
                    totalConvertirUnidades = Parametro / ArrayParam.get(0) / 42;
                    simbolo = "boe";
                    break;
                case 57: //Gasolina 95
                    totalConvertirUnidades = Parametro / ArrayParam.get(2);
                    simbolo = "Gasolina 95";
                    break;
                case 58: // Diesel
                    totalConvertirUnidades = Parametro / ArrayParam.get(3);
                    simbolo = "Diesel";
                    break;
                case 59: // Kerosene
                    totalConvertirUnidades = Parametro / ArrayParam.get(4);
                    simbolo = "Kerosene";
                    break;
                case 60: // Propano
                    totalConvertirUnidades = Parametro / ArrayParam.get(5);
                    simbolo = "Propano";
                    break;
                case 61: // Butano
                    totalConvertirUnidades = Parametro / ArrayParam.get(6);
                    simbolo = "Butano";
                    break;
                case 62: // GLP
                    totalConvertirUnidades = Parametro / ArrayParam.get(7);
                    simbolo = "GLP";
                    break;
                case 63: // Residual 6
                    totalConvertirUnidades = Parametro / ArrayParam.get(8);
                    simbolo = "Residual 6";
                    break;
                case 64: // Residual 500
                    totalConvertirUnidades = Parametro / ArrayParam.get(9);
                    simbolo = "Residual 500";
                    break;
                case 65: // GLP	Balones
                    totalConvertirUnidades = Parametro / 10 * 514.2 / 264.172 / ArrayParam.get(7);
                    simbolo = "GLP Balones";
                    break;

                // Petroleo
                case 84: //Galones
                    totalConvertirUnidades = Parametro;
                    simbolo = "gal";
                    break;
                case 85: //Litros
                    if (Firstvalue == 86) {
                        totalConvertirUnidades = (Parametro / 264) * 1000;
                    } else {
                        totalConvertirUnidades = Parametro / 0.264172051241558;
                    }
                    simbolo = "litros";
                    break;
                case 86: // metros cúbicos
                    totalConvertirUnidades = Parametro / 264;
                    simbolo = "m3";
                    break;
                case 87: // Kilogramos
                    totalConvertirUnidades = Parametro / 0.301314487632509;
                    simbolo = "Kg";
                    break;
                case 88: //Toneladas
                    totalConvertirUnidades = Parametro / 301.314487632509;
                    simbolo = "Ton";
                    break;
                case 89: //Barrilees
                    totalConvertirUnidades = Parametro / 42;
                    simbolo = "Barril";
                    break;

                // Gasolina 95
                case 90: //Galones
                    totalConvertirUnidades = Parametro;
                    simbolo = "gal";
                    break;
                case 91: //Litros
                    if (Firstvalue == 92) {
                        totalConvertirUnidades = (Parametro / 264) * 1000;
                    } else {
                        totalConvertirUnidades = Parametro / 0.264172051241558;
                    }
                    simbolo = "litros";
                    break;
                case 92: // metros cúbicos
                    totalConvertirUnidades = Parametro / 264;
                    simbolo = "m3";
                    break;
                case 93: // Kilogramos
                    totalConvertirUnidades = Parametro / 0.388235294117647;
                    simbolo = "Kg";
                    break;
                case 94: //Toneladas
                    totalConvertirUnidades = Parametro / 388.235294117647;
                    simbolo = "Ton";
                    break;
                case 95: //Barrilees
                    totalConvertirUnidades = Parametro / 42;
                    simbolo = "Barril";
                    break;

                // Diesel
                case 96: //Galones
                    totalConvertirUnidades = Parametro;
                    simbolo = "gal";
                    break;
                case 97: //Litros
                    if (Firstvalue == 98) {
                        totalConvertirUnidades = (Parametro / 264) * 1000;
                    } else {
                        totalConvertirUnidades = Parametro / 0.264172051241558;
                    }
                    simbolo = "litros";
                    break;
                case 98: // metros cúbicos
                    totalConvertirUnidades = Parametro / 264;
                    simbolo = "m3";
                    break;
                case 99: // Kilogramos
                    totalConvertirUnidades = Parametro / 0.317307692307692;
                    simbolo = "Kg";
                    break;
                case 100: //Toneladas
                    totalConvertirUnidades = Parametro / 317.307692307692;
                    simbolo = "Ton";
                    break;
                case 101: //Barrilees
                    totalConvertirUnidades = Parametro / 42;
                    simbolo = "Barril";
                    break;

                // Kerosene
                case 108: //Galones
                    totalConvertirUnidades = Parametro;
                    simbolo = "gal";
                    break;
                case 109: //Litros
                    if (Firstvalue == 110) {
                        totalConvertirUnidades = (Parametro / 264) * 1000;
                    } else {
                        totalConvertirUnidades = Parametro / 0.264172051241558;
                    }
                    simbolo = "litros";
                    break;
                case 110: // metros cúbicos
                    totalConvertirUnidades = Parametro / 264;
                    simbolo = "m3";
                    break;
                case 111: // Kilogramos
                    totalConvertirUnidades = Parametro / 0.352;
                    simbolo = "Kg";
                    break;
                case 112: //Toneladas
                    totalConvertirUnidades = Parametro / 352;
                    simbolo = "Ton";
                    break;
                case 113: //Barrilees
                    totalConvertirUnidades = Parametro / 42;
                    simbolo = "Barril";
                    break;

                // Propano
                case 114: //Galones
                    totalConvertirUnidades = Parametro;
                    simbolo = "gal";
                    break;
                case 115: //Litros
                    if (Firstvalue == 116) {
                        totalConvertirUnidades = (Parametro / 264) * 1000;
                    } else {
                        totalConvertirUnidades = Parametro / 0.264172051241558;
                    }
                    simbolo = "litros";
                    break;
                case 116: // metros cúbicos
                    totalConvertirUnidades = Parametro / 264;
                    simbolo = "m3";
                    break;
                case 117: // Kilogramos
                    totalConvertirUnidades = Parametro / 0.517647058823529;
                    simbolo = "Kg";
                    break;
                case 118: //Toneladas
                    totalConvertirUnidades = Parametro / 517.647058823529;
                    simbolo = "Ton";
                    break;
                case 119: //Barrilees
                    totalConvertirUnidades = Parametro / 42;
                    simbolo = "Barril";
                    break;

                // Propano
                case 120: //Galones
                    totalConvertirUnidades = Parametro;
                    simbolo = "gal";
                    break;
                case 121: //Litros
                    if (Firstvalue == 122) {
                        totalConvertirUnidades = (Parametro / 264) * 1000;
                    } else {
                        totalConvertirUnidades = Parametro / 0.264172051241558;
                    }
                    simbolo = "litros";
                    break;
                case 122: // metros cúbicos
                    totalConvertirUnidades = Parametro / 264;
                    simbolo = "m3";
                    break;
                case 123: // Kilogramos
                    totalConvertirUnidades = Parametro / 0.45360824742268;
                    simbolo = "Kg";
                    break;
                case 124: //Toneladas
                    totalConvertirUnidades = Parametro / 453.60824742268;
                    simbolo = "Ton";
                    break;
                case 125: //Barrilees
                    totalConvertirUnidades = Parametro / 42;
                    simbolo = "Barril";
                    break;


                // GLP
                case 126: //Galones
                    totalConvertirUnidades = Parametro;
                    simbolo = "gal";
                    break;
                case 127: //Litros
                    if (Firstvalue == 128) {
                        totalConvertirUnidades = (Parametro / 264) * 1000;
                    } else {
                        totalConvertirUnidades = Parametro / 0.264172051241558;
                    }
                    simbolo = "litros";
                    break;
                case 128: // metros cúbicos
                    totalConvertirUnidades = Parametro / 264;
                    simbolo = "m3";
                    break;
                case 129: // Kilogramos
                    totalConvertirUnidades = Parametro / 0.50965250965251;
                    simbolo = "Kg";
                    break;
                case 130: //Toneladas
                    totalConvertirUnidades = Parametro / 509.65250965251;
                    simbolo = "Ton";
                    break;
                case 131: //Barrilees
                    totalConvertirUnidades = Parametro / 42;
                    simbolo = "Barril";
                    break;

                // Residual 6
                case 132: //Galones
                    totalConvertirUnidades = Parametro;
                    simbolo = "gal";
                    break;
                case 133: //Litros
                    if (Firstvalue == 134) {
                        totalConvertirUnidades = (Parametro / 264) * 1000;
                    } else {
                        totalConvertirUnidades = Parametro / 0.264172051241558;
                    }
                    simbolo = "litros";
                    break;
                case 134: // metros cúbicos
                    totalConvertirUnidades = Parametro / 264;
                    simbolo = "m3";
                    break;
                case 135: // Kilogramos
                    totalConvertirUnidades = Parametro / 0.272164948453608;
                    simbolo = "Kg";
                    break;
                case 136: //Toneladas
                    totalConvertirUnidades = Parametro / 272.164948453608;
                    simbolo = "Ton";
                    break;
                case 137: //Barrilees
                    totalConvertirUnidades = Parametro / 42;
                    simbolo = "Barril";
                    break;

                // Residual 500
                case 138: //Galones
                    totalConvertirUnidades = Parametro;
                    simbolo = "gal";
                    break;
                case 139: //Litros
                    if (Firstvalue == 140) {
                        totalConvertirUnidades = (Parametro / 264) * 1000;
                    } else {
                        totalConvertirUnidades = Parametro / 0.264172051241558;
                    }
                    simbolo = "litros";
                    break;
                case 140: // metros cúbicos
                    totalConvertirUnidades = Parametro / 264;
                    simbolo = "m3";
                    break;
                case 141: // Kilogramos
                    totalConvertirUnidades = Parametro / 0.269387755102041;
                    simbolo = "Kg";
                    break;
                case 142: //Toneladas
                    totalConvertirUnidades = Parametro / 269.387755102041;
                    simbolo = "Ton";
                    break;
                case 143: //Barrilees
                    totalConvertirUnidades = Parametro / 42;
                    simbolo = "Barril";
                    break;

            }

            button_delete_show(root);

            NumberFormat f = NumberFormat.getInstance();
            f.setMaximumFractionDigits(2);

            TextView convert_units_to = root.findViewById(R.id.textview_units_to);

            convert_units_to.setText(f.format(totalConvertirUnidades));
            convert_units_to.setTextColor(Color.BLACK);
        }
        else
            Toast.makeText(getActivity(), "Ingrese cantidad a convertir", Toast.LENGTH_LONG).show();

    }

}
