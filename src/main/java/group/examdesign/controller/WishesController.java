package group.examdesign.controller;

import group.examdesign.service.IWishesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user/api/wishes")
public class WishesController {
    private IWishesService wishesService;
}
