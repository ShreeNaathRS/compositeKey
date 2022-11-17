package com.shree.compositeKey;

import com.shree.compositeKey.dto.CustomerDTO;
import com.shree.compositeKey.dto.ItemDTO;
import com.shree.compositeKey.dto.OrderDTO;
import com.shree.compositeKey.dto.OrderItemDTO;
import com.shree.compositeKey.entity.Item;
import com.shree.compositeKey.entity.Order;
import com.shree.compositeKey.entity.OrderItem;
import com.shree.compositeKey.service.CustomerService;
import com.shree.compositeKey.service.ItemService;
import com.shree.compositeKey.service.OrderItemService;
import com.shree.compositeKey.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import static com.shree.compositeKey.enums.Category.*;
import static java.time.LocalDate.now;
import static java.time.LocalDate.of;

@SpringBootApplication
public class CompositeKeyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompositeKeyApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ItemService itemService, CustomerService customerService, OrderService orderService, OrderItemService orderItemService) {
		return args -> {
			createItem(itemService);
			createCustomer(customerService);
			createOrder(customerService, orderService, itemService, orderItemService);
		};
	}

	private void createCustomer(CustomerService customerService) {
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		customerDTOList.add(new CustomerDTO(null, "Shree Naath R S","9940409540","rsshreenaath@gmail.com","2-Thiruvalluvar Nagar","Madhavaram Milk Colony","Tamil Nadu","Chennai","600051"));
		customerDTOList.add(new CustomerDTO(null, "Guru Prasad R S","9940401362","gurursprasad@gmail.com","2-Thiruvalluvar Nagar","Madhavaram Milk Colony","Tamil Nadu","Chennai","600051"));
		customerDTOList.add(new CustomerDTO(null, "Deeps","8072010867",null,"10-3rd cross street, Lakshmi Nagar","Lakshmipuram Road","Tamil Nadu","Chennai","600056"));
		customerDTOList.forEach(customer-> {
			try {
				customerService.saveCustomer(customer);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	private void createItem(ItemService itemService) {
		List<ItemDTO> itemDTOList = new ArrayList<>();
		itemDTOList.add(new ItemDTO(null, ELECTRONICS, "Mobile", "Samsung","S10",10499.00, 0.15, "Brown", true, now().plusYears(1L), of(2021, 5, 2)));
		itemDTOList.add(new ItemDTO(null, ELECTRONICS, "Mobile","Samsung","S11", 20000.00, 0.15, "Black", true, now().plusYears(1L), of(2020, 3, 10)));
		itemDTOList.add(new ItemDTO(null, ELECTRONICS, "Mobile", "Motorola","M12",13999.00, 0.17, "Silver", true, now().plusYears(2L), of(2015, 1, 6)));
		itemDTOList.add(new ItemDTO(null, ELECTRONICS, "Mobile", "Motorola","M22",24999.00, 0.25, "Blue", true, now().plusYears(2L), of(2021, 5, 2)));
		itemDTOList.add(new ItemDTO(null, ELECTRONICS, "Headphone", "boAt","141",1349.00, 0.01, "Black", false, null, of(2021, 5, 2)));
		itemDTOList.add(new ItemDTO(null, ELECTRONICS, "Headphone", "boAt","121",1199.00, 0.01, "Black", false, null, of(2021, 5, 2)));
		itemDTOList.add(new ItemDTO(null, FURNITURE, "Sofa", "Vivek","Honey Finish",35639.00, 25.00, "Dark Honey", false, null, of(2021, 5, 2)));
		itemDTOList.add(new ItemDTO(null, FURNITURE, "Sofa", "Wakefit","L Shape",41902.00, 27.00, "Omega Blue", false, null, of(2021, 5, 2)));
		itemDTOList.add(new ItemDTO(null, FURNITURE, "Mattress", "SleepX","Medium Soft",6446.00, 15.00, "Wood", false, null, of(2021, 5, 2)));
		itemDTOList.add(new ItemDTO(null, FURNITURE, "Mattress", "Deevine","Single Firm",588.00, 12.00, "Grey", false, null, of(2021, 5, 2)));
		itemDTOList.add(new ItemDTO(null, TRAVEL, "Bag", "Medler","55 litres",499.00, 1.00, "Navy Blue", true, now().plusYears(1L), of(2021, 5, 2)));
		itemDTOList.add(new ItemDTO(null, TRAVEL, "Bag", "Medler","55 litres",499.00, 1.00, "Wine", true, now().plusYears(1L), of(2021, 5, 2)));
		itemDTOList.add(new ItemDTO(null, TRAVEL, "Bag", "Lavie Sport","63 litres",1409.00, 10.00, "Navy", true, now().plusYears(1L), of(2015, 10, 2)));
		itemDTOList.add(new ItemDTO(null, TRAVEL, "Bag", "Lavie Sport","63 litres",1409.00, 10.00, "Black", true, now().plusYears(1L), of(2015, 10, 2)));
		itemDTOList.add(new ItemDTO(null, TRAVEL, "Pillow", "Fur Jaden","Foam luxury",749.00, 0.01, "Black", false, null, of(2010, 2, 7)));
		itemDTOList.add(new ItemDTO(null, TRAVEL, "Pillow", "Fur Jaden","Foam luxury",749.00, 0.01, "Grey", false, null, of(2010, 2, 7)));
		itemDTOList.add(new ItemDTO(null, TRAVEL, "Pillow", "Trajectory","Rest cushion",499.00, 0.02, "Grey", false, null, of(2022, 2, 7)));
		itemDTOList.add(new ItemDTO(null, TRAVEL, "Pillow", "Trajectory","Rest cushion",499.00, 0.02, "Black Combo", false, null, of(2022, 2, 7)));
		itemDTOList.add(new ItemDTO(null, TRAVEL, "Pillow", "Trajectory","Rest cushion",499.00, 0.02, "Blue", false, null, of(2022, 2, 7)));
		itemDTOList.add(new ItemDTO(null, GROCERY, "Oil", "Fortune","Sum flower",155.00, 1.00, null, true, now().plusMonths(6L), of(2022, 5, 7)));
		itemDTOList.add(new ItemDTO(null, GROCERY, "Oil", "Idhayam","Sum flower",215.15, 1.00, null, true, now().plusMonths(6L), of(2022, 4, 2)));
		itemDTOList.add(new ItemDTO(null, GROCERY, "Salt", "TATA","Vaccum Evaporated",25.00, 1.00, null, true, now().plusMonths(10L), of(2022, 8, 2)));
		itemDTOList.add(new ItemDTO(null, GROCERY, "Peanut", "Vedaka","Raw",235.00, 1.00, null, true, now().plusMonths(12L), of(2022, 3, 2)));
		itemDTOList.add(new ItemDTO(null, GROCERY, "Wallnut", "Vedaka","Raw",350.00, 1.00, null, true, now().plusMonths(18L), of(2022, 1, 10)));
		itemDTOList.forEach(item-> {
			try {
				itemService.saveItem(item);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	private void createOrder(CustomerService customerService, OrderService orderService, ItemService itemService, OrderItemService orderItemService) throws Exception {
		CustomerDTO customerDTO = customerService.findByPhoneNumber("9940409540");
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setCustomer(customerService.toEntity(customerDTO));
		orderDTO.setDate(now());
		orderDTO.setTotal(0.0);
		Item item1 = itemService.toEntity(itemService.findById(1L));
		Order order = orderService.toEntity(orderDTO);
		OrderItem orderItem = new OrderItem(null, order, item1,2);
		OrderItemDTO orderItemDTO = orderItemService.toDto(orderItem);
		orderItemService.saveOrderItem(orderItemDTO);
		OrderDTO orderSaved = orderService.getOrder(1L);
	}

}