package lv.groupOne.linkedinhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import lv.groupOne.linkedinhelper.R;
import lv.groupOne.linkedinhelper.data.model.ParseItemModel;

public class ParseItemAdapter extends RecyclerView.Adapter<ParseItemAdapter.ViewHolder> {

    private ArrayList<ParseItemModel> parseItemModelArrayList;
    private Context context;

    public ParseItemAdapter(ArrayList<ParseItemModel> parseItemModelArrayList, Context context) {
        this.parseItemModelArrayList = parseItemModelArrayList;
        this.context = context;
    }

    @Override
    public ParseItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseItemAdapter.ViewHolder holder, int position) {
        ParseItemModel parseItemModel = parseItemModelArrayList.get(position);
        holder.title_txt.setText(parseItemModel.getTitle());
        holder.role_txt.setText(parseItemModel.getRole());
        holder.metadata_txt.setText(parseItemModel.getMetadata());
        Picasso.get().load(parseItemModel.getImageLink()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return parseItemModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            ImageView imageView;
            TextView title_txt,role_txt,metadata_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_id);
            title_txt = itemView.findViewById(R.id.title_txt_id);
            role_txt = itemView.findViewById(R.id.role_txt_id);
            metadata_txt = itemView.findViewById(R.id.metadata_txt_id);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
