package com.diffblue.pov.servlets.controller;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class LogoutServletDiffblueTest {
  /**
   * Test new {@link LogoutServlet} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link LogoutServlet}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LogoutServlet.<init>()"})
  public void testNewLogoutServlet() {
    // Arrange, Act and Assert
    assertNull((new LogoutServlet()).getServletConfig());
  }

  /**
   * Test {@link LogoutServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link HttpSession} {@link HttpSession#getAttribute(String)} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LogoutServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LogoutServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenHttpSessionGetAttributeReturnNull() throws IOException, ServletException {
    // Arrange
    LogoutServlet logoutServlet = new LogoutServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doNothing().when(response).sendRedirect(Mockito.<String>any());

    // Act
    logoutServlet.doGet(request, response);

    // Assert
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link LogoutServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link HttpSession} {@link HttpSession#getAttribute(String)} return {@code null}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LogoutServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LogoutServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenHttpSessionGetAttributeReturnNull_thenThrowIOException()
      throws IOException, ServletException {
    // Arrange
    LogoutServlet logoutServlet = new LogoutServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doThrow(new IOException("login")).when(response).sendRedirect(Mockito.<String>any());

    // Act and Assert
    assertThrows(IOException.class, () -> logoutServlet.doGet(request, response));
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link LogoutServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link HttpSession} {@link HttpSession#getMaxInactiveInterval()} return three.</li>
   *   <li>Then calls {@link HttpServletResponse#setHeader(String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LogoutServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LogoutServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenHttpSessionGetMaxInactiveIntervalReturnThree_thenCallsSetHeader()
      throws IOException, ServletException {
    // Arrange
    LogoutServlet logoutServlet = new LogoutServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getMaxInactiveInterval()).thenReturn(3);
    doNothing().when(httpSession).removeAttribute(Mockito.<String>any());
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doNothing().when(response).sendRedirect(Mockito.<String>any());
    doNothing().when(response).setHeader(Mockito.<String>any(), Mockito.<String>any());

    // Act
    logoutServlet.doGet(request, response);

    // Assert
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(response, atLeast(1)).setHeader(Mockito.<String>any(), Mockito.<String>any());
    verify(httpSession).getAttribute(eq("user_id"));
    verify(httpSession).getMaxInactiveInterval();
    verify(httpSession).removeAttribute(eq("user_id"));
  }

  /**
   * Test {@link LogoutServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link HttpSession} {@link HttpSession#getMaxInactiveInterval()} return three.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LogoutServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LogoutServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenHttpSessionGetMaxInactiveIntervalReturnThree_thenThrowIOException()
      throws IOException, ServletException {
    // Arrange
    LogoutServlet logoutServlet = new LogoutServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getMaxInactiveInterval()).thenReturn(3);
    doNothing().when(httpSession).removeAttribute(Mockito.<String>any());
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doThrow(new IOException("login")).when(response).sendRedirect(Mockito.<String>any());
    doNothing().when(response).setHeader(Mockito.<String>any(), Mockito.<String>any());

    // Act and Assert
    assertThrows(IOException.class, () -> logoutServlet.doGet(request, response));
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(response, atLeast(1)).setHeader(Mockito.<String>any(), Mockito.<String>any());
    verify(httpSession).getAttribute(eq("user_id"));
    verify(httpSession).getMaxInactiveInterval();
    verify(httpSession).removeAttribute(eq("user_id"));
  }

  /**
   * Test {@link LogoutServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link HttpSession} {@link HttpSession#getAttribute(String)} return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LogoutServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LogoutServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenHttpSessionGetAttributeReturnNull() throws IOException, ServletException {
    // Arrange
    LogoutServlet logoutServlet = new LogoutServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doNothing().when(response).sendRedirect(Mockito.<String>any());

    // Act
    logoutServlet.doPost(request, response);

    // Assert
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link LogoutServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link HttpSession} {@link HttpSession#getAttribute(String)} return {@code null}.</li>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LogoutServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LogoutServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenHttpSessionGetAttributeReturnNull_thenThrowIOException()
      throws IOException, ServletException {
    // Arrange
    LogoutServlet logoutServlet = new LogoutServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn(null);
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doThrow(new IOException("login")).when(response).sendRedirect(Mockito.<String>any());

    // Act and Assert
    assertThrows(IOException.class, () -> logoutServlet.doPost(request, response));
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(httpSession).getAttribute(eq("user_id"));
  }

  /**
   * Test {@link LogoutServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Given {@link HttpSession} {@link HttpSession#getMaxInactiveInterval()} return three.</li>
   *   <li>Then calls {@link HttpServletResponse#setHeader(String, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LogoutServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LogoutServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_givenHttpSessionGetMaxInactiveIntervalReturnThree_thenCallsSetHeader()
      throws IOException, ServletException {
    // Arrange
    LogoutServlet logoutServlet = new LogoutServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getMaxInactiveInterval()).thenReturn(3);
    doNothing().when(httpSession).removeAttribute(Mockito.<String>any());
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doNothing().when(response).sendRedirect(Mockito.<String>any());
    doNothing().when(response).setHeader(Mockito.<String>any(), Mockito.<String>any());

    // Act
    logoutServlet.doPost(request, response);

    // Assert
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(response, atLeast(1)).setHeader(Mockito.<String>any(), Mockito.<String>any());
    verify(httpSession).getAttribute(eq("user_id"));
    verify(httpSession).getMaxInactiveInterval();
    verify(httpSession).removeAttribute(eq("user_id"));
  }

  /**
   * Test {@link LogoutServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then throw {@link IOException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LogoutServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void LogoutServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_thenThrowIOException() throws IOException, ServletException {
    // Arrange
    LogoutServlet logoutServlet = new LogoutServlet();
    HttpSession httpSession = mock(HttpSession.class);
    when(httpSession.getMaxInactiveInterval()).thenReturn(3);
    doNothing().when(httpSession).removeAttribute(Mockito.<String>any());
    when(httpSession.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getSession(anyBoolean())).thenReturn(httpSession);
    HttpServletResponse response = mock(HttpServletResponse.class);
    doThrow(new IOException("login")).when(response).sendRedirect(Mockito.<String>any());
    doNothing().when(response).setHeader(Mockito.<String>any(), Mockito.<String>any());

    // Act and Assert
    assertThrows(IOException.class, () -> logoutServlet.doPost(request, response));
    verify(request).getSession(eq(false));
    verify(response).sendRedirect(eq("login"));
    verify(response, atLeast(1)).setHeader(Mockito.<String>any(), Mockito.<String>any());
    verify(httpSession).getAttribute(eq("user_id"));
    verify(httpSession).getMaxInactiveInterval();
    verify(httpSession).removeAttribute(eq("user_id"));
  }
}
