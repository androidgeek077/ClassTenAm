package app.infosys.threepmclassdemoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.infosys.threepmclassdemoapp.Models.UserModel;

public class ViewUsersActivity extends AppCompatActivity {
    RecyclerView mUsersRecyclerView;
    DatabaseReference mStudentRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);
        mStudentRef = FirebaseDatabase.getInstance().getReference("StudentDetails");

        mUsersRecyclerView=findViewById(R.id.UsersRecyclerView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        mUsersRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<UserModel> options = new FirebaseRecyclerOptions.Builder<UserModel>()
                .setQuery(mStudentRef, UserModel.class)
                .build();

        final FirebaseRecyclerAdapter<UserModel, StudentViewHolder> adapter = new FirebaseRecyclerAdapter<UserModel, StudentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final StudentViewHolder holder, final int position, @NonNull final UserModel model) {


                DisplayMetrics displaymetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                //if you need three fix imageview in width

                holder.UserName.setText(model.getName());
                holder.UserName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog  dialog=new Dialog(ViewUsersActivity.this);
                        dialog.setContentView(R.layout.dummy_user_item_layout);
                        dialog.show();
                    }
                });
                holder.PhoneNo.setText(model.getPhonono());
                Glide.with(getApplicationContext()).load(model.getImgurl()).into(holder.mUserImgVw);



                holder.mDelCustomerBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference key = getRef(position);
                        key.removeValue();
                    }
                });


            }

            @Override
            public StudentViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dummy_user_item_layout, viewGroup, false);
                StudentViewHolder StudentViewHolder = new StudentViewHolder(view);
                return StudentViewHolder;
            }
        };

        mUsersRecyclerView.setAdapter(adapter);
        adapter.startListening();
//        loading.stop();

    }


    public static class StudentViewHolder extends RecyclerView.ViewHolder {


        TextView UserName, PhoneNo, mQueryTV;
        ImageView mUserImgVw;
        Button mDelCustomerBtn;


        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            UserName = itemView.findViewById(R.id.mNameTV);
            PhoneNo = itemView.findViewById(R.id.mPhoneNoTV);
            mUserImgVw = itemView.findViewById(R.id.mUserImgVw);


        }
    }

}
