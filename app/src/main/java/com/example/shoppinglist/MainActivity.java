package com.example.shoppinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shoppinglist.adapter.MainAdapter;
import com.example.shoppinglist.db.DBManager;
import com.example.shoppinglist.model.ShoppingListModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton fab;

    private String[]  shoppint_list_type =  {"-- Select Shopping List Type --", "Vegetables & Fruits", "Groceries", "Clothing", "Party", "Others"};

    private String[] myDataset = new String[] {"A","B","C","B","C","B","C","B","C","B","C"};

    private List<ShoppingListModel> shoppingList;

    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.myFAB);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.AppBar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                   // showOption(R.id.action_info);
                } else if (isShow) {
                    isShow = false;
                   // hideOption(R.id.action_info);
                }
            }
        });

        // create database object and open read/write
        dbManager = new DBManager(this);
        dbManager.Open();

        // set click listener on FAB button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createShoppingListDialog();
            }
        });

        // method call to get shopping list
        getShoppingList();

        /**
         * RecyclerView swipe method
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition();

                Toast.makeText(MainActivity.this, "Item Swipe position "+position, Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);

    }

    /**
     * Initialize views on load UI
     */
    private void initViews(){

    }

    /**
     * method to create add shopping list type
     */
    private void createShoppingListDialog(){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_shopping_list_type);

        final Spinner spinner = dialog.findViewById(R.id.spinner);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        Button btnCreate = dialog.findViewById(R.id.btn_create);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,shoppint_list_type);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        // cancel button click event
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // create button click event
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get selected type of the list
                String selected_item = spinner.getSelectedItem().toString().trim();

                Log.d("Spinner Selected item: ", ""+selected_item);

                if (selected_item.equalsIgnoreCase("-- Select Shopping List Type --")){

                    Toast.makeText(MainActivity.this, "Kindly select the shopping list type", Toast.LENGTH_LONG).show();

                }else {

                    // get current date
                    DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
                    String created_date = df.format(Calendar.getInstance().getTime());

                    Toast.makeText(MainActivity.this, selected_item, Toast.LENGTH_LONG).show();

                    dbManager.inserShoppingtList(selected_item, created_date);

                    dialog.dismiss();

                    // update the UI
                    //mAdapter.notifyDataSetChanged();

                    recyclerView.invalidate();

                    getShoppingList();

                }

            }
        });

        dialog.show();
    }


    @Override
    protected void onStop() {
        super.onStop();

        if (dbManager != null){
            dbManager.close();
        }
    }

    /**
     * fetch shopping list from database
     */
    private void getShoppingList(){

        shoppingList = new ArrayList<>();

        Cursor cursor = dbManager.getShoppingList();

        System.out.println("Shopping list count "+cursor.getCount());

        // if cursor has some data
        if(cursor.moveToFirst()){
            do{
                shoppingList.add(new ShoppingListModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));

            }while (cursor.moveToNext());

            cursor.close();

        }

        // specify an adapter (see also next example)
        mAdapter = new MainAdapter(shoppingList);
        recyclerView.setAdapter(mAdapter);
    }
}
