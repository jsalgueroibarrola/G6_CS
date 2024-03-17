package com.example.myapplication.fragments;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.aboutDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SettingsFragment extends Fragment {

    int position = 0;
    private String[] direccionPre = {"a.velascom.2021@alumnos.urjc.es","a.roldan.2021@alumnos.urjc.es","p.torrecilla.2021@alumnos.urjc.es","e.tentor.2021@alumnos.urjc.es","ca.vlad.2021@alumnos.urjc.es","i.ruizba.2021@alumnos.urjc.es"};


    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout aboutButton = requireView().findViewById(R.id.autoresButton);
        aboutButton.setOnClickListener(this::aboutButtonPressed);
        LinearLayout darkModeButton = requireView().findViewById(R.id.darkModeOptionButton);
        darkModeButton.setOnClickListener(this::darkModeButtonPressed);
        LinearLayout emailAdress = requireView().findViewById(R.id.mailButton);
        emailAdress.setOnClickListener(this::clickAndSendEmail);
    }
    public void clickAndSendEmail(View v){
        sendEmail(direccionPre);
    }
    public void sendEmail(String[] direccion){
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:"));
        email.putExtra(Intent.EXTRA_EMAIL,direccion);
        startActivity(email);

    }

    public void aboutButtonPressed(View view) {
        openDialog();
    }

    public void darkModeButtonPressed(View view) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());

        String[] list = getActivity().getResources().getStringArray(R.array.choicesForDarkModeDialog);

        int currentNightMode = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;

        switch (currentNightMode) {
            case android.content.res.Configuration.UI_MODE_NIGHT_NO:
                position = 2;
                break;
            case android.content.res.Configuration.UI_MODE_NIGHT_YES:
                position = 1;
                break;
            case android.content.res.Configuration.UI_MODE_NIGHT_UNDEFINED:
                position = 0;
                break;
            default:
                position = -1;
                break;
        }

        builder.setTitle("Modo oscuro")
                .setSingleChoiceItems(list, position, (dialog, which) -> position = which)
                .setPositiveButton("Ok", (dialog, which) -> {
                    if (position == 0) {
                        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    } else if (position == 2) {
                        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    } else if (position == 1) {
                        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
                })
                .setNegativeButton("Cancelar", (dialog, whichButton) -> {
                        // Do nothing
                });

        builder.show();
    }

    public void openDialog() {
        aboutDialog dialog = new aboutDialog();
        dialog.show(this.requireActivity().getSupportFragmentManager(), "aboutDialog");
    }



}