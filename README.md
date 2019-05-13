# modele_markova
Ukryte modele Markova - odtworzenie i prezentacja eksperymentu z „nieuczciwym kasynem”
(generator danych tj. „sztuczny krupier” zachowujący się zgodnie z podanymi przez użytkownika
prawdopodobieństwami i próba odtworzenia przebiegu podmian kostki na podstawie sekwencji
wyników wyjściowych). Implementacja algorytmu Viterbi oraz a'posteriori.

Zagadnienie “nieuczciwego kasyna”
Podczas rzutu uczciwą kostką prawdopodobieństwo wypadnięcia określonej liczby oczek
jest taka sama dla każdej liczby od 1 do 6 i wynosi ona ⅙. W przypadku gdy nieuczciwy
krupier podmieni kostkę na nieuczciwą prawdopodobieństwo prawdopodobieństwo to się
zmienia. Przykładowo wartości od 1 do 5 wypadają rzadziej, przykładowo raz na dziesięć
rzutów podczas gdy wartość 6 wypada statystycznie w co drugim rzucie.

Predykcja
Predykcja podmian polega na przewidzeniu za pomocą algorytmu które rzuty były
wykonywane za pomocą kostki uczciwej, a które za pomocą kostki nieuczciwej. W tym
projekcie zostały zaimplementowane dwa algorytmy: Viterbi oraz ciąg prawdopodobieństw
a'posteriori.
