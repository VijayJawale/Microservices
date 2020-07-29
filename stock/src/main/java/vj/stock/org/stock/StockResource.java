package vj.stock.org.stock;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class StockResource {
	@Autowired
	private StockRepository repository;
	private static final Logger logger = LoggerFactory.getLogger(StockResource.class);

	@PostConstruct
	public void init() {
		repository.save(new Stock("IBM", 100, 100));
		repository.save(new Stock("VMware", 100, 100));
		repository.save(new Stock("Dell", 100, 100));
		repository.save(new Stock("CCD", 100, 100));
	}

	@PostMapping(value = "/stocks")
	@ResponseBody
	public ResponseEntity<Stock> postMethodName(@RequestBody Stock entity) {
		logger.info("");
		Stock s = repository.save(entity);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(s.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping(value = "/stocks/{id}")
	@ResponseBody
	public Stock getStock(@PathVariable String id) {
		logger.info("");
		Stock s = repository.findById(UUID.fromString(id)).get();
		return s;
	}

	@DeleteMapping(value = "/stocks/{id}")
	@ResponseBody
	public Stock deleteStock(@PathVariable String id) {
		logger.info("");
		Stock s = repository.findById(UUID.fromString(id)).get();
		repository.deleteById(UUID.fromString(id));
		return s;
	}

	@GetMapping(value = "/stocks")
	public List<Stock> getMethodName() {
		return repository.findAll();
	}

}
