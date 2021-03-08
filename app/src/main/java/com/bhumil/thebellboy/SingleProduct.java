package com.bhumil.thebellboy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bhumil.thebellboy.Models.Product;
import com.bhumil.thebellboy.UtilityClasses.Utility;
import com.bumptech.glide.Glide;

public class SingleProduct extends AppCompatActivity {
    TextView tvProdName, tvProductDesc, tvProductPrice;
    ImageView ivProdImage;
    Button btnAddToBag, btnGoToProducts;
    RatingBar rtProductRating;
    private androidx.appcompat.widget.Toolbar tbToolBarSingleProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);

        rtProductRating = (RatingBar) findViewById(R.id.product_rating);
        tvProdName = (TextView) findViewById(R.id.tv_product_name);
        tvProductPrice = (TextView) findViewById(R.id.tv_product_price);
        tvProductDesc = (TextView) findViewById(R.id.tv_product_detail);
        ivProdImage = (ImageView)findViewById(R.id.iv_prod_thumbnail);
        btnAddToBag = (Button) findViewById(R.id.btn_add_to_bag);
        btnGoToProducts = (Button)findViewById(R.id.btn_goto_products);
        tbToolBarSingleProduct = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar_single_product);

        setSupportActionBar(tbToolBarSingleProduct);
        Intent prodIntent = getIntent();
        int ProductId = prodIntent.getIntExtra("productId",0);

        tbToolBarSingleProduct.setNavigationIcon(getResources().getDrawable(R.drawable.action_back));
        tbToolBarSingleProduct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        final Product objProduct = Utility.productsList.get(ProductId);

        Glide.with(SingleProduct.this).load(objProduct.getProdcutImage()).into(ivProdImage);
        tvProdName.setText(objProduct.getProductName());
        tvProductPrice.setText("$"+objProduct.getProductPrice());
        float rating = 0;
        try {
            rating = Float.parseFloat(objProduct.getProductRating());
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
            rating = 0;
        }
        rtProductRating.setRating(rating);
        //tvProductDesc.setText(objProduct.getProductRating() + objProduct.getProductPrice());

        btnAddToBag.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean bAdded = false;
                if(!btnAddToBag.getText().equals(getString(R.string.go_to_bag)))
                {
                    Utility.AddToBag(objProduct);
                    Toast.makeText(SingleProduct.this, objProduct.getProductName()+" "+getString(R.string.added_to_bag),Toast.LENGTH_LONG).show();
                    btnAddToBag.setText(getString(R.string.go_to_bag));
                    bAdded = true;
                }
                else
                {
                    Intent intOrderList = new Intent(SingleProduct.this, OrderListActivity.class);
                    startActivity(intOrderList);
                }

            }
        });

        btnGoToProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SingleProduct.super.onBackPressed();
            }
        });
    }
}
