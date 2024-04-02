package com.example.quocduy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.mindrot.jbcrypt.BCrypt;

public class RegisterActivity extends AppCompatActivity {
    private EditText fullnameedit, emailedit, phoneedit, useredit, passedit;
    private Button btngregis;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        fullnameedit = findViewById(R.id.txtFullname);
        emailedit = findViewById(R.id.txtEmail);
        phoneedit = findViewById(R.id.txtPhone);
        useredit = findViewById(R.id.txtUser);
        passedit = findViewById(R.id.txtPassWord);
        btngregis = findViewById(R.id.btnRegister);


        btngregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String fullname, email, phone, username, pass;
        fullname = fullnameedit.getText().toString();
        email = emailedit.getText().toString();
        phone = phoneedit.getText().toString();
        username = useredit.getText().toString();
        pass = passedit.getText().toString();

        // Kiểm tra xem các trường thông tin đã được nhập đầy đủ chưa
        if (TextUtils.isEmpty(fullname) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(username) || TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mã hóa mật khẩu trước khi lưu vào Firebase
        String hashedPass = hashPassword(pass);

        // Tạo một đối tượng User mới
        User user = new User(fullname, email, phone, username, hashedPass, hashedPass);

        // Lưu thông tin người dùng vào Realtime Database
        mDatabase.child("users").child(username).setValue(user);

        Toast.makeText(this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // Phương thức để mã hóa mật khẩu sử dụng jBCrypt
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
