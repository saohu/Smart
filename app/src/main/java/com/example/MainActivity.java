package com.example;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private List<String> datas;
    private AutoCompleteTextView mAuto;
    private MyOpenHelper mHelper;
    private SQLiteDatabase mDB;
    private ArrayAdapter <String>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new MyOpenHelper(this,"word.db",null,1);
        mDB = mHelper.getReadableDatabase();
        mAuto = (AutoCompleteTextView) findViewById(R.id.mAuto);
        datas = getData();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        mAuto.setAdapter(adapter);

    }
    public void search(View view){
        String input = mAuto.getText().toString();
        if (input == null||"".equals(input.trim())){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("警告提示");
            builder.setMessage("请输入搜索关键字");
            builder.create().show();
        }else {
            if (!datas.contains(input)){
                mDB.execSQL("inser into woie (word) values(?)",new String[]{input});
                datas = getData();
                adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
                mAuto.setAdapter(adapter);

            }
        }
    }

    private List<String> getData() {
        List<String>content = new ArrayList<String>();
        Cursor result = mDB.rawQuery("select*from word",null);
        while (result.moveToNext()){
            content.add(result.getString(result.getColumnIndex("word")));
        }
        return content;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
