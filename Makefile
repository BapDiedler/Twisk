CC = cc

ALL:client.o
	$(CC) -shared ressources/codeC/programmeC.o ressources/codeC/client.o -o ressources/codeC/libTwisk.so

client.o:ressources/codeC/def.h
	$(CC) -Wall -fPIC -c ressources/codeC/client.c -o ressources/codeC/client.o

main:main.c
	$(CC) -Wall -L main.c -o main -lTwisk
