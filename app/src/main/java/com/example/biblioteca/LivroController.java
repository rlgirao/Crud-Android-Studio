package com.example.biblioteca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class LivroController {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public LivroController(Context context){

        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Livro livro){

        ContentValues values =  new ContentValues();
        values.put("nome", livro.getNome());
        values.put("autor", livro.getAutor());
        values.put("ano", livro.getAno());

        return banco.insert("livro", null, values);
    }

    public List<Livro> listarLivros(){
        List<Livro> livros = new ArrayList<>();
        Cursor cursor = banco.query("livro", new String[]{"id","nome","autor","ano"},
                null, null, null, null, "nome COLLATE NOCASE ASC");
        while(cursor.moveToNext()){
            Livro livro = new Livro();
            livro.setId(cursor.getInt(0));
            livro.setNome(cursor.getString(1));
            livro.setAutor(cursor.getString(2));
            livro.setAno(cursor.getString(3));
            livros.add(livro);

        }
        return livros;
    }

    public void excluir(Livro a){
        banco.delete("livro","id = ?", new String[]{a.getId().toString()});
    }

    public void atualizar(Livro livro){
        ContentValues values =  new ContentValues();
        values.put("nome", livro.getNome());
        values.put("autor", livro.getAutor());
        values.put("ano", livro.getAno());
        banco.update("livro", values, "id = ?", new
                String[]{livro.getId().toString()});
    }


}
