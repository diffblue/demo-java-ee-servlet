package com.diffblue.pov.servlets.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockRequestDispatcher;

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
   *   <li>Given {@link MockHttpSession#MockHttpSession()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegisterServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_givenMockHttpSession() throws IOException, ServletException {
    // Arrange
    RegisterServlet registerServlet = new RegisterServlet();

    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setSession(new MockHttpSession());
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    registerServlet.doGet(request, response);

    // Assert
    assertEquals("WEB-INF/login.jsp", response.getForwardedUrl());
  }

  /**
   * Test {@link RegisterServlet#doGet(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then {@link MockHttpServletResponse} (default constructor) ForwardedUrl is {@code WEB-INF/login.jsp}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegisterServlet#doGet(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.doGet(HttpServletRequest, HttpServletResponse)"})
  public void testDoGet_thenMockHttpServletResponseForwardedUrlIsWebInfLoginJsp() throws IOException, ServletException {
    // Arrange
    RegisterServlet registerServlet = new RegisterServlet();
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    registerServlet.doGet(request, response);

    // Assert
    assertEquals("WEB-INF/login.jsp", response.getForwardedUrl());
  }

  /**
   * Test {@link RegisterServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then {@link MockHttpServletResponse} (default constructor) ForwardedUrl is {@code Resource}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegisterServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_thenMockHttpServletResponseForwardedUrlIsResource() throws IOException, ServletException {
    // Arrange
    RegisterServlet registerServlet = new RegisterServlet();
    MockHttpServletRequest request = mock(MockHttpServletRequest.class);
    when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(new MockRequestDispatcher("Resource"));
    doNothing().when(request).removeAttribute(Mockito.<String>any());
    doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    when(request.getParameter(Mockito.<String>any())).thenReturn("https://example.org/example");
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    registerServlet.doPost(request, response);

    // Assert
    verify(request, atLeast(1)).getParameter(Mockito.<String>any());
    verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
    verify(request, atLeast(1)).removeAttribute(Mockito.<String>any());
    verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    assertEquals("Resource", response.getForwardedUrl());
  }

  /**
   * Test {@link RegisterServlet#doPost(HttpServletRequest, HttpServletResponse)}.
   * <ul>
   *   <li>Then {@link MockHttpServletResponse} (default constructor) ForwardedUrl is {@code Resource}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RegisterServlet#doPost(HttpServletRequest, HttpServletResponse)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void RegisterServlet.doPost(HttpServletRequest, HttpServletResponse)"})
  public void testDoPost_thenMockHttpServletResponseForwardedUrlIsResource2() throws IOException, ServletException {
    // Arrange
    RegisterServlet registerServlet = new RegisterServlet();
    MockHttpServletRequest request = mock(MockHttpServletRequest.class);
    when(request.getRequestDispatcher(Mockito.<String>any())).thenReturn(new MockRequestDispatcher("Resource"));
    doNothing().when(request).removeAttribute(Mockito.<String>any());
    doNothing().when(request).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    when(request.getParameter(Mockito.<String>any())).thenReturn("");
    MockHttpServletResponse response = new MockHttpServletResponse();

    // Act
    registerServlet.doPost(request, response);

    // Assert
    verify(request, atLeast(1)).getParameter(Mockito.<String>any());
    verify(request).getRequestDispatcher(eq("WEB-INF/login.jsp"));
    verify(request, atLeast(1)).removeAttribute(Mockito.<String>any());
    verify(request, atLeast(1)).setAttribute(Mockito.<String>any(), Mockito.<Object>any());
    assertEquals("Resource", response.getForwardedUrl());
  }
}
