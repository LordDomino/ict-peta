import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    protected JLabel formHeader = new JLabel("User Registration");

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
    /**The JTextFields recognized by this program */
    protected JTextField[] fields = {
        firstNameField,
        lastNameField,
        userNameField,
        emailField,
        passwordField,
        contactNoField
    };

    protected String[] fieldNames = {
        "first_name",
        "last_name",
        "username",
        "email",
        "password",
        "contact_no"
    };

    // Buttons
    private JButton registerButton = new JButton("Register");
    private JButton editButton = new JButton("Edit");
    private JButton clearButton = new JButton("Clear");

    public WP_RegForm() {
        super(new GridBagLayout());
        compile();
    }

    public void prepare() {}

    public void prepareComponents() {
        registerButton.setEnabled(false);
        editButton.setEnabled(false);
        clearButton.setEnabled(false);

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
        setButtonTriggerOnAnyField(clearButton, fields);

        // Button action listeners
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ArrayList<String> fieldValues = new ArrayList<>();

                // Retrieve each input string from the JTextFields
                for (JTextField tf : fields) {
                    fieldValues.add(tf.getText());
                }

                // Check existence of data
                try {

                    // It is necessary to establish SQL connection first
                    // before requests can be sent
                    Main.establishSQLConnection();

                    // Query setup
                    String query = "SELECT * FROM " + Main.sqlTbl + " WHERE email = \"" + fieldValues.get(3) + "\"";
                    Statement statement = Main.connection.createStatement();

                    // Get the result from the query
                    ResultSet result = statement.executeQuery(query);

                    if (result.getFetchSize() == 0) {
                        // Query setup
                        query = "INSERT INTO " + Main.sqlTbl + " (";

                        for (String field : fieldNames) {
                            query = query + " `" + field + "`,";
                        }

                        query = query.substring(0, query.length()-1) + " ) VALUES (";

                        for (String val : fieldValues) {
                            if (val.equals("")) {
                                query = query + " NULL,";
                            } else {
                                query = query + " '" + val + "',";
                            }
                        }

                        query = query.substring(0, query.length()-1) + " );";

                        // Preview and execute query, if there are no errors
                        System.out.println(query);
                        Statement statement2 = Main.connection.createStatement();
                        statement2.executeUpdate(query);
                        
                        // Add the new results to the table
                        Main.root.tablePanel.updateTable(fieldValues.toArray());
                        clearTextFields();
                    }

                    // IMPORTANT! -- necessary to read the first row
                    //result.next();

                    // Close connection
                    Main.connection.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                        Main.root,
                        "Invalid input/s found; cannot proceed with operation",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                clearTextFields(); // Clear all text fields
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
        buttonsPanel.add(clearButton, gbc);
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

    protected void clearTextFields() {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
}
