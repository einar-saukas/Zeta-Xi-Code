CC = kotlinc
RM = del
SRC = src/main/kotlin/br/com/summa/zx/

all: zeta-xi

zeta-xi: $(SRC)Main.kt $(SRC)ZetaXi.kt $(SRC)BitInputStream.kt $(SRC)BitOutputStream.kt $(SRC)StringBitInputStream.kt $(SRC)StringBitOutputStream.kt
	$(CC) $(SRC)Main.kt $(SRC)ZetaXi.kt $(SRC)BitInputStream.kt $(SRC)BitOutputStream.kt $(SRC)StringBitInputStream.kt $(SRC)StringBitOutputStream.kt -include-runtime -d zeta-xi.jar

clean:
	$(RM) zeta-xi.jar
