select user(); database();

select deptno ,deptname, floor from department;

desc employee;


select empno, empname, title, manager, salary, dno 
from employee 
where dno = 5;

select e.empno, e.empname, e.title, m.empname as manager_name , m.empno as manager_no ,e.salary, e.dno, d.deptname 
from employee e left join employee m on e.manager = m.empno join department d on e.dno = d.deptno  
where e.dno=2;

select dno from employee where dno = 2;

desc student;

select sno, sname, kor, eng, math from student;

select *
from employee;

select *
from department;

insert into department
values(6,'프론트엔드', 10);

update department set deptname ='프론트엔드2', floor = 10 where deptno =6;
delete from department where deptno =6;


select *
from department;
delete from department where deptno = 6;



