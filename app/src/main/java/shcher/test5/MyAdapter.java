package shcher.test5;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


public class MyAdapter extends SectionedRecyclerViewAdapter<RecyclerView.ViewHolder> {
        //RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<RecyclerItem> listItems;
    private List<DataMeet> allData;
    private Context mContext;
    DBHelper dbHelper;
    SQLiteDatabase db;


    public MyAdapter(List<DataMeet> allData, Context mContext) {
        this.allData = allData;
        this.mContext = mContext;
    }



    @Override
    public int getSectionCount() {
        return allData.size();
    }

    @Override
    public int getItemCount(int meetcount) {
        return allData.get(meetcount).getAllMeetTime().size();
    }


    @Override
    public void onBindHeaderViewHolder(final RecyclerView.ViewHolder holder, final int meetcount) {

        final String curDate = allData.get(meetcount).getDateList();
        final SectionViewHolder dateViewHolder = (SectionViewHolder) holder;
        dateViewHolder.myTxtTitle.setText(curDate);
        dbHelper = new DBHelper(mContext);
        db = dbHelper.getWritableDatabase();


        float myTxtSize;

        SimpleDateFormat myFt = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date tecDate2 = null;
        try {
            tecDate2 = myFt.parse(curDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long qwe = tecDate2.getTime();
        long qwe2 = qwe+(24 * 60 * 60 * 1000);
        //Log.d("mLog"," tecDate2: " + tecDate2 + " qwe "+ qwe);


        String sqlQuery = "select _id, name, date, date2  from list where date>=? and date<? ";
        Cursor cView;
        cView = db.rawQuery(sqlQuery, new String[]{String.valueOf(qwe),String.valueOf(qwe2)});
        myTxtSize = mContext.getResources().getDimension(R.dimen.text_style_Small);

        if (cView.moveToFirst()){
                myTxtSize = mContext.getResources().getDimension(R.dimen.text_style_Large);
        }
        cView.close();

        dateViewHolder.myTxtTitle.setTextSize(myTxtSize);
        dateViewHolder.myTxtOptionDigit.setTextSize(myTxtSize);

        dateViewHolder.myTxtOptionDigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ContentValues contentValues = new ContentValues();
                            PopupMenu popupMenu = new PopupMenu(mContext,dateViewHolder.myTxtOptionDigit);
                            popupMenu.inflate(R.menu.option_menu);
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {

                                    //float myTxtSize;
                                    Date startDay = new Date();
                                    long startDayLong =  startDay.getTime();
                                    //int minD =  mContext.getResources().getInteger(R.integer.minDays);
                                 //   startDayLong = startDayLong+((meetcount)*(24 * 60 * 60 * 1000));

                                    //startDay.add(Calendar.DAY_OF_YEAR,position-minD);
                                    //int days = (int) (minD / (24 * 60 * 60 * 1000));
                                    SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
                                 //   SimpleDateFormat format2 = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                                    //long stD = startDay.getTime();
                                    //long stMil = stD.getTime();
                                    //myTxtSize = mContext.getResources().getDimension(R.dimen.text_style_Large);
                                    //holder.myTxtTitle.setTextSize(myTxtSize);
                                    //holder.myTxtOptionDigit.setTextSize(myTxtSize);
                                    SimpleDateFormat myFt = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                                    Date tecDate2 = null;
                                    try {
                                        tecDate2 = myFt.parse(curDate);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    long qwe = tecDate2.getTime();
                                    long qwe2 = qwe+(24 * 60 * 60 * 1000);


                                    switch (item.getItemId()){
                                        case R.id.mnu_item_add:
                                            Date tecDate = new Date(startDayLong);
                                            contentValues.put(DBHelper.KEY_NAME, "STAAS");
                                            contentValues.put(DBHelper.KEY_DATE, qwe ); //startDayLong startDayLong
                                            contentValues.put(DBHelper.KEY_DATE2, (format1.format(tecDate.getTime())) ); //(format1.format(tecDate.getTime()))
                                            //Log.d("mLog"," ID = " + contentValues);
                                            db.insert(DBHelper.TABLE_LIST,null,contentValues);
                                            //myTxtSize = mContext.getResources().getDimension(R.dimen.text_style_Large);
                                            //dateViewHolder.myTxtTitle.setTextSize(myTxtSize);
                                            //dateViewHolder.myTxtOptionDigit.setTextSize(myTxtSize);

                                            Toast.makeText(mContext,"Add " + (format1.format(startDay.getTime()))+ " long " + startDayLong + " дата"+ (format1.format(tecDate.getTime())),Toast.LENGTH_SHORT).show();
                                            break;
                                        case R.id.mnu_item_view:
                                            // View items
                                            Cursor c;
                                            //SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                                            //startDayLong = startDayLong-(24 * 60 * 60 * 1000);
                                            Date as1 = new Date(qwe);
                                            Date as2 = new Date(qwe2);
                                            String sqlQuery = "select _id, name, date, date2  from list where date between ? and ? ";

                                            c = db.rawQuery(sqlQuery, new String[]{String.valueOf(qwe),String.valueOf(qwe2)});

                                            if (c.moveToFirst()){
                                                int idIndex = c.getColumnIndex(DBHelper.KEY_ID);
                                                int nameIndex = c.getColumnIndex(DBHelper.KEY_NAME);
                                                int dateIndex = c.getColumnIndex(DBHelper.KEY_DATE);
                                                int date2Index = c.getColumnIndex(DBHelper.KEY_DATE2);
                                                    do {
                                                        Log.d("mLog"," ID = " + c.getInt(idIndex)
                                                                + ", name - " + c.getString(nameIndex)
                                                                + ", date = " +c.getString(dateIndex)
                                                                + ", date2 = " +c.getString(date2Index));
                                                    } while (c.moveToNext());
                                            } else  Log.d("mLog"," 0 rows ");

                                            c.close();
                                            Toast.makeText(mContext,"View" + String.valueOf(as1)+ " date2 "+String.valueOf(as2),Toast.LENGTH_LONG).show();
                                            break;
                                        case R.id.mnu_item_delete:
                                            // Del items
                                            db.delete(DBHelper.TABLE_LIST,null,null);
                                            notifyDataSetChanged();
                                            Toast.makeText(mContext,"Delete",Toast.LENGTH_SHORT).show();
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, boolean header) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        //return  RecyclerView.ViewHolder (v);
        View v = null;
        if (header)

        {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item, parent, false);
            return new SectionViewHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_meet, parent, false);
            return new ItemViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int section, int relativePosition, int absolutePosition) {

        ArrayList<String> meetInDate = allData.get(section).getAllMeetTime();
        String meetName = meetInDate.get(relativePosition);
        ItemViewHolder meetTime = (ItemViewHolder) holder;
        meetTime.myTxtTimeS.setText(meetName);


    }

    //    final RecyclerItem itemList = listItems.get(position);
    //    dbHelper = new DBHelper(mContext);
    //    db = dbHelper.getWritableDatabase();
//
    //    holder.myTxtTitle.setText(itemList.getTitle());
    //    Cursor cView;
    //    float myTxtSize;
    //    Date startDay = new Date();
    //    long startDayLong =  startDay.getTime();
    //    int minD =  mContext.getResources().getInteger(R.integer.minDays);
    //    int cNumber = Integer.valueOf(itemList.getNumber());
//
    //    startDayLong = startDayLong+((cNumber-minD)*(24 * 60 * 60 * 1000));
    //    SimpleDateFormat format2 = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
//
    //    String currentDate = format2.format(startDayLong);
    //    Date tecDate2 = null;
    //    try {
    //        tecDate2 = format2.parse(currentDate);
    //    } catch (ParseException e) {
    //        e.printStackTrace();
    //    }
    //    long qwe = tecDate2.getTime();
    //    long qwe2 = qwe+(24 * 60 * 60 * 1000);
    //    //Date as1 = new Date(qwe);
    //    //Date as2 = new Date(qwe2);
//
    //    String sqlQuery = "select _id, name, date, date2  from list where date>? and date<? ";
    //    cView = db.rawQuery(sqlQuery, new String[]{String.valueOf(qwe),String.valueOf(qwe2)});
    //    myTxtSize = mContext.getResources().getDimension(R.dimen.text_style_Small);
    //    //holder.reList.setVisibility(View.GONE);
//
    //    if (cView.moveToFirst()){
//
    //        myTxtSize = mContext.getResources().getDimension(R.dimen.text_style_Large);
    //        //LinearLayout linearLayout = new LinearLayout(mContext);
    //        //linearLayout.setOrientation(LinearLayout.VERTICAL);
    //        //RelativeLayout relativeLayout = new RelativeLayout(mContext);
    //        //holder.reList.setVisibility(View.VISIBLE);
    //        /*LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    //        TextView myTxtList1 = new TextView(mContext);
    //        TextView myTxtList2 = new TextView(mContext);
    //        myTxtList1.setText(format2.format(as1.getTime())+" position "+position +" cNumber "+cNumber);//(format1.format(startDay.getTime()))
    //        myTxtList2.setText(format2.format(as2.getTime())+" position "+position +" cNumber "+cNumber);
    //        lParams.setMargins(16,16,16,16);
//
    //        */
    //        //holder.reList.addView(myTxtList1,lParams);
    //        //holder.reList.addView(myTxtList2,lParams);
//
//
    //    }
    //    cView.close();
//
    //    holder.myTxtTitle.setTextSize(myTxtSize);
    //    holder.myTxtOptionDigit.setTextSize(myTxtSize);
//
    //    holder.myTxtOptionDigit.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View v) {
//
    //            final ContentValues contentValues = new ContentValues();
    //            PopupMenu popupMenu = new PopupMenu(mContext,holder.myTxtOptionDigit);
    //            popupMenu.inflate(R.menu.option_menu);
    //            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
    //                @Override
    //                public boolean onMenuItemClick(MenuItem item) {
//
    //                    float myTxtSize;
    //                    Date startDay = new Date();
    //                    long startDayLong =  startDay.getTime();
    //                    int minD =  mContext.getResources().getInteger(R.integer.minDays);
    //                    startDayLong = startDayLong+((position-minD)*(24 * 60 * 60 * 1000));
//
    //                    //startDay.add(Calendar.DAY_OF_YEAR,position-minD);
    //                    //int days = (int) (minD / (24 * 60 * 60 * 1000));
    //                    SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
    //                    SimpleDateFormat format2 = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    //                    //long stD = startDay.getTime();
    //                    //long stMil = stD.getTime();
    //                    //myTxtSize = mContext.getResources().getDimension(R.dimen.text_style_Large);
    //                    //holder.myTxtTitle.setTextSize(myTxtSize);
    //                    //holder.myTxtOptionDigit.setTextSize(myTxtSize);
//
    //                    switch (item.getItemId()){
    //                        case R.id.mnu_item_add:
    //                            Date tecDate = new Date(startDayLong);
    //                            contentValues.put(DBHelper.KEY_NAME, "STAAS");
    //                            contentValues.put(DBHelper.KEY_DATE, startDayLong ); //startDayLong
    //                            contentValues.put(DBHelper.KEY_DATE2, (format1.format(tecDate.getTime())) );
    //                            db.insert(DBHelper.TABLE_LIST,null,contentValues);
    //                            myTxtSize = mContext.getResources().getDimension(R.dimen.text_style_Large);
    //                            holder.myTxtTitle.setTextSize(myTxtSize);
    //                            holder.myTxtOptionDigit.setTextSize(myTxtSize);
//
    //                            Toast.makeText(mContext,"Add " + (format1.format(startDay.getTime()))+ " long " + startDayLong + " дата"+ (format1.format(tecDate.getTime())),Toast.LENGTH_SHORT).show();
    //                            break;
    //                        case R.id.mnu_item_view:
    //                            // View items
    //                            Cursor c;
    //                            //SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    //                            //startDayLong = startDayLong-(24 * 60 * 60 * 1000);
    //                            String currentDate = format2.format(startDayLong);
    //                            Date tecDate2 = null;
    //                            try {
    //                                tecDate2 = format2.parse(currentDate);
    //                            } catch (ParseException e) {
    //                                e.printStackTrace();
    //                            }
    //                            long qwe = tecDate2.getTime();
    //                            long qwe2 = qwe+(24 * 60 * 60 * 1000);
    //                            Date as1 = new Date(qwe);
    //                            Date as2 = new Date(qwe2);
    //                            String sqlQuery = "select _id, name, date, date2  from list where date between ? and ? ";
//
    //                            c = db.rawQuery(sqlQuery, new String[]{String.valueOf(qwe),String.valueOf(qwe2)});
//
    //                            if (c.moveToFirst()){
    //                                int idIndex = c.getColumnIndex(DBHelper.KEY_ID);
    //                                int nameIndex = c.getColumnIndex(DBHelper.KEY_NAME);
    //                                int dateIndex = c.getColumnIndex(DBHelper.KEY_DATE);
    //                                int date2Index = c.getColumnIndex(DBHelper.KEY_DATE2);
    //                                    do {
    //                                        Log.d("mLog"," ID = " + c.getInt(idIndex)
    //                                                + ", name - " + c.getString(nameIndex)
    //                                                + ", date = " +c.getString(dateIndex)
    //                                                + ", date2 = " +c.getString(date2Index));
    //                                    } while (c.moveToNext());
    //                            } else  Log.d("mLog"," 0 rows ");
//
    //                            c.close();
    //                            Toast.makeText(mContext,"View" + String.valueOf(as1)+ " date2 "+String.valueOf(as2),Toast.LENGTH_LONG).show();
    //                            break;
    //                        case R.id.mnu_item_delete:
    //                            // Del items
    //                            db.delete(DBHelper.TABLE_LIST,null,null);
    //                            notifyDataSetChanged();
    //                            Toast.makeText(mContext,"Delete",Toast.LENGTH_SHORT).show();
    //                            break;
    //                        default:
    //                            break;
    //                    }
    //                    return false;
    //                }
    //            });
    //            popupMenu.show();
    //        }
    //    });
    //}

    /*@Override
    public int getItemCount() {
        return listItems.size();
    }
    */

    /*
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView myTxtTitle;
        public TextView myTxtOptionDigit;
        //public LinearLayout reList;
        /*
        public TextView myTxtDescription;
        public TextView myTxtDescription2;
        public TextView myTxtTitleL;
        public TextView myTxtDescriptionM;
        public TextView myTxtDescriptionS;
        public TextView myTxtOptionDigit;
        public ImageView myTxtOptionDigit;
        */
    /*
        public ViewHolder(View itemView) {
            super(itemView);
            myTxtTitle = (TextView) itemView.findViewById(R.id.myTxtTitle);
            myTxtOptionDigit = (TextView) itemView.findViewById(R.id.myTxtOptionDigit);
            //reList =(LinearLayout) itemView.findViewById(R.id.myRelList);
            //holder.            myTxtTitle.setTextSize(mContext,R.dimen.text_style_Large);
            /*
            myTxtDescription = (TextView) itemView.findViewById(R.id.myTxtDescrip);
            myTxtTitleL = (TextView) itemView.findViewById(R.id.myTxtTitleL);
            myTxtDescription2 = (TextView) itemView.findViewById(R.id.myTxtDescrip2);
            myTxtDescriptionM = (TextView) itemView.findViewById(R.id.myTxtDescripM);
            myTxtDescriptionS = (TextView) itemView.findViewById(R.id.myTxtDescripS);
            myTxtOptionDigit = (ImageView) itemView.findViewById(R.id.myTxtOptionDigit);

            */
/*
        }
    }

    */
    public static class SectionViewHolder extends RecyclerView.ViewHolder {



        final TextView myTxtTitle;
        final TextView myTxtOptionDigit;

        public SectionViewHolder(View itemView) {
            super(itemView);

            myTxtTitle = (TextView) itemView.findViewById(R.id.myTxtTitle);
            myTxtOptionDigit = (TextView) itemView.findViewById(R.id.myTxtOptionDigit);


        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        final TextView myTxtTimeS;

        final TextView myTxtTimeE;


        public ItemViewHolder(View itemView) {
            super(itemView);
            myTxtTimeS = (TextView) itemView.findViewById(R.id.myTxtTimeS);
            myTxtTimeE = (TextView) itemView.findViewById(R.id.myTxtTimeE);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), myTxtTimeS.getText(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

}

///Пример проверки
/*
if (str == null || str.isEmpty()) {
  complainAboutUnusableString();
} else {
  doSomethingWith(str.charAt(0));
}

if (str != null && !str.isEmpty()) {
  doSomethingWith(str.charAt(0));
}
 */