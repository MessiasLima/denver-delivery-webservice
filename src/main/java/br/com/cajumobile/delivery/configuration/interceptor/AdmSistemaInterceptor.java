package br.com.cajumobile.delivery.configuration.interceptor;

import br.com.cajumobile.delivery.configuration.SecurityConfiguration;
import br.com.cajumobile.delivery.model.Usuario;
import br.com.cajumobile.delivery.model.enun.TipoUsuario;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdmSistemaInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equals("OPTIONS")){
            return true;
        }

        Usuario usuario = (Usuario) request.getAttribute(SecurityConfiguration.AUTHETICATED_USER);
        return usuario.getTipo().equals(TipoUsuario.ADM_SISTEMA);
    }
}
