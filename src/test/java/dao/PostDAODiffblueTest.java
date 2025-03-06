package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import model.Post;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import util.DBConnection;

public class PostDAODiffblueTest {
  /**
   * Test {@link PostDAO#insertPost(int, String)}.
   * <ul>
   *   <li>Then calls {@link PreparedStatement#executeUpdate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PostDAO#insertPost(int, String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void PostDAO.insertPost(int, String)"})
  public void testInsertPost_thenCallsExecuteUpdate() throws SQLException {
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
      (new PostDAO()).insertPost(1, "Not all who wander are lost");

      // Assert
      verify(connection).prepareStatement(eq("INSERT INTO post(user_id, body) VALUES (?,?);"));
      verify(preparedStatement).executeUpdate();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(preparedStatement).setString(eq(2), eq("Not all who wander are lost"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link PostDAO#insertPost(int, String)}.
   * <ul>
   *   <li>Then throw {@link SQLException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PostDAO#insertPost(int, String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void PostDAO.insertPost(int, String)"})
  public void testInsertPost_thenThrowSQLException() throws SQLException {
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
      assertThrows(SQLException.class, () -> (new PostDAO()).insertPost(1, "Not all who wander are lost"));
      verify(connection).prepareStatement(eq("INSERT INTO post(user_id, body) VALUES (?,?);"));
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link PostDAO#getAllPost()}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} return one.</li>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link PostDAO#getAllPost()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"ArrayList PostDAO.getAllPost()"})
  public void testGetAllPost_givenResultSetGetIntReturnOne_thenReturnSizeIsTwo() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenReturn(1);
      when(resultSet.getString(Mockito.<String>any())).thenReturn("String");
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act
      ArrayList<Post> actualAllPost = (new PostDAO()).getAllPost();

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM post ORDER BY post_time DESC;"));
      verify(preparedStatement).executeQuery();
      verify(resultSet, atLeast(1)).getInt(Mockito.<String>any());
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals(2, actualAllPost.size());
      Post getResult = actualAllPost.get(0);
      assertEquals("String", getResult.getBody());
      Post getResult2 = actualAllPost.get(1);
      assertEquals("String", getResult2.getBody());
      assertEquals("String", getResult.getPost_time());
      assertEquals("String", getResult2.getPost_time());
      assertEquals(1, getResult.getPost_id());
      assertEquals(1, getResult2.getPost_id());
      assertEquals(1, getResult.getUser_id());
      assertEquals(1, getResult2.getUser_id());
    }
  }

  /**
   * Test {@link PostDAO#getAllPost()}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} throw {@link SQLException#SQLException()}.</li>
   *   <li>Then throw {@link SQLException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PostDAO#getAllPost()}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"ArrayList PostDAO.getAllPost()"})
  public void testGetAllPost_givenResultSetGetIntThrowSQLException_thenThrowSQLException() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenThrow(new SQLException());
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);

      // Act and Assert
      assertThrows(SQLException.class, () -> (new PostDAO()).getAllPost());
      verify(connection).prepareStatement(eq("SELECT * FROM post ORDER BY post_time DESC;"));
      verify(preparedStatement).executeQuery();
      verify(resultSet).getInt(eq("post_id"));
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link PostDAO#getUserPost(int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} return one.</li>
   *   <li>Then return size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link PostDAO#getUserPost(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"ArrayList PostDAO.getUserPost(int)"})
  public void testGetUserPost_givenResultSetGetIntReturnOne_thenReturnSizeIsTwo() throws SQLException {
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
      ArrayList<Post> actualUserPost = (new PostDAO()).getUserPost(1);

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM post WHERE user_id = ? ORDER BY post_time DESC;"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(resultSet, atLeast(1)).getInt(Mockito.<String>any());
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals(2, actualUserPost.size());
      Post getResult = actualUserPost.get(0);
      assertEquals("String", getResult.getBody());
      Post getResult2 = actualUserPost.get(1);
      assertEquals("String", getResult2.getBody());
      assertEquals("String", getResult.getPost_time());
      assertEquals("String", getResult2.getPost_time());
      assertEquals(1, getResult.getPost_id());
      assertEquals(1, getResult2.getPost_id());
      assertEquals(1, getResult.getUser_id());
      assertEquals(1, getResult2.getUser_id());
    }
  }

  /**
   * Test {@link PostDAO#getUserPost(int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} throw {@link SQLException#SQLException()}.</li>
   *   <li>Then throw {@link SQLException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PostDAO#getUserPost(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"ArrayList PostDAO.getUserPost(int)"})
  public void testGetUserPost_givenResultSetGetIntThrowSQLException_thenThrowSQLException() throws SQLException {
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
      assertThrows(SQLException.class, () -> (new PostDAO()).getUserPost(1));
      verify(connection).prepareStatement(eq("SELECT * FROM post WHERE user_id = ? ORDER BY post_time DESC;"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(resultSet).getInt(eq("post_id"));
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link PostDAO#deletePost(int)}.
   * <ul>
   *   <li>Then calls {@link PreparedStatement#executeUpdate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PostDAO#deletePost(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void PostDAO.deletePost(int)"})
  public void testDeletePost_thenCallsExecuteUpdate() throws SQLException {
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
      (new PostDAO()).deletePost(1);

      // Assert
      verify(connection).prepareStatement(eq("DELETE FROM post WHERE post_id = ?;"));
      verify(preparedStatement).executeUpdate();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link PostDAO#deletePost(int)}.
   * <ul>
   *   <li>Then throw {@link SQLException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PostDAO#deletePost(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void PostDAO.deletePost(int)"})
  public void testDeletePost_thenThrowSQLException() throws SQLException {
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
      assertThrows(SQLException.class, () -> (new PostDAO()).deletePost(1));
      verify(connection).prepareStatement(eq("DELETE FROM post WHERE post_id = ?;"));
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link PostDAO#getPost(int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} return one.</li>
   *   <li>Then return Body is {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PostDAO#getPost(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Post PostDAO.getPost(int)"})
  public void testGetPost_givenResultSetGetIntReturnOne_thenReturnBodyIsString() throws SQLException {
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
      Post actualPost = (new PostDAO()).getPost(1);

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM post WHERE post_id = ?;"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(resultSet, atLeast(1)).getInt(Mockito.<String>any());
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals("String", actualPost.getBody());
      assertEquals("String", actualPost.getPost_time());
      assertEquals(1, actualPost.getPost_id());
      assertEquals(1, actualPost.getUser_id());
    }
  }

  /**
   * Test {@link PostDAO#getPost(int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} throw {@link SQLException#SQLException()}.</li>
   *   <li>Then throw {@link SQLException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PostDAO#getPost(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Post PostDAO.getPost(int)"})
  public void testGetPost_givenResultSetGetIntThrowSQLException_thenThrowSQLException() throws SQLException {
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
      assertThrows(SQLException.class, () -> (new PostDAO()).getPost(1));
      verify(connection).prepareStatement(eq("SELECT * FROM post WHERE post_id = ?;"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(resultSet).getInt(eq("post_id"));
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link PostDAO#getPost(int)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#next()} return {@code false}.</li>
   *   <li>Then return Body is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PostDAO#getPost(int)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Post PostDAO.getPost(int)"})
  public void testGetPost_givenResultSetNextReturnFalse_thenReturnBodyIsNull() throws SQLException {
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
      Post actualPost = (new PostDAO()).getPost(1);

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM post WHERE post_id = ?;"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(resultSet).next();
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertNull(actualPost.getBody());
      assertNull(actualPost.getPost_time());
      assertEquals(0, actualPost.getPost_id());
      assertEquals(0, actualPost.getUser_id());
    }
  }

  /**
   * Test {@link PostDAO#updatePost(Post)}.
   * <ul>
   *   <li>Then return {@code Post Update Failed.}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PostDAO#updatePost(Post)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"String PostDAO.updatePost(Post)"})
  public void testUpdatePost_thenReturnPostUpdateFailed() throws SQLException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      doThrow(new SQLException()).when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      PostDAO postDAO = new PostDAO();

      // Act
      String actualUpdatePostResult = postDAO.updatePost(new Post(1, 1, "Not all who wander are lost", "Post time"));

      // Assert
      verify(connection).prepareStatement(eq("UPDATE post SET body = ? WHERE post_id = ?;"));
      verify(preparedStatement).setString(eq(1), eq("Not all who wander are lost"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals("Post Update Failed.", actualUpdatePostResult);
    }
  }

  /**
   * Test {@link PostDAO#updatePost(Post)}.
   * <ul>
   *   <li>Then return {@code Post Update Successful.}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PostDAO#updatePost(Post)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"String PostDAO.updatePost(Post)"})
  public void testUpdatePost_thenReturnPostUpdateSuccessful() throws SQLException {
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
      PostDAO postDAO = new PostDAO();

      // Act
      String actualUpdatePostResult = postDAO.updatePost(new Post(1, 1, "Not all who wander are lost", "Post time"));

      // Assert
      verify(connection).prepareStatement(eq("UPDATE post SET body = ? WHERE post_id = ?;"));
      verify(preparedStatement).execute();
      verify(preparedStatement).setInt(eq(2), eq(1));
      verify(preparedStatement).setString(eq(1), eq("Not all who wander are lost"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
      assertEquals("Post Update Successful.", actualUpdatePostResult);
    }
  }

  /**
   * Test new {@link PostDAO} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link PostDAO}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void PostDAO.<init>()"})
  public void testNewPostDAO() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Missing observers.
    //   Diffblue Cover was unable to create an assertion.
    //   There are no fields that could be asserted on.

    // Arrange and Act
    new PostDAO();
  }
}
