import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class WP_RegForm extends JPanel implements Customizable {

    // Form panel sub-panels
    private JPanel headerPanel = new JPanel(new GridBagLayout());
    private JPanel fieldsPanel = new JPanel(new GridBagLayout());
    private JPanel buttonsPanel = new JPanel(new GridBagLayout());

    // GUI components
    public JLabel formHeader = new JLabel("User Registration");

    // Labels and fields 
    private JLabel firstNameLabel = new JLabel("First name");
    private JLabel lastNameLabel = new JLabel("Last name");
    private JLabel userNameLabel = new JLabel("User name");
    private JLabel emailLabel = new JLabel("Email address");
    private JLabel passwordLabel = new JLabel("Password");
    private JLabel contactNoLabel = new JLabel("Contact No.");

    private JTextField firstNameField = new JTextField(8);
    private JTextField lastNameField = new JTextField(8);
    private JTextField userNameField = new JTextField(8);
    private JTextField emailField = new JTextField(8);
    private JPasswordField passwordField = new JPasswordField(8);
    private JTextField contactNoField = new JTextField(8);

    // IMPORTANT! Only fields inside the array are recognized by some functions here
    protected JTextField[] fields = {
        firstNameField,
        lastNameField,
        userNameField,
        emailField,
        passwordField,
        contactNoField
    };

    // Buttons
    private JButton registerButton = new JButton("Register");
    private JButton editButton = new JButton("Edit");
    private JButton cancelButton = new JButton("Cancel");

    public WP_RegForm() {
        super(new GridBagLayout());
        compile();
    }

    public void prepare() {}

    public void prepareComponents() {
        registerButton.setEnabled(false);
        editButton.setEnabled(false);
        cancelButton.setEnabled(false);

        // JTextFields that are required
        JTextField[]  requiredTextFields = {
            firstNameField,
            lastNameField,
            userNameField,
            emailField,
            passwordField
        };

        // Sets document listeners to require several JTextFields to be
        // non-empty for registerButton to enable
        setButtonTriggerOnAllFields(registerButton, requiredTextFields);

        // Set cancelButton enabled on each non-empty JTextField
        setButtonTriggerOnAnyField(cancelButton, fields);

        // Button action listeners
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ArrayList<String> fieldValues = new ArrayList<>();

                // Retrieve each gathered string
                for (JTextField tf : fields) {
                    fieldValues.add(tf.getText());
                }

                // Check existence of data
                try {
                    Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + Main.sqlDb, 
                        "root",
                        ""
                    );

                    // WORK IN PROGRESS =======================================
                    
                    String query = "SELECT * FROM users_tbl";
                    Statement statement = connection.createStatement();
                    statement.executeQuery(query);

                    // WORK IN PROGRESS =======================================

                } catch (Exception e) {}
            }
        });
    }

    public void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        // Header panel
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(headerPanel, gbc);
        
        // Fields panel
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(fieldsPanel, gbc);
        
        // Buttons panel
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(buttonsPanel, gbc);
        
        // Form header
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 1;
        headerPanel.add(formHeader, gbc);


        // LABELS AND FIELDS
        // The following gbc settings are applied to all following components
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 0;
        gbc.weighty = 1;
        
        // First name label
        gbc.gridx = 0;
        gbc.gridy = 0;
        fieldsPanel.add(firstNameLabel, gbc);

        // Last name label
        gbc.gridx = 0;
        gbc.gridy = 1;
        fieldsPanel.add(lastNameLabel, gbc);
        
        // User name label
        gbc.gridx = 0;
        gbc.gridy = 2;
        fieldsPanel.add(userNameLabel, gbc);
        
        // Email label
        gbc.gridx = 0;
        gbc.gridy = 3;
        fieldsPanel.add(emailLabel, gbc);
        
        // Password label
        gbc.gridx = 0;
        gbc.gridy = 4;
        fieldsPanel.add(passwordLabel, gbc);
        
        // Contact No label
        gbc.gridx = 0;
        gbc.gridy = 5;
        fieldsPanel.add(contactNoLabel, gbc);


        gbc.weightx = 1;

        // First name field
        gbc.gridx = 1;
        gbc.gridy = 0;
        fieldsPanel.add(firstNameField, gbc);
        
        // Last name field
        gbc.gridx = 1;
        gbc.gridy = 1;
        fieldsPanel.add(lastNameField, gbc);

        // User name field
        gbc.gridx = 1;
        gbc.gridy = 2;
        fieldsPanel.add(userNameField, gbc);

        // Email field
        gbc.gridx = 1;
        gbc.gridy = 3;
        fieldsPanel.add(emailField, gbc);

        // Password field
        gbc.gridx = 1;
        gbc.gridy = 4;
        fieldsPanel.add(passwordField, gbc);

        // Contact No field
        gbc.gridx = 1;
        gbc.gridy = 5;
        fieldsPanel.add(contactNoField, gbc);


        // BUTTONS        
        // Register button
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonsPanel.add(registerButton, gbc);

        // Edit button
        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonsPanel.add(editButton, gbc);

        // Cancel button
        gbc.gridx = 2;
        gbc.gridy = 0;
        buttonsPanel.add(cancelButton, gbc);
    }

    public void finalizePrepare() {}

    public void compile() {
        prepare();
        prepareComponents();
        addComponents();
        finalizePrepare();
    }

    public void setButtonTriggerOnAnyField(JButton button, JTextField[] tfs) {
        for (JTextField tf : tfs) {
            tf.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent event) {
                    changed();
                }

                public void removeUpdate(DocumentEvent event) {
                    changed();
                }

                public void insertUpdate(DocumentEvent event) {
                    changed();
                }

                public void changed() {
                    if (checkAllTextFields(tfs)) {
                        button.setEnabled(true);
                    } else {
                        button.setEnabled(false);
                    }
                }
            });
        }
    }

    /**
     * Checks if all JTextField in the given array are not empty.
     * Returns {@code}true{@code} when all JTextFields are not empty,
     * otherwise returns {@code}false{@code}.
     * @param tfs the array of JTextFields to check
     * @return {@code}true{@code} if all JTextFields are not empty,
     * {@code}false{@code} if any JTextField is empty
     */
    public void setButtonTriggerOnAllFields(JButton button, JTextField[] tfs) {
        for (JTextField tf : tfs) {
            tf.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent event) {
                    changed();
                }

                public void removeUpdate(DocumentEvent event) {
                    changed();
                }

                public void insertUpdate(DocumentEvent event) {
                    changed();
                }

                public void changed() {
                    if (checkEmptyTextFields(tfs)) {
                        button.setEnabled(false);
                    } else {
                        button.setEnabled(true);
                    }
                }
            });
        }
    }

    /**
     * Checks if any JTextField in the given array is empty.
     * Returns {@code}true{@code} when one or more JTextField is
     * empty, otherwise returns {@code}false{@code}.
     * @param tfs the array of JTextFields to check
     * @return {@code}true{@code} if one or more JTextField is
     * empty, {@code}false{@code} if all JTextFields are not
     * empty
     */
    private boolean checkEmptyTextFields(JTextField[] tfs) {
        for (JTextField textField : tfs) {
            if (textField.getText().equals("")) {
                return true;
            }
        }
        return false;
    }

    private boolean checkAllTextFields(JTextField[] tfs) {
        for (JTextField textField : tfs) {
            if (!textField.getText().equals("")) {
                return true;
            }
        }
        return false;
    }
}
