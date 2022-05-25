package com.example.travelmania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.travelmania.common.LocalSession;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout emailLayout,pwdLayout;
    TextInputEditText emailInput,pwdInput;
    Button btn;

    LocalSession session;

    private FirebaseAuth auth;
    //private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailLayout=findViewById(R.id.email_layout);
        emailInput=findViewById(R.id.email_input);
        pwdLayout=findViewById(R.id.password_layout);
        pwdInput=findViewById(R.id.password_input);
        btn=findViewById(R.id.btn_login);

        session=new LocalSession(LoginActivity.this);

        //Initializing Firebase
        FirebaseApp.initializeApp(this);
        auth= FirebaseAuth.getInstance();;
        //currentUser = auth.getCurrentUser();

        btn.setOnClickListener(v -> {
            emailLayout.setError("");
            pwdLayout.setError("");
            String email=String.valueOf(emailInput.getText()).trim();
            String pass=String.valueOf(pwdInput.getText()).trim();
            if(validateFields(email,pass))
            {
               auth.signInWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(task -> {
                            if(task.isSuccessful()){
                                FirebaseUser fUser=auth.getCurrentUser();
                                if (fUser.isEmailVerified()) {
                                    session.setLoginStatus();
                                    callToast("Login Successful");
                                    startActivity(new Intent(LoginActivity.this, DrawerHomeActivity.class));
                                }else {
                                    callToast("Error !\nKindly Verify your Email Id");
                                }
                            }else{
                                callToast("Login Failed\n"+task.getException().getMessage());
                            }

                        });
            }
        });
    }

    private void callToast(String selectedValue) {
        Toast.makeText(LoginActivity.this,selectedValue,Toast.LENGTH_SHORT).show();
    }

    private boolean validateFields(String email, String pass) {
        if(email.isEmpty()){
            emailLayout.setError("Please Provide your Email Id");
        }
        /*else if(!email.matches(emailPattern)){
            emailLayout.setError("Please provide valid Email Id");
        }*/
        else if(!ValidateEmail(email)){
            emailLayout.setError("Please provide valid Email Id");
        }
        else if(pass.isEmpty()){
            pwdLayout.setError("Please provide Correct Password");
        }
        else if(!ValidatePass(pass)){
            pwdLayout.setError("Password must be at least of 8 character and contains at least one Character,one Number and one Special Character");        }
        else
            return true;
        return false;
    }

    private boolean ValidatePass(String pass) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_!()])(?=\\S+$).{8,24}$";
        /*^ represents starting character of the string.
        (?=.*[0-9]) represents a digit must occur at least once.
        (?=.*[a-z]) represents a lower case alphabet must occur at least once.
        (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
        (?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
        (?=\\S+$) white spaces don’t allowed in the entire string.
        .{4, } represents at least 4 characters
        $ represents the end of the string.*/
        return Pattern.compile(PASSWORD_PATTERN).matcher(pass).matches();

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

    public void navToSignUp(View view) {
        //Code to route to the signUp activity
        Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
    }

    public void btnGoogleLogin(View view) {
        callToast("Work in Progress");
    }

    public void btnFacebookLogin(View view) {
        callToast("Work in Progress");
    }

    public void btnMobileLogin(View view) {
        callToast("Work in Progress");
    }

    public void btnForgetPwd(View view) {
        callToast("Work in Progress");
    }
}