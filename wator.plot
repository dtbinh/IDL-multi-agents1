set terminal png
set output 'wator.png'

set title 'Wator'
set xlabel 'tour'
set ylabel 'population'
plot 'releve.dat' using 1:2 with lines title 'poisson', 'releve.dat' using 1:3 with lines title 'requin'