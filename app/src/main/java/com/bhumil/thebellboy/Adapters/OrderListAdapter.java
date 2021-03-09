package com.bhumil.thebellboy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhumil.thebellboy.Models.Product;
import com.bhumil.thebellboy.R;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.BagOrdersViewHolder>
{
    private Context context;
    private List<Product> orderList;
    public OrderRemoveClickListener mOnClickListener;



    public OrderListAdapter(Context context, List<Product> orderList, OrderRemoveClickListener mOnClickListener)
    {
        this.context = context;
        this.orderList = orderList;
        this.mOnClickListener = mOnClickListener;
    }

    @NonNull
    @Override
    public BagOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_list_card, parent, false);
        return new BagOrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BagOrdersViewHolder holder, int position)
    {
        final Product orderedProduct = orderList.get(position);
        holder.tvProductName.setText(orderedProduct.getProductName());
        //holder.tvProductQty.getText().toString();
        holder.tvProductQty.setText(""+orderedProduct.getProdcutQty());
        holder.tvProductPrice.setText("$"+orderedProduct.getProductTotal());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mOnClickListener.DeleteProduct(orderedProduct);
            }
        });
        holder.picker.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                holder.tvProductQty.clearFocus();
            }
        });
        holder.tvProductQty.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
//                    List<Product> tmpBagItems = new ArrayList<Product>();
//                    tmpBagItems = Utility.GetBagItems();
//                    Product product = tmpBagItems.get(0);
                    String str = holder.tvProductQty.getText().toString();
                    int day = new Integer(str);
                    if(day > 0){
                    orderedProduct.setProdcutQty(day);}
                    else{
                        orderedProduct.setProdcutQty(1);
                    }
                }
                if (!hasFocus) {
                    Toast.makeText(context, "Focus Lose", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Get Focus", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class BagOrdersViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvProductName, tvProductPrice;
        EditText tvProductQty;
        Button btnDelete;
        DatePicker picker;


        public BagOrdersViewHolder(@NonNull View view)
        {
            super(view);

            tvProductName = (TextView) itemView.findViewById(R.id.item_product_name);
            tvProductPrice = (TextView) itemView.findViewById(R.id.item_product_price);
            tvProductQty = (EditText) itemView.findViewById(R.id.item_product_qty);
            btnDelete = (Button) itemView.findViewById(R.id.btnOrderDelete);
            picker = (DatePicker) itemView.findViewById(R.id.datePicker1);
           // String tvProductQtystr = tvProductQty.getText().toString();
        }
    }

    public interface OrderRemoveClickListener
    {
        void DeleteProduct(Product product);
    }

}
