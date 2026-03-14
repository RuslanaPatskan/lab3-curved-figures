# Lab3 - Curved Figures (Кривые фигуры)

Java Swing application that draws animated rotating shapes.

## Build & Run
```bash
javac src/*.java -d src/
java -cp src/ TitlesFrame
```

## Shape encoding
Parameter = (tens digit) * 10 + (units digit)
- Tens: 1=3-star, 3=5-star, 5=rect, 6=hexagon, 7=triangle, 9=arc
- Units: 1=stroke3px, 3=default, 4=stroke7px, 7=gradient, 8=red
