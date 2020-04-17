package com.example.shoppinglist.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.ListActivity;
import com.example.shoppinglist.R;
import com.example.shoppinglist.model.ShoppingListModel;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    public static List<ShoppingListModel> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // each data item is just a string in this case
        public TextView tvTitle;
        public TextView tvDate;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
           // textView = v;

            itemView.setOnClickListener(this);

            tvTitle = itemView.findViewById(R.id.tv_type);
            tvDate = itemView.findViewById(R.id.tv_date);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Clicked item Position "+ getLayoutPosition()+", Type :"+mDataset.get(getLayoutPosition()).getType(), Toast.LENGTH_LONG).show();

            mDataset.get(getLayoutPosition()).getType();

            Intent i = new Intent(v.getContext(), ListActivity.class);
            v.getContext().startActivity(i);
        }
    }
        // Provide a suitable constructor (depends on the kind of dataset)
        public MainAdapter(List<ShoppingListModel> myDataset) {

            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MainAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            // Inflate the RecyclerView item layout xml.
            View itemView = layoutInflater.inflate(R.layout.main_list_row, parent, false);


            // create a new view
          //  TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_row, parent, false);



            MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
           // holder.textView.setText(mDataset[position]);

            holder.tvTitle.setText(mDataset.get(position).getType());
            holder.tvDate.setText(mDataset.get(position).getDate());


        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
}

