package com.example.vetclinic.view;

import com.example.vetclinic.controller.AnimalController;
import com.example.vetclinic.controller.TreatmentController;
import com.example.vetclinic.model.Treatment;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TreatmentTableModel extends GenericTableModel {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final TreatmentController treatmentController;

    public TreatmentTableModel(TreatmentController treatmentController, List rows) {
        super(rows, new String[]{"Nome", "Animal", "Data Início", "Data Fim"});
        this.treatmentController = treatmentController;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Treatment treatment = (Treatment) getItem(rowIndex);
        String[] cols = new String[]{
            treatment.getName(),
            treatment.getAnimal().getName(),
            dateFormat.format(treatment.getDateStart()),
            treatment.getDateEnd() == null ? "" : dateFormat.format(treatment.getDateEnd())
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
                return String.class;
            }
            default ->
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Treatment treatment = (Treatment) getItem(rowIndex);
        Treatment newTreatment = new Treatment(treatment);

        try {
            switch (columnIndex) {
                case 0 ->
                    newTreatment.setName((String) value);
                case 2 ->
                    newTreatment.setDateStart(dateFormat.parse((String) value));
                case 3 ->
                    newTreatment.setDateEnd(dateFormat.parse((String) value));
            }
        } catch (ParseException e) {
        }

        setItem(newTreatment, rowIndex);
        treatmentController.update(newTreatment);
    }

    public void setDateEnd(Date date, int rowIndex) {
        setValueAt(dateFormat.format(date), rowIndex, 3);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 0; // can edit name only
    }
}
