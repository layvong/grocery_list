package com.example.grocery_list;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class ShoppingListAdapter extends ArrayAdapter<ShoppingListItem> {
    public ShoppingListAdapter(Context context, int resource, List<ShoppingListItem> items) {
        super(context, resource, items);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        CheckBox checkBox = listItemView.findViewById(R.id.checkBox);
        TextView itemNameTextView = listItemView.findViewById(R.id.itemName);

        final ShoppingListItem currentItem = getItem(position);
        if (currentItem != null) {
            checkBox.setChecked(currentItem.isChecked());

            // Apply a strike-through text decoration when the item is checked
            if (currentItem.isChecked()) {
                SpannableString spannableString = new SpannableString(currentItem.getItemName());
                spannableString.setSpan(new StrikethroughSpan(), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                itemNameTextView.setText(spannableString);
            } else {
                // No decoration for unchecked items
                itemNameTextView.setText(currentItem.getItemName());
            }

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    currentItem.setChecked(isChecked);
                    // Notify the adapter of data change
                    notifyDataSetChanged();
                }
            });
        }

        return listItemView;
    }


}
