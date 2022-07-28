package com.server.erentronic.item.keyboard.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.erentronic.item.keyboard.Keyboard;
import com.server.erentronic.item.product.Product;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

@DataJpaTest
class KeyboardRepositoryTest {


	@Autowired
	KeyboardRepository keyboardRepository;

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	VendorRepository vendorRepository;

	@Autowired
	ConnectionRepository connectionRepository;

	@Autowired
	LayoutRepository layoutRepository;

	@Test
	void testProduct() {

		Keyboard keyboard = Keyboard.builder()
			.vendor(vendorRepository.findById(1L).get())
			.connection(connectionRepository.findById(1L).get())
			.layout(layoutRepository.findById(1L).get())
			.build();

		keyboardRepository.save(keyboard);

		List<Product> all = productRepository.findAll();
//		System.out.println("size" + all.size());
		System.out.println(keyboardRepository.findAll());

//		System.out.println(productRepository.findAll());


//		PageRequest pageRequest = PageRequest.of(0, 3);
//		Slice<Product> products = productRepository.findBy(pageRequest);

//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			String string = mapper.writeValueAsString(products);
//			System.out.println(string);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
	}

//	@Test
	void test() {

		Keyboard keyboard = Keyboard.builder()
			.vendor(vendorRepository.findById(1L).get())
			.connection(connectionRepository.findById(1L).get())
			.layout(layoutRepository.findById(1L).get())
			.build();

		keyboardRepository.save(keyboard);

		PageRequest pageRequest = PageRequest.of(5, 3);
		Slice<Keyboard> keyboards = keyboardRepository.findBy(pageRequest);
		System.out.println(keyboards.getContent());
		for (Keyboard keyboard1 : keyboards) {
			System.out.println(keyboard1);
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			String string = mapper.writeValueAsString(keyboards);
			System.out.println(string);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}
}
