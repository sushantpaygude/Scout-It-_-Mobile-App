package com.example.sushantpaygude.scoutit.Dashboard.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sushantpaygude.scoutit.Dashboard.Fragments.CoursesFragment;
import com.example.sushantpaygude.scoutit.Dashboard.Fragments.ProductsFragment;
import com.example.sushantpaygude.scoutit.MainActivity;
import com.example.sushantpaygude.scoutit.R;
import com.example.sushantpaygude.scoutit.Utilities.Utils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

public class MarketPlaceActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle t;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton floatingActionButton;
    private GoogleSignInClient mGoogleSignInClient;
    private android.support.v7.widget.Toolbar toolbar;
    private ImageView imageFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        drawerLayout = findViewById(R.id.navigationDrawer);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        floatingActionButton = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        imageFilter = toolbar.findViewById(R.id.imageFilter);
        floatingActionButton.setOnClickListener(this);
        imageFilter.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        t = new ActionBarDrawerToggle(this, drawerLayout,R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(t);
        t.syncState();
        navigationView = findViewById(R.id.nv);
        updateNavigationHeader();
        configureGoogleSignIn();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.account:
                        Toast.makeText(getApplicationContext(), "My Account",Toast.LENGTH_SHORT).show();break;
                    case R.id.settings:
                        Toast.makeText(getApplicationContext(), "Settings",Toast.LENGTH_SHORT).show();break;
                    case R.id.mycart:
                        Toast.makeText(getApplicationContext(), "My Cart", Toast.LENGTH_SHORT).show();break;
                    case R.id.logout:
                        mGoogleSignInClient.signOut().addOnCompleteListener(MarketPlaceActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                        });
                        break;
                    default:
                        return true;
                }

                return true;

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (id){
                    case R.id.products:
                        if(fragmentManager.findFragmentByTag("COURSE_FRAGMENT") != null){
                            fragmentManager.beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("COURSE_FRAGMENT")).commit();
                        }

                        if(fragmentManager.findFragmentByTag("PRODUCT_FRAGMENT") != null){
                            fragmentManager.beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("PRODUCT_FRAGMENT")).commit();
                        }
                        Toast.makeText(getApplicationContext(), "My Products",Toast.LENGTH_SHORT).show();
                        ProductsFragment productsFragment = new ProductsFragment();
                        fragmentTransaction.add(R.id.mainLayout,productsFragment,"PRODUCT_FRAGMENT");
                        fragmentTransaction.commit();
                        break;
                    case R.id.courses:
                        if(fragmentManager.findFragmentByTag("PRODUCT_FRAGMENT") != null){
                            fragmentManager.beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("PRODUCT_FRAGMENT")).commit();
                        }
                        if(fragmentManager.findFragmentByTag("COURSE_FRAGMENT") != null){
                            fragmentManager.beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("COURSE_FRAGMENT")).commit();
                        }

                        Toast.makeText(getApplicationContext(), "My Courses",Toast.LENGTH_SHORT).show();
                        CoursesFragment coursesFragment = new CoursesFragment();
                        fragmentTransaction.add(R.id.mainLayout,coursesFragment,"COURSE_FRAGMENT");
                        fragmentTransaction.commit();
                        break;
                        default:
                            return true;

                }
                return true;
            }
        });

    }

    private void updateNavigationHeader(){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        View headerView = navigationView.getHeaderView(0);
        if(account != null){
            TextView textUsername = headerView.findViewById(R.id.textUsername);
            TextView textEmail = headerView.findViewById(R.id.textEmail);
            ImageView imageView = headerView.findViewById(R.id.imageUser);
            Picasso.get().load(account.getPhotoUrl()).into(imageView);
            textUsername.setText(account.getGivenName() + " "+account.getFamilyName());
            textEmail.setText(account.getEmail());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                Fragment myFragment = getSupportFragmentManager().findFragmentByTag("PRODUCT_FRAGMENT");
                //Intent for adding a new product.
                if (myFragment != null && myFragment.isVisible()) {
                    Intent intent = new Intent(this,AddProductActivity.class);
                    startActivity(intent);
                }

                //Intent for adding a new course
                else{
                    myFragment = getSupportFragmentManager().findFragmentByTag("COURSE_FRAGMENT");
                    if (myFragment != null && myFragment.isVisible()) {
                        Intent intent = new Intent(this,AddCourseActivity.class);
                        startActivity(intent);
                    }
                }
                break;

            case R.id.imageFilter:
               if(bottomNavigationView.getSelectedItemId() == R.id.products){
                   myFragment = getSupportFragmentManager().findFragmentByTag("PRODUCT_FRAGMENT");
                   if(myFragment != null && myFragment.isVisible()){
                       getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("PRODUCT_FRAGMENT")).commit();

                   }
                   Intent intent = new Intent(this,FilterProductsActivity.class);
                   startActivityForResult(intent,Utils.REQUEST_FOR_PRODUCT_FILTER);
                   break;

               }


            else if(bottomNavigationView.getSelectedItemId() == R.id.courses){
                   myFragment = getSupportFragmentManager().findFragmentByTag("COURSE_FRAGMENT");
                   if(myFragment != null && myFragment.isVisible()) {
                       getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("COURSE_FRAGMENT")).commit();
                   }
                Intent intent1 = new Intent(this,FilterCoursesActivity.class);
                startActivityForResult(intent1,Utils.REQUEST_FOR_COURSE_FILTER);
                break;
            }

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
        if(requestCode == Utils.REQUEST_FOR_PRODUCT_FILTER && resultCode == RESULT_OK){
            String category = data.getStringExtra("CATEGORY");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ProductsFragment productsFragment = new ProductsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("CATEGORY",category);
            productsFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.mainLayout,productsFragment,"PRODUCT_FRAGMENT");
            fragmentTransaction.commit();
        }


        if(requestCode == Utils.REQUEST_FOR_COURSE_FILTER && resultCode == RESULT_OK){
            String category = data.getStringExtra("CATEGORY");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            CoursesFragment coursesFragment = new CoursesFragment();
            Bundle bundle = new Bundle();
            bundle.putString("CATEGORY",category);
            coursesFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.mainLayout,coursesFragment,"COURSE_FRAGMENT");
            fragmentTransaction.commit();
        }

    }
}
