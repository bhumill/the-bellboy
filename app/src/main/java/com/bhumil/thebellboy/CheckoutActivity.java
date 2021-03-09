package com.bhumil.thebellboy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bhumil.thebellboy.Models.Product;
import com.bhumil.thebellboy.UtilityClasses.Utility;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    private androidx.appcompat.widget.Toolbar tbToolBarCheckout;
    Button btnPlaceOrder;
    TextInputEditText tdCardNumber;
    RadioGroup rgPaymentOptions;
    protected SharedPreferences mSharedPreferences;
    TextView tvItemQty, tvItemTotal;
    EditText etNameText, etEmailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        mSharedPreferences = Utility.getPreference(this);
        tbToolBarCheckout = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar_checkout);
        btnPlaceOrder = (Button) findViewById(R.id.btn_place_order);
        rgPaymentOptions = (RadioGroup) findViewById(R.id.rg_payment_options);
        tdCardNumber = (TextInputEditText) findViewById(R.id.card_number_edit_text);
        etNameText = (EditText) findViewById(R.id.name_edit_text);
        etEmailText = (EditText) findViewById(R.id.email_edit_text);

        tvItemQty = (TextView) findViewById(R.id.tv_product_total_qty);
        tvItemTotal = (TextView) findViewById(R.id.tv_product_total_amount);

        setSupportActionBar(tbToolBarCheckout);
        tbToolBarCheckout.setNavigationIcon(getResources().getDrawable(R.drawable.action_back));
        tbToolBarCheckout.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        etNameText.setText(mSharedPreferences.getString(Utility.USERNAME, ""));
        etEmailText.setText(mSharedPreferences.getString(Utility.USEREMAIL, ""));
        double totalQty = 0;
        double totalCost= 0;
        if(Utility.GetBagItems().size() > 0)
        {
            List<Product> tmpBagItems = new ArrayList<Product>();
            tmpBagItems = Utility.GetBagItems();
            for(int i =0; i< tmpBagItems.size(); i++)
            {
                Product tmpProduct = tmpBagItems.get(i);
                totalQty += tmpProduct.getProdcutQty();
                totalCost += (tmpProduct.getProductTotal() * totalQty);
            }
        }

        tvItemQty.setText(""+totalQty);
        tvItemTotal.setText("$"+totalCost);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (rgPaymentOptions.getCheckedRadioButtonId() == -1)
                {
                    // no radio buttons are checked
                    Toast.makeText(CheckoutActivity.this, getString(R.string.error_payment_option), Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent viewOrders = new Intent(CheckoutActivity.this, PlacedOrderActivity.class);
                    startActivity(viewOrders);
//                    if (TextUtils.isEmpty(tdCardNumber.getText()))
//                    {
//                        Toast.makeText(CheckoutAcitivity.this, getString(R.string.error_card_input), Toast.LENGTH_LONG).show();
//                    }
//                    else
//                    {
//
//                    }
                }

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu1)
    {
        getMenuInflater().inflate(R.menu.action_logout, menu1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        mSharedPreferences.edit().clear().commit();
        Utility.ClearAll();
        startActivity(new Intent(CheckoutActivity.this, LoginActivity.class));
        finish();
        return super.onOptionsItemSelected(item);
    }
}