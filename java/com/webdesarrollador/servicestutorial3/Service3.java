package com.webdesarrollador.servicestutorial3;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class Service3 extends Service {

    private boolean isPlaying=false;

    MediaPlayer mediaPlayer;

    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        play();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return(null);
    }
    //Empezaremos la reproducción del audio, y crearemos la notificación
    private void play() {

        if (!isPlaying) {

            Log.d("play", "sonando");
            isPlaying=true;

            mediaPlayer = MediaPlayer.create(this, R.raw.paloma);
            mediaPlayer.start();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setContentTitle("App Música")
                    .setContentText("Canción sonando")
                    .setSmallIcon(R.drawable.ic_slow_motion_video_black_24dp);

            Intent i=new Intent(this, MainActivity.class);
            PendingIntent contentIntent=PendingIntent.getActivity(this, 0,i, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(contentIntent);

            //Pasamos el servicio a primer plano
            startForeground(1, builder.build());
        }
    }
    //Cancelamos la notificación, y liberamos recursos del mediaPlayer
    private void stop() {
        if (isPlaying) {
            Log.d("play", "detenido");
            isPlaying=false;
            //Liberamos recursos
            mediaPlayer.release();
            mediaPlayer = null;
            //Cancelamos la notificación
            stopForeground(true);
        }
    }
}