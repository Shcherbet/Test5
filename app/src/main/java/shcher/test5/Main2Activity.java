package shcher.test5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<RecyclerItem> listItems;
    private Date d;
    Calendar maxDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        d = new Date();
        maxDay = new GregorianCalendar();
        maxDay.add(Calendar.YEAR,1);
        long duration = maxDay.getTimeInMillis()-d.getTime();
        int days = (int) (duration / (24 * 60 * 60 * 1000));
        //duration.setTime(maxDay.getTime()-d.getTime());
       // Calendar maxDay = new GregorianCalendar();
        //maxDay.add(Calendar.YEAR,1);

        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMMM");

        //Generate sample data
        for (int i = 0; i < days; i++) {
            //RecyclerItem item = new RecyclerItem("Item " + (i + 1),"Welcome to new traning, this is desc of item " + (i+1)); maxDay.get(Calendar.DAY_OF_YEAR
            Calendar calDay = new GregorianCalendar();
            calDay.add(Calendar.DAY_OF_YEAR,i);

            listItems.add(new RecyclerItem("Item " + (format2.format(calDay.getTime())) ,"Days" + days +" date" +(format2.format(d))));
        }
        // set adapter
        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }
}
