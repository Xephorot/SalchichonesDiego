package com.example.salchichonesdiego.Combos;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salchichonesdiego.DataBase.CarritoDatabaseHelper;
import com.example.salchichonesdiego.R;

import java.util.ArrayList;
import java.util.List;

public class CombosActivity extends AppCompatActivity {

    private List<Combo> combos;
    private CarritoDatabaseHelper carritoDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combos);

        // Inicializar la base de datos del carrito
        carritoDatabaseHelper = new CarritoDatabaseHelper(this);

        // Crear datos de ejemplo de combos de salchichas
        combos = new ArrayList<>();
        combos.add(new Combo("Combo 1", 10.99, "Descripción del Combo 1", "Salchicha A, Salchicha B"));
        combos.add(new Combo("Combo 2", 12.99, "Descripción del Combo 2", "Salchicha C, Salchicha D"));
        combos.add(new Combo("Combo 3", 9.99, "Descripción del Combo 3", "Salchicha E, Salchicha F"));

        // Configurar el RecyclerView
        RecyclerView recyclerViewCombos = findViewById(R.id.recyclerViewCombos);
        recyclerViewCombos.setLayoutManager(new LinearLayoutManager(this));
        ComboAdapter comboAdapter = new ComboAdapter(combos);
        recyclerViewCombos.setAdapter(comboAdapter);
    }

    // Adaptador para el RecyclerView de combos de salchichas
    private class ComboAdapter extends RecyclerView.Adapter<ComboAdapter.ComboViewHolder> {

        private List<Combo> combos;

        public ComboAdapter(List<Combo> combos) {
            this.combos = combos;
        }

        @NonNull
        @Override
        public ComboViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_combo, parent, false);
            return new ComboViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ComboViewHolder holder, int position) {
            Combo combo = combos.get(position);

            holder.textViewComboName.setText(combo.getNombre());
            holder.textViewComboPrice.setText(String.format("$%.2f", combo.getPrecio()));

            holder.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Agregar el combo al carrito de compras
                    carritoDatabaseHelper.agregarCombo(combo);
                    Toast.makeText(CombosActivity.this, "Combo agregado al carrito", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return combos.size();
        }

        public class ComboViewHolder extends RecyclerView.ViewHolder {
            TextView textViewComboName;
            TextView textViewComboPrice;
            Button buttonAddToCart;

            public ComboViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewComboName = itemView.findViewById(R.id.comboNameTextView);
                textViewComboPrice = itemView.findViewById(R.id.comboPriceTextView);
                buttonAddToCart = itemView.findViewById(R.id.buttonAddToCart);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Cerrar la base de datos del carrito
        carritoDatabaseHelper.close();
    }
}

