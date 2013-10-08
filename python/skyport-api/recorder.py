#!/usr/bin/env python2

import sys

from skyport import SkyportReceiver, SkyportTransmitter
from socket import socket, timeout, AF_INET, SOCK_STREAM

PASS = "supersecretpassword"

sock = socket(AF_INET, SOCK_STREAM)
sock.connect(('127.0.0.1', 54321))

def send_line(line):
    print("sending: '%s'" % line)
    if sock.sendall(line + "\n") != None:
        print("Error sending Data!")

def read_packet():
    try:
        line = sock.recv(1)
        if not ret:
            print("Disconnected!")
            sys.exit(1)
        return line[:-1]
    except socket.timeout:
        print("timeout!")
        return None
    except socket.error as e:
        print("error: %s" % e)
        sys.exit(1)

def main():
    transmitter = SkyportTransmitter(send_line)
    transmitter.send_message({"message":"connect",
                              "revision":1,
                              "password":PASS,
                              "laserstyle":"start-stop"})

    while True:
        line = read_packet()
        print(line)
        if line == '{"message":"endactions"}':
            transmitter.send_message({"message":"ready"})


if __name__ == '__main__':
    main()
