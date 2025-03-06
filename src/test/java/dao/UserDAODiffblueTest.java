package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import model.User;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import util.DBConnection;

public class UserDAODiffblueTest {
  /**
   * Test {@link UserDAO#login(String, String)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getString(String)} return {@code jane.doe@example.org}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#login(String, String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean UserDAO.login(String, String)"})
  public void testLogin_givenResultSetGetStringReturnJaneDoeExampleOrg_thenReturnFalse() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getString(Mockito.<String>any())).thenReturn("jane.doe@example.org");
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act
      boolean actualLoginResult = (new UserDAO()).login("jane.doe@example.org", "iloveyou");

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE email = ? AND password = ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setString(anyInt(), Mockito.<String>any());
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertFalse(actualLoginResult);
    }
  }

  /**
   * Test {@link UserDAO#login(String, String)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getString(String)} return {@code jane.doe@example.org}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#login(String, String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean UserDAO.login(String, String)"})
  public void testLogin_givenResultSetGetStringReturnJaneDoeExampleOrg_thenReturnTrue() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getString(Mockito.<String>any())).thenReturn("jane.doe@example.org");
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act
      boolean actualLoginResult = (new UserDAO()).login("jane.doe@example.org", "jane.doe@example.org");

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE email = ? AND password = ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setString(anyInt(), eq("jane.doe@example.org"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertTrue(actualLoginResult);
    }
  }

  /**
   * Test {@link UserDAO#login(String, String)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getString(String)} return {@code String}.</li>
   *   <li>When {@code iloveyou}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#login(String, String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean UserDAO.login(String, String)"})
  public void testLogin_givenResultSetGetStringReturnString_whenIloveyou_thenReturnFalse() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getString(Mockito.<String>any())).thenReturn("String");
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act
      boolean actualLoginResult = (new UserDAO()).login("jane.doe@example.org", "iloveyou");

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE email = ? AND password = ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setString(anyInt(), Mockito.<String>any());
      verify(resultSet).getString(eq("email"));
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertFalse(actualLoginResult);
    }
  }

  /**
   * Test {@link UserDAO#login(String, String)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getString(String)} throw {@link SQLException#SQLException()}.</li>
   *   <li>When {@code iloveyou}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#login(String, String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean UserDAO.login(String, String)"})
  public void testLogin_givenResultSetGetStringThrowSQLException_whenIloveyou_thenReturnFalse() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getString(Mockito.<String>any())).thenThrow(new SQLException());
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act
      boolean actualLoginResult = (new UserDAO()).login("jane.doe@example.org", "iloveyou");

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE email = ? AND password = ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setString(anyInt(), Mockito.<String>any());
      verify(resultSet).getString(eq("email"));
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertFalse(actualLoginResult);
    }
  }

  /**
   * Test {@link UserDAO#login(String, String)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#next()} return {@code false}.</li>
   *   <li>When {@code iloveyou}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#login(String, String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean UserDAO.login(String, String)"})
  public void testLogin_givenResultSetNextReturnFalse_whenIloveyou_thenReturnFalse() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.next()).thenReturn(false).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act
      boolean actualLoginResult = (new UserDAO()).login("jane.doe@example.org", "iloveyou");

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE email = ? AND password = ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setString(anyInt(), Mockito.<String>any());
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertFalse(actualLoginResult);
    }
  }

  /**
   * Test {@link UserDAO#register(User)}.
   * <ul>
   *   <li>Then return {@code Email alreay used.}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#register(User)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"String UserDAO.register(User)"})
  public void testRegister_thenReturnEmailAlreayUsed() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      doThrow(new SQLIntegrityConstraintViolationException(
          "INSERT INTO user(first_name, last_name, email, password) VALUES (?, ?, ?, ?);")).when(preparedStatement)
              .setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      UserDAO userDAO = new UserDAO();

      // Act
      String actualRegisterResult = userDAO
          .register(new User(1, "Jane", "Doe", "iloveyou", "jane.doe@example.org", "Image"));

      // Assert
      verify(connection)
          .prepareStatement(eq("INSERT INTO user(first_name, last_name, email, password) VALUES (?, ?, ?, ?);"));
      verify(preparedStatement).setString(eq(1), eq("Jane"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals("Email alreay used.", actualRegisterResult);
    }
  }

  /**
   * Test {@link UserDAO#register(User)}.
   * <ul>
   *   <li>Then return {@code Registration Successful.}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#register(User)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"String UserDAO.register(User)"})
  public void testRegister_thenReturnRegistrationSuccessful() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.execute()).thenReturn(true);
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      UserDAO userDAO = new UserDAO();

      // Act
      String actualRegisterResult = userDAO
          .register(new User(1, "Jane", "Doe", "iloveyou", "jane.doe@example.org", "Image"));

      // Assert
      verify(connection)
          .prepareStatement(eq("INSERT INTO user(first_name, last_name, email, password) VALUES (?, ?, ?, ?);"));
      verify(preparedStatement).execute();
      verify(preparedStatement, atLeast(1)).setString(anyInt(), Mockito.<String>any());
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals("Registration Successful.", actualRegisterResult);
    }
  }

  /**
   * Test {@link UserDAO#getUsersForChat(int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} return one.</li>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#getUsersForChat(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"ArrayList UserDAO.getUsersForChat(int)"})
  public void testGetUsersForChat_givenResultSetGetIntReturnOne_thenReturnSizeIsTwo() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenReturn(1);
      when(resultSet.getString(Mockito.<String>any())).thenReturn("String");
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act
      ArrayList<User> actualUsersForChat = (new UserDAO()).getUsersForChat(1);

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE user_id <> ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(resultSet, atLeast(1)).getInt(eq("user_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals(2, actualUsersForChat.size());
      User getResult = actualUsersForChat.get(0);
      assertEquals("String", getResult.getEmail());
      User getResult2 = actualUsersForChat.get(1);
      assertEquals("String", getResult2.getEmail());
      assertEquals("String", getResult.getFirst_name());
      assertEquals("String", getResult2.getFirst_name());
      assertEquals("String", getResult.getImage());
      assertEquals("String", getResult2.getImage());
      assertEquals("String", getResult.getLast_name());
      assertEquals("String", getResult2.getLast_name());
      assertEquals("String", getResult.getPassword());
      assertEquals("String", getResult2.getPassword());
      assertEquals(1, getResult.getUser_id());
      assertEquals(1, getResult2.getUser_id());
    }
  }

  /**
   * Test {@link UserDAO#getUsersForChat(int)}.
   * <ul>
   *   <li>Then throw {@link SQLIntegrityConstraintViolationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#getUsersForChat(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"ArrayList UserDAO.getUsersForChat(int)"})
  public void testGetUsersForChat_thenThrowSQLIntegrityConstraintViolationException() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any()))
          .thenThrow(new SQLIntegrityConstraintViolationException("SELECT * FROM user WHERE user_id <> ?"));
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act and Assert
      assertThrows(SQLIntegrityConstraintViolationException.class, () -> (new UserDAO()).getUsersForChat(1));
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE user_id <> ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(resultSet).getInt(eq("user_id"));
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link UserDAO#getUserById(int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} return one.</li>
   *   <li>Then return Email is {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#getUserById(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"User UserDAO.getUserById(int)"})
  public void testGetUserById_givenResultSetGetIntReturnOne_thenReturnEmailIsString() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenReturn(1);
      when(resultSet.getString(Mockito.<String>any())).thenReturn("String");
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act
      User actualUserById = (new UserDAO()).getUserById(1);

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE user_id = ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(resultSet).getInt(eq("user_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals("String", actualUserById.getEmail());
      assertEquals("String", actualUserById.getFirst_name());
      assertEquals("String", actualUserById.getImage());
      assertEquals("String", actualUserById.getLast_name());
      assertEquals("String", actualUserById.getPassword());
      assertEquals(1, actualUserById.getUser_id());
    }
  }

  /**
   * Test {@link UserDAO#getUserById(int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#next()} return {@code false}.</li>
   *   <li>Then return Email is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#getUserById(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"User UserDAO.getUserById(int)"})
  public void testGetUserById_givenResultSetNextReturnFalse_thenReturnEmailIsNull() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.next()).thenReturn(false).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act
      User actualUserById = (new UserDAO()).getUserById(1);

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE user_id = ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertNull(actualUserById.getEmail());
      assertNull(actualUserById.getFirst_name());
      assertNull(actualUserById.getImage());
      assertNull(actualUserById.getLast_name());
      assertNull(actualUserById.getPassword());
      assertEquals(0, actualUserById.getUser_id());
    }
  }

  /**
   * Test {@link UserDAO#getUserById(int)}.
   * <ul>
   *   <li>Then throw {@link SQLIntegrityConstraintViolationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#getUserById(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"User UserDAO.getUserById(int)"})
  public void testGetUserById_thenThrowSQLIntegrityConstraintViolationException() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any()))
          .thenThrow(new SQLIntegrityConstraintViolationException("SELECT * FROM user WHERE user_id = ?"));
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act and Assert
      assertThrows(SQLIntegrityConstraintViolationException.class, () -> (new UserDAO()).getUserById(1));
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE user_id = ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(resultSet).getInt(eq("user_id"));
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link UserDAO#getUserByEmail(String)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} return one.</li>
   *   <li>Then return Email is {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#getUserByEmail(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"User UserDAO.getUserByEmail(String)"})
  public void testGetUserByEmail_givenResultSetGetIntReturnOne_thenReturnEmailIsString() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenReturn(1);
      when(resultSet.getString(Mockito.<String>any())).thenReturn("String");
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act
      User actualUserByEmail = (new UserDAO()).getUserByEmail("jane.doe@example.org");

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE email = ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setString(eq(1), eq("jane.doe@example.org"));
      verify(resultSet).getInt(eq("user_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals("String", actualUserByEmail.getEmail());
      assertEquals("String", actualUserByEmail.getFirst_name());
      assertEquals("String", actualUserByEmail.getImage());
      assertEquals("String", actualUserByEmail.getLast_name());
      assertEquals("String", actualUserByEmail.getPassword());
      assertEquals(1, actualUserByEmail.getUser_id());
    }
  }

  /**
   * Test {@link UserDAO#getUserByEmail(String)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#next()} return {@code false}.</li>
   *   <li>Then return Email is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#getUserByEmail(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"User UserDAO.getUserByEmail(String)"})
  public void testGetUserByEmail_givenResultSetNextReturnFalse_thenReturnEmailIsNull() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.next()).thenReturn(false).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act
      User actualUserByEmail = (new UserDAO()).getUserByEmail("jane.doe@example.org");

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE email = ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setString(eq(1), eq("jane.doe@example.org"));
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertNull(actualUserByEmail.getEmail());
      assertNull(actualUserByEmail.getFirst_name());
      assertNull(actualUserByEmail.getImage());
      assertNull(actualUserByEmail.getLast_name());
      assertNull(actualUserByEmail.getPassword());
      assertEquals(0, actualUserByEmail.getUser_id());
    }
  }

  /**
   * Test {@link UserDAO#getUserByEmail(String)}.
   * <ul>
   *   <li>Then throw {@link SQLIntegrityConstraintViolationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#getUserByEmail(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"User UserDAO.getUserByEmail(String)"})
  public void testGetUserByEmail_thenThrowSQLIntegrityConstraintViolationException() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any()))
          .thenThrow(new SQLIntegrityConstraintViolationException("SELECT * FROM user WHERE email = ?"));
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act and Assert
      assertThrows(SQLIntegrityConstraintViolationException.class,
          () -> (new UserDAO()).getUserByEmail("jane.doe@example.org"));
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE email = ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setString(eq(1), eq("jane.doe@example.org"));
      verify(resultSet).getInt(eq("user_id"));
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link UserDAO#deleteUser(int)}.
   * <ul>
   *   <li>Then calls {@link PreparedStatement#executeUpdate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#deleteUser(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void UserDAO.deleteUser(int)"})
  public void testDeleteUser_thenCallsExecuteUpdate() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeUpdate()).thenReturn(1);
      doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act
      (new UserDAO()).deleteUser(1);

      // Assert
      verify(connection).prepareStatement(eq("DELETE FROM user WHERE user_id = ?;"));
      verify(preparedStatement).executeUpdate();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link UserDAO#deleteUser(int)}.
   * <ul>
   *   <li>Then throw {@link SQLIntegrityConstraintViolationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#deleteUser(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void UserDAO.deleteUser(int)"})
  public void testDeleteUser_thenThrowSQLIntegrityConstraintViolationException() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      doThrow(new SQLIntegrityConstraintViolationException("DELETE FROM user WHERE user_id = ?;"))
          .when(preparedStatement)
          .setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act and Assert
      assertThrows(SQLIntegrityConstraintViolationException.class, () -> (new UserDAO()).deleteUser(1));
      verify(connection).prepareStatement(eq("DELETE FROM user WHERE user_id = ?;"));
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link UserDAO#updateProfile(User)}.
   * <ul>
   *   <li>Then return {@code Email alreay used.}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#updateProfile(User)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"String UserDAO.updateProfile(User)"})
  public void testUpdateProfile_thenReturnEmailAlreayUsed() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      doThrow(new SQLIntegrityConstraintViolationException(
          "UPDATE user SET first_name = ?, last_name = ?, email = ? WHERE user_id = ?;")).when(preparedStatement)
              .setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      UserDAO userDAO = new UserDAO();

      // Act
      String actualUpdateProfileResult = userDAO
          .updateProfile(new User(1, "Jane", "Doe", "iloveyou", "jane.doe@example.org", "Image"));

      // Assert
      verify(connection)
          .prepareStatement(eq("UPDATE user SET first_name = ?, last_name = ?, email = ? WHERE user_id = ?;"));
      verify(preparedStatement).setString(eq(1), eq("Jane"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals("Email alreay used.", actualUpdateProfileResult);
    }
  }

  /**
   * Test {@link UserDAO#updateProfile(User)}.
   * <ul>
   *   <li>Then return {@code Profile Update Successful.}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#updateProfile(User)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"String UserDAO.updateProfile(User)"})
  public void testUpdateProfile_thenReturnProfileUpdateSuccessful() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.execute()).thenReturn(true);
      doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      UserDAO userDAO = new UserDAO();

      // Act
      String actualUpdateProfileResult = userDAO
          .updateProfile(new User(1, "Jane", "Doe", "iloveyou", "jane.doe@example.org", "Image"));

      // Assert
      verify(connection)
          .prepareStatement(eq("UPDATE user SET first_name = ?, last_name = ?, email = ? WHERE user_id = ?;"));
      verify(preparedStatement).execute();
      verify(preparedStatement).setInt(eq(4), eq(1));
      verify(preparedStatement, atLeast(1)).setString(anyInt(), Mockito.<String>any());
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals("Profile Update Successful.", actualUpdateProfileResult);
    }
  }

  /**
   * Test {@link UserDAO#updatePassword(User)}.
   * <ul>
   *   <li>Then return {@code Password Update Failed.}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#updatePassword(User)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"String UserDAO.updatePassword(User)"})
  public void testUpdatePassword_thenReturnPasswordUpdateFailed() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      doThrow(new SQLIntegrityConstraintViolationException("UPDATE user SET password = ? WHERE user_id = ?;"))
          .when(preparedStatement)
          .setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      UserDAO userDAO = new UserDAO();

      // Act
      String actualUpdatePasswordResult = userDAO
          .updatePassword(new User(1, "Jane", "Doe", "iloveyou", "jane.doe@example.org", "Image"));

      // Assert
      verify(connection).prepareStatement(eq("UPDATE user SET password = ? WHERE user_id = ?;"));
      verify(preparedStatement).setString(eq(1), eq("iloveyou"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals("Password Update Failed.", actualUpdatePasswordResult);
    }
  }

  /**
   * Test {@link UserDAO#updatePassword(User)}.
   * <ul>
   *   <li>Then return {@code Password Update Successful.}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UserDAO#updatePassword(User)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"String UserDAO.updatePassword(User)"})
  public void testUpdatePassword_thenReturnPasswordUpdateSuccessful() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.execute()).thenReturn(true);
      doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      UserDAO userDAO = new UserDAO();

      // Act
      String actualUpdatePasswordResult = userDAO
          .updatePassword(new User(1, "Jane", "Doe", "iloveyou", "jane.doe@example.org", "Image"));

      // Assert
      verify(connection).prepareStatement(eq("UPDATE user SET password = ? WHERE user_id = ?;"));
      verify(preparedStatement).execute();
      verify(preparedStatement).setInt(eq(2), eq(1));
      verify(preparedStatement).setString(eq(1), eq("iloveyou"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals("Password Update Successful.", actualUpdatePasswordResult);
    }
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
