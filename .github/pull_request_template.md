## 🧩 Tipo de Mudança
- [ ] **Feat** (nova funcionalidade)
- [ ] **Fix** (correção de bug)
- [ ] **Refactor** (refatoração de código)
- [ ] **Docs** (documentação)
- [ ] **Test** (testes)
- [ ] **Chore** (tarefas gerais, configs, etc.)

---

## 🔍 Descrição da PR
...

---

## 🔗 Issues Relacionadas
- Ex: Closes #23

---

## ✅ Checklist de Qualidade

* [ ] Meu código segue as diretrizes de estilo deste projeto.
* [ ] Eu realizei uma auto-revisão (self-review) do meu próprio código.
* [ ] Eu testei esta mudança **localmente** e ela funciona como esperado.
* [ ] A documentação do Swagger foi atualizada (se aplicável).

---

## 🌎 Checklist de Produção (Render)

* [ ] **NENHUMA** nova variável de ambiente é necessária no Render.
* [ ] **ALERTA!** As seguintes variáveis de ambiente precisam ser **adicionadas** ou **atualizadas** no dashboard do Render **ANTES** de fazer o merge deste PR:
    * `[NOME_DA_VARIAVEL_1]` (ex: `SPRING_DATA_MONGODB_URI`)

---

## 🧪 Como Testar (Passos para Reprodução)
### Passos do Teste

1.  **Execute a aplicação** localmente (`mvn spring-boot:run`) ou acesse a URL do Swagger em produção: `https://api-redis-ok9n.onrender.com/swagger-ui/index.html`

2.  **Execute a nova funcionalidade:**
    * **Endpoint:** `[MÉTODO] /api/v1/endpoint`
    * **Corpo (Payload):**
    ```json
    {
      "chave": "valor"
    }
    ```
