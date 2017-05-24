package com.example.absolutelysaurabh.implicit_intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void OpenHomePage(View v){

        String url = "http://www.udacity.com";

        openpage(url);

    }

    private void openpage(String url) {

        Uri webpage = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW, webpage);

        if(i.resolveActivity(getPackageManager())!=null){

            //Check whether the intent we are going to is empty or not
            //otherwise our app will crash
            startActivity(i);
        }

    }

    public void open_map(View v){

        String address_string = "1600 Amphitheatre Parkway, CA";

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("geo").path("0,0").query(address_string);
        Uri addressUri = builder.build();

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(addressUri);

        //Now we nedd to check if device is able to handle the request or not
        //cz it might happen that the activity does not have any map app
        if(i.resolveActivity(getPackageManager())!=null){

            startActivity(i);
        }

    }

    public void Use_Send(View v){

       //We'll use ShareCompat to send data
        //advantage is it'll makes all the main decisions of what to share itself
        //MIME : Multipurpose Internet Mail Extension
//        String mimeType = "text/plain";
//        String title = "Learning How to share";
//        String textToShare = "Hello there";


        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "http://www.techbugged.blogspot.in";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "NewsFeed app");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share App Via"));

    }

}
