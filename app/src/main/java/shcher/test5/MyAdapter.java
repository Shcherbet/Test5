package shcher.test5;


import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<RecyclerItem> listItems;
    private Context mContext;

    public MyAdapter(List<RecyclerItem> listItems, Context mContext) {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final RecyclerItem itemList = listItems.get(position);
        //final Re// itemList1 = listItems.get(position);

        holder.myTxtTitle.setText(itemList.getTitle());
        holder.myTxtDescription.setText(itemList.getDescription());
        holder.myTxtOptionDigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Diplay options menu

                PopupMenu popupMenu = new PopupMenu(mContext,holder.myTxtOptionDigit);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mnu_item_save:
                                Toast.makeText(mContext,"Save",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.mnu_item_delete:
                                // Del items
                                listItems.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(mContext,"Delete",Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView myTxtTitle;
        public TextView myTxtDescription;
        public ImageView myTxtOptionDigit;
        public ViewHolder(View itemView) {
            super(itemView);
            myTxtTitle = (TextView) itemView.findViewById(R.id.myTxtTitle);
            myTxtDescription = (TextView) itemView.findViewById(R.id.myTxtDescrip);
            myTxtOptionDigit = (ImageView) itemView.findViewById(R.id.myTxtOptionDigit);

        }
    }


}
