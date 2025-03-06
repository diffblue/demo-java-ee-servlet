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

public class ProfileServletDiffblueTest {
  /**
   * Test new {@link ProfileServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ProfileServlet}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ProfileServlet.<init>()"})
  public void testNewProfileServlet() {
    // Arrange, Act and Assert
    assertNull((new ProfileServlet()).getServletConfig());
  }

  /**
   * Test {@link ProfileServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link IOException#IOException(String)} with {@code login}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProfileServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ProfileServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenIOExceptionWithLogin_thenThrowIOException()
      throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      mockDBConnection.when(DBConnection::getInstance).thenReturn(mock(DBConnection.class));
      ProfileServlet profileServlet = new ProfileServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getSession(anyBoolean())).thenReturn(httpSession);
      HttpServletResponse response = mock(HttpServletResponse.class);
      doThrow(new IOException("login")).when(response).sendRedirect(Mockito.<String>any());

      // Act and Assert
      assertThrows(IOException.class, () -> profileServlet.doGet(request, response));
      verify(request).getSession(eq(false));
      verify(response).sendRedirect(eq("login"));
      verify(httpSession).getAttribute(eq("user_id"));
    }
  }

  /**
   * Test {@link ProfileServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>When {@link HttpServletRequest} {@link ServletRequest#getParameter(String)} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProfileServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ProfileServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenNull_whenHttpServletRequestGetParameterReturnNull() throws IOException, ServletException {
    // Arrange
    ProfileServlet profileServlet = new ProfileServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
    doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(Mockito.<String>any())).thenReturn(null);
    when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
    doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    when(request.getSession(anyBoolean())).thenReturn(httpSession);

    // Act
    profileServlet.doGet(request, mock(HttpServletResponse.class));

    // Assert
    verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
    verify(request, atLeast(1)).getParameter(Mockito.<String>any());
    verify(request).getRequestDispatcher(eq("WEB-INF/profile.jsp"));
    verify(request).setAttribute(eq("posts"), isA(Object.class));
    verify(request).getSession(eq(false));
    verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link ProfileServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#executeUpdate()} return one.</li>
   *   <li>Then calls {@link PreparedStatement#executeUpdate()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProfileServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ProfileServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenPreparedStatementExecuteUpdateReturnOne_thenCallsExecuteUpdate()
      throws IOException, SQLException, ServletException {
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
      ProfileServlet profileServlet = new ProfileServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getParameter(Mockito.<String>any())).thenReturn("42");
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getSession(anyBoolean())).thenReturn(httpSession);

      // Act
      profileServlet.doGet(request, mock(HttpServletResponse.class));

      // Assert
      verify(connection).prepareStatement(eq("DELETE FROM post WHERE post_id = ?;"));
      verify(preparedStatement).executeUpdate();
      verify(preparedStatement).setInt(eq(1), eq(42));
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).getRequestDispatcher(eq("WEB-INF/post-update.jsp"));
      verify(request).setAttribute(eq("post_id"), isA(Object.class));
      verify(request).getSession(eq(false));
      verify(httpSession).getAttribute(eq("user_id"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link ProfileServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link ResultSet} {@link ResultSet#getInt(String)} return one.</li>
   *   <li>Then calls {@link PreparedStatement#executeQuery()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProfileServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ProfileServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenResultSetGetIntReturnOne_thenCallsExecuteQuery()
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
      ProfileServlet profileServlet = new ProfileServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(42);
      RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
      doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getParameter(Mockito.<String>any())).thenReturn(null);
      when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
      doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
      when(request.getSession(anyBoolean())).thenReturn(httpSession);

      // Act
      profileServlet.doGet(request, mock(HttpServletResponse.class));

      // Assert
      verify(connection).prepareStatement(eq("SELECT * FROM post WHERE user_id = ? ORDER BY post_time DESC;"));
      verify(preparedStatement).executeQuery();
      verify(preparedStatement).setInt(eq(1), eq(42));
      verify(resultSet, atLeast(1)).getInt(Mockito.<String>any());
      verify(resultSet, atLeast(1)).getString(Mockito.<String>any());
      verify(resultSet, atLeast(1)).next();
      verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
      verify(request, atLeast(1)).getParameter(Mockito.<String>any());
      verify(request).getRequestDispatcher(eq("WEB-INF/profile.jsp"));
      verify(request).setAttribute(eq("posts"), isA(Object.class));
      verify(request).getSession(eq(false));
      verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
      verify(dbConnection).getConnection();
      mockDBConnection.verify(DBConnection::getInstance);
    }
  }

  /**
   * Test {@link ProfileServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>When {@link HttpServletResponse} {@link HttpServletResponse#sendRedirect(String)} does nothing.</li>
   *   <li>Then calls {@link HttpServletResponse#sendRedirect(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProfileServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ProfileServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_whenHttpServletResponseSendRedirectDoesNothing_thenCallsSendRedirect()
      throws IOException, SQLException, ServletException {
    try (MockedStatic<DBConnection> mockDBConnection = mockStatic(DBConnection.class)) {

      // Arrange
      mockDBConnection.when(DBConnection::getInstance).thenReturn(mock(DBConnection.class));
      ProfileServlet profileServlet = new ProfileServlet();
      HttpSession httpSession = mock(HttpSession.class);
      when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(request.getSession(anyBoolean())).thenReturn(httpSession);
      HttpServletResponse response = mock(HttpServletResponse.class);
      doNothing().when(response).sendRedirect(Mockito.<String>any());

      // Act
      profileServlet.doGet(request, response);

      // Assert
      verify(request).getSession(eq(false));
      verify(response).sendRedirect(eq("login"));
      verify(httpSession).getAttribute(eq("user_id"));
    }
  }

  /**
   * Test {@link ProfileServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link IOException#IOException(String)} with {@code login}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProfileServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ProfileServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenIOExceptionWithLogin_thenThrowIOException() throws IOException, ServletException {
    // Arrange
    ProfileServlet profileServlet = new ProfileServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doThrow(new IOException("login")).when(response).sendRedirect(Mockito.<String>any());

    // Act and Assert
    assertThrows(IOException.class, () -> profileServlet.doPost(request, response));
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link ProfileServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@code Parameter}.</li>
   *   <li>Then calls {@link ServletRequest#getParameter(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProfileServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ProfileServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenParameter_thenCallsGetParameter() throws IOException, ServletException {
    // Arrange
    ProfileServlet profileServlet = new ProfileServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
    when(request.getSession(anyBoolean())).thenReturn(httpSession);

    // Act
    profileServlet.doPost(request, mock(HttpServletResponse.class));

    // Assert
    verify(request).getParameter(eq("type"));
    verify(request).getSession(eq(false));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link ProfileServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>When {@link HttpServletResponse} {@link HttpServletResponse#sendRedirect(String)} does nothing.</li>
   *   <li>Then calls {@link HttpServletResponse#sendRedirect(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProfileServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void ProfileServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_whenHttpServletResponseSendRedirectDoesNothing_thenCallsSendRedirect()
      throws IOException, ServletException {
    // Arrange
    ProfileServlet profileServlet = new ProfileServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doNothing().when(response).sendRedirect(Mockito.<String>any());

    // Act
    profileServlet.doPost(request, response);

    // Assert
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(httpSession).getAttribute(eq("user_id"));
  }
}
