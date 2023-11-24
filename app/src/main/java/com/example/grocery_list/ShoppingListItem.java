package com.example.grocery_list;

public class ShoppingListItem {
    private String itemName;
    private boolean checked;

    public ShoppingListItem(String itemName, boolean checked) {
        this.itemName = itemName;
        this.checked = checked;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
