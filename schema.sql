
-- SCHEMA DO SISTEMA DE RESERVAS DE RESTAURANTES

-- TABELAS
CREATE TABLE usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE restaurante (
    id_restaurante SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    capacidade INT NOT NULL,
    horario_abertura TIME NOT NULL,
    horario_fechamento TIME NOT NULL,
    imagem_url VARCHAR(255) NOT NULL
);

CREATE TABLE pratos_principais (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    avaliacao DECIMAL(3,2) CHECK (avaliacao BETWEEN 0 AND 5),
    imagem_url VARCHAR(255) NOT NULL
);

CREATE TABLE reserva (
    id_reserva SERIAL PRIMARY KEY,
    data_reserva DATE NOT NULL,
    hora_reserva TIME NOT NULL,
    numero_pessoas INT NOT NULL,
    nome_responsavel VARCHAR(255) NOT NULL,
    cpf_responsavel VARCHAR(14) NOT NULL,
    telefone_responsavel VARCHAR(20) NOT NULL,
    id_restaurante INT NOT NULL REFERENCES restaurante(id_restaurante),
    id_usuario INT REFERENCES usuario(id_usuario)
);

-- Valores ficticios para a tabela de restaurantes
INSERT INTO restaurante (id_restaurante, nome, endereco, capacidade, horario_abertura, horario_fechamento, imagem_url) VALUES
(1, 'Bistro da Esquina', 'Rua das Flores, 123', 50, '11:00', '22:00', 'bistro_da_esquina.jpg'),
(2, 'Cantina Italiana', 'Av. Itália, 456', 70, '12:00', '23:00', 'cantina_italiana.jpg'),
(3, 'Churrascaria Gaúcha', 'Rua do Churrasco, 789', 120, '18:00', '00:00', 'churrascaria_gaucha.jpg'),
(4, 'Sushi & Sashimi', 'Av. do Sushi, 321', 40, '11:30', '22:30', 'sushi_sashimi.jpg'),
(5, 'Café Central', 'Praça Central, 10', 30, '07:00', '20:00', 'cafe_central.jpg');

-- FUNÇÕES E TRIGGERS

-- Verificação de horário de funcionamento
CREATE OR REPLACE FUNCTION verificar_horario_reserva()
RETURNS TRIGGER AS $$
DECLARE
    horario_abre TIME;
    horario_fecha TIME;
BEGIN
    SELECT horario_abertura, horario_fechamento
    INTO horario_abre, horario_fecha
    FROM restaurante
    WHERE id_restaurante = NEW.id_restaurante;

    IF NEW.hora_reserva < horario_abre OR NEW.hora_reserva > horario_fecha THEN
        RAISE EXCEPTION 'Horário da reserva fora do horário de funcionamento do restaurante.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_horario_reserva
BEFORE INSERT OR UPDATE ON reserva
FOR EACH ROW
EXECUTE FUNCTION verificar_horario_reserva();

-- Validação de avaliação
CREATE OR REPLACE FUNCTION validar_avaliacao_prato()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.avaliacao < 0 OR NEW.avaliacao > 5 THEN
        RAISE EXCEPTION 'Avaliação deve estar entre 0 e 5.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_validar_avaliacao
BEFORE INSERT OR UPDATE ON pratos_principais
FOR EACH ROW
EXECUTE FUNCTION validar_avaliacao_prato();

-- PROCEDURES

-- Criar reserva
CREATE OR REPLACE PROCEDURE criar_reserva(
    p_data DATE,
    p_hora TIME,
    p_num_pessoas INT,
    p_nome_resp VARCHAR,
    p_cpf_resp VARCHAR,
    p_tel_resp VARCHAR,
    p_id_rest INT,
    p_id_usuario INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO reserva (
        data_reserva, hora_reserva, numero_pessoas,
        nome_responsavel, cpf_responsavel, telefone_responsavel,
        id_restaurante, id_usuario
    )
    VALUES (
        p_data, p_hora, p_num_pessoas,
        p_nome_resp, p_cpf_resp, p_tel_resp,
        p_id_rest, p_id_usuario
    );
END;
$$;

-- Atualizar capacidade do restaurante
CREATE OR REPLACE PROCEDURE atualizar_capacidade_restaurante(
    p_id_rest INT,
    p_nova_capacidade INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE restaurante
    SET capacidade = p_nova_capacidade
    WHERE id_restaurante = p_id_rest;
END;
$$;

-- FUNÇÕES AUXILIARES

-- Avaliação média dos pratos
CREATE OR REPLACE FUNCTION avaliacao_media()
RETURNS NUMERIC(3,2) AS $$
DECLARE
    media NUMERIC(3,2);
BEGIN
    SELECT AVG(avaliacao)::NUMERIC(3,2)
    INTO media
    FROM pratos_principais;
    RETURN media;
END;
$$ LANGUAGE plpgsql;

-- Total de reservas por restaurante
CREATE OR REPLACE FUNCTION total_reservas_restaurante(p_id_rest INT)
RETURNS INT AS $$
DECLARE
    total INT;
BEGIN
    SELECT COUNT(*) INTO total
    FROM reserva
    WHERE id_restaurante = p_id_rest;
    RETURN total;
END;
$$ LANGUAGE plpgsql;
