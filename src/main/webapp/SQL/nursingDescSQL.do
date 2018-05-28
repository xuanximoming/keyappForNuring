#
#Sun Oct 15 22:18:27 CST 2017
SQL=SELECT VITAL_SIGNS AS hl_item,\r\n       UNITS,\r\n       CLASS_CODE,\r\n       VITAL_CODE,\r\n       to_char(time_point, 'hh24\:mi\:ss') AS hl_time,\r\n       VITAL_SIGNS_CVALUES AS hl_value,\r\n       (20) min_value,\r\n       (100) max_value,\r\n       ('测试') alertmsg\r\n  FROM vital_signs_rec\r\n WHERE patient_id \= '%s'\r\n   AND visit_id \= %s\r\n   AND recording_date \= to_date('%s', 'yyyy-mm-dd')
