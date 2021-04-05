/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class UserDAO {
    private static final Logger LOG = Logger.getLogger(UserDAO.class);
    public UserDTO checkLogin(String userID, String password) throws ClassNotFoundException, SQLException {
        UserDTO result = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "select name, roleID, status FROM tblUsers WHERE userID =? AND password =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userID);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String role = rs.getString("roleID");
                boolean status = rs.getBoolean("status");
                result = new UserDTO(userID, password, name, role, status);
            }
        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public void create(UserDTO dto) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getCon();
            String sql = "INSERT INTO tblUsers (userID, name, password, roleID, status) VALUES(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getEmail());
            ps.setString(2, dto.getName());
            ps.setString(3, dto.getPassword());
            ps.setString(4, dto.getRole());
            ps.setBoolean(5, dto.isStatus());
            ps.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    public boolean checkID(String userID) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            if (conn != null) {
                String sql = "Select userID FROM tblUsers WHERE userID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    return false;
                }
            }
        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return true;
    }
}
