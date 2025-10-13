package com.example.gasolina;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button btnCalcular;
    TextView tvwProporcao, tvwResultado;
    EditText edtAlcool, edtGasolina;
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnCalcular = findViewById(R.id.btnCalcular);
        tvwProporcao = findViewById(R.id.tvwProporcao);
        tvwResultado = findViewById(R.id.tvwResultado);

        edtAlcool = findViewById(R.id.edtAlcool);
        edtGasolina = findViewById(R.id.edtGasolina);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strAlcool = edtAlcool.getText().toString().trim();
                String strGasolina = edtGasolina.getText().toString().trim();

                if (strAlcool.isEmpty()){
                    Toast.makeText(MainActivity.this, "Campo Alcool Vazio. Insira um Valor", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (strGasolina.isEmpty()){
                    Toast.makeText(MainActivity.this, "Campo Gasolina Vazio. Insira um Valor", Toast.LENGTH_SHORT).show();
                    return;
                }

                double dblAlcool = Double.parseDouble(strAlcool);
                double dblGasolina = Double.parseDouble(strGasolina);

                double dblResultado = (dblAlcool/dblGasolina) * 100;

                tvwProporcao.setText("Proporção " + df.format(dblResultado) + "%");

                if (dblResultado > 70)  {
                    tvwResultado.setText("Abasteça com Gasolina");
                } else {
                    tvwResultado.setText("Abasteça com Álcool");
                }


            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}