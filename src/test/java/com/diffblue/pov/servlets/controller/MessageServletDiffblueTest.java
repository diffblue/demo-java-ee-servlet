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
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link HttpServletRequest} {@link ServletRequest#getParameter(String)} return {@code 42}.</li>
   *   <li>Then calls {@link RequestDispatcher#forward(ServletRequest, ServletResponse)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_given42_whenHttpServletRequestGetParameterReturn42_thenCallsForward()
      throws IOException, ServletException {
    // Arrange
    MessageServlet messageServlet = new MessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(1);
    RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
    doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
    doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    when(request.getParameter(Mockito.<String>any())).thenReturn("42");
    when(request.getSession(anyBoolean())).thenReturn(httpSession);

    // Act
    messageServlet.doGet(request, mock(HttpServletResponse.class));

    // Assert
    verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
    verify(request, atLeast(1)).getParameter(eq("delete"));
    verify(request).getRequestDispatcher(eq("WEB-INF/message.jsp"));
    verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), isA(Object.class));
    verify(request).getSession(eq(false));
    verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
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
   *   <li>Given {@code null}.</li>
   *   <li>When {@link HttpServletRequest} {@link ServletRequest#getParameter(String)} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenNull_whenHttpServletRequestGetParameterReturnNull() throws IOException, ServletException {
    // Arrange
    MessageServlet messageServlet = new MessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(1);
    RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
    doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
    doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    when(request.getParameter(Mockito.<String>any())).thenReturn(null);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);

    // Act
    messageServlet.doGet(request, mock(HttpServletResponse.class));

    // Assert
    verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
    verify(request).getParameter(eq("delete"));
    verify(request).getRequestDispatcher(eq("WEB-INF/message.jsp"));
    verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), isA(Object.class));
    verify(request).getSession(eq(false));
    verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link NumberFormatException#NumberFormatException(String)} with {@code login}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenNumberFormatExceptionWithLogin() throws IOException, ServletException {
    // Arrange
    MessageServlet messageServlet = new MessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doThrow(new NumberFormatException("login")).when(response).sendRedirect(Mockito.<String>any());

    // Act and Assert
    assertThrows(NumberFormatException.class, () -> messageServlet.doGet(request, response));
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@code Parameter}.</li>
   *   <li>When {@link HttpServletRequest} {@link ServletRequest#getParameter(String)} return {@code Parameter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenParameter_whenHttpServletRequestGetParameterReturnParameter()
      throws IOException, ServletException {
    // Arrange
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
    verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
    verify(request, atLeast(1)).getParameter(eq("delete"));
    verify(request).getRequestDispatcher(eq("WEB-INF/message.jsp"));
    verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), isA(Object.class));
    verify(request).getSession(eq(false));
    verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link RequestDispatcher} {@link RequestDispatcher#forward(ServletRequest, ServletResponse)} throw {@link NumberFormatException#NumberFormatException(String)} with {@code UU}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenRequestDispatcherForwardThrowNumberFormatExceptionWithUu()
      throws IOException, ServletException {
    // Arrange
    MessageServlet messageServlet = new MessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(1);
    RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
    doThrow(new NumberFormatException("UU")).when(requestDispatcher)
        .forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
    doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
    when(request.getSession(anyBoolean())).thenReturn(httpSession);

    // Act and Assert
    assertThrows(NumberFormatException.class, () -> messageServlet.doGet(request, mock(HttpServletResponse.class)));
    verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
    verify(request, atLeast(1)).getParameter(eq("delete"));
    verify(request).getRequestDispatcher(eq("WEB-INF/message.jsp"));
    verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), isA(Object.class));
    verify(request).getSession(eq(false));
    verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>When {@link HttpServletResponse} {@link HttpServletResponse#sendRedirect(String)} does nothing.</li>
   *   <li>Then calls {@link HttpServletResponse#sendRedirect(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_whenHttpServletResponseSendRedirectDoesNothing_thenCallsSendRedirect()
      throws IOException, ServletException {
    // Arrange
    MessageServlet messageServlet = new MessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doNothing().when(response).sendRedirect(Mockito.<String>any());

    // Act
    messageServlet.doGet(request, response);

    // Assert
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>When {@link HttpServletRequest} {@link ServletRequest#getParameter(String)} return {@code 42}.</li>
   *   <li>Then calls {@link RequestDispatcher#forward(ServletRequest, ServletResponse)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_given42_whenHttpServletRequestGetParameterReturn42_thenCallsForward()
      throws IOException, ServletException {
    // Arrange
    MessageServlet messageServlet = new MessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(1);
    RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
    doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
    doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    when(request.getParameter(Mockito.<String>any())).thenReturn("42");
    when(request.getSession(anyBoolean())).thenReturn(httpSession);

    // Act
    messageServlet.doPost(request, mock(HttpServletResponse.class));

    // Assert
    verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
    verify(request, atLeast(1)).getParameter(eq("delete"));
    verify(request).getRequestDispatcher(eq("WEB-INF/message.jsp"));
    verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), isA(Object.class));
    verify(request).getSession(eq(false));
    verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
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
   *   <li>Given {@code null}.</li>
   *   <li>When {@link HttpServletRequest} {@link ServletRequest#getParameter(String)} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenNull_whenHttpServletRequestGetParameterReturnNull() throws IOException, ServletException {
    // Arrange
    MessageServlet messageServlet = new MessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(1);
    RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
    doNothing().when(requestDispatcher).forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
    doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    when(request.getParameter(Mockito.<String>any())).thenReturn(null);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);

    // Act
    messageServlet.doPost(request, mock(HttpServletResponse.class));

    // Assert
    verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
    verify(request).getParameter(eq("delete"));
    verify(request).getRequestDispatcher(eq("WEB-INF/message.jsp"));
    verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), isA(Object.class));
    verify(request).getSession(eq(false));
    verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link NumberFormatException#NumberFormatException(String)} with {@code login}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenNumberFormatExceptionWithLogin() throws IOException, ServletException {
    // Arrange
    MessageServlet messageServlet = new MessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doThrow(new NumberFormatException("login")).when(response).sendRedirect(Mockito.<String>any());

    // Act and Assert
    assertThrows(NumberFormatException.class, () -> messageServlet.doPost(request, response));
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@code Parameter}.</li>
   *   <li>When {@link HttpServletRequest} {@link ServletRequest#getParameter(String)} return {@code Parameter}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenParameter_whenHttpServletRequestGetParameterReturnParameter()
      throws IOException, ServletException {
    // Arrange
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
    verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
    verify(request, atLeast(1)).getParameter(eq("delete"));
    verify(request).getRequestDispatcher(eq("WEB-INF/message.jsp"));
    verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), isA(Object.class));
    verify(request).getSession(eq(false));
    verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link RequestDispatcher} {@link RequestDispatcher#forward(ServletRequest, ServletResponse)} throw {@link NumberFormatException#NumberFormatException(String)} with {@code UU}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenRequestDispatcherForwardThrowNumberFormatExceptionWithUu()
      throws IOException, ServletException {
    // Arrange
    MessageServlet messageServlet = new MessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(1);
    RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
    doThrow(new NumberFormatException("UU")).when(requestDispatcher)
        .forward(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(requestDispatcher);
    doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
    when(request.getSession(anyBoolean())).thenReturn(httpSession);

    // Act and Assert
    assertThrows(NumberFormatException.class, () -> messageServlet.doPost(request, mock(HttpServletResponse.class)));
    verify(requestDispatcher).forward(isA(ServletRequest.class), isA(ServletResponse.class));
    verify(request, atLeast(1)).getParameter(eq("delete"));
    verify(request).getRequestDispatcher(eq("WEB-INF/message.jsp"));
    verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), isA(Object.class));
    verify(request).getSession(eq(false));
    verify(httpSession, atLeast(1)).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>When {@link HttpServletResponse} {@link HttpServletResponse#sendRedirect(String)} does nothing.</li>
   *   <li>Then calls {@link HttpServletResponse#sendRedirect(String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MessageServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void MessageServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_whenHttpServletResponseSendRedirectDoesNothing_thenCallsSendRedirect()
      throws IOException, ServletException {
    // Arrange
    MessageServlet messageServlet = new MessageServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doNothing().when(response).sendRedirect(Mockito.<String>any());

    // Act
    messageServlet.doPost(request, response);

    // Assert
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(httpSession).getAttribute(eq("user_id"));
  }
}
