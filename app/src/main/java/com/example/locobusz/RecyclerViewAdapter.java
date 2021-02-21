package com.example.locobusz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private LayoutInflater inflater;

    private Context context;
    private List<Item> itemList;

    public RecyclerViewAdapter(Context context, List<Item> itemList){
        this.context=context;
        this.itemList=itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_show,viewGroup,false);
        return new ViewHolder(view,context);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int position) {
        Item item = itemList.get(position);

        viewholder.nameDetail.setText(item.getName());
        viewholder.rollNumberDetail.setText(String.valueOf(item.getRollNo()));
        viewholder.department.setText(item.getDepartment());

        if(item.getDepartment().equals("CSE")){
            viewholder.cardView.getBackground().setTint(Color.parseColor("#6D7B8D")); }
        else if(item.getDepartment().equals("IT")){
            viewholder.cardView.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));}
        else if(item.getDepartment().equals("EEE")){
            viewholder.cardView.setCardBackgroundColor(Color.parseColor("#BCC6CC"));}
        else if(item.getDepartment().equals("ECE")){
            viewholder.cardView.setCardBackgroundColor(Color.parseColor("#1569C7"));}
        else if(item.getDepartment().equals("ME")){
            viewholder.cardView.setCardBackgroundColor(Color.parseColor("#6960EC"));}
        else if(item.getDepartment().equals("BCA")){
            viewholder.cardView.setCardBackgroundColor(Color.parseColor("#C2DFFF"));}
        else if(item.getDepartment().equals("BCOM")){
            viewholder.cardView.setCardBackgroundColor(Color.parseColor("#ffde03"));}
        else if(item.getDepartment().equals("BBA")){
            viewholder.cardView.setCardBackgroundColor(Color.parseColor("#438D80"));}
        else if(item.getDepartment().equals("LLB")){
            viewholder.cardView.setCardBackgroundColor(Color.parseColor("#4E9258"));}


        viewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ShowAllDetail.class);
                intent.putExtra("name",item.getName());
                intent.putExtra("rollNo",String.valueOf(item.getRollNo()));
                intent.putExtra("department",item.getDepartment());
                intent.putExtra("departmentCode",String.valueOf(item.getDepartmentCode()));
                intent.putExtra("dateOfBirth",item.getDateOfBirth());
                intent.putExtra("gender",item.getGender());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameDetail;
        public TextView rollNumberDetail;
        public TextView department;
        public CardView cardView;
//        public ImageButton deleteButton;
//        public ImageButton editButton;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context=ctx;

            cardView=itemView.findViewById(R.id.mainCardViewAdapter);
            nameDetail=itemView.findViewById(R.id.nameDetail);
            rollNumberDetail=itemView.findViewById(R.id.rollNoDetail);
            department=itemView.findViewById(R.id.departmentDetail);
//            sizeDetail=itemView.findViewById(R.id.sizeDetail);

//            deleteButton=itemView.findViewById(R.id.deleteButton);
//            deleteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position=getAdapterPosition();
//                    Item item=itemList.get(position);
//                    deleteItem(item.getId());
//                }
//            });
//
//            editButton=itemView.findViewById(R.id.editButton);
//            editButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position=getAdapterPosition();
//                    Item item=itemList.get(position);
//                    editItem(item.getId());
//                }
//            });

        }

//        private void deleteItem(final int id) {
//            builder=new AlertDialog.Builder(context);
//            inflater=LayoutInflater.from(context);
//            View view=inflater.inflate(R.layout.delete_popup,null);
//
//            Button yesButton=view.findViewById(R.id.yesButton);
//            Button noButton=view.findViewById(R.id.noButton);
//
//            builder.setView(view);
//            dialog=builder.create();
//            dialog.show();
//
//            yesButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    DatabaseHandler db=new DatabaseHandler(context);
//                    Item item=db.getDetails(id);
//                    db.deleteContact(item);
//                    itemList.remove(getAdapterPosition());
//                    notifyItemRemoved(getAdapterPosition());
//
//                    dialog.dismiss();
//                }
//            });
//            noButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialog.dismiss();
//                }
//            });
//        }

//        private void editItem(int id){
//            builder=new AlertDialog.Builder(context);
//            inflater=LayoutInflater.from(context);
//            View view=inflater.inflate(R.layout.edit_popup,null);
//
//            final EditText itemName=view.findViewById(R.id.item1);
//            final EditText itemQuantity=view.findViewById(R.id.quantity1);
//            final EditText itemColor=view.findViewById(R.id.color1);
//            final EditText itemSize=view.findViewById(R.id.size1);
//            Button saveButton=view.findViewById(R.id.saveButton1);
//            ImageButton cancelButton=view.findViewById(R.id.cancelButton);
//
//
//            final Item item=itemList.get(getAdapterPosition());
//            itemName.setText(item.getName());
//            itemQuantity.setText(String.valueOf(item.getQuantity()));
//            itemColor.setText(item.getColor());
//            itemSize.setText(String.valueOf(item.getSize()));
//
//            builder.setView(view);
//            dialog=builder.create();
//            dialog.show();
//
//            saveButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    DatabaseHandler db=new DatabaseHandler(context);
//
//                    String name=itemName.getText().toString().trim();
//                    String color=itemColor.getText().toString().trim();
//                    int size=Integer.parseInt(itemSize.getText().toString().trim());
//                    int quantity=Integer.parseInt(itemQuantity.getText().toString().trim());
//
//                    item.setName(name);
//                    item.setColor(color);
//                    item.setQuantity(quantity);
//                    item.setSize(size);
//
//                    db.updateDetails(item);
//                    notifyItemChanged(getAdapterPosition(),item);
//                    dialog.dismiss();
//                }
//            });
//            cancelButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialog.dismiss();
//                }
//            });
//        }

    }
}
