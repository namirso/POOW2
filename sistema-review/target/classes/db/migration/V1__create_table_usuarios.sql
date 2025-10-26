create table usuarios (
                        id serial not null primary key,
                        uuid UUID DEFAULT gen_random_uuid(),
                        nome varchar(50) not null,
                        email varchar(100) not null unique,
                        senha varchar(20) not null,
                        permissao varchar(2) not null
)