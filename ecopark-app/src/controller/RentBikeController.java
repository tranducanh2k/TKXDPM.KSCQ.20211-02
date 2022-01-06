package controller;

import entity.Bike;
import entity.BikeDB;
import entity.BikeRental;
import entity.User;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentBikeController {

    	public List<Bike> getBikeFromDock(String dockName) throws SQLException {
            Statement stm = BikeDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("select * from Bike where dockName = '"+dockName+"'");
            List<Bike> list = new ArrayList<>();
            while (res.next()) {
                Bike bike = new Bike(res.getInt("bikeID"),
                                    res.getString("dockName"),
                                    res.getString("bikeType"),
                                    res.getString("licensePlate"),
                                    res.getString("battery"));
                list.add(bike);
            }

            BikeDB.getConnection().close();

            return list;
        }

        public void addRental(BikeRental bikeRental) throws SQLException {
            Connection connection = BikeDB.getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into BikeRental (userID, bikeID, startRental, timeRented, batteryStatus, bikeIsReturned) "
                                                + "values ("+bikeRental.getUserID()+","+bikeRental.getBikeID()+","+bikeRental.getStartRental()
                                                +","+bikeRental.getTimeRented()+","+bikeRental.getBatteryStatus()+","+bikeRental.isBikeIsReturned()+")");
            pst.executeUpdate();
            connection.close();
            pst.close();
        }

        public void updateRental(BikeRental bikeRental) throws SQLException {
            Connection conn = BikeDB.getConnection();
            PreparedStatement pst = conn.prepareStatement("Update BikeRental set timeRented = "+bikeRental.getTimeRented()
                                                            +" where rentalID = "+bikeRental.getRentalID());
            pst.executeUpdate();
            conn.close();
            pst.close();
        }

        public int findRental(BikeRental bikeRental) throws SQLException {
            Statement stm = BikeDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("select rentalID from BikeRental where userID = "+bikeRental.getUserID()
                                            +" and bikeID ="+bikeRental.getBikeID()+" and startRental ="+bikeRental.getStartRental());
            return res.getInt("rentalID");
        }
}
