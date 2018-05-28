package com.nursing.data.db;

/**
 * Created by Jack on 2016/1/18.
 * 每日评估功能说明
 */
public class DailyAssessmentSQL {

    private static String format(String format, Object... args){
        String result=String.format(format,args);
        System.out.println("--------------SQLStatement------------\n"+result);
        return result;
    }

    /**
     * 获取每日评估的日期列表
     * @param bid
     * @param vid
     * @return
     */
    public static String dailyAssessmentDateList(String bid,String vid){
        String sql="SELECT to_char(record_date,'yyyy-mm-dd') AS mrpg_date\n" +
                "  FROM PAT_EVALUATION_M\n" +
                "  WHERE PATIENT_ID='%s'\n" +
                "  AND VISIT_ID=%s\n" +
                "  AND dict_id=01\n" +
                "  GROUP BY record_date";
        return format(sql,bid,vid);
    }

    /**
     * 这个不懂，和日期有关...
     * 根据日期获取 item_id
     * @param bid
     * @param vid
     * @param pgdate
     * @return
     */
    public static String dailyAssessmentDetailListBydate(String bid,String vid,String pgdate){
        String sql="SELECT item_id\n" +
                "  FROM PAT_EVALUATION_M\n" +
                "  WHERE PATIENT_ID='%s'\n" +
                "  AND VISIT_ID=%s\n" +
                "  AND dict_id=01\n" +
                "  AND to_char(record_date,'yyyy-mm-dd')='%s'";
        return format(sql,bid,vid,pgdate);
    }


    /**
     * 获取所有的每日评估选项
     *
     */
    public static String dailyAssessmentLList(){
        String sql="SELECT item_id,\n" +
                "       item_name,\n" +
                "       flag,\n" +
                "\n" +
                "  (SELECT count(*)\n" +
                "   FROM HIS_DICT_ITEM\n" +
                "   WHERE dict_id=01\n" +
                "     AND SUBSTR(item_id,1,2)=A.item_id\n" +
                "     AND length(item_id)=4) AS itemn   \n" +
                "  FROM HIS_DICT_ITEM A   \n" +
                "  WHERE dict_id=01  \n" +
                "  AND length(item_id)=2\n" +
                "  AND item_id NOT in(11,12)\n" +
                "  ORDER BY item_id ASC";
        return sql;
    }

    /**
     * 根据itemID获取每日评估选项
     * @param itemId
     * @return
     */
    public static String dailyAssessmentLListByItemId(String itemId){
        String length=String.valueOf(itemId.length());
        String lengths=String.valueOf(itemId.length()+2);
        String lengthsPlus2=String.valueOf(itemId.length()+4);
        String sql="SELECT item_id,\n" +
                "       item_name,\n" +
                "       flag,\n" +
                "\n" +
                "  (SELECT count(*)\n" +
                "   FROM HIS_DICT_ITEM\n" +
                "   WHERE dict_id=01\n" +
                "     AND SUBSTR(item_id,1,%s)=A.item_id\n" +
                "     AND length(item_id)=%s) as itemn \n" +
                "FROM HIS_DICT_ITEM A\n" +
                "WHERE dict_id=01\n" +
                "  AND SUBSTR(item_id,1,%s)='%s'\n" +
                "  AND length(item_id)=%s\n" +
                "  AND item_id NOT in(11,12)\n" +
                "ORDER BY item_id ASC";
        return format(sql,lengths,lengthsPlus2,length,itemId,lengths);
    }



}
