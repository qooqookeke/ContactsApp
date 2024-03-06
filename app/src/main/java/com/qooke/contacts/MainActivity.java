package com.qooke.contacts;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.qooke.contacts.Model.Contact;
import com.qooke.contacts.adapter.ContactAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;

    // 리사이클러뷰는 다른 것들과 함께
    RecyclerView recyclerView;
    ContactAdapter adapter;
    ArrayList<Contact> contactArrayList = new ArrayList<>();


    // 애드 액티비티, 업데이트 액티비티로부터 데이터를 받는 코드
    public ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if (o.getResultCode() == 100) {
//                String name = o.getData().getStringExtra("name");
//                String phone = o.getData().getStringExtra("phone");
//                Contact contact = new Contact(contact);

                // 객체로 보내서 객체로 받기(캐스팅 사용)
                Contact contact = (Contact) o.getData().getSerializableExtra("contact");

                contactArrayList.add(contact);

                //  화면 갱신
                adapter.notifyDataSetChanged();

            } else if (o.getResultCode() == 200) {
                Contact contact = (Contact) o.getData().getSerializableExtra("name");
                int index = o.getData().getIntExtra("index", 0);

                // 데이터 수정하는 함수 set()
                contactArrayList.set(index, contact);

                adapter.notifyDataSetChanged();
            }
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);

        // 리사이클러뷰는 여기
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 연락처 추가하는 새로운 액티비티를 실행한다.
                Intent intent = new Intent(MainActivity.this, AddActivity.class);

                launcher.launch(intent);

            }
        });

        // 어댑터 만들고 리사이클러뷰에 적용하기
        adapter = new ContactAdapter(MainActivity.this, contactArrayList);
        recyclerView.setAdapter(adapter);

    }
}