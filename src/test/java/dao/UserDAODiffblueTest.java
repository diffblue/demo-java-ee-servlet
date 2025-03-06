package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.SQLException;
import model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UserDAODiffblueTest {
  /**
   * Test {@link UserDAO#login(String, String)}.
   * <p>
   * Method under test: {@link UserDAO#login(String, String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean UserDAO.login(String, String)"})
  public void testLogin() {
    // Arrange, Act and Assert
    assertFalse((new UserDAO()).login("jane.doe@example.org", "iloveyou"));
  }

  /**
   * Test {@link UserDAO#register(User)}.
   * <p>
   * Method under test: {@link UserDAO#register(User)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"String UserDAO.register(User)"})
  public void testRegister() {
    // Arrange
    UserDAO userDAO = new UserDAO();

    // Act and Assert
    assertEquals("Registration Failed.",
        userDAO.register(new User(1, "Jane", "Doe", "iloveyou", "jane.doe@example.org", "Image")));
  }

  /**
   * Test {@link UserDAO#getUsersForChat(int)}.
   * <p>
   * Method under test: {@link UserDAO#getUsersForChat(int)}
   */
  @Test
  @Ignore("TODO: Complete this test")
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"java.util.ArrayList UserDAO.getUsersForChat(int)"})
  public void testGetUsersForChat() throws SQLException {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Sandboxing policy violation.
    //   Diffblue Cover ran code in your project that tried
    //     to access the network.
    //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
    //   your code from damaging your system environment.
    //   See https://diff.blue/R011 to resolve this issue.

    // Arrange and Act
    (new UserDAO()).getUsersForChat(1);
  }

  /**
   * Test {@link UserDAO#getUserById(int)}.
   * <p>
   * Method under test: {@link UserDAO#getUserById(int)}
   */
  @Test
  @Ignore("TODO: Complete this test")
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"User UserDAO.getUserById(int)"})
  public void testGetUserById() throws SQLException {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Sandboxing policy violation.
    //   Diffblue Cover ran code in your project that tried
    //     to access the network.
    //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
    //   your code from damaging your system environment.
    //   See https://diff.blue/R011 to resolve this issue.

    // Arrange and Act
    (new UserDAO()).getUserById(1);
  }

  /**
   * Test {@link UserDAO#getUserByEmail(String)}.
   * <p>
   * Method under test: {@link UserDAO#getUserByEmail(String)}
   */
  @Test
  @Ignore("TODO: Complete this test")
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"User UserDAO.getUserByEmail(String)"})
  public void testGetUserByEmail() throws SQLException {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Sandboxing policy violation.
    //   Diffblue Cover ran code in your project that tried
    //     to access the network.
    //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
    //   your code from damaging your system environment.
    //   See https://diff.blue/R011 to resolve this issue.

    // Arrange and Act
    (new UserDAO()).getUserByEmail("jane.doe@example.org");
  }

  /**
   * Test {@link UserDAO#deleteUser(int)}.
   * <p>
   * Method under test: {@link UserDAO#deleteUser(int)}
   */
  @Test
  @Ignore("TODO: Complete this test")
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void UserDAO.deleteUser(int)"})
  public void testDeleteUser() throws SQLException {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Sandboxing policy violation.
    //   Diffblue Cover ran code in your project that tried
    //     to access the network.
    //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
    //   your code from damaging your system environment.
    //   See https://diff.blue/R011 to resolve this issue.

    // Arrange and Act
    (new UserDAO()).deleteUser(1);
  }

  /**
   * Test {@link UserDAO#updateProfile(User)}.
   * <p>
   * Method under test: {@link UserDAO#updateProfile(User)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"String UserDAO.updateProfile(User)"})
  public void testUpdateProfile() {
    // Arrange
    UserDAO userDAO = new UserDAO();

    // Act and Assert
    assertEquals("Profile Update Failed.",
        userDAO.updateProfile(new User(1, "Jane", "Doe", "iloveyou", "jane.doe@example.org", "Image")));
  }

  /**
   * Test {@link UserDAO#updatePassword(User)}.
   * <p>
   * Method under test: {@link UserDAO#updatePassword(User)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"String UserDAO.updatePassword(User)"})
  public void testUpdatePassword() {
    // Arrange
    UserDAO userDAO = new UserDAO();

    // Act and Assert
    assertEquals("Password Update Failed.",
        userDAO.updatePassword(new User(1, "Jane", "Doe", "iloveyou", "jane.doe@example.org", "Image")));
  }

  /**
   * Test new {@link UserDAO} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link UserDAO}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void UserDAO.<init>()"})
  public void testNewUserDAO() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Missing observers.
    //   Diffblue Cover was unable to create an assertion.
    //   There are no fields that could be asserted on.

    // Arrange and Act
    new UserDAO();
  }
}
