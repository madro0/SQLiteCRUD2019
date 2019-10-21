package com.example.crud.controller;


import android.os.Build;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.model.UserModel;

import java.util.List;

public class RecyclerViewListUsers extends RecyclerView.Adapter<RecyclerViewListUsers.Holder> {


    List<UserModel> listUsers;

    public RecyclerViewListUsers(List<UserModel> listUsers) {
        this.listUsers = listUsers;
    }

    public int idUser=-1;




    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_list_users, parent, false);
        RecyclerViewListUsers.Holder holder = new RecyclerViewListUsers.Holder(v);


        return holder;



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        holder.Name.setText(listUsers.get(position).getName());
        holder.Email.setText(listUsers.get(position).getEmail());
        holder.Id.setText(""+listUsers.get(position).getId());


        //idUser=listUsers.get(position).getId();



        /*holder.itemView.setOnContextClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public class Holder  extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private TextView Name, Email,Id;
        private CardView cardView;

        public Holder(@NonNull View itemView) {
            super(itemView);

            Name = (TextView) itemView.findViewById(R.id.textFullName);
            Email = (TextView) itemView.findViewById(R.id.textEmail);
            Id = (TextView) itemView.findViewById(R.id.textId);
            cardView=itemView.findViewById(R.id.cardview1);
            cardView.setOnCreateContextMenuListener(this);



        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(listUsers.get(this.getAdapterPosition()).getId(),0,0,"Update");
            menu.add(listUsers.get(this.getAdapterPosition()).getId(),1,1,"Delete");


        }


    }



}
