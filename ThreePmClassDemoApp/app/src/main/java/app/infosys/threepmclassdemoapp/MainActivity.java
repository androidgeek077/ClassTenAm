package app.infosys.threepmclassdemoapp;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText mEmailET, mPasswordET;
    String mEmailStr, mPasswordStr;

    PreferencesManager preferencesManager;
    Button mLoginBtn, mSignupBtn;

    FirebaseAuth auth;

    @Override
    protected void onStart() {
        super.onStart();
        preferencesManager=new PreferencesManager(this);
        if (preferencesManager.userIsLogged()){
            startActivity(new Intent(getBaseContext(), DashBoardActivity.class));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferencesManager=new PreferencesManager(this);
        auth = FirebaseAuth.getInstance();
        mEmailET = findViewById(R.id.mEmailET);
        mPasswordET = findViewById(R.id.mPasswordET);
        mLoginBtn = findViewById(R.id.mLoginBtn);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStrings();
                auth.signInWithEmailAndPassword(mEmailStr, mPasswordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid=auth.getCurrentUser().getUid();
                            preferencesManager.SetLogin(uid, true);
                            startActivity(new Intent(getBaseContext(), DashBoardActivity.class));
                            finish();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        mSignupBtn = findViewById(R.id.mSignupBtn);
        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SignupActivity.class));
            }
        });
    }

    void getStrings() {
        mEmailStr = mEmailET.getText().toString();
        mPasswordStr = mPasswordET.getText().toString();
    }
}
