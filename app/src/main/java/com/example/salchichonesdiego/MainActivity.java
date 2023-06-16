package com.example.salchichonesdiego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salchichonesdiego.Carrito.CarritoActivity;
import com.example.salchichonesdiego.Combos.Combo;
import com.example.salchichonesdiego.DataBase.CarritoDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCombos;
    private ComboAdapter comboAdapter;
    private List<Combo> combos;
    private List<Combo> combosSeleccionados;
    private CarritoDatabaseHelper databaseHelper;
    private Button btnCarrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCombos = findViewById(R.id.recyclerViewCombos);
        combos = new ArrayList<>();
        combosSeleccionados = new ArrayList<>();
        databaseHelper = new CarritoDatabaseHelper(this);
        btnCarrito = findViewById(R.id.btnCarrito);

        comboAdapter = new ComboAdapter(combos);
        recyclerViewCombos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCombos.setAdapter(comboAdapter);

        // Ejemplo de agregar combos de salchichas
        combos.add(new Combo("Combo 1", 9.99, "Descripción del Combo 1", "Salchicha A, Salchicha B"));
        combos.add(new Combo("Combo 2", 12.99, "Descripción del Combo 2", "Salchicha C, Salchicha D"));
        combos.add(new Combo("Combo 3", 8.99, "Descripción del Combo 3", "Salchicha E, Salchicha F"));

        // Verificar si hay combos seleccionados almacenados en savedInstanceState
        if (savedInstanceState != null) {
            combosSeleccionados = savedInstanceState.getParcelableArrayList("combos_seleccionados");
        } else {
            combosSeleccionados = new ArrayList<>();
        }

        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CarritoActivity.class);
                intent.putExtra("combos_seleccionados", new ArrayList<>(combosSeleccionados));
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("combos_seleccionados", new ArrayList<>(combosSeleccionados));
    }

    private class ComboAdapter extends RecyclerView.Adapter<ComboAdapter.ComboViewHolder> {

        private List<Combo> combos;

        public ComboAdapter(List<Combo> combos) {
            this.combos = combos;
        }

        @Override
        public ComboViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_combo, parent, false);
            return new ComboViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ComboViewHolder holder, int position) {
            Combo combo = combos.get(position);
            holder.comboNameTextView.setText(combo.getNombre());
            holder.comboPriceTextView.setText(String.format("$%.2f", combo.getPrecio()));

            if (combosSeleccionados.contains(combo)) {
                holder.btnAgregar.setEnabled(false);
            } else {
                holder.btnAgregar.setEnabled(true);
            }
        }

        @Override
        public int getItemCount() {
            return combos.size();
        }

        public class ComboViewHolder extends RecyclerView.ViewHolder {
            TextView comboNameTextView;
            TextView comboPriceTextView;
            Button btnAgregar;

            public ComboViewHolder(View itemView) {
                super(itemView);
                comboNameTextView = itemView.findViewById(R.id.comboNameTextView);
                comboPriceTextView = itemView.findViewById(R.id.comboPriceTextView);
                btnAgregar = itemView.findViewById(R.id.buttonAddToCart);

                btnAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        Combo combo = combos.get(position);

                        if (combosSeleccionados.contains(combo)) {
                            combosSeleccionados.remove(combo);
                            btnAgregar.setEnabled(true);
                        } else {
                            combosSeleccionados.add(combo);
                            btnAgregar.setEnabled(false);
                        }

                        Toast.makeText(MainActivity.this, "Se ha agregado el producto al carrito", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}
