/*
 	 	 Project		:		Reliable communications over unreliable channel
 	 	 Course			:		COSC 5328_02_1 Computer Network
 	 	 File Name		:		user2.c
 */

#include <stdio.h>
#include <string.h>
#include <time.h>
#include "rsocket.h"

#define port_local 	50407
#define mlen	50

void error(char *s) {
	perror(s);
	exit(1);
}

int main() {
	srand(time(NULL));
	int receiver_socket = r_socket(AF_INET, SOCK_BRP, IPPROTO_UDP);
	if (receiver_socket == -1)
		error("r_socket");

	struct sockaddr_in addr;
	socklen_t slen = sizeof(addr);
	addr.sin_family = AF_INET;			//Socket internet family
	addr.sin_port = htons(port_local);
	addr.sin_addr.s_addr = htonl(INADDR_ANY);
	if (r_bind(receiver_socket, (struct sockaddr*) &addr, sizeof(addr)) == -1)
		error("r_bind");

	char buf[mlen] = { 0 };
	printf("Received string from user1:: \n");
	while (r_recvfrom(receiver_socket, buf, 10, mlen, (struct sockaddr*) &addr, &slen) != -1) {
		printf("%s\n", buf);
		fflush(stdout);
	}

	r_close(receiver_socket);
	return 0;
}
