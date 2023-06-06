package sg.edu.rp.c346.id22027706.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd;
    Button btnClear;
    ListView lvTask;
    ArrayAdapter<String> aaTask;
    Spinner spinner;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        lvTask = findViewById(R.id.listViewTask);
        spinner = findViewById(R.id.spinner);
        btnDelete = findViewById(R.id.buttonDelete);

        ArrayList<String> alTasks = new ArrayList<String>();

        aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);
        lvTask.setAdapter(aaTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etTask.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter task to add", Toast.LENGTH_SHORT).show();
                } else {
                    String task = etTask.getText().toString();
                    alTasks.add(task);
                    Toast.makeText(MainActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.clear();
                etTask.setText("");
                aaTask.notifyDataSetChanged();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnDelete.setEnabled(false);
                        etTask.setText("Type in a new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        etTask.setText("Type in the index of the task to be removed");
                        aaTask.remove(etTask.getText().toString());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        }
    }
