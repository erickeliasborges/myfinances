package br.edu.utfpr.myfinances.filters.handlers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Getter
@Setter
@Component
public class ServletFilterHandler {

    private ServletRequest servletRequest;
    private ServletResponse servletResponse;

}
