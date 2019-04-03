package uqac.dim.smogmain;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button create_acc;
    private ProgressBar progress_co;
    private FirebaseAuth mAuth;
    private Button connect;
    private SignInButton signIn;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        create_acc = (Button) findViewById(R.id.button2);
        connect = (Button) findViewById(R.id.button);
        progress_co = (ProgressBar) findViewById(R.id.progressBar);


        progress_co.setVisibility(View.INVISIBLE);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_str = email.getText().toString();
                String pass_str = password.getText().toString();

                if (!TextUtils.isEmpty(email_str) && !TextUtils.isEmpty(pass_str)){

                    progress_co.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(email_str, pass_str).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                sendtomain();

                            }
                            else {

                                String error_mess = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "Error :" + error_mess, Toast.LENGTH_SHORT).show();

                            }

                            progress_co.setVisibility(View.INVISIBLE);

                        }
                    });


                }

            }




        });

        create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signupIntent);
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

        Intent maintIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(maintIntent);
        finish();
    }


}
