package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListarLivros extends AppCompatActivity {

    private ListView listView;
    private LivroController controller;
    private List<Livro> livros;
    private List<Livro> livrosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_livros);

        listView = findViewById(R.id.lista_livros);
        controller = new LivroController(this);

        livros = controller.listarLivros();
        livrosFiltrados.addAll(livros);

        //ArrayAdapter<Livro> adaptador = new ArrayAdapter<Livro>(this, android.R.layout.simple_list_item_1, livrosFiltrados);
        LivroAdapter adaptador = new LivroAdapter(this, livrosFiltrados);
        listView.setAdapter(adaptador);

        registerForContextMenu(listView);

    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_pesquisar).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraLivro(s);
                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
       super.onCreateContextMenu(menu, v, menuInfo);
       MenuInflater i = getMenuInflater();
       i.inflate(R.menu.menu_edicao, menu);

    }

    public void procuraLivro(String nome){
        livrosFiltrados.clear();
        for(Livro a : livros){
            if(a.getNome().toLowerCase().contains(nome.toLowerCase()) ||
                    a.getAutor().toLowerCase().contains(nome.toLowerCase()) ||
                    a.getAno().toLowerCase().contains(nome.toLowerCase())){
                livrosFiltrados.add(a);
            }
        }
        listView.invalidateViews();
    }

    public  void cadastrar(MenuItem item){
        Intent it = new Intent(this, CadastrarLivros.class);
        startActivity(it);
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Livro livroExcluir = livrosFiltrados.get(menuInfo.position);

        AlertDialog aviso = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja excluir o Livro?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int sim) {
                        livrosFiltrados.remove(livroExcluir);
                        livros.remove(livroExcluir);
                        controller.excluir(livroExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        aviso.show();
    }

    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Livro livroAtualizar = livrosFiltrados.get(menuInfo.position);

        Intent it = new Intent(this, CadastrarLivros.class);
        it.putExtra("livro", livroAtualizar);
        startActivity(it);
    }

    @Override
    public void onResume(){
        super.onResume();
        livros = controller.listarLivros();
        livrosFiltrados.clear();
        livrosFiltrados.addAll(livros);
        listView.invalidateViews();
    }

    public void voltar(View view){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}
