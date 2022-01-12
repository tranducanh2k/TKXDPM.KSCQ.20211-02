package controller;

import entity.Bike;
import entity.BikeDB;
import entity.BikeRental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentBikeController {

        private BikeDB bikeDB = new BikeDB();

    	public List<Bike> getBikeFromDock(String dockName) throws SQLException {
            Statement stm = bikeDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("select * from Bike where dockName = '"+dockName+"'");
            List<Bike> list = new ArrayList<>();
            while (res.next()) {
                Bike bike = new Bike(res.getInt("bikeID"),
                                    res.getString("dockName"),
                                    res.getString("bikeType"),
                                    res.getString("licensePlate"),
                                    res.getString("battery"),
                                    res.getString("image"));
                list.add(bike);
            }

            return list;
        }

        public void addRental(BikeRental bikeRental) {
            Connection connection = bikeDB.getConnection();
            String sql = "Insert into BikeRental(userID, bikeID, startRental, timeRented, batteryStatus, bikeIsReturned) Values (?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = null;
            try {
                pst = connection.prepareStatement(sql);
                pst.setInt(1, bikeRental.getUserID());
                pst.setInt(2, bikeRental.getBikeID());
                pst.setDate(3, new java.sql.Date(bikeRental.getStartRental().getTime()));
                pst.setInt(4, bikeRental.getTimeRented());
                pst.setString(5, bikeRental.getBatteryStatus());
                pst.setBoolean(6, bikeRental.isBikeIsReturned());

                pst.executeUpdate();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public int findRental(BikeRental bikeRental) throws SQLException {
            Statement stm = BikeDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("select rentalID from BikeRental where userID = "+bikeRental.getUserID()
                                            +" and bikeID ="+bikeRental.getBikeID()+" and startRental ="+bikeRental.getStartRental());
            return res.getInt("rentalID");
        }
}
