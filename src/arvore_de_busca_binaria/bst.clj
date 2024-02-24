(ns arvore-de-busca-binaria.bst
  (:require [clojure.string :as str]))


(defrecord Node [valor esquerda direita parent])

(defn criar-node
  "funcao que cria um node"
  [valor parent]
  (Node. valor nil nil parent))

(defn inserir-node
  "docstring"
  [raiz valor parent]
  (if (nil? raiz)
    (criar-node valor parent)
    (if (< valor (:valor raiz))
      (assoc raiz :esquerda (inserir-node (:esquerda raiz) valor raiz))
      (assoc raiz :direita (inserir-node (:direita raiz) valor raiz)))))

(defn criar-arvore
  "funcao que monta uma arvore binaria"
  [valores]
  (reduce (fn [tree valor] (inserir-node tree valor nil)) nil valores))


;teste
(def arvore-teste (criar-arvore [5 3 8 7 2 4 1 6]))



;Função para imprimir a árvore de forma mais legível
(defn imprimir-arvore [node nivel]
  (when node
    (imprimir-arvore (:direita node) (inc nivel))
    (println (apply str (repeat nivel "    ")) (:valor node))
    (imprimir-arvore (:esquerda node) (inc nivel))))
(println (imprimir-arvore arvore-teste 0))


(defn imprimir-desc-arvore
  "Imprime os nós da árvore, incluindo valor, nó da esquerda, nó da direita e nó pai."
  [node]
  (when node
    (println (format "Valor: %s, Esquerda: %s, Direita: %s, Pai: %s"
                     (:valor node)
                     (if (:esquerda node) (:valor (:esquerda node)) nil)
                     (if (:direita node) (:valor (:direita node)) nil)
                     (if (:parent node) (:valor (:parent node)) nil)))
    (imprimir-desc-arvore (:esquerda node))
    (imprimir-desc-arvore (:direita node))))

(println (imprimir-desc-arvore arvore-teste))