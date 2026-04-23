import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Book Node (Linked List)
class Book {
    int id;
    String title;
    String author;
    Book next;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.next = null;
    }
}

public class LibrarySystemUI {

    static Book head = null;

    // Add Book
    static void addBook(int id, String title, String author) {
        Book newBook = new Book(id, title, author);

        if (head == null) {
            head = newBook;
        } else {
            Book temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newBook;
        }
    }

    // Search Book
    static Book searchBook(int id) {
        Book temp = head;
        while (temp != null) {
            if (temp.id == id) return temp;
            temp = temp.next;
        }
        return null;
    }

    // Delete Book
    static boolean deleteBook(int id) {
        Book temp = head, prev = null;

        if (temp != null && temp.id == id) {
            head = temp.next;
            return true;
        }

        while (temp != null && temp.id != id) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) return false;

        prev.next = temp.next;
        return true;
    }

    // Display Books
    static String displayBooks() {
        StringBuilder sb = new StringBuilder();
        Book temp = head;

        if (temp == null) return "No books available.";

        while (temp != null) {
            sb.append("ID: ").append(temp.id)
              .append(", Title: ").append(temp.title)
              .append(", Author: ").append(temp.author)
              .append("\n");
            temp = temp.next;
        }
        return sb.toString();
    }

    // UI
    public static void main(String[] args) {

        JFrame frame = new JFrame("Library Management System");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextField idField = new JTextField(10);
        JTextField titleField = new JTextField(10);
        JTextField authorField = new JTextField(10);

        JTextArea output = new JTextArea(15, 40);
        output.setEditable(false);

        JButton addBtn = new JButton("Add");
        JButton searchBtn = new JButton("Search");
        JButton deleteBtn = new JButton("Delete");
        JButton displayBtn = new JButton("Display");

        frame.add(new JLabel("ID:"));
        frame.add(idField);
        frame.add(new JLabel("Title:"));
        frame.add(titleField);
        frame.add(new JLabel("Author:"));
        frame.add(authorField);

        frame.add(addBtn);
        frame.add(searchBtn);
        frame.add(deleteBtn);
        frame.add(displayBtn);

        frame.add(new JScrollPane(output));

        // Add Action
        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String title = titleField.getText();
                String author = authorField.getText();

                addBook(id, title, author);
                output.setText("Book added successfully!");
            } catch (Exception ex) {
                output.setText("Invalid input!");
            }
        });

        // Search Action
        searchBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Book b = searchBook(id);

                if (b != null) {
                    output.setText("Found:\nTitle: " + b.title + "\nAuthor: " + b.author);
                } else {
                    output.setText("Book not found.");
                }
            } catch (Exception ex) {
                output.setText("Invalid input!");
            }
        });

        // Delete Action
        deleteBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                boolean deleted = deleteBook(id);

                if (deleted) {
                    output.setText("Book deleted.");
                } else {
                    output.setText("Book not found.");
                }
            } catch (Exception ex) {
                output.setText("Invalid input!");
            }
        });

        // Display Action
        displayBtn.addActionListener(e -> {
            output.setText(displayBooks());
        });

        frame.setVisible(true);
    }
}