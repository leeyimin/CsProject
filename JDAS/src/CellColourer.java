
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author irham rasyidi
 */
public class CellColourer extends DefaultTableCellRenderer{
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

            if( Arrays.binarySearch( discrepancies.toArray(), row ) >= 0 ){
                if( isSelected ){
                    c.setBackground( Color.RED );
                }
                else{
                    if( row%2 == 0 )
                        c.setBackground( Color.YELLOW );
                    else
                        c.setBackground( new Color( 255, 255, 130 ) );
                }
            }
            else{
                if( isSelected )
                    c.setBackground( new Color( 57, 105, 138 ) );
                else{
                    if( row%2 == 0 )
                        c.setBackground( Color.WHITE );
                    else
                        c.setBackground( new Color( 242, 242, 242 ) );
                }
            }
                
            return c;
        }
    }