package com.example.erhan.pikacuyakala;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    ImageView image7;
    ImageView image8;
    ImageView image9;
    TextView skortext;
    TextView zamantext;
    ImageView [] imageArray;
    int skor=0;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image1=findViewById(R.id.imageView5);
        image2=findViewById(R.id.imageView6);
        image3=findViewById(R.id.imageView7);
        image4=findViewById(R.id.imageView8);
        image5=findViewById(R.id.imageView9);
        image6=findViewById(R.id.imageView10);
        image7=findViewById(R.id.imageView11);
        image8=findViewById(R.id.imageView12);
        image9=findViewById(R.id.imageView13);
        skortext=findViewById(R.id.textView3);
        zamantext=findViewById(R.id.textView2);
        imageArray=new ImageView[]{image1,image2,image3,image4,image5,image6,image7,image8,image9};
        ResimGizle();
        new CountDownTimer(15000,1000) {
            @Override
            public void onTick(long l) {
                zamantext.setText("Zaman: "+l/1000);
            }

            @Override
            public void onFinish() {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Yeni Oyun");
                alertDialog.setMessage("Oyun Yeniden Başlatılsın mı?");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray ){
                    image.setVisibility(View.INVISIBLE);
                }
                alertDialog.setPositiveButton(",Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=getIntent();
                        finish();
                       startActivity(intent);
                    }
                });
                alertDialog.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Oyun Bitti",Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();
            }
        }.start();
    }

    public void yakala(View view){

        skor++;
        skortext.setText("Skor: "+skor);

    }
    public void ResimGizle(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray ){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);


            }
        };
        handler.post(runnable);




    }
}
