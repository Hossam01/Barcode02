package com.example.barcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.arturogutierrez.Badges;
import com.github.arturogutierrez.BadgesNotSupportedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.leolin.shortcutbadger.ShortcutBadger;

import static com.github.arturogutierrez.Badges.setBadge;

public class Main2Activity extends AppCompatActivity {
    TextView TV_Header;
    Typeface font;
    ListView LV_Country;
    SimpleAdapter ADAhere;
    String TAG="Badges";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        TV_Header=(TextView) findViewById(R.id.TV_Header);
        LV_Country=(ListView)findViewById(R.id.LV_Country);







        List<Map<String,String>> MyData = null;
        GetData mydata =new GetData();
        MyData= mydata.doInBackground();
        String[] fromwhere = { "ot_doc_id" };

        int[] viewswhere = { R.id.lblcountryname};

        ADAhere = new SimpleAdapter(Main2Activity.this, MyData,R.layout.itemplate, fromwhere, viewswhere);

        LV_Country.setAdapter(ADAhere);
        ShortcutBadger.applyCount(Main2Activity.this, 2);
        try {
            Badges.setBadge(Main2Activity.this, 5);
        } catch (BadgesNotSupportedException badgesNotSupportedException) {
            Log.d(TAG, badgesNotSupportedException.getMessage());
        }




        LV_Country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,Object> obj=(HashMap<String,Object>)ADAhere.getItem(position);
                String ID=(String)obj.get("ot_doc_id");
                Toast.makeText(Main2Activity.this, ID, Toast.LENGTH_SHORT).show();
                Bundle b = new Bundle();

                // Storing data into bundle
                b.putString("ot_doc_id", ID);
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });



    }
}
