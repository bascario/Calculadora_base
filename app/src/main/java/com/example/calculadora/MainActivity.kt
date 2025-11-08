package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //Referencias a los elementos de UX
        val txtnum1 = findViewById<EditText>(R.id.txtNumero1)   //Constantes VAL (inmutables)
        val txtnum2 = findViewById<EditText>(R.id.txtNumero2)   //Variables VAR (mutables)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)
        val btnSumar = findViewById<Button>(R.id.btnSumar)
        val btnRestar = findViewById<Button>(R.id.btnRestar)
        val btnMultiplicar = findViewById<Button>(R.id.btnMultiplicar)
        val btnDividir = findViewById<Button>(R.id.btnDividir)
        val btnFactorial = findViewById<Button>(R.id.btnFactorial)

        //funciones auxiliares para valiudar la entrada
        fun obtenerNumeros(): Pair<Double?, Double?> {
            val num1 = txtnum1.text.toString()
            val num2 = txtnum2.text.toString()
            if(num1.isEmpty() || num2.isEmpty()) {
             Toast.makeText(this, "Ingrese ambos números", Toast.LENGTH_SHORT).show()
                return Pair(null, null)
            }
            return Pair(num1.toDoubleOrNull(), num2.toDoubleOrNull())
        }
    //eventos: sumar
        btnSumar.setOnClickListener {
            val (a, b) = obtenerNumeros()
            if (a != null && b != null) {
                txtResultado.text = "Resultado: ${a + b}"
            }
        }
        //eventos: restar
        btnRestar.setOnClickListener {
            val (a, b) = obtenerNumeros()
            if (a != null && b != null) {
                txtResultado.text = "Resultado: ${a - b}"
            }
        }
        //evento multiplicar
        btnMultiplicar.setOnClickListener {
            val (a, b) = obtenerNumeros()
            if (a != null && b != null) {
                txtResultado.text = "Resultado: ${a * b}"
            }
        }
        //evento dividir
        btnDividir.setOnClickListener {
            val (a, b) = obtenerNumeros()
            if (a != null && b != null) {
                if (b == 0.0) {
                    Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show()
                    txtResultado.text="la division por cero no se puede realizar"
                } else{
                    txtResultado.text = "Resultado: ${a / b}"
                }
            }
        }
        //evento factorial
        btnFactorial.setOnClickListener {
            val num1 = txtnum1.text.toString()
            if (num1.isEmpty()) {
                Toast.makeText(this, "Ingrese un número", Toast.LENGTH_SHORT).show()
            } else {
                val n = num1.toIntOrNull()
                if (n == null||n<0) {
                    Toast.makeText(this, "Ingrese un número entero positivo", Toast.LENGTH_SHORT)
                        .show()
                }else{
                val resultado = calcularFactorial(n)
                txtResultado.text = "Resultado: $n! = $resultado"
            }
        }
    }
}
private fun calcularFactorial(n: Int): Long {
    var resultado = 1L //var es mutable y val es inmutable
    for(i in 1..n){
        resultado *= i
    }
    return resultado
    }
}