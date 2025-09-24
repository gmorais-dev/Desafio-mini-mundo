CREATE TYPE status_entrega AS ENUM ('PENDENTE', 'EM_ANDAMENTO', 'CONCLUIDA', 'CANCELADA');

CREATE TABLE enderecos (
    id BIGSERIAL PRIMARY KEY,
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255),
    cep VARCHAR(20)
);

CREATE TABLE clientes (
    id BIGSERIAL PRIMARY KEY,
    nome_ou_razao_social VARCHAR(255) NOT NULL,
    cpf_ou_cnpj VARCHAR(20) NOT NULL,
    endereco_id BIGINT,
    CONSTRAINT fk_clientes_endereco FOREIGN KEY (endereco_id) REFERENCES enderecos(id)
);

CREATE TABLE entregas (
    id BIGSERIAL PRIMARY KEY,
    remetente_id BIGINT NOT NULL,
    destinatario_id BIGINT NOT NULL,
    status status_entrega NOT NULL,
    CONSTRAINT fk_entregas_remetente FOREIGN KEY (remetente_id) REFERENCES clientes(id),
    CONSTRAINT fk_entregas_destinatario FOREIGN KEY (destinatario_id) REFERENCES clientes(id)
);

CREATE TABLE mercadorias (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    peso DOUBLE PRECISION,
    volume DOUBLE PRECISION,
    valor DOUBLE PRECISION,
    entrega_id BIGINT,
    CONSTRAINT fk_mercadorias_entrega FOREIGN KEY (entrega_id) REFERENCES entregas(id)
);