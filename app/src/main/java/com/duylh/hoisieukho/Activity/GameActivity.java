package com.duylh.hoisieukho.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView txtDiem, txtTime, txt5050, txtKhanGia, txtTuVan, txtChonA, txtChonB, txtChonC, txtChonD, txtCauHoiSo, txtCauHoi;
    ImageButton btnBack;
    Button btnStop;
    int dem = 0;
    ArrayList<CauHoi> arrCauHoi;
    int vt = 0;
    String da = "";
    ArrayList<String> mangDapAn;
    CountDownTimer timer;
    int check = 0;
    int cau;
    int d = 0;
    Animation animation;
    long time;
    SharedPreferences sharedPreferences;
    Dialog dialog1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        addControl();
        Button();

        sharedPreferences = getSharedPreferences("ghinho", Context.MODE_PRIVATE);
        dem = sharedPreferences.getInt("ma", 1);

        XuLyCauHoi();

        ChonDapAn();
        XuLyHelp();


}

    private void Button(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cau = dem - 1;

                if(btnBack.isClickable()){
                    timer.cancel();
                }else {
                    DemThoiGian();
                }


                final Dialog dialog = new Dialog(GameActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.activity_continue);

                TextView txtMessage = (TextView) dialog.findViewById(R.id.txtLoiNhan);
                Button btnDung = (Button) dialog.findViewById(R.id.btnDung);
                Button btnTiep = (Button) dialog.findViewById(R.id.btnTiep);

                if(dem == 1){
                    txtMessage.setText("Tạm dừng khi bạn đang là 0 Điểm.. Vẫn muốn tạm dừng?");
                }else
                txtMessage.setText("Nếu tạm dừng, lần tiếp theo bạn sẽ bắt đầu từ câu "+cau +". Bạn vẫn muốn tạm dừng?");
                btnDung.setText("Dừng");
                btnTiep.setText("Tiếp");
                btnDung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog dialog1 = new Dialog(GameActivity.this);
                        dialog1.setCancelable(false);
                        dialog1.setContentView(R.layout.input_point);
                        XuLyGhiNho();
                        MainActivity.mediaPlayer2.stop();
                        MainActivity.mediaPlayer1.start();
                        finish();
                    }
                });

                btnTiep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        DemThoiGian();
                    }
                });
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnBack.isClickable()){
                    timer.cancel();
                }else {
                    DemThoiGian();
                }

                final int cau = dem-1;
                final Dialog dialog = new Dialog(GameActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.activity_continue);

                TextView txtMessage = (TextView) dialog.findViewById(R.id.txtLoiNhan);
                Button btnDung = (Button) dialog.findViewById(R.id.btnDung);
                Button btnTiep = (Button) dialog.findViewById(R.id.btnTiep);

                txtMessage.setText("Bạn chắc chắn muốn dừng chơi tại đây?");
                btnDung.setText("Dừng");
                btnTiep.setText("Tiếp");
                btnDung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.mediaPlayer2.stop();

                        final Dialog dialog1 = new Dialog(GameActivity.this);
                        dialog1.setContentView(R.layout.input_point);
                        dialog1.setCancelable(false);
                        dialog1.show();
                        Button btnHuy = (Button) dialog1.findViewById(R.id.btnHuyAddPoint);
                        Button btnAdd = (Button) dialog1.findViewById(R.id.btnAddPoint);
                        final  EditText edtPlayer = (EditText) dialog1.findViewById(R.id.edtPlayer);
                        final TextView txtPoint = (TextView) dialog1.findViewById(R.id.txtPoint);
                        txtPoint.setText("Điểm: "+cau);

                        btnHuy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dem = 1;
                                XuLyGhiNho();
                                timer.cancel();
                                dialog1.dismiss();
                                dialog.dismiss();
                                finish();

                            }
                        });

                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dem = 1;
                                XuLyGhiNho();
                                String name = edtPlayer.getText().toString();
                                if (name.trim().equals("")){
                                    Toast.makeText(GameActivity.this, "Chưa nhập tên!!", Toast.LENGTH_SHORT).show();
                                }else{
                                    SplashActivity.db.INSERT_RANK(name, cau);

                                    timer.cancel();
                                    dialog1.dismiss();
                                    MainActivity.mediaPlayer2.stop();
                                    MainActivity.mediaPlayer1.start();
                                    finish();
                                }
                            }
                        });


                    }
                });

                btnTiep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        DemThoiGian();
                    }
                });
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            }
        });
    }

    private void Winner(){
        dialog1 = new Dialog(GameActivity.this);
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
        Animation animation3 = AnimationUtils.loadAnimation(GameActivity.this, R.anim.anim_textwin);
        TextView txtWin = (TextView) dialog1.findViewById(R.id.txtWin);
        txtWin.startAnimation(animation3);

        ImageButton btnSend = (ImageButton) dialog1.findViewById(R.id.btnSendWiner);
        final EditText edtName = (EditText) dialog1.findViewById(R.id.edtNameWiner);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                if(name.trim().equals("")){
                    Toast.makeText(GameActivity.this, "Chưa nhập tên!", Toast.LENGTH_SHORT).show();
                }else {
                    SplashActivity.db.INSERT_RANK(name, 15);
                    dem = 1;
                    dialog1.dismiss();
                    finish();
                }
            }
        });

        Window window = dialog1.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }

    private void addControl(){
        animation   = AnimationUtils.loadAnimation(GameActivity.this, R.anim.anim_help);
        mangDapAn   = new ArrayList<>();
        arrCauHoi   = new ArrayList<>();
        txtDiem     = (TextView) findViewById(R.id.txtDiem);
        txtTime     = (TextView) findViewById(R.id.txtTimeGame);
        txt5050     = (TextView) findViewById(R.id.txt5050);
        txtKhanGia  = (TextView) findViewById(R.id.txtKhanGia);
        txtTuVan    = (TextView) findViewById(R.id.txtTuVan);
        txtChonA    = (TextView) findViewById(R.id.txtChonA);
        txtChonB    = (TextView) findViewById(R.id.txtChonB);
        txtChonC    = (TextView) findViewById(R.id.txtChonC);
        txtChonD    = (TextView) findViewById(R.id.txtChonD);
        txtCauHoiSo = (TextView) findViewById(R.id.txtCauHoiSo);
        txtCauHoi   = (TextView) findViewById(R.id.txtCauHoi);
        btnBack     = (ImageButton) findViewById(R.id.btnPauseGame);
        btnStop     = (Button) findViewById(R.id.btnStop);
    }


    private void XuLyCauHoi(){

       // dem = 1;
        if(dem==1){
            btnStop.setBackgroundResource(R.drawable.hand_not);
            btnStop.setEnabled(false);
        }
        else if(dem>1){
            btnStop.setBackgroundResource(R.drawable.hand);
            btnStop.setEnabled(true);
        }

        time = 0;
        if(dem < 6){
            time = 31000;
        }else if(dem > 5 && dem < 11){
            time = 61000;
        }else time = 151000;

        if(check == 999) {
            time = time / 2;
            check = 0;
        }

        int diem = dem -1;
        txtDiem.setText("Diểm: "+diem);

        DemThoiGian();


        txtChonA.setBackgroundResource(R.drawable.bg_dapan);
        txtChonB.setBackgroundResource(R.drawable.bg_dapan);
        txtChonC.setBackgroundResource(R.drawable.bg_dapan);
        txtChonD.setBackgroundResource(R.drawable.bg_dapan);
        String a, b, c, d;
        //Toast.makeText(this, dem+"", Toast.LENGTH_SHORT).show();

        arrCauHoi.clear();
        Cursor cursor = SplashActivity.db.GetData("SELECT * FROM Question_Table WHERE TTCauHoi = '"+dem+"'");
        while (cursor.moveToNext()){
            arrCauHoi.add(new CauHoi(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7)));
        }
        mangDapAn = new ArrayList<>();
        mangDapAn.clear();

        Random rand = new Random();

        vt = rand.nextInt(arrCauHoi.size());
        a = arrCauHoi.get(vt).DapanA;
        b = arrCauHoi.get(vt).DapanB;
        c = arrCauHoi.get(vt).DapanC;
        d = arrCauHoi.get(vt).DapanD;
        da = arrCauHoi.get(vt).DapAn;
        mangDapAn.add(a); mangDapAn.add(b); mangDapAn.add(c); mangDapAn.add(d);
        Collections.shuffle(mangDapAn);

        txtCauHoiSo.setText("Câu "+dem);
        txtCauHoi.setText(arrCauHoi.get(vt).CauHoi);


        Animation animation = new TranslateAnimation(700, 0,0, 0);
        animation.setDuration(700);
        animation.setFillAfter(true);
        txtChonA.startAnimation(animation);
        txtChonC.startAnimation(animation);

        Animation animation1 = new TranslateAnimation(-700, 0,0, 0);
        animation1.setDuration(700);
        animation1.setFillAfter(true);
        txtChonB.startAnimation(animation1);
        txtChonD.startAnimation(animation1);

        SetEmty();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtChonA.setText("A: "+mangDapAn.get(0));
                txtChonB.setText("B: "+mangDapAn.get(1));
                txtChonC.setText("C: "+mangDapAn.get(2));
                txtChonD.setText("D: "+mangDapAn.get(3));
            }
        }, 800);

    }

    private void SetEmty(){
        txtChonA.setText("");
        txtChonB.setText("");
        txtChonC.setText("");
        txtChonD.setText("");
    }
    private void ChonDapAn(){
        txtChonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(MainActivity.animation);
                if(txtChonA.getText().toString().trim().substring(3).equals(da.toString().trim())){
                    dem = dem+1;
                    if(dem>15){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TuVan();
                                Winner();
                                MainActivity.mediaPlayer2.stop();
                                MainActivity.mediaPlayer3.start();
                                timer.cancel();
                            }
                        }, 1000);
                    }else{
                        timer.cancel();
                        Continue();
                    }

                }else {XuLyLose(); TuVan();}
            }
        });

        txtChonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(MainActivity.animation);
                if(txtChonB.getText().toString().trim().substring(3).equals(da.toString().trim())){
                    dem = dem+1;
                    if(dem>15){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TuVan();
                                Winner();
                                MainActivity.mediaPlayer2.stop();
                                MainActivity.mediaPlayer3.start();
                                timer.cancel();
                            }
                        }, 1000);
                    }else{
                        timer.cancel();
                        Continue();
                    }
                }else {XuLyLose(); TuVan();}
            }
        });
        txtChonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(MainActivity.animation);
                if(txtChonC.getText().toString().trim().substring(3).equals(da.toString().trim())){
                    dem = dem+1;
                    if(dem>15){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TuVan();
                                Winner();
                                MainActivity.mediaPlayer2.stop();
                                MainActivity.mediaPlayer3.start();
                                timer.cancel();
                            }
                        }, 1000);
                    }else{
                        timer.cancel();
                        Continue();
                    }
                }else {XuLyLose(); TuVan();}
            }
        });
        txtChonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(MainActivity.animation);
                if(txtChonD.getText().toString().trim().substring(3).equals(da.toString().trim())){
                    dem = dem+1;
                    if(dem>15){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TuVan();
                                Winner();
                                MainActivity.mediaPlayer2.stop();
                                MainActivity.mediaPlayer3.start();
                                timer.cancel();
                            }
                        }, 1000);
                    }else{
                        timer.cancel();
                        Continue();
                    }
                }else {XuLyLose(); TuVan();}
            }
        });
    }

    private void XuLyHelp(){

        txt5050.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(GameActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.activity_continue);

                final TextView txtMess = (TextView) dialog.findViewById(R.id.txtLoiNhan);
                final Button btnYes = (Button) dialog.findViewById(R.id.btnTiep);
                Button btnNo = (Button) dialog.findViewById(R.id.btnDung);

                btnNo.setText("Noo");
                btnYes.setText("Yess");

                txtMess.setText("Sủ dụng quyền trợ giúp loại đi 2 phương án sai?");
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txt5050.setEnabled(false);
                        txt5050.setBackgroundResource(R.drawable.bg_offhelp);
                        dialog.dismiss();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Chon5050();
                            }
                        },1500);

                    }
                });
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            }
        });

        txtKhanGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(GameActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.activity_continue);

                final TextView txtMess = (TextView) dialog.findViewById(R.id.txtLoiNhan);
                final Button btnYes = (Button) dialog.findViewById(R.id.btnTiep);
                Button btnNo = (Button) dialog.findViewById(R.id.btnDung);

                btnNo.setText("Noo");
                btnYes.setText("Yess");

                txtMess.setText("Sủ dụng quyền trợ giúp đổi câu hỏi?");
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtKhanGia.setEnabled(false);
                        txtKhanGia.setBackgroundResource(R.drawable.bg_offhelp);
                        dialog.dismiss();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                check = 999;
                                timer.cancel();
                                XuLyCauHoi();

                            }
                        },1500);

                    }
                });
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            }
        });
        txtTuVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(GameActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.activity_continue);

                final TextView txtMess = (TextView) dialog.findViewById(R.id.txtLoiNhan);
                final Button btnYes = (Button) dialog.findViewById(R.id.btnTiep);
                Button btnNo = (Button) dialog.findViewById(R.id.btnDung);
                btnNo.setText("Noo");
                btnYes.setText("Yess");

                txtMess.setText("Sủ dụng quyền trợ giúp tư vấn?");
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtTuVan.setEnabled(false);
                        txtTuVan.setBackgroundResource(R.drawable.bg_offhelp);
                        dialog.dismiss();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TuVan();
                            }
                        },1500);

                    }
                });
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            }
        });
    }

    private void DemThoiGian(){
        //isCounterRunning = true;

        timer = new CountDownTimer(time, 1000) {


            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                txtTime.setText("Time: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                XuLyLose();
            }
        }.start();
    }


    private void Continue(){

        ArrayList<String> mangMS = new ArrayList<>();
        mangMS.add("Quá giỏi");
        mangMS.add("Quá thông minh!!!");
        mangMS.add("Qúa hay..");
        mangMS.add("Tuyệt vời ông mặt trời");
        mangMS.add("Đúng rồi..!! Ấn tiếp đê");
        mangMS.add("Tới lun ae ê.>!!");
        mangMS.add("Con cái nhà ai mà thông minh quá z nè.. @_@");
        mangMS.add("Wao..... Thật là ngưỡng cmn mộ :O");
        mangMS.add("IQ của bạn gần bằng Albert Einstein rồi đấy");
        mangMS.add("Tiếp nào bà con ơi..!!");
        mangMS.add("Ahihi... Thiên tài đây rồi..!!");
        mangMS.add("Obama đang chơi game này sao?? :O");
        mangMS.add("Tương lai của đất nước Việt Nam đây rồi.!!");
        mangMS.add("Ôi mẹ ơi!! con muốn giỏi như bạn ấy :((");
        mangMS.add("Kinh thật đấy??");
        mangMS.add("Chỉ có thể là hack thôi!");
        mangMS.add("Sao cái gì kug biết thế hở??");
        mangMS.add("Câu khó hơn đang ở phía trước");
        mangMS.add("Câu này dễ quá đúng không?");
        mangMS.add("Khó thế mà cũng biết á?");
        mangMS.add("Tôi iu bạn mất thôi!! Giỏi quá cơ...");
        mangMS.add("Á đù..!! Biết luôn bây??");


        final Dialog dialog = new Dialog(GameActivity.this);
        dialog.setContentView(R.layout.activity_continue);
        Button btnDung = (Button) dialog.findViewById(R.id.btnDung);
        Button btnTiep = (Button) dialog.findViewById(R.id.btnTiep);
        final TextView txtMessage = (TextView) dialog.findViewById(R.id.txtLoiNhan);

        Random rand = new Random();
        int vtMs = rand.nextInt(mangMS.size());

        txtMessage.setText(mangMS.get(vtMs));

        btnDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cau = dem-1;
                dialog.dismiss();
                final Dialog dialog1 = new Dialog(GameActivity.this);
                dialog1.setCancelable(false);
                dialog1.setContentView(R.layout.input_point);
                Button btnHuy = (Button) dialog1.findViewById(R.id.btnHuyAddPoint);
                Button btnAdd = (Button) dialog1.findViewById(R.id.btnAddPoint);
                final  EditText edtPlayer = (EditText) dialog1.findViewById(R.id.edtPlayer);

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                        dem = 1;
                        XuLyGhiNho();
                        finish();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = edtPlayer.getText().toString();
                        if (name.trim().equals("")){
                            Toast.makeText(GameActivity.this, "Chưa nhập tên!!", Toast.LENGTH_SHORT).show();
                        }else {
                            SplashActivity.db.INSERT_RANK(name, cau);
                            dialog1.dismiss();
                            dem = 1;
                            XuLyGhiNho();
                            finish();
                        }

                    }
                });

                dialog1.show();
            }
        });

        btnTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLyCauHoi();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    private void XuLyLose(){
        final Dialog dialog = new Dialog(GameActivity.this);
        dialog.setContentView(R.layout.activity_lose);
        Button btnThoat = (Button) dialog.findViewById(R.id.btnThoatLose);
        Button btnContinue = (Button) dialog.findViewById(R.id.btnContinueLose);
        ImageView img = (ImageView) dialog.findViewById(R.id.imgsadeffect);
        Animation animation = AnimationUtils.loadAnimation(GameActivity.this, R.anim.anim_sad);
        img.startAnimation(animation);
        dialog.setCancelable(false);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cau = dem-1;
                dialog.dismiss();

                MainActivity.mediaPlayer2.pause();
                final Dialog dialog1 = new Dialog(GameActivity.this);
                dialog1.setCancelable(false);
                dialog1.setContentView(R.layout.input_point);
                dialog1.show();
                Button btnHuy = (Button) dialog1.findViewById(R.id.btnHuyAddPoint);
                Button btnAdd = (Button) dialog1.findViewById(R.id.btnAddPoint);
                final  EditText edtPlayer = (EditText) dialog1.findViewById(R.id.edtPlayer);
                final TextView txtPoint = (TextView) dialog1.findViewById(R.id.txtPoint);
                txtPoint.setText("Điểm: "+cau);

                dem = 1;
                if(cau >= 5){
                    dialog1.show();
                }else {
                    dialog1.dismiss();
                    dialog.dismiss();
                    finish();
                }

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        XuLyGhiNho();
                        timer.cancel();
                        dialog1.dismiss();
                        finish();

                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        XuLyGhiNho();
                        String name = edtPlayer.getText().toString();
                        if (name.trim().equals("")){
                            Toast.makeText(GameActivity.this, "Chưa nhập tên!!", Toast.LENGTH_SHORT).show();
                        }else{
                            SplashActivity.db.INSERT_RANK(name, cau);

                            timer.cancel();
                            dialog1.dismiss();
                            finish();
                        }
                    }
                });

            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dem = 1;
                XuLyGhiNho();
                timer.cancel();
                dialog.dismiss();
                txt5050.setEnabled(true);
                txt5050.setBackgroundResource(R.drawable.bg_help);
                txtKhanGia.setEnabled(true);
                txtKhanGia.setBackgroundResource(R.drawable.bg_help);
                txtTuVan.setEnabled(true);
                txtTuVan.setBackgroundResource(R.drawable.bg_help);
                XuLyCauHoi();

            }
        });
            }

    private void XuLyGhiNho(){
        int kt;
        sharedPreferences = getSharedPreferences("ghinho", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        if(dem == 1){
            kt = 1;
        }else kt = dem - 1;
        editor.putInt("ma", kt);
        editor.commit();
        timer.cancel();
    }

    private void Chon5050(){
        if(txtChonA.getText().toString().substring(3).trim().equals(arrCauHoi.get(vt).DapAn.trim())){
            Random rand = new Random();
            int a = rand.nextInt(3);
            if(a == 0){
                txtChonB.setText("B: ");
                txtChonC.setText("C: ");
            }else if(a == 1){
                txtChonB.setText("B: ");
                txtChonD.setText("D: ");
            } else if(a == 2){
                txtChonC.setText("C: ");
                txtChonD.setText("D: ");
            }
        }else if(txtChonB.getText().toString().substring(3).trim().equals(arrCauHoi.get(vt).DapAn.trim())){
            Random rand = new Random();
            int a = rand.nextInt(3);
            if(a == 0){
                txtChonA.setText("A: ");
                txtChonC.setText("C: ");
            }else if(a == 1){
                txtChonA.setText("A: ");
                txtChonD.setText("D: ");
            } else if(a == 2){
                txtChonC.setText("C: ");
                txtChonD.setText("D: ");
            }
        }else if(txtChonC.getText().toString().substring(3).trim().equals(arrCauHoi.get(vt).DapAn.trim())){
            Random rand = new Random();
            int a = rand.nextInt(3);
            if(a == 0){
                txtChonA.setText("A: ");
                txtChonB.setText("B: ");
            }else if(a == 1){
                txtChonA.setText("A: ");
                txtChonD.setText("D: ");
            } else if(a == 2){
                txtChonB.setText("B: ");
                txtChonD.setText("D: ");
            }
        }else if(txtChonD.getText().toString().substring(3).trim().equals(arrCauHoi.get(vt).DapAn.trim())){
            Random rand = new Random();
            int a = rand.nextInt(3);
            if(a == 0){
                txtChonA.setText("A: ");
                txtChonB.setText("B: ");
            }else if(a == 1){
                txtChonA.setText("A: ");
                txtChonC.setText("C: ");
            } else if(a == 2){
                txtChonB.setText("B: ");
                txtChonC.setText("C: ");
            }
        }
    }

    private void TuVan(){
        if(txtChonA.getText().toString().substring(3).trim().equals(arrCauHoi.get(vt).DapAn.trim())){
            txtChonA.startAnimation(animation);
            txtChonA.setBackgroundResource(R.drawable.bg_tv);
        }else if(txtChonB.getText().toString().substring(3).trim().equals(arrCauHoi.get(vt).DapAn.trim())){

            txtChonB.startAnimation(animation);
            txtChonB.setBackgroundResource(R.drawable.bg_tv);
        }
        else if(txtChonC.getText().toString().substring(3).trim().equals(arrCauHoi.get(vt).DapAn.trim())){

            txtChonC.startAnimation(animation);
            txtChonC.setBackgroundResource(R.drawable.bg_tv);
        }else if(txtChonD.getText().toString().substring(3).trim().equals(arrCauHoi.get(vt).DapAn.trim())){

            txtChonD.startAnimation(animation);
            txtChonD.setBackgroundResource(R.drawable.bg_tv);
        }
    }

    @Override
    public void onBackPressed() {

    }

}
