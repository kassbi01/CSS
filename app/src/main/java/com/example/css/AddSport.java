package com.example.css;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class AddSport extends AppCompatActivity {

    //AppViewModel appViewModel;

    public static final String Extra_Date =
            "com.example.css.Extra_Type";
    public static final String Extra_Month =
            "com.example.css.Extra_Game";
    public static final String Extra_Year =
            "com.example.css.Extra_Location";



    private EditText editTextName;
    private EditText editTextTime;
    private EditText editTextLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sport_activity);

        editTextName = findViewById(R.id.name);
        editTextTime = findViewById(R.id.time);
        editTextLocation = findViewById(R.id.location);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();

                String Name = editTextName.getText().toString();
                String Time = editTextTime.getText().toString();
                String Location = editTextLocation.getText().toString();

                if (Name.trim().isEmpty() || Time.trim().isEmpty() || Location.trim().isEmpty()) {
                    //Toast.makeText(this, "please enter the field properly", Toast.LENGTH_LONG).show();
                    Toast.makeText(
                            getApplicationContext(),
                            "Please enter the field properly",
                            Toast.LENGTH_LONG).show();
                    setResult(RESULT_CANCELED, data);
                    return;
                } else {
                    data.putExtra(Extra_Date, Name);
                    data.putExtra(Extra_Month, Time);
                    data.putExtra(Extra_Year, Location);

                    setResult(RESULT_OK, data);

                }
                finish();

            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_sport, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item3:
                //SaveSport();
                Toast.makeText(this, "Sport Not Saved", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
