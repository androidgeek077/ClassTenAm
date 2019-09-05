package app.infosys.threepmclassdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DashBoardActivity extends AppCompatActivity {
    Button LogoutBtn;
    FirebaseAuth auth;
    PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        auth=FirebaseAuth.getInstance();
        preferencesManager=new PreferencesManager(this);
        Toast.makeText(this, preferencesManager.getUserId(), Toast.LENGTH_SHORT).show();
        LogoutBtn=findViewById(R.id.LogoutBtn);
        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                auth.signOut();
                preferencesManager.logoutUser(DashBoardActivity.this);
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });
    }
}
