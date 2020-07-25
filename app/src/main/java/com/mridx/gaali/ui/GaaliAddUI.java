package com.mridx.gaali.ui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.mridx.gaali.R;
import com.mridx.gaali.data.Gaali;

public class GaaliAddUI extends DialogFragment {


    private MaterialButton saveGaaliBtn;
    private TextInputEditText gaaliField, gaaliDescField;

    OnGaaliSave onGaaliSave;

    public interface OnGaaliSave {
        void onSaveGaali(Gaali gaali);
    }

    public void setOnGaaliSave(OnGaaliSave onGaaliSave) {
        this.onGaaliSave = onGaaliSave;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_gaali_view, container, false);
        getDialog().getWindow().getAttributes().gravity = Gravity.FILL_HORIZONTAL;
        //return super.onCreateView(inflater, container, savedInstanceState);
        setupView(view);
        return view;
    }

    private void setupView(View view) {
        saveGaaliBtn = view.findViewById(R.id.saveGaaliBtn);
        gaaliField = view.findViewById(R.id.gaaliField);
        gaaliDescField = view.findViewById(R.id.gaaliDescField);
        saveGaaliBtn.setOnClickListener(this::proceedSave);
    }

    private void proceedSave(View view) {
        String gaali = gaaliField.getText().toString().trim();
        if (gaali.length() == 0) {
            return;
        }
        onGaaliSave.onSaveGaali(new Gaali(gaali, gaaliDescField.getText().toString().trim()));
    }


}
