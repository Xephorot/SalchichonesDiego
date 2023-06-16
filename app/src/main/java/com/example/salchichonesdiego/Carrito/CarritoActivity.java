package com.example.salchichonesdiego.Carrito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.salchichonesdiego.Combos.Combo;
import com.example.salchichonesdiego.DataBase.CarritoDatabaseHelper;
import com.example.salchichonesdiego.MainActivity;
import com.example.salchichonesdiego.R;

import java.util.List;

public class CarritoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private List<Combo> cartItems;
    private CarritoDatabaseHelper databaseHelper;

    private Button btnBuy;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        recyclerViewCart = findViewById(R.id.cartRecyclerView);
        btnBuy = findViewById(R.id.btnBuy);
        btnReset = findViewById(R.id.btnReset);
        databaseHelper = new CarritoDatabaseHelper(this);

        // Obtén la lista de combos seleccionados del Intent
        Intent intent = getIntent();
        cartItems = intent.getParcelableArrayListExtra("combos_seleccionados");

        cartAdapter = new CartAdapter(cartItems);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCart.setAdapter(cartAdapter);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Realiza las operaciones necesarias para completar la compra
                // ...

                Toast.makeText(CarritoActivity.this, "Compra Completada", Toast.LENGTH_SHORT).show();
                // Elimina todos los elementos del carrito
                databaseHelper.eliminarCombos();
                // Actualiza la lista de combos
                cartItems.clear();
                cartAdapter.notifyDataSetChanged();

                // Retraso de 2 segundos antes de volver al MainActivity
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Vuelve al MainActivity
                        Intent intent = new Intent(CarritoActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Opcional: Finaliza la actividad actual si no se desea volver a ella
                    }
                }, 2000); // Retraso de 2 segundos (2000 milisegundos)
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Realiza las operaciones necesarias para restablecer el carrito
                // ...

                Toast.makeText(CarritoActivity.this, "Restableciendo Compra", Toast.LENGTH_SHORT).show();
                // Elimina todos los elementos del carrito
                databaseHelper.eliminarCombos();
                // Actualiza la lista de combos
                cartItems.clear();
                cartAdapter.notifyDataSetChanged();

                // Retraso de 2 segundos antes de volver al MainActivity
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Vuelve al MainActivity
                        Intent intent = new Intent(CarritoActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Opcional: Finaliza la actividad actual si no se desea volver a ella
                    }
                }, 2000); // Retraso de 2 segundos (2000 milisegundos)
            }
        });
    }
}
