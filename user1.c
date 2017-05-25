/*
 	 	 Project		:		Reliable communications over unreliable channel
 	 	 Course			:		COSC 5328_02_1 Computer Network
 	 	 File Name		:		user2.c
 */

#include <stdio.h>
#include <string.h>
#include <time.h>
#include "rsocket.h"

#define port_local 	50406
#define port_remote	50407
#define mlen	50

void error(char *s) {
	perror(s);
	exit(1);
}

int main() {
	srand(time(NULL));
	int sender_socket = r_socket(AF_INET, SOCK_BRP, IPPROTO_UDP);
	if (sender_socket == -1)
		error("r_socket");

	struct sockaddr_in addr;
	addr.sin_family = AF_INET;
	addr.sin_port = htons(port_local);
	addr.sin_addr.s_addr = htonl(INADDR_ANY);
	if (r_bind(sender_socket, (struct sockaddr*) &addr, sizeof(addr)) == -1)
		error("r_bind");

	addr.sin_port = htons(port_remote);
	if (inet_aton("127.0.0.1", &addr.sin_addr) == 0)
		error("inet_aton");

	printf("Enter your input data string::> ");
	char buf[mlen] = { 0 };
	if (!fgets(buf, mlen, stdin))
		error("get string");

	size_t len = strlen(buf) - 1;
	buf[len] = 0;
	unsigned u;
	for (u = 0; u < len; u++) {
		if (r_sendto(sender_socket, &buf[u], 1, 0, (struct sockaddr*) &addr, sizeof(addr))
				== -1)
			error("r_sendto");
	}

	while (r_isbuffer(sender_socket))
		usleep(1000000);
	r_close(sender_socket);
	return 0;
}
