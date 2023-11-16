import java.io.*;
import java.util.*;
import ChatExceptions.*;

/*
* ChatApplication class contains the main method and serves as the entry point for the chat application. 
* It initializes instances of PublicChat, PrivateChat, and FriendsList classes. It's a menu-driven 
* interface for users to send messages to public and private chats, add friends, and view chat messages and friends list. 
* It handles exceptions using the CustomExceptionHandler and SystemExceptionHandler classes.
* @author  Abdulwasse Yenus
* @version 1.0
* @since   11/16/2023
*/
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

/*
 * PublicChat class is responsible for handling public chat functionality. It
 * allows users to write messages to a public chat file and read/display
 * messages from the public chat file. The CustomExceptionHandler class is used
 * for handling exceptions in the program.
 * 
 * @author Abdulwasse Yenus
 * 
 * @version 1.0
 * 
 * @since 11/16/2023
 */
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

/*
 * PrivateChat class manages the private chat feature. It enables users to write
 * messages to a specific recipient in a private chat file and read/display
 * messages from the private chat file. The CustomExceptionHandler class is used
 * for handling exceptions in the program.
 * 
 * @author Abdulwasse Yenus
 * 
 * @version 1.0
 * 
 * @since 11/16/2023
 */
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

/*
 * FriendsList class handles the management of a user's friends list. It allows
 * users to add friends to the list and read/display the list of friends. The
 * CustomExceptionHandler class is used for handling exceptions in the program.
 * 
 * @author Abdulwasse Yenus
 * 
 * @version 1.0
 * 
 * @since 11/16/2023
 */
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