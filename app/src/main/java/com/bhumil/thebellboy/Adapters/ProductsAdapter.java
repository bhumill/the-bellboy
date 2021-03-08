package com.bhumil.thebellboy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bhumil.thebellboy.Models.Product;
import com.bhumil.thebellboy.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder>
{
    private Context mContext;
    private List<Product> productsList;
    public ProductClickListener mOnClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        public CardView cardView;
        public LinearLayout linearLayout;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            count = (TextView) itemView.findViewById(R.id.count);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout_view);
        }
    }

    public ProductsAdapter(Context mContext, List<Product> productsList, ProductClickListener productClickListener) {
        this.mContext = mContext;
        this.productsList = productsList;
        mOnClickListener = productClickListener;
    }

    @NonNull
    @Override
    public ProductsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_single_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.MyViewHolder holder, int position)
    {
        final Product product = productsList.get(position);
        holder.title.setText(product.getProductName());
        holder.count.setText( "$ "+ product.getProductPrice());

        // loading album cover using Glide library
        Glide.with(mContext).load(product.getProdcutImage()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.SelectProduct(product);
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mOnClickListener.SelectProduct(product);
            }
        });
//
//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });
    }

    @Override
    public int getItemCount()
    {
        return productsList.size();
    }

    public interface ProductClickListener
    {
        void SelectProduct(Product product);
    }
}