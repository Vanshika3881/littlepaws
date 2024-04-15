package com.example.littlepaws;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Model> mList;
    private Context context;

    public MyAdapter(Context context, ArrayList<Model> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mList.get(position);

        Glide.with(context).load(model.getImageUrl()).into(holder.imageView);
        holder.descriptionTextView.setText(model.getDescription());
        holder.addressTextview.setText(model.getAddress());
        holder.contactTextview.setText(model.getContact());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView descriptionTextView;
        TextView addressTextview;
        TextView contactTextview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_imageView);
            descriptionTextView = itemView.findViewById(R.id.item_description);
            addressTextview=itemView.findViewById(R.id.item_address);
            contactTextview=itemView.findViewById(R.id.item_contact);
        }
    }
}