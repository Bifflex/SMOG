package uqac.dim.smogmain;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText confirm_password;
    private Button create_account;
    private ImageButton return_back;
    private ProgressBar progress;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        email = (EditText) findViewById(R.id.register_email);
        password = (EditText) findViewById(R.id.register_pass);
        confirm_password = (EditText) findViewById(R.id.editText4);
        create_account = (Button) findViewById(R.id.button);
        return_back = (ImageButton) findViewById(R.id.imageButton);
        progress = (ProgressBar) findViewById(R.id.progress_reg);
        mAuth = FirebaseAuth.getInstance();

        progress.setVisibility(View.INVISIBLE);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_str = email.getText().toString();
                String password_str = password.getText().toString();
                String password1_str = confirm_password.getText().toString();

                if (!TextUtils.isEmpty(email_str) && !TextUtils.isEmpty(password_str) && !TextUtils.isEmpty(password1_str)){

                    progress.setVisibility(View.VISIBLE);

                    if (password_str.equals(password1_str)){

                        mAuth.createUserWithEmailAndPassword(email_str, password_str).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    sendtomain();
                                } else{
                                    String errorMess = task.getException().getMessage();
                                    Toast.makeText(SignUpActivity.this, "Error : " + errorMess, Toast.LENGTH_SHORT).show();
                                }

                                progress.setVisibility(View.INVISIBLE);
                            }
                        });

                    } else{
                        Toast.makeText(SignUpActivity.this, "Both fields password and confirm_password doesn't match.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        return_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            sendtomain();
        }
    }

    private void sendtomain() {

        Intent maintIntent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(maintIntent);
        finish();
    }
}
