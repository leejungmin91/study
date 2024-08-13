package com.min.store.product.service

import com.min.store.product.domain.ProductCreateDomainKotlin
import com.min.store.product.domain.ProductUpdateDomainKotlin
import com.min.store.product.entity.ProductKotlinEntity
import com.min.store.product.repository.ProductKotlinRepository
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
internal class ProductKotlinServiceTest {
    private val productRepository: ProductKotlinRepository = mockk()
    private val productService: ProductKotlinService = ProductKotlinService(productRepository)

    private val expectedProductEntity = ProductKotlinEntity(name = "연필", price = 100L)

    @Test
    fun `상품_저장_성공_테스트`() {
        every { productRepository.existsByName("연필") } returns false
        every { productRepository.save(match { (it.name == "연필") && (it.price == 100L) }) } returns expectedProductEntity

        val createProductDomain = ProductCreateDomainKotlin("연필", 100L)
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
        val updateProductEntity = ProductKotlinEntity(name = "연필", price = 200L)
        every { productRepository.save(match { (it.name == "연필") && (it.price == 200L) }) } returns updateProductEntity

        val updateProductDomain = ProductUpdateDomainKotlin("연필", 200L)
        val updateProduct = productService.update(0L, updateProductDomain)

        updateProduct.price shouldNotBe expectedProductEntity.price
    }
}
