package com.example.sportica;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class NewsDetailActivity extends AppCompatActivity {

    ImageView dim1;
    TextView dt1;
    TextView dt2;
    TextView dt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        dim1 = this.findViewById(R.id.dim1);
        dt1 = this.findViewById(R.id.dt1);
        dt2 = this.findViewById(R.id.dt2);
        dt3 = this.findViewById(R.id.dt3);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");
        String long_detail = intent.getStringExtra("long_detail");
        String url = intent.getStringExtra("url");
        new NavActivity.DownloadImage(dim1).execute(url);
        dt1.setTextSize(30);
        dt1.setText(title);
        dt2.setText(long_detail);
        dt3.setText(date);
    }
}
