package kr.co.restfulapi.rest;

import kr.co.restfulapi.dto.MemberDto;
import kr.co.restfulapi.dto.OrderDto;
import kr.co.restfulapi.dto.OrderRequestDto;
import kr.co.restfulapi.dto.SignUpRequestDto;
import kr.co.restfulapi.service.MemberService;
import kr.co.restfulapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDto> findOrder(@PathVariable Long orderId) {
		OrderDto orderDto = orderService.findOrder(orderId);
		return ResponseEntity.ok(orderDto);
	}

    /*@PostMapping
	public ResponseEntity<MemberDto> order(@Valid OrderRequestDto request) {
		OrderDto orderDto = orderService.order(request);
		return ResponseEntity.ok(orderDto);
	}*/

}
