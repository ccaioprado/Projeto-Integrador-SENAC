create database dbloja;

use dbloja;

create table funcionario(
idfuncionario bigInt primary key auto_increment,
nomef varchar(50) not null,
usuariof varchar(50) not null,
senhaf varchar(50) not null
);

insert into funcionario(nomef,usuariof,senhaf) values('Caio','caio',md5('123456'));

insert into funcionario(nomef,usuariof,senhaf) values('Matheus','matheus',md5('123456'));

select * from funcionario;

create table cliente(
idcliente bigInt primary key auto_increment,
nomec varchar(50) not null,
telefonec varchar(50) not null
);

insert into cliente(nomec,telefonec) values('Gustavo','11112222');

insert into cliente(nomec,telefonec) values('Wesley','333334444');

insert into cliente(nomec,telefonec) values('Cleverson','555556666');

insert into cliente(nomec,telefonec) values('Travis Scott','777778888');

insert into cliente(nomec,telefonec) values('Roddy Ricch','888889999');

insert into cliente(nomec,telefonec) values('The Weeknd','999990000');

insert into cliente(nomec,telefonec) values('Bizzy Banks','000001111');

select * from cliente;

create table estoque(
idproduto bigInt auto_increment primary key,
produto varchar(50) not null,
quantidade varchar(50) not null,
tamanho varchar(50) not null,
valor varchar(50) not null
);

drop table estoque;

insert into estoque(produto,quantidade,tamanho,valor) values('Air Jordan 1','1','40','3000');

insert into estoque(produto,quantidade,tamanho,valor) values('Air Max 97','2','39','900');

insert into estoque(produto,quantidade,tamanho,valor) values('Air Force 1','1','40','950');

insert into estoque(produto,quantidade,tamanho,valor) values('Air Max 270','6','42','1200');

insert into estoque(produto,quantidade,tamanho,valor) values('Air Max 720','1','38','1500');

insert into estoque(produto,quantidade,tamanho,valor) values('Air Max 90','1','40','800');

insert into estoque(produto,quantidade,tamanho,valor) values('Air 2090','1','41','1250');

insert into estoque(produto,quantidade,tamanho,valor) values('Air Max 200','1','40','1000');

insert into estoque(produto,quantidade,tamanho,valor) values('Air Jordan 3','1','40','45000');

insert into estoque(produto,quantidade,tamanho,valor) values('Puma Suede','1','40','300');

