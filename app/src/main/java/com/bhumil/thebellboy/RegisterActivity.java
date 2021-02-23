package com.bhumil.thebellboy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView tvLogin;
    TextView tvSignup;
    EditText etMobile, etPassword, etUserName, etEmail;
    private  long mBackPressedTime;
    private  int counter = 0;
    private Intent mIntent;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tvSignup = (TextView)findViewById(R.id.tv_sup);
        tvLogin = (TextView)findViewById(R.id.tv_have_account);
        etMobile = (EditText)findViewById(R.id.et_mobphone);
        etPassword = (EditText)findViewById(R.id.et_pswrdd);
        etUserName = (EditText)findViewById(R.id.et_usrname);
        etEmail = (EditText)findViewById(R.id.et_email);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        if(mAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        tvLogin.setOnClickListener(new View.OnClickListener()
        {
            //Already have an account-Login clicked
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(it);
            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String Email = etEmail.getText().toString().trim();
                String Password = etPassword.getText().toString().trim();
                String UserName = etUserName.getText().toString().trim();
                String PhoneNumber = etMobile.getText().toString().trim();
                if(TextUtils.isEmpty(Email))
                {
                    etEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(Password))
                {
                    etPassword.setError("Password is required");
                    return;
                }
                if(Password.length() < 6)
                {
                    etPassword.setError("Password must be 6 characters atleast");
                    return;
                }
                mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(RegisterActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("userName",UserName);
                            user.put("email",Email);
                            user.put("phone",PhoneNumber);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"onSuccess: user Profile is created for "+ userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this,"Error! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
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
            Toast.makeText(RegisterActivity.this, getString(R.string.st_back_pressed), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RegisterActivity.this, getString(R.string.st_back_pressed), Toast.LENGTH_SHORT).show();
            }
        }
    }
}