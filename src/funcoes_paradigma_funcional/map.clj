(ns funcoes-paradigma-funcional.map)

;Funcao de mapaeamento de umvetor para multiplicacao por 2 em todos os seus valores utilizando o conceito de recursao direta
(defn map_x2
  "funcao de mapeamento para multiplicar os valores de um vetor por 2"
  [vetor];passando vetor
    (if (empty? vetor);se o vetor estiver vazio
      [];retorna uma lista vazia...calma, lembre das aulas de plp e fará sentido.
      (let [elemento_inicio (first vetor);inicializa uma variavel que captura o primeiro valor do vetor
            restante (rest vetor)];e outra para o restante do vetor
        (cons (* elemento_inicio 2) (map_x2 restante));chama a funcao (cons) que insere um elemento na primeira posicao da lista
        ;passando o elemento do inicio a lista no momento que é utilizada no (cons)
        ;e passando também a chamada recursiva com o restante do vetor, dessa forma empilhando a execucao do codigo
        ;reduzindo cada vez mais o vetor até ele se tornar uma lista vazia (agora nossa condição de parada faz sentido ;) )
        ;no momento que o codigo "desempilha" as execucos, os valores sao adicionados no inicio da lista restante que, por sua vez, se torna o retorno completo da funcao (cons)
      )
    )
  )


;aqui é uma implementacao baseada na solução anterior mas sem utilizar a recursividade nativa, utilizando a funcao (loop)
(defn map_x2_versao_autoral
  "funcao de mapeamento para multiplicar os valores de um vetor por 2"
  [vetor];passando vetor
  (loop [vetor_resposta [];inicializa o loop com a definicao do vetor que ira armazenar as respostas
         inicio 0];e um apontador para os indices do vetor
    (if (= inicio (count vetor) ) ;se o apontador for igual ao o tamanho do vetor, quer dizer que toda lista fopi percorrida
      vetor_resposta; e que ja podemos retornar o vetor_resposta
      (let [indice_elemento inicio;se nao nós formalizamos (pra deixar mais legivel e intuitivo para melhor aabsorção do conceito) a variavel do indice do elemento
            elemento_indice_mult (* (nth vetor indice_elemento) 2) ] ;e o valor referente ao indice dentro do vetor multiplicado por dois
        (recur (conj vetor_resposta elemento_indice_mult) (inc indice_elemento)) ;chamamos a funcao (recur) para realizar a chamada recursiva do loop chamando a funcao (conj)
        ;para representar o vetor resposta recebendo o valor multiplicado e o indice do elemento incrementado para apontar para o proximo indice.
        )
      )
    )
  )

;aqui é implementacao do mapeamento caso quisermos aplicar qualquer funcao que criarmos e passarmos como parametro.
;o tipo de recurssao utilizado é o mesmo do primeiro codigo de mapeamento, o que faz a funcao ser bem compacta
(defn map_generico
  "Aplica a função em cada elemento do vetor e retorna um vetor com os resultados do mapeamento."
  [funcao vetor];define os argumentos funcao e vetor
  (if (empty? vetor); se o vetor estiver vazio
    [];retornaremos um vetor vazio
    (cons (funcao (first vetor)) (map_generico funcao (rest vetor))))) ;chama a funcao (cons) que insere um elemento na primeira posicao da lista
;passando o elemento do inicio a lista no momento que é utilizada no (cons)
;e passando também a chamada recursiva com o restante do vetor, dessa forma empilhando a execucao do codigo aplicando a funcao do argumento passado
;reduzindo cada vez mais o vetor até ele se tornar uma lista vazia (agora nossa condição de parada faz sentido ;) )
;no momento que o codigo "desempilha" as execucos, os valores sao adicionados no inicio da lista restante que, por sua vez, se torna o retorno completo da funcao (cons)


(defn multiplicar-por-dois
  "Define uma funcao para multiplicar os elementos por três, sendo aplicada no map_generico"
  [x]
  (* x 2))


(def vetor_teste [1 2 3 4 5 6 7 8 9])

(println (map_x2 vetor_teste))
(println (map_x2_versao_autoral vetor_teste))
(println (map_generico multiplicar-por-dois vetor_teste))