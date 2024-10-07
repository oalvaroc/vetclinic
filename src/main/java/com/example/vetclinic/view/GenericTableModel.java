package com.example.vetclinic.view;

/**
 * Adaptado de Prof. Dr. Plinio Vilela <prvilela@unicamp.br>
 */
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public abstract class GenericTableModel extends AbstractTableModel {

    private ArrayList<Object> rows;
    private String[] columns;

    public GenericTableModel(List rows, String[] columns) {
        this.rows = (ArrayList<Object>) rows;
        this.columns = columns;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int index) {
        return columns[index];
    }

    public Object getItem(int index) {
        if (index < 0) {
            return null;
        }
        return rows.get(index);
    }

    public void addItem(Object obj) {
        rows.add(obj);
        int lastIndex = getRowCount() - 1;
        fireTableRowsInserted(lastIndex, lastIndex);
    }

    public void setItem(Object obj, int index) {
        rows.set(index, obj);
        fireTableRowsUpdated(index, index);
    }

    public void removeItem(int index) {
        rows.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void addListOfItems(List<Object> items) {
        this.clear();
        for (Object obj : items) {
            this.addItem(obj);
        }
    }

    public void clear() {
        rows.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return rows.isEmpty();
    }

    public void setColumnWidth(JTable myTable, int[] widths) {
        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < widths.length; i++) {
            TableColumn col = myTable.getColumnModel().getColumn(i);
            col.setPreferredWidth(widths[i]);
        }
    }

    public void selectAndScroll(JTable table, int rowIndex) {
        table.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);
        scrollToVisible(table, rowIndex);
    }

    public void scrollToVisible(JTable table, int rowIndex) {
        scrollToVisible(table, rowIndex, 0);
    }

    public void scrollToVisible(JTable table, int rowIndex, int vColIndex) {
        if (!(table.getParent() instanceof JViewport)) {
            return;
        }
        this.setViewPortPosition((JViewport) table.getParent(), table.getCellRect(rowIndex, vColIndex, true));
    }

    private void setViewPortPosition(JViewport viewport, Rectangle position) {
        Point pt = viewport.getViewPosition();
        position.setLocation(position.x - pt.x, position.y - pt.y);
        viewport.scrollRectToVisible(position);
    }

}
