package app.techsol.classtenamdemoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;

import app.techsol.classtenamdemoapp.R;

public class MainActivity extends AppCompatActivity {
    ListView mStduentLIstView;
    String nameStr="Waqas", emailStr="example@gmail.com";

    CardView mmStudentCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mmStudentCV=findViewById(R.id.mStudentCV);
        mmStudentCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ViewStduentsActivity.class);
                intent.putExtra("name",nameStr);
                intent.putExtra("email",emailStr);
                startActivity(intent);

                Snackbar.make(view, "I'm snackbar", Snackbar.LENGTH_LONG).setAction("ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    startActivity(new Intent(getBaseContext(), ViewStduentsActivity.class));
                    }
                }).show();
            }
        });
    }
}
