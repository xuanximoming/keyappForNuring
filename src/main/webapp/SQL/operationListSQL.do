#
#Sun Oct 15 22:24:03 CST 2017
SQL=SELECT PATIENT_ID         AS brid,\r\n       VISIT_ID           AS zyid,\r\n       OPERATION_NO       AS shoushu_id,\r\n       OPERATION_DESC     AS shoushu_name,\r\n       ANAESTHESIA_METHOD AS showshu_buwei,\r\n       OPERATING_DATE     AS shoushu_date,\r\n       OPERATOR           AS shoushu_yishi\r\n  FROM OPERATION\r\n WHERE PATIENT_ID \= '%s'\r\n   AND VISIT_ID \= %s
