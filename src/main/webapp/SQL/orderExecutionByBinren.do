#
#Wed Oct 18 14:12:32 CST 2017
SQL=SELECT DISTINCT A.PATIENT_ID AS brid,\r\n                B.VISIT_ID AS zyid,\r\n                A.NAME AS brname,\r\n                B.BED_NO AS chuang,\r\n                A.SEX AS sex,\r\n                B.DOCTOR_IN_CHARGE AS ZHUYI,\r\n                to_char(A.DATE_OF_BIRTH, 'yyyy-mm-dd') AS BIRTH,\r\n                to_char(B.ADMISSION_DATE_TIME, 'yyyy-mm-dd') AS ZYDATE,\r\n                (SELECT DEPT_NAME\r\n                   FROM DEPT_DICT\r\n                  WHERE DEPT_CODE \= B.DEPT_CODE) AS BINGQU,\r\n                NVL(B.DIAGNOSIS,\r\n                    (select dg.diagnosis_desc\r\n                       from diagnosis dg\r\n                      where dg.patient_id \= B.PATIENT_ID\r\n                        and dg.visit_id \= B.VISIT_ID\r\n                        and dg.diagnosis_type \= 1\r\n                        and dg.diagnosis_no \= 1)) AS zhenduan,\r\n                A.CHARGE_TYPE AS feibie,\r\n                (B.PREPAYMENTS - B.TOTAL_CHARGES) AS feiyue,\r\n                (case\r\n                  WHEN (SELECT NURSING_CLASS_NAME\r\n                          FROM NURSING_CLASS_DICT\r\n                         WHERE NURSING_CLASS_CODE \= B.NURSING_CLASS) is NULL THEN\r\n                   ' '\r\n                  ELSE\r\n                   (SELECT NURSING_CLASS_NAME\r\n                      FROM NURSING_CLASS_DICT\r\n                     WHERE NURSING_CLASS_CODE \= B.NURSING_CLASS)\r\n                END) AS HULIDJ\r\n  FROM PAT_MASTER_INDEX A, pats_in_hospital B\r\n WHERE A.PATIENT_ID \= B.PATIENT_ID\r\n   AND B.PATIENT_ID \= '%s'\r\n   AND B.VISIT_ID \= %s\r\n
