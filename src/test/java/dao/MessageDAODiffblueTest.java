package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
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
import java.util.ArrayList;
import model.Message;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import util.DBConnection;

public class MessageDAODiffblueTest {
  /**
   * Test {@link MessageDAO#getAllMessage(int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} return one.</li>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#getAllMessage(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"ArrayList MessageDAO.getAllMessage(int)"})
  public void testGetAllMessage_givenResultSetGetIntReturnOne_thenReturnSizeIsTwo() throws SQLException {
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
      ArrayList<Message> actualAllMessage = (new MessageDAO()).getAllMessage(1);

      // Assert
      verify(connection).prepareStatement(eq(
          "SELECT * FROM message WHERE from_user = ? AND chat_time IN (SELECT MAX(chat_time) FROM message GROUP by to_user) ORDER BY chat_time DESC;"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(resultSet, atLeast(1)).getInt(eq("chat_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals(2, actualAllMessage.size());
      Message getResult = actualAllMessage.get(0);
      assertEquals("String", getResult.getChat_time());
      Message getResult2 = actualAllMessage.get(1);
      assertEquals("String", getResult2.getChat_time());
      assertEquals("String", getResult.getFrom_user());
      assertEquals("String", getResult2.getFrom_user());
      assertEquals("String", getResult.getMessage());
      assertEquals("String", getResult2.getMessage());
      assertEquals("String", getResult.getTo_user());
      assertEquals("String", getResult2.getTo_user());
      assertEquals(1, getResult.getChat_id());
      assertEquals(1, getResult2.getChat_id());
    }
  }

  /**
   * Test {@link MessageDAO#getAllMessage(int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} throw {@link SQLException#SQLException()}.</li>
   *   <li>Then throw {@link SQLException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#getAllMessage(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"ArrayList MessageDAO.getAllMessage(int)"})
  public void testGetAllMessage_givenResultSetGetIntThrowSQLException_thenThrowSQLException() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenThrow(new SQLException());
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
      assertThrows(SQLException.class, () -> (new MessageDAO()).getAllMessage(1));
      verify(connection).prepareStatement(eq(
          "SELECT * FROM message WHERE from_user = ? AND chat_time IN (SELECT MAX(chat_time) FROM message GROUP by to_user) ORDER BY chat_time DESC;"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(resultSet).getInt(eq("chat_id"));
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link MessageDAO#deleteAllMessage(int, int)}.
   * <ul>
   *   <li>Then calls {@link PreparedStatement#executeUpdate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#deleteAllMessage(int, int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageDAO.deleteAllMessage(int, int)"})
  public void testDeleteAllMessage_thenCallsExecuteUpdate() throws SQLException {
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
      (new MessageDAO()).deleteAllMessage(1, 1);

      // Assert
      verify(connection).prepareStatement(
          eq("DELETE FROM message WHERE (from_user = ? AND to_user = ?) OR (to_user = ? AND from_user = ?);"));
      verify(preparedStatement).executeUpdate();
      verify(preparedStatement, atLeast(1)).setInt(anyInt(), eq(1));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link MessageDAO#deleteAllMessage(int, int)}.
   * <ul>
   *   <li>Then throw {@link SQLException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#deleteAllMessage(int, int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageDAO.deleteAllMessage(int, int)"})
  public void testDeleteAllMessage_thenThrowSQLException() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      doThrow(new SQLException()).when(preparedStatement).setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act and Assert
      assertThrows(SQLException.class, () -> (new MessageDAO()).deleteAllMessage(1, 1));
      verify(connection).prepareStatement(
          eq("DELETE FROM message WHERE (from_user = ? AND to_user = ?) OR (to_user = ? AND from_user = ?);"));
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link MessageDAO#getMessage(int, int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} return one.</li>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#getMessage(int, int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"ArrayList MessageDAO.getMessage(int, int)"})
  public void testGetMessage_givenResultSetGetIntReturnOne_thenReturnSizeIsTwo() throws SQLException {
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
      ArrayList<Message> actualMessage = (new MessageDAO()).getMessage(1, 1);

      // Assert
      verify(connection).prepareStatement(eq(
          "SELECT * FROM message WHERE (from_user = ? OR to_user = ?) AND (from_user = ? OR to_user = ?) ORDER BY chat_time;"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setInt(anyInt(), eq(1));
      verify(resultSet, atLeast(1)).getInt(eq("chat_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals(2, actualMessage.size());
      Message getResult = actualMessage.get(0);
      assertEquals("String", getResult.getChat_time());
      Message getResult2 = actualMessage.get(1);
      assertEquals("String", getResult2.getChat_time());
      assertEquals("String", getResult.getFrom_user());
      assertEquals("String", getResult2.getFrom_user());
      assertEquals("String", getResult.getMessage());
      assertEquals("String", getResult2.getMessage());
      assertEquals("String", getResult.getTo_user());
      assertEquals("String", getResult2.getTo_user());
      assertEquals(1, getResult.getChat_id());
      assertEquals(1, getResult2.getChat_id());
    }
  }

  /**
   * Test {@link MessageDAO#getMessage(int, int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} throw {@link SQLException#SQLException()}.</li>
   *   <li>Then throw {@link SQLException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#getMessage(int, int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"ArrayList MessageDAO.getMessage(int, int)"})
  public void testGetMessage_givenResultSetGetIntThrowSQLException_thenThrowSQLException() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenThrow(new SQLException());
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
      assertThrows(SQLException.class, () -> (new MessageDAO()).getMessage(1, 1));
      verify(connection).prepareStatement(eq(
          "SELECT * FROM message WHERE (from_user = ? OR to_user = ?) AND (from_user = ? OR to_user = ?) ORDER BY chat_time;"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setInt(anyInt(), eq(1));
      verify(resultSet).getInt(eq("chat_id"));
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link MessageDAO#insertMessage(int, int, String)}.
   * <ul>
   *   <li>Then calls {@link PreparedStatement#executeUpdate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#insertMessage(int, int, String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageDAO.insertMessage(int, int, String)"})
  public void testInsertMessage_thenCallsExecuteUpdate() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeUpdate()).thenReturn(1);
      doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act
      (new MessageDAO()).insertMessage(1, 1, "Not all who wander are lost");

      // Assert
      verify(connection).prepareStatement(eq("INSERT INTO message(from_user, to_user, message) VALUES (?,?,?);"));
      verify(preparedStatement).executeUpdate();
      verify(preparedStatement, atLeast(1)).setInt(anyInt(), eq(1));
      verify(preparedStatement).setString(eq(3), eq("Not all who wander are lost"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link MessageDAO#insertMessage(int, int, String)}.
   * <ul>
   *   <li>Then throw {@link SQLException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#insertMessage(int, int, String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageDAO.insertMessage(int, int, String)"})
  public void testInsertMessage_thenThrowSQLException() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      doThrow(new SQLException()).when(preparedStatement).setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act and Assert
      assertThrows(SQLException.class, () -> (new MessageDAO()).insertMessage(1, 1, "Not all who wander are lost"));
      verify(connection).prepareStatement(eq("INSERT INTO message(from_user, to_user, message) VALUES (?,?,?);"));
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link MessageDAO#deleteMessage(int)}.
   * <ul>
   *   <li>Then calls {@link PreparedStatement#executeUpdate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#deleteMessage(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageDAO.deleteMessage(int)"})
  public void testDeleteMessage_thenCallsExecuteUpdate() throws SQLException {
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
      (new MessageDAO()).deleteMessage(1);

      // Assert
      verify(connection).prepareStatement(eq("DELETE FROM message WHERE chat_id = ?;"));
      verify(preparedStatement).executeUpdate();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link MessageDAO#deleteMessage(int)}.
   * <ul>
   *   <li>Then throw {@link SQLException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#deleteMessage(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageDAO.deleteMessage(int)"})
  public void testDeleteMessage_thenThrowSQLException() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      doThrow(new SQLException()).when(preparedStatement).setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act and Assert
      assertThrows(SQLException.class, () -> (new MessageDAO()).deleteMessage(1));
      verify(connection).prepareStatement(eq("DELETE FROM message WHERE chat_id = ?;"));
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link MessageDAO#messageCount(int, int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} return three.</li>
   *   <li>When four.</li>
   *   <li>Then return three.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#messageCount(int, int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int MessageDAO.messageCount(int, int)"})
  public void testMessageCount_givenResultSetGetIntReturnThree_whenFour_thenReturnThree() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenReturn(3);
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
      int actualMessageCountResult = (new MessageDAO()).messageCount(4, 3);

      // Assert
      verify(connection).prepareStatement(eq(
          "SELECT COUNT(*) msg_count FROM message WHERE (from_user = ? OR to_user = ?) AND (from_user = ? OR to_user = ?);"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
      verify(resultSet, atLeast(1)).getInt(eq("msg_count"));
      verify(resultSet, atLeast(1)).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals(3, actualMessageCountResult);
    }
  }

  /**
   * Test {@link MessageDAO#messageCount(int, int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} return three.</li>
   *   <li>When one.</li>
   *   <li>Then return three.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#messageCount(int, int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int MessageDAO.messageCount(int, int)"})
  public void testMessageCount_givenResultSetGetIntReturnThree_whenOne_thenReturnThree() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenReturn(3);
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
      int actualMessageCountResult = (new MessageDAO()).messageCount(1, 3);

      // Assert
      verify(connection).prepareStatement(eq(
          "SELECT COUNT(*) msg_count FROM message WHERE (from_user = ? OR to_user = ?) AND (from_user = ? OR to_user = ?);"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
      verify(resultSet, atLeast(1)).getInt(eq("msg_count"));
      verify(resultSet, atLeast(1)).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals(3, actualMessageCountResult);
    }
  }

  /**
   * Test {@link MessageDAO#messageCount(int, int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} return three.</li>
   *   <li>When three.</li>
   *   <li>Then return three.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#messageCount(int, int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int MessageDAO.messageCount(int, int)"})
  public void testMessageCount_givenResultSetGetIntReturnThree_whenThree_thenReturnThree() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenReturn(3);
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
      int actualMessageCountResult = (new MessageDAO()).messageCount(3, 3);

      // Assert
      verify(connection).prepareStatement(eq(
          "SELECT COUNT(*) msg_count FROM message WHERE (from_user = ? OR to_user = ?) AND (from_user = ? OR to_user = ?);"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setInt(anyInt(), eq(3));
      verify(resultSet, atLeast(1)).getInt(eq("msg_count"));
      verify(resultSet, atLeast(1)).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals(3, actualMessageCountResult);
    }
  }

  /**
   * Test {@link MessageDAO#messageCount(int, int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} return three.</li>
   *   <li>When two.</li>
   *   <li>Then return three.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#messageCount(int, int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int MessageDAO.messageCount(int, int)"})
  public void testMessageCount_givenResultSetGetIntReturnThree_whenTwo_thenReturnThree() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenReturn(3);
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
      int actualMessageCountResult = (new MessageDAO()).messageCount(2, 3);

      // Assert
      verify(connection).prepareStatement(eq(
          "SELECT COUNT(*) msg_count FROM message WHERE (from_user = ? OR to_user = ?) AND (from_user = ? OR to_user = ?);"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
      verify(resultSet, atLeast(1)).getInt(eq("msg_count"));
      verify(resultSet, atLeast(1)).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals(3, actualMessageCountResult);
    }
  }

  /**
   * Test {@link MessageDAO#messageCount(int, int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} throw {@link SQLException#SQLException()}.</li>
   *   <li>Then throw {@link SQLException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageDAO#messageCount(int, int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"int MessageDAO.messageCount(int, int)"})
  public void testMessageCount_givenResultSetGetIntThrowSQLException_thenThrowSQLException() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenThrow(new SQLException());
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
      assertThrows(SQLException.class, () -> (new MessageDAO()).messageCount(3, 3));
      verify(connection).prepareStatement(eq(
          "SELECT COUNT(*) msg_count FROM message WHERE (from_user = ? OR to_user = ?) AND (from_user = ? OR to_user = ?);"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setInt(anyInt(), eq(3));
      verify(resultSet).getInt(eq("msg_count"));
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test new {@link MessageDAO} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link MessageDAO}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageDAO.<init>()"})
  public void testNewMessageDAO() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Missing observers.
    //   Diffblue Cover was unable to create an assertion.
    //   There are no fields that could be asserted on.

    // Arrange and Act
    new MessageDAO();
  }
}
