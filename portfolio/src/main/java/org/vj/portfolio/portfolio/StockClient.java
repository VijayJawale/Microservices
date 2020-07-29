package org.vj.portfolio.portfolio;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "Stocks-Service", path = "stocks")
@RibbonClient(name = "Stocks-Service")
public interface StockClient {

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	List<Stock> getStocks();

	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
	Stock getStockById(@PathVariable("postId") Long id);
}