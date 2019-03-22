package com.example.sqlitee1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnDbOpListener {

    private ImageView mimageView;
    private static final int Request_Image_Capture=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mimageView=findViewById(R.id.imageView);

        if(findViewById(R.id.fragment_container) !=null)
        {
            if(savedInstanceState !=null)
            {
                return;
            }
            HomeFragment homeFragment=new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,homeFragment).commit();

        }
    }

    @Override
    public void dBOpPerformed(int method) {

        switch (method)
        {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddContactFragment()).addToBackStack(null).commit();
                break;


            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ReadContactsFragment()).addToBackStack(null).commit();
                break;

            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UpdateFragment()).addToBackStack(null).commit();
                break;
        }

    }

    public void takePicture(View view) {

        Intent imageTakeIntent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (imageTakeIntent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(imageTakeIntent,Request_Image_Capture);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode==Request_Image_Capture && requestCode==RESULT_OK)
        {
            Bundle extras=data.getExtras();
            Bitmap imageBitmap=(Bitmap) extras.get("data");
            mimageView.setImageBitmap(imageBitmap);
        }
    }
}
