package com.hse.flowerapp.service.impl;

import com.hse.flowerapp.domain.Item;
import com.hse.flowerapp.domain.Order;
import com.hse.flowerapp.domain.Review;
import com.hse.flowerapp.domain.Status;
import com.hse.flowerapp.dto.ReviewDto;
import com.hse.flowerapp.repository.ItemRepository;
import com.hse.flowerapp.repository.OrderRepository;
import com.hse.flowerapp.repository.ReviewRepository;
import com.hse.flowerapp.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, OrderRepository orderRepository, ItemRepository itemRepository) {
        this.reviewRepository = reviewRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }


    @Override
    public ReviewDto createItemReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setUserId(reviewDto.getUserId());
        review.setItemId(reviewDto.getItemId());
        review.setGrade(reviewDto.getGrade());
        review.setBody(reviewDto.getBody());
        review.setHeader(reviewDto.getHeader());
        review.setStatus(Status.ACTIVE);
        review.setOrderId(reviewDto.getOrderId());
        Date date = new Date();
        review.setCreated(date);
        review.setUpdated(date);
        reviewRepository.save(review);

        Order order = orderRepository.getById(reviewDto.getOrderId());
        order.setIsRated(true);
        orderRepository.save(order);

        Item item = itemRepository.getItemById(reviewDto.getItemId());
        Integer count = item.getReviewCount();
        Float rating = item.getRating();

        item.setRating((rating*count + reviewDto.getGrade())/(count + 1));
        item.setReviewCount(count + 1);
        itemRepository.save(item);

        return reviewDto;
    }

    @Override
    public List<ReviewDto> itemReviews(Long item_id) {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDto> myReviews = new ArrayList<>();
        reviews.forEach(review -> {
            if(review.getItemId().equals(item_id))
                myReviews.add(ReviewDto.convertReviewToReviewDTO(review));
        });

        return myReviews;
    }
}
