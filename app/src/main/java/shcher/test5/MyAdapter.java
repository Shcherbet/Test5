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
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<RecyclerItem> listItems;
    private Context mContext;
    DBHelper dbHelper;
    SQLiteDatabase db;

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
        dbHelper = new DBHelper(mContext);
        db = dbHelper.getWritableDatabase();



        //final Re// itemList1 = listItems.get(position);

        holder.myTxtTitle.setText(itemList.getTitle());
        //holder.myTxtTitleL.setText(itemList.getTitle());
        //holder.myTxtTitle.setTextAppearance(R.style.TextAppearance_AppCompat_Small);
        //holder.myTxtDescription.setText(itemList.getDescription());
        //holder.myTxtDescription.setTextSize(mContext.getResources().getDimension(R.dimen.text_style_Medium));
        //holder.myTxtDescription2.setText(itemList.getDescription());
        //holder.myTxtDescription2.setTextSize(mContext.getResources().getDimension(R.dimen.text_style_Small));
        //holder.myTxtDescriptionM.setText(itemList.getDescription());
        //holder.myTxtDescriptionS.setText(itemList.getDescription());
        Cursor cView;
        float myTxtSize;
        Date startDay = new Date();
        long startDayLong =  startDay.getTime();
        int minD =  mContext.getResources().getInteger(R.integer.minDays);
        startDayLong = startDayLong+((position-minD)*(24 * 60 * 60 * 1000));
        SimpleDateFormat format2 = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

        String currentDate = format2.format(startDayLong);
        Date tecDate2 = null;
        try {
            tecDate2 = format2.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long qwe = tecDate2.getTime();
        long qwe2 = qwe+(24 * 60 * 60 * 1000);
        Date as1 = new Date(qwe);
        Date as2 = new Date(qwe2);

        String sqlQuery = "select _id, name, date, date2  from list where date between ? and ? ";
        cView = db.rawQuery(sqlQuery, new String[]{String.valueOf(qwe),String.valueOf(qwe2)});

        if (cView.moveToFirst()){

            myTxtSize = mContext.getResources().getDimension(R.dimen.text_style_Large);

        } else {
            myTxtSize = mContext.getResources().getDimension(R.dimen.text_style_Small);

        }
        cView.close();

        holder.myTxtTitle.setTextSize(myTxtSize);
        holder.myTxtOptionDigit.setTextSize(myTxtSize);




        holder.myTxtOptionDigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Diplay options menu
                //dbHelper = new DBHelper(mContext);
                //db = dbHelper.getWritableDatabase();
                //db.execSQL("ALTER TABLE tngLenta ADD COLUMN datest TEXT;");
                //db.execSQL("drop table if exists tngLenta");
                /*
                db.execSQL("create table " + dbHelper.TABLE_LIST + "(" + dbHelper.KEY_ID + " integer primary key, "
                + dbHelper.KEY_NAME + " TEXT, "
                + dbHelper.KEY_DATE + " DATE "
                + dbHelper.KEY_DATE2 + " TEXT " + ")");*/

                // подключение к БД
                //dbHelper.onCreate();
                //dbHelper.onUpgrade( db,2,3);
                //db.execSQL("drop table if exists tngList");
                final ContentValues contentValues = new ContentValues();



                PopupMenu popupMenu = new PopupMenu(mContext,holder.myTxtOptionDigit);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        float myTxtSize;
                        Date startDay = new Date();
                        long startDayLong =  startDay.getTime();
                        int minD =  mContext.getResources().getInteger(R.integer.minDays);
                        startDayLong = startDayLong+((position-minD)*(24 * 60 * 60 * 1000));

                        //startDay.add(Calendar.DAY_OF_YEAR,position-minD);
                        //int days = (int) (minD / (24 * 60 * 60 * 1000));
                        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
                        SimpleDateFormat format2 = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                        //long stD = startDay.getTime();
                        //long stMil = stD.getTime();
                        //myTxtSize = mContext.getResources().getDimension(R.dimen.text_style_Large);
                        //holder.myTxtTitle.setTextSize(myTxtSize);
                        //holder.myTxtOptionDigit.setTextSize(myTxtSize);

                        switch (item.getItemId()){
                            case R.id.mnu_item_add:
                                Date tecDate = new Date(startDayLong);
                                contentValues.put(DBHelper.KEY_NAME, "STAAS");
                                contentValues.put(DBHelper.KEY_DATE, startDayLong ); //startDayLong
                                contentValues.put(DBHelper.KEY_DATE2, (format1.format(tecDate.getTime())) );
                                db.insert(DBHelper.TABLE_LIST,null,contentValues);
                                myTxtSize = mContext.getResources().getDimension(R.dimen.text_style_Large);
                                holder.myTxtTitle.setTextSize(myTxtSize);
                                holder.myTxtOptionDigit.setTextSize(myTxtSize);

                                Toast.makeText(mContext,"Add " + (format1.format(startDay.getTime()))+ " long " + startDayLong + " дата"+ (format1.format(tecDate.getTime())),Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.mnu_item_view:
                                // View items
                                Cursor c;
                                //SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                                //startDayLong = startDayLong-(24 * 60 * 60 * 1000);
                                String currentDate = format2.format(startDayLong);
                                Date tecDate2 = null;
                                try {
                                    tecDate2 = format2.parse(currentDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                long qwe = tecDate2.getTime();
                                long qwe2 = qwe+(24 * 60 * 60 * 1000);
                                Date as1 = new Date(qwe);
                                Date as2 = new Date(qwe2);
                                //String currentDate2 = format2.format(startDayLong2);
                                //String dayView = format2.format(startDay.getTime());
                                //Array arDayview = new String[] {"4444"};
                                //c = db.query(DBHelper.TABLE_LIST, null,null,null,null,null,null);
                                //String sqlQuery = "select * from table_list where date = ?";
                                //mDb.rawQuery(SELECT _id, time from t1 where time between ? and ?, where);
                                String sqlQuery = "select _id, name, date, date2  from list where date between ? and ? ";


                                c = db.rawQuery(sqlQuery, new String[]{String.valueOf(qwe),String.valueOf(qwe2)});
                                //c =db.query(DBHelper.TABLE_LIST, null,  new String("DATE < ?"), new String[]{String.valueOf(startDayLong)},null,null,null );
                                //(sqlQuery,startDay );
                                //Log.d("mLog"," cursor: " + c);

                               // contentValues.put(DBHelper.KEY_NAME, "STAAS");
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
                                //db.execSQL("drop table if exists " + DBHelper.TABLE_LIST);
                                //listItems.remove(position);
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
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView myTxtTitle;
        public TextView myTxtOptionDigit;
        /*
        public TextView myTxtDescription;
        public TextView myTxtDescription2;
        public TextView myTxtTitleL;
        public TextView myTxtDescriptionM;
        public TextView myTxtDescriptionS;
        public TextView myTxtOptionDigit;
        public ImageView myTxtOptionDigit;
        */

        public ViewHolder(View itemView) {
            super(itemView);
            myTxtTitle = (TextView) itemView.findViewById(R.id.myTxtTitle);
            myTxtOptionDigit = (TextView) itemView.findViewById(R.id.myTxtOptionDigit);
            //holder.            myTxtTitle.setTextSize(mContext,R.dimen.text_style_Large);
            /*
            myTxtDescription = (TextView) itemView.findViewById(R.id.myTxtDescrip);
            myTxtTitleL = (TextView) itemView.findViewById(R.id.myTxtTitleL);
            myTxtDescription2 = (TextView) itemView.findViewById(R.id.myTxtDescrip2);
            myTxtDescriptionM = (TextView) itemView.findViewById(R.id.myTxtDescripM);
            myTxtDescriptionS = (TextView) itemView.findViewById(R.id.myTxtDescripS);
            myTxtOptionDigit = (ImageView) itemView.findViewById(R.id.myTxtOptionDigit);

            */

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