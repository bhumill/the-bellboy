package com.bhumil.thebellboy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhumil.thebellboy.Adapters.OrderListAdapter;
import com.bhumil.thebellboy.Models.Product;
import com.bhumil.thebellboy.UtilityClasses.Utility;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar tbToolBarOrderList;
    RecyclerView rvOrderList;
    TextView tvNoData;
    Button btnCheckout;
    EditText etNumberOfDays;
    private OrderListAdapter mOrderListAdapter;
    protected SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        mSharedPreferences = Utility.getPreference(this);
        tbToolBarOrderList =  findViewById(R.id.toolbar_order_list);
        rvOrderList = (RecyclerView) findViewById(R.id.order_recycler_view);
        tvNoData = (TextView) findViewById(R.id.tv_no_orders);
        btnCheckout = (Button) findViewById(R.id.btn_checkout);
        etNumberOfDays = (EditText) findViewById(R.id.item_product_qty);
        setSupportActionBar(tbToolBarOrderList);
        tbToolBarOrderList.setNavigationIcon(getResources().getDrawable(R.drawable.action_back));
        tbToolBarOrderList.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        if(Utility.GetBagItems().size()>0)
        {
            //Show Recycler view
            mOrderListAdapter = new OrderListAdapter(OrderListActivity.this, Utility.GetBagItems(), new OrderListAdapter.OrderRemoveClickListener() {
                @Override
                public void DeleteProduct(Product product)
                {
                    Utility.RemoveFromBag(product);
                    Toast.makeText(OrderListActivity.this, "Product removed from bag!",Toast.LENGTH_SHORT).show();
                    if(Utility.GetBagItems().size()<=0)
                    {
                        rvOrderList.setVisibility(View.GONE);
                        tvNoData.setVisibility(View.VISIBLE);
                        btnCheckout.setText(R.string.go_to_products);
                    }
                    mOrderListAdapter.notifyDataSetChanged();
                }
            });
            rvOrderList.setAdapter(mOrderListAdapter);
            rvOrderList.setHasFixedSize(true);
            rvOrderList.setLayoutManager(new LinearLayoutManager(OrderListActivity.this));
        }
        else
        {
            rvOrderList.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
            btnCheckout.setText(R.string.go_to_products);
        }
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(btnCheckout.getText().equals("GO TO HOTELS"))
                {
                    Intent checkoutActivity = new Intent(OrderListActivity.this, HotelsActivity.class);
                    startActivity(checkoutActivity);
                }
                else
                {
                    btnCheckout.setFocusableInTouchMode(true);
                    btnCheckout.setFocusable(true);

                    List<Product> tmpBagItems = new ArrayList<Product>();
                    tmpBagItems = Utility.GetBagItems();
                    Product product = tmpBagItems.get(0);
                    Intent checkoutActivity = new Intent(OrderListActivity.this, CheckoutActivity.class);
                    startActivity(checkoutActivity);
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
        startActivity(new Intent(OrderListActivity.this, LoginActivity.class));
        finish();
        return super.onOptionsItemSelected(item);
    }

}