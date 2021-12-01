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

public class RecyclerViewAdapterPastResults extends RecyclerView.Adapter<RecyclerViewAdapterPastResults.ViewHolderRecyclerView> {
    ArrayList<CardViewPastResults> PastResultsArray;
    OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        listener = onItemClickListener;
    }

    public static class ViewHolderRecyclerView extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView topText;
        public TextView bottomText;
        public ImageView imageDelete;

        public ViewHolderRecyclerView(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            topText = itemView.findViewById(R.id.topText);
            bottomText = itemView.findViewById(R.id.bottomText);
            imageDelete = itemView.findViewById(R.id.imageDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
            imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onDeleteClick(position);
                        }
                    }
                }
            });

        }
    }

    public RecyclerViewAdapterPastResults(ArrayList<CardViewPastResults> cardViewPastResults) {
        PastResultsArray = cardViewPastResults;
    }

    @NonNull
    @Override
    public ViewHolderRecyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_activity_past_results, parent, false);
        ViewHolderRecyclerView viewHolderRecyclerView = new ViewHolderRecyclerView(view, listener);
        return viewHolderRecyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecyclerView holder, int position) {
        CardViewPastResults currentItem = PastResultsArray.get(position);
        holder.image.setImageResource(currentItem.getCardImage());
        holder.topText.setText(currentItem.getCardTopText());
        holder.bottomText.setText(currentItem.getCardBottomText());
    }

    @Override
    public int getItemCount() {
        return PastResultsArray.size();
    }
}
