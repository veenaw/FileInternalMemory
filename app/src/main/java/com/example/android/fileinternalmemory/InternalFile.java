package com.example.android.fileinternalmemory;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class InternalFile extends Activity {
    private final static String fileName = "TestFile.txt";
    private String TAG = "InternalFileRW";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_file);
        LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout);
        // Check whether fileName already exists in directory used
        // by the openFileOutput() method.
        // If the text file doesn't exist, then create it now

        if (!getFileStreamPath(fileName).exists()) {

            try {

                writeFile();

            } catch (FileNotFoundException e) {
                Log.i(TAG, "FileNotFoundException");
            }
        }


        // Read the data from the text file and display it
        try {

            readFile(ll);

        } catch (IOException e) {
            Log.i(TAG, "IOException");
        }
    }
    private void writeFile() throws FileNotFoundException {

        FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);

        PrintWriter pw = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(fos)));

        pw.println("Line 1: Again, File Writing API");
        pw.println("Line 2: Again, Once Again, File Writing API");
        pw.println("Line 3: Again, Once Again, Yet Again File Writing API");

        pw.close();

        Toast.makeText(InternalFile.this, "Done Writing to the file", Toast.LENGTH_LONG).show();
        SystemClock.sleep(5000);


    }

    private void readFile(LinearLayout ll) throws IOException {

        FileInputStream fis = openFileInput(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = "";

        while (null != (line = br.readLine())) {

            TextView tv = new TextView(this);
            tv.setTextSize(24);
            tv.setText(line);

            ll.addView(tv);

        }

        br.close();
        Toast.makeText(InternalFile.this, "Seeing the text on the screeen!!", Toast.LENGTH_SHORT).show();
    }
}
