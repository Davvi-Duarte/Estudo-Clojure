(ns algoritmo-busca.binaria)
(defn busca-binaria
  "funcao para definir busca binaria"
  [vetor elemento]
  (let [tamanho (count vetor)]
    (loop [inicio 0
           fim (dec tamanho)]
      (if (> inicio fim)
        nil
        (let [meio (quot (+ inicio fim) 2)
              valor-meio (nth vetor meio)]
          (cond
            (= elemento valor-meio) meio
            (< elemento valor-meio) (recur inicio (dec meio))
            :else (recur (inc meio) fim))
          )
        )
      )
    )
  )

; Exemplo de uso da função de busca binária:
(def vetor [1 2 3 4 5 6 7 8 9]) ; Define um vetor ordenado.
(def elemento 9) ; Define o elemento que queremos encontrar.

(def indice (busca-binaria vetor elemento)) ; Chama a função de busca binária para encontrar o índice do elemento no vetor.

(if indice
  (println (str "Elemento encontrado no índice: " indice)) ; Se o índice não for nil, imprime o índice do elemento encontrado.
  (println "Elemento não encontrado")) ; Caso contrário, imprime que o elemento não foi encontrado.
