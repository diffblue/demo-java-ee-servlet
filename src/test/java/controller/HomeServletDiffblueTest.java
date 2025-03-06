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

public class HomeServletDiffblueTest {
  /**
   * Test new {@link HomeServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link HomeServlet}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void HomeServlet.<init>()"})
  public void testNewHomeServlet() {
    // Arrange, Act and Assert
    assertNull((new HomeServlet()).getServletConfig());
  }

  /**
   * Test {@link HomeServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link RequestDispatcher} {@link RequestDispatcher#forward(ServletRequest, ServletResponse)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link HomeServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void HomeServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenRequestDispatcherForwardDoesNothing() throws IOException, SQLException, ServletException {
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
      HomeServlet homeServlet = new HomeServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getSession(anyBoolean())).thenReturn(httpSession);

      // Act
      homeServlet.doGet(request, mock(HttpServletResponse.class));

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM post ORDER BY post_time DESC;"));
      verify(preparedStatement).executeQuery();
      verify(resultSet, atLeast(1)).getInt(Mockito.<String>any());
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request).getRequestDispatcher(eq("WEB-INF/home.jsp"));
      verify(request).setAttribute(eq("posts"), isA(Object.class));
      verify(request).getSession(eq(false));
      verify(httpSession).getAttribute(eq("user_id"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link HomeServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then throw {@link ServletException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HomeServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void HomeServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_thenThrowServletException() throws IOException, SQLException, ServletException {
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
      HomeServlet homeServlet = new HomeServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doThrow(new ServletException("An error occurred")).when(requestDispatcher)
          .forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getSession(anyBoolean())).thenReturn(httpSession);

      // Act and Assert
      assertThrows(ServletException.class, () -> homeServlet.doGet(request, mock(HttpServletResponse.class)));
      verify(connection).prepareStatement(eq("SELECT * FROM post ORDER BY post_time DESC;"));
      verify(preparedStatement).executeQuery();
      verify(resultSet, atLeast(1)).getInt(Mockito.<String>any());
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request).getRequestDispatcher(eq("WEB-INF/home.jsp"));
      verify(request).setAttribute(eq("posts"), isA(Object.class));
      verify(request).getSession(eq(false));
      verify(httpSession).getAttribute(eq("user_id"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link HomeServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given empty string.</li>
   *   <li>When {@link HttpServletRequest} {@link ServletRequest#getParameter(String)} return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link HomeServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void HomeServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenEmptyString_whenHttpServletRequestGetParameterReturnEmptyString()
      throws IOException, ServletException {
    // Arrange
    HomeServlet homeServlet = new HomeServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(Mockito.<String>any())).thenReturn("");
    when(request.getSession(anyBoolean())).thenReturn(httpSession);

    // Act
    homeServlet.doPost(request, mock(HttpServletResponse.class));

    // Assert
    verify(request).getParameter(eq("post"));
    verify(request).getSession(eq(false));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link HomeServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link HttpSession} {@link HttpSession#getAttribute(String)} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HomeServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void HomeServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenHttpSessionGetAttributeReturnNull() throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      mockDBConnection.when(DBConnection::getInstance).thenReturn(mock(DBConnection.class));
      HomeServlet homeServlet = new HomeServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getSession(anyBoolean())).thenReturn(httpSession);
      HttpServletResponse response = mock(HttpServletResponse.class);
      doNothing().when(response).sendRedirect(Mockito.<String>any());

      // Act
      homeServlet.doPost(request, response);

      // Assert
      verify(request).getSession(eq(false));
      verify(response).sendRedirect(eq("login"));
      verify(httpSession).getAttribute(eq("user_id"));
    }
  }

  /**
   * Test {@link HomeServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link HttpSession} {@link HttpSession#getAttribute(String)} return {@code null}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HomeServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void HomeServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenHttpSessionGetAttributeReturnNull_thenThrowIOException()
      throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      mockDBConnection.when(DBConnection::getInstance).thenReturn(mock(DBConnection.class));
      HomeServlet homeServlet = new HomeServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getSession(anyBoolean())).thenReturn(httpSession);
      HttpServletResponse response = mock(HttpServletResponse.class);
      doThrow(new IOException("login")).when(response).sendRedirect(Mockito.<String>any());

      // Act and Assert
      assertThrows(IOException.class, () -> homeServlet.doPost(request, response));
      verify(request).getSession(eq(false));
      verify(response).sendRedirect(eq("login"));
      verify(httpSession).getAttribute(eq("user_id"));
    }
  }

  /**
   * Test {@link HomeServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#executeUpdate()} return one.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HomeServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void HomeServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenPreparedStatementExecuteUpdateReturnOne_thenThrowIOException()
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
      HomeServlet homeServlet = new HomeServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(1);
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
      when(request.getSession(anyBoolean())).thenReturn(httpSession);
      HttpServletResponse response = mock(HttpServletResponse.class);
      doThrow(new IOException("login")).when(response).sendRedirect(Mockito.<String>any());

      // Act and Assert
      assertThrows(IOException.class, () -> homeServlet.doPost(request, response));
      verify(connection).prepareStatement(eq("INSERT INTO post(user_id, body) VALUES (?,?);"));
      verify(preparedStatement).executeUpdate();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(preparedStatement).setString(eq(2), eq("Parameter"));
      verify(request).getParameter(eq("post"));
      verify(request).getSession(eq(false));
      verify(response).sendRedirect(eq("home"));
      verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link HomeServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then calls {@link Connection#prepareStatement(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HomeServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void HomeServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_thenCallsPrepareStatement() throws IOException, SQLException, ServletException {
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
      HomeServlet homeServlet = new HomeServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(1);
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
      when(request.getSession(anyBoolean())).thenReturn(httpSession);
      HttpServletResponse response = mock(HttpServletResponse.class);
      doNothing().when(response).sendRedirect(Mockito.<String>any());

      // Act
      homeServlet.doPost(request, response);

      // Assert
      verify(connection).prepareStatement(eq("INSERT INTO post(user_id, body) VALUES (?,?);"));
      verify(preparedStatement).executeUpdate();
      verify(preparedStatement).setInt(eq(1), eq(1));
      verify(preparedStatement).setString(eq(2), eq("Parameter"));
      verify(request).getParameter(eq("post"));
      verify(request).getSession(eq(false));
      verify(response).sendRedirect(eq("home"));
      verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }
}
