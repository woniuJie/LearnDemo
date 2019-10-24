package com.zsj.learndemo.snap;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zsj.learndemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshijie on 2019/6/5;
 */
public class SnapAdapter extends RecyclerView.Adapter<SnapAdapter.SnapViewHolder> {

    private List<String> list = new ArrayList<>();

    public SnapAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SnapViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_snap, viewGroup, false);
        return new SnapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SnapViewHolder snapViewHolder, int i) {
        switch (i % 2) {
            case 0:
                snapViewHolder.imageView.setBackgroundColor(Color.BLUE);
                break;
            case 1:
                snapViewHolder.imageView.setBackgroundColor(Color.YELLOW);
                break;
            default:
                snapViewHolder.imageView.setBackgroundColor(Color.RED);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SnapViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        public SnapViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_snap);
        }
    }
}
