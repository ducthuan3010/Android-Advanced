package edu.eiu.tourist_app;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import edu.eiu.tourist_app.datamodel.WikipediaPage;

public class TouristRecyclerAdapter extends RecyclerView.Adapter<TouristRecyclerAdapter.TouristViewHolder> {
    private List<WikipediaPage> touristItems;

    public TouristRecyclerAdapter(List<WikipediaPage> items) {
        this.touristItems = items;
    }

    @NonNull
    @Override
    public TouristViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.tourist_list_item, parent, false);
        TouristViewHolder viewHolder = new TouristViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TouristViewHolder holder, int position) {
        WikipediaPage site = touristItems.get(position);
        holder.bindView(site);
    }

    @Override
    public int getItemCount() {
        return touristItems.size();
    }

    public static class TouristViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout constraintLayout;
        private TextView textView;
        private ImageView imageView;

        public TouristViewHolder(View view) {
            super(view);
            this.constraintLayout = (ConstraintLayout) view;
            this.textView = view.findViewById(R.id.textView);
            this.imageView = view.findViewById(R.id.imageView);
        }

        private void bindView(WikipediaPage itemText) {
            textView.setText(itemText.getTitle());
            // itemText.getThumbnail().getSource();
            if (itemText.getThumbnail() != null && !TextUtils.isEmpty(itemText.getThumbnail().getSource())) {
                Glide.with(constraintLayout)
                        .load(itemText.getThumbnail().getSource())
                        .apply(RequestOptions.circleCropTransform()
                                .error(android.R.drawable.ic_dialog_alert)
                                .placeholder(android.R.drawable.btn_star))

                        .into(this.imageView);
            } else
                Glide.with(constraintLayout)
                        .load(android.R.drawable.ic_dialog_alert)
                        .apply(RequestOptions.circleCropTransform()
                                .error(android.R.drawable.ic_dialog_alert)
                                .placeholder(android.R.drawable.btn_star))

                        .into(this.imageView);

        }
    }
}
