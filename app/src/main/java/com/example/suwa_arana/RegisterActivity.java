package com.example.patientreg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Nonnull;

public class RegisterActivity extends AppCompatActivity {

    private EditText mName, mGender, mAge, mAddress, mEmail, mPhone;
    private Button mSaveBtn, mShowBtn;
    private FirebaseFirestore db;
    private String uId, uName, uGender, uAge, uAddress, uEmail, uPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName=findViewById(R.id.et_name);
        mGender=findViewById(R.id.et_gender);
        mAge=findViewById(R.id.et_age);
        mAddress=findViewById(R.id.et_address);
        mEmail=findViewById(R.id.et_email);
        mPhone=findViewById(R.id.et_phone);

        mSaveBtn=(Button) findViewById(R.id.btn_save);
        mShowBtn=(Button) findViewById(R.id.btn_show);

        db = FirebaseFirestore.getInstance();

        Bundle bundle =  getIntent().getExtras();
        if (bundle !=null){
            mSaveBtn.setText("Update");
            uName = bundle.getString("uName");
            uId = bundle.getString("uId");
            uGender = bundle.getString("uGender");
            uAge = bundle.getString("uAge");
            uAddress = bundle.getString("uAddress");
            uEmail = bundle.getString("uEmail");
            uPhone = bundle.getString("uPhone");
            mName.setText(uName);
            mGender.setText(uGender);
            mAge.setText(uAge);
            mAddress.setText(uAddress);
            mEmail.setText(uEmail);
            mPhone.setText(uPhone);
        }else{
            mSaveBtn.setText("Save");
        }

        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this ,ShowActivity.class));
            }
        });


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mName.getText().toString();
                String gender = mGender.getText().toString();
                String age = mAge.getText().toString();
                String address = mAddress.getText().toString();
                String email = mEmail.getText().toString();
                String phone = mPhone.getText().toString();


                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null) {
                    String id = uId;
                    updateToFireStore(id, name, gender, age, address, email, phone);
                } else {


                    String id = UUID.randomUUID().toString();

                    saveToFireStore(id, name, gender, age, address, email, phone);
                }
            }
        });
    }

    private void updateToFireStore(String id, String name, String gender, String age, String address, String email, String phone){

        db.collection("Documents").document(id).update("name" , name , "gender" , gender ,
                "age" , age, "address" , address ,"email" ,email ,"phone" ,phone)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Data Updated !!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Error :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToFireStore(String id, String name, String gender, String age, String address, String email, String phone){


        HashMap<String , Object> map = new HashMap<>();
        map.put("name" ,name);
        map.put("gender" ,gender);
        map.put("age" ,age);
        map.put("address" ,address);
        map.put("email" ,email);
        map.put("phone" ,phone);

        db.collection("Documents").document(id).set(map)
                .addOnCompleteListener(new OnCompleteListener<Void>(){
                    @Override
                    public void onComplete(@Nonnull Task<Void> task){
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Data Saved !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Failed !!", Toast.LENGTH_SHORT).show();
            }
        });


    }

}