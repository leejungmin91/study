package com.min.store.product.service

import com.min.store.product.domain.ProductCreateDomain
import com.min.store.product.domain.ProductUpdateDomain
import com.min.store.product.entity.ProductEntity
import com.min.store.product.repository.ProductRepository
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
internal class ProductKotlinServiceTest {
    private val productRepository: ProductRepository = mockk()
    private val productService: ProductService = ProductService(productRepository)

    private val expectedProductEntity = ProductEntity(name = "연필", price = 100L)

    @Test
    fun `상품_저장_성공_테스트`() {
        every { productRepository.existsByName("연필") } returns false
        every { productRepository.save(match { (it.name == "연필") && (it.price == 100L) }) } returns expectedProductEntity

        val createProductDomain = ProductCreateDomain("연필", 100L)
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

    @Test
    fun `상품_수정_성공_테스트`() {
        every { productRepository.findByIdOrNull(0L) } returns expectedProductEntity
        val updateProductEntity = ProductEntity(name = "연필", price = 200L)
        every { productRepository.save(match { (it.name == "연필") && (it.price == 200L) }) } returns updateProductEntity

        val updateProductDomain = ProductUpdateDomain("연필", 200L)
        val updateProduct = productService.update(0L, updateProductDomain)

        updateProduct.price shouldNotBe expectedProductEntity.price
    }
}
