package com.example.vetclinic.view;

import com.example.vetclinic.controller.VetController;
import com.example.vetclinic.model.Vet;
import java.util.List;

public class VetTableModel extends GenericTableModel {

    private VetController controller;

    public VetTableModel(VetController controller, List rows) {
        super(rows, new String[]{"Nome", "Endereço", "CEP", "Telefone", "Email"});
        this.controller = controller;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vet vet = (Vet) getItem(rowIndex);
        String[] cols = new String[]{
            vet.getName(),
            vet.getAddress(),
            vet.getCep(),
            vet.getTel(),
            vet.getEmail()
        };
        return cols[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex > 4) {
            throw new IndexOutOfBoundsException(columnIndex);
        }
        return String.class;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Vet vet = (Vet) getItem(rowIndex);
        Vet newCli = new Vet(vet);

        switch (columnIndex) {
            case 0 ->
                newCli.setName((String) value);
            case 1 ->
                newCli.setAddress((String) value);
            case 2 ->
                newCli.setCep((String) value);
            case 3 ->
                newCli.setTel((String) value);
            case 4 ->
                newCli.setEmail((String) value);
            default ->
                throw new IndexOutOfBoundsException(columnIndex);
        }

        setItem(newCli, rowIndex);
        controller.update(newCli);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }
}
