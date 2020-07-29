package org.vj.portfolio.portfolio;

import java.math.BigInteger;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PortfolioResource {

	@Autowired
	private PortfolioRepository repository;
	@Autowired
	private StockClient stockClient;

	@PostMapping(value = "/portfolios")
	@ResponseBody
	public ResponseEntity<Portfolio> postMethodName(@RequestBody Portfolio entity) {
		Portfolio p = repository.save(entity);
		repository.flush();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getId()).toUri();

		return ResponseEntity.created(location).body(p);
	}

	@GetMapping(value = "/portfolios/{id}")
	public Portfolio getMethodName(@RequestParam String id) {
		return repository.findById(UUID.fromString(id)).get();
	}

	@GetMapping(value = "/portfolios")
	public List<Portfolio> getMethodName() {
		List<Stock> stocks = stockClient.getStocks();
		Map<String, List<Stock>> map = stocks.stream().collect(Collectors.groupingBy(s -> s.getName()));
		System.out.println(stocks);
		List<Portfolio> portfolios = repository.findAll();
		for (Portfolio portfolio : portfolios) {
			for (Symbol s : portfolio.getSymbols()) {
				List<Stock> sts = map.getOrDefault(s.getName(), Collections.emptyList());
				if (!sts.isEmpty())
					s.setPrice(sts.get(0).getPrice() * s.getQty().longValue());
			}

		}

		return portfolios;
	}

}
