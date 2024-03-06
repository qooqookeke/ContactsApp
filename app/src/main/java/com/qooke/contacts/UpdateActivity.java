package com.qooke.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qooke.contacts.Model.Contact;

public class UpdateActivity extends AppCompatActivity {

    EditText editName;
    EditText editPhone;
    Button btnSave;

    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        btnSave = findViewById(R.id.btnSave);

        // 메인 액티비티에서 데이터 받아오기
        Contact contact = (Contact) getIntent().getSerializableExtra("contact");
        index = getIntent().getIntExtra("index", 0);

        editName.setText(contact.name);
        editPhone.setText(contact.phone);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();

                if(name.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(UpdateActivity.this, "필수항목을 모두 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 메인액티비티로 데이터를 돌려줘야한다.
                Intent intent = new Intent();
                Contact contact = new Contact(name, phone);
                intent.putExtra("contact", contact);
                intent.putExtra("index", index);
                setResult(200, intent);

                Toast.makeText(UpdateActivity.this, "수정완료 되었습니다.", Toast.LENGTH_SHORT).show();

                finish();

            }
        });
    }
}