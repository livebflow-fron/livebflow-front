CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    perfil VARCHAR(50) DEFAULT 'CONSULTOR'
);

CREATE TABLE IF NOT EXISTS imobiliarias (
    id SERIAL PRIMARY KEY,
    razao_social VARCHAR(150) NOT NULL,
    cnpj VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(100),
    telefone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS propostas (
    id SERIAL PRIMARY KEY,
    imobiliaria_id INT,
    tipo_locacao VARCHAR(50),
    valor_aluguel DECIMAL(10,2),
    status_kanban VARCHAR(50) DEFAULT 'A Fazer',
    etiqueta_urgencia VARCHAR(100),
    CONSTRAINT fk_imobiliaria
        FOREIGN KEY (imobiliaria_id)
        REFERENCES imobiliarias(id)
);
