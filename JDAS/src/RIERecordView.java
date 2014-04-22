
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author michelle
 */
public class RIERecordView extends javax.swing.JFrame {

    /**
     * Creates new form RIERecordView
     */
    TableModel rsData;
    
    Controller controller;
    public RIERecordView(Controller c) {
        initComponents();
        controller = c;
        try {
            rsData = buildTableModel(c.getResultSet("select * from rierecords"));
        } catch (SQLException ex) {
            Logger.getLogger(RIERecordView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static TableModel buildTableModel(ResultSet rs) throws SQLException {  
        String[] s = null;  
        ArrayList<Object> l = new ArrayList<Object>();  
        Object[][] data1 = null;  
  
        ResultSetMetaData metaData = rs.getMetaData();  
  
        // names of columns  
        // List<String> columnNamesList = new ArrayList<String>();  
  
        // int columnCount = columnNames.size();  
        int columnCount = metaData.getColumnCount();  
        for (int column = 0; column <= columnCount; column++) {  
            s = new String[columnCount];  
            s[column] = metaData.getColumnName(column);  
            // columnNames.add(metaData.getColumnName(column));  
  
        }  
  
        // data of the table  
        // Vector<Vector<Object>> data = new Vector<Vector<Object>>();  
        while (rs.next()) {  
            // Vector<Object> vector = new Vector<Object>();  
  
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {  
                l.add(rs.getObject(columnIndex));  
  
                // vector.add(rs.getObject(columnIndex));  
  
                // values.toArray(new Object[][] {})  
            }  
            data1 = l.toArray(new Object[][] {});  
            // data.add(vector);  
        }  
  
        DefaultTableModel t_model = new DefaultTableModel(data1, s);  
        return t_model; 
  
    }  
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(rsData);
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
