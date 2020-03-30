package com.example.mvpproject.View;

import java.util.ArrayList;

public interface HomeView {

    void showTable(ArrayList<String[]> rows, boolean user);

    void showMessageError();
}
