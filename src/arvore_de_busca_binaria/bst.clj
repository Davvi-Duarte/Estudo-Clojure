(ns arvore-de-busca-binaria.bst
  (:require [clojure.string :as str]))


(defrecord Node [valor esquerda direita])

(defn criar-node
  "funcao que cria um node"
  [valor]
  (Node. valor nil nil ))

(defn inserir-node
  "docstring"
  [raiz valor]
  (if (nil? raiz)
    (criar-node valor)
    (if (< valor (:valor raiz))
      (assoc raiz :esquerda (inserir-node (:esquerda raiz) valor))
      (assoc raiz :direita (inserir-node (:direita raiz) valor))))
  )

(defn criar-arvore
  "funcao que monta uma arvore binaria"
  [valores]
  (reduce inserir-node nil valores)
  )

;teste
(def arvore-teste (criar-arvore [5 3 8 7 2 4 1 6]))



;Função para imprimir a árvore de forma mais legível
(defn imprimir-arvore [node nivel]
  (when node
    (imprimir-arvore (:direita node) (inc nivel))
    (println (apply str (repeat nivel "    ")) (:valor node))
    (imprimir-arvore (:esquerda node) (inc nivel))))
(println (imprimir-arvore arvore-teste 0) )