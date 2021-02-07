package com.example.vinylrecordstore;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
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

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private List<String> artistList;
    private List<String> albumList;
    private List<String> priceList;
    private Context mContext;
    private int rowLayout;
    int images[];
    int flag = 0;
    //private List<Object> songs;
    private MediaPlayer mPlayer;
    Products product;

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_row, parent, false);
        return new ProductsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int i) {
        String entry = albumList.get(i);
        String art = artistList.get(i);
        String pr = priceList.get(i);
        holder.album.setText(entry);
        holder.artist.setText(art);
        holder.price.setText(pr);
        holder.img.setImageResource(images[i]);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  product.setAlbum(albumList.get(i));
                product.setArtist(artistList.get(i));
                product.setPrice(priceList.get(i));
                product.setImage(images[i]);*/
                MyDatabase myDatabase = new MyDatabase(v.getContext());
                myDatabase.insertProduct(entry, art, pr, images[i]);
                Toast.makeText(v.getContext(), "Product successfully added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (i == 0) {
                        stopPlaying();
                        mPlayer = MediaPlayer.create(mContext, R.raw.falling);
                        mPlayer.start();
                    } else if (i == 1) {
                        stopPlaying();
                        mPlayer = MediaPlayer.create(mContext, R.raw.coconutskins);
                        mPlayer.start();
                    } else if (i == 2) {
                        stopPlaying();
                        mPlayer = MediaPlayer.create(mContext, R.raw.bamboleo);
                        mPlayer.start();
                    } else if (i == 3) {
                        stopPlaying();
                        mPlayer = MediaPlayer.create(mContext, R.raw.kashmir);
                        mPlayer.start();
                    } else if (i == 4) {
                        stopPlaying();
                        mPlayer = MediaPlayer.create(mContext, R.raw.anotheronebitesthedust);
                        mPlayer.start();
                    } else if (i == 5) {
                        stopPlaying();
                        mPlayer = MediaPlayer.create(mContext, R.raw.anybodyaeenmybaby);
                        mPlayer.start();
                    } else if (i == 6) {
                        stopPlaying();
                        mPlayer = MediaPlayer.create(mContext, R.raw.letitbe);
                        mPlayer.start();
                    }
                }
        });
        holder.stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumList == null ? 0 : albumList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView album, artist, price, dollar;
        public ImageView img;
        public Button button;
        ImageButton play, stop;

        public ViewHolder(View itemView) {
            super(itemView);
            album = (TextView) itemView.findViewById(R.id.album);
            artist = (TextView) itemView.findViewById(R.id.artist);
            dollar = (TextView) itemView.findViewById(R.id.dollar);
            price = (TextView) itemView.findViewById(R.id.price);
            img = (ImageView) itemView.findViewById(R.id.img);
            button = (Button) itemView.findViewById(R.id.button);
            play = (ImageButton) itemView.findViewById(R.id.play);
            stop = (ImageButton) itemView.findViewById(R.id.stop);
        }
    }
    public ProductsAdapter(Context context, int[] images, List<String> artist,  List<String> album,  List<String> price, int rowLayout){
        this.mContext = context;
        this.images = images;
        this.artistList = artist;
        this.albumList = album;
        this.priceList = price;
        this.rowLayout = rowLayout;
       // this.songs = songs;
    }
    protected void stopPlaying(){
        // If media player is not null then try to stop it
        if(mPlayer!=null){
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

}
