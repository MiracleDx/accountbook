package com.dongx.accountbook.note.web;

import com.dongx.accountbook.note.config.DynamicDataSource;
import com.dongx.accountbook.note.model.MultiTenancy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * HelloController
 *
 * @author Dongx
 * Description:
 * Created in: 2019-10-19 23:18
 * Modified by:
 */
@RestController
public class HelloController {
	
	@Resource
	private DynamicDataSource dynamicDataSource;
	
	@GetMapping
	public Mono<Object> hello(ServerWebExchange exchange) {
		return exchange.getSession().flatMap(session -> {
			String db = null;
			try {
				db = dynamicDataSource.getConnection().getCatalog().toString();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MultiTenancy multiTenancy = new MultiTenancy()
					.withTenancyDb("tenancy1")
					.withTenancyName(db);
			session.getAttributes().put(session.getId(), multiTenancy);
			return Mono.just(session);
		});
		//return Mono.just("welcome to account_book");
	}
}
