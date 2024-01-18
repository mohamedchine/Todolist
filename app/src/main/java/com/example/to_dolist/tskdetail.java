package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;

public class tskdetail extends AppCompatActivity {
    EditText desc, title,date;
    CheckBox c;
   task tskselectionner;
    int lidmtaatask;

    Button del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tskdetail);
        title=findViewById(R.id.titre);
        date=findViewById(R.id.date);
        c=findViewById(R.id.completed);
        desc=findViewById(R.id.description);
        del=findViewById(R.id.buttonDeleteTask);

       // lhna idha kna selectionnina task mel liste nbedlou n7otou hadhaka task fil tskdetail bch nepdaytouh uniquement idha kna lgunah
        lidmtaatask = getIntent().getIntExtra("taskId", -1);
        tskselectionner=task.gettaskbyid(lidmtaatask);
        if(tskselectionner!=null){
            title.setText(tskselectionner.getTasktitle());
            date.setText(tskselectionner.getDeadline());
            desc.setText(tskselectionner.getDescription());
            if (tskselectionner.getTerminated().equals("yes")) {
                c.setChecked(true);
            } else {
                c.setChecked(false);
            }
        }
        else{
            del.setVisibility(View.INVISIBLE);
        }

    }

    public void savetask(View view) {
        sqlitedatabasescheme db =new sqlitedatabasescheme(this);
        String tit=String.valueOf(title.getText());
        String dat=String.valueOf(date.getText());
        String terminated;
        String dsc= String.valueOf(desc.getText());
        if (c.isChecked()) {
            terminated = "yes";
        } else {
            terminated = "no";
        }

        if(tskselectionner==null){
        int id=task.tasklist.size();
        task tsq=new task(id,tit,dat,terminated,dsc);
        task.tasklist.add(tsq);
        db.insertTask(tsq);

    }
    else{
        tskselectionner.setDeadline(dat);
        tskselectionner.setTasktitle(tit);
        tskselectionner.setDescription(dsc);
        tskselectionner.setTerminated(terminated);
        db.updatetask(tskselectionner);
        }
        finish();
    }

    public void deletetask(View view) {
        sqlitedatabasescheme db =new sqlitedatabasescheme(this);
        db.deleteTask(lidmtaatask);
        finish();
    }
}