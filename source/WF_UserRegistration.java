import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WF_UserRegistration extends JFrame implements Customizable {

    // Layout components
    private JPanel formPanel = new WP_RegForm();
    private JPanel tablePanel = new WP_TablePanel();

    // GUI components
    

    protected WF_UserRegistration() {
        super("User Registration Records");
        compile();
    }

    public void prepare() {
        setLayout(new GridBagLayout());
    }

    public void prepareComponents() {
    }

    public void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        // Form panel
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 1;
        add(formPanel, gbc);
        
        // Table panel
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(tablePanel, gbc);
    }

    public void finalizePrepare() {
        setLocationRelativeTo(null);
        pack();
        setMinimumSize(getSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    protected void compile() {
        prepare();
        prepareComponents();
        addComponents();
        finalizePrepare();
    }
}
