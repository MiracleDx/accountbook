package com.dongx.accountbook.note.filter;

import com.dongx.accountbook.note.config.DynamicDataSourceContextHolder;
import com.dongx.accountbook.note.config.TenancyMetaData;
import com.dongx.accountbook.note.model.MultiTenancy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * DynamicDataSourceFilter
 *
 * @author Dongx
 * Description:
 * Created in: 2019-10-19 22:24
 * Modified by:
 */
@Slf4j
@Component
@Order(-1)
public class LoginFilter implements WebFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		return exchange.getSession().flatMap(session -> {
			log.info("into login filter");
			String sessionId = session.getId();
			Object obj = session.getAttribute(sessionId);
			
			// 如果已经登陆 判断租户id
			if (Objects.nonNull(obj)) {
				MultiTenancy multiTenancy = null;
				// session中的属性值校验
				if (!(obj instanceof MultiTenancy)) {
					exchange.getResponse().setStatusCode(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
					return Mono.empty();
				}
			
				// 租户id校验
				multiTenancy = (MultiTenancy) obj;
				String tenancyDb = multiTenancy.getTenancyDb();
				if (StringUtils.isEmpty(tenancyDb)) {
					exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
					return Mono.empty();
				} else {
					// 是否是已有租户
					boolean isTenancy = TenancyMetaData.getTenancyDataSource().containsKey(tenancyDb);
					if (isTenancy) {
						DynamicDataSourceContextHolder.setDataSourceKey(tenancyDb);
					} else {
						exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
						return Mono.empty();
					}
				}
			}
			return chain.filter(exchange);
		});
	}
}
