package com.example.sushantpaygude.scoutit.Dashboard.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sushantpaygude.scoutit.Dashboard.Models.Product;
import com.example.sushantpaygude.scoutit.R;

import java.util.List;

/**
 * Created by sushantpaygude on 4/24/19.
 */

public class ItemsRecyclerViewAdapter extends RecyclerView.Adapter<ItemsRecyclerViewAdapter.ItemsViewHolder> {

    private List<Product> productList;
    public ItemsRecyclerViewAdapter(List<Product> productList){
    this.productList = productList;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_item,parent,false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        holder.textItemName.setText(productList.get(position).getProductName());
        holder.textProductDescription.setText(productList.get(position).getProductDescription());
        holder.textPrice.setText("$ " + String.valueOf(productList.get(position).getProductCost()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageItemImage;
        public TextView textItemName;
        public TextView textProductDescription;
        public TextView textPrice;
        public TextView buttonContact;
        public ItemsViewHolder(View itemView) {
            super(itemView);
            imageItemImage = itemView.findViewById(R.id.imageItemImage);
            textItemName = itemView.findViewById(R.id.textItemName);
            textProductDescription = itemView.findViewById(R.id.textProductDescription);
            textPrice = itemView.findViewById(R.id.textPrice);
            buttonContact = itemView.findViewById(R.id.buttonContact);
        }
    }
}
