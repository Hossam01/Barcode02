package com.example.barcode;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetData {

    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;

    public List<Map<String,String>> doInBackground() {

        List<Map<String, String>> data = null;
        data = new ArrayList<Map<String, String>>();
        try
        {
            ConnectionHelper conStr=new ConnectionHelper();
            connect =conStr.connectionclasss();        // Connect to database
            if (connect == null)
            {
                ConnectionResult = "Check Your Internet Access!";
            }
            else
            {
                // Change below query according to your own database.
                String query = "Select * from opr_trx where ot_process_id = 3 and ot_task_id = 2 and ot_task_type = 'user' and ot_task_status = 1 and ot_Process_status = 1 and ot_user_assignee_id = 107";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    Map<String,String> datanum=new HashMap<String,String>();
                    datanum.put("ID",rs.getString("ot_Id"));
                    datanum.put("ot_doc_id",rs.getString("ot_doc_id"));
                    //datanum.put("Capital",rs.getString("ot_doc_id"));
                    data.add(datanum);
                }


                ConnectionResult = " successful";
                isSuccess=true;
                connect.close();
            }
        }
        catch (Exception ex)
        {
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }

        return data;
    }

    public void insertdata(String id)
    {
        ConnectionHelper connectionClass = new ConnectionHelper();
        try
        {
            Connection con = connectionClass.connectionclasss();
            String query = "INSERT INTO Taqeem (Counter) VALUES ('" + id  + "') " ;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch (SQLException se)
        {
            Log.e("ERROR", se.getMessage());
        }
    }


}
