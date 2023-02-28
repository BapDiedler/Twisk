CC = cc

main:lib
	$(CC) -Wall -L. main.c -o main -lTwisk

lib:client.o
	$(CC) -shared programmeC.o client.o -o libTwisk.so

client.o:def.h client.c
	$(CC) -Wall -fPIC -c client.c -o client.o
