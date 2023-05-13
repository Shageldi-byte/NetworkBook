package com.android.networkbook;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Utils {
    public static String loadJSONFromAsset(String json_name, Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(json_name);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static ArrayList<ThemeData> getThemes(Context context){
        ArrayList<ThemeData> themeData=new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset("data/theme.json",context));
            JSONArray m_jArry = obj.getJSONArray("theme");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                themeData.add(new ThemeData(
                        1,
                        jo_inside.getString("title"),
                        jo_inside.getString("title"),
                        jo_inside.getString("title"),
                        jo_inside.getString("title").endsWith(".pdf")?jo_inside.getString("title"):"book.pdf",
                        jo_inside.getInt("start_pos"),
                        jo_inside.getInt("end_pos"),
                        1,
                        "1"
                ));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return themeData;
    }

    public static int[] convertIntegers(ArrayList<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

    public static Dialog getDialogProgressBar(Context context) {
        Dialog dialog = new Dialog(context);

        final ProgressBar progressBar = new ProgressBar(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(lp);
        progressBar.setBackgroundResource(android.R.color.transparent);
        dialog.setContentView(progressBar);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.setCancelable(true);
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        return dialog;
    }
}
