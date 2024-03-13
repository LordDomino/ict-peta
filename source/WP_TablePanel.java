import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;
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
    protected DefaultTableModel tableMd = new DefaultTableModel(columnNames, 0) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    protected JTable table = new JTable(tableMd);
    private JScrollPane tableArea = new JScrollPane(table);

    // Ribbon components
    protected JButton editButton = new JButton("Edit");

    public WP_TablePanel() {
        super(new GridBagLayout());
        compile();
    }

    public void prepare() {}

    public void prepareComponents() {
        table.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // enable table editing
                    tableMd = new DefaultTableModel(columnNames, 0) {
                        public boolean isCellEditable(int row, int column) {
                            return false;  // SET TO TRUE FOR FUNCTIONALITY
                        }
                    };

                    if (tableMd.isCellEditable(0, 0)) {
                        int row = table.getSelectedRow();
                        int column = table.getSelectedColumn();

                        table.setModel(tableMd);

                        try {
                            loadFromDatabase();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        System.out.println("Row " + row);
                        System.out.println("Column " + column);
                        
                        table.changeSelection(row, column, false, false);
                        table.editCellAt(row, column);
                        table.transferFocus();
                    }
                }
            }
        });
    }

    public void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        // Ribbon panel
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 20;
        gbc.weightx = 1;
        add(ribbon, gbc);

        // Table area
        gbc.weighty = 1;
        gbc.gridy = 1;
        add(tableArea, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.weightx = 1;
        ribbon.add(editButton, gbc);
    }

    public void finalizePrepare() {}

    public void compile() {
        prepare();
        prepareComponents();
        addComponents();
        finalizePrepare();
    }

    protected void updateTable(Object[] values) throws Exception {
        loadFromDatabase();
    }

    protected void loadFromDatabase() throws Exception {
        // Clear the rows
        tableMd.setRowCount(0);

        try {
            Main.establishSQLConnection();
            
            String query = "SELECT * FROM " + Main.sqlTbl;
            Statement statement = Main.connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
            ResultSet result = statement.executeQuery(query);
            
            result.last();
            int n = result.getRow();
            result.beforeFirst();

            int i = 0;
            while (i < n) {
                result.next();

                ArrayList<Object> dataFromSQL = new ArrayList<>();

                for (String field : Main.root.formPanel.fieldNames) {
                    dataFromSQL.add(result.getObject(field));
                }

                tableMd.addRow(dataFromSQL.toArray());
                i++;
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
