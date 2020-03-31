package com.example.olio_7_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    String getText() throws IOException {
        EditText ED1 = (EditText) findViewById(R.id.editText);
        String sisalto = ED1.getText().toString();
        return sisalto;
    }

    String getName() throws IOException {
        EditText ED2 = (EditText) findViewById(R.id.editText2);
        String nimi = ED2.getText().toString();
        ED2.getText().clear();
        return nimi;
    }

    public void setText (String text) throws IOException {
        EditText ED1 = (EditText) findViewById(R.id.editText);

        ED1.setText(text);
    }

   public void load(View v) throws IOException{

        String readline = "asd";
        String nimi = getName();
        FileInputStream fis = null;
        try{
            fis = openFileInput(nimi + ".txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            setText(sb.toString());

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e)  {
                    e.printStackTrace();
                }

            }
        }


    }

   public void save(View v) throws IOException{
        String nimi = getName();
        String sisalto = getText();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(nimi + ".txt", MODE_PRIVATE);
            fos.write(sisalto.getBytes());
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + nimi, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (fos != null){
                try{
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
