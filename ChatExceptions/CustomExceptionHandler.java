package ChatExceptions;
import java.io.*;
public class CustomExceptionHandler {

    //customized exception handler for chat
    public static class ChatException extends IOException {
        public ChatException(String message) {
            super(message);
        }
    }

    public static void chatExceptionHandler(ChatException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
