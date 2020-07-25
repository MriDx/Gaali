package com.mridx.gaali.ui;

import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Half;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mridx.gaali.R;
import com.mridx.gaali.adapter.GaaliAdapter;
import com.mridx.gaali.helper.MainUIHelper;

public class MainUI extends AppCompatActivity {

    private RecyclerView gaaliHolder;
    private ExtendedFloatingActionButton addGaaliFab;
    private Toolbar toolbar;

    private int width;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    private FirebaseFirestore firebaseFirestore;

    private MainUIHelper helper;
    public GaaliAdapter gaaliAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_holder);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Gaali");

        addGaaliFab = findViewById(R.id.addGaaliFab);

        addGaaliFab.setOnClickListener(this::openAddGaali);

        gaaliHolder = findViewById(R.id.gaaliHolder);
        gaaliAdapter = new GaaliAdapter();
        gaaliHolder.setAdapter(gaaliAdapter);
        gaaliHolder.setLayoutManager(new GridLayoutManager(this, 1));

        gaaliHolder.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && addGaaliFab.getVisibility() == View.VISIBLE) {
                    addGaaliFab.hide();
                } else if (dy < 0 && addGaaliFab.getVisibility() != View.VISIBLE) {
                    addGaaliFab.show();
                }
            }
        });

        setupDrawer();
        helper = new MainUIHelper(this);
        helper.start();

    }


    private void setupDrawer() {
        if (drawerLayout == null)
            drawerLayout = findViewById(R.id.drawer_layout);
        if (toggle == null)
            toggle = new ActionBarDrawerToggle(
                    this,
                    drawerLayout,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            );
        drawerLayout.addDrawerListener(toggle);
        if (navigationView == null)
            navigationView = findViewById(R.id.nav_view);
        if (width == 0) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            width = (int) (displayMetrics.widthPixels * 0.85);
        }
        ViewGroup.LayoutParams layoutParams = navigationView.getLayoutParams();
        layoutParams.width = width;
        navigationView.setLayoutParams(layoutParams);

        toggle.syncState();
    }


    private void openAddGaali(View view) {
        helper.openAddApp();
    }
}
