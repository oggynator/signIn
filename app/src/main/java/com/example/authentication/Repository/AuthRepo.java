package com.example.authentication.Repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.authentication.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthRepo {
    FirebaseAuth firebaseAuth;
    private MainActivity mainActivity;

    public AuthRepo(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        firebaseAuth = FirebaseAuth.getInstance();
        checkCredentials();
    }

    public AuthRepo() {
        firebaseAuth = FirebaseAuth.getInstance();
    }


    // Checks if there is a valid login token,if there is it calls the onloginsucces, if not the user is prompted for mail and password
    private void checkCredentials() {
        firebaseAuth.addIdTokenListener(new FirebaseAuth.IdTokenListener() {
            @Override
            public void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    mainActivity.onLoginFail();
                } else {
                    Log.d("success", firebaseAuth.getCurrentUser().getEmail());
                    mainActivity.onLoginSuccess(firebaseAuth.getCurrentUser());
                }
            }
        });
    }

    //Takes the email and password from the mainactivty , and sign the user in if it is correct
    public void signUserIn(String email, String password, final MainActivity mainActivity) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(mainActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mainActivity.onLoginSuccess(task.getResult().getUser());
                        } else {
                            System.out.println("incorrect mail or password");
                        }
                    }
                });
    }


}
