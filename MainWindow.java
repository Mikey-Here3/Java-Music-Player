package com.mikey.ui;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.io.FileInputStream;
import javazoom.jl.player.advanced.AdvancedPlayer;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Random;

public class MainWindow extends Container {
    private AdvancedPlayer player;
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private JPanel content;
    private HashMap<String, DefaultListModel<String>> playlists;
    private JPanel playlistManagerPanel;
    private JComboBox<String> playlistSelector;
    private DefaultListModel<String> currentPlaylistModel;
    private JList<String> songListView;
    private JPanel albumPanel;
    private DefaultListModel<String> albumListModel;
    private JList<String> albumListView;
    private HashMap<String, DefaultListModel<String>> albums;
    public MainWindow() {
        FlatDarkLaf.setup();
        playlists = new HashMap<>();
        albums = new HashMap<>();


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);

        frame.setTitle("Music Player");
        frame.setLayout(null);

        JPanel sidebar = createSidebarPanel();
        frame.add(sidebar);

        content = createContentPanel();
        frame.add(content);

        frame.setVisible(true);
    }

    private JPanel createSidebarPanel() {
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 200, 700);
        sidebar.setBackground(Color.DARK_GRAY);
        sidebar.setLayout(null);


        JLabel title = new JLabel("Beat Box", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(10, 10, 180, 40);
        sidebar.add(title);


        JButton homeButton = new JButton("Home", new FlatSVGIcon("icons/home.svg", 16, 16));
        homeButton.setBounds(10, 60, 180, 50);
        styleSidebarButton(homeButton);
        homeButton.addActionListener(e -> showContentPanel(HomePanel()));
        sidebar.add(homeButton);

        JButton signUpButton = new JButton("Sign Up", new FlatSVGIcon("icons/signup.svg", 16, 16));
        signUpButton.setBounds(10, 120, 180, 50);
        styleSidebarButton(signUpButton);
        signUpButton.addActionListener(e -> showContentPanel(createSignUpPanel()));
        sidebar.add(signUpButton);

        JButton loginButton = new JButton("Login", new FlatSVGIcon("icons/login.svg", 16, 16));
        loginButton.setBounds(10, 180, 180, 50);
        styleSidebarButton(loginButton);
        loginButton.addActionListener(e -> showContentPanel(createLoginPanel()));
        sidebar.add(loginButton);

        JButton playlistButton = new JButton("Playlist", new FlatSVGIcon("icons/playlist.svg", 16, 16));
        playlistButton.setBounds(10, 240, 180, 50);
        styleSidebarButton(playlistButton);
        playlistButton.addActionListener(e -> showContentPanel(createPlaylistPanel()));
        sidebar.add(playlistButton);

        JButton albumButton = new JButton("Album", new FlatSVGIcon("icons/album.svg", 16, 16));
        albumButton.setBounds(10, 300, 180, 50);
        styleSidebarButton(albumButton);
        albumButton.addActionListener(e -> showContentPanel(createAlbumPanel()));
        sidebar.add(albumButton);

        JButton addSongButton = new JButton("Play Song", new FlatSVGIcon("icons/addsong.svg", 16, 16));
        addSongButton.setBounds(10, 360, 180, 50);
        styleSidebarButton(addSongButton);
        addSongButton.addActionListener(e -> showContentPanel(createPlaySongPanel()));
        sidebar.add(addSongButton);

//        JButton profileButton = new JButton("Profile", new FlatSVGIcon("icons/profile.svg", 16, 16));
//        profileButton.setBounds(10, 420, 180, 50);
//        styleSidebarButton(profileButton);
//        profileButton.addActionListener(e -> showContentPanel(createProfilePanel()));
//        sidebar.add(profileButton);

        return sidebar;
    }

    private void styleSidebarButton(JButton button) {
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private JPanel createContentPanel() {
        JPanel content = new JPanel();
        content.setBounds(200, 0, 800, 700);
        content.setLayout(null);

        FlatSVGIcon icon = new FlatSVGIcon("icons/cover-audio.svg", 500, 500);
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(300, 10, 500, 500);
        content.add(iconLabel);

        JLabel features = new JLabel("Features", SwingConstants.CENTER);
        features.setForeground(Color.WHITE);
        features.setFont(new Font("Arial", Font.BOLD, 20));
        features.setBounds(20, 20, 300, 50);
        content.add(features);

        String[] featuresList = {"Discover new songs", "Create playlists", "Stream music from your device", "Import your playlists, Audio files", "Profile Management"};

        for (int i = 0; i < featuresList.length; i++) {
            JLabel featureLabel = new JLabel(  (i + 1) + ". " + featuresList[i]);
            featureLabel.setForeground(Color.WHITE);
            featureLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            featureLabel.setBounds(20, 100 + i * 50, 300, 50);
            content.add(featureLabel);
        }
        return content;
    }
    private JPanel HomePanel() {
        JPanel content = new JPanel();
        content.setBounds(00, 0, 800, 700);
        content.setLayout(null);

        FlatSVGIcon icon = new FlatSVGIcon("icons/cover-audio.svg", 500, 500);
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(300, 10, 500, 500);
        content.add(iconLabel);

        JLabel features = new JLabel("Features", SwingConstants.CENTER);
        features.setForeground(Color.WHITE);
        features.setFont(new Font("Arial", Font.BOLD, 20));
        features.setBounds(20, 20, 300, 50);
        content.add(features);

        String[] featuresList = {"Discover new songs", "Create playlists", "Stream music from your device", "Import your playlists, Audio files", "Profile Management"};

        for (int i = 0; i < featuresList.length; i++) {
            JLabel featureLabel = new JLabel(  (i + 1) + ". " + featuresList[i]);
            featureLabel.setForeground(Color.WHITE);
            featureLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            featureLabel.setBounds(20, 100 + i * 50, 300, 50);
            content.add(featureLabel);
        }
        return content;
    }

    private void showContentPanel(JPanel panel) {
        content.removeAll();
        content.add(panel);
        content.revalidate();
        content.repaint();
    }

    private JPanel createSignUpPanel() {
        JPanel signUpPanel = new JPanel();
        signUpPanel.setBounds(0, 0, 1000, 700);
        signUpPanel.setLayout(null);
        signUpPanel.setBackground(Color.DARK_GRAY);



        // Header Section
        JLabel headerLabel = new JLabel("Sign Up",SwingConstants.CENTER);
        headerLabel.setBounds(150, 20, 300, 30);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        signUpPanel.add(headerLabel);

        // Cover Icon
        FlatSVGIcon coverIcon = new FlatSVGIcon("icons/signup-cover.svg", 400, 500);
        JLabel coverLabel = new JLabel(coverIcon);
        coverLabel.setBounds(400, 60, 400, 500);

        // First Name Label and Field
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(150, 60, 100, 20);
        firstNameLabel.setForeground(Color.WHITE);
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpPanel.add(firstNameLabel);

        JTextField firstNameField = createStyledTextField("First Name");
        firstNameField.setBounds(150, 85, 300, 30);
        signUpPanel.add(firstNameField);

        // Last Name Label and Field
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(150, 120, 100, 20);
        lastNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpPanel.add(lastNameLabel);

        JTextField lastNameField = createStyledTextField("Last Name");
        lastNameField.setBounds(150, 145, 300, 30);
        signUpPanel.add(lastNameField);

        // Email Label and Field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(150, 180, 100, 20);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpPanel.add(emailLabel);

        JTextField emailField = createStyledTextField("Email");
        emailField.setBounds(150, 205, 300, 30);
        signUpPanel.add(emailField);

        // Password Label and Field with Placeholder
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(150, 240, 100, 20);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 265, 260, 30);
        passwordField.setEchoChar((char) 0);
        addPlaceholder(passwordField, "Password");
        signUpPanel.add(passwordField);

        // Toggle Password Button
        FlatSVGIcon eyeIcon = new FlatSVGIcon("icons/eye.svg", 20, 20);
        FlatSVGIcon hideIcon = new FlatSVGIcon("icons/hide.svg", 20, 20);

        JButton togglePasswordButton = new JButton(eyeIcon);
        togglePasswordButton.setBounds(420, 265, 30, 30);
        togglePasswordButton.setBackground(Color.DARK_GRAY);
        togglePasswordButton.setFocusPainted(false);

        signUpPanel.add(togglePasswordButton);

        // Confirm Password Label and Field with Placeholder
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(150, 300, 150, 20);
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpPanel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(150, 325, 300, 30);
        confirmPasswordField.setEchoChar((char) 0);
        addPlaceholder(confirmPasswordField, "Confirm Password");
        signUpPanel.add(confirmPasswordField);

        togglePasswordButton.addActionListener(e -> {
            if (passwordField.getEchoChar() == '●') {
                passwordField.setEchoChar((char) 0); // Show password
                confirmPasswordField.setEchoChar((char) 0); // Show password
                togglePasswordButton.setIcon(hideIcon); // Change to hide icon
            } else {
                passwordField.setEchoChar('●'); // Hide password
                confirmPasswordField.setEchoChar('●'); // Hide password
                togglePasswordButton.setIcon(eyeIcon); // Change to eye icon
            }
        });

        // Gender Selection Panel
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(150, 360, 100, 20);
        genderLabel.setForeground(Color.WHITE);
        genderLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpPanel.add(genderLabel);

        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new GridLayout(1, 3, 10, 0));
        genderPanel.setBounds(150, 385, 300, 30);
        genderPanel.setOpaque(false);

        JRadioButton maleButton = new JRadioButton("Male");
        JRadioButton femaleButton = new JRadioButton("Female");
        JRadioButton otherButton = new JRadioButton("Other");

        maleButton.setForeground(Color.WHITE);
        femaleButton.setForeground(Color.WHITE);
        otherButton.setForeground(Color.WHITE);

        maleButton.setBackground(Color.DARK_GRAY);
        femaleButton.setBackground(Color.DARK_GRAY);
        otherButton.setBackground(Color.DARK_GRAY);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);

        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        genderPanel.add(otherButton);

        signUpPanel.add(genderPanel);

        // Country Label and Dropdown
        JLabel countryLabel = new JLabel("Country:");
        countryLabel.setBounds(150, 420, 100, 20);
        countryLabel.setForeground(Color.WHITE);
        countryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpPanel.add(countryLabel);

        JComboBox<String> countryBox = new JComboBox<>(new String[]{"Select Country", "USA", "Canada", "UK"});
        countryBox.setBounds(150, 445, 300, 30);
        signUpPanel.add(countryBox);

        // Agree Checkbox
        JCheckBox agreeCheckBox = new JCheckBox("I agree to the terms and Conditions");
        agreeCheckBox.setBounds(150, 500, 250, 30);
        agreeCheckBox.setBackground(Color.DARK_GRAY);
        agreeCheckBox.setForeground(Color.WHITE);
        signUpPanel.add(agreeCheckBox);

        // Sign-Up Button
        JButton signUpButton = createSVGButton("icons/signup.svg");
        signUpButton.setText("Sign Up");
        signUpButton.setBounds(150, 550, 300, 40);
        signUpButton.setFont(new Font("Arial", Font.PLAIN, 16));
        signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpButton.addActionListener(
                e -> {
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String email = emailField.getText();
                    String password = new String(passwordField.getPassword());
                    String confirmPassword = new String(confirmPasswordField.getPassword());
                    String gender = maleButton.isSelected() ? "Male" : femaleButton.isSelected() ? "Female" : "Other";
                    String country = countryBox.getSelectedItem().toString();
                    boolean agree = agreeCheckBox.isSelected();

                    if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || gender.equals("Select Country") || !agree) {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields and accept the terms.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (!password.equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(null, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mikeyEcommerce", "root", "MikeyHere3")) {
                            // Check if email exists
                            String query = "SELECT COUNT(*) FROM users WHERE email = ?";
                            try (PreparedStatement checkStmt = connection.prepareStatement(query)) {
                                checkStmt.setString(1, email);
                                try (ResultSet rs = checkStmt.executeQuery()) {
                                    rs.next();
                                    if (rs.getInt(1) > 0) {
                                        JOptionPane.showMessageDialog(null, "Email already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        // Insert into database
                                        query = "INSERT INTO users (first_name, last_name, email, password, gender, country) VALUES (?, ?, ?, ?, ?, ?)";
                                        try (PreparedStatement insertStmt = connection.prepareStatement(query)) {
                                            insertStmt.setString(1, firstName);
                                            insertStmt.setString(2, lastName);
                                            insertStmt.setString(3, email);
                                            insertStmt.setString(4, password);
                                            insertStmt.setString(5, gender);
                                            insertStmt.setString(6, country);

                                            insertStmt.executeUpdate();

                                            JOptionPane.showMessageDialog(null, "Sign-up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                                            // Reset Fields
                                            firstNameField.setText("");
                                            lastNameField.setText("");
                                            emailField.setText("");
                                            passwordField.setText("");
                                            confirmPasswordField.setText("");
                                            maleButton.setSelected(false);
                                            femaleButton.setSelected(false);
                                            otherButton.setSelected(false);
                                            countryBox.setSelectedIndex(0);
                                            agreeCheckBox.setSelected(false);
                                        }
                                    }
                                }
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }
        );
        signUpPanel.add(signUpButton);

        JButton loginButton = createSVGButton("icons/login.svg");
        loginButton.setText("Login");
        loginButton.setBounds(150, 600, 300, 40);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> {
            content.removeAll();
            content.add(createLoginPanel());
            content.revalidate();
            content.repaint();
        });
        signUpPanel.add(loginButton);
        signUpPanel.add(coverLabel);

        return signUpPanel;
    }

    // Helper Method to Add Placeholder
    private void addPlaceholder(JTextComponent field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });

    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField(placeholder);
        field.setForeground(Color.GRAY);
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
        return field;
    }

    private JButton createSVGButton(String iconPath) {
        FlatSVGIcon icon = new FlatSVGIcon(iconPath, 20, 20);
        JButton button = new JButton(icon);
        button.setBackground(Color.DARK_GRAY);
        button.setFocusPainted(false);
        return button;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1100, 700);

        JLabel label = new JLabel("Login", SwingConstants.CENTER);
        label.setBounds(100, 120, 250, 50);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(Color.WHITE);
        panel.add(label);



        FlatSVGIcon logincoverIcon = new FlatSVGIcon("icons/login-cover.svg", 350, 400);
        JLabel logincoverLabel = new JLabel(logincoverIcon);
        logincoverLabel.setBounds(350, 40, 400, 500);
        logincoverLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(logincoverLabel);

        JLabel emailLabel = new JLabel("Email:", SwingConstants.LEFT);
        emailLabel.setBounds(50, 200, 100, 30);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(emailLabel);

        JTextField emailField = createStyledTextField("Enter your email");
        emailField.setBounds(150, 200, 200, 30);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
        passwordLabel.setBounds(50, 250, 100, 30);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(passwordLabel);

        JTextField passwordField = createStyledTextField("Enter your password");
        passwordField.setBounds(150, 250, 200, 30);
//        passwordField.setEchoChar('*');
        panel.add(passwordField);

        JCheckBox rememberMeCheckbox = new JCheckBox("Remember Me");
        rememberMeCheckbox.setBounds(50, 300, 200, 30);
        rememberMeCheckbox.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(rememberMeCheckbox);

        JLabel forgotlabel = new JLabel("Forgot Password?");
        forgotlabel.setBounds(250, 300, 300, 30);
        forgotlabel.setFont(new Font("Arial", Font.PLAIN, 16));
        forgotlabel.setForeground(Color.WHITE);
        forgotlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(forgotlabel);

        forgotlabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
//                Making Underlined
                forgotlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                forgotlabel.setText("<html><u>Forgot Password?</u></html>");
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                createForgotPasswordFrame();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                forgotlabel.setText("Forgot Password?");
            }
            private Component createForgotPasswordFrame() {
                JFrame frame = new JFrame("Forgot Password");
                frame.setSize(300, 200);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().setLayout(new FlowLayout());

                JLabel label = new JLabel("Enter your email to reset your password");
                label.setBounds(10, 10, 380, 20);
                frame.add(label);

                JTextField emailField = new JTextField();
                emailField.setBounds(10, 40, 380, 20);
                frame.add(emailField);

                JButton resetButton = new JButton("Reset Password");
                resetButton.setBounds(10, 70, 380, 20);
                resetButton.addActionListener(e -> {
                    String email = emailField.getText();
                    resetPassword(email);
                    frame.dispose();
                });
                frame.add(resetButton);

                JLabel orLabel = new JLabel("or", SwingConstants.CENTER);
                orLabel.setBounds(10, 100, 380, 20);
                frame.add(orLabel);

                JButton cancelButton = new JButton("Cancel");
                cancelButton.setBounds(10, 130, 380, 20);
                cancelButton.addActionListener(e -> frame.dispose());
                frame.add(cancelButton);

                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
                frame.setLocationRelativeTo(null);


                frame.setLayout(null);
                frame.setVisible(true);
                return frame;
            }

            private void resetPassword(String email) {
                Connection conn = null;
                try {
                    String url = "jdbc:mysql://localhost:3306/MikeyEcommerce";
                    String username = "root";
                    String password = "MikeyHere3";
                    conn = DriverManager.getConnection(url, username, password);
                    String query = "SELECT * FROM users WHERE email = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, email);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        String newPassword = generateRandomPassword();
                        String updateQuery = "UPDATE users SET password = ? WHERE email = ?";
                        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                        updateStmt.setString(1, newPassword);
                        updateStmt.setString(2, email);
                        updateStmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Password reset successfully. New password: " + newPassword);
                    } else {
                        JOptionPane.showMessageDialog(null, "Email not found.");
                    }
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            private String generateRandomPassword() {
                StringBuilder sb = new StringBuilder(16);
                Random random = new Random();
                for (int i = 0; i < 5; i++) {
                    int randomNumber = random.nextInt(62);
                    if (randomNumber < 10) {
                        sb.append(randomNumber);
                    } else if (randomNumber < 36) {
                        sb.append((char) ('A' + randomNumber - 10));
                    } else {
                        sb.append((char) ('a' + randomNumber - 36));
                    }
                }
                return sb.toString();
            }
        });

        JButton loginButton = createSVGButton("icons/login.svg");
        loginButton.setText("Login");
        loginButton.setBounds(150, 350, 200, 40);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> {});
        panel.add(loginButton);

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String emailget = emailField.getText();
                String passwordget = new String(passwordField.getText());

                if (emailget.isEmpty() || passwordget.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter email and password.");
                    return;
                }

                boolean isLoginSuccessful = loginUser(emailget, passwordget);
                if (isLoginSuccessful) {
                    // Navigate to the next screen or functionality
                    System.out.println("User logged in successfully!");
                }

            }
        });

        JLabel orLabel = new JLabel("or",SwingConstants.CENTER);
        orLabel.setBounds(150, 400, 200, 30);
        orLabel.setForeground(Color.WHITE);
        orLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(orLabel);

        JButton registerButton = createSVGButton("icons/signup.svg");
        registerButton.setText("Register");
        registerButton.setBounds(170, 430, 150, 40);
        registerButton.setFont(new Font("Arial", Font.PLAIN, 16));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(e -> {});
        panel.add(registerButton);

        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                content.removeAll();
                content.add(createSignUpPanel());
                content.revalidate();
                content.repaint();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                registerButton.setText("<html><u>Register</u></html>");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                registerButton.setText("Register");
            }
        });


        panel.setLayout(null);
        return panel;
    }
    private int currentUserId = -1; // Holds the current user ID after successful login

    private int getCurrentUserId() throws SQLException {
        int userId = 1; // Example
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT id FROM users WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) {
                    throw new SQLException("User ID does not exist in the database.");
                }
            }
        }
        return userId;
    }

    public boolean loginUser(String email, String password) {
        // Database connection details
        String dbURL = "jdbc:mysql://localhost:3306/mikeyEcommerce";
        String dbUser = "root";
        String dbPassword = "MikeyHere3";

        // SQL Query to fetch user by email and password
        String query = "SELECT id FROM users WHERE email = ? AND password = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            // Fetch the user ID for the given email and password
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Credentials are valid, retrieve the user ID
                currentUserId = rs.getInt("id");

                JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid email or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {}
        }

        return false;
    }
    private JPanel createPlaylistPanel() {
        playlistManagerPanel = new JPanel();
        playlistManagerPanel.setBounds(0, 0, 800, 700);
        playlistManagerPanel.setBackground(Color.DARK_GRAY);
        playlistManagerPanel.setLayout(null);

        FlatSVGIcon cover = new FlatSVGIcon("icons/playlist-cover.svg", 200, 200);
        JLabel labelcover = new JLabel(cover);
        labelcover.setBounds(20, 50, 200, 200);
        playlistManagerPanel.add(labelcover);

        FlatSVGIcon cover2 = new FlatSVGIcon("icons/playlist-cover2.svg", 200, 200);
        JLabel labelcover2 = new JLabel(cover2);
        labelcover2.setBounds(550, 50, 200, 200);
        playlistManagerPanel.add(labelcover2);

        JLabel titleLabel = new JLabel("Playlist Manager", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(250, 20, 300, 40);
        playlistManagerPanel.add(titleLabel);

        JButton createPlaylistButton = new JButton("Create Playlist");
        createPlaylistButton.setBounds(300, 70, 200, 50);
        styleSidebarButton(createPlaylistButton);
        createPlaylistButton.addActionListener(e -> {
            openPlaylistCreationDialog();
            refreshPlaylistSelector(); // Ensure the selector is updated immediately
        });
        playlistManagerPanel.add(createPlaylistButton);

        JButton removePlaylistButton = new JButton("Remove Playlist");
        removePlaylistButton.setBounds(300, 130, 200, 50);
        styleSidebarButton(removePlaylistButton);
        removePlaylistButton.addActionListener(e -> {
            removePlaylist();
            refreshPlaylistSelector(); // Ensure the selector is updated immediately
        });
        playlistManagerPanel.add(removePlaylistButton);

        JButton addSongsButton = new JButton("Add Songs");
        addSongsButton.setBounds(300, 190, 200, 50);
        styleSidebarButton(addSongsButton);
        addSongsButton.addActionListener(e -> openSongDialog());
        playlistManagerPanel.add(addSongsButton);

        playlistSelector = new JComboBox<>();
        playlistSelector.setBounds(300, 250, 200, 30);
        playlistSelector.addActionListener(e -> loadPlaylist());
        playlistManagerPanel.add(playlistSelector);

        currentPlaylistModel = new DefaultListModel<>();
        songListView = new JList<>(currentPlaylistModel);
        songListView.setDragEnabled(true);
        songListView.setDropMode(DropMode.ON);
        JScrollPane scrollPane = new JScrollPane(songListView);
        scrollPane.setBounds(200, 290, 400, 400);
        playlistManagerPanel.add(scrollPane);

        // Initial refresh to populate the combo box
        refreshPlaylistSelector();

        return playlistManagerPanel;
    }

    private void removePlaylist() {
        String selectedPlaylist = (String) playlistSelector.getSelectedItem();
        if (selectedPlaylist != null) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                conn.setAutoCommit(false); // Start transaction
                int userId = getCurrentUserId();
                int playlistId = getPlaylistId(conn, userId, selectedPlaylist);

                if (playlistId > 0) {
                    // Delete all songs associated with the playlist
                    String deleteSongsQuery = "DELETE FROM songs WHERE playlist_id = ?";
                    try (PreparedStatement deleteSongsStmt = conn.prepareStatement(deleteSongsQuery)) {
                        deleteSongsStmt.setInt(1, playlistId);
                        deleteSongsStmt.executeUpdate();
                    }

                    // Delete the playlist
                    String deletePlaylistQuery = "DELETE FROM playlists WHERE id = ? AND user_id = ?";
                    try (PreparedStatement deletePlaylistStmt = conn.prepareStatement(deletePlaylistQuery)) {
                        deletePlaylistStmt.setInt(1, playlistId);
                        deletePlaylistStmt.setInt(2, userId);
                        deletePlaylistStmt.executeUpdate();
                    }

                    conn.commit(); // Commit transaction
                    refreshPlaylistSelector();
                    JOptionPane.showMessageDialog(null, "Playlist removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Playlist not found.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error removing playlist.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No playlist selected.");
        }
    }
    private void openSongDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("MP3 Files", "mp3"));
        fileChooser.setMultiSelectionEnabled(true);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();

            String selectedPlaylist = (String) playlistSelector.getSelectedItem();
            if (selectedPlaylist != null) {
                try (Connection conn = DatabaseConnection.getConnection()) {
                    int userId = getCurrentUserId();
                    int playlistId = getPlaylistId(conn, userId, selectedPlaylist);
                    if (playlistId > 0) {
                        String query = "INSERT INTO songs (playlist_id, song_name, url) VALUES (?, ?, ?)";
                        try (PreparedStatement stmt = conn.prepareStatement(query)) {
                            for (File file : selectedFiles) {
                                stmt.setInt(1, playlistId);
                                stmt.setString(2, file.getName());
                                stmt.setString(3, file.getAbsolutePath());
                                stmt.executeUpdate();
                                System.out.println("Inserted song: " + file.getName());
                            }
                        }
                        loadPlaylist();
                        JOptionPane.showMessageDialog(null, "Songs added successfully.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding songs.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a playlist first.");
            }
        }
    }

    private int getPlaylistId(Connection conn, int userId, String playlistName) throws SQLException {
        String query = "SELECT id FROM playlists WHERE user_id = ? AND name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, playlistName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("id");
        }
        return -1;
    }

    private void refreshPlaylistSelector() {
        playlistSelector.removeAllItems(); // Clear existing items

        try (Connection conn = DatabaseConnection.getConnection()) {
            int userId = getCurrentUserId();
            String query = "SELECT name FROM playlists WHERE user_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();

                boolean hasPlaylists = false;
                while (rs.next()) {
                    String playlistName = rs.getString("name");
                    playlistSelector.addItem(playlistName);
                    hasPlaylists = true;
                }

                if (hasPlaylists) {
                    playlistSelector.setSelectedIndex(0); // Automatically select the first playlist
                    loadPlaylist(); // Load the selected playlist
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error refreshing playlists.");
        }
    }

    private void openPlaylistCreationDialog() {
        String playlistName = JOptionPane.showInputDialog(null, "Enter Playlist Name:");
        if (playlistName != null && !playlistName.trim().isEmpty()) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                int userId = getCurrentUserId();

                // Check if the playlist already exists for the user
                if (isPlaylistExists(conn, userId, playlistName)) {
                    JOptionPane.showMessageDialog(null, "A playlist with this name already exists.");
                    return;
                }

                // Insert the new playlist
                String query = "INSERT INTO playlists (user_id, name) VALUES (?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, userId);
                    stmt.setString(2, playlistName);
                    stmt.executeUpdate();
                    refreshPlaylistSelector();
                    JOptionPane.showMessageDialog(null, "Playlist created successfully.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error creating playlist.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Playlist name cannot be empty.");
        }
    }

    // Helper method to check if the playlist already exists
    private boolean isPlaylistExists(Connection conn, int userId, String playlistName) throws SQLException {
        String query = "SELECT COUNT(*) FROM playlists WHERE user_id = ? AND name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, playlistName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Returns true if a playlist exists
            }
        }
        return false;
    }

    private void loadPlaylist() {
        String selectedPlaylist = (String) playlistSelector.getSelectedItem();
        if (selectedPlaylist != null) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                int userId = getCurrentUserId();
                String query = "SELECT song_name FROM songs s " +
                        "JOIN playlists p ON s.playlist_id = p.id " +
                        "WHERE p.user_id = ? AND p.name = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, userId);
                    stmt.setString(2, selectedPlaylist);
                    ResultSet rs = stmt.executeQuery();

                    currentPlaylistModel.clear();
                    while (rs.next()) {
                        currentPlaylistModel.addElement(rs.getString("song_name"));
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading playlist.");
            }
        }
    }

    private JPanel createPlaySongPanel() {
        JPanel playSongPanel = new JPanel();
        playSongPanel.setBounds(0, 0, 800, 700);
        playSongPanel.setBackground(Color.DARK_GRAY);
        playSongPanel.setLayout(null);

        // Title label
        JLabel titleLabel = new JLabel("Play Songs", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(250, 20, 300, 40);
        playSongPanel.add(titleLabel);

        // Playlist selector label
        JLabel playlistLabel = new JLabel("Select Playlist:", SwingConstants.LEFT);
        playlistLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        playlistLabel.setForeground(Color.WHITE);
        playlistLabel.setBounds(50, 100, 200, 30);
        playSongPanel.add(playlistLabel);

        // Playlist selector dropdown
        JComboBox<String> playlistDropdown = new JComboBox<>();
        playlistDropdown.setBounds(50, 140, 200, 30);
        playSongPanel.add(playlistDropdown);

        // Song list label
        JLabel songListLabel = new JLabel("Songs:", SwingConstants.LEFT);
        songListLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        songListLabel.setForeground(Color.WHITE);
        songListLabel.setBounds(50, 200, 200, 30);
        playSongPanel.add(songListLabel);

        // Song list view
        DefaultListModel<String> songListModel = new DefaultListModel<>();
        JList<String> songListView = new JList<>(songListModel);
        songListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songListView.setForeground(Color.WHITE);
        songListView.setBackground(Color.DARK_GRAY);
        JScrollPane scrollPane = new JScrollPane(songListView);
        scrollPane.setBounds(50, 250, 250, 200);
        playSongPanel.add(scrollPane);

        // Load playlists into the dropdown
        loadPlaylistsIntoDropdown(playlistDropdown, songListModel);

        // Add action listener to playlist selector
        playlistDropdown.addActionListener(e -> {
            String selectedPlaylist = (String) playlistDropdown.getSelectedItem();
            if (selectedPlaylist != null) {
                loadSongsForPlaylist(selectedPlaylist, songListModel);
            }
        });
        FlatSVGIcon playIcon = new FlatSVGIcon("icons/play.svg", 20, 20);
        JButton playButton = new JButton("Play", playIcon);
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playButton.addActionListener(e -> {
            String selectedSong = songListView.getSelectedValue();
            if (selectedSong != null) {
                playSong(selectedSong);
            }
        });
        playButton.setBounds(50, 500, 200, 50);
        playSongPanel.add(playButton);

        // Add action listener to upload button

//        Pause button
        FlatSVGIcon pauseIcon = new FlatSVGIcon("icons/pause.svg", 20, 20);
        JButton pauseButton = new JButton("Pause", pauseIcon);
        pauseButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pauseButton.addActionListener(e -> {
            if (player != null) {
                stopPlaying();
            }
        });
        pauseButton.setBounds(300, 500, 200, 50);
        playSongPanel.add(pauseButton);

        playSongPanel.setVisible(true);

        return playSongPanel;
    }

    private void playSong(String songName) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT url FROM songs WHERE song_name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, songName);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String songPath = rs.getString("url");
                    if (songPath != null && !songPath.isEmpty()) {
                        System.out.println("Fetched path: " + songPath);
                        playMp3(songPath);
                    } else {
                        JOptionPane.showMessageDialog(null, "No valid path found for song.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Song not found in the database.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching song from database.");
        }
    }
    private void playMp3(String mp3Path) {
        System.out.println("Attempting to play: " + mp3Path); // Debugging
        try {
            if (player != null) {
                player.close();
            }

            File file = new File(mp3Path);
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "MP3 path does not exist: " + mp3Path);
                return;
            }
            player = new AdvancedPlayer(new FileInputStream(file));
            new Thread(() -> {
                try {
                    player.play(0, Integer.MAX_VALUE);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error playing MP3.");
                }
            }).start();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not play song.");
        }
    }
    private void stopPlaying() {
        if (player != null) {
            player.close();
        }
    }
    private void loadPlaylistsIntoDropdown(JComboBox<String> playlistDropdown, DefaultListModel<String> songListModel) {
        playlistDropdown.removeAllItems(); // Clear existing items
        try (Connection conn = DatabaseConnection.getConnection()) {
            int userId = getCurrentUserId();
            String query = "SELECT name FROM playlists WHERE user_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    playlistDropdown.addItem(rs.getString("name"));
                }

                // Automatically load the first playlist's songs if available
                if (playlistDropdown.getItemCount() > 0) {
                    playlistDropdown.setSelectedIndex(0);
                    String firstPlaylist = (String) playlistDropdown.getItemAt(0);
                    loadSongsForPlaylist(firstPlaylist, songListModel);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading playlists.");
        }
    }

    private void loadSongsForPlaylist(String playlistName, DefaultListModel<String> songListModel) {
        songListModel.clear(); // Clear the current song list
        try (Connection conn = DatabaseConnection.getConnection()) {
            int userId = getCurrentUserId();
            String query = "SELECT song_name FROM songs s " +
                    "JOIN playlists p ON s.playlist_id = p.id " +
                    "WHERE p.user_id = ? AND p.name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                stmt.setString(2, playlistName);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    songListModel.addElement(rs.getString("song_name"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading songs.");
        }
    }


    private JPanel createAlbumPanel() {
        albumPanel = new JPanel();
        albumPanel.setBounds(0, 0, 800, 700);
        albumPanel.setBackground(Color.DARK_GRAY);
        albumPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Album Viewer", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(250, 20, 300, 40);
        albumPanel.add(titleLabel);

        JButton addAlbumButton = new JButton("Add Album");
        addAlbumButton.setBounds(300, 70, 200, 30);
        addAlbumButton.addActionListener(e -> openAlbumDialog());
        albumPanel.add(addAlbumButton);

        JButton removeAlbumButton = new JButton("Remove Album");
        removeAlbumButton.setBounds(300, 120, 200, 30);
        removeAlbumButton.addActionListener(e -> removeAlbum());
        albumPanel.add(removeAlbumButton);

        albumListModel = new DefaultListModel<>();
        albumListView = new JList<>(albumListModel);
        JScrollPane scrollPane = new JScrollPane(albumListView);
        scrollPane.setBounds(200, 160, 400, 400);
        albumPanel.add(scrollPane);

        refreshAlbumList();
        return albumPanel;
    }

    private void removeAlbum() {
        String selectedAlbum = albumListView.getSelectedValue();
        if (selectedAlbum != null && albums.containsKey(selectedAlbum)) {
            albums.remove(selectedAlbum);
            refreshAlbumList();
            JOptionPane.showMessageDialog(null, "Album removed successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No album selected or album does not exist.");
        }
    }

    private void openAlbumDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            if (selectedDirectory != null) {
                String albumName = selectedDirectory.getName();

                // Ensure playlist creation if not already exists
                if (!isPlaylistExistsInDB(albumName)) {
                    try (Connection conn = DatabaseConnection.getConnection()) {
                        conn.setAutoCommit(false); // Enable transactions
                        String insertPlaylistQuery = "INSERT INTO playlists (user_id, name) VALUES (?, ?)";
                        try (PreparedStatement stmt = conn.prepareStatement(insertPlaylistQuery)) {
                            stmt.setInt(1, getCurrentUserId());
                            stmt.setString(2, albumName);
                            stmt.executeUpdate();
                        }

                        int playlistId = getPlaylistId(conn, getCurrentUserId(), albumName);
                        if (playlistId != -1) {
                            File[] songs = selectedDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
                            if (songs != null) {
                                String insertSongQuery = "INSERT INTO songs (playlist_id, song_name, url) VALUES (?, ?, ?)";
                                try (PreparedStatement stmt = conn.prepareStatement(insertSongQuery)) {
                                    for (File song : songs) {
                                        stmt.setInt(1, playlistId);
                                        stmt.setString(2, song.getName());
                                        stmt.setString(3, song.getAbsolutePath());
                                        stmt.executeUpdate();
                                    }
                                }
                            }
                        }

                        conn.commit();
                        refreshPlaylistSelector();
                        JOptionPane.showMessageDialog(null, "Songs and Playlist created successfully!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error adding songs to the playlist.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Playlist already exists!");
                }
            }
        }
    }
    private boolean isPlaylistExistsInDB(String playlistName) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM playlists WHERE user_id = ? AND name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, getCurrentUserId());
                stmt.setString(2, playlistName);
                ResultSet rs = stmt.executeQuery();
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void refreshAlbumList() {
        albumListModel.clear();
        for (String albumName : albums.keySet()) {
            albumListModel.addElement(albumName);
        }
    }



    public static void main(String[] args) {
        new MainWindow();
    }
}
