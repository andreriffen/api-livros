#!/bin/bash

# ==============================================================================
# Script de Testes da API de Livros
# ==============================================================================

BASE_URL="http://localhost:8080"

echo "🚀 Iniciando testes da API de Livros..."
echo ""

# ==============================================================================
# TESTES DE AUTORES
# ==============================================================================

echo "📚 ========== AUTORES =========="
echo ""

echo "➤ 1. POST /autores - Criando William Gibson"
curl -s -X POST "$BASE_URL/autores" \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "William Gibson",
  "email": "gibson@sprawl.net",
  "nacionalidade": "Americano-Canadense"
}' | jq '.'
echo ""

echo "➤ 2. POST /autores - Criando Neal Stephenson"
curl -s -X POST "$BASE_URL/autores" \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Neal Stephenson",
  "email": "neal@metaverse.com",
  "nacionalidade": "Americano"
}' | jq '.'
echo ""

echo "➤ 3. GET /autores - Listando todos os autores"
curl -s "$BASE_URL/autores" | jq '.'
echo ""

echo "➤ 4. GET /autores/1 - Buscando autor por ID"
curl -s "$BASE_URL/autores/1" | jq '.'
echo ""

echo "➤ 5. PUT /autores/1 - Atualizando autor"
curl -s -X PUT "$BASE_URL/autores/1" \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "William Ford Gibson",
  "email": "gibson@sprawl.net",
  "nacionalidade": "Americano-Canadense"
}' | jq '.'
echo ""

# ==============================================================================
# TESTES DE EDITORAS
# ==============================================================================

echo "🏢 ========== EDITORAS =========="
echo ""

echo "➤ 6. POST /editoras - Criando editora Aleph"
curl -s -X POST "$BASE_URL/editoras" \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Aleph",
  "email": "contato@aleph.com.br",
  "cidade": "São Paulo",
  "estado": "SP"
}' | jq '.'
echo ""

echo "➤ 7. POST /editoras - Criando editora Suma"
curl -s -X POST "$BASE_URL/editoras" \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Suma",
  "email": "contato@suma.com.br",
  "cidade": "Rio de Janeiro",
  "estado": "RJ"
}' | jq '.'
echo ""

echo "➤ 8. GET /editoras - Listando todas as editoras"
curl -s "$BASE_URL/editoras" | jq '.'
echo ""

echo "➤ 9. GET /editoras/1 - Buscando editora por ID"
curl -s "$BASE_URL/editoras/1" | jq '.'
echo ""

echo "➤ 10. PUT /editoras/1 - Atualizando editora"
curl -s -X PUT "$BASE_URL/editoras/1" \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Aleph",
  "email": "sac@aleph.com.br",
  "cidade": "São Paulo",
  "estado": "SP"
}' | jq '.'
echo ""

# ==============================================================================
# TESTES DE LIVROS
# ==============================================================================

echo "📖 ========== LIVROS =========="
echo ""

echo "➤ 11. POST /livros - Criando Neuromancer"
curl -s -X POST "$BASE_URL/livros" \
  -H 'Content-Type: application/json' \
  -d '{
  "titulo": "Neuromancer",
  "isbn": "978-8576570080",
  "precoDeCusto": 45.00,
  "margemDeLucro": 0.40,
  "status": "DISPONIVEL",
  "autorId": 1,
  "editoraId": 1
}' | jq '.'
echo ""

echo "➤ 12. POST /livros - Criando Snow Crash"
curl -s -X POST "$BASE_URL/livros" \
  -H 'Content-Type: application/json' \
  -d '{
  "titulo": "Snow Crash",
  "isbn": "978-8576572459",
  "precoDeCusto": 50.00,
  "margemDeLucro": 0.35,
  "status": "DISPONIVEL",
  "autorId": 2,
  "editoraId": 1
}' | jq '.'
echo ""

echo "➤ 13. GET /livros - Listando todos os livros"
curl -s "$BASE_URL/livros" | jq '.'
echo ""

echo "➤ 14. GET /livros/1 - Buscando livro por ID"
curl -s "$BASE_URL/livros/1" | jq '.'
echo ""

echo "➤ 15. PUT /livros/1 - Atualizando status do livro para ESGOTADO"
curl -s -X PUT "$BASE_URL/livros/1" \
  -H 'Content-Type: application/json' \
  -d '{
  "titulo": "Neuromancer",
  "isbn": "978-8576570080",
  "precoDeCusto": 45.00,
  "margemDeLucro": 0.40,
  "status": "ESGOTADO",
  "autorId": 1,
  "editoraId": 1
}' | jq '.'
echo ""

# ==============================================================================
# TESTES DE VALIDAÇÃO E ERROS
# ==============================================================================

echo "⚠️  ========== TESTES DE VALIDAÇÃO =========="
echo ""

echo "➤ 16. POST /autores - Tentando criar autor com email duplicado (deve falhar)"
curl -s -X POST "$BASE_URL/autores" \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Outro Autor",
  "email": "gibson@sprawl.net",
  "nacionalidade": "Brasileiro"
}' | jq '.'
echo ""

echo "➤ 17. GET /autores/999 - Buscando autor inexistente (deve retornar 404)"
curl -s "$BASE_URL/autores/999" | jq '.'
echo ""

echo "➤ 18. POST /livros - Tentando criar livro com ISBN duplicado (deve falhar)"
curl -s -X POST "$BASE_URL/livros" \
  -H 'Content-Type: application/json' \
  -d '{
  "titulo": "Outro Livro",
  "isbn": "978-8576570080",
  "precoDeCusto": 30.00,
  "margemDeLucro": 0.30,
  "status": "DISPONIVEL",
  "autorId": 1,
  "editoraId": 1
}' | jq '.'
echo ""

# ==============================================================================
# TESTES DE DELEÇÃO
# ==============================================================================

echo "🗑️  ========== TESTES DE DELEÇÃO =========="
echo ""

echo "➤ 19. DELETE /livros/2 - Deletando livro Snow Crash"
curl -s -X DELETE "$BASE_URL/livros/2" -w "\nHTTP Status: %{http_code}\n"
echo ""

echo "➤ 20. GET /livros - Verificando que o livro foi deletado"
curl -s "$BASE_URL/livros" | jq '.'
echo ""

echo "✅ Testes concluídos!"
