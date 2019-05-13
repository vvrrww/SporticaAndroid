package com.example.sportica;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

import static java.sql.DriverManager.println;

public class NavActivity extends AppCompatActivity {
    TextView myTV;
    String json;
    BottomNavigationView nav1;
    FrameLayout main_frame;

    NewsFragment newsFragment;
    TournamentFragment tournamentFragment;
    CalenderFragment accountFragment;

    static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        // Welcome text
        myTV = this.findViewById(R.id.myTV);
        if(getIntent().hasExtra("com.example.sportica.json")){
            try{
                json = getIntent().getExtras().getString("com.example.sportica.json");
                JSONObject user = new JSONObject(json);
                myTV.setText("Welcome back! "+user.getString("firstname")+" "+user.getString("lastname"));
                id = user.getString("ouid");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        nav1 = this.findViewById(R.id.nav1);
        main_frame = this.findViewById(R.id.main_frame);

        newsFragment = new NewsFragment();
        tournamentFragment = new TournamentFragment();
        accountFragment = new CalenderFragment();

        setFragment(newsFragment);

        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){

                    case R.id.nav_news:
                        setFragment(newsFragment);
                        return true;
                    case R.id.nav_tournament:
                        setFragment(tournamentFragment);
                        return true;
                    case R.id.nav_calendar:
                        setFragment(accountFragment);
                        return true;

                        default: return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    public static class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImage(ImageView bmImage) {
            this.bmImage = (ImageView ) bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.d("Error", e.getStackTrace().toString());
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public static String getID() {
        return id;
    }
}
