/*1*/
select model, speed, hd from pc where price<500;
/*2*/
SELECT maker FROM product where product.type='printer';
/*3*/
SELECT model,hd,screen FROM laptop WHERE price>1000 
/*4*/
SELECT * FROM printer pr WHERE pr.color='y';
/*5*/
SELECT pc.model, pc.speed, pc.hd FROM pc WHERE price<600 AND (cd='12' OR cd='24');
/*6*/
SELECT pr.maker, l.speed FROM product pr, laptop l WHERE pr.model=l.model and l.hd>1000 
/*7*/
SELECT pro.model, l.price FROM product pro, laptop l WHERE pro.maker = 'hp' AND pro.model = l.model
union
SELECT pro.model, pc.price FROM product pro, pc WHERE pro.maker = 'hp' AND pro.model = pc.model
union
SELECT pro.model, printer.price FROM product pro, printer WHERE pro.maker = 'hp' AND pro.model = printer.model;
/*8*/
