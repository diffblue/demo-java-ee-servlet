package com.diffblue.pov.servlets.controller;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

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
   *   <li>Given {@link RequestDispatcher} {@link RequestDispatcher#forward(ServletRequest, ServletResponse)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LoginServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenRequestDispatcherForwardDoesNothing() throws IOException, ServletException {
    // Arrange
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
    verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
    verify(request, atLeast(1)).getParameter(Mockito.<String>any());
    verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
    verify(request).removeAttribute(eq("msg"));
    verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
  }

  /**
   * Test {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link RequestDispatcher} {@link RequestDispatcher#forward(ServletRequest, ServletResponse)} does nothing.</li>
   * </ul>
   * <p>
   * Method under test: {@link LoginServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LoginServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenRequestDispatcherForwardDoesNothing2() throws IOException, ServletException {
    // Arrange
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
  public void testDoPost_thenThrowServletException() throws IOException, ServletException {
    // Arrange
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
    verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
    verify(request, atLeast(1)).getParameter(Mockito.<String>any());
    verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
    verify(request).removeAttribute(eq("msg"));
    verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
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
  public void testDoPost_thenThrowServletException2() throws IOException, ServletException {
    // Arrange
    LoginServlet loginServlet = new LoginServlet();
    RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
    doThrow(new ServletException("An error occurred")).when(requestDispatcher)
        .forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
    doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    when(request.getParameter(Mockito.<String>any())).thenReturn("");
    doNothing().when(request).removeAttribute(Mockito.<String>any());

    // Act and Assert
    assertThrows(ServletException.class, () -> loginServlet.doPost(request, mock(HttpServletResponse.class)));
    verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
    verify(request, atLeast(1)).getParameter(Mockito.<String>any());
    verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
    verify(request).removeAttribute(eq("msg"));
    verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
  }
}
