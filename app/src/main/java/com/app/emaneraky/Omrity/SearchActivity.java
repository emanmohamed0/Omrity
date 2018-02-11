package com.app.emaneraky.Omrity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.app.emaneraky.Omrity.Model.SearchDetail;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerViewSearch;
    RecyclerSearchAdapter searchAdapter;
    List<SearchDetail> searchDetails;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Companies");

        recyclerViewSearch = (RecyclerView) findViewById(R.id.search_recycler);
        recyclerViewSearch.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewSearch.setLayoutManager(layoutManager);

        searchDetails = new ArrayList<>();
        searchDetails.add(new SearchDetail("Emy Mohamed", "23 years old", "https://firebasestorage.googleapis.com/v0/b/meta-iterator-149219.appspot.com/o/message_images%2F-L4c721PjxEuIWd8DEug.jpg?alt=media&token=0bd707c9-8379-45a8-b43e-8b3c62b49205"));
        searchDetails.add(new SearchDetail("Jousef", "25 years old", "https://firebasestorage.googleapis.com/v0/b/meta-iterator-149219.appspot.com/o/message_images%2F-L4c721PjxEuIWd8DEug.jpg?alt=media&token=0bd707c9-8379-45a8-b43e-8b3c62b49205"));
        searchDetails.add(new SearchDetail("Eman Mohamed", "35 years old", "https://firebasestorage.googleapis.com/v0/b/meta-iterator-149219.appspot.com/o/message_images%2F-L4c721PjxEuIWd8DEug.jpg?alt=media&token=0bd707c9-8379-45a8-b43e-8b3c62b49205"));

        searchAdapter = new RecyclerSearchAdapter( getBaseContext(),searchDetails);
        recyclerViewSearch.setAdapter(searchAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_manu, menu);

//        MenuItem search = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        search(searchView);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (searchAdapter != null)
                    searchAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }


}
