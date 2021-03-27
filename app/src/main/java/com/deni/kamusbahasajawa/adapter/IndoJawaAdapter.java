package com.deni.kamusbahasajawa.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deni.kamusbahasajawa.R;
import com.deni.kamusbahasajawa.model.IndoJawa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deni Supriyatna on 16 - Oct - 2019.
 */
public class IndoJawaAdapter extends RecyclerView.Adapter<IndoJawaAdapter.IndoJawaHolder> {

    class IndoJawaHolder extends RecyclerView.ViewHolder{
        public TextView textIndo, textJawa;

        public IndoJawaHolder(@NonNull View itemView){
            super(itemView);
            textIndo = itemView.findViewById(R.id.text_indonesia);
            textJawa = itemView.findViewById(R.id.text_jawa);
        }

        public void bind(final IndoJawa item, final OnItemClickListener listener){
            textIndo.setText(item.getIndo());
            textJawa.setText(item.getJawa());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.OnItemClick(item);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        public void OnItemClick(IndoJawa item);
    }

    private List<IndoJawa> list = new ArrayList<>();
    private OnItemClickListener listener;

    public IndoJawaAdapter(){

    }

    public IndoJawaAdapter(List<IndoJawa>list){
        this.list = list;
    }

    public IndoJawaAdapter(List<IndoJawa> list, OnItemClickListener listener){
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public IndoJawaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_activity_item_layout, viewGroup, false);
        IndoJawaHolder holder = new IndoJawaHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IndoJawaHolder indoJawaHolder, int i) {
        IndoJawa item = list.get(i);
        indoJawaHolder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
