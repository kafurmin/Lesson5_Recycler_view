package com.example.kif.lesson5_recycler_view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private CheckBox checkBox;
    private int counter;
    TextView textview_count;
    private ArrayList<Student> arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textview_count = (TextView) findViewById(R.id.textview_count);

        counter  = 0;

        arr = new ArrayList<>();
        arr.add(new Student("Ivan", "Ivanov", 22, false));
        arr.add(new Student("Petro", "Petrovich", 23, false));
        arr.add(new Student("Ann", "Semenovich", 24, false));


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(this, arr);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        final Random random = new Random();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = random.nextInt(100);
                arr.add(new Student("Ivan"+i, "Ivanov"+i, i, false));
                adapter.notifyDataSetChanged();

            }
        });

        ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                final int fromPos = viewHolder.getAdapterPosition();
                final int toPos = target.getAdapterPosition();

                Student student = arr.get(fromPos);
                arr.remove(fromPos);
                arr.add(toPos, student);
                adapter.notifyItemMoved(fromPos, toPos);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int fromPos = viewHolder.getAdapterPosition();
                multiple_Checked(viewHolder.itemView, fromPos, false);
                arr.remove(fromPos);
                adapter.notifyItemRemoved(fromPos);

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Toast.makeText(this,
                "Clicked: " + position + ", index " + recyclerView.indexOfChild(view),
                Toast.LENGTH_SHORT).show();

        multiple_Checked(view, position, true);

    }

    public void multiple_Checked(View view, int position, boolean flag){
        checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        Student student = arr.get(position);

        if(checkBox.isChecked()){
            if(flag){
                student.setChecked(false);
                counter--;
                checkBox.setChecked(false);
            }else{
                student.setChecked(false);
                counter--;
            }
        }
        else {
            if(flag) {
                counter++;
                checkBox.setChecked(true);
                student.setChecked(true);
            }
        }


        textview_count.setText(String.valueOf(counter));
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