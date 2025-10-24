CREATE TABLE obras (
                       id serial NOT NULL PRIMARY KEY,
                       nome varchar(255) NOT NULL,
                       direcao varchar(255) NOT NULL,
                       url_imagem varchar(500),

                       idtipo integer NOT NULL,
                       CONSTRAINT fk_tipo
                           FOREIGN KEY(idtipo)
                               REFERENCES tipos(id)
                               ON DELETE RESTRICT
);