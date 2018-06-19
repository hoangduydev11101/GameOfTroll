package com.duylh.hoisieukho.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.duylh.hoisieukho.Model.CauHoi;
import com.duylh.thanhtrolltrotai.R;
import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnNew, btnDanhGia, btnRank, btnExit;
    ImageButton btnQuestion, btnInfor, btnShare, btnMute;
    boolean kt = false;
    public static MediaPlayer mediaPlayer1;
    public static MediaPlayer mediaPlayer2;
    public static MediaPlayer mediaPlayer3;
    public  static AlphaAnimation animation;

    ArrayList<CauHoi> mangCauHoi;
    SharedPreferences sharedPreferences;
    int ma = 0;
    String name = "ahihi";
    int d = 0;
    Dialog dialog1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        XuLyButton();
        mediaPlayer1.start();
        mediaPlayer1.setLooping(true);



    }

    private void XuLyButton(){
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.activity_continue);
                TextView txtMessage = (TextView) dialog.findViewById(R.id.txtLoiNhan);
                Button btnNo = (Button) dialog.findViewById(R.id.btnDung);
                Button btnYes = (Button) dialog.findViewById(R.id.btnTiep);

                txtMessage.setText("Bạn thật sự muốn thoát game??");
                btnNo.setText("Tiếp");
                btnYes.setText("Nghỉ");

                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer1.stop();
                        mediaPlayer2.stop();
                        //SplashActivity.db.QueryData("DELETE FROM Question_Table");
                        finish();
                    }
                });

                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            }
        });

        btnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                if(mediaPlayer1.isPlaying()){
                    v.setBackgroundResource(R.drawable.mute);
                    mediaPlayer1.pause();
                    kt = true;
                }else {
                    v.setBackgroundResource(R.drawable.mute_not);
                    mediaPlayer1.start();
                    kt = false;
                }

            }
        });

        btnInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                Intent intent = new Intent(MainActivity.this, InformationActivity.class);
                startActivity(intent);
            }
        });
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer1.pause();
                if(kt==false){
                    mediaPlayer2.start();
                    //kt = true;
                }
                //mediaPlayer2.start();
                v.startAnimation(animation);
                Intent intent1 = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent1);
            }
        });

        btnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, XepHangActivity.class);
                startActivity(intent2);
            }
        });
        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // mangCauHoi.clear();
                Toast.makeText(MainActivity.this, "Đánh giá", Toast.LENGTH_SHORT).show();
               /* Cursor cursor = SplashActivity.db.GetData("SELECT * FROM Question_Table WHERE TTCauHoi = 1");
                while (cursor.moveToNext()){
                    mangCauHoi.add(new CauHoi(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7)));
                }
                for(int i=0; i<mangCauHoi.size(); i++){
                    Toast.makeText(MainActivity.this, mangCauHoi.get(i).CauHoi, Toast.LENGTH_SHORT).show();
                }
*/
                /*dialog1 = new Dialog(MainActivity.this);
                dialog1.setContentView(R.layout.activity_winer);
                dialog1.show();
                GifImageView gif = (GifImageView) dialog1.findViewById(R.id.gifWin);
                try {
                    InputStream inputStream = getAssets().open("win.gif");
                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    gif.setBytes(bytes);
                    gif.startAnimation();
                } catch (IOException e) {

                }
                Animation animation3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_textwin);
                TextView txtWin = (TextView) dialog1.findViewById(R.id.txtWin);
                txtWin.startAnimation(animation3);

                ImageButton btnSend = (ImageButton) dialog1.findViewById(R.id.btnSendWiner);
                final EditText edtName = (EditText) dialog1.findViewById(R.id.edtNameWiner);

                btnSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = edtName.getText().toString();
                        if(name.trim().equals("")){
                            Toast.makeText(MainActivity.this, "Chưa nhập tên!", Toast.LENGTH_SHORT).show();
                        }else {
                            SplashActivity.db.INSERT_RANK(name, 15);
                            //dem = 1;
                            dialog1.dismiss();
                        }
                    }
                });

                Window window = dialog1.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog1.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
*/
            }
        });
    }

    @Override
    public void onBackPressed() {
        dialog1 = new Dialog(MainActivity.this);
        dialog1.setContentView(R.layout.activity_continue);
        TextView txtMessage = (TextView) dialog1.findViewById(R.id.txtLoiNhan);
        Button btnNo = (Button) dialog1.findViewById(R.id.btnDung);
        Button btnYes = (Button) dialog1.findViewById(R.id.btnTiep);

        txtMessage.setText("Bạn thật sự muốn thoát game??");
        btnNo.setText("Tiếp");
        btnYes.setText("Nghỉ");

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer1.stop();
                mediaPlayer2.stop();
                finish();
            }
        });

        dialog1.show();
        Window window = dialog1.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }

    private void addControl(){
        mangCauHoi = new ArrayList<>();
        mediaPlayer3 = MediaPlayer.create(MainActivity.this, R.raw.winer);
        animation    = new AlphaAnimation(1f, 0.5f);
        btnNew       = (Button) findViewById(R.id.btnChoiMoi);
        btnDanhGia   = (Button) findViewById(R.id.btnDanhGia);
        btnRank      = (Button) findViewById(R.id.btnXepHang);
        btnExit      = (Button) findViewById(R.id.btnThoat);
        btnQuestion  = (ImageButton) findViewById(R.id.btnQuestion);
        btnInfor     = (ImageButton) findViewById(R.id.btnInfor);
        btnShare     = (ImageButton) findViewById(R.id.btnShare);
        btnMute      = (ImageButton) findViewById(R.id.btnMute);
        mediaPlayer1 = MediaPlayer.create(MainActivity.this, R.raw.ring1);
        mediaPlayer2 = MediaPlayer.create(MainActivity.this, R.raw.ring2);
    }
}
