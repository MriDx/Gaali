package com.mridx.gaali.helper;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mridx.gaali.data.DataToUpload;
import com.mridx.gaali.data.Gaali;
import com.mridx.gaali.data.GaaliToShow;
import com.mridx.gaali.ui.GaaliAddUI;
import com.mridx.gaali.ui.MainUI;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainUIHelper {

    private Context context;
    private MainUI mainUI;
    private FirebaseFirestore firebaseFirestore;
    private GaaliAddUI gaaliAddUI;

    private ArrayList<GaaliToShow> gaaliList = new ArrayList<>();

    public MainUIHelper(Context context) {
        this.context = context;
        mainUI = ((MainUI) context);
    }

    public void start() {
        initFireStore();
        loadData();
        mainUI.gaaliAdapter.setOnGaaliClicked(this::onGaaliClicked);
    }


    private void initFireStore() {
        if (firebaseFirestore == null)
            firebaseFirestore = FirebaseFirestore.getInstance();
    }

    private void loadData() {
        gaaliList.clear();
        Query query = firebaseFirestore.collection("gaali").document("all").collection("gaalis")
                .orderBy("date", Query.Direction.DESCENDING);
        query
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot snapshot : task.getResult()) {
                            gaaliList.add(new GaaliToShow(String.valueOf(snapshot.get("gaali")), String.valueOf(snapshot.get("desc")), String.valueOf(snapshot.get("creator"))));
                        }
                        mainUI.gaaliAdapter.injectList(gaaliList);
                    } else {
                        Log.d("kaku", "loadData: something went wrong");
                    }
                });
    }

    public void openAddApp() {
        if (gaaliAddUI == null) {
            gaaliAddUI = new GaaliAddUI();
            gaaliAddUI.setOnGaaliSave(this::onSaveGaali);
        }
        gaaliAddUI.show(mainUI.getSupportFragmentManager(), "Gaali Add UI");

    }

    private void onSaveGaali(Gaali gaali) {

        firebaseFirestore.collection("gaali").document("all").collection("gaalis")
                .add(new DataToUpload(gaali.gaali, gaali.desc, "mridx", FieldValue.serverTimestamp()));
    }


    private void onGaaliClicked(GaaliToShow gaaliToShow) {
    }

}
