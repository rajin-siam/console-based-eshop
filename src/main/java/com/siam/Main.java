package com.siam;

import com.siam.menu.Menu;
import com.siam.menu.impl.MainMenu;

public class Main {

    public static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        Menu mainMenu =  new MainMenu();
        mainMenu.start();

    }

}
