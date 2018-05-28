#
#Sun Oct 15 22:32:29 CST 2017
SQL=SELECT *\r\n  FROM SHIFT_EXCHANGE_RECORD_DETAIL\r\n WHERE ROWNUM <\= 1\r\n   AND PATIENT_ID \= '%s'\r\n   AND VISIT_ID \= %s\r\n   AND to_char(SHIFT_DATE, 'yyyy-mm-dd') \= '%s'\r\n
