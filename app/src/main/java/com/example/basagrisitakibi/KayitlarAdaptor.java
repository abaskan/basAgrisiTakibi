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

public class KayitlarAdaptor extends RecyclerView.Adapter<KayitlarAdaptor.CardTasarimTutucu>{
    Context mContext;
    ArrayList<Kayitlar> kayitlarListesi;

    public KayitlarAdaptor(Context mContext, ArrayList<Kayitlar> kayitlarListesi) {
        this.mContext = mContext;
        this.kayitlarListesi = kayitlarListesi;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kayitlar_card_tasarim,parent,false);


        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        final Kayitlar kayit = kayitlarListesi.get(position);
        holder.cardStartTextView.setText("Baş. Zamanı : " + kayit.getStartTime());
        holder.cardStopTextView.setText("Bit.Zamanı : " + kayit.getStopTime());
        holder.cardSiddetTextView.setText(kayit.getAgriSiddeti());
        holder.kayitlarCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,DetayKayitlar.class);
                intent.putExtra("nesne",kayit);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return kayitlarListesi.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        TextView cardStopTextView;
        TextView cardStartTextView;
        TextView cardSiddetTextView;
        CardView kayitlarCard;


        public CardTasarimTutucu(View itemView) {
            super(itemView);

            cardStartTextView = itemView.findViewById(R.id.cardStartTextView);
            cardStopTextView = itemView.findViewById(R.id.cardStopTextView);
            cardSiddetTextView = itemView.findViewById(R.id.cardSiddetTextView);
            kayitlarCard = itemView.findViewById(R.id.kayitlarCard);

        }
    }
}
