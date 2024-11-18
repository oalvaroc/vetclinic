package com.example.vetclinic.view;

import com.example.vetclinic.controller.ProductController;
import com.example.vetclinic.model.Product;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class ProductTableModel extends GenericTableModel {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private ProductController controller;

    public ProductTableModel(ProductController controller, List rows) {
        super(rows, new String[]{"Nome", "Data Adicionado", "Data Validade", "Qtd."});
        this.controller = controller;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = (Product) getItem(rowIndex);
        String[] cols = new String[]{
            product.getName(),
            product.getDateEntry() == null ? "" : dateFormat.format(product.getDateEntry()),
            product.getDateExpiration() == null ? "" : dateFormat.format(product.getDateExpiration()),
            Integer.toString(product.getCount())
        };
        return cols[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return String.class;
            }
            case 1 -> {
                return String.class;
            }
            case 2 -> {
                return String.class;
            }
            case 3 -> {
                return Integer.class;
            }
            default ->
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Product product = (Product) getItem(rowIndex);
        Product newProd = new Product(product);

        try {
            switch (columnIndex) {
                case 0 ->
                    newProd.setName((String) value);
                case 1 ->
                    newProd.setDateEntry(dateFormat.parse((String) value));
                case 2 ->
                    newProd.setDateExpiration(dateFormat.parse((String) value));
                case 3 -> {
                    if ((Integer) value >= 0) {
                        newProd.setCount((Integer) value);
                    }
                }
            }
        } catch (ParseException e) {
        }

        setItem(newProd, rowIndex);
        controller.update(newProd);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }
}
