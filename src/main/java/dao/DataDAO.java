package dao;

import database.MyConnection;
import model.Data;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class DataDAO{
    public static List<Data> getAllFiles(String email) throws SQLException {
    Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("Select * from Date where email = ?");
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        List<Data> files = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String path = rs.getString(3);
            files.add(new Data(id,name,path));

        }
        return files;
    }

     public static int hideFile(Data file)throws SQLException, IOException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into Data(id,name,path,email,bin_data) values(?,?,?,?)");
        ps.setString(1,file.getFileName());
        ps.setString(2,file.getPath());
        ps.setString(3,file.getEmail());
        File f = new File(file.getPath());
         FileReader fr = new FileReader(f);
         ps.setCharacterStream(4,fr,f.length());
         int ans = ps.executeUpdate();
         fr.close();
         f.delete();
         return ans;
     }
     public static void unhide(int id) throws SQLException,IOException{
         Connection connection = MyConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement("Select path, binary data where = ? ");
         ps.setInt(1,id);
         ResultSet rs = ps.executeQuery();
         rs.next();
         String path = rs.getString("path");
         Clob clob = rs.getClob("bin_data");

         Reader r = clob.getCharacterStream();
         FileWriter fw = new FileWriter(path);
         int i;
         while ((i=r.read())!=-1){
             fw.write((char)i);
         }
         fw.close();
         ps = connection.prepareStatement("Delete from data where id = ?");
         ps.setInt(1,id);
         ps.executeUpdate();
         System.out.println("Successfully unhidden");
     }
}
