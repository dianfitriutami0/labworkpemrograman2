/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pointofsales;

/**
 *
 * @author ACER
 */
public class session {
    private static String user,id;
    
    public static void setuser(String user){
        session.user = user;
        
    }
    
    public static String getuser(){
    return user;
    }
    
    public static void setid(String id){
        session.id = id;
        
    }
    
    public static String getid(){
    return id;
    }
}
