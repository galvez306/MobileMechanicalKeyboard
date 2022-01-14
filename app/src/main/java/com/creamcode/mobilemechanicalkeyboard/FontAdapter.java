package com.creamcode.mobilemechanicalkeyboard;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FontAdapter extends RecyclerView.Adapter<FontAdapter.ViewHolder> {

    List<String> fuentes;
    Context ctx;
    LayoutInflater layoutInflater;

    public FontAdapter(Context ctx, List<String> fonts){
        this.fuentes = fonts;
        this.layoutInflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.fuente_card_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //aqui modificar cada linea
        holder.tvFuente.setText(fuentes.get(position));
        Typeface type = Typeface.createFromAsset(layoutInflater.getContext().getAssets(), "faster.ttf");
        holder.tvFuente.setTypeface(type);
    }

    @Override
    public int getItemCount() {
        return fuentes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFuente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFuente = itemView.findViewById(R.id.tv_fuente);
        }
    }
}
