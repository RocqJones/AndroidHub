package com.rocqjones.intoverflown.mkulimamarket;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<Products> products;
    private Context context;

    public ProductAdapter(ArrayList<Products> products, Context context){
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.container;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new ProductViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i) {
        Products product = products.get(i);
        //get holder from getters in 'Products'
        productViewHolder.locationTextView.setText(product.getLocationHolder());
        productViewHolder.dateTextView.setText(product.getDateHolder().toString());
        productViewHolder.priceTextView.setText(product.getPriceHolder());
        productViewHolder.productTextView.setText(product.getProductHolder());
        productViewHolder.productQuatintyTextView.setText(product.getProductQuatintyHolder());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView locationTextView;
        public TextView dateTextView;
        public TextView priceTextView;
        public TextView productTextView;
        public TextView productQuatintyTextView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            //id from container
            locationTextView = itemView.findViewById(R.id.locationCitm);
            priceTextView = itemView.findViewById(R.id.price);
            dateTextView = itemView.findViewById(R.id.date);
            productTextView = itemView.findViewById(R.id.productName);
            productQuatintyTextView = itemView.findViewById(R.id.productQuantity);
        }
    }
}
