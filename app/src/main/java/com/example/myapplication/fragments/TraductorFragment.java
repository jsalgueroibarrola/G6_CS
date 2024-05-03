package com.example.myapplication.fragments;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.LanguageDetection;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.Objects;

public class TraductorFragment extends Fragment {

    private EditText multi2;
    private String idiomaOrigin;
    private String idiomaDestination;
    private TextView charCounter;

    public static TraductorFragment newInstance() {
        return new TraductorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.translator_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView();
    }

    public void setupView() {
        Spinner spinnerOrigin = requireView().findViewById(R.id.spinner_origin_lang);
        Spinner spinnerDestination = requireView().findViewById(R.id.spinner_destination_lang);

        // Asignar variable al contador de caracteres
        charCounter = requireView().findViewById(R.id.charCounter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.requireActivity(), R.array.languages_array_origin, android.R.layout.simple_dropdown_item_1line);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this.requireActivity(), R.array.languages_array_destination, android.R.layout.simple_dropdown_item_1line);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigin.setAdapter(adapter);
        spinnerDestination.setAdapter(adapter2);

        // Configura el listener para el Spinner
        spinnerOrigin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                idiomaOrigin = parentView.getItemAtPosition(position).toString();
                // "seleccion" contiene el texto del elemento seleccionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Manejar la ausencia de selección si es necesario
            }
        });

        // Configura el listener para el Spinner
        spinnerDestination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                idiomaDestination = parentView.getItemAtPosition(position).toString();
                // "seleccion" contiene el texto del elemento seleccionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Manejar la ausencia de selección si es necesario
            }
        });

        // Listener para el contador de caracteres sobre el cuadro de texto de origen
        EditText multi1 = requireView().findViewById(R.id.multiText1);

        multi1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //antes de escribir en el traductor
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // COntar cuantos caracteres hay en el cuadro de texto
                int charCount = multi1.length();

                // Convertir el contador a String
                String charCountString = String.valueOf(charCount);

                // Mostrar el contador en el TextView
                charCounter.setText(charCountString + "/2000");

                // Si el contador supera los 1500 caracteres, cambiar el color del contador a rojo
                if (charCount > 1500) {
                    charCounter.setTextColor(ContextCompat.getColor(requireContext(), R.color.accent));
                } else {
                    charCounter.setTextColor(ContextCompat.getColor(requireContext(), R.color.less_smoke));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                //despues de haber introducido la frase/palabra a traducir
            }
        });

        Button button = (Button) requireView().findViewById(R.id.buttonTrad);
        button.setOnClickListener(this::buttonPressed);

        ImageButton imageButton = (ImageButton) requireView().findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this::copyButtonPressed);
    }

    public void buttonPressed(View view) {
        EditText multi1 = requireView().findViewById(R.id.multiText1);
        multi2 = requireView().findViewById(R.id.multiText2);
        String text = multi1.getText().toString();

        if (text.isEmpty()) {
            Toast.makeText(this.requireActivity(), "No hay texto para traducir", Toast.LENGTH_SHORT).show();
        } else {
            hideSoftKeyboard(this.requireActivity());
            if (this.idiomaOrigin.equals("AUTO")) {
                LanguageDetection languageDetection = new LanguageDetection();
                this.idiomaOrigin = languageDetection.detect(text);
                // Cambiar valor de spinner origin lang

                Spinner spinnerOriginTr = requireView().findViewById(R.id.spinner_origin_lang);
                for (int i = 0; i < 11; i++) {
                    if (spinnerOriginTr.getItemAtPosition(i).equals(this.idiomaOrigin)) {
                        spinnerOriginTr.setSelection(i);
                    }
                }
            }
            String trText = MainActivity.translator.traducir(this.idiomaOrigin, this.idiomaDestination, text);
            multi2.setText(trText);
        }
    }

    public void copyButtonPressed(View view) {
        multi2 = requireView().findViewById(R.id.multiText2);
        String text2 = multi2.getText().toString();

        if (text2.isEmpty()) {
            Toast.makeText(this.requireActivity(), "No hay texto para copiar", Toast.LENGTH_SHORT).show();
        } else {
            ClipboardManager clipboard = (ClipboardManager) requireActivity().getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Label", text2);
            clipboard.setPrimaryClip(clip);
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken(), 0);
        }
    }

}