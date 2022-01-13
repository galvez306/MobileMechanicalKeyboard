package com.creamcode.mobilemechanicalkeyboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EscKeyAdapter extends RecyclerView.Adapter<EscKeyAdapter.ViewHolder> {

    List<String> characters;
    Context ctx;
    LayoutInflater layoutInflater;

    public EscKeyAdapter(Context ctx, List<String> chars){
        this.characters = chars;
        this.layoutInflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.key_card_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.btnEscKey.setText(characters.get(position));
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        AppCompatButton btnEscKey;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnEscKey = itemView.findViewById(R.id.btn_esc_key_card);

            btnEscKey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Guardado "+characters.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
