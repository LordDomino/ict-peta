import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WP_TablePanel extends JPanel implements Customizable {

    // Layout components
    private JPanel ribbon = new JPanel(new GridBagLayout());
    
    // Table related components
    public String[] columnNames = {
        "First name",
        "Last name",
        "User name",
        "Email",
        "Password",
        "Contact No."
    };
    public DefaultTableModel tableMd = new DefaultTableModel(columnNames, 1) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    public JTable table = new JTable(tableMd);
    private JScrollPane tableArea = new JScrollPane(table);

    public WP_TablePanel() {
        super(new GridBagLayout());
        compile();
    }

    public void prepare() {}

    public void prepareComponents() {}

    public void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        // Ribbon panel
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(ribbon, gbc);

        // Table area
        gbc.weighty = 1;
        gbc.gridy = 1;
        add(tableArea, gbc);
    }

    public void finalizePrepare() {}

    public void compile() {
        prepare();
        prepareComponents();
        addComponents();
        finalizePrepare();
    }
}
