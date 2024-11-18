package com.example.vetclinic;

import com.example.vetclinic.controller.Controller;
import com.example.vetclinic.view.MainForm;

public class VetClinic {

    public static void main(String[] args) {
        MainForm.launch(Controller.getInstance());
    }
}
