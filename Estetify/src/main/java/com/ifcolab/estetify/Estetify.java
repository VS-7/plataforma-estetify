package com.ifcolab.estetify;

import com.ifcolab.estetify.controller.AdminController;
import com.ifcolab.estetify.view.FrLogin;

public class Estetify {

    public static void main(String[] args) {
       FrLogin tela = new FrLogin();
       tela.setVisible(true);

        AdminController adminController = new AdminController();
        adminController.criarAdminPadrao();
    }
}
