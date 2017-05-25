# Filename: Makefile
# Description: The make file is to build up the brpsocket library.
# Warning:  

SRC = rsocket.c
OBJ = $(SRC:.c=.o)
OUT = librsocket.a
INCLUDES = -I.
CCFLAGS = -O3 -Wall
CCC = gcc
LIBS = librsocket.a -lpthread

 
USER1SRC = user1.c 
USER1OBJ = $(USER1SRC:.c=.o)
USER1OUT = user1

USER2SRC = user2.c 
USER2OBJ = $(USER2SRC:.c=.o)
USER2OUT = user2

all: $(OUT) $(USER1OUT) $(USER2OUT)

.SUFFIXES: .c
default: $(OUT) $(USER1OUT) $(USER2OUT)

.c.o:
	$(CCC) -lrt $(INCLUDES) $(CCFLAGS) -c $< -o $@
 
$(OUT): $(OBJ) 
	ar rcs $(OUT) $(OBJ)
 	
$(USER1OUT): $(USER1OBJ)
	  $(CCC) -lrt $(USER1OBJ) -o $@ $(LIBS)

$(USER2OUT): $(USER2OBJ)
	  $(CCC) -lrt $(USER2OBJ) -o $@ $(LIBS)

cleanobj: 
	rm -f $(OBJ) $(USER1OBJ) $(USER2OBJ)

clean: 
	rm -f  $(OBJ) $(USER1OBJ) $(USER2OBJ) $(OUT) $(USER1OUT) $(USER2OUT) 
