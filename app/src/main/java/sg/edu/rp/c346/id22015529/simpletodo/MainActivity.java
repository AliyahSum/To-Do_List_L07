package sg.edu.rp.c346.id22015529.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinAddDelete ;
    EditText etTask ;
    Button btnAdd ;
    Button btnDelete ;
    Button btnClear ;
    ListView lvTasks ;
    ArrayList<String> taskList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinAddDelete = findViewById(R.id.spinnerOptions) ;
        etTask = findViewById(R.id.editTextTask) ;
        btnAdd = findViewById(R.id.buttonAdd) ;
        btnDelete = findViewById(R.id.buttonDelete) ;
        btnClear = findViewById(R.id.buttonClear) ;
        lvTasks = findViewById(R.id.listViewTasks) ;

        taskList = new ArrayList<String>() ;

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList) ;
        lvTasks.setAdapter(adapter) ;

        spinAddDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = spinAddDelete.getSelectedItemPosition() ;
                if (pos == 0) {
                    etTask.setHint("Type in a new task here"); ;
                    btnDelete.setEnabled(false) ;
                    btnAdd.setEnabled(true) ;

                    btnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String newTaskAdded = etTask.getText().toString() ;
                            taskList.add(newTaskAdded) ;
                            adapter.notifyDataSetChanged() ;
                        }
                    });

                    btnClear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (taskList.isEmpty() == false) {
                                taskList.clear() ;
                                adapter.notifyDataSetChanged() ;
                            }
                            else if (taskList.isEmpty() == true) {
                                Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show() ;
                            }
                        }
                    });

                }
                else if (pos == 1) {
                    etTask.setHint("Type in the index of the task to be removed"); ;
                    btnAdd.setEnabled(false) ;
                    btnDelete.setEnabled(true) ;

                    btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int taskDeleted = Integer.parseInt(etTask.getText().toString()) ;
                            if (taskDeleted -1 > taskList.size()) {
                                Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show() ;
                            }
                            else if (taskDeleted -1 <= taskList.size()) {
                                taskList.remove(taskDeleted -1);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });

                    btnClear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (taskList.isEmpty() == false) {
                                taskList.clear() ;
                                adapter.notifyDataSetChanged() ;
                            }
                            else if (taskList.isEmpty() == true) {
                                Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show() ;
                            }
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}