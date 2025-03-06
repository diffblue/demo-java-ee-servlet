package com.diffblue.pov.servlets.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UserDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link User#User()}
   *   <li>{@link User#setEmail(String)}
   *   <li>{@link User#setFirst_name(String)}
   *   <li>{@link User#setImage(String)}
   *   <li>{@link User#setLast_name(String)}
   *   <li>{@link User#setPassword(String)}
   *   <li>{@link User#setUser_id(int)}
   *   <li>{@link User#getEmail()}
   *   <li>{@link User#getFirst_name()}
   *   <li>{@link User#getImage()}
   *   <li>{@link User#getLast_name()}
   *   <li>{@link User#getPassword()}
   *   <li>{@link User#getUser_id()}
   * </ul>
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void User.<init>()", "void User.<init>(int, String, String, String, String, String)",
      "String User.getEmail()", "String User.getFirst_name()", "String User.getImage()", "String User.getLast_name()",
      "String User.getPassword()", "int User.getUser_id()", "void User.setEmail(String)",
      "void User.setFirst_name(String)", "void User.setImage(String)", "void User.setLast_name(String)",
      "void User.setPassword(String)", "void User.setUser_id(int)"})
  public void testGettersAndSetters() {
    // Arrange and Act
    User actualUser = new User();
    actualUser.setEmail("jane.doe@example.org");
    actualUser.setFirst_name("Jane");
    actualUser.setImage("Image");
    actualUser.setLast_name("Doe");
    actualUser.setPassword("iloveyou");
    actualUser.setUser_id(1);
    String actualEmail = actualUser.getEmail();
    String actualFirst_name = actualUser.getFirst_name();
    String actualImage = actualUser.getImage();
    String actualLast_name = actualUser.getLast_name();
    String actualPassword = actualUser.getPassword();

    // Assert
    assertEquals("Doe", actualLast_name);
    assertEquals("Image", actualImage);
    assertEquals("Jane", actualFirst_name);
    assertEquals("iloveyou", actualPassword);
    assertEquals("jane.doe@example.org", actualEmail);
    assertEquals(1, actualUser.getUser_id());
  }

  /**
   * Test getters and setters.
   * <ul>
   *   <li>When one.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link User#User(int, String, String, String, String, String)}
   *   <li>{@link User#setEmail(String)}
   *   <li>{@link User#setFirst_name(String)}
   *   <li>{@link User#setImage(String)}
   *   <li>{@link User#setLast_name(String)}
   *   <li>{@link User#setPassword(String)}
   *   <li>{@link User#setUser_id(int)}
   *   <li>{@link User#getEmail()}
   *   <li>{@link User#getFirst_name()}
   *   <li>{@link User#getImage()}
   *   <li>{@link User#getLast_name()}
   *   <li>{@link User#getPassword()}
   *   <li>{@link User#getUser_id()}
   * </ul>
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void User.<init>()", "void User.<init>(int, String, String, String, String, String)",
      "String User.getEmail()", "String User.getFirst_name()", "String User.getImage()", "String User.getLast_name()",
      "String User.getPassword()", "int User.getUser_id()", "void User.setEmail(String)",
      "void User.setFirst_name(String)", "void User.setImage(String)", "void User.setLast_name(String)",
      "void User.setPassword(String)", "void User.setUser_id(int)"})
  public void testGettersAndSetters_whenOne() {
    // Arrange and Act
    User actualUser = new User(1, "Jane", "Doe", "iloveyou", "jane.doe@example.org", "Image");
    actualUser.setEmail("jane.doe@example.org");
    actualUser.setFirst_name("Jane");
    actualUser.setImage("Image");
    actualUser.setLast_name("Doe");
    actualUser.setPassword("iloveyou");
    actualUser.setUser_id(1);
    String actualEmail = actualUser.getEmail();
    String actualFirst_name = actualUser.getFirst_name();
    String actualImage = actualUser.getImage();
    String actualLast_name = actualUser.getLast_name();
    String actualPassword = actualUser.getPassword();

    // Assert
    assertEquals("Doe", actualLast_name);
    assertEquals("Image", actualImage);
    assertEquals("Jane", actualFirst_name);
    assertEquals("iloveyou", actualPassword);
    assertEquals("jane.doe@example.org", actualEmail);
    assertEquals(1, actualUser.getUser_id());
  }
}
