package com.bhumil.thebellboy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bhumil.thebellboy.UtilityClasses.Utility;
import com.bhumil.thebellboy.UtilityClasses.Validations;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView tvSignUp, tvLogin;
    EditText tvEmail,tvPassword;
    private  long mBackPressedTime;
    private  int counter = 0;
    private Intent mIntent;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvSignUp = (TextView) findViewById(R.id.tv_sign_up);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        tvEmail = (EditText) findViewById(R.id.tv_login_email);
        tvPassword = (EditText) findViewById(R.id.tv_login_pass);
        mAuth = FirebaseAuth.getInstance();

        tvSignUp.setOnClickListener(new View.OnClickListener()
        {
            //Sign Up clicked
            @Override
            public void onClick(View v)
            {
                Intent signupIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(signupIntent);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                String Email = tvEmail.getText().toString().trim();
                String Password = tvPassword.getText().toString().trim();
                if(TextUtils.isEmpty(Email))
                {
                    tvEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(Password))
                {
                    tvPassword.setError("Password is required");
                    return;
                }
                if(Password.length() < 6)
                {
                    tvPassword.setError("Password must be 6 characters atleast");
                    return;
                }
                mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this,"Log In Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"Error! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void onBackPressed()
    {
        mBackPressedTime = System.currentTimeMillis();
        if(counter == 0)
        {
            Toast.makeText(LoginActivity.this, getString(R.string.st_back_pressed), Toast.LENGTH_SHORT).show();
            counter = 1;
        }
        else
        {
            if (mBackPressedTime + 5000 > System.currentTimeMillis())
            {
                mIntent = new Intent(Intent.ACTION_MAIN);
                mIntent.addCategory(Intent.CATEGORY_HOME);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mIntent);
                finish();
                super.onBackPressed();

            } else
            {
                Toast.makeText(LoginActivity.this, getString(R.string.st_back_pressed), Toast.LENGTH_SHORT).show();
            }
        }
    }
}