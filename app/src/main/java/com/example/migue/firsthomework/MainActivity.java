package com.example.migue.firsthomework;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public int color=59;
   public  Button background;
    public  Button  change ;
    public Button font ;
    Intent intent;
    public int [] colors ={Color.RED,color= Color.BLUE,Color.GREEN,Color.CYAN,Color.MAGENTA,Color.BLACK};
    public static final String SOUND_ID = "sound id";
    private int current_sound = 0;
    int selected_sound = 0;
    private MediaPlayer backgroundPlayer;
    private MediaPlayer buttonPlayer;
    static public Uri[] sounds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sounds = new Uri[4];
//Use parse method of the Uri class to obtain the Uri of a resource
//specified by a string
        sounds[0] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.ringd);
        sounds[1] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.ring01);
        sounds[2] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.ring02);
        sounds[3] = Uri.parse("android.resource://" + getPackageName() + "/" +
                R.raw.ring03);

        buttonPlayer = new MediaPlayer();
        buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
  //                      .setAction("Action", null).show();
                //Reset the player
                buttonPlayer.reset();
                try {
//Set Data source according to the current_sound value
                    buttonPlayer.setDataSource(getApplicationContext(),sounds[current_sound]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//Prepare the player asynchronously
                buttonPlayer.prepareAsync();
//No need to call start() since we call with onPreparedListener
            }
        });
        buttonPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
//Pause the backgroundPlayer
                backgroundPlayer.pause();
//Start the buttonPlayer
                mp.start();
            }
        });
        buttonPlayer.setOnCompletionListener(new
                                                     MediaPlayer.OnCompletionListener() {
                                                         @Override
                                                         public void onCompletion(MediaPlayer mp) {
                                                             backgroundPlayer.start();
                                                         }
                                                     });





        intent = new Intent(getApplicationContext(),SecondActivity.class);
        background = (Button) findViewById(R.id.background);
        change = (Button) findViewById(R.id.change);
        font = (Button) findViewById(R.id.font);


        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("value",background.getId());
                startActivityForResult(intent,1);
                Toast.makeText(MainActivity.this, "background", Toast.LENGTH_SHORT).show();

            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("value",change.getId());
                startActivityForResult(intent,2);
                Toast.makeText(MainActivity.this, "change", Toast.LENGTH_SHORT).show();
            }
        });
        font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "font", Toast.LENGTH_SHORT).show();
                 // intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("value",font.getId());
            startActivityForResult(intent,3);
            }
        });

    }

    @Override
public void onActivityResult(int RequestCode,int ResultCode,Intent data) {
        //if (RequestCode == RESULT_OK) {
            // Make sure the request was successful
            if (ResultCode == RESULT_OK && RequestCode == 1) {
                color = (int) data.getIntExtra("color", color);
                getWindow().getDecorView().setBackgroundColor(colors[color]);
                //  root.setBackgroundColor(colors[color]);
            }
        if (ResultCode == RESULT_OK && RequestCode == 2) {


            color = (int) data.getIntExtra("color", color);
            background.setBackgroundColor(colors[color]);
            change.setBackgroundColor(colors[color]);
            font.setBackgroundColor(colors[color]);
        }
        if (ResultCode == RESULT_OK && RequestCode == 3) {


            color = (int) data.getIntExtra("color", color);
            background.setTextColor(colors[color]);
            change.setTextColor(colors[color]);
            font.setTextColor(colors[color]);
        }

        //  }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
//Send the background player to the paused state
        backgroundPlayer.pause();
        buttonPlayer.pause();
    }
    @Override
    protected void onResume() {
        super.onResume();
//Create and prepare MediaPlayer with R.raw.mario as the data stream
//source
        backgroundPlayer = MediaPlayer.create(this, R.raw.mario);
//Define a procedure that will be executed when the MediaPlayer goes to
//the prepared state
        backgroundPlayer.setOnPreparedListener(new
                                                       MediaPlayer.OnPreparedListener() {
                                                           @Override
                                                           public void onPrepared(MediaPlayer mp) {
//Set the looping parameter to true
                                                               mp.setLooping(true);
//Start the playback.
//By placing the start method in the onPrepared event
//we are always certain that the audio stream is prepared.
                                                               mp.start();
                                                           }
                                                       });
    }
    @Override
    protected void onStop(){
        super.onStop();
//Release the background player when we don’t need it.
        backgroundPlayer.release();
    }
}
