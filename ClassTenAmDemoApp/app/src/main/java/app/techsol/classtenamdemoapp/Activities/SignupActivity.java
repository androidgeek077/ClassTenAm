package app.techsol.classtenamdemoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import app.techsol.classtenamdemoapp.R;

public class SignupActivity extends AppCompatActivity {

    EditText mEmailET, mPasswordET;
    Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mEmailET=findViewById(R.id.emailET);
        mPasswordET=findViewById(R.id.passwordET);
        mLoginBtn=findViewById(R.id.loginBtn);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest myStringRequest=new StringRequest(1, "https://malikwaqas077.000webhostapp.com/android/signup.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SignupActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignupActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params=new HashMap<>();
                        params.put("useremail", mEmailET.getText().toString());
                        params.put("password", mPasswordET.getText().toString());
                        return  params;
                    }
                };

                RequestQueue requestQueue= Volley.newRequestQueue(SignupActivity.this);
                requestQueue.add(myStringRequest);
            }
        });
    }
}
