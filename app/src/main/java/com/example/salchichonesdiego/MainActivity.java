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
        combos.add(new Combo("Salchichas Pequeñas", 9.99, "Estas Salchicas son tan pequeñas que entraran en cualquier lugar", "Salchicha enana 10 Unidades"));
        combos.add(new Combo("Salchichas Enormes", 12.99, "Solo los valientes puede comer todas estas salchicas", "Salchicha Garagantuan 3 unidades"));
        combos.add(new Combo("Salchichas Raras", 8.99, "?????????", "Un Misterio"));
        combos.add(new Combo("Salchicha Clásica", 6.99, "La salchicha clásica de siempre", "Salchicha 100% carne de res 5 unidades"));
        combos.add(new Combo("Salchicha Picante", 7.99, "Una salchicha con un toque de picante", "Salchicha picante 6 unidades"));
        combos.add(new Combo("Salchicha de Pollo", 5.99, "Deliciosa salchicha hecha de carne de pollo", "Salchicha de pollo 8 unidades"));
        combos.add(new Combo("Salchicha Vegetariana", 8.99, "Una opción vegetariana sin carne", "Salchicha vegetariana a base de soja 4 unidades"));
        combos.add(new Combo("Salchicha de Tofu", 9.99, "Salchicha hecha a base de tofu", "Salchicha de tofu 6 unidades"));
        combos.add(new Combo("Salchicha de Pavo", 7.99, "Salchicha de pavo baja en grasa", "Salchicha de pavo 10 unidades"));
        combos.add(new Combo("Salchicha Ahumada", 8.99, "Salchicha ahumada con un sabor intenso", "Salchicha ahumada 8 unidades"));
        combos.add(new Combo("Salchicha de Cordero", 9.99, "Salchicha elaborada con carne de cordero", "Salchicha de cordero 6 unidades"));
        combos.add(new Combo("Salchicha de Cerdo", 7.99, "Salchicha jugosa de cerdo", "Salchicha de cerdo 12 unidades"));
        combos.add(new Combo("Salchicha de Ternera", 8.99, "Salchicha tierna y sabrosa de ternera", "Salchicha de ternera 8 unidades"));


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
