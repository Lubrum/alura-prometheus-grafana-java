package br.com.alura.forum.config.security;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.MapType;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class TokenService {

	private final String expiration;

	private final SecretKey jwtSecretKey;

	private final JwtParser jwtParser;

	public TokenService(
			@Value("${forum.jwt.expiration}") String expiration,
			@Value("${forum.jwt.secret}") String secret
	) {
		this.expiration = expiration;
		this.jwtSecretKey = Keys.hmacShaKeyFor(secret.getBytes(UTF_8));

		final ObjectMapper json = new ObjectMapper();
		final MapType mt = json.getTypeFactory().constructMapType(Map.class, String.class, String.class);
		final ObjectReader jwtReader = json.readerFor(mt);

		this.jwtParser = Jwts.parserBuilder().deserializeJsonWith(bytes -> {
			try {
				return jwtReader.readValue(bytes);
			} catch (final IOException e) {
				throw new RuntimeException(e);
			}
		}).setSigningKey(this.jwtSecretKey).build();
	}

	public String gerarToken(Authentication authentication) {	
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

		return Jwts.builder()
				.setIssuer("API do Fórum da Alura")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(jwtSecretKey, SignatureAlgorithm.HS256)
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			jwtParser.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = jwtParser.parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
