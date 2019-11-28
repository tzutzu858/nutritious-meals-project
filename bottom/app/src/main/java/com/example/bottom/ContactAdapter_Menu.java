package com.example.bottom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter_Menu extends RecyclerView.Adapter<ContactAdapter_Menu.ViewHolder> {
    private LayoutInflater myInflater2;
    private List<Contact_Menu> itemList2;
//    public ContactAdapter_Menu(List<Contact_Menu> menuList) {
////        this.menuList = menuList;
////    }

private List<Contact_Menu> menuList;

    public ContactAdapter_Menu(List<Contact_Menu> itemList2, Context ctx) {

        this.menuList = itemList2;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.breakfast.setText(menuList.get(position).getBreakfast());
        holder.lunch.setText(menuList.get(position).getLunch());
        holder.dinner.setText(menuList.get(position).getDinner());
        holder.date.setText(menuList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView date,breakfast, lunch, dinner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            breakfast=itemView.findViewById(R.id.breakfast);
            lunch=itemView.findViewById(R.id.lunch);
            dinner=itemView.findViewById(R.id.dinner);


        }
    }
    public void setItemList(List<Contact_Menu> itemList) {
        this.menuList = itemList;
    }
}
