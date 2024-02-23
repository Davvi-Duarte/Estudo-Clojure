(ns funcoes-paradigma-funcional.filter)

;A funcao filter, apesar de parecer simples, mas ainda estava um pouco nebuloso o conceito, entao ese algoritmo, depois de revisoes e tentativas
;auxiliou e muito o entndimento do problema e como poderiamos implementar sua solucao de uma forma enxuta e inteligente
;reforcando conceitos de recursividade e colaborando com a visao geral do paradigma funcional.


(defn filter_generico
  "implementacao da funcao filter do paradigma funcional"
  [predicado vetor];chamamos os argumentos do predicado que irá serveir como parametro para nosso filtro e o vetor que irá ser manipulado
  (if (empty? vetor);condicao de parada: se o vetor estiver vazio, quer dizer que a execucao do algoritmo chegou ao seu fim
    [] ;e retornamos um vetor vazio, que nao irá influenciar na hora de desempilhar a funcao
    (if (predicado (first vetor));se o predicado passado for aplicado no primeiro elemento do vetor
      (cons (first vetor) (filter_generico predicado (rest vetor))) ;e for verdadeiro: adiciona o elemento no inicio do vetor que será criado quando o programa terminar de executar e comecar a desempilhar os valores
      (filter_generico predicado (rest vetor)));caso seja falso ele so chama recursivamente a funcao, sem necessitar adicionar o valor que nao foi atendido pelo filtro.
    )
  )
;OBS: a funcao (cons) é importante pos, caso utilizassemos a funcao (conj) os valores iriam vir de forma inversa.
;no caso a cons reinverte os valores desempilhados fazendo com que eles sejam posicionados de forma coerente com a sua posição.


;define o vetor de teste
(def vetor [1 2 3 4 5 6 7 8 9])

;print testando o predicado (even?) que verifica se um numero é par
(println (filter_generico even? vetor))

;print testando o predicado #(not(even? %)) utilizando da estrutura de uma funcao anonima para negar o predicado (even?)
;capturando asssim apenas os numeros ímpares
(println (filter_generico #(not (even? %)) vetor))
