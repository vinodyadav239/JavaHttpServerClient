# Java HTTP Server + Client

This project demonstrates a simple *HTTP Server and Client in Java* without using external frameworks.  
It shows how to handle *GET* and *POST* requests on the server-side, and how to send them from a client.

## 🚀 How to Run

### 1. Compile the project
```bash
javac SimpleHttpServer.java HttpClientExample.java
java SimpleHttpServer

The server starts at http://localhost:8080

### Run the client
java HttpClientExample

## testing with postman
Create a GET request -->http://localhost:8080
Create a POST request -->http://localhost:8080
with body
name:Vinod
role:student


## Features
Minimal HTTP server in pure java (ServerSocket & Socket)
Supports GET and POST requests
java client using httpURLConnection
Easy to extend into a REST Api

Technologies
Java(Core)
Sockets
HTTP Protocol basics
