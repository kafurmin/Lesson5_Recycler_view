package com.example.kif.lesson5_recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Kif on 13.07.2017.
 */

public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView mTextViewFirstName;
    public TextView mTextViewLastName;
    public TextView mTextViewAge;
    public CheckBox mCheckBox;
    public View mRootView;

    private RecyclerAdapter adapter;

    public StudentViewHolder(View view, RecyclerAdapter recyclerAdapter) {
        super(view);

        view.setOnClickListener(this);
        adapter = recyclerAdapter;

        mTextViewFirstName = (TextView) view.findViewById(R.id.firstName);
        mTextViewLastName = (TextView) view.findViewById(R.id.lastName);
        mTextViewAge = (TextView) view.findViewById(R.id.age);
        mCheckBox = (CheckBox) view.findViewById(R.id.checkbox);
        mRootView = view.findViewById(R.id.root);
    }

    @Override
    public void onClick(View v) {
        adapter.setOnItemHolderClick(this);
    }
}