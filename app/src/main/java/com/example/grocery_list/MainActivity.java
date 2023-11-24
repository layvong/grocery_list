package com.example.grocery_list;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mShoppingList;
    private EditText mItemEdit;
    private Button mAddButton;
    private ShoppingListAdapter mAdapter; // Use your custom adapter

    private Button mClearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Shopping List");

        mShoppingList = findViewById(R.id.shopping_listView);
        mItemEdit = findViewById(R.id.item_editText);
        mAddButton = findViewById(R.id.add_button);

        // Create a list of ShoppingListItem objects
        List<ShoppingListItem> shoppingItems = new ArrayList<>();

        mAdapter = new ShoppingListAdapter(this, R.layout.list_item, shoppingItems);
        mClearButton = findViewById(R.id.clear_button);
        mShoppingList.setAdapter(mAdapter);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = mItemEdit.getText().toString();
                final ShoppingListItem newItem = new ShoppingListItem(item, false);


                // Notify the adapter of data change by adding the item to the adapter, not to shoppingItems
                mAdapter.add(newItem);

                // Notify the adapter of the data change
                mAdapter.notifyDataSetChanged();
                mItemEdit.setText("");
            }
        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the shopping list and notify the adapter
                mAdapter.clear(); // Clears the adapter
                mAdapter.notifyDataSetChanged();
                // Clear the shoppingItems list
                shoppingItems.clear();
            }
        });


    }
}
