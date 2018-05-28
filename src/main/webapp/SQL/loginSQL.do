#
#Sun Oct 15 22:34:56 CST 2017
SQL=select *\r\n  from dept_dict d\r\n inner join dept_vs_ward vs\r\n    on d.dept_code \= vs.dept_code\r\n inner join staff_vs_group s\r\n    on s.group_code \= vs.ward_code\r\n inner join staff_dict sta\r\n    on sta.emp_no \= s.emp_no\r\n where sta.user_name \= '%s'\r\n   and sta.password \= '%s'\r\n
