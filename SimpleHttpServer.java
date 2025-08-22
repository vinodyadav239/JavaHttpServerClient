import java.io.*;
import java.net.*;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        int port = 8080; // server will run on http://localhost:8080
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("HTTP Server started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            handleClient(clientSocket);
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            // Read request line (e.g. "GET / HTTP/1.1")
            String requestLine = in.readLine();
            if (requestLine == null) return;

            System.out.println("Request: " + requestLine);

            String method = requestLine.split(" ")[0];
            String path = requestLine.split(" ")[1];

            // Read headers
            String line;
            int contentLength = 0;
            while (!(line = in.readLine()).isEmpty()) {
                if (line.startsWith("Content-Length:")) {
                    contentLength = Integer.parseInt(line.split(":")[1].trim());
                }
            }

            String responseBody = "";
         if(method.equals("GET")) {
                responseBody = "<h1>Hello from Java HTTP Server (GET)</h1>";
            } else if (method.equals("POST")) {
                char[] bodyData = new char[contentLength];
                in.read(bodyData);
                String requestBody = new String(bodyData);

                System.out.println("POST body: " + requestBody);

                responseBody = "<h1>Received POST data:</h1><p>" + requestBody + "</p>";
            } else {
                responseBody = "<h1>Method Not Supported</h1>";
            }

            // Build HTTP response
            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + responseBody.length() + "\r\n" +
                    "\r\n" +
                    responseBody;

            out.write(httpResponse);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
 }
}
}