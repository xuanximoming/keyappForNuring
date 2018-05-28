package com.nursing.data.db;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

/**
 * Created by Jack on 2015/12/14.
 * 将数据库的查询结果直接格式化json输出
 */
public class DBDirectFormatJson {

    public static String select(String sql){
        String result=null;
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            if (conn.isClosed()){
                DBHelper.resetConnection();
                conn=DBHelper.getConnection();
            }
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            result=resultSetToJson(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("--------------DBDirectFormatJson------------\n");
        System.out.println(result);
        return result;
    }

    private static String resultSetToJson(ResultSet rs) throws SQLException,JSONException
    {
        // json数组
        JSONArray array = new JSONArray();

        // 获取列数
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // 遍历ResultSet中的每条数据
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();
            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName =metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.put(jsonObj);
        }

        return array.toString();
    }


}
