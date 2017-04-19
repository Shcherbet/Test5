package shcher.test5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
     MyAdapter adapter;
     List<RecyclerItem> listItems;
     Date d;
    //private int minD = (int) this.getResources().getInteger(R.integer.minDays);
    //private int maxD = (int) this.getResources().getInteger(R.integer.maxDays);
    //private int asd;
    //Calendar maxDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        d = new Date();
        //maxDay = new GregorianCalendar();
        //maxDay.add(Calendar.YEAR,1);
        //long duration = maxDay.getTimeInMillis()-d.getTime();
        //int days = (int) (duration / (24 * 60 * 60 * 1000));
        //duration.setTime(maxDay.getTime()-d.getTime());
        //Calendar maxDay = new GregorianCalendar();
        //maxDay.add(Calendar.YEAR,1);
        //SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMMM", Locale.getDefault());
        int minD =  this.getResources().getInteger(R.integer.minDays);
        int maxD =  this.getResources().getInteger(R.integer.maxDays);

        //Generate sample data
        for (int i = 0; i < maxD; i++) {
            //RecyclerItem item = new RecyclerItem("Item " + (i + 1),"Welcome to new traning, this is desc of item " + (i+1)); maxDay.get(Calendar.DAY_OF_YEAR
            Calendar calDay = new GregorianCalendar();
            calDay.add(Calendar.DAY_OF_YEAR,i-minD);

            listItems.add(new RecyclerItem("Date " + (format2.format(calDay.getTime())) ,"Date " +(format2.format(d))));
        }
        // set adapter
        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(minD);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recycle_menu,menu);
        //menu.setGroupVisible(R.id.group1,chb1.isChecked());
        //menu.findItem(R.id.item1).setVisible(chb2.isChecked());
        /*MenuItem menuStar = menu.findItem(R.id.item1);
        if (chb2.isChecked()){
            menuStar.setVisible(true);
        }
        else {
            menuStar.setVisible(false);
        }
        */
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.myToday){
            Toast.makeText(Main2Activity.this, R.string.myRecTxtMenu, Toast.LENGTH_SHORT).show();
            int minD = this.getResources().getInteger(R.integer.minDays);
            recyclerView.scrollToPosition(minD);
        }
        return super.onOptionsItemSelected(item);
    }
}
