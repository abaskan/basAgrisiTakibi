package com.example.basagrisitakibi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RaporlarAdaptor extends RecyclerView.Adapter<RaporlarAdaptor.raporCardTutucu> {
    Context mContext;
    ArrayList <Raporlar> raporlarListesi;


    public RaporlarAdaptor(Context mContext, ArrayList<Raporlar> raporlarListesi) {
        this.mContext = mContext;
        this.raporlarListesi = raporlarListesi;
    }

    @NonNull
    @Override
    public raporCardTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raporlar_card_tasarim,parent,false);


        return new raporCardTutucu(view);
    }

    @Override
    public int getItemCount() {
        return raporlarListesi.size();
    }

    @Override
    public void onBindViewHolder(@NonNull raporCardTutucu holder, int position) {
        final Raporlar rapor = raporlarListesi.get(position);

        holder.raporTextView.setText(rapor.getKayitTarihi());
        holder.raporCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,DetayRaporlar.class);
                intent.putExtra("rapor",rapor);
                mContext.startActivity(intent);
            }
        });
    }

    public class raporCardTutucu extends RecyclerView.ViewHolder{
        CardView raporCard;
        TextView raporTextView;

        public raporCardTutucu(View itemView) {
            super(itemView);
            raporCard = itemView.findViewById(R.id.raporCard);
            raporTextView = itemView.findViewById(R.id.raporTextView);
        }
    }
}
