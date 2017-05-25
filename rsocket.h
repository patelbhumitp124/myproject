/*
 	 	 Project		:		Reliable communications over unreliable channel
 	 	 Course			:		COSC 5328_02_1 Computer Network
 	 	 File Name		:		rsocket.h
 */

#ifndef RSOCKET_H_INCLUDED
#define RSOCKET_H_INCLUDED

#include <stdlib.h>
#include <stdbool.h>
#include <sys/socket.h>
#include <unistd.h>
#include <arpa/inet.h>

///////////////////////////////////////////////////////////////////////////////////
#ifdef __cplusplus
extern "C" {
#endif

/* NOTE (tema#1#): BRP socket type. */
#define SOCK_BRP        10
/* NOTE (tema#1#): You can assume that the maximum number of BRP sockets that
 can be opened is 25. */
#define BRP_MAX         25
/* NOTE (tema#1#): You can assume that the maximum size of these tables will not
 be more than 50 at any time. */
#define MSG_MAX_COUNT   50

#define T     2000 /* sthread timeout in milliseconds */
#define P     0.20 /* probability of packet loss */

// time combination
#define gettimesec(src) ((src)->tv_sec + ((src)->tv_nsec)/1000000000.0)

// define the message type: 1. data message  2. ACK message
typedef enum
	__attribute__((packed)) {brp_data_message, brp_ackknoledgement
} brp_type;

// define message structure
typedef struct _message_t {
	uint8_t indx;   // unsigned 8 bit index: 0~255
	brp_type Type;
	char DATA[]; // the array to store the message content
} _message_t;

// define the message information structure
typedef struct _msg_entry_t {
	struct timespec start_sent_time; // latest sent time
	struct sockaddr address;  // destination address
	uint16_t size_bit;         // unsigned 16 bit size: 0~65535
	uint16_t read_all;
	_message_t* msg_structure;       // msg -- message struct
} _msg_entry_t;

// define the socket description table (each socket has one R thread and one S thread) structure
typedef struct _sockfd_entry_t {
	int socket_file_descriptor;
	size_t references;
	size_t idx;
	unsigned msg_index;
	unsigned trans_count;
	pthread_t thread_array[2];      // thread array to store the R and S
	pthread_mutex_t mutex;
	_msg_entry_t* unacknoledgement_table; // unacknoledgement_table -- message info struct
	_msg_entry_t* Receiver_table;  // Receiver_table -- message info struct
} _sockfd_entry_t;

int r_socket(int domain, int type, int protocol);
int r_bind(int sockfd, const struct sockaddr* addr, socklen_t addrlen);

ssize_t r_sendto(int sockfd, const void* buf, size_t len, int flags,
		const struct sockaddr* dest_addr, socklen_t addrlen);

ssize_t r_recvfrom(int sockfd, void* buf, size_t len, int flags,
		struct sockaddr* src_addr, socklen_t* addrlen);

int r_close(int sockfd);

bool r_isbuffer(int sockfd);

#ifdef __cplusplus
}
#endif
//////////////////////////////////////////////////////////////////////////////////////////////////
#endif // RSOCKET_H_INCLUDED
