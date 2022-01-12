package controller;

import entity.Bike;
import entity.BikeDB;
import entity.Dock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ViewDockController {
    public List<Dock> getDocks() throws SQLException {
        Statement stm = BikeDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery("select * from Dock");
        List<Dock> list = new ArrayList<>();
        while (res.next()) {
            Dock dock = new Dock(res.getString("dockName"),
                    res.getString("location"),
                    res.getString("area"),
                    res.getInt("numberOfBike"),
                    res.getString("image"));
            list.add(dock);
        }

        return list;
    }
}
