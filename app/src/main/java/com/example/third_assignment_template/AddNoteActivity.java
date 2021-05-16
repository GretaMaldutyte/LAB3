package com.example.third_assignment_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }

    public void onAddNoteClick(View view) {
        EditText txtNoteName = findViewById(R.id.txtNoteName);
        EditText txtNote = findViewById(R.id.txtNote);

        if (txtNote.getText().toString().isEmpty() || txtNoteName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        //https://stackoverflow.com/questions/14034803/misbehavior-when-trying-to-store-a-string-set-using-sharedpreferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();
        Set<String> currentNotes = sp.getStringSet("notes", new HashSet<String>());

        currentNotes.add(txtNoteName.getText().toString() + "\n\n" + txtNote.getText().toString());

        spEd.putStringSet("notes", currentNotes);
        spEd.apply();

        finish();
    }
}
