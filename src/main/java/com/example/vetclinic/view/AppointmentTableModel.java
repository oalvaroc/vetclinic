package com.example.vetclinic.view;

import com.example.vetclinic.model.Appointment;
import java.text.SimpleDateFormat;
import java.util.List;

public class AppointmentTableModel extends GenericTableModel {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public AppointmentTableModel(List rows) {
        super(rows, new String[]{"Veterinário", "Data"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        System.out.println(getItem(rowIndex));
        Appointment appointment = (Appointment) getItem(rowIndex);
        String[] cols = new String[]{
            appointment.getVet().getName(),
            dateFormat.format(appointment.getDate())
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
            default ->
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
