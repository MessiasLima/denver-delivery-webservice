package br.com.cajumobile.delivery.configuration.interceptor;

import br.com.cajumobile.delivery.configuration.SecurityConfiguration;
import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.persistence.NoResultException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@WebFilter
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {

            if (request.getMethod().equals("OPTIONS")){
                return true;
            }

            String authorization = request.getHeader("Authorization");
            Usuario usuario = usuarioService.findByAuthorization(authorization);
            request.setAttribute(SecurityConfiguration.AUTHETICATED_USER, usuario);
            return true;
        } catch (EmptyResultDataAccessException | NoResultException noResultException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
