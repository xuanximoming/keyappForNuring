#
#Sun Oct 15 22:21:20 CST 2017
SQL=SELECT a.TEST_NO AS jy_id,\r\n       a.SPECIMEN AS jy_biaoben,\r\n       b.order_text AS jy_zhenduan,\r\n       to_char(a.REQUESTED_DATE_TIME, 'yyyy-mm-dd') AS jy_date\r\n  FROM LAB_TEST_MASTER a, ORDERS b\r\n WHERE a.PATIENT_ID \= b.PATIENT_ID\r\n   AND a.PATIENT_ID \= '%s'\r\n   AND a.VISIT_ID \= b.VISIT_ID\r\n   AND a.VISIT_ID \= %s\r\n   AND a.TEST_NO \= b.app_no\r\n   AND b.order_class \= 'C'\r\n ORDER BY a.test_no desc
