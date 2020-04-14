//package com.example.css;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ListView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//
//public class WebScraping extends AppCompatActivity {
//
//    ListView CurationList;
//    Button go;
//
//    CustomCurationAdapter adapter;
//
//    public String[] listItem; // String Array to contain list of data to display
//    String s3; // This string needs to be accessible in all functions
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.webscraping_activity);
//
//        CurationList = (ListView) findViewById(R.id.postLISTVIEW);
//        go = (Button) findViewById(R.id.testButton);
//
//        go.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new testGetUserData().execute(); // execute our underlying class testGetUserData
//            }
//        });
//    }
//
//    public static class testGetUserData extends AsyncTask<Void, Void, Void> {
//
//        ProgressDialog p = new ProgressDialog(WebScraping.this); // A simple Dialog that displays progress of function.
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            try {
//                org.jsoup.nodes.Document doc = Jsoup.connect("https://www.luther.edu/events/?category=455731&no_search=1").get(); //Doc contains HTML data from Jsoup
//                //String title = doc.title();
//                Elements element = doc.select("div[class=contribution]"); // This tag will get us all post data
//
//                listItem = new String[element.size()]; // initialize our list, to the size of the list of elements
//
//                for (int i = 0; i < element.size(); i++) {
//                    String s = element.get(i).text() + "\n"; // String 's' contains the text of our elements.
//                    getUsernamesFromElement(s, i); //
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//
//    public void getUsernamesFromElement(String s, int i) { //This function is used to cut up the elements,
//        String ss = s.substring(0, 20); // into substrings that are easier to work with. 'ss' contains the first 20 characters.
//        String[] ss1 = ss.split(" ", 3); // ss1[1] now contains username, as we split the text at " ".
//
//        String testTitle[] = s.split(" ", 3);  // String array testTitle = entire element split in " ".
//        String title = testTitle[2]; // Post Title now contained in string title.
//        String subTitle = title.substring(0, 30); // Shorten the title, so as to not flood display. url should be
//        //length of 31 characters, = 'https://steemit.com/utopian-io'
//        String urls[] = s.split(" ", 4); // Gather Url from element.
//        String finalURL = urls[2]; // URL contained within finalURL
//
//        try { //Make a new connection, with the url from the current element.
//            Document doc1 = Jsoup.connect(finalURL).userAgent("Mozilla").get(); //store new page data
//            Elements postValuess = doc1.select("span[class=voting__inner]"); //this tag will find you the post value
//
//            String s1 = postValuess.get(0).text() + "\n"; // s1 now contains post value data
//            String[] s2 = s1.split(" ", 2); // String Array ontaining split element.
//            s3 = s2[0]; //s[2] = dollar amount of post value with dolar sign
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } //catch blah
//
//        listItem[i] = ss1[1] + "\n" + subTitle + "   " + s3 + "\n"; // Assemble each item in the order to display in list.
//
//    }
//
//        public boolean saveData(String[] array, String arrayName, Context ctex) { //Method to save our array to SharedPreferences
//            SharedPreferences prefs = ctex.getSharedPreferences("ArrayData", 0); //open file
//            SharedPreferences.Editor editor = prefs.edit(); //new editor
//            editor.putInt(arrayName + "_size", array.length); //maintain size of array for integrity
//            for (int i = 0; i < array.length; i++) // for i in array,
//                editor.putString(arrayName + "_" + i, array[i]); //input string
//            return editor.commit();//final save
//        }
//
//        public String[] loadArray(String arrayName, Context mContext) { // Method to load array from SharedPreferences
//            SharedPreferences prefs = mContext.getSharedPreferences("ArrayData", 0); //open
//            int size = prefs.getInt(arrayName + "_size", 0); //get size
//            String array[] = new String[size]; //create new array of same size
//            for (int i = 0; i < size; i++) //for element in array/size
//                array[i] = prefs.getString(arrayName + "_" + i, null); //add string
//            return array;
//        }
//
//    @Override
//    protected void onPostExecute(Void aVoid) { // the final steps to our Async Task
//        super.onPostExecute(aVoid);
//        saveData(listItem, "Urls", WebScraping.this); //save array to preferences
//        adapter = new CustomCurationAdapter(MainActivity.this, listItem); //implement new adapter
//        CurationList.setAdapter(adapter); //atach list to adapter
//        p.dismiss(); //end of progress, so end of dialog
//    }
//
//    @Override
//    protected void onPreExecute() { //this is the pre execution cycle of AsyncTask,
//        super.onPreExecute();
//
//        p.setTitle("Curation Hounds Released"); //and we will use it to set up our progressDialog // title to display
//        p.setMessage("Please wait while we sniff out some awesome posts for you!"); //message to display
//        p.setIndeterminate(false); // used for setting progress amounts. not necessary here.
//        p.show(); //show dialog
//    }
//
//}
//}
//
//
//
