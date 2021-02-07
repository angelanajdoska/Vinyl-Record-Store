package com.example.vinylrecordstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinylrecordstore.models.Products;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context mContext;
    private int rowLayout;
    List<Products> productsList;
    Button button;
    TextView textView;
    private Double price;
    CartFragment cartFragment;

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row, parent, false);
        return new CartAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Products entry = productsList.get(position);
        holder.album.setText(entry.getAlbum());
        holder.artist.setText(entry.getArtist());
        holder.price.setText(entry.getPrice());
        holder.img.setImageResource(entry.getImage());
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabase myDatabase = new MyDatabase(mContext);
                myDatabase.deleteOne(productsList.get(position).getId());
                productsList.remove(productsList.get(position));
                myDatabase.close();
                notifyDataSetChanged();
                getPrice();
            }
        });

    }

    @Override
    public int getItemCount() {
        return productsList == null ? 0 : productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView album, artist, price, dollar;
        public ImageView img;
        public ImageButton cancel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            album = (TextView) itemView.findViewById(R.id.album);
            artist = (TextView) itemView.findViewById(R.id.artist);
            dollar = (TextView) itemView.findViewById(R.id.dollar);
            price = (TextView) itemView.findViewById(R.id.price);
            img = (ImageView) itemView.findViewById(R.id.img);
            cancel = (ImageButton) itemView.findViewById(R.id.cancel);
        }
    }
    public CartAdapter(Context context, int rowLayout, List<Products> productsList, Button button, TextView textView, Double price){
        this.mContext = context;
        this.rowLayout = rowLayout;
        this.productsList = productsList;
        this.button= button;
        this.textView = textView;
        this.price = price;
    }
    public void getPrice(){
        price = 0.0;
        for (Products item : productsList) {
            price+= Double.parseDouble(item.getPrice());
        }
        textView.setText("Total: $" + String.format("%.2f", price));
    }
}
