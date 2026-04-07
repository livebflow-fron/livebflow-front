-- Limpa as tabelas (opcional, para garantir que não duplique se recriar)
TRUNCATE TABLE propostas, imobiliarias, usuarios RESTART IDENTITY CASCADE;

-- Criando um usuário de teste (A senha agora está hasheada em SHA-256 para o MVP funcionar com a verificação)
INSERT INTO usuarios (email, senha, perfil) VALUES
('consultor@livebbank.com.br', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'CONSULTOR'), -- 123456
('admin@livebbank.com.br', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'ADMIN'), -- admin123
('teste1@livebbank.com.br', '553e4b77dc1c1f7601ad02e960f2e0ff30dcb9747cd49fd632f05a96db4da898', 'CONSULTOR'), -- teste1
('teste2@livebbank.com.br', '24d4b8fdfa0684f8db9f1b954df0b0ee111cc21b8bbfcd8da1fedced3c896e38', 'CONSULTOR'); -- teste2

-- Criando Imobiliárias fictícias
INSERT INTO imobiliarias (razao_social, cnpj, email, telefone) VALUES
('Imobiliária Sol Nascente LTDA', '12.345.678/0001-90', 'contato@solnascente.com.br', '(11) 98765-4321'),
('Vila Rica Imóveis', '98.765.432/0001-10', 'locacao@vilaricaimoveis.com.br', '(11) 91234-5678'),
('Premier Locações Garantidas', '45.678.901/0001-55', 'parceria@premierloc.com.br', '(21) 99999-8888');

-- Criando Propostas Fictícias para popular o Kanban
INSERT INTO propostas (imobiliaria_id, tipo_locacao, valor_aluguel, status_kanban, etiqueta_urgencia) VALUES
(1, 'Residencial', 2500.00, 'A Fazer', 'Alta Prioridade'),
(1, 'Comercial', 8500.00, 'Em Análise', 'Normal'),
(2, 'Residencial', 1800.00, 'Aprovada', 'Normal'),
(2, 'Residencial', 3200.00, 'A Fazer', 'Baixa'),
(3, 'Comercial', 15000.00, 'Concluída', 'Alta Prioridade'),
(3, 'Residencial', 4500.00, 'Em Análise', 'Urgente');
