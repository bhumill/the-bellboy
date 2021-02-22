package com.bhumil.thebellboy.UtilityClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

//import com.projects.bhumil.shoppy.Models.Product;
import com.bhumil.thebellboy.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Utility
{
    public static final String USEREMAIL   = "useremail";
    public static final String USERNAME   = "username";
    public static final String USERPASSWORD = "userpassword";
//    public static final List<Product> productsList = new ArrayList<>();
//    public static final List<Product> orderedproductsList = new ArrayList<>();
//    public static List<Product> placedOrders = new ArrayList<>();

    public static SharedPreferences getPreference(Context ctx)
    {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

//    public static List<Product> GetProductList()
//    {
//        if(productsList.size()<=0)
//        {
//            PrepareAlbums();
//        }
//        return productsList;
//    }
//    public static List<Product> GetBagItems()
//    {
//        return orderedproductsList;
//    }
//
//    public static List<Product> GetPlacedOrders()
//    {
//        return placedOrders;
//    }
//
//    public static void CopyBagToOrders()
//    {
//        placedOrders.addAll(orderedproductsList);//= orderedproductsList;
//        orderedproductsList.clear();
//
//    }
//    public static void AddToBag(Product product)
//    {
//        if(orderedproductsList.size()>0)
//        {
//            Boolean bproductFound = false;
//            for(int i=0; i<orderedproductsList.size(); i++)
//            {
//                Product tmpProduct = orderedproductsList.get(i);
//                if(tmpProduct.getProductId() == product.getProductId())
//                {
//                    orderedproductsList.get(i).setProdcutQty(tmpProduct.getProdcutQty()+1);
//                    BigDecimal bd = new BigDecimal(tmpProduct.getProductPrice() * tmpProduct.getProdcutQty())
//                            .setScale(2, RoundingMode.HALF_UP);
//                    double ActualCost = bd.doubleValue();
//                    orderedproductsList.get(i).setProductTotal(ActualCost);
//                    bproductFound = true;
//                    break;
//                }
//                else
//                {
//                    bproductFound = false;
//                }
//            }
//            if(bproductFound == false)
//            {
//                orderedproductsList.add(product);
//            }
//        }
//        else
//        {
//            orderedproductsList.add(product);
//        }
//    }

//    public static void RemoveFromBag(Product product)
//    {
//        Boolean bproductFound = false;
//        for(int i=0; i<orderedproductsList.size(); i++)
//        {
//            Product tmpProduct = orderedproductsList.get(i);
//            if(tmpProduct.getProductId() == product.getProductId())
//            {
//                if(tmpProduct.getProdcutQty()>1)
//                {
//                    orderedproductsList.get(i).setProdcutQty(tmpProduct.getProdcutQty()-1);
//                    orderedproductsList.get(i).setProductTotal((tmpProduct.getProductPrice() * tmpProduct.getProdcutQty()));
//                    bproductFound = true;
//                    break;
//                }
//                else
//                {
//                    bproductFound = false;
//                }
//            }
//            else
//            {
//                bproductFound = false;
//            }
//        }
//        if(bproductFound == false)
//        {
//            orderedproductsList.remove(product);
//        }
//    }

//    public static void ClearAll()
//    {
//        orderedproductsList.clear();
//        productsList.clear();
//    }



    /**
     * Adding few albums for testing
     */
//    public static void PrepareAlbums() {
//        int[] covers = new int[]{
//                R.drawable.shoes1,
//                R.drawable.shoes2,
//                R.drawable.shoes3,
//                R.drawable.shoes4,
//                R.drawable.shoes5,
//                R.drawable.shoes6,
//                R.drawable.shoes7,
//                R.drawable.shoes8,
//                R.drawable.shoes9,
//                R.drawable.shoes10};
//
//        Product a = new Product(0,"Nike Adapt", 30.5, "3.5", covers[0]);
//        productsList.add(a);
//
//        a = new Product(1,"Nike Air", 32.2, "2.5", covers[1]);
//        productsList.add(a);
//
//        a = new Product(2,"Nike Flyleather", 20.8, "3.0", covers[2]);
//        productsList.add(a);
//
//        a = new Product(3,"Nike Free", 10.2, "4.5", covers[3]);
//        productsList.add(a);
//
//        a = new Product(4,"Nike Zoom Fly", 15.4, "5", covers[4]);
//        productsList.add(a);
//
//        a = new Product(5,"Nike Zoom Rival", 11.7, "3", covers[5]);
//        productsList.add(a);
//
//        a = new Product(6,"Nike Challenge", 10.2, "4", covers[6]);
//        productsList.add(a);
//
//        a = new Product(7,"Nike Just In", 34.3, "4.5", covers[7]);
//        productsList.add(a);
//
//        a = new Product(8,"Nike Air Force", 20.4, "5", covers[8]);
//        productsList.add(a);
//
//        a = new Product(9,"Nike Air Max", 10.1, "4", covers[9]);
//        productsList.add(a);
//    }

}
