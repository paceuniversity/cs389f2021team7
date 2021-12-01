package com.example.carbonfootprint;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterSettings extends RecyclerView.Adapter<RecyclerViewAdapterSettings.ViewHolderRecyclerViewSettings> {
    ArrayList<CardViewSettings> SettingsArray;
    OnItemClickListenerSettings listener;
    public interface OnItemClickListenerSettings {
        void onItemClick(int position);
    }

    public void setOnItemClickListenerSettings(OnItemClickListenerSettings onItemClickListenerSettings) {
        listener = onItemClickListenerSettings;
    }

    public static class ViewHolderRecyclerViewSettings extends RecyclerView.ViewHolder {
        public ImageView image2;
        public TextView topText2;
        public TextView bottomText2;
        public ImageView imageDelete2;

        public ViewHolderRecyclerViewSettings(@NonNull View itemView, OnItemClickListenerSettings onItemClickListenerSettings) {
            super(itemView);
            image2 = itemView.findViewById(R.id.image2);
            topText2 = itemView.findViewById(R.id.topText2);
//            bottomText2 = itemView.findViewById(R.id.bottomText2);
//            imageDelete2 = itemView.findViewById(R.id.imageDelete2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListenerSettings != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListenerSettings.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    public RecyclerViewAdapterSettings(ArrayList<CardViewSettings> cardViewSettings) {
        SettingsArray = cardViewSettings;
    }

    @NonNull
    @Override
    public ViewHolderRecyclerViewSettings onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_activity_settings, parent, false);
        ViewHolderRecyclerViewSettings viewHolderRecyclerViewSettings = new ViewHolderRecyclerViewSettings(view, listener);
        return viewHolderRecyclerViewSettings;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecyclerViewSettings holder, int position) {
        CardViewSettings currentItem = SettingsArray.get(position);
        holder.image2.setImageResource(currentItem.getCardImage());
        holder.topText2.setText(currentItem.getCardTopText());
//        holder.bottomText2.setText(currentItem.getCardBottomText());
    }

    @Override
    public int getItemCount() {
        return SettingsArray.size();
    }
}
