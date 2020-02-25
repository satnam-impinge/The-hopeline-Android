package com.payoda.hopeline;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Ayush on 2/14/2018.
 */

public class DemoActivity extends Activity {

    @Override


    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_demo);





        //initializing layouts


        ImageView imageView = (ImageView) findViewById(R.id.textView);


        ImageView snapImage = (ImageView)findViewById(R.id.imageView);


        imageView.setDrawingCacheEnabled(true);




        //specifying the dimensions of view


        imageView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),


                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));




        imageView.layout(0, 0, imageView.getMeasuredWidth(), imageView.getMeasuredHeight());




        imageView.buildDrawingCache(true);


        Bitmap b = Bitmap.createBitmap(imageView.getDrawingCache());


        imageView.setDrawingCacheEnabled(false); // clear drawing cache




        snapImage.setImageBitmap(b);





        ByteArrayOutputStream bytes = new ByteArrayOutputStream();


        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);




        //create a new file in sdcard name it as "testimage.jpg"


        File fileImage = new File(Environment.getExternalStorageDirectory()


                + File.separator + "testimage.jpg");





        //write the bytes in file


        FileOutputStream fo = null;


        try {


            fileImage.createNewFile();


            fo = new FileOutputStream(fileImage);


            fo.write(bytes.toByteArray());


        } catch (FileNotFoundException e) {


            e.printStackTrace();


        } catch (IOException e) {


            e.printStackTrace();


        }


    }


}

