# Deploy do Liveb Bank S/A (Ambiente Isolado com Nginx Proxy Manager)

Este é o guia passo a passo oficial para fazer o deploy na sua VPS. Para não conflitar com ambientes existentes (como o NPM, n8n ou Evolution API que ocupam as portas 80, 443 e 8080), as portas foram isoladas.

## 1. Liberação de Firewall da VPS
Antes de tudo, garanta que seu provedor de VPS (Hostinger, etc.) e o `ufw` do Ubuntu permitam tráfego nestas novas portas:
```bash
sudo ufw allow 3020/tcp
sudo ufw allow 8082/tcp
sudo ufw reload
```
- **3020**: Porta do Web Server do Frontend estático (Nginx Alpine).
- **8082**: Porta da API Spring Boot Java.

## 2. Subindo a Arquitetura (Isolada)
Transfira ou clone este projeto para dentro da VPS, **preferencialmente fora de `/opt`** para não cruzar com suas automações em Python:
```bash
cd /root/livebbank_mvp
sudo docker-compose up -d --build
```
Isso fará o `liveb_db`, `liveb_api` e `liveb_frontend` subirem num grupo totalmente à parte do compose principal do servidor.

## 3. Configurando o Domínio `.cloud` no Nginx Proxy Manager
O Docker *rodará* o sistema no IP da máquina, mas é a sua plataforma do NPM (já ativa na porta 81) quem vai gerenciar os domínios para acesso da equipe:

1. Acesse seu painel NPM: `http://72.61.222.34:81`
2. Vá em **Hosts -> Proxy Hosts -> Add Proxy Host**.
3. Crie um host para o visual da aplicação (onde a equipe acessará o HTML):
   - **Domain Names**: `liveb.x7q-prt99.cloud` (ou o que desejar)
   - **Scheme**: `http`
   - **Forward Hostname / IP**: `72.61.222.34` (O IP da sua máquina host)
   - **Forward Port**: `3020`
   - Marque a aba **SSL** e clique em "Request a new SSL Certificate".

*(Opcional - Para a API externa)*
Se a equipe também for interagir diretamente com a API fora do front:
- **Domain Names**: `api-liveb.x7q-prt99.cloud`
- **Scheme**: `http`
- **Forward Hostname**: `72.61.222.34`
- **Forward Port**: `8082`

## 4. Endpoints
- **Frontend** rodando em: `http://localhost:3020`
- **Backend (API)** rodando em: `http://localhost:8082`
- **Banco Postgres** rodando em: `http://localhost:5432`

Pronto! Os testes com os membros do grupo do WhatsApp podem começar simulando como se fosse o domínio da Liveb Bank real. Quando acabarem os testes, basta dar um `sudo docker-compose down` para matar toda a estrutura sem derrubar o seu ecossistema original.
