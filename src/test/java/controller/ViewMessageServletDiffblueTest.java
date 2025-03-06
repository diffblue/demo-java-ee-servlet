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

public class ViewMessageServletDiffblueTest {
  /**
   * Test new {@link ViewMessageServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ViewMessageServlet}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.<init>()"})
  public void testNewViewMessageServlet() {
    // Arrange, Act and Assert
    assertNull((new ViewMessageServlet()).getServletConfig());
  }

  /**
   * Test {@link ViewMessageServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link RequestDispatcher} {@link RequestDispatcher#forward(ServletRequest, ServletResponse)} does nothing.</li>
   *   <li>Then calls {@link Connection#prepareStatement(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ViewMessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenRequestDispatcherForwardDoesNothing_thenCallsPrepareStatement()
      throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenReturn(1);
      when(resultSet.getString(Mockito.<String>any())).thenReturn("String");
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      when(preparedStatement.executeUpdate()).thenReturn(1);
      doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      ViewMessageServlet viewMessageServlet = new ViewMessageServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(-99);
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("42");
      when(request.getSession(anyBoolean())).thenReturn(httpSession);

      // Act
      viewMessageServlet.doGet(request, mock(HttpServletResponse.class));

      // Assert
      verify(connection, atLeast(1)).prepareStatement(Mockito.<String>any());
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).executeUpdate();
      verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
      verify(resultSet, atLeast(1)).getInt(eq("chat_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).getRequestDispatcher(eq("WEB-INF/view-message.jsp"));
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      verify(request).getSession(eq(false));
      verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
      verify(dbConnection, atLeast(1)).getConnection();
      mockDBConnection.verify(DBConnection::getInstance, atLeast(1));
    }
  }

  /**
   * Test {@link ViewMessageServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then throw {@link ServletException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ViewMessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_thenThrowServletException() throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      ResultSet resultSet = mock(ResultSet.class);
      when(resultSet.getInt(Mockito.<String>any())).thenReturn(1);
      when(resultSet.getString(Mockito.<String>any())).thenReturn("String");
      when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      when(preparedStatement.executeQuery()).thenReturn(resultSet);
      when(preparedStatement.executeUpdate()).thenReturn(1);
      doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      ViewMessageServlet viewMessageServlet = new ViewMessageServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(-99);
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doThrow(new ServletException("An error occurred")).when(requestDispatcher)
          .forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("42");
      when(request.getSession(anyBoolean())).thenReturn(httpSession);

      // Act and Assert
      assertThrows(ServletException.class, () -> viewMessageServlet.doGet(request, mock(HttpServletResponse.class)));
      verify(connection, atLeast(1)).prepareStatement(Mockito.<String>any());
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).executeUpdate();
      verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
      verify(resultSet, atLeast(1)).getInt(eq("chat_id"));
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).getRequestDispatcher(eq("WEB-INF/view-message.jsp"));
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      verify(request).getSession(eq(false));
      verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
      verify(dbConnection, atLeast(1)).getConnection();
      mockDBConnection.verify(DBConnection::getInstance, atLeast(1));
    }
  }

  /**
   * Test {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link HttpServletRequest} {@link ServletRequest#getParameter(String)} return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenEmptyString_whenHttpServletRequestGetParameterReturnEmptyString()
      throws IOException, ServletException {
    // Arrange
    ViewMessageServlet viewMessageServlet = new ViewMessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(Mockito.<String>any())).thenReturn("");
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doNothing().when(response).sendRedirect(Mockito.<String>any());

    // Act
    viewMessageServlet.doPost(request, response);

    // Assert
    verify(request, atLeast(1)).getParameter(Mockito.<String>any());
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("view-message?id="));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link HttpSession} {@link HttpSession#getAttribute(String)} return {@code 42}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenHttpSessionGetAttributeReturn42() throws IOException, ServletException {
    // Arrange
    ViewMessageServlet viewMessageServlet = new ViewMessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("42");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doNothing().when(response).sendRedirect(Mockito.<String>any());

    // Act
    viewMessageServlet.doPost(request, response);

    // Assert
    verify(request, atLeast(1)).getParameter(Mockito.<String>any());
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("view-message?id=Parameter"));
    verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link HttpSession} {@link HttpSession#getAttribute(String)} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenHttpSessionGetAttributeReturnNull() throws IOException, ServletException {
    // Arrange
    ViewMessageServlet viewMessageServlet = new ViewMessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doNothing().when(response).sendRedirect(Mockito.<String>any());

    // Act
    viewMessageServlet.doPost(request, response);

    // Assert
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link HttpSession} {@link HttpSession#getAttribute(String)} return {@code null}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenHttpSessionGetAttributeReturnNull_thenThrowIOException()
      throws IOException, ServletException {
    // Arrange
    ViewMessageServlet viewMessageServlet = new ViewMessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doThrow(new IOException("login")).when(response).sendRedirect(Mockito.<String>any());

    // Act and Assert
    assertThrows(IOException.class, () -> viewMessageServlet.doPost(request, response));
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link IOException#IOException(String)} with {@code login}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenIOExceptionWithLogin_thenThrowIOException() throws IOException, ServletException {
    // Arrange
    ViewMessageServlet viewMessageServlet = new ViewMessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doThrow(new IOException("login")).when(response).sendRedirect(Mockito.<String>any());

    // Act and Assert
    assertThrows(IOException.class, () -> viewMessageServlet.doPost(request, response));
    verify(request, atLeast(1)).getParameter(Mockito.<String>any());
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("view-message?id=Parameter"));
    verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@code Parameter}.</li>
   *   <li>When {@link HttpServletRequest} {@link ServletRequest#getParameter(String)} return {@code Parameter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenParameter_whenHttpServletRequestGetParameterReturnParameter()
      throws IOException, ServletException {
    // Arrange
    ViewMessageServlet viewMessageServlet = new ViewMessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doNothing().when(response).sendRedirect(Mockito.<String>any());

    // Act
    viewMessageServlet.doPost(request, response);

    // Assert
    verify(request, atLeast(1)).getParameter(Mockito.<String>any());
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("view-message?id=Parameter"));
    verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#executeUpdate()} return one.</li>
   *   <li>Then calls {@link PreparedStatement#executeUpdate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenPreparedStatementExecuteUpdateReturnOne_thenCallsExecuteUpdate()
      throws IOException, SQLException, ServletException {
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
      ViewMessageServlet viewMessageServlet = new ViewMessageServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("42");
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getParameter(Mockito.<String>any())).thenReturn("42");
      when(request.getSession(anyBoolean())).thenReturn(httpSession);
      HttpServletResponse response = mock(HttpServletResponse.class);
      doNothing().when(response).sendRedirect(Mockito.<String>any());

      // Act
      viewMessageServlet.doPost(request, response);

      // Assert
      verify(connection).prepareStatement(eq("INSERT INTO message(from_user, to_user, message) VALUES (?,?,?);"));
      verify(preparedStatement).executeUpdate();
      verify(preparedStatement, atLeast(1)).setInt(anyInt(), eq(42));
      verify(preparedStatement).setString(eq(3), eq("42"));
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).getSession(eq(false));
      verify(response).sendRedirect(eq("view-message?id=42"));
      verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setInt(int, int)} throw {@link SQLException#SQLException()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ViewMessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ViewMessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenPreparedStatementSetIntThrowSQLException()
      throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      PreparedStatement preparedStatement = mock(PreparedStatement.class);
      doThrow(new SQLException()).when(preparedStatement).setInt(anyInt(), anyInt());
      Connection connection = mock(Connection.class);
      when(connection.prepareStatement(Mockito.<String>any())).thenReturn(preparedStatement);
      DBConnection dbConnection = mock(DBConnection.class);
      when(dbConnection.getConnection()).thenReturn(connection);
      mockDBConnection.when(DBConnection::getInstance).thenReturn(dbConnection);
      ViewMessageServlet viewMessageServlet = new ViewMessageServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("42");
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getParameter(Mockito.<String>any())).thenReturn("42");
      when(request.getSession(anyBoolean())).thenReturn(httpSession);
      HttpServletResponse response = mock(HttpServletResponse.class);
      doNothing().when(response).sendRedirect(Mockito.<String>any());

      // Act
      viewMessageServlet.doPost(request, response);

      // Assert
      verify(connection).prepareStatement(eq("INSERT INTO message(from_user, to_user, message) VALUES (?,?,?);"));
      verify(preparedStatement).setInt(eq(1), eq(42));
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).getSession(eq(false));
      verify(response).sendRedirect(eq("view-message?id=42"));
      verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }
}
