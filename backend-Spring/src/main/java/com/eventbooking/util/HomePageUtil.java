package com.eventbooking.util;

import com.eventbooking.repository.CategoryRepository;
import com.eventbooking.service.BookingService;
import com.eventbooking.service.ImageStorageService;
import com.eventbooking.service.UserService;
import com.eventbooking.service.dto.UserDTO;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class HomePageUtil {

    private static BookingService bookingService;
    private static ImageStorageService imageStorageService;
    private static CategoryRepository categoryRepository;
    public HomePageUtil(BookingService bookingService,
                        ImageStorageService imageStorageService,
                        CategoryRepository categoryRepository) {
        HomePageUtil.bookingService = bookingService;
        HomePageUtil.imageStorageService = imageStorageService;
        HomePageUtil.categoryRepository = categoryRepository;
    }

    public static boolean bookedEvent(Long eventId) {
        UserDTO userDTO = SecurityUtil.getCurrentUser();
        if (userDTO == null) {
            return false;
        }
        return bookingService.existsByUserEmailAndEventId(userDTO.getEmail(), eventId);
    }

    public static String getCategoryTitle(Integer categoryId) {
        if(categoryId==null || categoryId==0) return "General";
        return categoryRepository.findById(categoryId).orElseThrow().getTitle();
    }
}
