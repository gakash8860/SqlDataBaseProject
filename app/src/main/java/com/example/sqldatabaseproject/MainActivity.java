package com.example.sqldatabaseproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST =1 ;
    EditText name,mail,pass;
    Button upload,signup,camer;
    String PERMISSIONS[]={Manifest.permission.CAMERA};
    DataBaseHelper db;
    Uri mImageuri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.tname);
        mail=findViewById(R.id.tmail);
        db=new DataBaseHelper(this);
        pass=findViewById(R.id.tpass);
        signup=findViewById(R.id.signup);
        upload=findViewById(R.id.upload);
        if (name.equals(null)){
            name.setError("Enter your Name");
        }else if(mail.equals(null)){
            mail.setError("Enter your Email : ");
        }else if (pass.equals(null)){
            pass.setError("Enter your Password");
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean result = db.insertData(name.getText().toString(),mail.getText().toString(), pass.getText().toString());
               Log.e("Result",""+result);

                if (result) {
                    Toast.makeText(MainActivity.this, "Data is inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data is not inserted", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
    public void chooseFile(View view){
        CropImage.activity().start(MainActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (requestCode==RESULT_OK){
                mImageuri=result.getUri();
            }else if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                Exception e = result.getError();
                Toast.makeText(this, "Possible Error is"+e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}