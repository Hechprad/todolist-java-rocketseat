package br.com.hecherat.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.hecherat.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Indica que esta classe deve ser gerenciada pelo sprint
@Component
// Esta classe será chamada sempre antes de uma requisição
public class FilterTaskAuth extends OncePerRequestFilter {
  // Indica para o Spring que ele deve gerenciar o ciclo de vida
  @Autowired
  private IUserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    var servletPath = request.getServletPath();

    // Fará a verificação de autenticação apenas na rota "/tasks"
    if (servletPath.startsWith("/tasks")) {
      // Resgata o header de autenticação da requisição
      var authorization = request.getHeader("Authorization");
      var encodedAuth = authorization.substring("Basic".length()).trim();

      // Gera um array de bytes
      byte[] decodedAuth = Base64.getDecoder().decode(encodedAuth);

      // Gera uma string contendo o "userName:password"
      var authString = new String(decodedAuth);

      String[] credentials = authString.split(":");
      String userName = credentials[0];
      String password = credentials[1];

      // Busca o usuário e verifica se ele existe
      var user = this.userRepository.findByUserName(userName);
      if (user == null) {
        response.sendError(401);

        // Caso exista, valida a senha
      } else {

        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

        // Se estiver válida, segue com a execução da chamada
        if (passwordVerify.verified) {
          request.setAttribute("userId", user.getId());
          filterChain.doFilter(request, response);
        } else {
          response.sendError(401);
        }
      }
    } else {
      filterChain.doFilter(request, response);
    }

  }

}
