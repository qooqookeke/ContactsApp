package com.qooke.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qooke.contacts.Model.Contact;

public class AddActivity extends AppCompatActivity {

    EditText editName;
    EditText editphone;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editName = findViewById(R.id.editName);
        editphone = findViewById(R.id.editPhone);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString().trim();
                String phone = editphone.getText().toString().trim();

                if(name.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(AddActivity.this, "필수 항목입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 메인액티비티로 데이터를 돌려준다.
                Intent intent = new Intent();

                // 데이터 묶음 처리해서 한번에 보내기
                Contact contact = new Contact(name, phone);
                intent.putExtra("contact", contact);

                // 기존 방식, 각각 intent 변수에 넣어서 보내는 방법
//                intent.putExtra("name", name);
//                intent.putExtra("phone", phone);

                setResult(100, intent);

                Toast.makeText(AddActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();

                finish();

            }
        });
    }
}