package shcher.test5;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView myView;
    Button myBtn1;
    Button myBtn2;
    Button myBtn3;
    Button myBtn4;
    CheckBox chb1,chb2;
    MenuItem menuStar;// = menu.findItem(R.id.item1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = (TextView) findViewById(R.id.myView);
        myBtn1 = (Button) findViewById(R.id.button1);
        myBtn2 = (Button) findViewById(R.id.button2);
        myBtn3 = (Button) findViewById(R.id.button3);
        myBtn4 = (Button) findViewById(R.id.button4);
        chb1 = (CheckBox) findViewById(R.id.checkBox1);
        chb2 = (CheckBox) findViewById(R.id.checkBox2);

        View.OnClickListener OnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){
                    case R.id.button1:
                        myView.setText(R.string.myViewTxt1);
                        Toast toast = Toast.makeText(MainActivity.this, R.string.myViewTxt1, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP,0,0);
                        LinearLayout toastImage = (LinearLayout) toast.getView();
                        ImageView imageView = new ImageView(MainActivity.this);
                        imageView.setImageResource(R.drawable.ic_account_balance_black_48dp);
                        toastImage.addView(imageView);
                        toast.show();
                        break;
                    case R.id.button2:
                        myView.setText(R.string.myViewTxt2);
                        break;
                    case R.id.button3:
                        myView.setText(R.string.myViewTxt3);
                        break;

                    case R.id.button4:
                        Intent intent = new Intent(MainActivity.this,ScrollingActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }

            }
        };
        myBtn1.setOnClickListener(OnClickListener);
        myBtn2.setOnClickListener(OnClickListener);
        myBtn3.setOnClickListener(OnClickListener);
        myBtn4.setOnClickListener(OnClickListener);


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.group1,chb1.isChecked());
        menu.findItem(R.id.item1).setVisible(chb2.isChecked());
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
        getMenuInflater().inflate(R.menu.menu_main,menu);
        menuStar = menu.findItem(R.id.item1);
        chb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuStar.setVisible(chb2.isChecked());
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.item1){
            Toast.makeText(MainActivity.this, R.string.myItemTxt1, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


}
