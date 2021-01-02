package android.eservices.countries.presentation.countrydisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.eservices.countries.R;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.squareup.picasso.Picasso;

/**
 * It is the second activity that display a detail for a specific country
 */

public class country_activity extends AppCompatActivity {

    private ImageView thumbnailImageView;
    private TextView nameTextView;
    private TextView regionTextView;
    private TextView capitalTextView;
    private TextView populationTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_activity);

        Intent intent = getIntent();

        nameTextView = findViewById(R.id.name);
        regionTextView = findViewById(R.id.region);
        capitalTextView = findViewById(R.id.capital);
        populationTextView = findViewById(R.id.population);
        thumbnailImageView = findViewById(R.id.image);

        String name = intent.getExtras().getString("name");
        String region = intent.getExtras().getString("region");
        String urlPoster = intent.getExtras().getString("thumbnail");
        String capital = intent.getExtras().getString("capital");
        Long population = intent.getExtras().getLong("population");

        nameTextView.setText(name);
        regionTextView.setText(region);
        capitalTextView.setText(capital);
        populationTextView.setText(population.toString());
        GlideToVectorYou
                .init()
                .with(capitalTextView.getContext())
                .load(Uri.parse(urlPoster),thumbnailImageView);
    }

}
