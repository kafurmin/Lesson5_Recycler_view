package com.example.kif.lesson5_recycler_view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Kif on 12.07.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder> {
    private ArrayList<Student> mStudents;
    private LayoutInflater mInflater;

    private AdapterView.OnItemClickListener Listener;

    public RecyclerAdapter(android.content.Context context, ArrayList<Student> students) {
        this.mStudents = students;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.child_item, null);

        StudentViewHolder viewHolder = new StudentViewHolder(view, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentViewHolder studentViewHolder, final int i) {
        Student student = mStudents.get(i);
        int type = getItemViewType(i);


        studentViewHolder.mTextViewFirstName.setText(student.FirstName);
        studentViewHolder.mTextViewLastName.setText(student.LastName);
        studentViewHolder.mTextViewAge.setText(String.valueOf(student.Age));
        studentViewHolder.mCheckBox.setChecked(student.getChecked());

        final ViewGroup.LayoutParams lp = studentViewHolder.itemView.getLayoutParams();

        if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp;

            //    new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

            if (type == 0) {
                sglp.setFullSpan(true);
                studentViewHolder.itemView.setLayoutParams(sglp);
                studentViewHolder.mTextViewFirstName.setText(student.FirstName);
                studentViewHolder.mTextViewLastName.setText(student.LastName);
                studentViewHolder.mTextViewAge.setText(String.valueOf(student.Age));
                studentViewHolder.mCheckBox.setChecked(student.getChecked());


            }
            if (type == 1 || type == 2) {


                studentViewHolder.mTextViewFirstName.setText(student.FirstName);
                studentViewHolder.mTextViewLastName.setText(student.LastName);
                studentViewHolder.mTextViewAge.setText(String.valueOf(student.Age));
                studentViewHolder.mCheckBox.setChecked(student.getChecked());


            }
        }
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        Listener = onItemClickListener;
    }

    public void setOnItemHolderClick(StudentViewHolder holder){
        if (Listener != null) {
            Listener.onItemClick(null, holder.itemView,
                    holder.getAdapterPosition(), holder.getItemId());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position%3);
    }
}