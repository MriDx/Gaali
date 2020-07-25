package com.mridx.gaali.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.mridx.gaali.R;
import com.mridx.gaali.data.Gaali;
import com.mridx.gaali.data.GaaliToShow;

import java.util.ArrayList;

public class GaaliAdapter extends RecyclerView.Adapter<GaaliAdapter.MyViewHolder> {

    OnGaaliClicked onGaaliClicked;

    public interface OnGaaliClicked {
        void setOnGaaliClicked(GaaliToShow gaaliClicked);
    }

    public void setOnGaaliClicked(OnGaaliClicked onGaaliClicked) {
        this.onGaaliClicked = onGaaliClicked;
    }

    private ArrayList<GaaliToShow> gaaliList = new ArrayList<>();

    @NonNull
    @Override
    public GaaliAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gaali_view, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GaaliAdapter.MyViewHolder holder, int position) {
        GaaliToShow gaaliToShow = gaaliList.get(position);
        holder.gaaliView.setText(gaaliToShow.getGaali());
        holder.gaaliProviderView.setText("By- " + gaaliToShow.getCreator());
        holder.itemView.setOnClickListener(view -> onGaaliClicked.setOnGaaliClicked(gaaliToShow));
    }

    @Override
    public int getItemCount() {
        return gaaliList.size();
    }

    public void injectList(ArrayList<GaaliToShow> gaaliList) {
        this.gaaliList = gaaliList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView gaaliView, gaaliProviderView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            gaaliView = itemView.findViewById(R.id.gaaliShortView);
            gaaliProviderView = itemView.findViewById(R.id.gaaliProviderView);
        }
    }
}
