package shcher.test5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private  MyAdapter adapter;
    private List<RecyclerItem> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        //Generate sample data
        for (int i = 0; i<10; i++){
            //RecyclerItem item = new RecyclerItem("Item " + (i + 1),"Welcome to new traning, this is desc of item " + (i+1));
            listItems.add(new RecyclerItem("Item " + (i + 1),"Welcome to new traning, this is desc of item " + (i+1)));
        }
        // set adapter
        adapter = new MyAdapter(listItems,this);
        recyclerView.setAdapter(adapter);
    }
}
