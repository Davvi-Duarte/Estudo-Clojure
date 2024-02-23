(ns funcoes-paradigma-funcional.reduce)

;após a criação do map e o estudo do algoritmo, a implementacao da funcao reduce foi feita de forma mais assertiva.
;dessa vez o conceito de recursividade otimiza a utilização da variavel resposta, o que deixa o algoritmo mais rapido e inteligente.


(defn reduce_generico
  "implementacao da funcao reduce do paradigma funcional"
  [vetor f]
  (loop
    [resposta (first vetor)  ; chama a variavel que irá acumular a resposta pegando o primeiro elemento do vetor
     vetor_manipulado (rest vetor)] ; chama a variavel que irá conter o restante do vetor (sem o primeiro valor)
    (if (empty? vetor_manipulado) ;quando o vetor estiver vazio que
      resposta ;e que podemos retornar nossa variavel resposta
      (recur (f resposta (first vetor_manipulado))  (rest vetor_manipulado)))) ;chamamos a recursividade do loop chamando a funcao passada por argumento
      ; sendo aplicada com o valor resposata e o segundo elemento da lista (o primeiro elemmento da lista restante)
      ; e passa também o restante da lista iniciando pelo segundo elemento dela.
  )

;definindo vetor de teste
(def vetor [1 1 1 1 1 1 1 1 1 1])

;print para verificar funcionamento
(println (reduce_generico vetor -))