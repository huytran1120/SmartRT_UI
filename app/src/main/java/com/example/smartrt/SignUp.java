package com.example.smartrt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginbtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Hook
        regName=findViewById(R.id.reg_name);
        regUsername=findViewById(R.id.reg_username);
        regPassword=findViewById(R.id.reg_password);
        regEmail=findViewById(R.id.reg_email);
        regPhoneNo=findViewById(R.id.reg_PhoneNo);
        regBtn=findViewById(R.id.reg_btn);
        regToLoginbtn=findViewById(R.id.reg_login_btn);

        regToLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SignUp.this,Login.class);
                startActivity(intent);
                finish();
            }
        });



    }

    private Boolean validateName(){
        String val =regName.getEditText().getText().toString();

        if(val.isEmpty()) {
            regName.setError("cannot be empty");
            return false;
        }
        else{
            regName.setError(null);
            return true;
        }

    }
    private Boolean validateUserName(){
        String val =regUsername.getEditText().getText().toString();
        String nowhiteSpace = "(?=\\s+$)";  //  \A\w{4,20}\z

        if(val.isEmpty()) {
            regUsername.setError("cannot be empty");
            return false;
        }
        else if(val.length()>=15){
            regUsername.setError("Username too long");
            return false;
        }
        else if (val.matches(nowhiteSpace)){
            regUsername.setError("white space are not allowed");
            return false;
        }
        else{
            regUsername.setError(null);
            return true;
        }

    }
    private Boolean validateEmail(){
        String val =regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()) {
            regEmail.setError("cannot be empty");
            return false;
        }
        else if (!val.matches(emailPattern)){
            regEmail.setError("Invalid email address");
            return false;
        }
        else{
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validatePhoneNo(){
        String val =regPhoneNo.getEditText().getText().toString();


        if(val.isEmpty()) {
            regPhoneNo.setError("cannot be empty");
            return false;
        }

        else{
            regPhoneNo.setError(null);
            return true;
        }

    }
    private Boolean validatePassword(){
        String val =regPassword.getEditText().getText().toString();
        String passwordVal = "^"+".{4,}"+"$";
        if(val.isEmpty()) {
            regPassword.setError("cannot be empty");
            return false;
        }
        else if (!val.matches(passwordVal)){
            regPassword.setError("Invalid password");
            return false;
        }
        else{
            regPassword.setError(null);
            return true;
        }

    }

    //sava data in firebase on button click
    public void registerUser(View view) {



     //   regBtn.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View view) {
                if(!validateName()){
                    validateName();
                    return;   //set bao loi
                }
                if(!validateUserName()){
                    validateUserName();
                    return;   //set bao loi
                }
                if(!validateEmail()){
                    validateEmail();
                    return;   //set bao loi
                }
                if(!validatePhoneNo()){
                    validatePhoneNo();
                    return;   //set bao loi
                }
                if(!validatePassword()){
                    validatePassword();
                    return;   //set bao loi
                }
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users");
                //get all the avalue
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNo = regPhoneNo.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();
                UserHelperClass helperClass =new UserHelperClass(name, username, email, phoneNo, password);
                reference.child(username).setValue(helperClass);
                Intent intent =new Intent(SignUp.this,Login.class);
                startActivity(intent);
  //          }
  //      });

   }




}