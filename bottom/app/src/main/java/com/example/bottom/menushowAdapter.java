package com.example.bottom;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class menushowAdapter extends RecyclerView.Adapter<menushowAdapter.ViewHolder> {
    private List<menushowContact> mLIST;

    public menushowAdapter(List<menushowContact> itemList, Context ctx){
                this.mLIST=itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.menushow_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.mIMG.setImageResource(R.drawable.ic_launcher_background);
        holder.mID.setText(mLIST.get(position).getId1());
        holder.mTEL.setText(mLIST.get(position).getTel());
        holder.mDAT.setText(mLIST.get(position).getEatdate());
        holder.mWHEN.setText(mLIST.get(position).getWhen());
        holder.mDISH1.setText(mLIST.get(position).getDish1());
        holder.mDISH2.setText(mLIST.get(position).getDish2());
        holder.mDISH3.setText(mLIST.get(position).getDish3());
        holder.mDISH4.setText(mLIST.get(position).getDish4());
        holder.mDISH5.setText(mLIST.get(position).getDish5());

        Log.d("CCC=", mLIST.get(position).getDish1());
    }

    @Override
    public int getItemCount() {
        return mLIST.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //public ImageView mIMG;
        public TextView mID, mTEL,mDAT,mWHEN,mDISH1,mDISH2,mDISH3,mDISH4,mDISH5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //mIMG=(ImageView)itemView.findViewById(R.id.image00);

            mID=(TextView)itemView.findViewById(R.id.id1);
            mTEL=(TextView)itemView.findViewById(R.id.tel);
            mDAT=(TextView)itemView.findViewById(R.id.whatdate);
            mWHEN=(TextView)itemView.findViewById(R.id.when);
            mDISH1=(TextView)itemView.findViewById(R.id.dish1);
            mDISH2=(TextView)itemView.findViewById(R.id.dish2);
            mDISH3=(TextView)itemView.findViewById(R.id.dish3);
            mDISH4=(TextView)itemView.findViewById(R.id.dish4);
            mDISH5=(TextView)itemView.findViewById(R.id.dish5);




        }


    }
    public void setItemList(List<menushowContact> itemList) {
        this.mLIST=itemList;
    }

}
