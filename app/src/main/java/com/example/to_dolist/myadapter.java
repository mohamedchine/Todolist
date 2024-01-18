package com.example.to_dolist;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class myadapter extends BaseAdapter {
    Context x;
    List<task> l;

    public myadapter(Context x, List<task> l) {
        this.x = x;
        this.l = l;
    }

    @Override
    public int getCount() {
        return l.size();
    }

    @Override
    public Object getItem(int i) {
        return l.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(x).inflate(R.layout.designmtaitem, viewGroup, false);
        TextView tasktitle=view.findViewById(R.id.tasktitle);
        TextView deadline=view.findViewById(R.id.deadline);
        TextView termineroupas=view.findViewById(R.id.termineroupas);
        task currentTask = l.get(i);
        tasktitle.setText(currentTask.getTasktitle());
        deadline.setText(currentTask.getDeadline());
        termineroupas.setText(currentTask.getTerminated());
        return view;
    }
}
