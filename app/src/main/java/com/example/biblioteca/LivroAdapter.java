package com.example.biblioteca;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LivroAdapter extends BaseAdapter {

    private List<Livro> livros;
    private Activity activity;

    public LivroAdapter(Activity activity, List<Livro> livros) {
        this.activity = activity;
        this.livros = livros;
    }

    @Override
    public int getCount() {
        return livros.size();
    }

    @Override
    public Object getItem(int position) {
        return livros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return livros.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = activity.getLayoutInflater().inflate(R.layout.item, viewGroup, false);
        TextView nome = v.findViewById(R.id.txtNome);
        TextView autor = v.findViewById(R.id.txtAutor);
        TextView ano = v.findViewById(R.id.txtAno);

        Livro a = livros.get(position);

        nome.setText(a.getNome());
        autor.setText(a.getAutor());
        ano.setText(a.getAno());

        return v;
    }
}
