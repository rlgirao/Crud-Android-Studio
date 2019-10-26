package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cadastrar(View view){
        Intent it = new Intent(this, CadastrarLivros.class);
        startActivity(it);
    }

    public  void pesquisar(View view){
        Intent it = new Intent(this, ListarLivros.class);
        startActivity(it);
    }
}
