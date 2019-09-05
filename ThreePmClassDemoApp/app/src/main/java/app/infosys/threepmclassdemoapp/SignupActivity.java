package app.infosys.threepmclassdemoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import app.infosys.threepmclassdemoapp.Models.UserModel;

public class SignupActivity extends AppCompatActivity {

    EditText mEmailET, mPasswordET, mPhoneNoET, mAddressET, mNameET;
    String mEmailStr, mPasswordStr, mPhoneNoStr, mAddressStr, mNameStr;
    Button mLoginBtn, mSignupBtn;
    FirebaseAuth auth;
    DatabaseReference mStudentRef;
    StorageReference mProfilePicStorageRef;
    Button mSelectImgBtn;
    ImageView mSelectedImgVw;
    private int RC_NAV_PHOTO_PICKER = 345;
    private Uri selectedUri;
    private String ImgUrl, Uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        auth = FirebaseAuth.getInstance();
        mProfilePicStorageRef = FirebaseStorage.getInstance().getReference("UserPictures");
        mStudentRef = FirebaseDatabase.getInstance().getReference("StudentDetails");
        mEmailET = findViewById(R.id.mEmailET);
        mSelectedImgVw = findViewById(R.id.mSelectedImgVw);
        mSelectImgBtn = findViewById(R.id.mSelectImgBtn);
        mSelectImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_NAV_PHOTO_PICKER);

            }
        });
        mPasswordET = findViewById(R.id.mPasswordET);
        mPhoneNoET = findViewById(R.id.mPhoneNoET);
        mAddressET = findViewById(R.id.mAddressET);
        mNameET = findViewById(R.id.mNameET);

        mLoginBtn = findViewById(R.id.mSigninBtn);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });
        mSignupBtn = findViewById(R.id.mSignupBtn);
        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStrings();
                auth.createUserWithEmailAndPassword(mEmailStr, mPasswordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Uid = auth.getCurrentUser().getUid();


                            mProfilePicStorageRef.putFile(selectedUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    mProfilePicStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String uploadedImgURL = uri.toString();
                                            UserModel model = new UserModel(mNameStr, mAddressStr, mPhoneNoStr, Uid, uploadedImgURL);
                                            mStudentRef.child(Uid).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(SignupActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    void getStrings() {
        mEmailStr = mEmailET.getText().toString();
        mPasswordStr = mPasswordET.getText().toString();
        mPhoneNoStr = mPhoneNoET.getText().toString();
        mAddressStr = mAddressET.getText().toString();
        mNameStr = mNameET.getText().toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            selectedUri = data.getData();
            if (selectedUri != null) {
                mSelectedImgVw.setImageURI(selectedUri);
                mSelectedImgVw.setVisibility(View.VISIBLE);
            }
        }
    }
}
