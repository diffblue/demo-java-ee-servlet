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

public class LoginServletDiffblueTest {
  /**
   * Test new {@link LoginServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link LoginServlet}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LoginServlet.<init>()"})
  public void testNewLoginServlet() {
    // Arrange, Act and Assert
    assertNull((new LoginServlet()).getServletConfig());
  }

  /**
   * Test {@link LoginServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link IOException#IOException(String)} with {@code user_id}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoginServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LoginServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenIOExceptionWithUserId_thenThrowIOException() throws IOException, ServletException {
    // Arrange
    LoginServlet loginServlet = new LoginServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doThrow(new IOException("user_id")).when(response).sendRedirect(Mockito.<String>any());

    // Act and Assert
    assertThrows(IOException.class, () -> loginServlet.doGet(request, response));
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("home"));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link LoginServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link RequestDispatcher} {@link RequestDispatcher#forward(ServletRequest, ServletResponse)} does nothing.</li>
   *   <li>Then calls {@link RequestDispatcher#forward(ServletRequest, ServletResponse)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoginServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LoginServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenRequestDispatcherForwardDoesNothing_thenCallsForward()
      throws IOException, ServletException {
    // Arrange
    LoginServlet loginServlet = new LoginServlet();
    RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
    doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);

    // Act
    loginServlet.doGet(request, mock(HttpServletResponse.class));

    // Assert
    verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
    verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
    verify(request).getSession(eq(false));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link LoginServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>When {@link HttpServletResponse} {@link HttpServletResponse#sendRedirect(String)} does nothing.</li>
   *   <li>Then calls {@link HttpServletResponse#sendRedirect(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoginServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LoginServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_whenHttpServletResponseSendRedirectDoesNothing_thenCallsSendRedirect()
      throws IOException, ServletException {
    // Arrange
    LoginServlet loginServlet = new LoginServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doNothing().when(response).sendRedirect(Mockito.<String>any());

    // Act
    loginServlet.doGet(request, response);

    // Assert
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("home"));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link HttpServletRequest} {@link ServletRequest#getParameter(String)} return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LoginServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenEmptyString_whenHttpServletRequestGetParameterReturnEmptyString()
      throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      mockDBConnection.when(DBConnection::getInstance).thenReturn(mock(DBConnection.class));
      LoginServlet loginServlet = new LoginServlet();
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("");
      doNothing().when(request).removeAttribute(Mockito.<String>any());

      // Act
      loginServlet.doPost(request, mock(HttpServletResponse.class));

      // Assert
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
      verify(request).removeAttribute(eq("msg"));
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    }
  }

  /**
   * Test {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link IOException#IOException(String)} with {@code email}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LoginServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenIOExceptionWithEmail_thenThrowIOException()
      throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenReturn(1);
      when(resultSet.getString(Mockito.<String>any())).thenReturn("Parameter");
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      LoginServlet loginServlet = new LoginServlet();
      HttpSession httpSession = mock(HttpSession.class);
      doNothing().when(httpSession).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      doNothing().when(httpSession).setMaxInactiveInterval(anyInt());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getSession(anyBoolean())).thenReturn(httpSession);
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
      doNothing().when(request).removeAttribute(Mockito.<String>any());
      HttpServletResponse response = mock(HttpServletResponse.class);
      doThrow(new IOException("email")).when(response).sendRedirect(Mockito.<String>any());

      // Act and Assert
      assertThrows(IOException.class, () -> loginServlet.doPost(request, response));
      verify(connection, atLeast(1)).prepareStatement(Mockito.<String>any());
      verify(preparedStatement, atLeast(1)).executeQuery();
      verify(preparedStatement, atLeast(1)).setString(anyInt(), eq("Parameter"));
      verify(resultSet).getInt(eq("user_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).removeAttribute(eq("msg"));
      verify(request).getSession(eq(true));
      verify(response).sendRedirect(eq("home"));
      verify(httpSession).setAttribute(eq("user_id"), isA(Object.class));
      verify(httpSession).setMaxInactiveInterval(eq(1800));
      verify(dbConnection, atLeast(1)).getConnection();
      mockDBConnection.verify(DBConnection::getInstance, atLeast(1));
    }
  }

  /**
   * Test {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} return one.</li>
   *   <li>Then calls {@link ResultSet#getInt(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LoginServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenResultSetGetIntReturnOne_thenCallsGetInt()
      throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenReturn(1);
      when(resultSet.getString(Mockito.<String>any())).thenReturn("Parameter");
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      LoginServlet loginServlet = new LoginServlet();
      HttpSession httpSession = mock(HttpSession.class);
      doNothing().when(httpSession).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      doNothing().when(httpSession).setMaxInactiveInterval(anyInt());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getSession(anyBoolean())).thenReturn(httpSession);
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
      doNothing().when(request).removeAttribute(Mockito.<String>any());
      HttpServletResponse response = mock(HttpServletResponse.class);
      doNothing().when(response).sendRedirect(Mockito.<String>any());

      // Act
      loginServlet.doPost(request, response);

      // Assert
      verify(connection, atLeast(1)).prepareStatement(Mockito.<String>any());
      verify(preparedStatement, atLeast(1)).executeQuery();
      verify(preparedStatement, atLeast(1)).setString(anyInt(), eq("Parameter"));
      verify(resultSet).getInt(eq("user_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).removeAttribute(eq("msg"));
      verify(request).getSession(eq(true));
      verify(response).sendRedirect(eq("home"));
      verify(httpSession).setAttribute(eq("user_id"), isA(Object.class));
      verify(httpSession).setMaxInactiveInterval(eq(1800));
      verify(dbConnection, atLeast(1)).getConnection();
      mockDBConnection.verify(DBConnection::getInstance, atLeast(1));
    }
  }

  /**
   * Test {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getString(String)} return {@code String}.</li>
   *   <li>Then calls {@link RequestDispatcher#forward(ServletRequest, ServletResponse)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LoginServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenResultSetGetStringReturnString_thenCallsForward()
      throws IOException, SQLException, ServletException {
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
      LoginServlet loginServlet = new LoginServlet();
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
      doNothing().when(request).removeAttribute(Mockito.<String>any());

      // Act
      loginServlet.doPost(request, mock(HttpServletResponse.class));

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE email = ? AND password = ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setString(anyInt(), eq("Parameter"));
      verify(resultSet).getString(eq("email"));
      verify(resultSet).next();
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
      verify(request).removeAttribute(eq("msg"));
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#next()} return {@code false}.</li>
   *   <li>Then calls {@link RequestDispatcher#forward(ServletRequest, ServletResponse)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LoginServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenResultSetNextReturnFalse_thenCallsForward()
      throws IOException, SQLException, ServletException {
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
      LoginServlet loginServlet = new LoginServlet();
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
      doNothing().when(request).removeAttribute(Mockito.<String>any());

      // Act
      loginServlet.doPost(request, mock(HttpServletResponse.class));

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE email = ? AND password = ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setString(anyInt(), eq("Parameter"));
      verify(resultSet).next();
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
      verify(request).removeAttribute(eq("msg"));
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then throw {@link ServletException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LoginServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_thenThrowServletException() throws IOException, SQLException, ServletException {
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
      LoginServlet loginServlet = new LoginServlet();
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doThrow(new ServletException("An error occurred")).when(requestDispatcher)
          .forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
      doNothing().when(request).removeAttribute(Mockito.<String>any());

      // Act and Assert
      assertThrows(ServletException.class, () -> loginServlet.doPost(request, mock(HttpServletResponse.class)));
      verify(connection).prepareStatement(eq("SELECT * FROM user WHERE email = ? AND password = ?"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement, atLeast(1)).setString(anyInt(), eq("Parameter"));
      verify(resultSet).getString(eq("email"));
      verify(resultSet).next();
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
      verify(request).removeAttribute(eq("msg"));
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>When {@link HttpServletResponse} {@link HttpServletResponse#sendRedirect(String)} does nothing.</li>
   *   <li>Then calls {@link HttpServletRequest#getSession(boolean)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LoginServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_whenHttpServletResponseSendRedirectDoesNothing_thenCallsGetSession()
      throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getString(Mockito.<String>any())).thenReturn("Parameter");
      when(resultSet.next()).thenReturn(true).thenReturn(false).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      LoginServlet loginServlet = new LoginServlet();
      HttpSession httpSession = mock(HttpSession.class);
      doNothing().when(httpSession).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      doNothing().when(httpSession).setMaxInactiveInterval(anyInt());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getSession(anyBoolean())).thenReturn(httpSession);
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
      doNothing().when(request).removeAttribute(Mockito.<String>any());
      HttpServletResponse response = mock(HttpServletResponse.class);
      doNothing().when(response).sendRedirect(Mockito.<String>any());

      // Act
      loginServlet.doPost(request, response);

      // Assert
      verify(connection, atLeast(1)).prepareStatement(Mockito.<String>any());
      verify(preparedStatement, atLeast(1)).executeQuery();
      verify(preparedStatement, atLeast(1)).setString(anyInt(), eq("Parameter"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).removeAttribute(eq("msg"));
      verify(request).getSession(eq(true));
      verify(response).sendRedirect(eq("home"));
      verify(httpSession).setAttribute(eq("user_id"), isA(Object.class));
      verify(httpSession).setMaxInactiveInterval(eq(1800));
      verify(dbConnection, atLeast(1)).getConnection();
      mockDBConnection.verify(DBConnection::getInstance, atLeast(1));
    }
  }
}
