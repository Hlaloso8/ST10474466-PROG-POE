
package loginchat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import loginchat.LoginChat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginChatTest {
    private LoginChat login = new LoginChat();
    
    public LoginChatTest() {
    }

    @Test
    public void testMain() {
    }

    @Test
    public void testCheckUsername() {
        //Check format of the Username
        assertTrue(login.checkUsername("kyl_1"));
        assertTrue(login.checkUsername("a_b"));
        
        //incorrect format
        assertFalse(login.checkUsername("kyle!!!!!!!"));
        assertFalse(login.checkUsername("kyle"));
        assertFalse(login.checkUsername("ky le"));
    }

    @Test
    public void testCheckPasswordComplexity() {
        //check correct password
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertTrue(login.checkPasswordComplexity("A1@bcdefg"));
        
        //incorrect passwords
        assertFalse(login.checkPasswordComplexity("password"));
        assertFalse(login.checkPasswordComplexity("Password"));
        assertFalse(login.checkPasswordComplexity("P@ssw0r"));
        assertFalse(login.checkPasswordComplexity("password123"));
        
    }

    @Test
    public void testCheckCellPhoneNumber() {
        //correct formats
        assertTrue(login.checkCellPhoneNumber("+27831234567"));
        assertTrue(login.checkCellPhoneNumber("+1234567890"));
        
        //incorrect formats
        assertFalse(login.checkCellPhoneNumber("08966553"));
        assertFalse(login.checkCellPhoneNumber("+123456"));
        assertFalse(login.checkCellPhoneNumber("27831234567"));
        assertFalse(login.checkCellPhoneNumber("abc1234567"));
        
    }

    @Test
    public void testRegisterUser() {
        //user input
        String simulatedInput = "Kyle\ntest_1\nP@ssw0rd123!\n+27831234567\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner testScanner = new Scanner(inputStream);
        
        String result = login.registerUser(testScanner);
        
        assertEquals("Registration Successful",result);
        
        //verify
        assertEquals("test_1", getPrivateField(login, "username"));
        assertEquals("P@ssw0rd123!", getPrivateField(login,"password"));
        assertEquals("+27831234567", getPrivateField(login,"cellnumber"));
        assertEquals("Hlaloso", getPrivateField(login, "firstName"));
        assertEquals("Tleane", getPrivateField(login, "lastName"));        
        
    }
    
    //method to set private fields using reflection
    private void setPrivateField(Object object, String fieldName, Object value){
        try{
            java.lang.reflect.Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        }catch (Exception e){
            throw new RuntimeException("Error setting field:" + fieldName, e);
        }
    }

    @Test
    public void testLoginUser() {
        //set up data test
        setPrivateField(login, "username", "test_1");
        setPrivateField(login, "password", "P@ssw0rd123");
        
        //Successful login
        assertTrue(login.loginUser("test_1", "P@ssw0rd123"));
        
        //Failed logins
        assertFalse(login.loginUser("wrong", "P@ssw0rd"));
        assertFalse(login.loginUser("test_1", "wrong"));
        assertFalse(login.loginUser("wrong", "wrong"));
        
    }

    @Test
    public void testReturnLoginStatus() {
        
        setPrivateField(login, "firstName", "Kyle");
        setPrivateField(login, "lastName", "");
        
        //Success login message
        assertEquals("Welcome Kyle, it is great to see you again.", login.returnLoginStatus(true));
        
        //Failed login message
        assertEquals("Username or password incorrect, please try again.",login.returnLoginStatus(false));
        
        
    }

    private Object getPrivateField(LoginChat login, String username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setPrivateField(LoginChat login, String username, String test_1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
