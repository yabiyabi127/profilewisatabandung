package salam.aplikasi.wisatabandung.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import salam.aplikasi.wisatabandung.R;
import salam.aplikasi.wisatabandung.activity.DestinationDetailActivity;
import salam.aplikasi.wisatabandung.model.Destination;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {
    private Context context;
    private List<Destination> destinationList;

    public DestinationAdapter(List<Destination> destinationList, Context context) {
        this.destinationList = destinationList;
        this.context = context;
    }

    @NonNull
    @Override
    public DestinationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_wisata, viewGroup, false);

        return new DestinationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationAdapter.ViewHolder viewHolder, int i) {
        final Destination pesanan = destinationList.get(i);
        viewHolder.uuid.setText(pesanan.getName());
        viewHolder.name.setText(pesanan.getName());
        viewHolder.menu.setText(pesanan.getLocation());

        Glide.with(context)
                .load(pesanan.getImage())
                .fitCenter()
                .into(viewHolder.imageView);

        viewHolder.buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DestinationDetailActivity.class);
                intent.putExtra("name", pesanan.getName());
                intent.putExtra("location", pesanan.getLocation());
                intent.putExtra("description", pesanan.getDescription());
                intent.putExtra("image", pesanan.getImage());
                intent.putExtra("lat", pesanan.getLatitude());
                intent.putExtra("long", pesanan.getLongitude());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, menu, uuid;
        private ImageView imageView;
        private  CardView buttonDetail;

        private ViewHolder(View itemView) {
            super(itemView);
            uuid = itemView.findViewById(R.id.txtUuid);
            name = itemView.findViewById(R.id.txtName);
            menu = itemView.findViewById(R.id.txtLocation);
            imageView = itemView.findViewById(R.id.image);
            buttonDetail = itemView.findViewById(R.id.cardItem);
        }
    }
}

