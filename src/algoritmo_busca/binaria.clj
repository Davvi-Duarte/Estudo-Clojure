(ns algoritmo-busca.binaria)

(def vetor [1 2 3 4 5 6 7 8 9])

(defn busca-binaria
  "algoritimo para realizar buscaa de um elemento m um vetor ordenado"
  [vetor elemento]
  (loop [vetor-busca vetor
         elemento-busca elemento]
    (let [meio (nth (quot(count vetor-busca)2) vetor-busca)
          vetordiv (split-at meio vetor-busca )
          esquerdos (nth vetordiv 0)
          direitos (nth vetordiv 1)]

      (println vetordiv)
      (println meio)
      (println esquerdos)
      (println direitos)

      (if (= [elemento-busca] esquerdos)
        esquerdos
        (if (= [elemento-busca] direitos)
          direitos
          (if (and (empty? esquerdos) (empty? direitos))
            (str "esse número nao está presente no vetor")
            (if (<= elemento-busca meio)
              (recur esquerdos elemento-busca)
              (if (> elemento meio)
                (recur direitos elemento-busca)
                (str "esse número nao está presente no vetor")))))))))


(println vetor)
(println busca-binaria vetor 2)