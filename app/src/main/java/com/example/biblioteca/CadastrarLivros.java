package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarLivros extends AppCompatActivity {

    private EditText nome;
    private EditText autor;
    private EditText ano;
    private LivroController controller;
    private Livro livro = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_livros);

        nome = findViewById(R.id.edtNome);
        autor = findViewById(R.id.edtAutor);
        ano = findViewById(R.id.edtAno);

        controller = new LivroController(this);

        Intent it = getIntent();
        if(it.hasExtra("livro")){
            livro = (Livro) it.getSerializableExtra("livro");
            nome.setText(livro.getNome());
            autor.setText(livro.getAutor());
            ano.setText(livro.getAno());
        }
    }

    public void salvar(View View){
        if(livro == null){
            Livro livro = new Livro();
            livro.setNome(nome.getText().toString());
            livro.setAutor(autor.getText().toString());
            livro.setAno(ano.getText().toString());

            controller.inserir(livro);
            Toast.makeText(this,"Livro cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

            Intent it = new Intent(this, ListarLivros.class);
            startActivity(it);
        }else{
            livro.setNome(nome.getText().toString());
            livro.setAutor(autor.getText().toString());
            livro.setAno(ano.getText().toString());
            controller.atualizar(livro);

            Toast.makeText(this,"Livro atualizado com sucesso!", Toast.LENGTH_SHORT).show();

            Intent it = new Intent(this, ListarLivros.class);
            startActivity(it);
        }

    }

    public void voltar(View view){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}
