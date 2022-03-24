package com.example.appsale30112021.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.appsale30112021.R;
import com.example.appsale30112021.data.remote.response.ProductResponse;
import com.example.appsale30112021.databinding.ItemProductBinding;
import com.example.appsale30112021.ultils.AppConstant;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    List<ProductResponse> lstProduct;

    public ProductAdapter(){
        lstProduct = new ArrayList<>();
    }

    public void updateListProduct(List<ProductResponse> data){
        if (lstProduct != null && lstProduct.size() > 0){
            lstProduct.clear();
        }
        lstProduct.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding itemProductBinding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ProductViewHolder(itemProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(lstProduct.get(position));
    }

    @Override
    public int getItemCount() {
        return lstProduct.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ItemProductBinding mBinding;

        public ProductViewHolder(@NonNull ItemProductBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }

        public void bind(ProductResponse product){
            mBinding.setProduct(product);
        }
    }



}
