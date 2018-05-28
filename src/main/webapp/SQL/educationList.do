#
#Sun Oct 15 22:33:34 CST 2017
SQL=SELECT item_id,\r\n       item_name,\r\n       (SELECT count(*)\r\n          FROM HIS_DICT_ITEM\r\n         WHERE dict_id \= 07\r\n           AND SUBSTR(item_id, 1, 4) \= A.item_id\r\n           AND length(item_id) \= 6) AS itemn\r\n  FROM HIS_DICT_ITEM A\r\n WHERE dict_id \= 07\r\n   AND length(item_id) \= 4\r\n ORDER BY item_id ASC
