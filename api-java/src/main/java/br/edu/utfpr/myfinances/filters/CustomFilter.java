package br.edu.utfpr.myfinances.filters;

import br.edu.utfpr.myfinances.filters.handlers.ServletFilterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class CustomFilter implements Filter {

    private final ServletFilterHandler servletFilterHandler;

    @Autowired
    public CustomFilter(ServletFilterHandler servletFilterHandler) {
        this.servletFilterHandler = servletFilterHandler;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletFilterHandler.setServletRequest(servletRequest);
        servletFilterHandler.setServletResponse(servletResponse);

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
