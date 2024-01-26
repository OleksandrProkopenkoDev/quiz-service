package ua.com.quizservice.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

/**
 * The {@code JwtAuthConverter} explains to Spring that our ROLES in the Keycloak is the same roles
 * that we use in @PreAuthorize for method security
 */
@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  private static final String JWT_CLAIM_WITH_CLIENTS = "resource_access";
  private static final String JWT_CLAIM_WITH_ROLES = "roles";
  private static final JwtGrantedAuthoritiesConverter JWT_GRANTED_AUTHORITIES_CONVERTER =
      new JwtGrantedAuthoritiesConverter();

  @Value("${keycloak.clientId}")
  private String keycloakClient;

  @Override
  public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
    final Collection<GrantedAuthority> authorities =
        Stream.concat(
                JWT_GRANTED_AUTHORITIES_CONVERTER.convert(jwt).stream(),
                extractResourceRoles(jwt).stream())
            .collect(Collectors.toSet());

    return new JwtAuthenticationToken(jwt, authorities);
  }

  private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
    if (jwt.getClaim(JWT_CLAIM_WITH_CLIENTS) == null) {
      return Collections.emptySet();
    }

    final Map<String, List<String>> clients = jwt.getClaim(JWT_CLAIM_WITH_CLIENTS);
    final Map<String, List<String>> clientRoles = getClientRoles(clients);

    if (clientRoles == null || clientRoles.get(JWT_CLAIM_WITH_ROLES) == null) {
      return Collections.emptySet();
    }

    final List<String> roles = clientRoles.get(JWT_CLAIM_WITH_ROLES);
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
  }

  private Map<String, List<String>> getClientRoles(Map<String, List<String>> resourceRoles) {
    return (Map<String, List<String>>) resourceRoles.get(keycloakClient);
  }
}
