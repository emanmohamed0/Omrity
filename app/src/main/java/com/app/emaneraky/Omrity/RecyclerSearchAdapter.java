package com.app.emaneraky.Omrity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.emaneraky.Omrity.Model.SearchDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerSearchAdapter extends RecyclerView.Adapter<RecyclerSearchAdapter.MyViewHolder>
        implements Filterable {
    private Context context;
    private List<SearchDetail> searchList;
    private List<SearchDetail> searchListFiltered;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, detail;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.city_displayname);
            detail = view.findViewById(R.id.detail_city);
            thumbnail = view.findViewById(R.id.city_img);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
//                    listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public RecyclerSearchAdapter(Context context, List<SearchDetail> searchList) {
        this.context = context;

        this.searchList = searchList;
        this.searchListFiltered = searchList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final SearchDetail contact = searchListFiltered.get(position);
        holder.name.setText(contact.getName());
        holder.detail.setText(contact.getDetail());

        Picasso.with(context).load(contact.getImage()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return searchListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    searchListFiltered = searchList;
                } else {
                    List<SearchDetail> filteredList = new ArrayList<>();
                    for (SearchDetail row : searchList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getDetail().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    searchListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = searchListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                searchListFiltered = (ArrayList<SearchDetail>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
