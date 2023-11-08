import java.io.*;
import java.util.*;
import ChatExceptions.*;

public class ChatApplication extends CustomExceptionHandler {
    static SystemExceptionHandler systemExceptionHandler = new SystemExceptionHandler();
    private static final String PUBLIC_CHAT_FILE = "C://Users//abdul//Desktop//java_backend//java_for_backend//practice//files//Eurakarte.log";
    private static final String PRIVATE_CHAT_FILE = "C://Users//abdul//Desktop//java_backend//java_for_backend//practice//files//Donut[AFK].log";
    private static final String FRIENDS_LIST_FILE = "C://Users//abdul//Desktop//java_backend//java_for_backend//practice//files//friends.list";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PublicChat publicChat = new PublicChat(PUBLIC_CHAT_FILE);
        PrivateChat privateChat = new PrivateChat(PRIVATE_CHAT_FILE);
        FriendsList friendsList = new FriendsList(FRIENDS_LIST_FILE);
        try {
            while (true) {
                System.out.println("============================================");
                System.out.println("Press 1 => to send a message to public chat");
                System.out.println("Press 2 => to send a message to private chat");
                System.out.println("Press 3 => to add a friend");
                System.out.println("Press 4 => to view public chat messages");
                System.out.println("Press 5 => to view pivate chat messages");
                System.out.println("Press 6 => to view friends list");
                System.out.println("Press 7 => to exit");
                System.out.println("============================================");
                System.out.print("Your choice => ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter your message => ");
                        String publicMessage = scanner.nextLine();
                        publicChat.write(publicMessage);
                        break;
                    case 2:
                        System.out.print("Enter the recipient's name => ");
                        String recipient = scanner.nextLine();
                        System.out.print("Enter your message => ");
                        String privateMessage = scanner.nextLine();
                        privateChat.write(recipient, privateMessage);
                        break;
                    case 3:
                        System.out.print("Enter the name of the friend you want to add => ");
                        String friend = scanner.nextLine();
                        friendsList.add(friend);
                        break;
                    case 4:
                        publicChat.readAndDisplay();
                        break;
                    case 5:
                        privateChat.readAndDisplay();
                        break;
                    case 6:
                        System.out.println("Friends list:");
                        List<String> friends = friendsList.read();
                        for (String friendName : friends) {
                            System.out.println(friendName);
                        }
                        break;
                    case 7:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }
            }
        } catch (Exception e) {
            systemExceptionHandler.systemException(e);
        }
    }
}


class PublicChat extends CustomExceptionHandler {
    private final String fileName;

    public PublicChat(String fileName) {
        this.fileName = fileName;
    }

    public void write(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(message);
        } catch (IOException e) {
            System.out.println("Error writing to public chat file: " + e.getMessage());
        }
    }

    public void readAndDisplay() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (CustomExceptionHandler.ChatException e) {
            CustomExceptionHandler.chatExceptionHandler(e);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

class PrivateChat {
    private final String fileName;

    public PrivateChat(String fileName) {
        this.fileName = fileName;
    }

    public void write(String recipient, String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(recipient + " => " + message);
        } catch (CustomExceptionHandler.ChatException e) {
            CustomExceptionHandler.chatExceptionHandler(e);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void readAndDisplay() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (CustomExceptionHandler.ChatException e) {
            CustomExceptionHandler.chatExceptionHandler(e);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

class FriendsList {
    private final String fileName;

    public FriendsList(String fileName) {
        this.fileName = fileName;
    }

    public void add(String friend) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(friend);
        } catch (CustomExceptionHandler.ChatException e) {
            CustomExceptionHandler.chatExceptionHandler(e);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<String> read() {
        List<String> friends = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                friends.add(line);
            }
        } catch (CustomExceptionHandler.ChatException e) {
            CustomExceptionHandler.chatExceptionHandler(e);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return friends;
    }
}