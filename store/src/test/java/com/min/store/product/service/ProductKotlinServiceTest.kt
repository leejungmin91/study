package com.min.store.product.service

import com.min.store.product.domain.ProductCreateDomainKotlin
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

    private val expectedProductEntity = ProductKotlinEntity(name = "연필", price = 100)

    @Test
    fun `상품_저장_성공_테스트`() {
        every { productRepository.existsByName("연필") } returns false
        every { productRepository.save(any<ProductKotlinEntity>()) } returns expectedProductEntity

        val createProductDomain = ProductCreateDomainKotlin("연필", 100)
        val createProduct = productService.register(createProductDomain)

        createProduct.name shouldBe expectedProductEntity.name
    }

    @Test
    fun `상품_조회_성공_테스트`() {
        // given
        every { productRepository.findByName("연필") } returns expectedProductEntity

        // when
        val searchedProduct = productService.getByName("연필")

        // then
        searchedProduct.name shouldBe expectedProductEntity.name
    }
}
