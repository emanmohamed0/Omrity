package com.app.emaneraky.Omrity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView detailrecycle;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    List<DetailOffer> detailOfferList;
    String image = "https://firebasestorage.googleapis.com/v0/b/meta-iterator-149219.appspot.com/o/message_images%2F-L4c721PjxEuIWd8DEug.jpg?alt=media&token=0bd707c9-8379-45a8-b43e-8b3c62b49205";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        detailrecycle = (RecyclerView) findViewById(R.id.recycle_detail);
        detailrecycle.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        detailrecycle.setLayoutManager(layoutManager);

        detailOfferList = new ArrayList<>();
        detailOfferList.add(new DetailOffer(image, "company_name", "bus", "food", "price", "hotels"));

        recyclerViewAdapter = new RecyclerViewAdapter(detailOfferList, getBaseContext());
        detailrecycle.setAdapter(recyclerViewAdapter);
    }
}
