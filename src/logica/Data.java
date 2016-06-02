/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.Appointment;
import modelos.Paciente;
import modelos.Usuario;

/**
 *
 * @author Developer
 */
public class Data {
    private Connection con;
	
	public Data(){
		performConnection();
	}
	 

	public void performConnection() {
		String host = "us-cdbr-azure-central-a.cloudapp.net";
		String user = "b6f0037c8e8a43";
		String pass = "790b591b";
		String dtbs = "as_8e32428410fc117";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String newConnectionURL = "jdbc:mysql://" + host + "/" + dtbs
					+ "?" + "user=" + user + "&password=" + pass;
					con = (Connection) DriverManager.getConnection(newConnectionURL);
		}catch (Exception e) {
			System.out.println("Error en l'obertura de la connexió.");
		}
		
	}
	
	public void closeConnection() {
		try {
			con.close();
		}catch (Exception e) {
			System.out.println("Error al cerrar la conexión.");
		}
	}
        public int login(String usuario,String password){
              int encontrado=0;
        try {
            Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery("SELECT * FROM user WHERE username='"+usuario+"' AND password ='"+password+"'");             
                rs.last();
                encontrado=rs.getRow();
                System.out.println(String.valueOf(encontrado));                 
                rs.close();
                st.close();
                
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encontrado;
        }
        
        public Usuario GetUsuarios( String username) throws SQLException{
		 Usuario user=new Usuario();
		String selec="SELECT * FROM user where username= ?";
		PreparedStatement ps = con.prepareStatement(selec);
                ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
                        user.id=rs.getInt(1);
			user.userName=rs.getString("username");
                        user.password=rs.getString("password");
                        user.email=rs.getString("email");
		}
		rs.close();
		return user;
	}
        
        public void NuevoUsuario(Usuario usuario) throws SQLException {
		String seleccio = "INSERT INTO `user` (`username` ,`password` ,`email`)" +
				"VALUES (?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setString(1, usuario.userName);
		ps.setString(2, usuario.password);
		ps.setString(3, usuario.email);
		ps.executeUpdate();
        
        }
        public void ActualizarUsuario(Usuario usuario) throws SQLException {
		String seleccio = "update `user` set `username`= ? ,`password`= ? ,`email`= ? where id= ?";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setString(1, usuario.userName);
		ps.setString(2, usuario.password);
		ps.setString(3, usuario.email);
		ps.setInt(4, usuario.id);
                ps.executeUpdate();
        
        }
        public void EliminarUsuario(int ID) throws SQLException {
		String seleccio = "DELETE FROM `user` WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setInt(1, ID);
		ps.executeUpdate();
	}
        public void NuevoPaciente(Paciente paciente) throws SQLException {
		String seleccio = "INSERT INTO `patent` (`id`,`name` ,`lastname` "+
                        ",`birthdate`,`age`,`gender`,`address`,`phone`,`email`,"+
                        "`bloodtype`,`state`)" +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
                ps.setString(1, paciente.id);
		ps.setString(2, paciente.name);
		ps.setString(3, paciente.lastname);
		ps.setString(4, paciente.birthdate);
                ps.setString(5, paciente.age);
                ps.setString(6, paciente.gender);
                ps.setString(7, paciente.address);
                ps.setString(8, paciente.phone);
                ps.setString(9, paciente.email);
                ps.setString(10, paciente.bloodtype);
                ps.setString(11, paciente.state);
		ps.executeUpdate();
                ps.close();
        
        }
        public void ActualizarPaciente(Paciente paciente) throws SQLException {
		String seleccio = "UPDATE `patent` set `name`= ? ,`lastname`= ?, `birthdate`= ?,`age`= ?,`gender`= ?,`address`= ?,`phone` = ?,`email` = ?,`bloodtype`= ?,`state` = ? WHERE `id`= ?";
		PreparedStatement ps = con.prepareStatement(seleccio);                
		ps.setString(1, paciente.name);
		ps.setString(2, paciente.lastname);
		ps.setString(3, paciente.birthdate);
                ps.setString(4, paciente.age);
                ps.setString(5, paciente.gender);
                ps.setString(6, paciente.address);
                ps.setString(7, paciente.phone);
                ps.setString(8, paciente.email);
                ps.setString(9, paciente.bloodtype);
                ps.setString(10, paciente.state);
                ps.setString(11, paciente.id);
		ps.executeUpdate();
                ps.close();
        
        }
        
        public Paciente GetPaciente(String ID) throws SQLException{
        Paciente paciente=new Paciente(); 
        String select="SELECT * FROM patent where id= ?";
          PreparedStatement ps = con.prepareStatement(select);
          ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();      		 
		while(rs.next()){
			//ls.add(rs.getString("username"));
                       paciente.id=rs.getString("id");
                        paciente.name=rs.getString("name");
                        paciente.lastname=rs.getString("lastname");
                        paciente.birthdate=rs.getString("birthdate");
                        paciente.age=rs.getString("age");
                        paciente.gender=rs.getString("gender");
                        paciente.address=rs.getString("address");
                        paciente.phone=rs.getString("phone");
                        paciente.email=rs.getString("email");
                        paciente.bloodtype=rs.getString("bloodtype");
                        paciente.state=rs.getString("state"); 
		}
		rs.close();
          
        return paciente;
        }
        public ResultSet GetPacientes() throws SQLException{
        Paciente paciente=new Paciente(); 
        String select="SELECT * FROM patent";
          PreparedStatement ps = con.prepareStatement(select);         
		ResultSet rs = ps.executeQuery();    		 
		 
		 
          
        return rs;
        }
        public void EliminarPaciente(String ID) throws SQLException {
		String seleccio = "DELETE FROM `patent` WHERE `id` = ?";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setString(1, ID);
		ps.executeUpdate();
	}
        
         public ResultSet GetCitas(String id) throws SQLException{
        Paciente paciente=new Paciente(); 
        String select="SELECT * FROM appointment where idpaciente="+id;
          PreparedStatement ps = con.prepareStatement(select);         
		ResultSet rs = ps.executeQuery();  
        return rs;
        }
         public void NuevaCita(Appointment cita) throws SQLException {
		String seleccio = "insert into appointment (`id`,`idpaciente`,`date`,`time`,`matter`,`state`) values(?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setString(1, cita.id);
		ps.setString(2, cita.idpaciente);
		ps.setString(3, cita.date);
                ps.setString(4, cita.time);
                ps.setString(5, cita.matter);
                ps.setString(6, cita.state);
		ps.executeUpdate();
        
        }
          public void ActualizarCita(Appointment cita) throws SQLException {
		String seleccio = "update  appointment set `date`=?,`time`=?,`matter`=?  where `id`=?";
		PreparedStatement ps = con.prepareStatement(seleccio);
		 
		ps.setString(1, cita.date);
                ps.setString(2, cita.time);
                ps.setString(3, cita.matter);
                ps.setString(4, cita.id);
		ps.executeUpdate();        
        }
        public void CancelarCita(String cita) throws SQLException {
		String seleccio = "update  appointment set `state`=?  where `id`=?";
		PreparedStatement ps = con.prepareStatement(seleccio);
                ps.setString(1, "Cancelada");
		ps.setString(2, cita);
		ps.executeUpdate();        
        }  
          
        public Appointment GetCita(String ID) throws SQLException{
        Appointment cita=new Appointment(); 
        String select="SELECT * FROM appointment where id= ?";
          PreparedStatement ps = con.prepareStatement(select);
          ps.setString(1, ID);
		ResultSet rs = ps.executeQuery();      		 
		while(rs.next()){
			//ls.add(rs.getString("username"));
                       cita.id=rs.getString("id");
                       cita.idpaciente=rs.getString("idpaciente");
                       cita.date=rs.getString("date");
                       cita.time=rs.getString("time");
                       cita.matter=rs.getString("matter");
                       cita.state=rs.getString("state");
                        
		}
		rs.close();
          
        return cita;
        }
        public ResultSet ReporteCitas() throws SQLException{
        Paciente paciente=new Paciente(); 
        String select="select p.id,p.name,p.lastname,p.email,a.date,a.time,a.matter,a.state from patent p inner join appointment a on p.id=a.idpaciente";
          PreparedStatement ps = con.prepareStatement(select);         
		ResultSet rs = ps.executeQuery();  
        return rs;
        }
}
