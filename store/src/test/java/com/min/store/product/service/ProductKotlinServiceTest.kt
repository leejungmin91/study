package com.min.store.product.service

import com.min.store.product.entity.ProductKotlinEntity
import com.min.store.product.repository.ProductKotlinRepository
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ProductKotlinServiceTest {
    private val productRepository: ProductKotlinRepository = mockk()
    private val productService: ProductKotlinService = ProductKotlinService(productRepository)

    @Test
    fun 상품_조회_성공_테스트() {
        // given
        val expectedProductEntity = ProductKotlinEntity(id = 0, name = "연필", price = 100)
        every { productRepository.findByName("연필") } returns expectedProductEntity

        // when
        val searchedProduct = productService.getByName("연필")

        // then
        searchedProduct.name shouldBe expectedProductEntity.name
    }
}
