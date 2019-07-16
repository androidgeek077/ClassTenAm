package app.techsol.classtenamdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewStduentsActivity extends AppCompatActivity {
    ListView mStudentListVW;
    String [] StudentArr={"Hina", "Hira", "Shamsa", "Kiran", "Komal", "Shanza", "Hafsa","Hina", "Hira", "Shamsa", "Kiran", "Komal", "Shanza", "Hafsa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stduents);

        ArrayAdapter  myAdapter=new ArrayAdapter(this, R.layout.student_list_item_layout, R.id.list_item, StudentArr);
        mStudentListVW=findViewById(R.id.StudentListView);
        mStudentListVW.setAdapter(myAdapter);

    }
}
