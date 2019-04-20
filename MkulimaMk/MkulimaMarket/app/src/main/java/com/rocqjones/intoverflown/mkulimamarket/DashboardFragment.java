package com.rocqjones.intoverflown.mkulimamarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements View.OnClickListener {

    //call for imageView global var
    ImageView maize, beans, cowpeas, tomatoes, potatoes, onions, fruits, coconuts, othernuts, carrots, greenpeas, fish, coffee, tea,
            rice, pyrethrum, sugarcane, sunflower, poutry, milkchurns, vegetables, cabbage;

    //call for product name
    EditText productEdNameText;

    //This constructor will initialize object of a class
    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_dashboard, container, false);

        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //For Product name
        productEdNameText = v.findViewById(R.id.productEdNameText);

        //set click listener for every product in Dashboard Fragment
        maize = (ImageView) v.findViewById(R.id.maize);
        maize.setOnClickListener((View.OnClickListener) this);

        beans = (ImageView) v.findViewById(R.id.beans);
        beans.setOnClickListener((View.OnClickListener) this);

        cowpeas = (ImageView) v.findViewById(R.id.cowpeas);
        cowpeas.setOnClickListener((View.OnClickListener) this);

        tomatoes = (ImageView) v.findViewById(R.id.tomatoes);
        tomatoes.setOnClickListener((View.OnClickListener) this);

        potatoes = (ImageView) v.findViewById(R.id.potatoes);
        potatoes.setOnClickListener((View.OnClickListener) this);

        onions = (ImageView) v.findViewById(R.id.onions);
        onions.setOnClickListener((View.OnClickListener) this);

        coconuts = (ImageView) v.findViewById(R.id.coconuts);
        coconuts.setOnClickListener((View.OnClickListener) this);

        othernuts = (ImageView) v.findViewById(R.id.othernuts);
        othernuts.setOnClickListener((View.OnClickListener) this);

        carrots = (ImageView) v.findViewById(R.id.carrots);
        carrots.setOnClickListener((View.OnClickListener) this);

        greenpeas = (ImageView) v.findViewById(R.id.greenpeas);
        greenpeas.setOnClickListener((View.OnClickListener) this);

        fish = (ImageView) v.findViewById(R.id.fish);
        fish.setOnClickListener((View.OnClickListener) this);

        fruits = (ImageView) v.findViewById(R.id.fruits);
        fruits.setOnClickListener((View.OnClickListener) this);

        coffee = (ImageView) v.findViewById(R.id.coffee);
        coffee.setOnClickListener((View.OnClickListener) this);

        tea = (ImageView) v.findViewById(R.id.tea);
        tea.setOnClickListener((View.OnClickListener) this);

        rice = (ImageView) v.findViewById(R.id.rice);
        rice.setOnClickListener((View.OnClickListener) this);

        pyrethrum = (ImageView) v.findViewById(R.id.pyrethrum);
        pyrethrum.setOnClickListener((View.OnClickListener) this);

        sugarcane = (ImageView) v.findViewById(R.id.sugarcane);
        sugarcane.setOnClickListener((View.OnClickListener) this);

        sunflower = (ImageView) v.findViewById(R.id.sunflower);
        sunflower.setOnClickListener((View.OnClickListener) this);

        poutry = (ImageView) v.findViewById(R.id.poutry);
        poutry.setOnClickListener((View.OnClickListener) this);

        milkchurns = (ImageView) v.findViewById(R.id.milk);
        milkchurns.setOnClickListener((View.OnClickListener) this);

        vegetables = (ImageView) v.findViewById(R.id.vegetables);
        vegetables.setOnClickListener((View.OnClickListener) this);

        cabbage = (ImageView) v.findViewById(R.id.cabbages);
        cabbage.setOnClickListener((View.OnClickListener) this);
        return v;
    }

    @Override
    public void onClick(View v) {
        //This switch case is intented for switching within products on click
        switch (v.getId()) {
            case R.id.maize:
                //new sellingSubmitData
                Intent a = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(a);
                break;

            case R.id.beans:
                Intent b = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(b);
                break;

            case R.id.cowpeas:
                Intent c = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(c);
                break;

            case R.id.tomatoes:
                Intent d = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(d);
                break;

            case R.id.potatoes:
                Intent e = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(e);
                break;

            case R.id.onions:
                Intent f = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(f);
                break;

            case R.id.fruits:
                Intent g = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(g);
                break;

            case R.id.coconuts:
                Intent h = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(h);
                break;

            case R.id.othernuts:
                Intent i = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(i);
                break;

            case R.id.carrots:
                Intent j = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(j);
                break;

            case R.id.greenpeas:
                Intent k = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(k);
                break;

            case R.id.fish:
                Intent l = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(l);
                break;

            case R.id.coffee:
                Intent m = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(m);
                break;

            case R.id.tea:
                Intent n = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(n);
                break;

            case R.id.rice:
                Intent o = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(o);
                break;

            case R.id.pyrethrum:
                Intent p = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(p);
                break;

            case R.id.sugarcane:
                Intent q = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(q);
                break;

            case R.id.sunflower:
                Intent r = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(r);
                break;

            case R.id.poutry:
                Intent s = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(s);
                break;

            case R.id.milk:
                Intent t = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(t);
                break;

            case R.id.vegetables:
                Intent u = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(u);
                break;

            case R.id.cabbages:
                Intent w = new Intent(getActivity(), SellingSubmitDataActivity.class);
                startActivity(w);
                break;
        }
    }
}
