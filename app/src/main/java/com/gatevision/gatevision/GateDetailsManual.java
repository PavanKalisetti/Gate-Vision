package com.gatevision.gatevision;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class GateDetailsManual extends AppCompatActivity {

    CardView GateDetailsSlidingGateCardview, GateDetailsSwingGateCardview, GateDetailsOthersCardview;
    Boolean[] typeOfGate = {false, false, false};
    TextView GateDetailsSlidingText, GateDetailsSwingGateText, GateDetailsOthersText;

    Button GateDetailsSubmitButton;

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
        GateDetailsOthersCardview = findViewById(R.id.GateDetailsOthersCardview);
        GateDetailsSlidingText = findViewById(R.id.GateDetailsSlidingText);
        GateDetailsSwingGateText = findViewById(R.id.GateDetailsSwingGateText);
        GateDetailsOthersText = findViewById(R.id.GateDetailsOthersText);
        GateDetailsSubmitButton = findViewById(R.id.GateDetailsSubmitButton);


        GateDetailsSlidingGateCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!typeOfGate[0]){
                    GateDetailsSlidingGateCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2277F6")));
                    GateDetailsSwingGateCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ECF0F1")));
                    GateDetailsOthersCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ECF0F1")));
                    GateDetailsSlidingText.setTextColor(getResources().getColor(R.color.white));
                    GateDetailsSwingGateText.setTextColor(getResources().getColor(R.color.black));
                    GateDetailsOthersText.setTextColor(getResources().getColor(R.color.black));
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
                    GateDetailsOthersCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ECF0F1")));
                    GateDetailsSwingGateText.setTextColor(getResources().getColor(R.color.white));
                    GateDetailsSlidingText.setTextColor(getResources().getColor(R.color.black));
                    GateDetailsOthersText.setTextColor(getResources().getColor(R.color.black));
                    typeOfGate[1] = true;
                }else{
                    GateDetailsSwingGateCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ECF0F1")));
                    typeOfGate[1] = false;
                }



            }
        });

        GateDetailsOthersCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!typeOfGate[2]){
                    GateDetailsOthersCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2277F6")));
                    GateDetailsSlidingGateCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ECF0F1")));
                    GateDetailsSwingGateCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ECF0F1")));
                    GateDetailsOthersText.setTextColor(getResources().getColor(R.color.white));
                    GateDetailsSlidingText.setTextColor(getResources().getColor(R.color.black));
                    GateDetailsSwingGateText.setTextColor(getResources().getColor(R.color.black));
                    typeOfGate[2] = true;
                }else{
                    GateDetailsOthersCardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ECF0F1")));
                    typeOfGate[2] = false;
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
    }
}