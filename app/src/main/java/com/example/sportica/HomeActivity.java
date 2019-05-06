package com.example.sportica;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {


    BottomNavigationView nav1;
    FrameLayout main_frame;

    NewsFragment newsFragment;
    TournamentFragment tournamentFragment;
    AccountFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        Intent intent = getIntent();

        nav1 = this.findViewById(R.id.nav1);
        main_frame = this.findViewById(R.id.main_frame);

        newsFragment = new NewsFragment();
        tournamentFragment = new TournamentFragment();
        accountFragment = new AccountFragment();

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
                    case R.id.nav_account:
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
}
