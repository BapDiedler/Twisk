CC = cc

main:lib
	$(CC) -Wall -Lressources/codeC ressources/codeC/main.c -o main -lTwisk

lib:client.o
	$(CC) -shared ressources/codeC/programmeC.o ressources/codeC/client.o -o ressources/codeC/libTwisk.so

client.o:ressources/codeC/def.h
	$(CC) -Wall -fPIC -c ressources/codeC/client.c -o ressources/codeC/client.o
