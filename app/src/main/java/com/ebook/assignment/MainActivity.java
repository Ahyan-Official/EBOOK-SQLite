package com.ebook.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper mDatabaseHelper;
    private Button btnAdd ;
    private EditText editTextAuthor,editTextBook,editTextCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextAuthor = (EditText) findViewById(R.id.editTextAuthor);
        editTextBook = (EditText) findViewById(R.id.editTextBook);
        editTextCategory = (EditText) findViewById(R.id.editTextCategory);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry1 = editTextAuthor.getText().toString();
                String newEntry2 = editTextBook.getText().toString();
                String newEntry3 = editTextCategory.getText().toString();

                if (editTextAuthor.length() != 0 && editTextBook.length() != 0 && editTextCategory.length() != 0) {

                    AddData(newEntry1,newEntry2,newEntry3);

                    editTextAuthor.setText("");
                    editTextBook.setText("");
                    editTextCategory.setText("");

                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });



    }

    public void AddData(String newEntry1,String newEntry2,String newEntry3) {
        boolean insertData = mDatabaseHelper.addData(newEntry1,newEntry2,newEntry3);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");

            Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
            startActivity(intent);
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
