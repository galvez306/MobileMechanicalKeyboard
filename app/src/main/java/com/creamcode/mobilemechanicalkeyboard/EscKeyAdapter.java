package com.creamcode.mobilemechanicalkeyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EscKeyAdapter extends RecyclerView.Adapter<EscKeyAdapter.ViewHolder> {

    private SharedPreferences sharedPreferences;

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
        holder.tvEscKey.setText(characters.get(position));
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView tvEscKey;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEscKey = itemView.findViewById(R.id.tv_card);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("esckeycap", characters.get(getAdapterPosition()));
                    editor.apply();
                    Toast.makeText(v.getContext(), characters.get(getAdapterPosition())+" "+v.getContext().getResources().getString(R.string.guardado), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
