/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ViewRIERecords.java
 *
 * Created on Apr 22, 2014, 2:44:03 PM
 */

import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;


/**
 *
 * @author irham rasyidi
 */
public class ViewRIERecords extends javax.swing.JFrame implements TableModelListener {

    Controller cont;
    ArrayList<String> categories;
    DefaultTableColumnModel dtcm ;
    /** Creates new form ViewRIERecords */
    public ViewRIERecords( Controller cont ) throws SQLException {
        
        this.cont = cont;

        ResultSet rs = cont.getResultSet("select distinct category from rierecords;"); 
        int catCount = 0;
        
        categories = new ArrayList<String>(catCount+1);
        categories.add("All");
        
        while (rs.next()) {
            categories.add(rs.getString(1));
        }
 
        initComponents();
        //jTable1.setDefaultRenderer( Object.class, new CellColourer() );
        
        rs = cont.getResultSet("select id, userid, category, title, desc1, desc2, award, year, score from rierecords;"); 
        updateTable(rs);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View RIE Records");

        jLabel1.setText("Category:");

        jComboBox1.setModel(new DefaultComboBoxModel(categories.toArray()));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Select a Category"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Delete selected entries");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Done");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, 0, 661, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int index = jComboBox1.getSelectedIndex();
        String[] queries = new String[]{
            "select id, userid, category, title, desc1, desc2, award, year, score from rierecords;", 
            "SELECT ID, USERID, DESC1, YEAR FROM RIERECORDS WHERE CATEGORY = 14;",
            "SELECT ID, USERID, DESC1, YEAR FROM RIERECORDS WHERE CATEGORY = 15;",
            "SELECT ID, USERID, DESC1, AWARD, YEAR FROM RIERECORDS WHERE CATEGORY = 16;",
            "SELECT ID, USERID, DESC1, YEAR FROM RIERECORDS WHERE CATEGORY = 17;",
            "SELECT ID, USERID, TITLE, DESC1, YEAR FROM RIERECORDS WHERE CATEGORY = 18 OR CATEGORY = 19;",
            "SELECT ID, USERID, DESC1, YEAR FROM RIERECORDS WHERE CATEGORY = 20;"
        };
        String query = "SELECT ID, USERID, TITLE, DESC1, DESC2, AWARD, YEAR, SCORE FROM RIERECORDS WHERE ";
        //ID is also taken just to make things easier for discrepancy checks.
        //I'm not going to use the ResultSet, and stick with only the Table's content.
        //TODO Change the Integer values to Strings
/*
        if( index == 0 )
            updateTable( cont.getResultSet( "SELECT ID, USERID, CATEGORY, TITLE, DESC1, DESC2, AWARD, YEAR, SCORE FROM RIERECORDS;" ) );
        else
            updateTable( query + jComboBox1.gety)*/
        updateTable( cont.getResultSet( queries[index] ) );
        if( index == 5 ) checkForDiscrepancies();

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int[] selected = jTable1.getSelectedRows();
        for (int i: selected){
            i = Integer.parseInt((String) jTable1.getModel().getValueAt(i, 0));
            
            cont.deleteRecord(cont.RIE, i);
        }
        
        //update table
        jComboBox1ActionPerformed(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    public void checkForDiscrepancies(){
        int id = -1, title = -1, desc = -1;
        int column = jTable1.getColumnCount(), row = jTable1.getRowCount();
        for( int a = 0; a < column; a++ ){
            if( jTable1.getColumnName(a).equals( "title" ) )
                title = a;
            if( jTable1.getColumnName(a).equals( "id" ) )
                id = a;
            if( jTable1.getColumnName(a).equals( "desc1" ) )
                desc = a;
        }

        if( title == -1 || id == -1 || desc == -1 ) return;
        //Perhaps an exception

        ArrayList< String[] > records = new ArrayList< String[] >();
        String[] nextRecord = new String[4];

        for( int a = 0; a < row; a++ ){
            nextRecord[0] = (String) jTable1.getModel().getValueAt( a, id );
            nextRecord[1] = (String) jTable1.getModel().getValueAt( a, title );
            nextRecord[2] = (String) jTable1.getModel().getValueAt( a, desc );
            nextRecord[3] = Integer.toString( a );
            records.add( nextRecord );
        }

        Collections.sort( records, new Comparator<String[]>(){
            public int compare( String[] a, String[] b ){
                return a[1].compareTo( b[1] );
            }
        });


        ArrayList<Integer> toColour = new ArrayList<Integer>();
        int reference, end;
        boolean disc;
        for( int a = 0; a < row; a++ ){
            reference = a;
            disc = false;

            for( end = a+1; end < row && records.get( reference )[1].equals( records.get( end )[1] ); end++ ){
                if( disc ) continue;
                if( !records.get( reference )[2].equals( records.get( end )[2] ) )
                    disc = true;
            }

            if( disc ){
                for( int i = reference; i < end; i++ )
                    toColour.add( Integer.parseInt( records.get(i)[3] ) );
            }

            a = end;
        }

        Collections.sort( toColour );
        ( (CellColourer) jTable1.getDefaultRenderer( Object.class ) ).setDiscrepancies( toColour );
        jTable1.repaint();
       
    }

    private void updateTable( ResultSet resultSet ){
        //( (CellColourer) jTable1.getDefaultRenderer( Object.class ) ).clearDiscrepancies();
        try{
            DefaultTableModel dtm = new DefaultTableModel(){
                @Override
                public boolean isCellEditable( int row, int column ){
                    if (column <3) return false;
                    else return true;
                }
            };

            Vector<Object> nextRow = new Vector<Object>();

            int colCount = resultSet.getMetaData().getColumnCount();
            
            dtm.setColumnCount(colCount);
            
            while( resultSet.next() ) { 
                for( int a = 1; a <= colCount; a++ ) {
                    nextRow.add(resultSet.getString(a));
                }

                dtm.addRow( nextRow.toArray() );
                nextRow.clear();
            } 

            dtm.addTableModelListener(this);
            jTable1.setModel( dtm );
            dtcm = (DefaultTableColumnModel) jTable1.getColumnModel();

            for( int a = 0; a < resultSet.getMetaData().getColumnCount(); a++ ){
                dtcm.getColumn(a).setHeaderValue( resultSet.getMetaData().getColumnName(a+1) );
            }
        }
        catch( SQLException exp ){
            System.out.println(exp);
        }
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private class CellColourer extends DefaultTableCellRenderer{
        ArrayList<Integer> discrepancies;

        public CellColourer(){
            discrepancies = new ArrayList<Integer>();
        }

        public void setDiscrepancies( ArrayList<Integer> toColour ){
            discrepancies = toColour;
        }

        public void clearDiscrepancies(){
            discrepancies.clear();
        }

        @Override
        public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col ){
            Component c = super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, col );

            if( Arrays.binarySearch( discrepancies.toArray(), row ) >= 0 )
                c.setBackground( Color.YELLOW );
            else
                c.setBackground( table.getBackground() );

            return c;
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println("Table changed.");
        
        int row = e.getFirstRow();
        int col = e.getColumn(); //TODO fix year bug
        
        //String colName = jTable1.getModel().getColumnName(col);
        String colName = (String) dtcm.getColumn(col).getHeaderValue(); 
        //TODO this is sort of a hack bc column name may not be the table name. cahnge later
        String newValue = (String) jTable1.getModel().getValueAt(row, col);
        //TODO SANITIZE INPUT
        System.out.println("New value = " + newValue);
        
        int id = Integer.parseInt((String) jTable1.getModel().getValueAt(row, 0)); //hardcoding...
        
        cont.updateIndividualRecord(Controller.RIE, id, colName, newValue);
    }

}
