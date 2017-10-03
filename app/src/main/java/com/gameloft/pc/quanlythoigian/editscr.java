package com.gameloft.pc.quanlythoigian;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class editscr extends AppCompatActivity {
    int i;
    int size;
    private final String SHARED_PREFERENCES_NAME="data_detailscr";
    private final String SHARED_PREFERENCES_SIZE="data_size";
    private final String MON="mon";
    private final String PHONG="phong";
    private final String THOIGIAN="thoigian";
    private final String GIANGVIEN="giangvien";
    private final String EMAIL="email";
    private final String SDT="sdt";
    private final String SIZE="size";
    private EditText edtmon,edtphong,edttg,edtgv,edtemail,edtsdt;
    private Button btnluu,btnxoa;

    private EditText edtThoiGianBatDau, edtThoiGianKetThuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editscr);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("MyPackage");
        i=bundle.getInt("pos");

        anhxa();

        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tang kich thuoc arraylist len 1 va luu
                size++;
                SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_SIZE,MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(SIZE,String.valueOf(size));
                editor.apply();
                //them du lieu va luu
                add_data();
                Intent ht=new Intent(editscr.this,thoi_khoa_bieu.class);
                startActivity(ht);
            }


        });
        btnxoa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                data_move();
                Intent ht1=new Intent(editscr.this,thoi_khoa_bieu.class);
                startActivity(ht1);
            }
        });
        edtThoiGianBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogBatDau();
            }
        });
        edtThoiGianKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogKetThuc();
            }
        });
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_SIZE,MODE_PRIVATE);
        size=Integer.parseInt(sharedPreferences.getString(SIZE,"0"));
        inraeditscr();
    }

    private void anhxa(){
        edtmon=(EditText)findViewById(R.id.môn);
        edtphong=(EditText)findViewById(R.id.phong);
        edttg=(EditText) findViewById(R.id.thoigianbatdau);
        edtgv=(EditText)findViewById(R.id.giangvien);
        edtemail=(EditText)findViewById(R.id.email);
        edtsdt=(EditText)findViewById(R.id.sdt);
        btnluu=(Button)findViewById(R.id.btnhienthi);
        btnxoa=(Button)findViewById(R.id.btnxoadata);
        edtThoiGianBatDau=(EditText) findViewById(R.id.thoigianbatdau);
        edtThoiGianKetThuc=(EditText) findViewById(R.id.thoigianketthuc);
    }

    public void add_data(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_NAME+String.valueOf(i),MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(MON,edtmon.getText().toString());
        editor.putString(PHONG,edtphong.getText().toString());
        editor.putString(THOIGIAN,edttg.getText().toString());
        editor.putString(GIANGVIEN,edtgv.getText().toString());
        editor.putString(EMAIL,edtemail.getText().toString());
        editor.putString(SDT,edtsdt.getText().toString());
        editor.apply();
    }
    public void inraeditscr(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_NAME+String.valueOf(i),MODE_PRIVATE);
        edtmon.setText(sharedPreferences.getString(MON,""));
        edtphong.setText(sharedPreferences.getString(PHONG,""));
        edttg.setText(sharedPreferences.getString(THOIGIAN,""));
        edtgv.setText(sharedPreferences.getString(GIANGVIEN,""));
        edtemail.setText(sharedPreferences.getString(EMAIL,""));
        edtsdt.setText(sharedPreferences.getString(SDT,""));
    }

    private void showTimePickerDialogBatDau() {
        Calendar calendar= Calendar.getInstance();
        int hour= calendar.get(Calendar.HOUR);
        int min= calendar.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfday, int minute) {
                edtThoiGianBatDau.setText(hourOfday+" : "+minute);
            }
        };

        TimePickerDialog pic=new TimePickerDialog(this, onTimeSetListener, hour, min, true);
        pic.show();

    }
    private void showTimePickerDialogKetThuc() {
        Calendar calendar= Calendar.getInstance();
        int hour= calendar.get(Calendar.HOUR);
        int min= calendar.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfday, int minute) {
                edtThoiGianKetThuc.setText(hourOfday+" : "+minute);
            }
        };

        TimePickerDialog pic=new TimePickerDialog(this, onTimeSetListener, hour, min, true);
        pic.show();
    }
    //xoa du lieu can xoa
    public void data_move(){
        //xoa het du lieu can xoa
        SharedPreferences sharedPreferences4 = getSharedPreferences(SHARED_PREFERENCES_NAME+String.valueOf(i), MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sharedPreferences4.edit();
        editor4.clear();
        editor4.apply();
        //giam khoi luong list xuong va luu lai
        SharedPreferences sharedPreferences2=getSharedPreferences(SHARED_PREFERENCES_SIZE,MODE_PRIVATE);
        SharedPreferences.Editor editor2=sharedPreferences2.edit();
        size=Integer.parseInt(sharedPreferences2.getString(SIZE,"0"))-1;
        editor2.putString(SIZE,String.valueOf(size));
        editor2.apply();
        //chuyen du liẹu tu (i+1 to i)
        for(int k=i;k<size;k++){
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME+String.valueOf(i), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            SharedPreferences sharedPreferences1=getSharedPreferences(SHARED_PREFERENCES_NAME+String.valueOf(k+1),MODE_PRIVATE);
            SharedPreferences.Editor editor1=sharedPreferences1.edit();

            editor.putString(MON,sharedPreferences1.getString(MON,""));
            editor.putString(PHONG,sharedPreferences1.getString(PHONG,""));
            editor.putString(THOIGIAN,sharedPreferences1.getString(THOIGIAN,""));
            editor.putString(GIANGVIEN,sharedPreferences1.getString(GIANGVIEN,""));
            editor.putString(EMAIL,sharedPreferences1.getString(EMAIL,""));
            editor.putString(SDT,sharedPreferences1.getString(SDT,""));
            editor1.apply();
            editor.apply();
        }
        //xoa duu lieu list cuoi
        SharedPreferences sharedPreferences3=getSharedPreferences(SHARED_PREFERENCES_NAME+String.valueOf(size),MODE_PRIVATE);
        SharedPreferences.Editor editor3=sharedPreferences3.edit();
        editor3.clear();
        editor3.apply();

    }

}
