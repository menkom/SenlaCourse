/*1*/
select model, speed, hd from pc where price<500;
/*2*/
SELECT maker FROM product where product.type='printer';
/*3*/
SELECT model,hd,screen FROM laptop WHERE price>1000;
/*4*/
SELECT * FROM printer pr WHERE pr.color='y';
/*5*/
SELECT pc.model, pc.speed, pc.hd FROM pc WHERE price<600 AND (cd='12' OR cd='24');
/*6*/
SELECT pr.maker, l.speed FROM product pr, laptop l WHERE pr.model=l.model and l.hd>1000;
/*7*/
SELECT pro.model, l.price FROM product pro, laptop l WHERE pro.maker = 'hp' AND pro.model = l.model
union
SELECT pro.model, pc.price FROM product pro, pc WHERE pro.maker = 'hp' AND pro.model = pc.model
union
SELECT pro.model, printer.price FROM product pro, printer WHERE pro.maker = 'hp' AND pro.model = printer.model;
/*8*/
select * from product where maker not in(select maker from product WHERE type='pc');
/*9*/
SELECT maker FROM product pr, pc WHERE pc.model=pr.model and pc.speed>=450;
/*10*/
SELECT model, price FROM printer where price=(SELECT MAX(price) FROM printer);
/*11*/
SELECT sum(speed)/count(speed) FROM pc;
/*12*/
SELECT AVG(speed) FROM laptop where price>1000;
/*13*/
SELECT AVG(speed) FROM pc, product pr where pr.model=pc.model and pr.maker='hp';
/*14*/
SELECT speed, AVG(price) as price FROM pc group by speed;
/*15*/
SELECT hd FROM pc group by pc.hd having count(pc.hd) >1;
#16
/*17*/
select pr.type, l.model, l.speed from laptop l, product pr where l.model=pr.model and speed<(select min(speed) from pc);
/*18*/
select pro.maker, pri.price from product pro, printer pri where pro.model=pri.model and pri.color='y' and pri.price=(select min(pr1.price) from printer pr1 where pr1.color='y');
/*19*/
select pr.maker, avg(l.screen) from laptop l, product pr where l.model=pr.model group BY pr.maker;
/*20*/
SELECT maker, count(model) as count FROM product pr where type='pc' group by pr.maker having count(pr.model) >3;
/*21*/
SELECT pr.maker, max(pc.price) FROM product pr, pc where pc.model=pr.model group by pr.maker;
/*22*/
select pc.speed, avg(pc.price)as price from pc where pc.speed>600 group by pc.speed;
/*23*/
SELECT distinct pr.maker FROM product pr, pc where pc.model=pr.model and pc.speed>750 and pr.maker in ( SELECT pr.maker FROM product pr, laptop l where l.model=pr.model and l.speed>750);
#24
#25
select DISTINCT maker 
from product 
where maker in (select pro.maker 
	from product pro, pc 
	where pro.model=pc.model 
	  and speed = (select max(speed) 
	  from pc 
	  where ram = (select MIN(ram) from pc)) 
		and ram = (select MIN(ram) from pc));