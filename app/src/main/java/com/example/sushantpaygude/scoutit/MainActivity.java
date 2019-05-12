package com.example.sushantpaygude.scoutit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sushantpaygude.scoutit.Dashboard.Activities.MarketPlaceActivity;
import com.example.sushantpaygude.scoutit.Registration.Models.User;
import com.example.sushantpaygude.scoutit.Utilities.Utils;
import com.example.sushantpaygude.scoutit.Utilities.VolleySingleton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.sushantpaygude.scoutit.Utilities.Utils.RC_SIGN_IN;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private com.google.android.gms.common.SignInButton googleSignIn;
    private GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        googleSignIn = findViewById(R.id.googleSignIn);
        googleSignIn.setOnClickListener(this);
        configureGoogleSignIn();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Build a GoogleSignInClient with the options specified by gso.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            Intent intent = new Intent(getApplicationContext(), MarketPlaceActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.googleSignIn:
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;
        }
    }

    private void configureGoogleSignIn(){

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

         mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            Toast.makeText(this,"Sign in Complete",Toast.LENGTH_SHORT).show();
            postUserDetails(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //updateUI(null);
        }
    }

    private void postUserDetails(GoogleSignInAccount account){

        User user = new User();
        user.setFirstName(account.getGivenName());
        user.setLastName(account.getFamilyName());
        user.setEmail(account.getEmail());
        JsonObjectRequest jsonObjectRequest = null;
        try {
            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Utils.ADD_USER, new JSONObject(new Gson().toJson(user)), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(),"User sign up successful.",Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        Intent intent = new Intent(getApplicationContext(), MarketPlaceActivity.class);
        startActivity(intent);

    }
}
