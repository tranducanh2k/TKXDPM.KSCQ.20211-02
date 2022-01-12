package controller;

import entity.Bike;
import entity.BikeDB;
import entity.Dock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReturnBikeController {
    /**
     * update ten bai xe cua xe va so xe trong bai tra xe
     * @param dock
     * @param bike
     * @throws SQLException
     */
    public void updateDockBike(Dock dock, Bike bike) throws SQLException {
        Connection conn = BikeDB.getConnection();

        //update so xe trong bai xe
        String sql2 = "update Dock set numberOfBike = numberOfBike-1 where dockName = ?";
        PreparedStatement pst2 = conn.prepareStatement(sql2);
        pst2.setString(1, bike.getDockName());
        pst2.executeUpdate();
        pst2.close();

        String sql3 = "update Dock set numberOfBike = numberOfBike+1 where dockName = ?";
        PreparedStatement pst3 = conn.prepareStatement(sql3);
        pst3.setString(1, dock.getDockName());
        pst3.executeUpdate();
        pst3.close();


        //update ten bai xe cua xe duoc tra
        String sql = "update Bike set dockName = ? where bikeID = ?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, dock.getDockName());
        pst.setInt(2, bike.getBikeID());

        pst.executeUpdate();
        pst.close();
    }

}
