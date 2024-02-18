(ns algoritimos-ordenacao.insertion-sort)

(defn insere-elemento
  "Funcao que insere o elemento entre os valores menores e maiores"
  [elemento vetor] ;elemento que vai ser inserido e vetor no estado atual


  ; A notação #(< % elemento) serve como uma espécie de filtro dentro da linguagem
  ;basicamenteeu estou dizendo que eu quero filtrar os elementos "%" menores "(<)" que o "elemento"
  ;#() é uma funcao anonima que, dentre outras aplicabilidades, ela pode passar uma funcao comoargumento EX:(map), (take-while), (<)
  ;bem como pode criar apenas uma funcao no escopo local de forma mais concisa, sem precisar criar uma nova funcao no escopo global.
  
  ; DOCUMENTACAO (take-while)
  ;(take-while condicao sequencia-da-qual-vou-retirar) // gera uma sequencia com a condicao que voce define a partir de outra sequencia.

  ; Nesse caso a gente usa o take-while para pegar todos os valores menores que o elemento dentro do vetor
  (let [menores (take-while #(< % elemento) vetor);inicializa variaveis da funcao
        maiores (drop (count menores) vetor)];aqui criamos a lista de elementos maiores que o elemento passado, dropando o numero de elementos da lista de menores elementos restando assim somente os maiores.
    (concat menores [elemento] maiores); concatenamos a lista de menores, com uma lista com o elemnto e a lista de elementos maiores, inserindo assim o elemento.
    )
  )
(defn insertion_sort
  "funcao de ordenacao seguindo o aloritimo de selection-sort"
  [vetor];vetor que vai ser ordenado.
  (loop [vetor-ordenado [] ;define o inicio do loop e os vetores que irão ser manipulados.
         vetor-restante vetor]
    (if (empty? vetor-restante); condicao de parada do loop: quando o vetor restante estiver vazio,
      vetor-ordenado ; retorna o vetor ordenado
      (let [elemento (first vetor-restante); se nao criamos uma variavel para pegar o primeiro elemento do vetor
            restante-vetor (rest vetor-restante); outra para pegar o restante dos elementos do vetor
            vetor-atualizado (insere-elemento elemento vetor-ordenado)] ; e definimos o vetor atualizado chamando nossa funcao de insercao passando o primeiro elemnto e o vetor ordenado
        (recur vetor-atualizado restante-vetor) ; volta o loop passando o vetor com o elemento inserido e o restante do vetor com os valores que faltam serem inseridos.
        )))
  )



(def vetor-teste [5 6 2 7 1 65 98 542 34 3 21 1])

;print para verificar a utilizacao da funcao (empty?)
(println (empty? vetor-teste))

;print para verificar a acao do codigo e chamar a funcao
(println (insertion_sort vetor-teste) )
