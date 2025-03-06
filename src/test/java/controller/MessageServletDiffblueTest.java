package controller;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
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
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import util.DBConnection;

public class MessageServletDiffblueTest {
  /**
   * Test new {@link MessageServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link MessageServlet}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.<init>()"})
  public void testNewMessageServlet() {
    // Arrange, Act and Assert
    assertNull((new MessageServlet()).getServletConfig());
  }

  /**
   * Test {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link HttpSession} {@link HttpSession#getAttribute(String)} return {@code Attribute}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenHttpSessionGetAttributeReturnAttribute() throws IOException, ServletException {
    // Arrange
    MessageServlet messageServlet = new MessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(Mockito.<String>any())).thenThrow(new NumberFormatException("user_id"));
    when(request.getSession(anyBoolean())).thenReturn(httpSession);

    // Act and Assert
    assertThrows(NumberFormatException.class, () -> messageServlet.doGet(request, mock(HttpServletResponse.class)));
    verify(request).getParameter(eq("delete"));
    verify(request).getSession(eq(false));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link RequestDispatcher} {@link RequestDispatcher#forward(ServletRequest, ServletResponse)} throw {@link NumberFormatException#NumberFormatException(String)} with {@code user_id}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenRequestDispatcherForwardThrowNumberFormatExceptionWithUserId()
      throws IOException, SQLException, ServletException {
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
      MessageServlet messageServlet = new MessageServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(1);
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doThrow(new NumberFormatException("user_id")).when(requestDispatcher)
          .forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
      when(request.getSession(anyBoolean())).thenReturn(httpSession);

      // Act and Assert
      assertThrows(NumberFormatException.class, () -> messageServlet.doGet(request, mock(HttpServletResponse.class)));
      verify(connection, atLeast(1)).prepareStatement(Mockito.<String>any());
      verify(preparedStatement, atLeast(1)).executeQuery();
      verify(preparedStatement, atLeast(1)).setInt(eq(1), eq(1));
      verify(resultSet, atLeast(1)).getInt(eq("user_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(eq("delete"));
      verify(request).getRequestDispatcher(eq("WEB-INF/message.jsp"));
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      verify(request).getSession(eq(false));
      verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
      verify(dbConnection, atLeast(1)).getConnection();
      mockDBConnection.verify(DBConnection::getInstance, atLeast(1));
    }
  }

  /**
   * Test {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#next()} return {@code false}.</li>
   *   <li>Then calls {@link Connection#prepareStatement(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenResultSetNextReturnFalse_thenCallsPrepareStatement()
      throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenReturn(1);
      when(resultSet.getString(Mockito.<String>any())).thenReturn("String");
      when(resultSet.next()).thenReturn(false).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      MessageServlet messageServlet = new MessageServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(1);
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
      when(request.getSession(anyBoolean())).thenReturn(httpSession);

      // Act
      messageServlet.doGet(request, mock(HttpServletResponse.class));

      // Assert
      verify(connection, atLeast(1)).prepareStatement(Mockito.<String>any());
      verify(preparedStatement, atLeast(1)).executeQuery();
      verify(preparedStatement, atLeast(1)).setInt(eq(1), eq(1));
      verify(resultSet).getInt(eq("chat_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(eq("delete"));
      verify(request).getRequestDispatcher(eq("WEB-INF/message.jsp"));
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      verify(request).getSession(eq(false));
      verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
      verify(dbConnection, atLeast(1)).getConnection();
      mockDBConnection.verify(DBConnection::getInstance, atLeast(1));
    }
  }

  /**
   * Test {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#next()} return {@code true}.</li>
   *   <li>Then calls {@link Connection#prepareStatement(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenResultSetNextReturnTrue_thenCallsPrepareStatement()
      throws IOException, SQLException, ServletException {
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
      MessageServlet messageServlet = new MessageServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(1);
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
      when(request.getSession(anyBoolean())).thenReturn(httpSession);

      // Act
      messageServlet.doGet(request, mock(HttpServletResponse.class));

      // Assert
      verify(connection, atLeast(1)).prepareStatement(Mockito.<String>any());
      verify(preparedStatement, atLeast(1)).executeQuery();
      verify(preparedStatement, atLeast(1)).setInt(eq(1), eq(1));
      verify(resultSet, atLeast(1)).getInt(eq("user_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(eq("delete"));
      verify(request).getRequestDispatcher(eq("WEB-INF/message.jsp"));
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      verify(request).getSession(eq(false));
      verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
      verify(dbConnection, atLeast(1)).getConnection();
      mockDBConnection.verify(DBConnection::getInstance, atLeast(1));
    }
  }

  /**
   * Test {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link HttpSession} {@link HttpSession#getAttribute(String)} return {@code Attribute}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenHttpSessionGetAttributeReturnAttribute() throws IOException, ServletException {
    // Arrange
    MessageServlet messageServlet = new MessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(Mockito.<String>any())).thenThrow(new NumberFormatException("user_id"));
    when(request.getSession(anyBoolean())).thenReturn(httpSession);

    // Act and Assert
    assertThrows(NumberFormatException.class, () -> messageServlet.doPost(request, mock(HttpServletResponse.class)));
    verify(request).getParameter(eq("delete"));
    verify(request).getSession(eq(false));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link RequestDispatcher} {@link RequestDispatcher#forward(ServletRequest, ServletResponse)} throw {@link NumberFormatException#NumberFormatException(String)} with {@code user_id}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenRequestDispatcherForwardThrowNumberFormatExceptionWithUserId()
      throws IOException, SQLException, ServletException {
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
      MessageServlet messageServlet = new MessageServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(1);
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doThrow(new NumberFormatException("user_id")).when(requestDispatcher)
          .forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
      when(request.getSession(anyBoolean())).thenReturn(httpSession);

      // Act and Assert
      assertThrows(NumberFormatException.class, () -> messageServlet.doPost(request, mock(HttpServletResponse.class)));
      verify(connection, atLeast(1)).prepareStatement(Mockito.<String>any());
      verify(preparedStatement, atLeast(1)).executeQuery();
      verify(preparedStatement, atLeast(1)).setInt(eq(1), eq(1));
      verify(resultSet, atLeast(1)).getInt(eq("user_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(eq("delete"));
      verify(request).getRequestDispatcher(eq("WEB-INF/message.jsp"));
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      verify(request).getSession(eq(false));
      verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
      verify(dbConnection, atLeast(1)).getConnection();
      mockDBConnection.verify(DBConnection::getInstance, atLeast(1));
    }
  }

  /**
   * Test {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#next()} return {@code false}.</li>
   *   <li>Then calls {@link Connection#prepareStatement(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenResultSetNextReturnFalse_thenCallsPrepareStatement()
      throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenReturn(1);
      when(resultSet.getString(Mockito.<String>any())).thenReturn("String");
      when(resultSet.next()).thenReturn(false).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      MessageServlet messageServlet = new MessageServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(1);
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
      when(request.getSession(anyBoolean())).thenReturn(httpSession);

      // Act
      messageServlet.doPost(request, mock(HttpServletResponse.class));

      // Assert
      verify(connection, atLeast(1)).prepareStatement(Mockito.<String>any());
      verify(preparedStatement, atLeast(1)).executeQuery();
      verify(preparedStatement, atLeast(1)).setInt(eq(1), eq(1));
      verify(resultSet).getInt(eq("chat_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(eq("delete"));
      verify(request).getRequestDispatcher(eq("WEB-INF/message.jsp"));
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      verify(request).getSession(eq(false));
      verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
      verify(dbConnection, atLeast(1)).getConnection();
      mockDBConnection.verify(DBConnection::getInstance, atLeast(1));
    }
  }

  /**
   * Test {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#next()} return {@code true}.</li>
   *   <li>Then calls {@link Connection#prepareStatement(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenResultSetNextReturnTrue_thenCallsPrepareStatement()
      throws IOException, SQLException, ServletException {
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
      MessageServlet messageServlet = new MessageServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(1);
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
      when(request.getSession(anyBoolean())).thenReturn(httpSession);

      // Act
      messageServlet.doPost(request, mock(HttpServletResponse.class));

      // Assert
      verify(connection, atLeast(1)).prepareStatement(Mockito.<String>any());
      verify(preparedStatement, atLeast(1)).executeQuery();
      verify(preparedStatement, atLeast(1)).setInt(eq(1), eq(1));
      verify(resultSet, atLeast(1)).getInt(eq("user_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(eq("delete"));
      verify(request).getRequestDispatcher(eq("WEB-INF/message.jsp"));
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      verify(request).getSession(eq(false));
      verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
      verify(dbConnection, atLeast(1)).getConnection();
      mockDBConnection.verify(DBConnection::getInstance, atLeast(1));
    }
  }
}
