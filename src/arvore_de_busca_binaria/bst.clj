(ns arvore-de-busca-binaria.bst
  (:require [clojure.string :as str]))

;----------------------------------------------------------------------------------------------------------------------------------
;ATENCAO: Documentação realizada de forma informal, unicamente para reforçar conhecimentos do código, com a finalidade de estudo.
; NÃO deve ser utilizada como parâmetro para documentações formais e/ou profissionais
;----------------------------------------------------------------------------------------------------------------------------------

;Implementacao de uma árvore binaria de busca, com suas funcoes de construcao de arvore, criar nós, buscar nó especifico
;e as funcoes de predecessor(IMPLEMENTADA, EM REFINAMENTO) e sucessor(EM DESENVOLVIMENTO) de cada nó.

(defrecord Node [valor esquerda direita pai]); defini um novo tipo de dado/expressao, que é um mapa contendo as chaves:
                                                ; valor, esquerda, direita e o pai do nó

(defn criar-node
  "funcao que cria um node"
  [valor pai]
  (Node. valor nil nil pai));chama a nova funcao passando os parametros iniciais de um nó base

;Para entender essa funcao é preciso entender coo o codigo observa a arvore.
;Basicamente a funcao vê a árvore como um conjunto de subarvores onde cada nó, juntamente com seus filhos da esquerda e da direita,
;montam uma subarvore          (Raiz)
;                       [esquerda] [direita]
;Tendo em mente a abstração.
(defn inserir-node
  "funcao para inserir um vetor dentro de uma arvore"
  [raiz valor pai]
  (if (nil? raiz);Caso de parada: se a raiz da subárvore for vazia,
    (criar-node valor pai); atribua o nó com o valor desejado e o seu nó pai da subárvore atual
    (if (< valor (:valor raiz));Se o valor que eu quero inserir for menor que o valor da raiz da subárvore atual
      (assoc raiz :esquerda (inserir-node (:esquerda raiz) valor raiz)) ;nós associamos o valor resultante da recursao da funcao, passando agora a observar a subárvore da esquerda da subárvore atual.(resumindo)
      (assoc raiz :direita (inserir-node (:direita raiz) valor raiz))))) ;se não nós associamos o valor resultante da recursao da funcao, passando agora a observar a subárvore da direita da subárvore atual.(resumindo)



(defn criar-arvore
  "funcao que monta uma arvore binaria"
  [valores] ;recebe a lista de valores que iram ser atruibuidas aos nós de acordo com a posicao delas na lista.
  (reduce (fn [arvore valor] (inserir-node arvore valor nil)) nil valores)) ;o reduce serve para reduzir todos os dados em um unico dado.
           ;funcao lambda responsavel por chamar a funcao inserir node, que por sua vez retorna uma arvore(que acumulua os nodes) parcialmente preenchida com os nós, até completar os valores da lista.
;em outras palavras nós utilizamos o reduce para ir iterando sobre a lista e capturando um elemento por vez, esses elementos sao usados para criar os nodes e sao iseridos um por vez na arvore à medida em que são capturados pelo recur.
;em cada insersao o acumulador "arvore" e retornado com o novo nó, até acabar os elementos da lista. (a menor funcao, mas a que precisa de mais abstracao)

 (defn get-node   ; funcao basica para encontrar um node a partir da arvore e do valor do nó
  "funcao auxiliar para predecessor e sucessor"
  [arvore valor-node] ; recebe os argumentos passando a arvore e o nó que desejamos obter.
  (cond ;abrimos uma expressao condicional
    (nil? (:valor arvore)) nil;(caso de parada) se o valor da raiz da sub-arvore observada for nil, quer dizer que ele nao existe
    (= valor-node (:valor arvore)) arvore ;(caso de parada) se o valor da raz da sub-arvore observada for igual ao valor que queremos, retorna essa raiz
    (< valor-node (:valor arvore)) (get-node (:esquerda arvore)  valor-node) ;(caso de recursao) caso o valor do nó for menor q o valor da raiz, realiza a recursividade passando à observar agora a sub-arvore da esquerda.
    :else (get-node (:direita arvore)  valor-node));(caso de recursao) caso o valor do nó for maior q o valor da raiz, realiza a recursividade passando à observar agora a sub-arvore da direita.
  )

(defn get-parent-menor ;retorna o parent que seja menor que o proprio valor do nó
  ;basicamente essa funcao ajuda a subir pelos níveis da arvore binaria de busca acessando os parents dos nós.
  "funcao auxiliar das funcoes predecessore"
  [node] ; recebe o nó
  (cond
    (and (:pai node) (< (:valor node) (:valor (:pai node)))) (get-parent-menor (:pai node)) ;caso o valor do nó seja menor que o do nó pai, sobe um nível acessando o nó pai
    (and (:pai node) (> (:valor node) (:valor (:pai node)))) (:pai node);caso o valor do nó seja maior que o do nó pai, retorna o nó pai
    :else nil));caso contrario, nao existe nó predecessor.

; para entender a funcao predecessor utilizei os conceitos demonstrados no material do profesor João Arthur que está referenciado no readme do repositorio
(defn predecessor
  "funcao para encontrar o nó predecessor"
  [arvore node]
  ;Obtém o nó específico da árvore para realizarmos a verificacao do caso inicial
  (let [node-especifico (get-node arvore node)]
    (if (and (nil? (:esquerda node-especifico)); Verifica se o nó específico não tem filhos (é uma folha) e se é menor que o pai
             (nil? (:direita node-especifico))
             (< (:valor node-especifico) (:valor (:pai node-especifico))))
      (:valor (get-parent-menor node-especifico)); Se for uma folha e menor que o pai, retorna o valor do pai mais próximo menor
      (loop [node-rec node-especifico ; Caso contrário, itera recursivamente na árvore para encontrar o predecessor
             pega-subarvore-esquerda 0]; pegando os argumentos do nó que desejamos identificar o predecessor e uma flag q verifica se, em algum momento, ja acessamos a subarvore da esquerda
        (cond ;abrimos as condicionais para nossos casos de parada e de recursao

          ; Se o nó não tem filho esquerdo, não tem filho direito, não acessou a sbuarvore da esquerda
          ; e seu valor é maior que o valor do pai, retorna o valor do pai
          (and (nil? (:esquerda node-rec)) (nil? (:direita node-rec)) (= pega-subarvore-esquerda 0) (> (:valor node-rec) (:valor (:pai node-rec))) ) (:valor (:pai node-rec))

          ; Se o nó não tem filho esquerdo, tem filho direito, não acessou a sbuarvore da esquerda
          ; e seu valor é maior que o valor do pai, retorna o valor do pai
          (and (nil? (:esquerda node-rec)) (not (nil? (:direita node-rec))) (= pega-subarvore-esquerda 0) (> (:valor node-rec) (:valor (:pai node-rec))) ) (:valor (:pai node-rec))

          ; Se o nó não tem filho direito, e ja acessamos a subarvore da esquerda, retorna o valor do nó atual
          (and (nil? (:direita node-rec)) (= pega-subarvore-esquerda 1)) (:valor node-rec)

          ; Se o nó tem filho esquerdo, e não acessou a asubárvore da esquerda,  itera recursivamente na subárvore esquerda e altera a flag sinalizando que ja acessou a subárvore da esquerda
          (and (not (nil? (:esquerda node-rec))) (= pega-subarvore-esquerda 0)) (recur (:esquerda node-rec) 1)
          ; Se o nó tem filho direito e ja acessou a sbárvore da esquerda, itera recursivamente na subárvore direita
          (and (not (nil? (:direita node-rec))) (= pega-subarvore-esquerda 1)) (recur (:direita node-rec) 1)
          ; Condição padrão caso não caia em nenhum dos casos anteriores indicando que não existe predecessor.
          :else nil)))))



;cria arvore teste (TESTE SIMPLES)
(def arvore-teste (criar-arvore [15 9 31 53 76 2 41 11]))


;Função para imprimir a árvore de forma mais legível tentando siular a construcao da arvore
(defn imprimir-arvore [node nivel]
  (when node
    (imprimir-arvore (:direita node) (inc nivel))
    (println (apply str (repeat nivel "    ")) (:valor node))
    (imprimir-arvore (:esquerda node) (inc nivel))))
(println (imprimir-arvore arvore-teste 0)) ; imprime arvore


;imprime sessao dos valores dos predecessores de cada nó
(println "---------PREDECESSORES---------")
(println (predecessor arvore-teste 2))
(println (predecessor arvore-teste 9))
(println (predecessor arvore-teste 11))
(println (predecessor arvore-teste 15))
(println (predecessor arvore-teste 31))
(println (predecessor arvore-teste 41))
(println (predecessor arvore-teste 53))
(println (predecessor arvore-teste 76))
(println "-------------------------------")

;imprime a descricao da arvore em forma de texto
(defn imprimir-desc-arvore
  "Imprime os nós da árvore, incluindo valor, nó da esquerda, nó da direita e nó pai."
  [node]
  (when node
    (println (format "Valor: %s, Esquerda: %s, Direita: %s, Pai: %s"
                     (:valor node)
                     (if (:esquerda node) (:valor (:esquerda node)) nil)
                     (if (:direita node) (:valor (:direita node)) nil)
                     (if (:pai node) (:valor (:pai node)) nil)))
    (imprimir-desc-arvore (:esquerda node))
    (imprimir-desc-arvore (:direita node))))

(println (imprimir-desc-arvore arvore-teste)) ;imprime descricao da arvore

