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

public class RegisterServletDiffblueTest {
  /**
   * Test new {@link RegisterServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link RegisterServlet}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.<init>()"})
  public void testNewRegisterServlet() {
    // Arrange, Act and Assert
    assertNull((new RegisterServlet()).getServletConfig());
  }

  /**
   * Test {@link RegisterServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link IOException#IOException(String)} with {@code user_id}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegisterServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenIOExceptionWithUserId_thenThrowIOException() throws IOException, ServletException {
    // Arrange
    RegisterServlet registerServlet = new RegisterServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doThrow(new IOException("user_id")).when(response).sendRedirect(Mockito.<String>any());

    // Act and Assert
    assertThrows(IOException.class, () -> registerServlet.doGet(request, response));
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("home"));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link RegisterServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link RequestDispatcher} {@link RequestDispatcher#forward(ServletRequest, ServletResponse)} does nothing.</li>
   *   <li>Then calls {@link RequestDispatcher#forward(ServletRequest, ServletResponse)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegisterServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenRequestDispatcherForwardDoesNothing_thenCallsForward()
      throws IOException, ServletException {
    // Arrange
    RegisterServlet registerServlet = new RegisterServlet();
    RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
    doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());

    // Act
    registerServlet.doGet(request, mock(HttpServletResponse.class));

    // Assert
    verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
    verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
    verify(request).setAttribute(eq("page"), isA(Object.class));
    verify(request).getSession(eq(false));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link RegisterServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>When {@link HttpServletResponse} {@link HttpServletResponse#sendRedirect(String)} does nothing.</li>
   *   <li>Then calls {@link HttpServletResponse#sendRedirect(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegisterServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_whenHttpServletResponseSendRedirectDoesNothing_thenCallsSendRedirect()
      throws IOException, ServletException {
    // Arrange
    RegisterServlet registerServlet = new RegisterServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doNothing().when(response).sendRedirect(Mockito.<String>any());

    // Act
    registerServlet.doGet(request, response);

    // Assert
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("home"));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link RegisterServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#execute()} return {@code true}.</li>
   *   <li>Then calls {@link Connection#prepareStatement(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegisterServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenPreparedStatementExecuteReturnTrue_thenCallsPrepareStatement()
      throws IOException, SQLException, ServletException {
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
      RegisterServlet registerServlet = new RegisterServlet();
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).removeAttribute(Mockito.<String>any());
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");

      // Act
      registerServlet.doPost(request, mock(HttpServletResponse.class));

      // Assert
      verify(connection)
          .prepareStatement(eq("INSERT INTO user(first_name, last_name, email, password) VALUES (?, ?, ?, ?);"));
      verify(preparedStatement).execute();
      verify(preparedStatement, atLeast(1)).setString(anyInt(), eq("Parameter"));
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
      verify(request, atLeast(1)).removeAttribute(Mockito.<String>any());
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link RegisterServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#execute()} return {@code true}.</li>
   *   <li>Then throw {@link ServletException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegisterServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenPreparedStatementExecuteReturnTrue_thenThrowServletException()
      throws IOException, SQLException, ServletException {
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
      RegisterServlet registerServlet = new RegisterServlet();
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doThrow(new ServletException("An error occurred")).when(requestDispatcher)
          .forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).removeAttribute(Mockito.<String>any());
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");

      // Act and Assert
      assertThrows(ServletException.class, () -> registerServlet.doPost(request, mock(HttpServletResponse.class)));
      verify(connection)
          .prepareStatement(eq("INSERT INTO user(first_name, last_name, email, password) VALUES (?, ?, ?, ?);"));
      verify(preparedStatement).execute();
      verify(preparedStatement, atLeast(1)).setString(anyInt(), eq("Parameter"));
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
      verify(request, atLeast(1)).removeAttribute(Mockito.<String>any());
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link RegisterServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link RequestDispatcher} {@link RequestDispatcher#forward(ServletRequest, ServletResponse)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegisterServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenRequestDispatcherForwardDoesNothing() throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      mockDBConnection.when(DBConnection::getInstance).thenReturn(mock(DBConnection.class));
      RegisterServlet registerServlet = new RegisterServlet();
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).removeAttribute(Mockito.<String>any());
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("");

      // Act
      registerServlet.doPost(request, mock(HttpServletResponse.class));

      // Assert
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
      verify(request, atLeast(1)).removeAttribute(Mockito.<String>any());
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    }
  }

  /**
   * Test {@link RegisterServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then throw {@link ServletException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegisterServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_thenThrowServletException() throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      mockDBConnection.when(DBConnection::getInstance).thenReturn(mock(DBConnection.class));
      RegisterServlet registerServlet = new RegisterServlet();
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doThrow(new ServletException("An error occurred")).when(requestDispatcher)
          .forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).removeAttribute(Mockito.<String>any());
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getParameter(Mockito.<String>any())).thenReturn("");

      // Act and Assert
      assertThrows(ServletException.class, () -> registerServlet.doPost(request, mock(HttpServletResponse.class)));
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
      verify(request, atLeast(1)).removeAttribute(Mockito.<String>any());
      verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    }
  }
}
