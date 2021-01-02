package android.eservices.countries.presentation.countrydisplay.list.adapter;

import android.content.Context;
import android.content.Intent;
import android.eservices.countries.data.api.model.Country;
import android.eservices.countries.presentation.countrydisplay.country_activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.eservices.countries.R;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import java.util.ArrayList;
import java.util.List;

/**
 * It is our adapter that manage displayed countries.
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    public static class CountryViewHolder extends RecyclerView.ViewHolder {

        public ImageView thumbnailImageView;
        public TextView nameTextView;
        public TextView regionTextView;
        private View v;
        private Country countryItemViewModel;
        CardView cardView;


        public CountryViewHolder(View v) {
            super(v);
            nameTextView = v.findViewById(R.id.name_text_view);
            regionTextView = v.findViewById(R.id.region);
            thumbnailImageView = v.findViewById(R.id.thumbnail_image_view);
            cardView = v.findViewById(R.id.card_view_id);
            this.v = v;
        }


        void bind(Country countryItemViewModel) {
            this.countryItemViewModel = countryItemViewModel;
            nameTextView.setText(countryItemViewModel.getName());
            regionTextView.setText(countryItemViewModel.getRegion());
            String urlPoster = countryItemViewModel.getFlag();
            GlideToVectorYou
                    .init()
                    .with(v.getContext())
                    .load(Uri.parse(urlPoster),thumbnailImageView);
        }

    }

    private List<Country> countryItemViewModelList;
    private Context context;


    // Provide a suitable constructor (depends on the kind of dataset)
    public CountryAdapter() {
        countryItemViewModelList = new ArrayList<>();
    }

    public void bindViewModels(List<Country> countryItemViewModelList) {
        this.countryItemViewModelList.clear();
        this.countryItemViewModelList.addAll(countryItemViewModelList);
        notifyDataSetChanged();
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_list_content, parent, false);
        CountryViewHolder countryViewHolder = new CountryViewHolder(v);
        return countryViewHolder;
    }


    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        holder.bind(countryItemViewModelList.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, country_activity.class);
                intent.putExtra("name",countryItemViewModelList.get(position).getName());
                intent.putExtra("capital",countryItemViewModelList.get(position).getCapital());
                intent.putExtra("area",countryItemViewModelList.get(position).getArea());
                intent.putExtra("thumbnail",countryItemViewModelList.get(position).getFlag());
                intent.putExtra("population",countryItemViewModelList.get(position).getPopulation());
                intent.putExtra("region",countryItemViewModelList.get(position).getRegion());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryItemViewModelList.size();
    }

}
