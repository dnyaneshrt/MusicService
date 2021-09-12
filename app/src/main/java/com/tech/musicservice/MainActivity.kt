package com.tech.musicservice

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent = Intent(this, MyMusicService::class.java)

        btn_play.setOnClickListener {
            //start playing song
            startService(intent)
        }
        btn_stop.setOnClickListener {
            //got media file
            stopService(intent)

        }
    }
}

//1)create a class as a child class of android.app.Service
//2)android.app.Service is an abstract class having abstract method called onBind() so implemnt it.
//3) onCreate(),onStartCommand(),onDestroy()
//first time- onCreate(), onStratCommand
// second time- only onStartCommand()
//onDestroy will be called ,when serive stopped.
/*
service doen not any UI,
how our activity will communicate with service?
Intent.
 */
//same like activity,we have to register service inside manifest.xml ( <service android:name=".MyMusicService"/>)
class MyMusicService : Service() {

    var player: MediaPlayer? = null
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // creating a media player which
        // will play the audio of Default
        // ringtone in android device
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);

        // providing the boolean
        // value as true to play
        // the audio on loop
        player?.setLooping(true);

        // starting the process
        player?.start();

        // returns the status
        // of the program
        return START_STICKY;

    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun onDestroy() {
        super.onDestroy()
        player?.stop()
    }
}
