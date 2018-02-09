package com.app.emaneraky.Omrity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DetailViewHolder> {
    List<DetailOffer> detailOfferList;
    Context context;

    public RecyclerViewAdapter(List<DetailOffer> detailOfferList, Context context) {
        this.detailOfferList = detailOfferList;
        this.context = context;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false);

        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        DetailOffer detailOffer = detailOfferList.get(position);
        holder.displayname.setText(detailOffer.getCompany_name());
        holder.bus.setText(detailOffer.getBus());
        holder.hotel.setText(detailOffer.getHotels());
        holder.food.setText(detailOffer.getFood());
        holder.price.setText(detailOffer.getPrice());

        Picasso.with(context).load(detailOffer.getImage()).into(holder.imagecompany);

    }

    @Override
    public int getItemCount() {
        return detailOfferList.size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imagecompany;
        TextView displayname, bus, food, hotel, price;

        public DetailViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imagecompany = (ImageView)view.findViewById(R.id.imageCompany);
            displayname = (TextView)view.findViewById(R.id.dispaly_nameCompany);
            bus = (TextView)view.findViewById(R.id.bus);
            hotel = (TextView)view.findViewById(R.id.hotels);
            food = (TextView)view.findViewById(R.id.food);
            price = (TextView)view.findViewById(R.id.price);

        }
    }
}
