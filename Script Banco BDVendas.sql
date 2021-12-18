CREATE DATABASE VendaEstoque;


CREATE USER 'Mauro'@'%' IDENTIFIED BY '123';

GRANT ALL ON *.* TO 'Mauro'@'%' WITH GRANT OPTION;


flush privileges;


USE BDVENDAS;
SELECT * from loja.tb_clientes;
update table loja.tb_clientes rename telefone to telefone int;  
/***** TABELA CLIENTES *****/
CREATE TABLE loja.tb_clientes (
  id bigserial primary key,
  nome varchar(100),
  rg varchar (30),
  cpf varchar (20),
  email varchar(200),
  telefone varchar(30),
  celular varchar(30),
  cep varchar(100),
  endereco varchar (255),
  numero int,
  complemento varchar (200),
  bairro varchar (100),
  cidade varchar (100),
  estado varchar (2)
);
/*****************/

/***** TABELA FORNECEDORES *****/
CREATE TABLE loja.tb_fornecedores (
  id bigserial primary key,
  nome varchar(100),
  cnpj varchar (100),
  email varchar(200),
  telefone varchar(30),
  celular varchar(30),
  cep varchar(100),
  endereco varchar (255),
  numero int,
  complemento varchar (200),
  bairro varchar (100),
  cidade varchar (100),
  estado varchar (2)
);
/*****************/

/***** TABELA FUNCIONARIOS *****/
CREATE TABLE loja.tb_funcionarios (
  id bigserial primary key,
  nome varchar(100),
  rg varchar (30),
  cpf varchar (20),
  email varchar(200),
  senha varchar(10),
  cargo varchar(100),
  nivel_acesso varchar(50),
  telefone varchar(30),
  celular varchar(30),
  cep varchar(100),
  endereco varchar (255),
  numero int,
  complemento varchar (200),
  bairro varchar (100),
  cidade varchar (100),
  estado varchar (2)
);
/*****************/


/***** TABELA PRODUTOS *****/
CREATE TABLE loja.tb_produtos (
  id bigserial primary key,
  descricao varchar(100),
  preco decimal (10,2),
  qtd_estoque int,
  for_id int,

  FOREIGN KEY (for_id) REFERENCES loja.tb_fornecedores(id)
);
/*****************/

/***** TABELA VENDAS *****/
CREATE TABLE loja.tb_vendas (
  id bigserial primary key,
  cliente_id int,
  data_venda date,
  total_venda decimal (10,2),
  observacoes text,

  FOREIGN KEY (cliente_id) REFERENCES loja.tb_clientes(id)
);
/*****************/

/***** TABELA ITENS_VENDAS *****/
CREATE TABLE loja.tb_itensvendas (
  id bigserial primary key,
  venda_id int,
  produto_id int,
  qtd int,
  subtotal decimal (10,2),

  FOREIGN KEY (venda_id) REFERENCES loja.tb_vendas(id),
  FOREIGN KEY (produto_id) REFERENCES loja.tb_produtos(id)
);
/*****************/


select * from tb_clientes where nome like 'a%';