package com.example.uipractiseapp.rv_practise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uipractiseapp.R;
import com.squareup.picasso.Picasso;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedHolder> {

    @NonNull
    @Override
    public FeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView;
        rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent,false);
        return new FeedHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedHolder holder, int position) {
        //用户头像
        Picasso.with(holder.itemView.getContext())
                .load(getAvatarResId(position))
                .centerInside()
                .fit()
                .into(holder.mIvAvatar);

        //内容图片
        Picasso.with(holder.itemView.getContext())
                .load(getContentResId(position))
                .centerInside()
                .fit()
                .into(holder.mIvContent);

        //nickname
        holder.mTvNickname.setText("NetEase " + position);
    }

    private int getAvatarResId(int position){
        switch (position % 4){
            case 0:
                return R.drawable.avatar1;
            case 1:
                return R.drawable.avatar2;
            case 2:
                return R.drawable.avatar3;
            case 3:
                return R.drawable.avatar4;
        }
        return 0;
    }

    public int getContentResId(int position){
        switch (position % 4){
            case 0:
                return R.drawable.taeyeon_one;
            case 1:
                return R.drawable.taeyeon_two;
            case 2:
                return R.drawable.taeyeon_three;
            case 3:
                return R.drawable.taeyeon_four;
        }
        return 0;
    }


    @Override
    public int getItemCount() {
        return 100;
    }

    public class FeedHolder extends RecyclerView.ViewHolder{
        ImageView mIvAvatar;
        ImageView mIvContent;
        TextView mTvNickname;
        public FeedHolder(@NonNull View itemView) {
            super(itemView);
            mIvAvatar = itemView.findViewById(R.id.iv_avatar);
            mIvContent = itemView.findViewById(R.id.iv_content);
            mTvNickname = itemView.findViewById(R.id.tv_nickname);
        }
    }
}
