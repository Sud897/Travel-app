package com.example.travelmania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.travelmania.common.AppConstant;
import com.example.travelmania.common.LocalSession;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout nameLayout,emailLayout,mobileLayout,pwdLayout,confirmPwdLayout;
    TextInputEditText nameInput,emailInput,mobileInput,pwdInput,confirmPwdInput;
    Button btnSignUp;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nameLayout=findViewById(R.id.name_layout);
        nameInput=findViewById(R.id.name_input);
        emailLayout=findViewById(R.id.email_layout);
        emailInput=findViewById(R.id.email_input);
        mobileLayout=findViewById(R.id.number_layout);
        mobileInput=findViewById(R.id.number_input);
        pwdLayout=findViewById(R.id.password_layout);
        pwdInput=findViewById(R.id.password_input);
        confirmPwdLayout=findViewById(R.id.confirm_pwd_layout);
        confirmPwdInput=findViewById(R.id.confirm_pwd_input);
        btnSignUp=findViewById(R.id.btn_signUp);

        /**FirebaseApp.initializeApp(SignUpActivity.this);*/
        auth=FirebaseAuth.getInstance();


        btnSignUp.setOnClickListener(v -> {
            nameLayout.setError("");
            emailLayout.setError("");
            mobileLayout.setError("");
            pwdLayout.setError("");
            confirmPwdLayout.setError("");
            String name=String.valueOf(nameInput.getText()).trim();
            String email=String.valueOf(emailInput.getText()).trim();
            String number=String.valueOf(mobileInput.getText()).trim();
            String pwd=String.valueOf(pwdInput.getText()).trim();
            String confirm_pwd=String.valueOf(confirmPwdInput.getText()).trim();

            if(validateFields(name,email,number,pwd,confirm_pwd))
            {
                auth.createUserWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(task -> {
                           if(task.isSuccessful()){
                               auth.getCurrentUser().sendEmailVerification();
                               String uid=auth.getUid();
                               createProfile(uid,email,name,number);
                           }else {
                               callSnackBar(task.getException().getMessage());
                           }
                        })
                        .addOnFailureListener(e -> callToast(e.getMessage()));
            }
        });

    }




    private void createProfile(String uid, String email, String name, String number) {
        Map<String,Object> userMap=new HashMap<>();          //Collection framework
        userMap.put(AppConstant.USER_ID,uid);
        userMap.put(AppConstant.USER_PIC,null);
        userMap.put(AppConstant.USER_NAME,name);
        userMap.put(AppConstant.USER_DOB,null);
        userMap.put(AppConstant.USER_GENDER,null);
        userMap.put(AppConstant.USER_EMAIL,email);
        userMap.put(AppConstant.USER_NUMBER,number);
        userMap.put(AppConstant.USER_PASSPORT,null);
        userMap.put(AppConstant.USER_DISTRICT,null);
        userMap.put(AppConstant.USER_PIN,null);
        userMap.put(AppConstant.USER_STATE,null);

        FirebaseFirestore fireStore=FirebaseFirestore.getInstance();
        fireStore.collection("userAccounts")
                .document(uid)
                .set(userMap)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        callToast("Sign Up Successful");
                        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                        SignUpActivity.this.finish();
                    }
                    else
                        callSnackBar("Couldn't create your profile");
                });
    }

    private boolean validateFields(String name, String email, String number, String pwd, String confirm_pwd) {
        if(name.isEmpty()){
            nameLayout.setError("please provide your Name");
        }
        else if(!name.matches("^[a-zA-Z.\\s]+$")){
            nameLayout.setError("Please provide your Valid Name");
        }
        else if(email.isEmpty()){
            emailLayout.setError("Please Provide your Email Id");
        }
        else if(!ValidateEmail(email)){
            emailLayout.setError("Please provide valid Email Id");
        }
        else if(number.isEmpty()){
            mobileLayout.setError("Please provide your Number");
        }
        else if(/*number.length()<10|| number.length() > 13 ||*/ !number.matches("^[+]?[0-9]{10,13}$")){
            mobileLayout.setError("Please provide valid Mobile number");
        }
        else if(!isValidPassword(pwd)){
            pwdLayout.setError("Password must be at least of 8 character and contains at least one Character,one Number and one Special Character");
        }
        else if(!matchPassword(pwd,confirm_pwd)){
            confirmPwdLayout.setError("Password is not Matched");
        }
        else
            return true;
        return false;

    }

    private boolean isValidPassword(String pwd) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_!()])(?=\\S+$).{8,24}$";
        /*^ represents starting character of the string.
        (?=.*[0-9]) represents a digit must occur at least once.
        (?=.*[a-z]) represents a lower case alphabet must occur at least once.
        (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
        (?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
        (?=\\S+$) white spaces don’t allowed in the entire string.
        .{4, } represents at least 4 characters
        $ represents the end of the string.*/
        return Pattern.compile(PASSWORD_PATTERN).matcher(pwd).matches();
    }

    private boolean matchPassword(String pwd, String confirm_pwd) {
        return Pattern.compile(pwd).matcher(confirm_pwd).matches();
    }

    private boolean ValidateEmail(String email) {
        final String emailPattern = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"+
                "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
                "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."+
                "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"+
                "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" +
                "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
        return Pattern.compile(emailPattern).matcher(email).matches();
    }
    private void callSnackBar(String msg) {
        Snackbar.make(SignUpActivity.this,findViewById(R.id.signUp_layout),msg,Snackbar.LENGTH_LONG).show();
    }
    private void callToast(String selectedValue) {
        Toast.makeText(SignUpActivity.this,selectedValue,Toast.LENGTH_SHORT).show();
    }
}