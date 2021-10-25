CC = gcc
CFLAGS = -g -Wall
RM = del
SRC = src/
EXTENSION = .exe

all: zeta_xi

zeta_xi: $(SRC)main.c $(SRC)zeta_xi.c
	$(CC) $(CFLAGS) -o zeta_xi$(EXTENSION) $(SRC)main.c $(SRC)zeta_xi.c

clean:
	$(RM) *.obj
