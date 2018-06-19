package com.duylh.hoisieukho.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.duylh.thanhtrolltrotai.R;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        ImageButton btnFinish = (ImageButton) findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.nameofgame_anim);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.anim_nameme);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.anim_email);
        Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.anim_sdt);
        Animation animation4 = AnimationUtils.loadAnimation(this, R.anim.anim_fb);
        Animation animation5 = AnimationUtils.loadAnimation(this, R.anim.anim_btn);
        btnFinish.startAnimation(animation5);
        TextView txtName = (TextView) findViewById(R.id.txtNameofGame);
        txtName.startAnimation(animation);
        TextView txtNameofMe = (TextView) findViewById(R.id.txtNameofMe);
        txtNameofMe.startAnimation(animation1);
        TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtEmail.startAnimation(animation2);
        TextView txtSDT = (TextView) findViewById(R.id.txtSDT);
        txtSDT.startAnimation(animation3);
        TextView txtFB = (TextView) findViewById(R.id.txtFB);
        txtFB.startAnimation(animation4);
        final TextView txtVer = (TextView) findViewById(R.id.txtVersion);
        txtVer.startAnimation(animation5);
        txtVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InformationActivity.this, txtVer.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
