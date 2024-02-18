(ns selection-sort)

(defn selection-sort
  "funcao de ordenacao seguindo o aloritimo de selection-sort"
  [vetor];vetor que ira ser ordenado


  ;DOCUMENTACAO DO LOOP
  ;(loop
  ; [args]
  ; (recur *args*))

  ;A funcao loop para controlar o fluxo e realizar loops recursivos. ela trabalha diretamente com a funcao (recur) que garante a volta da recursividade
  ;Aqui eu defini os vetores que iriam ser manipulados passando o vetor como argumento na funcao
  (loop [vetor-ordenado []
         vetor-restante vetor]
    ;Aqui verificamos nosso caso de parada, se o vetor restante estiver vazio nós retornaremos o vetor ordenado
    (if (empty? vetor-restante)
      vetor-ordenado
      ;Se nao nós iniciaremos uma variavel para capturar o valor mínimo do vetor restante
      (let [minimo (apply min vetor-restante)
            ;Retiramos o valor minimo do vetor restante e associamos para outra variavel
            vetor-sem-minimo (remove #(= % minimo) vetor-restante)]
        ;ativaremos a recursao passando o vetor ordenado juntado com o valor minimo na primeira posicao e vetor sem o valor minimo encontrado. ambos como argumentos para retornar ao loop.
        (recur (conj vetor-ordenado minimo) vetor-sem-minimo))
      )
    )
  )



;vetor de teste
(def vetor-teste [5 6 2 7 1 65 98 542 34 3 21 1])

;print para verificar a utilizacao da funcao (empty?)
(println (empty? vetor-teste))

;print para verificar a acao do codigo e chamar a funcao
(println (selection-sort vetor-teste) )

