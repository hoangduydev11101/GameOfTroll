package com.duylh.hoisieukho.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.duylh.hoisieukho.Model.XepHangAdapter;
import com.duylh.hoisieukho.Model.Xephang;
import com.duylh.thanhtrolltrotai.R;

import java.util.ArrayList;

public class XepHangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xep_hang);

        ArrayList<Xephang> arrRank = new ArrayList<>();

            ListView lvRank = (ListView) findViewById(R.id.lvXepHang);

        Cursor cursor = SplashActivity.db.GetData("SELECT * FROM XepHang_Table ORDER BY Diem DESC LIMIT 10");
        while (cursor.moveToNext()){
            arrRank.add(new Xephang(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));

        }
        XepHangAdapter adapter = new XepHangAdapter(XepHangActivity.this, R.layout.row_xh, arrRank);

        lvRank.setAdapter(adapter);
    }
}
