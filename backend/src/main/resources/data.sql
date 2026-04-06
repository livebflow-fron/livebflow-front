-- Limpa as tabelas (opcional, para garantir que não duplique se recriar)
TRUNCATE TABLE propostas, imobiliarias, usuarios RESTART IDENTITY CASCADE;

-- Criando um usuário de teste (A senha '123456' no formato puro, caso seu sistema valide texto puro no MVP, senao tera que criar a hash e ajustar)
INSERT INTO usuarios (email, senha, perfil) VALUES
('consultor@livebbank.com.br', '123456', 'CONSULTOR'),
('admin@livebbank.com.br', 'admin123', 'ADMIN');

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
