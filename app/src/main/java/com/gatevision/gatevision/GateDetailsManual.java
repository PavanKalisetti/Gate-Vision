package com.gatevision.gatevision;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;


import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.io.IOException;


public class GateDetailsManual extends AppCompatActivity {

    CardView GateDetailsSlidingGateCardview, GateDetailsSwingGateCardview, CablingProvisions, MaterialStorage;
    Boolean[] typeOfGate = {false, false};
    Boolean[] CablingAndMaterial = {false, false};
    TextView GateDetailsSlidingText, GateDetailsSwingGateText, CablingProvisionsText, MaterialStorageText;

    Button GateDetailsSubmitButton;

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 101;

    private Button uploadImageButton;
    private ImageView imagePreview;

    private CardView uploadImgCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gate_details_manual);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        GateDetailsSlidingGateCardview = findViewById(R.id.GateDetailsSlidingGateCardview);
        GateDetailsSwingGateCardview = findViewById(R.id.GateDetailsSwingGateCardview);

        GateDetailsSlidingText = findViewById(R.id.GateDetailsSlidingText);
        GateDetailsSwingGateText = findViewById(R.id.GateDetailsSwingGateText);

        GateDetailsSubmitButton = findViewById(R.id.GateDetailsSubmitButton);

        uploadImgCardView = findViewById(R.id.uploadImgCardView);


        CablingProvisions = findViewById(R.id.CablingProvisions);
        MaterialStorage = findViewById(R.id.MaterialStorage);
        CablingProvisionsText = findViewById(R.id.CablingProvisionsText);
        MaterialStorageText = findViewById(R.id.MaterialStorageText);


        GateDetailsSlidingGateCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!typeOfGate[0]){
                    GateDetailsSlidingGateCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2277F6")));
                    GateDetailsSwingGateCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ECF0F1")));

                    GateDetailsSlidingText.setTextColor(getResources().getColor(R.color.white));
                    GateDetailsSwingGateText.setTextColor(getResources().getColor(R.color.black));
                    typeOfGate[0] = true;
                }else{
                    GateDetailsSlidingGateCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ECF0F1")));
                    typeOfGate[0] = false;
                }


            }
        });


        GateDetailsSwingGateCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!typeOfGate[1]){
                    GateDetailsSwingGateCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2277F6")));
                    GateDetailsSlidingGateCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ECF0F1")));
                    GateDetailsSwingGateText.setTextColor(getResources().getColor(R.color.white));
                    GateDetailsSlidingText.setTextColor(getResources().getColor(R.color.black));
                    typeOfGate[1] = true;
                }else{
                    GateDetailsSwingGateCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ECF0F1")));
                    typeOfGate[1] = false;
                }



            }
        });

        CablingProvisions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!CablingAndMaterial[0]){
                    CablingProvisions.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2277F6")));
                    CablingProvisionsText.setTextColor(getResources().getColor(R.color.white));
                    CablingAndMaterial[0] = true;
                }else{
                    CablingProvisions.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ECF0F1")));
                    CablingProvisionsText.setTextColor(getResources().getColor(R.color.black));
                    CablingAndMaterial[0] = false;
                }
            }
        });

        MaterialStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!CablingAndMaterial[1]){
                    MaterialStorage.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2277F6")));
                    MaterialStorageText.setTextColor(getResources().getColor(R.color.white));
                    CablingAndMaterial[1] = true;
                }else{
                    MaterialStorage.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ECF0F1")));
                    MaterialStorageText.setTextColor(getResources().getColor(R.color.black));
                    CablingAndMaterial[1] = false;
                }
            }
        });


        GateDetailsSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GateDetailsManual.this, Output.class);
                startActivity(intent);
            }
        });


        uploadImageButton = findViewById(R.id.uploadImageButton);
        imagePreview = findViewById(R.id.imagePreview);

        // Set a click listener to open the gallery
        uploadImageButton.setOnClickListener(v -> {
            if (checkPermission()) {
                openImagePicker();
            } else {
                requestStoragePermission();
            }
        });


    }


    // Method to open image picker
    private void openImagePicker() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    // Check for storage permission
    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    // Request storage permission
    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                STORAGE_PERMISSION_CODE);
    }

    // Handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Handle the image selected from gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                // Convert image URI to Bitmap and display it in the ImageView
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imagePreview.setImageBitmap(bitmap);
                uploadImgCardView.setVisibility(View.GONE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



}