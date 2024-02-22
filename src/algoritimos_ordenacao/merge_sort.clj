(ns algoritimos-ordenacao.merge-sort)

;EM ANDAMENTO

(def vetor [5 6 2 7 1 65 25 98 542 34 3 21 1])


(def meio (nth vetor (quot (count vetor) 2)))
(def indice-meio (position meio vetor ))
(def esquerda (take indice-meio vetor))
(def direita (drop (inc indice-meio) vetor))



(println meio)
(println esquerda )
(println direita )


