package com.qooke.contacts.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.qooke.contacts.MainActivity;
import com.qooke.contacts.Model.Contact;
import com.qooke.contacts.R;
import com.qooke.contacts.UpdateActivity;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    Context context;
    ArrayList<Contact> contactArrayList;

    public ContactAdapter(Context context, ArrayList<Contact> contactArrayList) {
        this.context = context;
        this.contactArrayList = contactArrayList;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);
        return new ContactAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contactArrayList.get(position);

        // 뷰에다가 보여주기
        holder.txtName.setText(contact.name);
        holder.txtPhone.setText(contact.phone);
    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;

        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 새로운 editActivity 실행한다.
                    Intent intent = new Intent(context, UpdateActivity.class);

                    // 1. index
                    // 2. 이름
                    // 3. 전화번호
                    int index = getAdapterPosition();
                    Contact contact = contactArrayList.get(index);

                    intent.putExtra("index", index);

                    // 데이터 바로 보내주기 -> updateActivity
                    intent.putExtra("contact", contact);

                    // 캐스팅
                    ((MainActivity)context).launcher.launch(intent);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showAlertDialog();

                }
            });

        }

        private void showAlertDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(false);
            builder.setTitle("주소록 삭제");
            builder.setMessage("정말 삭제하시겠습니까?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 몇번째 데이터를 선택했는지 알아야한다.
                    int index = getAdapterPosition();
                    //어레이리스트에서 삭제한다.
                    contactArrayList.remove(index);
                    // 화면에 보여준다.
                    notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("No", null);
            builder.show();
        }

    }
}
