package com.example.salchichonesdiego.Carrito;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salchichonesdiego.Combos.Combo;
import com.example.salchichonesdiego.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Combo> cartItems;

    public CartAdapter(List<Combo> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_combo, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Combo combo = cartItems.get(position);
        holder.comboNameTextView.setText(combo.getNombre());
        holder.comboPriceTextView.setText(String.valueOf(combo.getPrecio()));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView comboNameTextView;
        TextView comboPriceTextView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            comboNameTextView = itemView.findViewById(R.id.comboNameTextView);
            comboPriceTextView = itemView.findViewById(R.id.comboPriceTextView);
        }
    }
}

