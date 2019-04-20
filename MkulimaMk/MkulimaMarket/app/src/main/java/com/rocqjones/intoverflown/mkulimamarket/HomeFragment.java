package com.rocqjones.intoverflown.mkulimamarket;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  {
    //private ProductAdapter adapter;
    private RecyclerView view;
    private DatabaseReference productRef, userNameRt;

    private FirebaseAuth mAuth;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseApp.initializeApp(getContext());
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        productRef = FirebaseDatabase.getInstance().getReference().child("products");
        userNameRt = FirebaseDatabase.getInstance().getReference().child("Users");

        view = rootView.findViewById(R.id.recycleV);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        view.setLayoutManager(layoutManager);

        DisplayProducts();
        return rootView;

    }

    //Display product details from 'products' database
    private void DisplayProducts() {
        FirebaseRecyclerOptions<RetriveData> options =
                new FirebaseRecyclerOptions.Builder<RetriveData>()
                        .setQuery(productRef, RetriveData.class)
                .build();

        FirebaseRecyclerAdapter<RetriveData,ProductViewHolder > adapter =
                new FirebaseRecyclerAdapter<RetriveData, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull RetriveData model) {

                        //set value to holder after getting them from the firebase database
                        holder.dateH.setText("Date: "+ model.getDate().toString());
                        holder.sellerLocation.setText("Location: "+ model.getLocation());
                        holder.productName.setText(model.getProductName());
                        holder.productQty.setText(model.getProductQty() + " Kgs/Lt");
                        holder.productPrice.setText("Ksh "+ model.getProductPrice());
                        holder.customerName.setText(model.getSellername());
                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View view  = LayoutInflater.from(getContext()).inflate(R.layout.container,viewGroup,false);
                        ProductViewHolder ViewHolder = new ProductViewHolder(view);
                        return ViewHolder;
                    }
                };
        //This will create multiple containers on the home screen to products added
        view.setAdapter(adapter);
        adapter.startListening();

    }

    //Declare holders that will be ser on the recycler view
    public static  class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView productName,productQty,productPrice, sellerLocation, dateH, customerName;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            // Get id from 'container'
            dateH = (TextView) itemView.findViewById(R.id.date);
            sellerLocation = (TextView) itemView.findViewById(R.id.locationCitm);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productQty = (TextView) itemView.findViewById(R.id.productQuantity);
            productPrice = (TextView) itemView.findViewById(R.id.price);
            //get customerName Id
            customerName = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
