Here's my feedback:

Although there are many ways to design the classes , this is what I expect you when you design your classes:


1.
Create Interface or Abstract class ---Chat

Add abstract methods: public void write(String message, String url)
                    : public List<String> read(String url)
					: public void display(List messages)
					: Add any other methods if you have more
3.					
Extend or implement Chat ---> create PublicChat	class		

3.
Extend PublicChat or implement Chat       --->  create PrivateChat class
 Add Method(you dont have to create a separate class for this unless you feel obliged) :public List<String> getFriends(String name)
4.  Add Class Friend.
   Add method: addFriend()
             : updateFriend()
             : deleteFriend()
5. You normally have two classes for SystemExceptionHandler and CustomExceptionHandler.I expect these classes to inherit one of Exception classes. So , you shouldnt extend one of these
in your Class but create instances of them and write your logs.

Check this link for a good example of how to design your classes, OO Design
https://www.pearsonenespanol.com/docs/librariesprovider5/deitel-adicionales-9e/como-programar-en-c-14/material-complementario/cap26.pdf?sfvrsn=3ee3d2b2_2
 
Finally, 
Create All classes separatly!

- Revise these sections: Methods and Encapsulation,Class Design

-------------------------------------------------------------------



Good!

Add Comments in your codes. in each classes add something like these :
What is Javadoc?
Javadoc is a tool which comes with JDK and it is used for generating Java code documentation in HTML format from Java source code, which requires documentation in a predefined format.

Following is a simple example where the lines inside /*….*/ are Java multi-line comments. Similarly, the line which preceeds // is Java single-line comment.


Example:

/**
* The HelloWorld program implements an application that
* simply displays "Hello World!" to the standard output.
*
* @author  Addisu Thomas
* @version 1.0
* @since   2023-01-01 
*/
public class HelloWorld {

   public static void main(String[] args) {
      // Prints Hello, World! on standard output.
      System.out.println("Hello World!");
   }
}
