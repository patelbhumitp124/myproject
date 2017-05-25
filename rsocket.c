/*
 Project		:		Reliable communications over unreliable channel
 Course			:		COSC 5328_02_1 Computer Network
 File Name		:		rsocket.c
 */
#include <stdint.h>
#include <stdio.h>
#include <limits.h>
#include <string.h>
#include <pthread.h>
#include <sys/time.h>
#include "rsocket.h"
//////////////////////////////////////////////////////////////////////////////////////
#define MIN(a, b) (((a)<(b))?(a):(b)) // Condition? value if true : value if false

// time difference calculation: sec section + nsec section
#define timespecsub3(dest, left, right)                   \
do {                                                      \
    (dest)->tv_sec = (left)->tv_sec - (right)->tv_sec;    \
    (dest)->tv_nsec = (left)->tv_nsec - (right)->tv_nsec; \
    if ((dest)->tv_nsec < 0) {                            \
        (dest)->tv_sec--;                                 \
        (dest)->tv_nsec += 1000000000;                    \
    }                                                     \
} while (0)

static pthread_mutex_t _sdftable_mutex = PTHREAD_MUTEX_INITIALIZER;
static _sockfd_entry_t* _sdftable[BRP_MAX] = { 0 };

void* _R(void* param);		//received message
void* _S(void* param);		// deal with time out and re-transmission.

//-----the initialization function to initialize new sock entry when the pointer is not null-----
_sockfd_entry_t* _sockfd_entry_new(int sockfd, size_t idx) {
	_sockfd_entry_t* p = malloc(sizeof(*p));
	if (p) {

		p->references = 1;
		p->idx = idx;
		p->socket_file_descriptor = sockfd;
		pthread_create(&p->thread_array[0], NULL, _R, p);
		pthread_create(&p->thread_array[1], NULL, _S, p);
		pthread_mutex_init(&p->mutex, NULL);
		p->Receiver_table = calloc(MSG_MAX_COUNT, sizeof(_msg_entry_t));
		p->unacknoledgement_table = calloc(MSG_MAX_COUNT, sizeof(_msg_entry_t));
		p->msg_index = 0;
		p->trans_count = 0;		//
	}
	return p;
}

//-----the function to free the memory of each message table entry-----
static
void _free_msg_table(_msg_entry_t* msge) {
	unsigned u;
	for (u = 0; u < MSG_MAX_COUNT; u++) {
		free(msge[u].msg_structure);
		msge[u].msg_structure = NULL;
	}
	free(msge);
}

//-----the function to free the memeory of each socket table entry-----
void _sockfd_entry_free(_sockfd_entry_t* sfde) {

	pthread_cancel(sfde->thread_array[0]);     // send kill signal to R thread
	pthread_join(sfde->thread_array[0], NULL); // suspend, wait R thread to stop
	pthread_cancel(sfde->thread_array[1]);     // send kill signal to S thread
	pthread_join(sfde->thread_array[1], NULL); // suspend, wait S thread to stop
	pthread_mutex_destroy(&sfde->mutex);
	_free_msg_table(sfde->unacknoledgement_table);
	_free_msg_table(sfde->Receiver_table);
	close(sfde->socket_file_descriptor);
	free(sfde);
}

//-----the function to initilize socket table when new socket built up-----
_sockfd_entry_t* _sdftable_entry_new(int sockfd) {
	_sockfd_entry_t* p = NULL;
	pthread_mutex_lock(&_sdftable_mutex);
	unsigned u = BRP_MAX;
	while (u--) {
		if (!_sdftable[u]) {
			p = _sockfd_entry_new(sockfd, u);
			_sdftable[u] = p;
			break;
		}
	}
	pthread_mutex_unlock(&_sdftable_mutex);
	return p;
}

//-----the function to query socket and return pointer in socket table-----
_sockfd_entry_t* _sdftable_entry_retain(int sockfd) {
	_sockfd_entry_t* p = NULL;
	pthread_mutex_lock(&_sdftable_mutex);
	unsigned u = BRP_MAX;
	while (u--) {
		if (_sdftable[u] && _sdftable[u]->socket_file_descriptor == sockfd) {
			p = _sdftable[u];
			p->references++;
			break;
		}
	}
	pthread_mutex_unlock(&_sdftable_mutex);
	return p;
}

//-----the function to free the socket table-----
void _sdftable_entry_release(_sockfd_entry_t* sfde) {
	pthread_mutex_lock(&_sdftable_mutex);
	if (sfde && (sfde->idx < BRP_MAX)
			&& (_sdftable[sfde->idx]->socket_file_descriptor
					== sfde->socket_file_descriptor)) {
		if (--sfde->references == 0) {
			_sdftable[sfde->idx] = NULL;
			_sockfd_entry_free(sfde);
		}
	}
	pthread_mutex_unlock(&_sdftable_mutex);
}


///////////////////////////////////////////////////////////////////////////////////////////

//-----the function to simulate message lost by compare random probability and pre-defined probability in .h file-----
int dropMessage(float prob) {
	float x = ((float) rand() / (float) (RAND_MAX));
	return (x < prob);
}

////////////////////////////////////////////////////////////////////////////////////////////


//-----the R thread function: handle all received messages-----
void* _R(void* param) {
	_sockfd_entry_t* sfde = (_sockfd_entry_t*) param;

	// 65535 for UDP message size
	char data[USHRT_MAX];
	struct sockaddr addr;
	while (true) {
		socklen_t addr_len = sizeof(addr);
		ssize_t recv = recvfrom(sfde->socket_file_descriptor, data, USHRT_MAX,
				0, &addr, &addr_len);

		if (recv != -1 && (recv >= sizeof(_message_t)) && !dropMessage(P))
		// if recvfrom succeeds, receive buff not less than fixed message size, no message lost
				{
			_message_t* msg = (_message_t*) data;

			pthread_mutex_lock(&sfde->mutex);
			if (msg->Type == brp_data_message) {

				// store new messages into received message table
				sfde->Receiver_table[msg->indx].size_bit = recv;
				sfde->Receiver_table[msg->indx].read_all = 0;
				sfde->Receiver_table[msg->indx].address = addr;
				sfde->Receiver_table[msg->indx].msg_structure = malloc(recv);
				memcpy(sfde->Receiver_table[msg->indx].msg_structure, data,
						recv);

				// send ACK to confirm the received message
				msg->Type = brp_ackknoledgement;
				sendto(sfde->socket_file_descriptor, msg, recv, 0,
						&sfde->Receiver_table[msg->indx].address, addr_len);
			} else if (msg->Type == brp_ackknoledgement) {

				// remove corresponding message from unacknowledgement after receiving ACK
				free(sfde->unacknoledgement_table[msg->indx].msg_structure);
				sfde->unacknoledgement_table[msg->indx].msg_structure = NULL;
			}
			pthread_mutex_unlock(&sfde->mutex);
		}
	}
	return NULL;
}

//-----the S thread function: handle the timeouts and retransmissions-----
void* _S(void* param) {
	_sockfd_entry_t* sfde = (_sockfd_entry_t*) param;
	float retry = (T / 1000) * 2;
	while (true) {
		usleep((T) * 1000);
		unsigned u;
		for (u = 0; u < MSG_MAX_COUNT; u++) {
			pthread_mutex_lock(&sfde->mutex);
			if (sfde->unacknoledgement_table[u].msg_structure) {
				struct timespec tm;
				clock_gettime(CLOCK_MONOTONIC, &tm);
				timespecsub3(&tm, &tm,
						&sfde->unacknoledgement_table[u].start_sent_time);
				float time = gettimesec(&tm);
				if (time > retry)
				// if the message timeout period is over
						{

					// update lastest sending time and retransmit the message
					clock_gettime(CLOCK_MONOTONIC,
							&sfde->unacknoledgement_table[u].start_sent_time);
					sendto(sfde->socket_file_descriptor,
							sfde->unacknoledgement_table[u].msg_structure,
							sfde->unacknoledgement_table[u].size_bit, 0,
							&sfde->unacknoledgement_table[u].address,
							sizeof(struct sockaddr));
					// increase total transmissions count
					sfde->trans_count++;
				}
			}
			pthread_mutex_unlock(&sfde->mutex);
		}
	}
	return NULL;
}

////////////////////////////////////////////////////////////////////////////////////

//-----brp defined function: r_socket-----
int r_socket(int domain, int type, int protocol) {
	int sockfd = socket(domain, SOCK_DGRAM, protocol);
	if (type == SOCK_BRP && (sockfd != -1)) {
		_sockfd_entry_t* sfde = _sdftable_entry_new(sockfd);
		if (!sfde) {
			close(sockfd);
			return -1;
		}
	}
	return sockfd;
}

////////////////////////////////////////////////////////////////////////////////////

//-----brp defined function: r_bind-----
int r_bind(int sockfd, const struct sockaddr* addr, socklen_t addrlen) {
	int res = -1;
	_sockfd_entry_t* sfde = _sdftable_entry_retain(sockfd);
	if (sfde)
		res = bind(sockfd, addr, addrlen);
	_sdftable_entry_release(sfde);
	return res;
}

////////////////////////////////////////////////////////////////////////////////////

//-----brp defined function: r_sendto-----
ssize_t r_sendto(int sockfd, const void* buf, size_t len, int flags,
		const struct sockaddr* dest_addr, socklen_t addrlen) {
	ssize_t res = -1;
	_sockfd_entry_t* sfde = _sdftable_entry_retain(sockfd);
	if (sfde && buf && len && dest_addr && addrlen) {
		_message_t* msg;
		size_t msg_sz = sizeof(*msg) + len;
		msg = malloc(msg_sz);
		msg->Type = brp_data_message;
		msg->indx = sfde->msg_index;
		sfde->msg_index = ((sfde->msg_index + 1) % MSG_MAX_COUNT); // deal with the pointer pointing to the last message entry
		memcpy(msg->DATA, buf, len);
		res = sendto(sockfd, msg, msg_sz, 0, dest_addr, addrlen);
		if (res != -1) {
			pthread_mutex_lock(&sfde->mutex);
			// store new message in unacknowledged message table
			sfde->unacknoledgement_table[msg->indx].msg_structure = msg;
			sfde->unacknoledgement_table[msg->indx].size_bit = msg_sz;
			sfde->unacknoledgement_table[msg->indx].read_all = 0;
			sfde->unacknoledgement_table[msg->indx].address = *dest_addr;

			// get current time and set it as latest sent time. used in '_S' to calculate ACK time difference
			clock_gettime(CLOCK_MONOTONIC,
					&sfde->unacknoledgement_table[msg->indx].start_sent_time);

			// increase total transmissions count
			sfde->trans_count++;
			pthread_mutex_unlock(&sfde->mutex);
			res -= sizeof(*msg);
		} else
			free(msg);
	}
	_sdftable_entry_release(sfde);
	return res;
}

////////////////////////////////////////////////////////////////////////////////////


//-----brp defined function: r_recfrom !!!!
ssize_t r_recvfrom(int sockfd, void* buf, size_t len, int flags,
		struct sockaddr* src_addr, socklen_t* addrlen) {
	ssize_t res = -1;
	_sockfd_entry_t* sfde = _sdftable_entry_retain(sockfd);
	if (sfde && buf) {
		while (sfde->references > 1) {
			unsigned u;
			for (u = 0; u < MSG_MAX_COUNT; u++) {
				pthread_mutex_lock(&sfde->mutex);
				if (sfde->Receiver_table[u].msg_structure) {

					res = MIN(len, sfde->Receiver_table[u].size_bit);
					sfde->Receiver_table[u].read_all += res;
					res -= sizeof(_message_t);
					memcpy(buf, sfde->Receiver_table[u].msg_structure->DATA,
							res);
					if (src_addr && addrlen) {
						*src_addr = sfde->Receiver_table[u].address;
						*addrlen = sizeof(*src_addr);
					}

					if (sfde->Receiver_table[u].read_all
							== sfde->Receiver_table[u].size_bit) {
						free(sfde->Receiver_table[u].msg_structure);
						sfde->Receiver_table[u].msg_structure = NULL;
					}
					pthread_mutex_unlock(&sfde->mutex);
					_sdftable_entry_release(sfde);
					return res;
				}
				pthread_mutex_unlock(&sfde->mutex);
			}
			usleep((500) * 1000);
		}
	}
	_sdftable_entry_release(sfde);
	return res;
}

////////////////////////////////////////////////////////////////////////////////////


//-----brp defined function: r_close-----
int r_close(int sockfd) {

	_sockfd_entry_t* sfde = _sdftable_entry_retain(sockfd);
	// double release to reset the reference counter
	_sdftable_entry_release(sfde);
	_sdftable_entry_release(sfde);
	return 0;
}

////////////////////////////////////////////////////////////////////////////////////


//-----function to check if the space *msg point to is null------
bool r_isbuffer(int sockfd) {
	bool res = false;
	_sockfd_entry_t* sfde = _sdftable_entry_retain(sockfd);
	if (sfde) {
		pthread_mutex_lock(&sfde->mutex);
		unsigned u;
		for (u = 0; u < MSG_MAX_COUNT; u++) {
			if (sfde->unacknoledgement_table[u].msg_structure) {
				res = true;
				break;
			}
		}
		pthread_mutex_unlock(&sfde->mutex);
	}
	_sdftable_entry_release(sfde);
	return res;
}

//////////////////////////////////////////////////////////////////////////////////
