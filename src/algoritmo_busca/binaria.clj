(ns algoritmo-busca.binaria)

;Na primeira tentativa, o código foi feito com papel e caneta, sendo testado por um compílador REPL na versão mobile para IOS
;O Repelet REPL.
;O problema foi o não entendeimento total do problema e da estruturação da busca binária
;Onde eu estava tentando dividir o vetor em dois, literalmente, sem utilizar de apontadores para isso
;Com o entendimento da necessidade dos apontadores e da ciência do concebimento equivocado do conceito de busca binária
;Busquei entender melhor o codigo, anotar passo a passo no caderno as regras e estruturas necessárias e esse foi o resultado.

(defn busca-binaria
  "funcao para definir busca binaria"
  [vetor elemento] ;define os argumentos vetor e elemento
  (let [tamanho (count vetor)] ; inicia uma variável tamanho contando a quantidade de elementos de um vetor // (count)

    (loop [inicio 0 ; Inicia o loop passando o apontador inicio inicializado com o valor 0 apontando para o indice zero do vetor
           fim (dec tamanho)] ; e também o apontador fim para apontar para o indice do ultimo elemento do vetor utilizando a funcao de decrementar // (dec)

      (if (> inicio fim) ; Condição de parada: se o apontador do inicio passar do apontador do fim,
        (str "Elemento não encontrado")  ;retorna que o elemento nao foi encontrado

        (let [meio (quot (+ inicio fim) 2); se nao ele inicializa a variavel meio passando a divisão inteira por 2 da soma dos apontadores inicio e fim
              ;a ideia da variavel meio é que o apontador meio siga a referencia dos apontadores inicio e fim, nao o tamanho do vetor em sim.
              ;essa pseudo redução facilita a manipulacao do tamanho observado pelo codigo do vetor utilizado para realizar a busca,
              ; sem precisar manipular a estrutura do vetor de fato, facilitando assim o processo de codificação

              valor-meio (nth vetor meio)];é definido também o valor do elemento dentro do vetor com o indice equivalente ao meio.

          (cond ;inicializa a funcao condicional, onde teremos as condicoes de recursividade e conclusao da busca

            (= elemento valor-meio) (str "Elemento encontrado no índice: " meio) ;quando o elemento for igual ao meio, retorna mensagem indicando o indice do valor encontrado.

            (< elemento valor-meio) (recur inicio (dec meio)) ; caso o elemento seja menor que o meio, nós faremos a recursividade do loop definindo agora a visualização da primeira metade do vetor
            ; mantendo o valor do apontador inicio e passando o elemento anterior ao meio como novo valor do apontador fim.

            :else (recur (inc meio) fim)) ; caso o elemento seja maior (nem igual, nem menor) que o meio, nós faremos a recursividade do loop definindo agora a visualização da segunda metade do vetor
          ; definindo passando o elemento sucessor ao meio como novo valor do apontador inicio e mantendo o valor do apontador fim.
          )
        )
      )
    )
  )

;Bloco de retorno da funcao busca-binaria
(def vetor [1 2 3 4 5 6 7 8 9]) ; Define um vetor ordenado.

; Chama a função de busca binária para encontrar o índice do elemento no vetor, no caso o 9.
(println (busca-binaria vetor 9))

