package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ShoppingList extends AppCompatActivity {

    private ListView shoppingList;
    private EditText nameItem;
    private Button addItem,clearItem;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> list;
    private String item;
    private int index=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        Methods.onAttachedWindows(ShoppingList.this);
        final TinyDB Save=new TinyDB(ShoppingList.this);

        shoppingList = findViewById(R.id.shopping_listView);
        nameItem = findViewById(R.id.item_editText);
        addItem = findViewById(R.id.addButton);
        clearItem = findViewById(R.id.clearButton);

        list = Save.getListString("shoppingList");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        shoppingList.setAdapter(adapter);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameItem.length() == 0 )
                    nameItem.setHint("empty");
                else
                 {
                    item = nameItem.getText().toString();
                    index = Save.getInt("index");
                    list.add(index + " - " + item);
                    index++;
                    Save.putInt("index", index);
                    Save.putListString("shoppingList", list);
                    adapter.notifyDataSetChanged();
                    nameItem.setText("");
                    nameItem.setHint("Name item");
                 }
            }
        });
        clearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save.remove("shoppingList");
                Save.putInt("index",1);
                adapter.clear();

            }
        });
    }
}