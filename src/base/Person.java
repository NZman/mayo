/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

/**
 *
 * @author Oren
 */
public interface Person {

  /**@param name The name of the Person */
  void setName(String name);
  
  /**@param uname The username of the Person */
  void setUserName(String uname);
  
  /**@return A single string containing the Person's name */
  String getName();

  /**@return the Person's user name */
  String getUserName();
  
}
