package com.example.floating_action_button;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * The MainActivity creates a Shopping List using a ArrayList with a RecyclerView.
 * The RecyclerView in combination with an Adapter is the recommended way for creating lists in Android.
 * It is possible to add an item to the shopping list by using a FloatingActionButton.
 *
 * The documentation focuses on the FloatingActionButton and the dialog for adding an item.
 * For Javadoc information about the RecyclerView and Adapter check out the specific project.
 *
 * Layout File: activity_main.xml
 *
 * @author Lukas Plenk
 */
public class MainActivity extends AppCompatActivity implements AddDialog.AddDialogListener {

    private RecyclerView recyclerView;
    private ArrayList<Item> itemList;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<>();
        fillItemList();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final Adapter adapter = new Adapter(itemList);
        recyclerView.setAdapter(adapter);

        floatingActionButton = findViewById(R.id.button_add_item);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                openDialog();
            }
        });
    }

    private void fillItemList() {

        itemList.add(new Item("Noodles", "1"));
        itemList.add(new Item("Cheese", "1"));
        itemList.add(new Item("Pepper", "2"));
        itemList.add(new Item("Onions", "4"));
        itemList.add(new Item("Carrots", "2"));
        itemList.add(new Item("Tomato", "1"));
        itemList.add(new Item("Fish", "4"));
        itemList.add(new Item("Grill Sauce", "1"));
    }

    /**
     * This method creates an instance of the AddDialog class.
     * That instance will be displayed on the screen via a FragmentManager.
     */
    public void openDialog() {

        AddDialog addDialog = new AddDialog();
        addDialog.show(getSupportFragmentManager(), "Add Item");
    }

    /**
     * This method takes the data from the dialog and creates a new element in the ArrayList.
     * @param item is the name of the item which the user typed in the dialog.
     * @param quantity is the quantity of the item which the user typed in the dialog.
     */
    @Override
    public void applyTexts(String item, String quantity) {

        itemList.add(new Item(item, quantity));
    }
}