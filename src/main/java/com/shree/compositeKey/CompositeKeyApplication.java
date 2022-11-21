package com.shree.compositeKey;

import com.shree.compositeKey.dto.CustomerDTO;
import com.shree.compositeKey.dto.ItemDTO;
import com.shree.compositeKey.dto.OrderDTO;
import com.shree.compositeKey.entity.Customer;
import com.shree.compositeKey.entity.Item;
import com.shree.compositeKey.entity.Order;
import com.shree.compositeKey.entity.OrderItem;
import com.shree.compositeKey.enums.Category;
import com.shree.compositeKey.service.CustomerService;
import com.shree.compositeKey.service.ItemService;
import com.shree.compositeKey.service.OrderItemService;
import com.shree.compositeKey.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static com.shree.compositeKey.enums.Category.*;
import static java.time.LocalDate.now;
import static java.time.LocalDate.of;
import static java.util.stream.Collectors.*;

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
			streamExcersice(itemService, customerService, orderService, orderItemService);
		};
	}

	private void createCustomer(CustomerService customerService) {
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		customerDTOList.add(new CustomerDTO(null, "Shree Naath R S","9940409540","rsshreenaath@gmail.com","2-Thiruvalluvar Nagar","Madhavaram Milk Colony","Tamil Nadu","Chennai","600051"));
		customerDTOList.add(new CustomerDTO(null, "Guru Prasad R S","9940401362","gurursprasad@gmail.com","2-Thiruvalluvar Nagar","Madhavaram Milk Colony","Tamil Nadu","Chennai","600051"));
		customerDTOList.add(new CustomerDTO(null, "Sridhar C R","9444346591","sridhar10051960@gmail.com","2-Thiruvalluvar Nagar","Madhavaram Milk Colony","Tamil Nadu","Chennai","600051"));
		customerDTOList.add(new CustomerDTO(null, "Padma V S","9445309945","vspadma1966@gmail.com","2-Thiruvalluvar Nagar","Madhavaram Milk Colony","Tamil Nadu","Chennai","600051"));
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

	private void createOrder(CustomerService customerService, OrderService orderService, ItemService itemService, OrderItemService orderItemService) {
		List<OrderDetails> orderDetailsList = new ArrayList<>();
		Map<Long, Integer> itemQuantity1 = new HashMap<>();
		itemQuantity1.put(1L, 2);
		itemQuantity1.put(2L, 1);
		Map<Long, Integer> itemQuantity2 = new HashMap<>();
		itemQuantity2.put(7L, 1);
		itemQuantity2.put(10L, 4);
		itemQuantity2.put(12L, 3);
		itemQuantity2.put(13L, 2);
		itemQuantity2.put(21L, 5);
		itemQuantity2.put(23L, 3);
		Map<Long, Integer> itemQuantity3 = new HashMap<>();
		itemQuantity3.put(4L, 2);
		itemQuantity3.put(8L, 1);
		itemQuantity3.put(10L, 10);
		itemQuantity3.put(2L, 1);
		Map<Long, Integer> itemQuantity4 = new HashMap<>();
		itemQuantity4.put(5L, 2);
		itemQuantity4.put(21L, 2);
		itemQuantity4.put(9L, 2);
		itemQuantity4.put(13L, 3);
		itemQuantity4.put(22L, 2);
		itemQuantity4.put(8L, 1);
		orderDetailsList.add(new OrderDetails("9940409540", itemQuantity1));
		orderDetailsList.add(new OrderDetails("9445309945", itemQuantity2));
		orderDetailsList.add(new OrderDetails("9444346591", itemQuantity3));
		orderDetailsList.add(new OrderDetails("8072010867", itemQuantity4));
		createActualOrder(customerService, orderService, itemService, orderItemService, orderDetailsList);
	}

	private void createActualOrder(CustomerService customerService, OrderService orderService, ItemService itemService, OrderItemService orderItemService,
								   List<OrderDetails> orderDetails) {
		AtomicInteger i= new AtomicInteger();
		orderDetails.forEach(o -> {
			i.getAndIncrement();
			CustomerDTO customerDTO = customerService.findByPhoneNumber(o.getCustomerPhoneNumber());
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setCustomer(customerService.toEntity(customerDTO));
			if(i.get() == 1){
				orderDTO.setDate(now().minusMonths(2));
			}
			else{
				orderDTO.setDate(now());
			}
			orderDTO.setTotal(0.0);
			orderDTO.setOrderItems(new HashSet<>());
			Order order = orderService.toEntity(orderDTO);
			try {
				order = orderService.toEntity(orderService.saveOrder(orderService.toDto(order)));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			Order finalOrder = order;
			o.getItemQuantity().forEach((itemId, quantity) -> {
				Item item = itemService.toEntity(itemService.findById(itemId));
				OrderItem orderItem = new OrderItem(null, finalOrder, item, quantity);
				try {
					orderItemService.saveOrderItem(orderItemService.toDto(orderItem));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
		});
	}

	private void streamExcersice(ItemService itemService, CustomerService customerService, OrderService orderService, OrderItemService orderItemService) {
//		exercise1(orderService);
//		exercise2(orderService, TRAVEL);
//		exercise3(orderService, itemService);
		exercise4(orderService, itemService);
	}

	private List<Order> getAllOrders(OrderService orderService){
		return orderService.getOrderRepo().findAll();
	}

	private List<Item> getAllItems(ItemService itemService){
		return itemService.findAll();
	}

	private Predicate<OrderItem> filterItemCategory(Category category){
		return orderItem -> orderItem.getItem().getCategory().equals(category);
	}

//	Get customers who orders greater than 50000
	private void exercise1(OrderService orderService) {
		List<Order> allOrders = getAllOrders(orderService);
		List<Customer> customerList = allOrders.stream()
				.filter(order -> order.getTotal() > 50000)
				.map(Order::getCustomer)
				.collect(toList());
		System.out.println(customerList);
	}

//	Get the customer who buys a given category the most
	private void exercise2(OrderService orderService, Category category) {
		List<Order> allOrders = getAllOrders(orderService);
		Map<Customer, List<Order>> ordersGroupedByCustomer = allOrders.stream()
				.filter(order -> order.getOrderItems().stream()
						.anyMatch(filterItemCategory(category)))
				.collect(groupingBy(Order::getCustomer));
		Map<Customer, Integer> customerQuantity = ordersGroupedByCustomer.entrySet().stream()
				.collect(toMap(Map.Entry::getKey, o -> o.getValue().stream()
						.map(Order::getOrderItems)
						.mapToInt(orderItems -> orderItems.stream()
								.filter(filterItemCategory(category))
								.mapToInt(OrderItem::getQuantity).sum()
						).sum()
				));
		Customer customer = customerQuantity.entrySet().stream()
				.max(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey)
				.orElse(null);
		System.out.println(customer);
	}

//	Get items that are not present in any order
	private void exercise3(OrderService orderService, ItemService itemService) {
		List<Item> allItems = getAllItems(itemService);
		List<Order> allOrders = getAllOrders(orderService);
		List<Item> unusedItems = allItems.stream()
				.filter(item -> allOrders.stream()
						.flatMap(order -> order.getOrderItems().stream())
						.noneMatch(orderItem -> orderItem.getItem().getId().equals(item.getId())))
				.collect(toList());
		System.out.println(unusedItems);
	}

//	Get items that was last sold before one month
	private void exercise4(OrderService orderService, ItemService itemService) {
		List<Order> allOrders = getAllOrders(orderService);
		List<Item> allItems = getAllItems(itemService);
		Map<Boolean, List<Order>> partitionedOrders = allOrders.stream()
				.collect(partitioningBy(order -> order.getDate().isBefore(now().minusMonths(1))));
		Map<Boolean, List<Item>> partitionedItems = partitionedOrders.entrySet().stream()
				.collect(toMap(Map.Entry::getKey, order -> order.getValue().stream()
						.flatMap(o -> o.getOrderItems().stream()
								.map(orderItem -> orderItem.getItem())).collect(toList())));
		List<Long> itemIds = allItems.stream()
				.map(Item::getId)
				.filter(value -> partitionedItems.get(true).stream().anyMatch(item -> item.getId().equals(value))
						&& partitionedItems.get(false).stream().noneMatch(item -> item.getId().equals(value)))
				.collect(toList());
		System.out.println(itemIds);
	}

	/*
 TODO: 20-11-2022 Complete the below exercises
1. Get orders in which any one item is expired
2. Get count of items sold based on category level
3. Get quantity of item sold the max per order
4. Get customer who has spent the most
5. Get total of all orders in the current month
*/

}

@Data
@AllArgsConstructor
class OrderDetails {
	private String customerPhoneNumber;
	private Map<Long, Integer> itemQuantity;
}