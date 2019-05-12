package com.example.sushantpaygude.scoutit.Dashboard.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.sushantpaygude.scoutit.R;
import com.example.sushantpaygude.scoutit.Utilities.Utils;

public class FilterProductsActivity extends AppCompatActivity implements View.OnClickListener{
private RelativeLayout layoutElectronics;
private RelativeLayout layoutBooks;
private RelativeLayout layoutFurniture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        layoutBooks = findViewById(R.id.layoutBooks);
        layoutElectronics = findViewById(R.id.layoutElectronics);
        layoutFurniture = findViewById(R.id.layoutFurniture);
        layoutBooks.setOnClickListener(this);
        layoutElectronics.setOnClickListener(this);
        layoutFurniture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.layoutBooks:
                intent.putExtra("CATEGORY", Utils.productCategoryEnum.BOOKS.toString());
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.layoutElectronics:
                intent.putExtra("CATEGORY", Utils.productCategoryEnum.ELECTRONICS.toString());
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.layoutFurniture:
                intent.putExtra("CATEGORY", Utils.productCategoryEnum.FURNITURE.toString());
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
