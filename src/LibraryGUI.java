import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LibraryGUI extends JFrame {
    // In-memory lists to simulate storage
    private java.util.List<User> users = new ArrayList<>();
    private java.util.List<Book> books = new ArrayList<>();

    // Components for User Interface
    private JTextField userNameField, userEmailField, bookTitleField, bookAuthorField, bookCopiesField;
    private JTextArea userArea, bookArea;

    public LibraryGUI() {
        // Set up the JFrame
        setTitle("Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create and set up panels
        JPanel userPanel = new JPanel(new GridLayout(3, 2));
        JPanel bookPanel = new JPanel(new GridLayout(3, 2));
        JPanel actionPanel = new JPanel();

        // User input fields
        userPanel.add(new JLabel("User Name:"));
        userNameField = new JTextField();
        userPanel.add(userNameField);

        userPanel.add(new JLabel("User Email:"));
        userEmailField = new JTextField();
        userPanel.add(userEmailField);

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = userNameField.getText();
                String email = userEmailField.getText();
                if (!name.isEmpty() && !email.isEmpty()) {
                    users.add(new User(name, email));
                    userNameField.setText("");
                    userEmailField.setText("");
                    updateUserArea();
                }
            }
        });
        userPanel.add(addUserButton);

        // Book input fields
        bookPanel.add(new JLabel("Book Title:"));
        bookTitleField = new JTextField();
        bookPanel.add(bookTitleField);

        bookPanel.add(new JLabel("Book Author:"));
        bookAuthorField = new JTextField();
        bookPanel.add(bookAuthorField);

        bookPanel.add(new JLabel("Copies Available:"));
        bookCopiesField = new JTextField();
        bookPanel.add(bookCopiesField);

        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = bookTitleField.getText();
                String author = bookAuthorField.getText();
                int copies;
                try {
                    copies = Integer.parseInt(bookCopiesField.getText());
                    if (copies > 0 && !title.isEmpty() && !author.isEmpty()) {
                        Book newBook = new Book(title, author, copies);
                        books.add(newBook);
                        System.out.println("Book Added: " + newBook);  // Debugging statement
                        bookTitleField.setText("");
                        bookAuthorField.setText("");
                        bookCopiesField.setText("");
                        updateBookArea();
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter valid data for all fields.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for copies.");
                }
            }
        });
        bookPanel.add(addBookButton);

        // Text Areas to display the list of users and books
        userArea = new JTextArea(10, 30);
        userArea.setEditable(false);
        JScrollPane userScrollPane = new JScrollPane(userArea);

        bookArea = new JTextArea(10, 30);
        bookArea.setEditable(false);
        JScrollPane bookScrollPane = new JScrollPane(bookArea);

        // Action panel to add all elements together
        actionPanel.add(new JLabel("Users:"));
        actionPanel.add(userScrollPane);
        actionPanel.add(new JLabel("Books:"));
        actionPanel.add(bookScrollPane);

        // Add panels to frame
        add(userPanel, BorderLayout.NORTH);
        add(bookPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
    }

    // Method to update the user list
    private void updateUserArea() {
        StringBuilder sb = new StringBuilder();
        for (User user : users) {
            sb.append(user).append("\n");
        }
        userArea.setText(sb.toString());
    }

    // Method to update the book list
    private void updateBookArea() {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(book).append("\n");
        }
        bookArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryGUI().setVisible(true);
            }
        });
    }
}
