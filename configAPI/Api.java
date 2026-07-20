import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Api {
    public static void main(String[] args) throws Exception {
        // Inicia o servidor Java na porta 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // EndPoint /api/status que retorna JSON
        server.createContext("/api/status", new StatusHandler());
        
        server.setExecutor(null); 
        System.out.println("API Java rodando na porta 8080...");
        server.start();
    }

    static class StatusHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Define o cabeçalho HTTP para responder em formato JSON
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            
            // JSON que simulamos vir do Banco de Dados
            String jsonResponse = "{\"status\": \"online\", \"banco_de_dados\": \"conectado\", \"mensagem\": \"Dados vindos do Backend Java!\"}";
            
            exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(jsonResponse.getBytes());
            os.close();
        }
    }
}