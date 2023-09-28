package com.example.projetotcc.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetotcc.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
     
    public static final String TAG ="Activity Main";
    TextView mTextViewForgotPassword;
    Button mButtonLogin;
    ProgressBar mProgressBarLogin;
    EditText mEditTextEmail, mEditTextPassword;
    String mStringEmail,mStringPassword;
    SharedPreferences mSharedPreferences;

    private boolean isRequeredPassword(){
        return TextUtils.isEmpty(mEditTextPassword.getText());
    }
    private boolean isValidEmail(String mStringEmail){
      if(mStringEmail==null || mStringEmail.isEmpty()){
          return false; 
      }
      return Patterns.EMAIL_ADDRESS.matcher(mStringEmail).matches();
      
    }
    private void showProduct(){
        Intent mIntent = new Intent(getApplicationContext() , HomeActivity.class);
        startActivity(mIntent);
        finish();
    }
    private void verifyLogged(){
        if (mSharedPreferences.getString("logged","false").equals("true")){
            showProduct();
        }

    }
    private void postData() {
        mStringEmail = String.valueOf(mEditTextEmail.getText()).toLowerCase(Locale.ROOT);
        mStringPassword = String.valueOf(mEditTextPassword.getText());


        if (!isValidEmail(mStringEmail)) {
            String mTextMessage = getString(R.string.text_email_not_valid);
            Toast.makeText(getApplicationContext(), mTextMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isRequeredPassword()) {
            String mTextMessage = getString(R.string.text_Password_not_valid);
            Toast.makeText(getApplicationContext(), mTextMessage, Toast.LENGTH_SHORT).show();
            return;

        }
        mProgressBarLogin.setVisibility(View.VISIBLE);

        User mUser = new User(mStringEmail, mStringPassword);

        String mResult = UserDao.authenticateUser(mUser, getApplicationContext());

        mProgressBarLogin.setVisibility(View.GONE);
         if(mResult.isEmpty()|| mResult.equals("") || mResult.equals("Exeption")){
             String mTextMessage;
             mTextMessage=getString(R.string.text_email_or_password_incorrect);
             if (mResult.equals("Exeption")){

             }
Toast.makeText(getApplicationContext(), mTextMessage, Toast.LENGTH_SHORT).show();
         return;
         }
SharedPreferences.Editor mEditor =mSharedPreferences.edit();
         mEditor.putString("logged", "True");
         mEditor.putString("Email",mStringEmail);
         mEditor.putString( "Password",mResult);
         mEditor.apply();

         Intent mIntent = new Intent(getApplicationContext(), HomeActivity.class);
    mIntent.putExtra("EXTRA_FULL_NAME", mResult);
    startActivity(mIntent);
    finish();
    }

    public class ClickButtonLogin implements  View.OnClickListener{
        @Override
        public void onClick(View view) {
            postData();
        }
    }

    //-- CASO TENHA UMA TELA DE CADASTRO//

  //  private void showSignUp(){
    // Intent mIntent = new Intent(getApplicationContext(), Activity.class);
    // startActivity(mIntent);
    //finish();
  //  }
  // public class ClickNewUserSignUp implements  View.OnClickListener{

    //  @Override
   //   public void onClick(View view) {
      //    showSignUP();
    //  }
 // }

   // private void showForgotPassword(){
    //    Intent mIntent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
    //    startActivity(mIntent);
   //     finish();
  //  }
  //  public class ClickMyForgotPassword implements View.OnClickListener{
      //  @Override
     //   public void onClick(View view) {
      //    showForgotPassword();
      //  }
  //  }

    public class EditMyTextAction implements TextView.OnEditorActionListener{
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
            if (actionId== EditorInfo.IME_ACTION_DONE){
                postData();
            }
            return false;
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextEmail= findViewById(R.id.textEmail);
        mEditTextPassword= findViewById(R.id.textPassword);
          mEditTextPassword.setOnEditorActionListener(new EditMyTextAction( ));

        mButtonLogin = findViewById(R.id.loginbtn);
        mButtonLogin.setOnClickListener(new ClickButtonLogin());

        mProgressBarLogin = findViewById(R.id.progressBarLogin);
        //mTextViewNewUser.setOnCLickListener(new ClickNewUserSignUp());
        //mTextViewForgotPassword = findViewById(R.id.textView_forgot_password);
        //mTextViewForgotPassword.setOnClickListener(new ClickMyForgotPassword());

        mSharedPreferences = getSharedPreferences("MyAppName", MODE_PRIVATE);
        verifyLogged();
    }
}



    