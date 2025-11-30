#!/bin/bash

# ==============================================================================
# Script para Popular a API de Livros com Dados Iniciais
# ==============================================================================
# Este script cria autores, editoras e livros clássicos da computação
# ==============================================================================

BASE_URL="http://localhost:8080"

echo "╔════════════════════════════════════════════════════════════════╗"
echo "║         📚 Populando API de Livros - Dados Iniciais            ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""

# ==============================================================================
# CRIANDO AUTORES
# ==============================================================================

echo "👥 Criando Autores..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""

echo "📝 Criando autor: Gang of Four (GoF)"
AUTOR1=$(curl -s -X POST "$BASE_URL/autores" \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Gang of Four",
  "email": "gof@designpatterns.com",
  "nacionalidade": "Internacional"
}' | jq -r '.id')
echo "   ✅ Autor criado com ID: $AUTOR1"
echo ""

echo "📝 Criando autor: Robert C. Martin (Uncle Bob)"
AUTOR2=$(curl -s -X POST "$BASE_URL/autores" \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Robert C. Martin",
  "email": "unclebob@cleancoder.com",
  "nacionalidade": "Americano"
}' | jq -r '.id')
echo "   ✅ Autor criado com ID: $AUTOR2"
echo ""

echo "📝 Criando autor: Martin Fowler"
AUTOR3=$(curl -s -X POST "$BASE_URL/autores" \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Martin Fowler",
  "email": "martin@refactoring.com",
  "nacionalidade": "Britânico"
}' | jq -r '.id')
echo "   ✅ Autor criado com ID: $AUTOR3"
echo ""

echo "📝 Criando autor: Eric Evans"
AUTOR4=$(curl -s -X POST "$BASE_URL/autores" \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Eric Evans",
  "email": "eric@domainlanguage.com",
  "nacionalidade": "Americano"
}' | jq -r '.id')
echo "   ✅ Autor criado com ID: $AUTOR4"
echo ""

# ==============================================================================
# CRIANDO EDITORAS
# ==============================================================================

echo "🏢 Criando Editoras..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""

echo "📝 Criando editora: Addison-Wesley"
EDITORA1=$(curl -s -X POST "$BASE_URL/editoras" \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Addison-Wesley",
  "email": "contato@addison-wesley.com",
  "cidade": "Boston",
  "estado": "MA"
}' | jq -r '.id')
echo "   ✅ Editora criada com ID: $EDITORA1"
echo ""

echo "📝 Criando editora: O'Reilly Media"
EDITORA2=$(curl -s -X POST "$BASE_URL/editoras" \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "OReilly Media",
  "email": "contato@oreilly.com",
  "cidade": "Sebastopol",
  "estado": "CA"
}' | jq -r '.id')
echo "   ✅ Editora criada com ID: $EDITORA2"
echo ""

echo "📝 Criando editora: Bookman (Brasil)"
EDITORA3=$(curl -s -X POST "$BASE_URL/editoras" \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "Bookman",
  "email": "contato@bookman.com.br",
  "cidade": "Porto Alegre",
  "estado": "RS"
}' | jq -r '.id')
echo "   ✅ Editora criada com ID: $EDITORA3"
echo ""

# ==============================================================================
# CRIANDO LIVROS
# ==============================================================================

echo "📖 Criando Livros..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""

echo "📝 Criando livro: Design Patterns: Elements of Reusable Object-Oriented Software"
LIVRO1=$(curl -s -X POST "$BASE_URL/livros" \
  -H 'Content-Type: application/json' \
  -d "{
  \"titulo\": \"Design Patterns: Elements of Reusable Object-Oriented Software\",
  \"isbn\": \"978-0201633610\",
  \"precoDeCusto\": 120.00,
  \"margemDeLucro\": 0.50,
  \"status\": \"DISPONIVEL\",
  \"autorId\": $AUTOR1,
  \"editoraId\": $EDITORA1
}" | jq -r '.id')
echo "   ✅ Livro criado com ID: $LIVRO1"
echo "   💰 Preço de Custo: R$ 120,00 | Margem: 50% | Preço de Venda: R$ 180,00"
echo ""

echo "📝 Criando livro: Clean Code: A Handbook of Agile Software Craftsmanship"
LIVRO2=$(curl -s -X POST "$BASE_URL/livros" \
  -H 'Content-Type: application/json' \
  -d "{
  \"titulo\": \"Clean Code: A Handbook of Agile Software Craftsmanship\",
  \"isbn\": \"978-0132350884\",
  \"precoDeCusto\": 85.00,
  \"margemDeLucro\": 0.45,
  \"status\": \"DISPONIVEL\",
  \"autorId\": $AUTOR2,
  \"editoraId\": $EDITORA1
}" | jq -r '.id')
echo "   ✅ Livro criado com ID: $LIVRO2"
echo "   💰 Preço de Custo: R$ 85,00 | Margem: 45% | Preço de Venda: R$ 123,25"
echo ""

echo "📝 Criando livro: Refactoring: Improving the Design of Existing Code"
LIVRO3=$(curl -s -X POST "$BASE_URL/livros" \
  -H 'Content-Type: application/json' \
  -d "{
  \"titulo\": \"Refactoring: Improving the Design of Existing Code\",
  \"isbn\": \"978-0201485677\",
  \"precoDeCusto\": 95.00,
  \"margemDeLucro\": 0.40,
  \"status\": \"DISPONIVEL\",
  \"autorId\": $AUTOR3,
  \"editoraId\": $EDITORA1
}" | jq -r '.id')
echo "   ✅ Livro criado com ID: $LIVRO3"
echo "   💰 Preço de Custo: R$ 95,00 | Margem: 40% | Preço de Venda: R$ 133,00"
echo ""

echo "📝 Criando livro: Domain-Driven Design: Tackling Complexity in the Heart of Software"
LIVRO4=$(curl -s -X POST "$BASE_URL/livros" \
  -H 'Content-Type: application/json' \
  -d "{
  \"titulo\": \"Domain-Driven Design: Tackling Complexity in the Heart of Software\",
  \"isbn\": \"978-0321125217\",
  \"precoDeCusto\": 110.00,
  \"margemDeLucro\": 0.48,
  \"status\": \"DISPONIVEL\",
  \"autorId\": $AUTOR4,
  \"editoraId\": $EDITORA1
}" | jq -r '.id')
echo "   ✅ Livro criado com ID: $LIVRO4"
echo "   💰 Preço de Custo: R$ 110,00 | Margem: 48% | Preço de Venda: R$ 162,80"
echo ""

echo "📝 Criando livro: The Pragmatic Programmer (Edição Brasileira)"
LIVRO5=$(curl -s -X POST "$BASE_URL/livros" \
  -H 'Content-Type: application/json' \
  -d "{
  \"titulo\": \"The Pragmatic Programmer\",
  \"isbn\": \"978-8577807017\",
  \"precoDeCusto\": 75.00,
  \"margemDeLucro\": 0.35,
  \"status\": \"DISPONIVEL\",
  \"autorId\": $AUTOR2,
  \"editoraId\": $EDITORA3
}" | jq -r '.id')
echo "   ✅ Livro criado com ID: $LIVRO5"
echo "   💰 Preço de Custo: R$ 75,00 | Margem: 35% | Preço de Venda: R$ 101,25"
echo ""

# ==============================================================================
# RESUMO FINAL
# ==============================================================================

echo ""
echo "╔════════════════════════════════════════════════════════════════╗"
echo "║                    ✅ DADOS POPULADOS COM SUCESSO!             ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""
echo "📊 Resumo:"
echo "   • Autores criados: 4"
echo "   • Editoras criadas: 3"
echo "   • Livros criados: 5"
echo ""
echo "🔍 Para visualizar os dados:"
echo "   curl http://localhost:8080/autores | jq '.'"
echo "   curl http://localhost:8080/editoras | jq '.'"
echo "   curl http://localhost:8080/livros | jq '.'"
echo ""
echo "📖 Acesse o Swagger UI em:"
echo "   http://localhost:8080/swagger-ui/index.html"
echo ""
echo "Fim"
echo ""
echo "Autor: Andre Guilherme Barreto de Farias"
echo "Matrícula: 202111701842"
echo "TÉCNICO EM DESENVOLVIMENTO DE SISTEMAS [3010]/FLN - Técnico - Subsequente - Florianópolis"
echo ""
echo "================================================================="
echo ""

echo "╔════════════════════════════════════════════════════════════════╗"
echo "║  💡 Execute agora o script de testes: ./test-api.sh            ║"
echo "╚════════════════════════════════════════════════════════════════╝"
