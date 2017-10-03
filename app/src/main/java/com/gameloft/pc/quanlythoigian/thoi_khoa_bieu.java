package com.gameloft.pc.quanlythoigian;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.gameloft.pc.quanlythoigian.classPackage.CustomAdapter;
import com.gameloft.pc.quanlythoigian.classPackage.monHoc;

import java.util.ArrayList;

public class thoi_khoa_bieu extends AppCompatActivity {

    private final String SHARED_PREFERENCES_NAME="data_detailscr";
    private final String SHARED_PREFERENCES_SIZE="data_size";
    private final String MON="mon";
    private final String PHONG="phong";
    private final String THOIGIAN="thoigian";
    private final String SIZE="size";
    static private final ArrayList<monHoc> arrayList = new ArrayList<>();
    int i;
    int size1;
    Button btnLeft;
    Button btnRight;
    ImageButton btnAdd;
    ListView lvMonHoc;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoi_khoa_bieu);

        lvMonHoc = (ListView) findViewById(R.id.lvMonHoc);
        btnAdd = (ImageButton) findViewById(R.id.btnAdd);


        arrayList.clear();
        luu();
        for(int j=0;j<size1;j++) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(j), MODE_PRIVATE);
            arrayList.add(new monHoc(sharedPreferences.getString(MON, "mon"), sharedPreferences.getString(THOIGIAN, "thoi gian"),sharedPreferences.getString(PHONG, "phong")));
        }
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.dong_listview, arrayList);
        lvMonHoc.setAdapter(customAdapter);

        //them du lieu
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thoi_khoa_bieu.this,editscr.class);
                Bundle bundle = new Bundle();
                SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_SIZE,MODE_PRIVATE);
                size1=Integer.parseInt(sharedPreferences.getString(SIZE,"0"));
                int j=size1;
                bundle.putInt("pos",j);
                intent.putExtra("MyPackage",bundle);
                startActivityForResult(intent,2); //cho` ket qua tra ve tu editscr nhe !!!
            }// bên editscr muốn biết là do bấm nút Add nên nhảy qua thì phải kiểm tra requestCode là 2 nhé
        });

        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(thoi_khoa_bieu.this,detailscr.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pos",position); //gui sang vi tri cua mon dc chon --> de hien thi detail
                intent.putExtra("MyPackage",bundle);//chứ ko có vị trí thì biết hiển thị detail của môn nào ?
                startActivity(intent); // ko cần trả về dữ liệu, chỉ vào xem detail thôi.
            }
        });

        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(thoi_khoa_bieu.this,editscr.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pos",position);
                bundle.putInt("size",arrayList.size());
                intent.putExtra("MyPackage",bundle);
                startActivityForResult(intent,3);
                //tương tự, chỗ này chuyển qua editscr vs mã requestCode = 3 (để phân biệt vs nút bấm Add)
                return false;
            }
        });
    }

    private void luu() {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_SIZE,MODE_PRIVATE);
        size1=Integer.parseInt(sharedPreferences.getString(SIZE,"0"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 2){//kq trả về từ việc thêm thông tin
            //lay thong tin tu editscr nhe (lay Monhoc, thoigian, phong de hien thi len listview)
        }
        if(resultCode == 3){//kq trả về từ việc sửa thông tin
            //lấy thông tin từ editscr để hiển thị lên listview
        }
    }
}

//Tuy nhiên mình vẫn ko hiểu làm thế nào để editscr phân biệt được lúc mở nó lên là do nút Add
//hay do bấm vào item trên ListView
//AE suy nghĩ thử
