//package com.example.css;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//
//
//public class MainActivity extends AppCompatActivity implements ParserResponseInterface{
//
//    private TextView headlineTextView;
//    private TextView articleTextView;
//    private TextView errorMessageTextView;
//    private ProgressBar progressBar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        headlineTextView = (TextView) findViewById(R.id.headline);
//        articleTextView = (TextView) findViewById(R.id.article);
//        errorMessageTextView = (TextView) findViewById(R.id.errorMessage);
//        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//
//
//        //Execute AsyncTask for Parsing HTML
//        new HtmlParser(this).execute("https://developer.android.com/training/index.html");
//
//    }
//
//
//    //@SuppressLint("SetTextI18n")
//    @Override
//    public void onParsingDone(ArticleModel articleModel) {
//
//        progressBar.setVisibility(View.GONE);
//
//        if(articleModel!=null){
//            headlineTextView.setText(articleModel.getHeadline());
//            articleTextView.setText(articleModel.getArticle());
//        }
//        else
//            errorMessageTextView.setText("Something wrong! Can't parse HTML");
//    }
//
//}

package com.example.css;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_SPORT_REQUEST = 1;
    private AppViewModel appViewModel;
    private Context context;
    TextView texx;

    private EditText editTextName;
    private EditText editTextDate;
    private EditText editTextLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        final SportAdapter adapter = new SportAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Get a new or existing ViewModel from the ViewModelProvider.
        appViewModel = new ViewModelProvider(this).get(AppViewModel.class);

        // Add an observer on the LiveData returned.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        appViewModel.getAllSports().observe(this, new Observer<List<Sport>>() {
            @Override
            public void onChanged(@Nullable final List<Sport> sports) {
                // Update the cached copy of the words in the adapter.
                adapter.setSport(sports);
            }
        });

//        FloatingActionButton ButtonAddSport = findViewById(R.id.fab);
//        ButtonAddSport.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AddSport.class);
//                startActivityForResult(intent, ADD_SPORT_REQUEST);
//            }
//        });

        String url;
        String Name;
        String Time;
        String location;

        FloatingActionButton buttonAdd = findViewById(R.id.fab);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                //appViewModel.populateDB(url);
            }
        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
//        if(requestCode == ADD_SPORT_REQUEST && resultCode == RESULT_OK) {
//            //I am working on the main activity on the video Room 7, 22:02
//            //Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
//
//            Sport sports = new Sport();
//
//            String name = data.getStringExtra(AddSport.Extra_Date);
//            String time = data.getStringExtra(AddSport.Extra_Month);
//            String location = data.getStringExtra(AddSport.Extra_Year);
//
//            sports.setSport(name, time, location);
//            appViewModel.insert(sports);
//            Toast.makeText(this, "Sport Saved", Toast.LENGTH_SHORT).show();
//
//        } else {
//            Toast.makeText(
//                    getApplicationContext(),
//                    "sport not saved",
//                    Toast.LENGTH_LONG).show();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item3:
                //SaveSport();
                Toast.makeText(this, "Sport Saved", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    private class PopulateAsncTask extends AsyncTask<Void, Void, Void> {
//        String eventName;
//        String title;
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            try{
//            Document htmlDocument = Jsoup.connect("https://www.luther.edu/events/?category=455731&no_search=1").get();
//
//            title = htmlDocument.title();
//
//            Elements eventName = htmlDocument.getElementsByClass("title");
//            Elements time = htmlDocument.getElementsByClass("time");
//            Elements locatiom = htmlDocument.getElementsByClass("location");
//
//            } catch(Exception e){e.printStackTrace();}
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            texx.setText(title);
//        }
//    }
}


