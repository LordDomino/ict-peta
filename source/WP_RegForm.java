import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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

    // Buttons
    private JButton registerButton = new JButton("Register");
    private JButton editButton = new JButton("Edit");
    private JButton cancelButton = new JButton("Cancel");

    public WP_RegForm() {
        super(new GridBagLayout());
        compile();
    }

    public void prepare() {}

    public void prepareComponents() {}

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
}
