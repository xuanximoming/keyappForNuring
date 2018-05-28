#
#Sun Oct 15 22:16:54 CST 2017
SQL=SELECT to_char(recording_date, 'yyyy-mm-dd') as recording_date,\r\n       COUNT(*) AS counum\r\n  FROM vital_signs_rec\r\n WHERE patient_id \= '%s'\r\n   AND visit_id \= %s\r\n GROUP BY recording_date\r\n ORDER BY recording_date DESC
