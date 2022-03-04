package com.creamcode.mobilemechanicalkeyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SwitchAdapter extends RecyclerView.Adapter<SwitchAdapter.ViewHolder> {

    private SharedPreferences sharedPreferences;

    List<String> switches;
    List<Integer> switchesImg;
    List<Integer> switchesSound;
    Context ctx;
    LayoutInflater layoutInflater;
    MediaPlayer switchSound;
    Toast tostada;

    public SwitchAdapter(Context ctx, List<String> switches, List<Integer> switchesImg, List<Integer> switchesSound){
        this.switches = switches;
        this.layoutInflater = LayoutInflater.from(ctx);
        this.switchesImg = switchesImg;
        this.switchesSound = switchesSound;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.switch_card_view,parent,false);
        return new SwitchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgSwitch.setImageResource(switchesImg.get(position));
        holder.tvswitch.setText(switches.get(position));
    }

    @Override
    public int getItemCount() {
        return switches.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSwitch;
        TextView tvswitch;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSwitch = itemView.findViewById(R.id.cv_imv_switch);
            tvswitch = itemView.findViewById(R.id.cv_tv_switch);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchSound = MediaPlayer.create(v.getContext(),switchesSound.get(getAdapterPosition()));
                    switchSound.start();
                    switchSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer p1) {
                            p1.release();
                        }
                    });
                    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("SWITCH_TYPE", switches.get(getAdapterPosition()));
                    editor.apply();
                    if (tostada!= null) {
                        tostada.cancel();
                    }
                    tostada = Toast.makeText(v.getContext(), v.getContext().getResources().getString(R.string.guardado), Toast.LENGTH_SHORT);
                    tostada.show();
                    /*sharedPreferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("esckeycap", switches.get(getAdapterPosition()));
                    editor.apply();
                    Toast.makeText(v.getContext(), switches.get(getAdapterPosition())+" "+v.getContext().getResources().getString(R.string.guardado), Toast.LENGTH_SHORT).show();*/
                }
            });
        }
    }
}
