package com.nursing.data.db;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nursing.data.SystemConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jack on 2015/12/14.
 */
public class DBDirectFormatJsonTest {

    private static final String testPatientID="00133633";
    private static final String testVisitID="1";
    private static final String testLoginUID="MJ01";

    @Test
    public void testLogin() throws Exception {
        String json= DBDirectFormatJson.select(SQLStatement.loginSQL(testLoginUID,"whatever"));
    }

    @Test
    public void testPatientList(){
        DBDirectFormatJson.select(SQLStatement.patientListSQL("1174"));
    }

    @Test
    public void testPatientDetail(){
        DBDirectFormatJson.select(SQLStatement.patientDetailSQL(testPatientID,testVisitID));
    }

    @Test
    public void testNextPatientDetail(){
        String result=DBDirectFormatJson.select(SQLStatement.wardCodeAndBedNoSQL(testPatientID,testVisitID));
        String wradCode="";
        String bedNo="";
        JSONArray array=new JSONArray(result);
        if (array!=null){
            for (int i = 0; i < array.length(); i++) {
                JSONObject item=array.optJSONObject(i);
                wradCode=item.optString("WARD_CODE");
                bedNo=item.optString("BED_NO");
            }
            DBDirectFormatJson.select(SQLStatement.nextPatientDetailSQL(wradCode,bedNo));
        }
    }

    @Test
    public void testPreviousPatientDetail(){
        String result=DBDirectFormatJson.select(SQLStatement.wardCodeAndBedNoSQL(testPatientID,testVisitID));
        String wradCode="";
        String bedNo="";
        JSONArray array=new JSONArray(result);
        if (array!=null){
            for (int i = 0; i < array.length(); i++) {
                JSONObject item=array.optJSONObject(i);
                wradCode=item.optString("WARD_CODE");
                bedNo=item.optString("BED_NO");
            }
            DBDirectFormatJson.select(SQLStatement.previousPatientDetailSQL(wradCode,bedNo));
        }
    }

    /**
     * 接口需要返回的Json数据格式
     * {
     "orderType": [
     {
     "name": "药疗",
     "code": "A"
     },
     {
     "name": "处置",
     "code": "E"
     }
     ],
     "orderCL": [
     {
     "name": "\u4E34\u65F6",
     "code": 0,
     "color": "#FF6600"
     },
     {
     "name": "\u957F\u671F",
     "code": 1,
     "color": "#000000"
     }
     ],
     "orderList": [
     {
     "BRID": "00133083",
     "ZYID": 1,
     "YZ_ITEM": "\u75F0-\u5F6D\u6C0F\u676F\u6297\u9178\u67D3\u8272",
     "YZ_TYPE": "C",
     "ORDER_CLASS_NAME": "\u68C0\u9A8C",
     "YZ_CHLIN": 0,
     "YZ_COLOR": "#FF6600",
     "YZ_JINUM": "",
     "YZ_DANWEI": " ",
     "ZX_TIME": "2015\/5\/5 16:11:57"
     }
     ]
     }
     */
    @Test
    public void testOrders(){
        StringBuffer jsonBuffer=new StringBuffer();
        String orderCLJson="[{\"name\":\"长期\",\"code\": 0,\"color\": \"#FF6600\"},{\"name\": \"临时\",\"code\": 1,\"color\": \"#000000\"}]";
        jsonBuffer.append("{");
        jsonBuffer.append("\"orderType\": ");
        jsonBuffer.append(DBDirectFormatJson.select(SQLStatement.orderTypeSQL()));
        jsonBuffer.append(",\"orderCL\":");
        jsonBuffer.append(orderCLJson);
        jsonBuffer.append(",\"orderList\":");
        jsonBuffer.append(DBDirectFormatJson.select(SQLStatement.orderListSQL(testPatientID,testVisitID,"H","长期")));
        jsonBuffer.append("}");
        System.out.println(jsonBuffer.toString());
    }

    @Test
    public void testAdmissionAssessment(){
        DBDirectFormatJson.select(SQLStatement.admissionAssessmentSQL());
        DBDirectFormatJson.select(SQLStatement.admissionAssessmentSQL("0111"));
    }

    @Test
    public void testNursing(){
        DBDirectFormatJson.select(SQLStatement.nursingSQL(testPatientID,testVisitID));
        DBDirectFormatJson.select(SQLStatement.nursingDescSQL(testPatientID,testVisitID,"2015-12-15","B"));
    }

    @Test
    public void testCheckList(){
        DBDirectFormatJson.select(SQLStatement.checkListSQL(testPatientID,testVisitID));
    }

    @Test
    public void testInspectionList(){
        DBDirectFormatJson.select(SQLStatement.inspectionList(testPatientID,testVisitID));
    }

    @Test
    public void testDBOperation(){
        DBDirectFormatJson.select("SELECT a.title,\n" +
                "   a.surgery_class,\n" +
                "   a.name,\n" +
                "   a.input_code,\n" +
                "   '0' as permission,\n" +
                "   b.db_user,\n" +
                "   b.user_dept\n" +
                "FROM   staff_dict a,\n" +
                "   users b\n" +
                "WHERE  a.name = b.user_name\n" +
                "   AND a.dept_code = b.user_dept\n" +
                "   AND db_user = '9001'");
    }

    @Test
    public void testCurrentTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
        String dateResult=df.format(new Date());
        System.out.println("date="+dateResult);
    }

    @Test
    public void testContains(){
        String string="1231232123";
        List<String> strings=new ArrayList<String>();
        strings.add(string);
        for (int i = 0; i < strings.size(); i++) {
            if (strings.get(i).contains("123")){
                System.out.println("true");
            }
        }
    }

    @Test
    public void testQrcode(){
        String string="00133083T10T2016-01-22080000";

        String[] decodeArray=string.split("T");
        String oid=decodeArray[1];
        String dotime=decodeArray[2];


        if (dotime.length()==14){
            String qrcodeDate=dotime.substring(0,9);
            String qrcodeTime=dotime.substring(9,14);
            String hour=qrcodeTime.substring(0,1);
            String minute=qrcodeTime.substring(1,3);
            String second=qrcodeTime.substring(3,5);
            qrcodeTime=hour+":"+minute+":"+second;
            System.out.println("qrcodeDate="+qrcodeDate);
            System.out.println("qrcodeTime="+qrcodeTime);
        }else if (dotime.length()==15){
            String qrcodeDate=dotime.substring(0,10);
            String qrcodeTime=dotime.substring(10,15);
            String hour=qrcodeTime.substring(0,1);
            String minute=qrcodeTime.substring(1,3);
            String second=qrcodeTime.substring(3,5);
            qrcodeTime=hour+":"+minute+":"+second;
            System.out.println("qrcodeDate="+qrcodeDate);
            System.out.println("qrcodeTime="+qrcodeTime);
        }else if (dotime.length()==16){
            String qrcodeDate=dotime.substring(0,10);
            String qrcodeTime=dotime.substring(10,16);
            String hour=qrcodeTime.substring(0,2);
            String minute=qrcodeTime.substring(2,4);
            String second=qrcodeTime.substring(4,6);
            qrcodeTime=hour+":"+minute+":"+second;
            System.out.println("qrcodeDate="+qrcodeDate);
            System.out.println("qrcodeTime="+qrcodeTime);
        }

        System.out.println("oid="+oid);
        System.out.println("dotime="+dotime);
    }

    @Test
    public void testOrderExecution(){
        String orderJson=DBDirectFormatJson.select("SELECT E.PATIENT_ID AS brid,\n" +
                "                E.VISIT_ID AS zyid,\n" +
                "                E.ORDER_NO AS yzoid,\n" +
                "                (O.ORDER_TEXT) AS yz_item,\n" +
                "                decode(e.administration,\n" +
                "                '口服','A','肌肉注射','B','皮下注射','B','皮内注射','B','静滴','C','静脉注射','C','续静滴','C','静脉输液','C','入壶','C','静脉泵入','C','氧气吸入','D','雾化吸入','D','F') yz_type,\n" +
                "                E.administration AS ORDER_CLASS_NAME,\n" +
                "                O.REPEAT_INDICATOR AS yz_chlin,\n" +
                "                E.IS_EXECUTE AS ISEXECUTE,\n" +
                "                O.DOSAGE AS yz_jinum,\n" +
                "                O.DOSAGE_UNITS AS yz_danwei,\n" +
                "                to_char(e.SCHEDULE_PERFORM_TIME,'yyyy-mm-dd hh:mi:ss') AS zx_time\n" +
                "        FROM ORDERS_EXECUTE E, ORDERS O\n" +
                "        WHERE E.PATIENT_ID ='00133083'\n" +
                "                AND E.VISIT_ID =1\n" +
                "                AND decode(e.administration,'口服','A','肌肉注射','B','皮下注射','B','皮内注射','B','静滴','C','静脉注射','C','续静滴','C','静脉输液','C','入壶','C','静脉泵入','C','氧气吸入','D','雾化吸入','D','F')='C'\n" +
                "                AND E.PATIENT_ID = O.PATIENT_ID\n" +
                "                AND E.VISIT_ID = O.VISIT_ID\n" +
                "                AND E.ORDER_NO = O.ORDER_NO\n" +
                "                AND E.ORDER_SUB_NO = O.ORDER_SUB_NO\n" +
                "                AND to_char(E.SCHEDULE_PERFORM_TIME,'YY-MM-DD') =to_char(SYSDATE,'YY-MM-DD')\n" +
                "                AND (E.SCHEDULE_PERFORM_TIME > trunc(SYSDATE)\n" +
                "                AND E.SCHEDULE_PERFORM_TIME < trunc(SYSDATE + 1))\n" +
                "                AND ( (E.REPEAT_INDICATOR IS NULL\n" +
                "                OR E.REPEAT_INDICATOR = '0')\n" +
                "                OR (E.REPEAT_INDICATOR = '1'\n" +
                "                AND (O.STOP_DATE_TIME IS NULL\n" +
                "                OR O.STOP_DATE_TIME >= E.SCHEDULE_PERFORM_TIME)) )\n" +
                "        ORDER BY  zx_time ASC,yzoid asc");
        System.out.println("result="+orderJson);
    }

    @Test
    public void testNumToLetter(){
        String dotime="2015-1-2680000";
        String[] newlyDotimeArr=dotime.split("-");
        String newlyYear=newlyDotimeArr[0];
        String newlyMonth=newlyDotimeArr[1];
        String newlyDay=newlyDotimeArr[2].substring(0,2);

        String newlyTimeAll=newlyDotimeArr[2].substring(2,newlyDotimeArr[2].length());
        String hour,minute,second;
        if (newlyTimeAll.length()==5){
            hour=newlyTimeAll.substring(0,1);
            minute=newlyTimeAll.substring(1,3);
            second=newlyTimeAll.substring(3,5);
        }else{
            hour=newlyTimeAll.substring(0,2);
            minute=newlyTimeAll.substring(2,4);
            second=newlyTimeAll.substring(4,6);
        }
        String qrcodeDate=newlyYear+"-"+newlyMonth+"-"+newlyDay;
        String qrcodeTime=hour+":"+minute+":"+second;
        dotime=qrcodeDate+" "+qrcodeTime;
        System.out.println("dotime="+dotime);
    }

    @Test
    public void testScanTime(){
        System.out.println("result="+getScanTime("2015-1-2680000"));
    }

    public String getScanTime(String scanTime){
        String[] newlyDotimeArr=scanTime.split("-");
        String newlyYear=newlyDotimeArr[0];
        String newlyMonth=newlyDotimeArr[1];
        String newlyDay=newlyDotimeArr[2].substring(0,2);

        String newlyTimeAll=newlyDotimeArr[2].substring(2,newlyDotimeArr[2].length());
        String hour,minute,second;
        if (newlyTimeAll.length()==5){
            hour=newlyTimeAll.substring(0,1);
            minute=newlyTimeAll.substring(1,3);
            second=newlyTimeAll.substring(3,5);
        }else{
            hour=newlyTimeAll.substring(0,2);
            minute=newlyTimeAll.substring(2,4);
            second=newlyTimeAll.substring(4,6);
        }
        if (newlyMonth.length()==1){
            newlyMonth="0"+newlyMonth;
        }
        if (hour.length()==1){
            hour="0"+hour;
        }
        String qrcodeDate=newlyYear+"-"+newlyMonth+"-"+newlyDay;
        String qrcodeTime=hour+":"+minute+":"+second;
        return qrcodeDate+" "+qrcodeTime;
    }


}