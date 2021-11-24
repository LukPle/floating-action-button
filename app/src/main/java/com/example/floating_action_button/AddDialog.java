package com.example.floating_action_button;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

/**
 * This class manages the Dialog that opens up in order to add a new item to the list.
 *
 * Layout File: layout_dialog.xml
 */
public class AddDialog extends AppCompatDialogFragment {

    private EditText editItem;
    private EditText editQuantity;
    private AddDialogListener listener;

    /**
     * The onCreateDialog method directs everything that needs to be done when the user opens the Dialog.
     * This includes the Layout for the Dialog.
     * There is also the negative and positive feedback from the user that gets handled here.
     * @param savedInstanceState is a standard parameter.
     * @return builder.create() which creates the dialog for the user.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // Creating a Builder and assigning the Layout for the Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view);
        builder.setTitle("Add Item");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { }
        });

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String item = editItem.getText().toString();
                String quantity = editQuantity.getText().toString();

                // The listener calls the applyTexts method which adds the new item to the Shopping List
                listener.applyTexts(item, quantity);
            }
        });

        editItem = view.findViewById(R.id.edit_item);
        editQuantity = view.findViewById(R.id.edit_quantity);

        return builder.create();
    }

    /**
     * This method initializes the listener.
     * If the listener cannot be defined there is an Exception.
     * That exception demands the implementation of the AddDialog interface.
     * @param context is the activity.
     */
    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);

        try {

            listener = (AddDialogListener) context;
        }
        catch (ClassCastException e) {

            throw new ClassCastException(context.toString() +"must implement AddDialogListener");
        }
    }

    /**
     * The AddDialogListener is an interface for implementing an applyTexts method to different classes.
     * It gets used in the MainActivity and the AddDialog.
     * The applyTexts method is necessary in order to get the user input from the dialog to the list itself.
     */
    public interface AddDialogListener {

        void applyTexts(String item, String quantity);
    }
}
